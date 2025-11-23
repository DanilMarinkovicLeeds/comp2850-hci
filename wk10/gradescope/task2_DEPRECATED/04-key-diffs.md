# Key Diffs — Code and UX Changes

**Purpose**: Document specific code/template/CSS changes made during Week 10 redesign.

**Format**: Show before/after snippets or annotated screenshots with brief explanations.

---

## Change 1: [Feature/Component Name]

### Problem

[1-2 sentences describing the accessibility or usability issue this change fixes]

**Evidence**: [Link to Week 9 finding, e.g., "See `05-findings.md` section A1"]

---

### Code Changes

#### Before

**File**: `[path/to/file.kt or template.peb or styles.css]`

```[language]
[Paste relevant "before" code snippet — keep it focused (5-15 lines)]
```

**Issues**:
- [Issue 1: e.g., "Error message lacks role='alert' — not announced to SR"]
- [Issue 2: e.g., "aria-describedby not linking error to input"]

---

#### After

**File**: `[path/to/file.kt or template.peb or styles.css]`

```[language]
[Paste relevant "after" code snippet — highlight key changes]
```

**Improvements**:
- ✅ [Improvement 1: e.g., "Added role='alert' — SR now announces errors"]
- ✅ [Improvement 2: e.g., "Added aria-describedby='title-error' — SR reads error when focused on input"]

**WCAG compliance**: [List relevant criteria, e.g., "4.1.3 Status Messages (AA), 3.3.1 Error Identification (A)"]

---

### UX Impact

**Before** (screenshot or description):
[Describe user experience before fix, e.g., "SR users submit blank form, hear no feedback, assume success, task incomplete"]

**After** (screenshot or description):
[Describe user experience after fix, e.g., "SR users submit blank form, hear 'Title is required' alert, correct error, task complete"]

**Screenshot** (optional but recommended):
- `05-evidence/before-validation-error.png` — Error shown but not accessible
- `05-evidence/after-validation-error.png` — Error with role="alert" and aria-describedby

---

## Change 2: [Feature/Component Name]

[Repeat structure: Problem → Code Changes (Before/After) → UX Impact]

---

## Change 3: [Feature/Component Name]

[Repeat structure]

---

## Summary of Changes

**Total files modified**: [n]

| File | Type | Lines Changed | Description |
|------|------|--------------|-------------|
| `[filename.kt]` | Kotlin | [+X / -Y] | [Brief description, e.g., "Added error focus management"] |
| `[filename.peb]` | Pebble template | [+X / -Y] | [Brief description, e.g., "Added role='alert' to error spans"] |
| `[filename.css]` | CSS | [+X / -Y] | [Brief description, e.g., "Improved error text contrast"] |

---

## Example Format (Reference)

### Change 1: Validation Error Accessibility

#### Problem

Validation errors on the task edit form (T2) were visible but **not announced to screen readers**, causing a 33% error rate among SR users in Week 9 pilots.

**Evidence**: Week 9 `05-findings.md` section A1 (Finding: "Validation errors not announced by screen readers")

---

#### Code Changes

**Before** (`templates/tasks/edit.peb`):
```html
<form action="/tasks/{{ task.id }}/edit" method="post">
  <label for="title">Task title</label>
  <input type="text" id="title" name="title" value="{{ task.title }}">

  {% if error %}
    <span class="error">{{ error }}</span>
  {% endif %}

  <button type="submit">Save</button>
</form>
```

**Issues**:
- ❌ Error message lacks `role="alert"` — not announced to SR
- ❌ No `aria-describedby` linking error to input
- ❌ No error summary for multiple errors

---

**After** (`templates/tasks/edit.peb`):
```html
<form action="/tasks/{{ task.id }}/edit" method="post">
  {% if errors %}
    <div id="error-summary" class="error-summary" role="alert" tabindex="-1">
      <h2>There is a problem</h2>
      <ul>
        {% for error in errors %}
          <li><a href="#{{ error.field }}">{{ error.message }}</a></li>
        {% endfor %}
      </ul>
    </div>
  {% endif %}

  <label for="title">Task title</label>
  <input type="text" id="title" name="title" value="{{ task.title }}"
         aria-describedby="title-hint{% if errors.title %} title-error{% endif %}">
  <span id="title-hint" class="hint">Max 100 characters</span>

  {% if errors.title %}
    <span id="title-error" class="error" role="alert">{{ errors.title }}</span>
  {% endif %}

  <button type="submit">Save</button>
</form>
```

**Improvements**:
- ✅ Added `role="alert"` to error summary — SR announces when page loads
- ✅ Added inline error with `role="alert"` — SR announces when error appears (HTMX path)
- ✅ Added `aria-describedby` linking hint + error to input — SR reads error when focused
- ✅ Error summary has `tabindex="-1"` and receives focus in no-JS mode
- ✅ Error summary links to specific fields (`<a href="#title">`)

**WCAG compliance**:
- ✅ **4.1.3 Status Messages (AA)** — Errors announced to assistive tech
- ✅ **3.3.1 Error Identification (A)** — Errors identified and described in text
- ✅ **3.3.3 Error Suggestion (AA)** — Error messages suggest how to fix

---

#### Routes Changes

**Before** (`Routes.kt`):
```kotlin
post("/tasks/{id}/edit") {
    val id = call.parameters["id"]?.toInt() ?: error("Invalid ID")
    val title = call.receiveParameters()["title"] ?: ""

    if (title.isBlank()) {
        // Redirect with error flag
        call.respondRedirect("/tasks/$id/edit?error=blank_title")
        return@post
    }

    // ... save task
}
```

**Issues**:
- ❌ Error parameter loses context (only one error at a time)
- ❌ No focus management for no-JS path

---

**After** (`Routes.kt`):
```kotlin
post("/tasks/{id}/edit") {
    val id = call.parameters["id"]?.toInt() ?: error("Invalid ID")
    val title = call.receiveParameters()["title"] ?: ""

    val errors = mutableMapOf<String, String>()
    if (title.isBlank()) {
        errors["title"] = "Title is required"
    }
    if (title.length > 100) {
        errors["title"] = "Title must be 100 characters or less"
    }

    if (errors.isNotEmpty()) {
        if (call.isHtmx()) {
            // HTMX: Return form fragment with errors
            val html = renderTemplate("tasks/_edit-form.peb", mapOf(
                "task" to task,
                "errors" to errors
            ))
            call.respondText(html, ContentType.Text.Html, status = HttpStatusCode.BadRequest)
        } else {
            // No-JS: Redirect with error summary focused
            val errorParams = errors.entries.joinToString("&") { "${it.key}_error=${it.value}" }
            call.respondRedirect("/tasks/$id/edit?$errorParams&focus=error-summary")
        }
        return@post
    }

    // ... save task
}
```

**Improvements**:
- ✅ Multiple errors supported (not just one)
- ✅ HTMX path returns 400 Bad Request with form fragment
- ✅ No-JS path redirects with error parameters + focus hint
- ✅ Template receives structured error map

---

#### CSS Changes

**Before** (`styles.css`):
```css
.error {
  color: #d32f2f; /* Contrast ratio 3.5:1 on white (FAILS AA) */
  font-size: 14px;
}
```

**Issues**:
- ❌ Contrast 3.5:1 fails WCAG 1.4.3 (Contrast AA, requires 4.5:1)
- ❌ Font size too small for low vision users

---

**After** (`styles.css`):
```css
.error {
  color: #b71c1c; /* Contrast ratio 7.1:1 (PASSES AAA) */
  font-size: 16px;
  font-weight: 600;
  margin-top: 0.25rem;
}

.error-summary {
  border: 3px solid #b71c1c;
  padding: 1rem;
  margin-bottom: 1.5rem;
  background-color: #fdecea; /* Light red background */
}

.error-summary h2 {
  color: #b71c1c;
  font-size: 1.25rem;
  margin-top: 0;
}

.error-summary a {
  color: #b71c1c;
  text-decoration: underline;
  font-weight: 600;
}
```

**Improvements**:
- ✅ Contrast 7.1:1 exceeds AAA standard (helps low vision users)
- ✅ Larger font size (16px) more readable
- ✅ Error summary visually distinct (border + background)
- ✅ Links in error summary clearly visible

**WCAG compliance**:
- ✅ **1.4.3 Contrast (AA)** — Text contrast ≥ 4.5:1
- ✅ **1.4.6 Contrast Enhanced (AAA)** — Text contrast ≥ 7:1 (bonus)

---

### UX Impact

**Before** (Week 9 pilots):
- SR users submit blank form → hear nothing → assume success → task incomplete (80% completion rate)
- Keyboard users see error but must tab through entire page to find it (no focus management)
- Error text small and low contrast (difficult for low vision users)

**After** (Week 10 re-pilots):
- SR users submit blank form → hear "There is a problem. Title is required" → correct error → task complete (100% completion rate)
- Keyboard users: error summary receives focus automatically (no need to hunt for error)
- Error text large, high contrast, with clear visual hierarchy

**Screenshots**:
- `05-evidence/before-validation-error.png` — Error shown but not accessible
- `05-evidence/after-validation-error.png` — Error with role="alert", aria-describedby, and improved styling
- `05-evidence/after-error-summary.png` — Error summary with links to fields

---

## Testing Evidence

**Regression tests**: See `02-a11y-regression-checklist.csv` (all PASS)

**Re-pilot data**: See `06-metrics/post/postchange.csv`

**Before/after comparison**: See `03-before-after-summary.md`

---

## Commit History (Optional)

**If using version control**, include commit references:

```
commit abc123f
Author: [Your name]
Date: 2025-10-15

feat(a11y): add role="alert" to validation errors (WCAG 4.1.3)

- Added role="alert" to inline error messages
- Added aria-describedby linking errors to inputs
- Added error summary with role="alert" and focus management
- Improved error text contrast from 3.5:1 to 7.1:1 (AAA)
- Tested with NVDA: all errors now announced correctly

Closes: wk9-01 (see backlog/backlog.csv)
Related: WCAG 4.1.3 (Status Messages AA), 3.3.1 (Error Identification A)
```

---

## Summary

**Total changes**: [n] files modified, [+X / -Y] lines changed

**Key achievements**:
- ✅ SR users can now detect and correct validation errors independently
- ✅ Keyboard users don't need to hunt for errors (auto-focus)
- ✅ Low vision users benefit from improved contrast (7.1:1 AAA)
- ✅ No-JS parity maintained (all functionality works with JS disabled)
- ✅ No regressions (all existing tasks still work)

**WCAG compliance**: **AA** achieved (4.1.3, 3.3.1, 3.3.3, 1.4.3)

**Next steps**: Week 11 studio crit — present evidence-led redesign with before/after metrics

---

**Author**: [Your name]
**Date**: [YYYY-MM-DD]
**Related files**: `01-redesign-brief.md`, `02-a11y-regression-checklist.csv`, `03-before-after-summary.md`
