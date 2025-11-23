# Week 10 • Lab 2 — Inclusive Redesign, Re-Verification, Task 2 Packaging

![COMP2850](https://img.shields.io/badge/COMP2850-HCI-blue)
![Week 10](https://img.shields.io/badge/Week-10-orange)
![Lab 2](https://img.shields.io/badge/Lab-2-green)
![Status](https://img.shields.io/badge/Status-Draft-yellow)



---

## Before Lab: Required Reading (10 mins)

📖 **Essential**
- Review your redesign brief (`wk10/lab-wk10/docs/redesign-brief.md`)
- Review [Assistive Testing Checklist](../references/assistive-testing-checklist.md)
- Skim the [Screenshot Evidence Guide](../references/screenshot-guide.md)
- [WCAG 2.2 Quick Reference](https://www.w3.org/WAI/WCAG22/quickref/) (sections 3.3, 4.1)

📖 **Contextual**:
- [WebAIM: Creating Accessible Forms](https://webaim.org/techniques/forms/)
- [GOV.UK: Making your service accessible](https://www.gov.uk/service-manual/helping-people-to-use-your-service/making-your-service-accessible-an-introduction)

---

## Introduction: From Plan to Implementation

Week 10 Lab 1 identified Priority 1 fixes through data analysis. **Today you implement and verify those fixes**.

**This lab is where HCI theory becomes practice**:
- **Implementation**: Server-first code + HTMX enhancements
- **Verification**: Rigorous accessibility testing (keyboard, SR, no-JS)
- **Measurement**: Before/after metrics proving improvement
- **Documentation**: Evidence chains for assessment

**Why this matters**:
- **assessment**: Requires before/after metrics + code changes + verification evidence
- **assessment portfolio (due end Week 10)**: This fix becomes a case study (problem → data → fix → verification)
- **Professional practice**: Inclusive design is iterative—implement, test, measure, refine

**Quality bar**: Changes must improve accessibility **without breaking existing functionality**. Regression testing is critical.

---

## Learning Focus

### Lab Objectives
By the end of this session, you will have:
- Implemented top 3 prioritised fixes (WCAG 2.2 AA)
- Run regression testing (axe + manual keyboard, SR, no-JS)
- Re-piloted with n=2 to verify improvements
- Measured post-change metrics and compared to baseline
- Documented evidence chains (code diffs, screenshots, metrics)
- Packaged assessment submission with before/after data

### Learning Outcomes Addressed
This lab contributes to the following module Learning Outcomes ([full definitions](../references/learning-outcomes.md)):

- **LO4**: Evaluate for accessibility — evidenced by regression testing
- **LO6**: Apply iterative design — evidenced by redesign → re-verification cycle
- **LO9**: Apply inclusive design — evidenced by WCAG-compliant redesign
- **LO12**: Demonstrate professionalism — evidenced by evidence chains in documentation
Maps to WCAG: 2.2 AA (demonstrable compliance)

---

## Key Concepts

### Regression Testing

> **Regression Testing** [GLOSSARY]
>
> Verifying that new changes don't break existing functionality. Critical when fixing accessibility issues.
>
> **HCI context**: Fixing one accessibility issue can inadvertently break another. Example:
> - Fix: Add `role="alert"` to error messages (helps SR users)
> - Regression: Alert interrupts SR navigation mid-task (too assertive)
> - Solution: Use `aria-live="polite"` instead
>
> **Regression checklist covers**:
> - Keyboard navigation (tab order, focus indicators)
> - Screen reader announcements (live regions, labels)
> - No-JS parity (functionality without JavaScript)
> - Visual rendering (contrast, zoom, reflow)
>
> **Best practice**: Test with same protocol used in Week 9 pilots—ensures comparability.
>
> 🔗 [W3C: Regression Testing for Accessibility](https://www.w3.org/WAI/test-evaluate/combined-expertise/)

### Before/After Metrics

> **Before/After Metrics** [GLOSSARY]
>
> Quantitative comparison showing improvement from intervention. Core of evidence-based HCI.
>
> **Requirement**: Baseline (Week 9) + post-change (Week 10 Lab 2) measured identically.
>
> **Example**:
> | Metric | Before | After | Δ | Interpretation |
> |--------|--------|-------|---|----------------|
> | T2 completion (no-JS) | 50% | 100% | +50% | Fix restored parity |
> | T2 error rate | 33% | 10% | -23% | Improved affordances |
> | T2 median time (all) | 1400ms | 1250ms | -150ms | Faster (less confusion) |
>
> **HCI connection**: Before/after metrics demonstrate **measurable impact**—not just "we fixed it" but "completion improved by 50%."
>
> **assessment requires**: Table + narrative explaining significance (who benefits, why it matters).
>
> 🔗 [Measuring UX: Before/After Studies](https://measuringux.com/before-after/)

### Accessible Error Handling

> **Accessible Error Handling** [GLOSSARY]
>
> Validation errors must be perceivable, understandable, and recoverable for all participants.
>
> **WCAG requirements**:
> - **3.3.1 Error Identification (A)**: Errors identified in text and programmatically determinable
> - **3.3.3 Error Suggestion (AA)**: Provide correction suggestions when known
> - **4.1.3 Status Messages (AA)**: Messages announced without focus change
>
> **Best practices**:
> - **Inline errors**: `aria-describedby` links input to error message
> - **Summary errors**: Page-level alert with links to problem fields
> - **Live regions**: `role="alert"` for HTMX, `aria-live="assertive"` for critical errors
> - **Focus management**: Move focus to error summary (no-JS) or keep on input (HTMX)
>
> **Example (HTMX)**:
> ```html
> <!-- OOB status update -->
> <div id="status" role="alert" aria-live="assertive" hx-swap-oob="true">
>   Title is required. Please enter at least one character.
> </div>
> ```
>
> **Example (no-JS)**:
> ```html
> <!-- Error summary at top of page -->
> <div id="error-summary" role="alert" tabindex="-1">
>   <h2>There is a problem</h2>
>   <ul>
>     <li><a href="#title">Title is required</a></li>
>   </ul>
> </div>
> <!-- Input with aria-describedby -->
> <input id="title" aria-invalid="true" aria-describedby="title-error">
> <p id="title-error">Title is required. Please enter at least one character.</p>
> ```
>
> 🔗 [GOV.UK: Error Message Pattern](https://design-system.service.gov.uk/components/error-message/)

### Code Diffs for Evidence

> **Code Diffs** [GLOSSARY]
>
> Side-by-side comparison showing what changed. Essential for assessment evidence.
>
> **Format**:
> ```diff
> - <div id="status" hx-swap-oob="true">Title is required.</div>
> + <div id="status" role="alert" aria-live="assertive" hx-swap-oob="true">
> +   Title is required. Please enter at least one character.
> + </div>
> ```
>
> **What to include**:
> - File path (e.g., `src/main/kotlin/routes/Tasks.kt:45`)
> - Before/after code (3-5 lines context)
> - Brief explanation of change and WCAG impact
>
> **Generate automatically**:
> ```bash
> git diff wk9-baseline..HEAD -- templates/ > wk10/assessment/04-key-diffs.md
> ```
>
> **HCI connection**: Diffs make fixes **transparent and reproducible**—assessors and peers can see exactly what changed.
>
> 🔗 [Git: Generating Diffs](https://git-scm.com/docs/git-diff)

---

## Activity A: Implement Priority 1 Fix (40 min)

**Goal**: Execute redesign plan from Week 10 Lab 1 brief.

### Step 1: Review redesign brief (5 min)

Open `wk10/lab-wk10/docs/redesign-brief.md` and confirm:
- [ ] Problem statement clear (backed by Week 9 data)
- [ ] Proposed changes specific (file paths, before/after code)
- [ ] Acceptance criteria measurable
- [ ] Verification plan defined

**Assign roles** (if pair programming):
- **Driver**: Writes code
- **Navigator**: Reviews changes, runs tests, checks against brief

**Swap after 20 min**.

### Step 2: Implement server-side changes (15 min)

**Example: T2 edit validation error accessibility**

**File**: `src/main/kotlin/routes/Tasks.kt`

**Before** (Week 9 baseline):
```kotlin
post("/tasks/{id}/edit") {
    val id = call.parameters["id"]?.toIntOrNull() ?: return@post call.respond(HttpStatusCode.BadRequest)
    val title = call.receiveParameters()["title"].orEmpty().trim()

    if (title.isBlank()) {
        if (call.isHtmx()) {
            val status = """<div id="status" hx-swap-oob="true">Title is required.</div>"""
            return@post call.respondText(status, ContentType.Text.Html, HttpStatusCode.BadRequest)
        } else {
            return@post call.respondRedirect("/tasks/{id}/edit?error=title")
        }
    }

    // Success path...
}
```

**After** (Week 10 fix):
```kotlin
post("/tasks/{id}/edit") {
    val id = call.parameters["id"]?.toIntOrNull() ?: return@post call.respond(HttpStatusCode.BadRequest)
    val title = call.receiveParameters()["title"].orEmpty().trim()

    if (title.isBlank()) {
        if (call.isHtmx()) {
            // CHANGE: Added role="alert" + aria-live="assertive" for SR announcement
            val status = """<div id="status" role="alert" aria-live="assertive" hx-swap-oob="true">
                Title is required. Please enter at least one character.
            </div>"""
            return@post call.respondText(status, ContentType.Text.Html, HttpStatusCode.BadRequest)
        } else {
            // CHANGE: Redirect to edit page with error param (focus handled in template)
            return@post call.respondRedirect("/tasks?error=title&edit_id=$id")
        }
    }

    // Success path unchanged
    repo.update(id, title)
    if (call.isHtmx()) {
        val item = PebbleRender.render("tasks/_item.peb", mapOf("t" to repo.get(id)))
        val status = """<div id="status" role="status" aria-live="polite" hx-swap-oob="true">
            Updated "${repo.get(id)?.title}".
        </div>"""
        call.respondText(item + status, ContentType.Text.Html)
    } else {
        call.respondRedirect("/tasks")
    }
}
```

**Key changes**:
1. **HTMX error**: Added `role="alert"` + `aria-live="assertive"` → SR announces immediately
2. **HTMX success**: Changed to `role="status"` + `aria-live="polite"` → less intrusive
3. **No-JS error**: Redirect includes `edit_id` → template can focus correct field

### Step 3: Implement template changes (15 min)

**File**: `templates/tasks/index.peb`

**Add error summary** (for no-JS path):

```twig
{% if error %}
<div id="error-summary" class="error-summary" role="alert" aria-live="assertive" tabindex="-1">
  <h2>There is a problem</h2>
  <ul>
    {% if error == "title" %}
    <li><a href="#title">Title is required. Please enter at least one character.</a></li>
    {% endif %}
  </ul>
</div>
<script>
  // Progressive enhancement: auto-focus error summary
  // Works even if JS loaded late; gracefully degrades if JS disabled after page load
  (function() {
    var errorSummary = document.getElementById('error-summary');
    if (errorSummary) {
      errorSummary.focus();
    }
  })();
</script>
{% endif %}
```

**Update form input** (link to error):

```twig
<label for="title">Task title</label>
<input id="title" name="title" type="text"
       {% if error == "title" %}
       aria-invalid="true"
       aria-describedby="title-hint title-error"
       {% else %}
       aria-describedby="title-hint"
       {% endif %}
       required>

<p id="title-hint" class="hint">Keep titles short and specific.</p>

{% if error == "title" %}
<p id="title-error" class="error-message" role="alert">
  Title is required. Please enter at least one character.
</p>
{% endif %}
```

**Key changes**:
1. **Error summary**: `role="alert"` + `tabindex="-1"` + auto-focus script
2. **Input**: `aria-invalid="true"` when error present
3. **aria-describedby**: Links input to both hint and error
4. **Inline error**: `role="alert"` for immediate announcement

### Step 4: Test manually (5 min)

**Start server**: `./gradlew run`

**Test HTMX path**:
1. Navigate to `/tasks`
2. Click Edit on a task
3. Clear title field, click Save
4. **Expected**: Status message appears immediately ("Title is required...")
5. **Verify with SR** (if available): Message should be announced without focus change

**Test no-JS path**:
1. Disable JavaScript (DevTools → Settings → Disable JavaScript)
2. Hard refresh (Ctrl+Shift+R)
3. Click Edit on a task
4. Clear title, click Save
5. **Expected**: Page reloads with error summary at top, summary has focus (visible outline)
6. **Verify**: Tab order → error summary link → click link → focus lands on `#title` input

**Test keyboard**:
1. Tab through entire flow (Edit → clear → submit → error summary → error link → input)
2. **Verify**: All elements reachable, focus visible, no keyboard traps

✋ **Stop and check**:
- [ ] HTMX error path works (status appears, SR announces)
- [ ] No-JS error path works (summary focused, link navigates to input)
- [ ] Keyboard navigation smooth (no traps, focus visible)
- [ ] No console errors

---

## Activity B: Run Regression Checklist (20 min)

**Goal**: Verify fix didn't break existing functionality or introduce new accessibility issues.

### Step 1: Download regression checklist (2 min)

**Create `wk10/lab-wk10/a11y/regression-checklist.csv`**:

```csv
category,check,pass,notes
Keyboard,All interactive elements reachable with Tab,,
Keyboard,Focus order matches visual order,,
Keyboard,Focus indicators visible on all elements,,
Keyboard,No keyboard traps,,
Keyboard,Skip link functional,,
Screen Reader,All form labels announced,,
Screen Reader,Error messages announced (role=alert),,
Screen Reader,Success messages announced (role=status),,
Screen Reader,Live regions working (filter results count etc),,
Screen Reader,Headings navigable (H key in NVDA/Orca),,
Forms,Errors identified in text,,
Forms,Errors linked to inputs (aria-describedby),,
Forms,aria-invalid set when error present,,
Forms,Error suggestions provided (when applicable),,
No-JS,All tasks completable with JS disabled,,
No-JS,Error summary focusable and keyboard-navigable,,
No-JS,PRG pattern working (refresh doesn't duplicate),,
No-JS,Full page renders vs fragments (correct responses),,
Visual,Contrast meets WCAG AA (4.5:1 text),,
Visual,200% zoom: no horizontal scroll,,
Visual,Error messages visible (not colour-only),,
Visual,Focus indicators meet contrast requirements,,
```

### Step 2: Execute checklist (15 min)

**Work through each row systematically**:

**Keyboard testing**:
- [ ] Tab from skip link → forms → buttons → tasks → pagination
- [ ] Shift+Tab reverses order
- [ ] Enter activates links/buttons, Space toggles checkboxes
- [ ] Escape closes modals (if any)
- [ ] Focus visible on all stops (blue outline or similar)

**Screen reader testing** (NVDA Windows / Orca Linux):
- [ ] Navigate headings with H key → hears "Tasks", "Add Task", etc.
- [ ] Forms mode (F key) → lands on first input
- [ ] Error submission → hears "Alert. Title is required..."
- [ ] Success submission → hears "Updated [title]" (or similar)
- [ ] Filter results → hears "Showing 3 tasks" (if live region present)

**No-JS testing**:
- [ ] Disable JS, hard refresh
- [ ] Add task → works (PRG redirect)
- [ ] Submit blank form → error summary appears and focused
- [ ] Click error link → focus moves to input
- [ ] Edit task → works (full page reload)
- [ ] Delete task → works (no confirmation expected—documented trade-off)

**Visual testing**:
- [ ] Use contrast checker (DevTools → CSS Overview or extension)
- [ ] Zoom to 200% (Ctrl/Cmd + plus) → no horizontal scroll
- [ ] Error states visible without colour (text, icons, position)

### Step 3: Document results (3 min)

**Fill in `pass` column**: `yes`, `no`, `n/a`

**Fill in `notes` column** for failures or concerns:

**Example**:
```csv
category,check,pass,notes
Screen Reader,Error messages announced (role=alert),yes,"NVDA announces immediately after submit"
No-JS,Error summary focusable and keyboard-navigable,yes,"Focus indicator visible, Tab order correct"
Visual,Focus indicators meet contrast requirements,no,"Blue outline has 2.8:1 contrast—needs 3:1. Log backlog item wk10-07"
```

**If failures found**:
- **Critical** (blocks task completion): Fix immediately before proceeding
- **High** (accessibility barrier): Log backlog item, document in notes
- **Medium/Low**: Acceptable if documented in constraints/known issues

✋ **Stop and check**:
- [ ] Regression checklist complete (all rows filled)
- [ ] No critical failures (or fixed)
- [ ] Evidence captured (screenshots if needed for failures)

---

## Activity C: Collect Post-Change Metrics (25 min)

**Goal**: Run targeted pilots to measure improvement.

### Step 1: Prepare verification pilots (5 min)

**Minimal protocol** (adapted from Week 9):
- **n=3 participants** (yourself + 2 peers, or 3 quick runs with different variants)
- **Focus on changed task** (T2 edit in our example)
- **Variants**: 1× standard (HTMX), 1× keyboard/SR, 1× no-JS

**Generate session IDs**:
```bash
# P6: Standard (HTMX, JS-on)
# P7: Keyboard + NVDA (SR)
# P8: No-JS
```

**Set cookies**:
```javascript
document.cookie = "sid=P6_post; path=/";
```

### Step 2: Run verification pilots (15 min)

**For each participant**:
1. Read consent (quick version: "Testing post-fix, ~5 min, can stop anytime")
2. Navigate to `/tasks`
3. Run T2 (Edit task): "Change 'Submit invoices' to 'Submit invoices by Friday'"
4. **Intentionally trigger error** once (submit blank) to test error handling
5. Then complete successfully
6. Note completion (yes/no), errors (count), time (from logs), confidence (1-5)

**Example pilot P7 (keyboard + SR)**:
```
P7_post (NVDA, keyboard-only):
- T2 attempt 1: Blank submission → Error announced "Alert. Title is required..." ✓
- T2 attempt 2: Success → "Updated Submit invoices by Friday" announced ✓
- Completion: yes
- Errors: 1 (intentional blank)
- Time: 1356ms (from metrics.csv)
- Confidence: 5
- Notes: "Error was clear this time, heard it immediately"
```

### Step 3: Analyse post-change data (5 min)

**Open `data/metrics.csv`**, filter for session IDs `P6_post`, `P7_post`, `P8_post`.

**Calculate same metrics as Week 9**:
- Completion rate: 3/3 = 100% ✓ (was 80% JS-on, 0% JS-off)
- Error rate: 1/4 attempts = 25% (was 33%—slight improvement)
- Median time (success only): median([1356, 1289, 3201]) = 1356ms
  - JS-on: 1322ms (was 1400ms—faster)
  - JS-off: 3201ms (was N/A—now functional!)

**Update `analysis/summary.md`**:

```markdown
## Before/After Comparison

### Task T2 (Edit Task)

| Metric | Before (Week 9) | After (Week 10) | Δ | Interpretation |
|--------|-----------------|-----------------|---|----------------|
| Completion (JS-on) | 4/5 (80%) | 2/2 (100%) | +20% | All participants succeeded |
| Completion (JS-off) | 0/1 (0%) | 1/1 (100%) | +100% | **Parity restored** |
| Completion (all) | 4/6 (67%) | 3/3 (100%) | +33% | Fix eliminated failures |
| Error rate (all) | 2/6 (33%) | 1/4 (25%) | -8% | Improved affordances |
| Median time (JS-on) | 1400ms | 1322ms | -78ms | Slightly faster (less confusion) |
| Median time (JS-off) | N/A | 3201ms | — | Functional (expected slower) |

### Key Improvements

**Accessibility**:
- Screen reader users: Error messages now announced immediately (`role="alert"`)
- Keyboard-only users: Error summary keyboard-navigable (`tabindex="-1"`, auto-focus)
- No-JS users: Can now complete task (was blocked before)

**WCAG Compliance**:
- ✅ 3.3.1 Error Identification (Level A): Errors identified in text and programmatically
- ✅ 4.1.3 Status Messages (Level AA): Validation errors announced without focus change
- ✅ 3.2.1 On Focus (Level A): Focus managed predictably (error summary → input)

**Impact**:
- **Who benefits**: Estimated 2-5% UK population (SR users) + keyboard-only + no-JS users
- **Severity**: High → Resolved (task was previously impossible for no-JS, difficult for SR)
```

✋ **Stop and check**:
- [ ] Post-change metrics collected (n=3 minimum)
- [ ] Before/after comparison table complete
- [ ] Narrative interpretation written
- [ ] Evidence captured (SR transcript, screenshots)

---

## Activity D: Package assessment Evidence Bundle (20 min)

**Goal**: Assemble all artefacts for Gradescope submission.

**Directory structure**: `wk10/assessment/`

### Step 1: Copy/create core documents (10 min)

**1. `01-redesign-brief.md`**:
- Copy from `wk10/lab-wk10/docs/redesign-brief.md`
- Update "Success Criteria Summary" section with actual results

**2. `02-regression-checklist.csv`**:
- Copy from `wk10/lab-wk10/a11y/regression-checklist.csv`
- Ensure all rows filled with pass/notes

**3. `03-before-after-summary.md`**:
- Copy before/after table from `analysis/summary.md`
- Add 2-3 paragraph narrative:
  - What changed (code + UX)
  - Who benefits (inclusion impact)
  - Evidence of improvement (completion +33%, parity restored)

**Example**:
```markdown
# Before/After Summary — Task 2

## Problem Addressed

Task T2 (Edit Task) had 67% overall completion in Week 9 pilots, with 0% completion for no-JS participants. Root cause: validation errors not accessible to screen readers or keyboard users.

## Solution Implemented

Added accessible error handling:
- HTMX: `role="alert"` + `aria-live="assertive"` for immediate SR announcement
- No-JS: Error summary with `tabindex="-1"` + auto-focus on page load
- Both: `aria-describedby` linking inputs to error messages

## Results

[Insert before/after table from Activity C]

## Impact

Fix restored functional parity for no-JS users (0% → 100% completion) and improved accessibility for screen reader and keyboard-only users. WCAG 2.2 Level AA compliance achieved (3.3.1, 4.1.3, 3.2.1).
```

**4. `04-key-diffs.md`**:

Generate code diffs:
```bash
git diff wk9-baseline..HEAD -- templates/tasks/index.peb src/main/kotlin/routes/Tasks.kt > wk10/assessment/04-key-diffs.md
```

Annotate with explanations:
```markdown
# Key Code Changes — Task 2

## File: `src/main/kotlin/routes/Tasks.kt` (Line 45)

### Before
```kotlin
val status = """<div id="status" hx-swap-oob="true">Title is required.</div>"""
```

### After
```kotlin
val status = """<div id="status" role="alert" aria-live="assertive" hx-swap-oob="true">
    Title is required. Please enter at least one character.
</div>"""
```

**Change**: Added `role="alert"` + `aria-live="assertive"`

**Rationale**: Ensures screen readers announce validation errors immediately without focus change (WCAG 4.1.3 Status Messages).

---

## File: `templates/tasks/index.peb` (Line 23)

### Before
```twig
{% if error %}
<div class="alert">Could not save</div>
{% endif %}
```

### After
```twig
{% if error %}
<div id="error-summary" class="error-summary" role="alert" aria-live="assertive" tabindex="-1">
  <h2>There is a problem</h2>
  <ul>
    <li><a href="#title">Title is required. Please enter at least one character.</a></li>
  </ul>
</div>
<script>
  (function() {
    var errorSummary = document.getElementById('error-summary');
    if (errorSummary) { errorSummary.focus(); }
  })();
</script>
{% endif %}
```

**Changes**:
1. Structured error summary (heading + list of errors)
2. `tabindex="-1"` makes div focusable programmatically
3. Auto-focus script (progressive enhancement)
4. `role="alert"` announces to SR on page load

**Rationale**: No-JS users can now navigate to error summary with keyboard, fixing 0% completion rate (WCAG 3.3.1, 3.2.1).
```

### Step 2: Collect evidence artefacts (5 min)

**Create `05-evidence/` directory**:

```
wk10/assessment/05-evidence/
├── screenshots/
│   ├── before-htmx-error.png (no role=alert visible in devtools)
│   ├── after-htmx-error.png (role=alert visible)
│   ├── before-nojs-error.png (error present but not focused)
│   ├── after-nojs-error.png (error summary with focus outline)
│   └── annotations.md
├── sr-transcripts/
│   ├── before-nvda.txt ("Title is required" not announced)
│   └── after-nvda.txt ("Alert. Title is required..." announced)
└── metrics/
    ├── before-analysis.csv (copy of Week 9 analysis.csv)
    └── after-analysis.csv (post-change metrics)
```

**Screenshot annotations.md**:
```markdown
# Evidence Annotations

## before-htmx-error.png
**Alt text**: "Browser devtools showing div#status with text 'Title is required' but no ARIA attributes"

**Context**: Week 9 baseline. Error appears visually but screen readers don't announce it.

## after-htmx-error.png
**Alt text**: "Browser devtools showing div#status with role=alert, aria-live=assertive, and text 'Title is required. Please enter at least one character.'"

**Context**: Week 10 fix. Error now has semantic markup for SR announcement.

## after-nojs-error.png
**Alt text**: "Task list page with error summary at top highlighted with blue focus outline. Error reads 'There is a problem' with link to title field."

**Context**: Week 10 fix, no-JS path. Error summary receives focus on page load, keyboard-navigable.
```

### Step 3: Create README (5 min)

**Create `wk10/assessment/README.md`**:

```markdown
# COMP2850 HCI — assessment Submission

**Student**: [Your name]
**Date**: 2025-10-22
**Module**: COMP2850 HCI

---

## Contents

1. **01-redesign-brief.md** — Problem statement, proposed changes, acceptance criteria
2. **02-regression-checklist.csv** — Accessibility verification (keyboard, SR, no-JS)
3. **03-before-after-summary.md** — Quantitative metrics comparison (Week 9 vs Week 10)
4. **04-key-diffs.md** — Annotated code changes with WCAG rationale
5. **05-evidence/** — Screenshots, SR transcripts, metrics CSVs

---

## Summary

**Problem**: Task T2 (Edit Task) had 67% completion rate in Week 9 pilots (0% for no-JS). Validation errors not accessible to screen readers or keyboard users.

**Fix**: Added accessible error handling (`role="alert"`, `aria-describedby`, `tabindex="-1"`, auto-focus).

**Result**: 100% completion rate (all variants), WCAG 2.2 Level AA compliance (3.3.1, 4.1.3, 3.2.1).

**Impact**: Restored functional parity for no-JS users, improved accessibility for SR and keyboard-only users (estimated 2-5% UK population).

---

## Evidence Chain

1. **Raw data**: `05-evidence/metrics/before-analysis.csv` (Week 9 pilots)
2. **Analysis**: `03-before-after-summary.md` (completion 67% → 100%)
3. **Prioritisation**: `01-redesign-brief.md` (Priority 1, Score 8)
4. **Implementation**: `04-key-diffs.md` (code changes with WCAG rationale)
5. **Verification**: `02-regression-checklist.csv` + `05-evidence/screenshots/` (post-change testing)
6. **Measurement**: `05-evidence/metrics/after-analysis.csv` (post-change pilots)

All files reference each other for full traceability.
```

✋ **Stop and check**:
- [ ] All 5 core documents present in `task2/`
- [ ] Evidence artefacts organised in `05-evidence/`
- [ ] README.md complete with summary
- [ ] All files sanitized (no PII)
- [ ] File names match spec exactly

---

## Commit & Reflect (10 min)

### Commit message

```bash
git add templates/ src/main/kotlin/ data/metrics.csv analysis/ wk10/assessment/ wk10/lab-wk10/a11y/ backlog/backlog.csv

git commit -m "$(cat <<'EOF'
wk10s2: implemented inclusive redesign, verified, packaged Task 2

- Implemented Priority 1 fix: accessible validation error handling (T2 edit)
  - HTMX: Added role=alert + aria-live=assertive for SR announcement
  - No-JS: Added error summary with tabindex=-1 + auto-focus
  - Both: Added aria-describedby linking inputs to errors
- Completed accessibility regression checklist (21/22 pass, 1 contrast issue logged)
- Ran verification pilots (n=3): T2 completion 67% → 100%, parity restored for no-JS
- Collected before/after metrics: completion +33%, error rate -8%, median time -78ms (JS-on)
- Packaged assessment bundle: brief, checklist, metrics, code diffs, evidence
- Updated backlog: wk9-01, wk9-03 marked fixed with verification evidence

Key results:
- No-JS users: 0% → 100% completion (functional parity restored)
- SR users: Errors now announced immediately (WCAG 4.1.3 compliance)
- Keyboard users: Error summary keyboard-navigable (WCAG 3.2.1 compliance)
- All variants: WCAG 2.2 Level AA achieved (3.3.1, 4.1.3, 3.2.1)

Task 2 ready for submission.


EOF
)"
```

### Reflection questions

**Answer in `wk10/reflection.md`**:

1. **Implementation challenges**: What was harder than expected? Did you encounter unexpected regressions?

2. **Verification insights**: Did post-change pilots reveal new issues? How confident are you in the ≥90% completion claim?

3. **Trade-offs**: Did you make any compromises (e.g., auto-focus script requires JS)? How do you justify them?

4. **Before/after impact**: Looking at the metrics table, which improvement matters most for inclusion? Why?

5. **Evidence quality**: Is your evidence chain complete (data → analysis → fix → verification)? What's missing or weak?

6. **Assessment readiness**: Can you present this fix in 5 minutes (problem → data → solution → proof)? Practice your narrative.

---

## Looking Ahead: Week 11 (Optional Refinement Week 11 Studio Crit Early Marking)

Next week:
- **Lab 1**: Present evidence chains to peers (5 min each)
- Receive critique: Is evidence convincing? Are claims justified?
- Give critique: Check peers' evidence chains, WCAG compliance
- **Lab 2**: Final refinements, portfolio assembly, submission prep

**Before Week 11**:
- Review assessment bundle—can you explain every file?
- Practice 5-min presentation (problem → data → fix → verification)
- Identify 1-2 backup issues (if your main fix gets critiqued)

---

## Further Reading & Resources

### Essential
- Review [Assistive Testing Checklist](../references/assistive-testing-checklist.md)
- Skim the [Screenshot Evidence Guide](../references/screenshot-guide.md)
- Review [Evaluation Metrics Quick Reference](../references/evaluation-metrics-quickref.md)
- [WCAG 2.2 Quick Reference](https://www.w3.org/WAI/WCAG22/quickref/)

### Accessible Error Handling
- [WebAIM: Creating Accessible Forms](https://webaim.org/techniques/forms/)
- [GOV.UK: Error Message Pattern](https://design-system.service.gov.uk/components/error-message/)
- [ARIA Authoring Practices: Alert](https://www.w3.org/WAI/ARIA/apg/patterns/alert/)

### Before/After Studies
- [Measuring UX: Before/After Studies](https://measuringux.com/before-after/)
- [Nielsen: Measuring Improvement](https://www.nngroup.com/articles/measure-learnability/)

### Code Evidence
- [Git: Generating Diffs](https://git-scm.com/docs/git-diff)
- [GOV.UK: Documenting decisions](https://www.gov.uk/service-manual/service-standard/point-15-document-how-you-have-evaluated-your-service)

### Academic
- **Lazar et al. (2017).** *Research Methods in HCI* (2nd ed.). Chapter 12: Reporting results
- **Sauro & Lewis (2016).** *Quantifying the User Experience* (2nd ed.). Chapter 9: Before/after comparisons

---

## Glossary Summary

| Term | One-line definition |
|------|---------------------|
| **Regression testing** | Verifying new changes don't break existing functionality |
| **Before/after metrics** | Quantitative comparison showing improvement from intervention |
| **Accessible error handling** | Errors perceivable, understandable, recoverable for all participants |
| **Code diffs** | Side-by-side comparison showing what changed (for evidence) |
| **role="alert"** | ARIA role announcing content immediately to screen readers |
| **aria-live="assertive"** | Live region that interrupts SR to announce urgent changes |
| **aria-describedby** | Links element to descriptive text (hints, errors) |
| **tabindex="-1"** | Makes non-interactive element focusable programmatically (not in tab order) |
| **Progressive enhancement** | Baseline works without JS; JS adds enhancements |
| **Evidence chain** | Traceability from data → analysis → fix → verification |

---

**Lab complete!** You have an implemented, verified, documented inclusive redesign ready for final assessment submission (due end Week 10).
