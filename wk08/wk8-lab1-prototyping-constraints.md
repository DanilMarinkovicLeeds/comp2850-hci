# Week 8 • Lab 1 — Prototyping with Constraints: Partials, Pagination, Filtering

![COMP2850](https://img.shields.io/badge/COMP2850-HCI-blue)
![Week 8](https://img.shields.io/badge/Week-8-orange)
![Lab 1](https://img.shields.io/badge/Lab-1-green)
![Status](https://img.shields.io/badge/Status-Draft-yellow)

> We keep saying “server-first for everyone, HTMX for enhancement.” Scaling that pattern to dozens or hundreds of tasks introduces new constraints. Today you will factor templates, add filtering and pagination, and document the trade-offs you are making.

---

## Why this lab matters
- Template partials reduce duplication and keep accessibility fixes in one place.
- Pagination and filtering control cognitive load and network cost when lists grow.
- History (`hx-push-url`) and live status updates maintain web affordances (back button, result count) for all participants.
- Documented trade-offs help you justify design decisions in critiques and assessments.

This lab feeds directly into Week 8 Lab 2 (no-JS parity verification) and sets up metrics collection in Week 9.

---

## Pre-lab reading (15 min)
- [HTMX Examples — Active Search](https://htmx.org/examples/active-search/)
- [HTMX reference: `hx-push-url`](https://htmx.org/attributes/hx-push-url/) and [`hx-trigger`](https://htmx.org/attributes/hx-trigger/)
- [Pebble template inheritance](https://pebbletemplates.io/wiki/tag/extends/) and include syntax

Keep the [Pebble Cheatsheet](../references/pebble-cheatsheet.md) and [HTMX Patterns](../references/htmx-patterns.md) open for reference.

---

## Learning Focus

### Lab Objectives
By the end of this session, you will have:
- Factored the tasks page into reusable Pebble partials (`_layout`, `_list`, `_item`, `_pager`)
- Added production-ready UI with header, navigation, and footer partials
- Implemented filtering + pagination with a dual-path architecture (full-page and HTMX fragment routes)
- Maintained accessible result announcements (`aria-describedby`) and predictable focus
- Used `hx-push-url` so history and bookmarking work across pages
- Documented trade-offs in server-side vs client-side filtering

### Learning Outcomes Addressed
This lab contributes to the following module Learning Outcomes ([full definitions](../references/learning-outcomes.md)):

- **LO5**: Create interface prototypes — evidenced by HTMX partials implementation
- **LO7**: Analyse design constraints — evidenced by pagination trade-offs document
- **LO13**: Integrate HCI with SE — evidenced by server-rendered fragments

---
---

## Background: Inclusive prototyping at scale
Why refactor now? As lists grow, duplication becomes dangerous: accessibility fixes, aria hooks, and HTMX attributes drift if they live in multiple places. Partials keep the source of truth in one file so every flow (full page and fragment) inherits the same semantics.

Pagination and filtering are not just performance tricks; they control cognitive load, especially for screen-reader participants who would otherwise wade through hundreds of list items. Implement them with inclusive defaults: predictable focus, result announcements, and URLs that respect the back button.

Keep these references handy while you work:
- [Pebble Cheatsheet](references/pebble-cheatsheet.md)
- `../references/template-map-week8.md` (visual map of the partials you are about to create)
- `../references/htmx-pattern-cheatsheet.md` (Active Search, OOB status, indicators)
- [MDN: Accessible Pagination Patterns](https://developer.mozilla.org/en-US/docs/Web/Accessibility/Understanding_WCAG/Navigation_and_orienting#pagination)

> **Visual**: Template hierarchy for the tasks UI

{{#include ../references/visuals/template-hierarchy.md}}

<small>Full legend in [Process Visuals](../references/process-visuals.md#template-hierarchy).</small>

### Worked example: Active Search with parity
The filter form uses HTMX for fast updates, but must still function when JS is disabled. Compare the enhanced and fallback behaviours:

```html
<form action="/tasks" method="get"
      hx-get="/tasks/fragment"
      hx-target="#task-area"
      hx-trigger="keyup changed delay:300ms, submit from:closest(form)"
      hx-push-url="true">
  <label for="q">Filter tasks</label>
  <input id="q" name="q" value="{{ query|default('') }}" type="search"
         aria-describedby="q-hint">
  <small id="q-hint">Type to filter; works without JavaScript.</small>
  <button type="submit">Apply</button>
</form>
```

- **HTMX path**: typing triggers `/tasks/fragment`, which returns `_list` + `_pager` + a status update (`Found X tasks`). `hx-push-url"true"` keeps the URL in sync so back/forward works.
- **No-JS path**: pressing Enter submits the form to `/tasks`; the full page renders using the same partials. Because labels and hints live in the partial, accessibility remains intact in both cases.

When verifying parity, disable JS and watch the address bar: URLs should still reflect the current query and page so participants can bookmark or share the state.

---

## 0. Baseline check (5 min)
Run `./gradlew run` and load `http://localhost:8080/tasks`:
- Confirm add/edit/delete from Week 7 still work with JS on.
- Disable JavaScript and re-run the same flows to ensure parity.
- If anything regressed, fix it before adding complexity.

---

## 1. Factor templates into partials (40 min)

> **💡 Template Naming Convention: Underscore Prefix**
>
> Starting in Week 8, we adopt a **underscore prefix** (`_`) for **partial templates**:
>
> | Name | Type | Purpose | Example |
> |------|------|---------|---------|
> | `index.peb` | **Full page** | Complete HTML document (extends base) | `tasks/index.peb` |
> | `_item.peb` | **Partial** | Fragment included in other templates | `tasks/_item.peb` |
> | `_list.peb` | **Partial** | Reusable component (used multiple times) | `tasks/_list.peb` |
> | `_layout/base.peb` | **Layout** | Shared page structure | `_layout/base.peb` |
>
> **Why the underscore?**
> - **Visual distinction**: Instantly recognizable as a partial (not a standalone page)
> - **Common convention**: Used by Rails, Jekyll, Sass, and other template systems
> - **Prevent direct access**: In some frameworks, `_` files are not served directly
>
> **Rule of thumb**:
> - **No underscore** = Full page that can be rendered directly (e.g., `/tasks` → `tasks/index.peb`)
> - **Underscore** = Fragment included via `{% include %}` or HTMX `hx-get`
>
> **This week's structure**:
> ```
> templates/
> ├── _layout/
> │   └── base.peb        ← Shared layout (underscore because it's a partial)
> └── tasks/
>     ├── index.peb       ← Full page (no underscore)
>     ├── _item.peb       ← Partial: single task <li>
>     ├── _list.peb       ← Partial: task list <ul>
>     └── _pager.peb      ← Partial: pagination nav
> ```

We want a structure like the one in `../references/template-map-week8.md`.

### 1.1 Base layout
Create `templates/_layout/base.peb` with the shared head, status region, skip link, Pico CSS, HTMX script, and utility classes. This keeps accessibility hooks (live region, focus outline, visually hidden class) in a single place.

### 1.2 Task item partial
Create `templates/tasks/_item.peb` that renders a single `<li>` with:
- Stable id `task-{{ t.id }}`.
- The title and the Edit/Delete forms from Week 7 (keep ARIA labels and dual attributes).
- No inline business logic—just markup and data from the model.

### 1.3 Task list partial
Create `templates/tasks/_list.peb` containing:
```pebble
<ul id="task-list" aria-describedby="result-count">
  {% for task in page.items %}
    {% include "tasks/_item.peb" with {"task": task} %}
  {% endfor %}
</ul>
<p id="result-count" class="visually-hidden">
  Showing {{ page.items|length }} of {{ page.totalItems }} tasks{% if query %} matching "{{ query }}"{% endif %}
</p>
```
This ensures screen readers hear the result count whenever the list changes.

### 1.4 Update index page
Modify `templates/tasks/index.peb` so it extends `_layout/base.peb`, renders the add form, and includes `_list.peb` and `_pager.peb` inside a `<div id="task-area">` container (we will populate `_pager.peb` shortly).

**⚠️ Important:** Also update the add task form to `hx-target="#task-area"` with `hx-swap="innerHTML"` (not `#task-list` with `beforeend` from Week 7). Adding a task now affects pagination, so we replace the entire task area rather than appending a single item.

✋ **Checkpoint**: restart the server and confirm the tasks page renders exactly as before (no functionality has changed yet). If anything broke, inspect your partials and template paths.

### 1.5 Add production-ready UI with header/nav/footer partials (15 min)

Now that you understand the partial pattern, let's apply it to create a more polished, production-ready UI. We'll factor the base layout into reusable header, navigation, and footer components.

**Why add this now?**
- **Maintainability**: Accessibility features (skip links, ARIA live regions) stay in one place
- **Reusability**: Header and footer will be consistent across all pages
- **Professionalism**: Navigation and session info help users understand context
- **Best practice**: Industry-standard template organization (header/nav/footer separation)

**What you're building:**
```
templates/_layout/
├── base.peb         ← Updated to include partials
├── _header.peb      ← Skip link, ARIA live region, includes nav
├── _nav.peb         ← Site branding, main navigation links
└── _footer.peb      ← University branding, helpful links, session info
```

#### 1.5.1 Create header partial

Create `templates/_layout/_header.peb`:
```pebble
{# Header partial with navigation and branding #}
{# WCAG 2.4.1: Skip link for keyboard navigation #}
<a class="skip-link" href="#main">Skip to main content</a>

{# WCAG 4.1.3: ARIA live region for dynamic status announcements #}
{# Polite: non-interrupting announcements after current speech finishes #}
<div id="status" role="status" aria-live="polite" aria-atomic="true" class="visually-hidden"></div>

<header>
  {% include "_layout/_nav.peb" %}
</header>
```

**Key accessibility features:**
- **Skip link**: Keyboard users can jump directly to main content (WCAG 2.4.1)
- **Live region**: Screen readers announce status updates without interrupting navigation (WCAG 4.1.3)
- **Semantic `<header>`**: Screen readers identify site header as a landmark

#### 1.5.2 Create navigation partial

Create `templates/_layout/_nav.peb`:
```pebble
{# Navigation partial with site branding and main menu #}
{# WCAG 1.3.1: Semantic nav element for screen readers #}
<nav aria-label="Main navigation">
  <ul>
    <li><strong>COMP2850 Task Manager</strong></li>
  </ul>
  <ul>
    <li><a href="/tasks">Tasks</a></li>
    <li><a href="/health">Health</a></li>
  </ul>
</nav>
```

**Pico CSS pattern**: Pico's `<nav>` styling automatically creates a horizontal navigation bar with the first `<ul>` on the left (branding) and second `<ul>` on the right (links).

**Why `aria-label`?**: If you have multiple `<nav>` elements on a page, labels like `aria-label="Main navigation"` help screen reader users distinguish between them.

#### 1.5.3 Create footer partial

Create `templates/_layout/_footer.peb`:
```pebble
{# Footer partial with helpful links and session info #}
{# WCAG 1.3.1: Semantic footer element #}
<footer>
  <div class="container">
    <p>
      <small>
        COMP2850 HCI &bull; Server-first architecture &bull;
        <a href="https://htmx.org/docs/" target="_blank" rel="noopener">HTMX Docs</a> &bull;
        <a href="https://www.w3.org/WAI/WCAG22/quickref/" target="_blank" rel="noopener">WCAG 2.2</a>
      </small>
    </p>
    <p>
      <small>
        Session: {{ sessionId | default('N/A') }} &bull;
        Mode: {% if isHtmx %}HTMX{% else %}No-JS{% endif %}
      </small>
    </p>
  </div>
</footer>
```

**What the footer provides:**
- **Context links**: Quick access to HTMX and WCAG documentation
- **Session visibility**: Helps with debugging (which session is active?)
- **Mode indicator**: Shows whether HTMX is working (useful for verifying parity)

**Security note**: `target="_blank"` requires `rel="noopener"` to prevent the new page from accessing `window.opener`.

#### 1.5.4 Update base layout to use partials

Now update `templates/_layout/base.peb` to include these partials. Replace your current base.peb with:

```pebble
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="utf-8" />
  <meta name="viewport" content="width=device-width, initial-scale=1" />
  <title>{% block title %}COMP2850 Task Manager{% endblock %}</title>

  {# Pico CSS for baseline accessible styles (WCAG 2.2 AA compliant) #}
  <link rel="stylesheet" href="https://unpkg.com/@picocss/pico@2/css/pico.min.css">

  {# HTMX for progressive enhancement #}
  <script src="https://unpkg.com/htmx.org@1.9.12"></script>

  <style>
    /* Visually hidden but accessible to screen readers (WCAG 1.3.1) */
    .visually-hidden {
      position: absolute !important;
      height: 1px;
      width: 1px;
      overflow: hidden;
      clip: rect(1px, 1px, 1px, 1px);
      white-space: nowrap;
    }

    /* Skip link for keyboard navigation (WCAG 2.4.1) */
    .skip-link {
      position: absolute;
      left: -10000px;
      width: 1px;
      height: 1px;
      overflow: hidden;
    }
    .skip-link:focus {
      position: static;
      width: auto;
      height: auto;
      background: #1976d2;
      color: white;
      padding: 0.5rem 1rem;
      text-decoration: none;
      font-weight: bold;
      z-index: 9999;
    }

    /* Pagination styles */
    .pagination {
      display: flex;
      gap: 0.5rem;
      align-items: center;
    }
  </style>
</head>
<body>
  {# Include header with navigation and accessibility features #}
  {% include "_layout/_header.peb" %}

  {# Main landmark for screen readers (WCAG 1.3.1) #}
  {# tabindex="-1" allows programmatic focus for skip link #}
  <main id="main" class="container" tabindex="-1">
    {% block content %}
    {# Page-specific content goes here #}
    {% endblock %}
  </main>

  {# Include footer with helpful links and session info #}
  {% include "_layout/_footer.peb" %}
</body>
</html>
```

**What changed:**
- Moved skip link and live region to `_header.peb`
- Added navigation via `_nav.peb`
- Added footer with helpful links and session info
- Enhanced CSS for skip link visibility on focus
- Added `{% block title %}` so pages can customize the `<title>` tag

#### 1.5.5 Update route handlers to pass session data

For the footer to display session info, update your route handlers to include `sessionId` and `isHtmx` in the template model:

```kotlin
get("/tasks") {
    val query = call.request.queryParameters["q"].orEmpty()
    val page = call.request.queryParameters["page"]?.toIntOrNull() ?: 1
    val data = repo.search(query = query, page = page, size = 10)

    // Add session info for footer
    val sessionId = call.sessions.get<UserSession>()?.id ?: "guest"
    val isHtmx = call.request.headers["HX-Request"]?.equals("true", ignoreCase = true) == true

    val model = mapOf(
        "title" to "Tasks",
        "page" to data,
        "query" to query,
        "sessionId" to sessionId,
        "isHtmx" to isHtmx
    )
    call.respondHtml(PebbleRender.render("tasks/index.peb", model))
}
```

**Note**: If you don't have session support yet, you can pass a placeholder value like `"dev-session"` for now.

✋ **Checkpoint**: restart the server and verify:
- **Navigation bar** appears at the top with "COMP2850 Task Manager" branding and Tasks/Health links
- **Skip link** becomes visible when you press Tab (test keyboard navigation)
- **Footer** shows at the bottom with helpful links and session info
- **All existing functionality** still works (add/edit/delete tasks)
- **Accessibility**: Skip link works, navigation is keyboard-accessible, footer links open in new tabs

**Benefits of this refactoring:**
- 🎨 **Professional appearance**: Navigation and branding make it look like a real web app
- ♿ **Accessibility maintained**: Skip links and ARIA regions still work
- 🔧 **Easier maintenance**: Update navigation in one place, all pages inherit it
- 📚 **Learning transfer**: This pattern applies to any server-rendered web app

---

## 2. Add pagination and filtering (35 min)
### 2.1 Repository paging helper
Add a `Page<T>` data class and a `search(query, page, size)` helper in your repository or service layer so routes can request paged data. Keep the page number clamped (`coerceIn`) to valid bounds.

### 2.2 Pager partial
Create `templates/tasks/_pager.peb`:
```pebble
<nav aria-label="Pagination">
  <ul class="pagination">
    {% if page.currentPage > 1 %}
      <li>
        <a href="/tasks?q={{ query|default('') }}&page={{ page.currentPage - 1 }}"
           hx-get="/tasks/fragment?q={{ query|default('') }}&page={{ page.currentPage - 1 }}"
           hx-target="#task-area"
           hx-push-url="true">
          Previous
        </a>
      </li>
    {% endif %}
    <li aria-current="page">Page {{ page.currentPage }} of {{ page.totalPages }}</li>
    {% if page.currentPage < page.totalPages %}
      <li>
        <a href="/tasks?q={{ query|default('') }}&page={{ page.currentPage + 1 }}"
           hx-get="/tasks/fragment?q={{ query|default('') }}&page={{ page.currentPage + 1 }}"
           hx-target="#task-area"
           hx-push-url="true">
          Next
        </a>
      </li>
    {% endif %}
  </ul>
</nav>
```
Dual attributes guarantee the pager works both with and without HTMX.

### 2.3 Filter form
Above the task area add a filter form:
```pebble
<form action="/tasks" method="get"
      hx-get="/tasks/fragment"
      hx-target="#task-area"
      hx-trigger="keyup changed delay:300ms, submit from:closest(form)"
      hx-push-url="true">
  <label for="q">Filter tasks</label>
  <input id="q" name="q" value="{{ query|default('') }}" type="search"
         aria-describedby="q-hint">
  <small id="q-hint">Type to filter; works without JavaScript.</small>
  <button type="submit">Apply</button>
</form>
```
The visible submit button provides the no-JS fallback; HTMX enhances with Active Search. Use the indicator pattern from the cheat sheet if you want to show loading state.

### 2.4 Routes
Add two GET handlers in your Ktor routing block:
```kotlin
get("/tasks") {
    val query = call.request.queryParameters["q"].orEmpty()
    val page = call.request.queryParameters["page"]?.toIntOrNull() ?: 1
    val data = repo.search(query = query, page = page, size = 10)
    val model = mapOf("title" to "Tasks", "page" to data, "query" to query)
    call.respondHtml(PebbleRender.render("tasks/index.peb", model))
}

get("/tasks/fragment") {
    val query = call.request.queryParameters["q"].orEmpty()
    val page = call.request.queryParameters["page"]?.toIntOrNull() ?: 1
    val data = repo.search(query = query, page = page, size = 10)
    val list = PebbleRender.render("tasks/_list.peb", mapOf("page" to data, "query" to query))
    val pager = PebbleRender.render("tasks/_pager.peb", mapOf("page" to data, "query" to query))
    val status = """<div id="status" hx-swap-oob="true">Found ${data.total} tasks.</div>"""
    call.respondText(list + pager + status, ContentType.Text.Html)
}
```
`/tasks` handles full-page renders. `/tasks/fragment` returns fragments + an out-of-band status update for HTMX.

✋ **Checkpoint**:
- Filter form updates the list as you type (HTMX) and on submit (no-JS).
- Pager links update the list and URL with HTMX; back/forward work.
- Live region announces “Found X tasks.”
- With JavaScript disabled, the filter submits and redraws the whole page; pager links still navigate correctly.

---

## 3. Test with assistive technology (10 min)
Use the checklist:
- Keyboard-only: Tab through filter form, pagination links, and tasks. Focus order should make sense and remain visible.
- Screen reader: Trigger a filter change and ensure the result count is announced. Navigate the pager links; confirm `aria-current` is read out.
- No-JS parity: Disable JS and confirm filtering/paging still work via full-page loads.
- History: After filtering, use the back button—does the list revert? If not, check `hx-push-url` and your full-page route.

Record outcomes in `wk08/docs/prototyping-constraints.md` under an “Accessibility verification” section.

---

## 4. Document trade-offs (15 min)
Open `wk08/docs/prototyping-constraints.starter.md` (or create `prototyping-constraints.md`) and note:
- Rendering splits (full page vs fragments).
- Accessibility hooks (result count, live region, focus handling).
- State management decisions (query parameter naming, page size choices).
- Risks you introduced (duplicate HTML fragments, maintaining `_list` and `_pager`, potential 404 on out-of-range pages).

This documentation is evidence for Week 10 prioritisation and helps staff review your implementation quickly.

---

## Commit guidance
```bash
git add templates/_layout/base.peb templates/tasks/_*.peb templates/tasks/index.peb src/main/kotlin wk08/docs/prototyping-constraints.md

git commit -m "wk8s1: partials + pagination/filter with hx-push-url" --no-verify
```
Suggested commit body:
```
- factored tasks templates into base layout, list, item, pager partials
- added search form with dual-path (HTMX + full-page) handling
- implemented pagination routes returning fragments + status announcements
- verified keyboard, screen reader, JS-off parity
- documented rendering splits and trade-offs in wk08/docs/prototyping-constraints.md
```

---

## Advanced: Template View Adapter Pattern (Optional)

**For students comfortable with abstraction layers**:

The current approach passes the `Page` object directly to templates (`"page" to data`), which keeps templates coupled to the Kotlin data class structure. If you change `Page.currentPage` to `Page.number`, all templates break.

**Alternative: View adapter method**

Add a `toPebbleContext()` method to `Page` that flattens properties into template-friendly names:

```kotlin
fun toPebbleContext(itemsKey: String = "items"): Map<String, Any> = mapOf(
    itemsKey to items,
    "currentPage" to currentPage,
    "totalPages" to totalPages,
    "totalItems" to totalItems,
    "pageSize" to pageSize,
    "hasPrevious" to hasPrevious,
    "hasNext" to hasNext,
    "previousPage" to previousPage,
    "nextPage" to nextPage
)
```

Then routes can use:
```kotlin
val model = page.toPebbleContext("tasks") + mapOf("query" to query)
```

And templates access flattened variables:
```pebble
{{ tasks|length }} of {{ totalItems }}
<li>Page {{ currentPage }} of {{ totalPages }}</li>
```

**Trade-offs**:
- ✅ Decouples templates from Kotlin internals (can rename properties safely)
- ✅ Cleaner template syntax (shorter variable names)
- ❌ Extra abstraction layer to understand
- ❌ "Magic" variable names (harder to trace where they come from)

**When to use**: Production codebases where template stability matters. For learning, the direct approach (`page.items`, `page.page`) is clearer.

---

## What's next?
Week 8 Lab 2 focuses on wiring additional routes, error handling, and no-JS verification scripts. Keep your server running and your documentation open—we will extend the same patterns immediately.

Optional stretch this week: add a "mark complete" flow using the same partials and log any accessibility considerations.

---

## Further resources
- [HTMX Examples — Active Search](https://htmx.org/examples/active-search/)
- [MDN — Accessible Pagination Patterns](https://developer.mozilla.org/en-US/docs/Web/Accessibility/Understanding_WCAG/Navigation_and_orienting#pagination)

You now have a scalable, accessible list structure ready for deeper evaluation and instrumentation.
