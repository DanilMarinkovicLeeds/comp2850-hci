# Week 11 • Lab 2: Wrap-up, Portfolio & Submission Readiness

![COMP2850](https://img.shields.io/badge/COMP2850-HCI-blue)
![Week 11](https://img.shields.io/badge/Week-11-orange)
![Lab 2](https://img.shields.io/badge/Lab-2-green)
![Status](https://img.shields.io/badge/Status-Draft-yellow)

---

## Terminology Note

Throughout COMP2850 we use **people-centred language** (e.g., "person using a screen reader") rather than deficit-based terms (e.g., "blind user"). This reflects contemporary inclusive-design practice and acknowledges that disability arises from environmental barriers, not individual impairment.

---

## Pre-reading

**Essential**
- [GOV.UK Design System: Form validation](https://design-system.service.gov.uk/patterns/validation/)
- [hypermedia.systems, Ch. 9: Practical Patterns](https://hypermedia.systems/hypermedia-on-the-web/#_practical_patterns)

**Recommended**
- [Shneiderman et al. (2016). *Designing the User Interface*, Ch. 15: Evaluation & Iteration](https://www.cs.umd.edu/~ben/papers/)
- [W3C (2024). WCAG 2.2 AA Quick Reference](https://www.w3.org/WAI/WCAG22/quickref/?currentsidebar=%23col_customize&levels=aaa)
- [Academic Skills Centre: Reflective Writing](https://www.leeds.ac.uk/arts/info/125218/academic_skills/149/reflective_writing)

---

## Introduction

### Context

This final lab brings your HCI project to completion. Over the past six weeks you have:

1. **Needs-finding** (Week 6): Identified stakeholder needs with informed consent
2. **Ethics overlay** (Week 7): Built a privacy-by-design feature
3. **Accessibility audit** (Week 7): Identified and fixed WCAG 2.2 AA violations
4. **Constraint exploration** (Week 8): Verified no-JS parity
5. **Evaluation** (Week 9): Ran task-based pilots and collected metrics
6. **Analysis** (Week 10): Prioritised redesign with (Impact + Inclusion) – Effort
7. **Redesign & verification** (Week 10): Fixed priority issues and re-tested
8. **Studio critique** (Week 11 Lab 1): Presented evidence and received peer feedback

Today you will **polish your work**, **assemble a portfolio** showing complete evidence chains, and **prepare for Gradescope submission**. This lab emphasises **traceability**: every claim you make must be backed by evidence (code, metrics, screenshots, pilot notes), and every artefact must link to the design decision it supports.

### Why This Matters

**Professionally**, portfolios demonstrate your ability to:
- Execute a complete HCI process (needs-finding → evaluation → iteration)
- Communicate design rationale with evidence
- Integrate accessibility and ethics from the start (not as afterthoughts)
- Manage traceability in complex projects

**Academically**, reflective portfolios are a recognised pedagogy for consolidating learning (Carson et al., 2020). Research shows that students who explicitly connect theory (e.g., WCAG success criteria) to practice (e.g., code fixes) develop deeper understanding than those who treat labs as discrete tasks.

## Learning Focus

### Lab Objectives
By the end of this session, you will have:
- Assembled a portfolio showing complete evidence chains (raw data → analysis → fix → verification)
- Produced reflective writing linking theory (WCAG, privacy principles) to practice (code, metrics)
- Reviewed all evidence chains across Tasks 1 & 2
- Completed self-assessment against all 13 Learning Outcomes
- Verified Gradescope submission readiness (file structure, size limits, naming conventions)
- Integrated peer critique feedback into backlog
- Finalised portfolio with reflections

### Learning Outcomes Addressed
This lab contributes to the following module Learning Outcomes ([full definitions](../references/learning-outcomes.md)):

- **LO12**: Demonstrate professionalism — evidenced by complete, honest self-assessment
- **All 13 LOs** — evidenced by portfolio demonstrating cumulative achievement across Weeks 6-11

---

## Key Concepts

### 1. Evidence Chain

An **evidence chain** is a traceable path from raw data through analysis and decision-making to implementation and verification. In HCI, evidence chains demonstrate that design decisions are grounded in observation, not assumption.

**Example (accessible error handling)**:
1. **Raw data**: Pilot P3 (SR user) took 127 seconds on T3 (add task); pilot notes: "P3 didn't hear validation error"
2. **Analysis**: Median for sighted pilots = 18 s; P3 MAD = 109 s outlier
3. **Finding**: "Validation errors not announced by screen readers" → backlog item
4. **Fix**: Added `role="alert" aria-live="assertive"` to error div (commit abc123)
5. **Verification**: Re-ran P3 with NVDA; time reduced to 22 s; pilot confirmed "error announced immediately"

**Traceability requirements**:
- Every finding must cite pilot ID, task code, and metric
- Every fix must cite commit hash and file path
- Every verification must cite pilot ID, assistive tech, and outcome

### 2. Reflective Writing

**Reflective writing** connects theory to practice by answering:
- **What** did I do? (Description)
- **Why** did I do it? (Theory/rationale)
- **How** did it work? (Evidence)
- **What** did I learn? (Insight)

**Example (Week 7 inline edit)**:
> **What**: I implemented an accessible inline edit using HTMX's `hx-target` and `hx-swap="outerHTML"`.
> **Why**: This approach supports progressive enhancement (WCAG 2.1.1 Keyboard Access) because the edit form is server-rendered, so keyboard and mouse interactions are identical.
> **How**: Manual keyboard testing confirmed that Tab navigated to the "Edit" button, Enter activated it, and focus moved to the title input without page refresh. NVDA announced "Edit task, button" and "Title, edit, [existing value]" correctly.
> **What I learned**: Server-first patterns simplify accessibility because you don't need custom ARIA or JavaScript focus management—the browser handles it. However, I initially forgot to add `aria-live="polite"` to the success message, which meant SR users didn't hear confirmation. After adding it (commit xyz789), NVDA announced "Task updated" automatically.

**Good reflective writing**:
- Cites specific code (file paths, line numbers, commit hashes)
- Links to theory (WCAG success criteria, HTMX docs, academic sources)
- Includes evidence (screenshots, pilot quotes, metrics)
- Identifies mistakes and corrections (not just successes)

### 3. Portfolio Structure

A **portfolio** is an organised collection of artefacts (code, docs, evidence) with accompanying reflection. For COMP2850, your portfolio includes:

1. **Code repository** (GitLab/GitHub): All commits from Week 6–11
2. **Evidence directory**: Organised by week (`evidence/wk6/`, `evidence/wk7/`, etc.)
3. **assessment package** (Gradescope): Consent, pilot data, findings, backlog
4. **assessment package** (Gradescope): Prioritisation, fix commits, verification, reflection
5. **README** or **reflection doc**: Narrative tying everything together

**Portfolio quality indicators**:
- ✅ Every claim has a citation (pilot ID, commit, screenshot)
- ✅ Evidence is organised and easy to navigate
- ✅ Code is clean (no commented-out blocks, no TODO markers)
- ✅ Reflection demonstrates learning (mistakes acknowledged, theory applied)
- ❌ Missing evidence (e.g., "P2 struggled" with no pilot notes)
- ❌ Orphaned artefacts (e.g., screenshot with no context)
- ❌ Copy-paste from labs without adaptation

### 4. Submission Readiness

**Gradescope** has technical requirements:
- **File size limit**: 100 MB per submission (compress screenshots if needed)
- **File naming**: Use consistent, descriptive names (e.g., `P1_task3_console.png`, not `Screenshot 2025-01-15.png`)
- **Directory structure**: Flat or shallow hierarchy (Gradescope doesn't preserve nested folders well)
- **PDF compilation**: Reflection and analysis docs should be PDF (not .docx or .md)

**Pre-submission checklist**:
1. Run `du -sh evidence/` to check total size
2. Verify all screenshots are legible (minimum 1280×720 resolution)
3. Test PDF rendering (check fonts, images, code blocks)
4. Confirm commit hashes are correct (use `git log --oneline`)
5. Spell-check and proofread all written work

---

## Activity 1: Polish assessment Based on Critique Feedback

**Time**: 30 minutes
**Materials**: Studio crit feedback (Lab 1), assessment draft package (Week 9)

### Step 1: Review Critique Feedback

Open your studio crit feedback notes. Look for **suggestions** that apply to **assessment** (evaluation and instrumentation). Common themes:

- **Pilot protocol clarity**: "I wasn't sure if consent was verbal or signed" → add screenshot of consent script
- **Metrics interpretation**: "Median looks fine, but no discussion of MAD outliers" → add MAD analysis
- **Evidence completeness**: "P3 notes mention 'struggled with SR' but no specifics" → add verbatim quotes, timestamps
- **Backlog traceability**: "Backlog item #7 says 'fix validation' but doesn't cite pilot data" → add pilot ID and metric

### Step 2: Update assessment Files

Your assessment package should include:

```
task1/
├── consent-script.md          # Informed consent protocol
├── pilot-protocol.md          # Step-by-step task execution guide
├── metrics.csv                # Raw data (from data/metrics.csv)
├── pilot-notes/               # Qualitative observations
│   ├── P1_notes.md
│   ├── P2_notes.md
│   ├── P3_notes.md
│   └── P4_notes.md
├── analysis.md                # Median, MAD, error rate, completion rate
├── findings.md                # Synthesised insights with evidence
├── backlog-update.md          # New items added to product backlog
└── reflection-task1.pdf       # Reflective writing (compiled)
```

**Action**: For each critique suggestion, update the relevant file. Example:

**Before** (`findings.md`):
```markdown
3. Validation errors not visible to screen reader users.
```

**After** (`findings.md`):
```markdown
3. **Validation errors not announced by screen readers** (Priority 1)
   - **Evidence**: Pilot P3 (NVDA, keyboard) took 127 s on T3 (add task); median = 18 s, MAD = 10 s → P3 is 10.9 MAD outlier. Pilot notes (line 14): "P3 submitted blank form three times; NVDA did not announce 'Title is required' error."
   - **Root cause**: Error div lacks `role="alert"` and `aria-live` region.
   - **Impact**: WCAG 4.1.3 (Status Messages) failure; SR users cannot recover from validation errors.
   - **Backlog item**: #7 "Add role=alert to validation error div" (Priority 1, Effort: 2)
```

### Step 3: Compress and Verify

1. **Compress screenshots**: If `pilot-notes/` includes large PNGs, compress them:
   ```bash
   # Using ImageMagick (if available)
   mogrify -resize 1920x1080 -quality 85 pilot-notes/*.png

   # Or use online tool: https://tinypng.com/
   ```

2. **Check total size**:
   ```bash
   du -sh task1/
   # Should be < 50 MB for assessment alone
   ```

3. **Verify PDF compilation**: Convert `reflection-task1.md` to PDF:
   ```bash
   pandoc reflection-task1.md -o reflection-task1.pdf --pdf-engine=xelatex

   # Or use Markdown preview → print to PDF in your editor
   ```

4. **Test readability**: Open `reflection-task1.pdf` and check:
   - Code blocks are formatted (monospace font, syntax highlighting preserved)
   - Screenshots are legible (not pixelated)
   - Fonts render correctly (no missing glyphs)

**Stop and check**: Open `findings.md`. For each finding, verify:
- ✅ Pilot ID cited
- ✅ Task code cited (T1, T2, T3, T4)
- ✅ Metric cited (median, MAD, error rate, or qualitative quote)
- ✅ WCAG criterion or privacy principle cited (if applicable)
- ✅ Backlog item ID cross-referenced

---

## Activity 2: Assemble assessment Portfolio

**Time**: 40 minutes
**Materials**: assessment draft (Week 10), regression checklist, verification pilot data, critique feedback

### Step 1: Review assessment Requirements

assessment focuses on **redesign** and **verification**. Your package must show:

1. **Prioritisation rationale**: How you scored issues with (Impact + Inclusion) – Effort
2. **Implementation evidence**: Commit hashes, before/after code diffs, WCAG mapping
3. **Regression testing**: Proof that fixes didn't break existing features
4. **Verification pilots**: Re-ran same tasks with same assistive tech; metrics improved
5. **Reflection**: What you learned about inclusive redesign

### Step 2: Organise assessment Files

```
task2/
├── prioritisation.md          # Scoring matrix with rationale
├── fixes/                     # One file per priority issue
│   ├── fix01-validation-alert.md
│   ├── fix02-focus-visible.md
│   └── fix03-error-summary.md
├── commits.md                 # List of all fix commits with hashes
├── regression-checklist.pdf   # Completed checklist (from Week 10)
├── verification/              # Re-test data
│   ├── metrics-after.csv
│   ├── P3_retest_notes.md
│   └── P3_retest_nvda.png     # Screenshot of NVDA announcing error
├── wcag-compliance.md         # Mapping fixes → WCAG success criteria
└── reflection-task2.pdf       # Reflective writing
```

### Step 3: Write Fix Documentation

For each priority issue, create a `fix##-description.md` file. Use this template:

```markdown
# Fix 01: Validation Errors Not Announced by Screen Readers

## Problem Statement
**Evidence**: Pilot P3 (NVDA, keyboard) took 127 s on T3 (add task); median = 18 s. Pilot notes: "P3 didn't hear error message."
**Impact**: WCAG 4.1.3 (Status Messages) failure. SR users cannot recover from validation errors.
**Priority score**: (5 Impact + 5 Inclusion) – 2 Effort = 8 (Priority 1)

## Solution
Added `role="alert"` and `aria-live="assertive"` to error div for HTMX path; added error summary for no-JS path.

### Before (Commit 7a3f9b2)
```kotlin
// routes/Tasks.kt, line 45
if (title.isBlank()) {
    if (call.isHtmx()) {
        val status = """<div id="status" hx-swap-oob="true">Title is required.</div>"""
        return@post call.respondText(status, ContentType.Text.Html, HttpStatusCode.BadRequest)
    }
    // No-JS path omitted for brevity
}
```

### After (Commit c8d1e4f)
```kotlin
// routes/Tasks.kt, line 45
if (title.isBlank()) {
    if (call.isHtmx()) {
        val status = """<div id="status" role="alert" aria-live="assertive" hx-swap-oob="true">
            Title is required. Please enter at least one character.
        </div>"""
        return@post call.respondText(status, ContentType.Text.Html, HttpStatusCode.BadRequest)
    }
    // No-JS: redirect to /tasks?error=title, error-summary.peb includes role=alert
}
```

### WCAG Mapping
- **4.1.3 Status Messages (AA)**: Error is now programmatically announced to SR users without focus change.
- **3.3.1 Error Identification (A)**: Error message explicitly states what went wrong and how to fix it.

## Verification
**Re-test**: Pilot P3, NVDA 2024.1, keyboard-only, JS-on
**Result**: Time reduced to 22 s (within 1 MAD of median). NVDA announced "Title is required" immediately after form submission. P3 confirmed: "Much better—I heard the error straight away."

**Regression**: Keyboard, mouse, no-JS paths all tested; no regressions (see `regression-checklist.pdf`).

## Reflection
This fix taught me that **announcements require explicit ARIA roles**. I initially assumed HTMX's OOB swap would be enough, but SR users rely on live regions to detect dynamic changes. The fix was simple (2 lines), but the impact was huge (10.9 MAD outlier → within normal range). This reinforces the principle that **accessibility is often low-effort if designed in from the start**, but expensive to retrofit.

---
**Commit**: c8d1e4f
**Files changed**: `routes/Tasks.kt` (line 45–52), `views/error-summary.peb` (added)
**Evidence**: `verification/P3_retest_nvda.png`, `verification/P3_retest_notes.md`
```

**Repeat for all priority fixes** (typically 3–5 issues).

### Step 4: Complete Regression Checklist

Your regression checklist from Week 10 Lab 2 should be converted to PDF. If incomplete, fill it out now:

| Test | Path | Tool | Result | Notes |
|------|------|------|--------|-------|
| T1 (Filter) | HTMX, mouse | Chrome | ✅ Pass | Filters apply instantly; status updates in #status div |
| T1 (Filter) | No-JS | Chrome | ✅ Pass | Form submission triggers full-page reload; filters persist in query string |
| T2 (Edit) | HTMX, keyboard | Firefox + NVDA | ✅ Pass | Tab to "Edit", Enter activates; focus moves to input; NVDA announces "Title, edit, [value]" |
| T3 (Add) | HTMX, keyboard | Firefox + NVDA | ✅ Pass | Blank submission triggers alert; NVDA announces error |
| T3 (Add) | No-JS | Chrome | ✅ Pass | Redirect to /tasks?error=title; error summary shown with focus on heading |
| T4 (Delete) | HTMX, mouse | Chrome | ✅ Pass | OOB swap removes `<li>`; status confirms deletion |

**Save as PDF**: `regression-checklist.pdf`

### Step 5: Update WCAG Compliance Doc

Create `wcag-compliance.md` mapping all fixes to WCAG 2.2 AA criteria:

```markdown
# WCAG 2.2 AA Compliance Summary

## Level A (Baseline)
- **1.3.1 Info & Relationships**: Semantic HTML (`<button>`, `<label>`, `<ul>`) used throughout; verified with axe DevTools.
- **2.1.1 Keyboard**: All interactive elements accessible via Tab/Enter/Space; no keyboard traps (tested with Firefox + NVDA).
- **3.3.1 Error Identification**: Validation errors explicitly describe problem and solution ("Title is required. Please enter at least one character.").
- **4.1.2 Name, Role, Value**: All form inputs have associated labels; buttons have accessible names.

## Level AA (Target)
- **1.4.3 Contrast (Minimum)**: Error text (`#d32f2f`) on white background = 5.2:1 (tested with Colour Contrast Analyser).
- **2.4.7 Focus Visible**: Custom focus outline (3px solid `#1976d2`) added to all interactive elements (CSS line 45).
- **3.3.3 Error Suggestion**: Validation messages include corrective hint ("Please enter at least one character").
- **4.1.3 Status Messages**: Alerts use `role="alert" aria-live="assertive"`; success messages use `aria-live="polite"`.

## Fixes Mapped to Criteria
| Fix | WCAG Criterion | Commit | Verification |
|-----|---------------|--------|-------------|
| Validation alert | 4.1.3 (AA) | c8d1e4f | P3 retest (NVDA announced error) |
| Focus visible | 2.4.7 (AA) | b9a2c3d | Keyboard-only pilot (focus outline visible) |
| Error summary (no-JS) | 3.3.1 (A) | e7f8a1b | No-JS pilot (error list shown, focus on heading) |

---
**Reference**: [WCAG 2.2 Quick Reference](https://www.w3.org/WAI/WCAG22/quickref/)
```

### Step 6: Write assessment Reflection

Your `reflection-task2.md` should answer:

1. **What redesign approach did you take?** (Prioritisation framework, fix strategy)
2. **Why these fixes?** (Link to evidence: metrics, pilot quotes, WCAG criteria)
3. **How did you verify impact?** (Re-test results, regression checks)
4. **What did you learn?** (Insights about accessibility, trade-offs, server-first architecture)

**Example structure** (aim for 800–1,000 words):

```markdown
# assessment Reflection: Inclusive Redesign

## Overview
After analysing pilot data from Week 9, I identified 8 accessibility and usability issues. Using the (Impact + Inclusion) – Effort prioritisation framework, I focused on the top 3 issues that disproportionately affected screen reader users and keyboard-only users...

## Prioritisation Rationale
I scored each issue on three dimensions:
- **Impact** (1–5): How much does this block task completion?
- **Inclusion** (1–5): Does this disproportionately affect people using assistive tech?
- **Effort** (1–5): How complex is the fix?

The highest-scoring issue was "Validation errors not announced by SR" (Priority: 8). Pilot P3 took 127 s on T3 (median = 18 s), and pilot notes (line 14) stated: "P3 didn't hear error; submitted blank form three times." This is a WCAG 4.1.3 failure (Status Messages) and blocks task completion entirely for SR users...

## Implementation
I implemented three priority fixes:

### Fix 1: Validation Alerts (Commit c8d1e4f)
**Problem**: Error div lacked `role="alert"` and `aria-live` region.
**Solution**: Added `role="alert" aria-live="assertive"` to HTMX error swap (routes/Tasks.kt:47); added error summary with `role="alert"` for no-JS path (views/error-summary.peb).
**Theory**: WCAG 4.1.3 requires status messages to be programmatically announced. ARIA live regions solve this without moving focus (which would disrupt flow).
**Result**: P3 retest reduced time to 22 s (within 1 MAD); NVDA announced error immediately...

[Continue for Fix 2, Fix 3]

## Verification & Regression
I re-ran Pilot P3 with the same tasks (T1–T4), assistive tech (NVDA 2024.1), and input method (keyboard-only). Time on T3 dropped from 127 s to 22 s, and P3 confirmed "error announced immediately" (see verification/P3_retest_notes.md).

I also completed a regression checklist covering HTMX, no-JS, keyboard, and mouse paths (see regression-checklist.pdf). No regressions were detected...

## Learning & Trade-offs
This project taught me that **server-first architecture simplifies accessibility** because HTML is semantic by default, and the browser handles focus/tab order/SR announcements. However, I discovered one trade-off: HTMX's OOB swaps don't trigger live region announcements unless you explicitly add `role="alert"`. This is documented in hypermedia.systems (Ch. 9), but I missed it initially.

I also learned that **prioritisation frameworks are essential** when time is limited. I could have fixed all 8 issues, but focusing on the top 3 (total effort: 6 hours) yielded the biggest impact (removed all SR blockers). The remaining 5 issues are in my backlog for Semester 2...

## Conclusion
By grounding decisions in pilot data (median, MAD, qualitative notes) and WCAG criteria, I built an inclusive task manager that works identically with/without JS, with/without a mouse, and with/without a screen reader. This process reinforced that accessibility is not a feature—it's a design constraint that improves usability for everyone.
```

**Save as**: `reflection-task2.md` → compile to `reflection-task2.pdf`.

**Stop and check**: Open `task2/fixes/`. For each fix file, verify:
- ✅ Problem statement cites pilot data (ID, task, metric)
- ✅ Solution includes before/after code with commit hash
- ✅ WCAG criterion mapped
- ✅ Verification evidence cited (retest pilot, screenshot)
- ✅ Reflection identifies learning or trade-off

---

## Activity 3: Final Repository & Evidence Review

**Time**: 20 minutes
**Materials**: Git log, `evidence/` directory, README

### Step 1: Review Commit History

Run `git log --oneline --since="2025-01-01"` to see all commits from Weeks 6–11. Check for:

- ✅ Descriptive commit messages (e.g., "Add role=alert to validation error (WCAG 4.1.3)" not "fix bug")
- ✅ Consistent branch strategy (e.g., all work on `main`, or feature branches merged cleanly)
- ❌ Merge conflicts left unresolved
- ❌ Sensitive data in history (e.g., API keys, database passwords)

If you find issues, clean them up now:

```bash
# Amend last commit message
git commit --amend -m "Add ARIA live region to error div (WCAG 4.1.3)"

# Rebase to clean up history (use with caution)
git rebase -i HEAD~5  # Interactive rebase for last 5 commits
```

**Create `commits.md`** for assessment package:

```markdown
# Commit Log: assessment Fixes

| Commit | Date | Description | Files |
|--------|------|-------------|-------|
| c8d1e4f | 2025-01-10 | Add role=alert to validation error (WCAG 4.1.3) | routes/Tasks.kt, views/error-summary.peb |
| b9a2c3d | 2025-01-11 | Add focus-visible outline (WCAG 2.4.7) | static/style.css |
| e7f8a1b | 2025-01-12 | Add error summary for no-JS path (WCAG 3.3.1) | views/error-summary.peb, routes/Tasks.kt |

**Full log**: `git log --oneline --since="2025-01-06" --until="2025-01-12"`
```

### Step 2: Audit Evidence Directory

Your `evidence/` folder should be organised by week:

```
evidence/
├── wk6/
│   ├── consent-screenshot.png
│   └── backlog-before.png
├── wk7/
│   ├── axe-audit-before.png
│   ├── axe-audit-after.png
│   └── inline-edit-demo.png
├── wk8/
│   ├── nojs-verification.png
│   └── dual-path-trace.md
├── wk9/
│   ├── P1_console.png
│   ├── P2_console.png
│   ├── P3_nvda_console.png
│   ├── P4_nojs_console.png
│   └── metrics.csv
├── wk10/
│   ├── prioritisation-matrix.png
│   ├── fix01-before.png
│   ├── fix01-after.png
│   └── regression-checklist.pdf
└── wk11/
    ├── crit-slides.pdf
    ├── P3_retest_nvda.png
    └── reflection-final.pdf
```

**Action**: For each directory, check:
1. **File naming**: Descriptive, not generic (e.g., `P3_nvda_console.png` not `screenshot.png`)
2. **Legibility**: Open each image; confirm text is readable (min 1280×720)
3. **Context**: Create a `README.md` in each week's folder explaining what each file is

**Example** (`evidence/wk9/README.md`):
```markdown
# Week 9 Evidence: Evaluation & Pilots

- **P1_console.png**: Browser console for Pilot 1 (HTMX, mouse, JS-on); shows Logger entries for T3, T1, T2, T4.
- **P2_console.png**: Browser console for Pilot 2 (HTMX, keyboard, JS-on).
- **P3_nvda_console.png**: Combined screenshot showing NVDA speech viewer + browser console for Pilot 3 (SR, keyboard, JS-on). Note NVDA did NOT announce validation error (fixed in Week 10).
- **P4_nojs_console.png**: Browser console for Pilot 4 (no-JS, mouse); shows full-page reloads on form submit.
- **metrics.csv**: Raw data exported from `data/metrics.csv` after all pilots completed.
```

### Step 3: Update Repository README

Your main `README.md` should provide a **portfolio navigation guide**. Example:

```markdown
# COMP2850 HCI: Task Manager with Privacy by Design

**Student**: [Your Name]
**Module**: COMP2850 Human-Computer Interaction, University of Leeds
**Academic Year**: 2024/25

---

## Project Overview

This repository contains a **task manager web application** built with **Kotlin/Ktor + HTMX**, demonstrating:
- **Privacy by design**: Anonymous session IDs, no PII collected, UK GDPR compliance
- **WCAG 2.2 AA compliance**: Accessible to keyboard, screen reader, and no-JS users
- **Server-first architecture**: HTML rendered server-side, progressive enhancement with HTMX
- **Evidence-led design**: Evaluated via task-based pilots (n=4); redesigned based on metrics

---

## Portfolio Structure

### Code & Application
- **`src/`**: Kotlin/Ktor routes, models, utilities
- **`views/`**: Pebble templates (HTML)
- **`static/`**: CSS, JS (minimal), HTMX library
- **`data/`**: Anonymised session + metrics CSVs
- **`test/`**: Unit tests (not covered in labs)

### Evidence (by week)
- **`evidence/wk6/`**: Needs-finding, consent protocol, backlog initialisation
- **`evidence/wk7/`**: Ethics overlay (session IDs), accessibility audit (axe DevTools), inline edit fix
- **`evidence/wk8/`**: No-JS verification, dual-path routing, trade-offs doc
- **`evidence/wk9/`**: Pilot protocol, raw metrics, pilot notes, qualitative findings
- **`evidence/wk10/`**: Prioritisation matrix, fix commits, regression checklist, verification pilots
- **`evidence/wk11/`**: Studio crit slides, peer feedback, final reflection

### Gradescope Submissions
- **`task1/`**: Evaluation package (consent, pilots, analysis, findings, backlog)
- **`task2/`**: Redesign package (prioritisation, fixes, verification, reflection)

---

## Key Features

1. **Privacy by Design** (Week 6–7)
   - Anonymous session IDs (12-char hex) stored in cookies
   - No usernames, emails, or PII collected
   - Data stored in local CSV (not cloud database) with UK GDPR justification

2. **WCAG 2.2 AA Compliance** (Week 7, 10)
   - Semantic HTML (`<button>`, `<label>`, `<ul>`)
   - Keyboard accessible (Tab, Enter, Space)
   - Screen reader tested (NVDA 2024.1, VoiceOver)
   - Error handling: `role="alert" aria-live="assertive"`
   - Focus visible: Custom outline (3px solid #1976d2)

3. **Server-First + HTMX** (Week 6, 8)
   - All state managed server-side (tasks stored in `data/tasks.csv`)
   - HTMX handles dynamic updates (`hx-post`, `hx-target`, `hx-swap`)
   - No-JS parity: All features work with JS disabled (verified in Week 8)

4. **Evaluation & Iteration** (Week 9–10)
   - Task-based pilots (n=4): T1 (filter), T2 (edit), T3 (add), T4 (delete)
   - Metrics: Median = 18 s, MAD = 10 s; P3 (SR) outlier = 127 s
   - Prioritised fixes: (Impact + Inclusion) – Effort scoring
   - Verification: P3 retest reduced to 22 s (within 1 MAD)

---

## Running the Application

```bash
# Prerequisites: JDK 21+, Gradle 8+
./gradlew run

# Application starts on http://localhost:8080/tasks
```

**Test with different modes**:
- **HTMX + mouse**: Default behaviour (Chrome, Firefox)
- **HTMX + keyboard**: Tab to navigate, Enter/Space to activate
- **Screen reader**: NVDA (Windows), VoiceOver (macOS)
- **No-JS**: Disable JS in browser DevTools (F12 → Settings → Disable JavaScript)

---

## Commit Highlights

| Commit | Week | Description |
|--------|------|-------------|
| abc123 | 6 | Initial scaffold: routes, Pebble templates, HTMX setup |
| def456 | 6 | Add session ID generation + cookie storage |
| ghi789 | 7 | Add Logger for metrics instrumentation |
| jkl012 | 7 | Fix inline edit accessibility (focus + ARIA) |
| mno345 | 8 | Add dual-path validation (HTMX + no-JS) |
| pqr678 | 10 | Add role=alert to error div (WCAG 4.1.3) |
| stu901 | 10 | Add focus-visible outline (WCAG 2.4.7) |

**Full log**: `git log --oneline`

---

## References

- **HTMX**: [hypermedia.systems](https://hypermedia.systems/) (Carson Gross, 2023)
- **WCAG 2.2**: [W3C Quick Reference](https://www.w3.org/WAI/WCAG22/quickref/)
- **Privacy by Design**: [ICO Guide](https://ico.org.uk/for-organisations/uk-gdpr-guidance-and-resources/)
- **Ktor**: [ktor.io](https://ktor.io/)
- **Pebble Templates**: [pebbletemplates.io](https://pebbletemplates.io/)

---

## Acknowledgements

- **Module staff**: Dr. [Name], teaching assistants
- **Peers**: Studio crit feedback from [Team Names]
- **Tools**: axe DevTools, NVDA, Colour Contrast Analyser, curl

---

**Licence**: Academic work for COMP2850 (not for redistribution)
```

**Stop and check**: Open `README.md` in your repo. Verify:
- ✅ Project overview explains what you built and why
- ✅ Portfolio structure lists all key directories
- ✅ Running instructions work (test with `./gradlew run`)
- ✅ Commit highlights cite key milestones
- ✅ References cite all external sources (HTMX, WCAG, academic papers)

---

## Activity 4: Practice Gradescope Submission

**Time**: 20 minutes
**Materials**: assessment and assessment packages, Gradescope test environment (if available)

### Step 1: Check File Size Limits

Gradescope has a **100 MB limit per submission**. Check your package sizes:

```bash
du -sh task1/
du -sh task2/

# If over 50 MB, identify large files
du -ah task1/ | sort -rh | head -n 10
du -ah task2/ | sort -rh | head -n 10
```

**Common culprits**:
- Uncompressed screenshots (5–10 MB each)
- Video recordings (not required; remove or compress heavily)
- Database dumps (not required; use `metrics.csv` only)

**Compression tips**:
```bash
# Compress PNGs with ImageMagick
mogrify -resize 1920x1080 -quality 85 task1/**/*.png task2/**/*.png

# Or use online tool: https://tinypng.com/

# Compress PDFs with Ghostscript
gs -sDEVICE=pdfwrite -dCompatibilityLevel=1.4 -dPDFSETTINGS=/screen \
   -dNOPAUSE -dQUIET -dBATCH \
   -sOutputFile=reflection-compressed.pdf reflection-task1.pdf
```

### Step 2: Create Submission ZIPs

Gradescope typically requires a **single ZIP per task**. Create them:

```bash
# assessment
cd task1/
zip -r ../task1-submission.zip .
cd ..

# assessment
cd task2/
zip -r ../task2-submission.zip .
cd ..

# Verify contents
unzip -l task1-submission.zip
unzip -l task2-submission.zip
```

**Check**:
- ✅ All required files present (consent, pilots, analysis, reflection)
- ✅ No extra files (e.g., `.DS_Store`, `Thumbs.db`, `__MACOSX/`)
- ✅ File paths are flat or shallow (Gradescope doesn't always preserve deep nesting)

### Step 3: Test Upload (Dry Run)

If Gradescope has a **draft/test assignment**, upload your ZIPs there. Otherwise, do a local dry run:

1. **Simulate grader view**: Extract your ZIP in a temp directory and navigate it as if you're a marker:
   ```bash
   mkdir /tmp/grader-test
   cd /tmp/grader-test
   unzip ~/comp2850/task1-submission.zip

   # Open files in order:
   # 1. consent-script.md
   # 2. pilot-protocol.md
   # 3. analysis.md
   # 4. findings.md
   # 5. reflection-task1.pdf
   ```

2. **Check cross-references**: Do all citations work? (e.g., "See P3_notes.md line 14" → open that file and verify line 14 exists)

3. **Verify PDFs**: Open `reflection-task1.pdf` and `reflection-task2.pdf`:
   - ✅ Code blocks formatted correctly
   - ✅ Screenshots embedded and legible
   - ✅ Page numbers visible (if multi-page)
   - ✅ No truncated text or missing images

### Step 4: Pre-Submission Checklist

Print this checklist and tick off each item:

**assessment (Evaluation)**
- [ ] `consent-script.md` includes informed consent protocol
- [ ] `pilot-protocol.md` includes step-by-step task execution guide
- [ ] `metrics.csv` is anonymised (no real names, emails)
- [ ] `pilot-notes/` includes 4 files (P1, P2, P3, P4) with timestamps and quotes
- [ ] `analysis.md` includes median, MAD, error rate, completion rate
- [ ] `findings.md` synthesises insights with pilot citations
- [ ] `backlog-update.md` lists new items added, with priority scores
- [ ] `reflection-task1.pdf` compiled, readable, includes code examples and theory links
- [ ] Total size < 50 MB
- [ ] All commit hashes correct (`git log --oneline` to verify)

**assessment (Redesign)**
- [ ] `prioritisation.md` includes scoring matrix with rationale
- [ ] `fixes/` directory includes one file per priority issue (typically 3–5)
- [ ] `commits.md` lists all fix commits with hashes and files changed
- [ ] `regression-checklist.pdf` completed and signed off
- [ ] `verification/` includes retest data (metrics, pilot notes, screenshots)
- [ ] `wcag-compliance.md` maps fixes to WCAG 2.2 success criteria
- [ ] `reflection-task2.pdf` compiled, readable, includes before/after code and learning insights
- [ ] Total size < 50 MB
- [ ] All WCAG criteria correctly cited (check against [W3C Quick Reference](https://www.w3.org/WAI/WCAG22/quickref/))

**Repository**
- [ ] `README.md` includes portfolio navigation and running instructions
- [ ] `evidence/` organised by week with descriptive file names
- [ ] Commit history clean (no sensitive data, descriptive messages)
- [ ] Application runs locally (`./gradlew run` works)

### Step 5: Backup Strategy

Before assessment refinement:

1. **Tag your repository**:
   ```bash
   git tag -a v1.0-task1 -m "assessment submission (2025-01-15)"
   git tag -a v1.0-task2 -m "assessment submission (2025-01-17)"
   git push origin --tags
   ```

2. **Create backup ZIPs** with date stamps:
   ```bash
   cp task1-submission.zip task1-submission-2025-01-15.zip
   cp task2-submission.zip task2-submission-2025-01-17.zip
   ```

3. **Upload to cloud** (optional but recommended):
   - University OneDrive, Google Drive, or Dropbox
   - Keeps a timestamped copy in case Gradescope has issues

**Stop and check**: Unzip `task1-submission.zip` and `task2-submission.zip` in a temp directory. Open `reflection-task1.pdf` and `reflection-task2.pdf`. For each:
- ✅ All pages render correctly
- ✅ Code blocks use monospace font
- ✅ Screenshots are legible (text readable at 100% zoom)
- ✅ File size < 20 MB each

---

## Activity 5: Module Reflection & Semester 2 Planning

**Time**: 20 minutes
**Materials**: Lab notes from Weeks 6–11, backlog, peer feedback

### Step 1: Self-Assessment — Learning Outcomes

Review your evidence against all **13 HCI Learning Outcomes** you've addressed across Weeks 6-11. Rate your confidence (1 = not confident, 5 = very confident) and note where the evidence lives.

See [Learning Outcomes Reference](../references/learning-outcomes.md) for full LO definitions.

| LO | Outcome | Confidence (1–5) | Evidence Location |
|----|---------|------------------|-------------------|
| **LO1** | Differentiate people-centred methods | ☐☐☐☐☐ | W6 L2 job stories, W9 L1 eval plan |
| **LO2** | Design and conduct needs-finding | ☐☐☐☐☐ | W6 L2 consent protocol |
| **LO3** | Analyse ethical implications | ☐☐☐☐☐ | W7 L1 consent modal, privacy audit |
| **LO4** | Evaluate for accessibility | ☐☐☐☐☐ | W7 L2 axe audit, WCAG map |
| **LO5** | Create prototypes | ☐☐☐☐☐ | W8 L1 HTMX features |
| **LO6** | Apply iterative design | ☐☐☐☐☐ | W9 L2 pilots → W10 L2 redesign |
| **LO7** | Analyse design constraints | ☐☐☐☐☐ | W8 L2 no-JS trade-offs doc |
| **LO8** | Design and execute evaluation | ☐☐☐☐☐ | W9 L1 metrics + W9 L2 pilots |
| **LO9** | Apply inclusive design | ☐☐☐☐☐ | W7 L2 fix, W10 L2 redesign |
| **LO10** | Critique societal impacts | ☐☐☐☐☐ | W7 L1 ethics reflection |
| **LO11** | Collaborate in teams | ☐☐☐☐☐ | W9 L2 peer pilots, W11 L1 crit |
| **LO12** | Demonstrate professionalism | ☐☐☐☐☐ | All labs: consent, citations |
| **LO13** | Integrate HCI with SE | ☐☐☐☐☐ | W8 L1 Ktor patterns, W9 L1 instrumentation |

**Confidence scale**: 1 = Not confident, 3 = Moderately confident, 5 = Very confident

**Reflection prompt**:
1. For each LO, locate your evidence (code, docs, reflections)
2. Note any gaps — do you have weak evidence for any LO?
3. For any outcome rated < 4, what would help you improve? (More practice, more reading, more feedback?)

### Step 2: Identify Key Insights

Write **3–5 bullet points** summarising what you learned:

**Example**:
- **Server-first simplifies accessibility**: By rendering HTML server-side, I avoided complex ARIA management and focus trapping. This reinforced that progressive enhancement (start with HTML, add JS sparingly) is faster and more robust than JS-heavy SPAs.
- **Evidence changes everything**: Before Week 9 pilots, I thought my task manager was "fine". Metrics revealed a 10.9 MAD outlier for SR users, forcing me to confront assumptions. Quantitative + qualitative data is essential for inclusive design.
- **Prioritisation is a skill**: I wanted to fix all 8 issues, but (Impact + Inclusion) – Effort scoring helped me focus on the 3 that mattered most. This taught me that good design isn't about perfection—it's about impact per unit of effort.
- **Accessibility is iterative**: My first fix (adding `role="alert"`) didn't work because I forgot `aria-live`. After reading WCAG 4.1.3 and testing with NVDA, I corrected it. This trial-and-error process is normal and valuable.
- **Reflection cements learning**: Writing 1,500 words about my redesign forced me to connect theory (WCAG) to practice (code) in a way that coding alone didn't. Research shows that portfolios deepen understanding.

### Step 3: Update Backlog for Semester 2

Your product backlog should include:

1. **Unfinished Week 11 items** (deprioritised due to time)
2. **Peer feedback** (from studio crit)
3. **New ideas** (from reflection or further reading)

**Example** (backlog extract):

| ID | Description | Priority | Effort | Notes |
|----|-------------|----------|--------|-------|
| #8 | Add keyboard shortcut hints (e.g., "Press / to focus search") | P2 | 3 | Feedback from peer review; improves discoverability |
| #9 | Implement undo for task deletion | P2 | 4 | Not in scope for assessment, but valuable for real users |
| #10 | Add dark mode toggle (WCAG 1.4.3 compliance) | P3 | 5 | Low priority; contrast already meets AA |
| #11 | Explore HTMX websockets for real-time collaboration | P4 | 8 | Stretch goal; requires learning new HTMX feature |
| #12 | Add automated a11y tests (axe-core in CI/CD pipeline) | P1 | 6 | High priority; prevents regressions in future work |

**Action**: Add these items to your `backlog.md` or issue tracker (GitLab/GitHub Issues).

### Step 4: Write Final Reflection (Optional but Recommended)

If you have time, write a **500-word final reflection** summarising your journey from Week 6 to Week 11. Save as `evidence/wk11/reflection-final.md`.

**Prompts**:
- What was the most challenging part of this module?
- What surprised you about HCI or accessibility?
- How will you apply these skills in future projects (Semester 2, internships, career)?
- What would you do differently if you started again?

**Example** (first 150 words):
> The most challenging part of COMP2850 was shifting from "code-first" to "people-first" thinking. In previous modules (COMP1721, COMP2811), I optimised for elegance (clean OOP, efficient algorithms). In COMP2850, I learned that **usability trumps elegance**. My first instinct was to build a React SPA with complex state management, but the server-first + HTMX approach forced me to prioritise simplicity and robustness. This felt uncomfortable at first—I kept wanting to add JS—but Week 9 pilots proved that simpler is often better. Pilot P4 (no-JS) completed tasks just as fast as Pilot P1 (HTMX + mouse), which validated the "hypermedia as the engine of application state" philosophy from Carson Gross.
>
> The biggest surprise was how **cheap** accessibility fixes are if you design them in from the start. Adding `role="alert"` took 2 minutes; adding it *after* building 500 lines of validation logic took 2 hours (because I had to refactor the error-handling architecture). This taught me...

### Step 5: Plan Semester 2 Goals

COMP2850 continues in Semester 2 with advanced topics. Based on your Week 6–11 experience, set **3 goals**:

**Example**:
1. **Goal 1 (Technical)**: Learn HTMX websockets to add real-time collaboration (backlog #11).
   - **How**: Read hypermedia.systems Ch. 11; build a prototype with 2-person task sharing.
   - **Why**: Real-time features are common in industry (Google Docs, Figma); want to understand how to build them accessibly.

2. **Goal 2 (Research)**: Conduct a formal usability study (n=8, IRB approval) comparing HTMX vs React for accessibility.
   - **How**: Replicate Week 9 protocol with 4 HTMX pilots + 4 React pilots; compare median time + SR usability.
   - **Why**: Interested in HCI research as a career; want to publish at CHI or ASSETS.

3. **Goal 3 (Professional)**: Contribute accessibility fixes to an open-source project.
   - **How**: Find a project with open a11y issues (e.g., on GitHub "good first issue" + "accessibility" labels); submit PR.
   - **Why**: Build portfolio, gain experience with real-world codebases, give back to community.

**Action**: Write these goals in `evidence/wk11/semester2-goals.md` or a personal notebook.

**Stop and check**: Open your backlog (`backlog.md` or issue tracker). Verify:
- ✅ All incomplete items from Week 6–11 are listed
- ✅ Peer feedback suggestions are added (with priority scores)
- ✅ Semester 2 stretch goals are identified
- ✅ Each item has a priority (P1–P4) and effort estimate (1–8)

---

## Semester 2 Roadmap: Building on Your Foundation

**Overview**: In Semester 1 (Weeks 6–11), you built a **privacy-by-design task manager** with WCAG 2.2 AA compliance, evaluated it with task-based pilots, and iterated based on evidence. Semester 2 builds on this foundation by introducing **advanced HCI topics**: multi-user collaboration, AI-assisted features, performance optimisation, and scaling to production. This section previews key features, technical topics, and skills development paths for Semester 2.

---

### Features Roadmap

The following features represent typical Semester 2 enhancements. Your backlog (from Activity 5, Step 3) should prioritise 3–5 of these based on your learning goals.

| Feature | Description | HCI Concepts | Technical Skills | WCAG Considerations |
|---------|-------------|--------------|------------------|---------------------|
| **1. Pagination** | Display 10 tasks per page with "Previous/Next" navigation | Information architecture, cognitive load | Query string parameters, server-side pagination logic | WCAG 2.4.1 (Bypass Blocks), 2.4.8 (Location) |
| **2. Tag/Category System** | Add multiple tags per task; filter by tag | Metadata schema, faceted search | Many-to-many relationships (CSV join table or SQLite) | WCAG 4.1.2 (Name, Role, Value) for tag buttons |
| **3. Search & Autocomplete** | Full-text search across task titles; suggest as you type | Search UX patterns, latency perception | HTMX debouncing, server-side text search | WCAG 4.1.3 (Status Messages) for result count |
| **4. Multi-User (Sessions)** | Multiple users with separate task lists (no login, session-based) | Privacy vs collaboration trade-offs | Session isolation, CSV partitioning by session ID | WCAG 3.3.8 (Accessible Authentication - Minimum) |
| **5. Real-Time Updates** | When User A adds a task, User B's list auto-updates (via WebSockets) | Shared awareness, CSCW principles | HTMX WebSockets extension, Ktor WebSocket plugin | WCAG 4.1.3 (announce updates via live regions) |
| **6. AI Task Suggestions** | LLM generates task breakdowns (e.g., "Write essay" → sub-tasks) | AI transparency, user agency | OpenAI API integration, prompt engineering | WCAG 3.3.2 (Labels or Instructions) for AI disclaimers |
| **7. Offline Mode (PWA)** | Service worker caches app; tasks sync when online | Network resilience, progressive web apps | Service Worker API, IndexedDB, background sync | WCAG 3.2.4 (Consistent Identification) across online/offline |
| **8. Accessibility Dashboard** | Real-time WCAG compliance score; lighthouse metrics | Automated testing limits, developer tools | axe-core integration, CI/CD pipeline | Meta-accessibility: ensuring dashboard itself is accessible |
| **9. Dark Mode** | User-selectable theme (light/dark/high-contrast) | Personalisation, sensory preferences | CSS custom properties, `prefers-colour-scheme` media query | WCAG 1.4.3 (Contrast - Minimum) across all themes |
| **10. Export & Import** | Download tasks as JSON/CSV/PDF; import from Todoist/Trello | Data portability, interoperability | CSV/JSON serialisation, PDF generation (iText/PDFKit) | WCAG 1.1.1 (ensure exported PDFs are tagged/accessible) |

**Prioritisation guidance**: If your Semester 1 evaluation identified **performance issues** (e.g., slow search), prioritise pagination + search (features 1, 3). If you're interested in **AI/ethics**, prioritise feature 6 + transparency documentation. If you want to explore **real-time collaboration**, prioritise features 4–5.

---

### Technical Topics (Semester 2 Lectures/Labs)

Semester 2 typically covers these advanced HCI topics. Your portfolio work will align with one or more:

1. **Computer-Supported Cooperative Work (CSCW)**
   - **What**: Design patterns for multi-user collaboration (conflict resolution, awareness, turn-taking)
   - **Application**: Features 4–5 (multi-user, real-time updates)
   - **Reading**: Grudin (1994) "CSCW: History and Focus"; Schmidt & Bannon (1992) "Taking CSCW Seriously"

2. **AI & Automation Ethics**
   - **What**: Transparency, explainability, user agency when integrating AI features
   - **Application**: Feature 6 (AI task suggestions) with clear disclaimers ("AI-generated; verify before relying on")
   - **Reading**: Shneiderman (2022) "Human-Centered AI"; EU AI Act principles

3. **Performance & Perceived Responsiveness**
   - **What**: Optimising server response time, perceptual speed tricks (skeleton screens, optimistic UI)
   - **Application**: Features 1, 3 (pagination, search) with <100ms response targets
   - **Reading**: Nielsen (1993) "Response Times: The 3 Important Limits"

4. **Progressive Web Apps (PWAs)**
   - **What**: Offline-first architecture, service workers, installable web apps
   - **Application**: Feature 7 (offline mode) with background sync when network returns
   - **Reading**: MDN Web Docs: Service Worker API; "Offline Cookbook" patterns

5. **Inclusive AI & Assistive Tech Integration**
   - **What**: Ensuring AI features work with screen readers, voice input, switch access
   - **Application**: Feature 6 (AI suggestions) with ARIA announcements, feature 8 (a11y dashboard) accessible to all
   - **Reading**: W3C (2024) "AI & Accessibility Research Symposium"

6. **Scaling to Production**
   - **What**: Database migration (CSV → PostgreSQL), caching (Redis), deployment (Docker, Railway, Fly.io)
   - **Application**: All features; moving from prototype to production-ready app
   - **Reading**: Ktor docs on Database integration; Railway deployment guides

**Study path**: If you're interested in **research**, focus on topics 1–2 (CSCW, AI ethics) and design a formal study. If you're interested in **industry**, focus on topics 3, 6 (performance, scaling) and build a production deployment. If you're interested in **accessibility advocacy**, focus on topics 5, 8 (inclusive AI, a11y tooling).

---

### Backlog Integration

Your **product backlog** (from Activity 5, Step 3) should now include:

1. **Incomplete Semester 1 items** (e.g., backlog #8–12 from Activity 5 example)
2. **Peer feedback** (from Week 11 Lab 1 studio crit)
3. **Semester 2 feature candidates** (select 3–5 from table above)

**Example integrated backlog** (stored in `backlog.md` or issue tracker):

| ID | Description | Priority | Effort | Notes | Semester |
|----|-------------|----------|--------|-------|----------|
| #1 | ✅ CRUD operations (add, edit, delete tasks) | P1 | 8 | Complete (Week 6) | S1 |
| #2 | ✅ HTMX dual-mode (JS-on vs no-JS) | P1 | 10 | Complete (Week 6, 8) | S1 |
| #3 | ✅ Validation error alerts (WCAG 4.1.3) | P1 | 2 | Complete (Week 10) | S1 |
| #8 | Add keyboard shortcut hints | P2 | 3 | Peer feedback (Week 11) | S1 |
| #9 | Implement undo for task deletion | P2 | 4 | Deferred from Week 10 | S1 |
| #12 | Add automated a11y tests (axe-core CI/CD) | P1 | 6 | High priority for S2; prevents regressions | **S2** |
| #13 | **Pagination** (10 tasks/page) | P2 | 5 | Improves performance for 100+ tasks | **S2** |
| #14 | **Tag system** (filter by category) | P2 | 8 | Requested in Week 6 needs-finding | **S2** |
| #15 | **Search with autocomplete** | P3 | 6 | Nice-to-have; low priority vs pagination | **S2** |
| #16 | **AI task breakdown** (LLM integration) | P4 | 10 | Stretch goal; requires OpenAI API + ethics review | **S2** |

**Priority formula (from Week 10)**: `(Impact + Inclusion) - Effort`

**Action**: Open your `backlog.md` (or create it in your repo root) and add Semester 2 features with priority scores.

---

### Skills Development Path

To successfully implement Semester 2 features, you'll need to develop these skills progressively:

#### Path 1: Full-Stack Developer (Features 1–5, 7)
**Goal**: Build production-ready, scalable web app

| Skill | When to Learn | Resources | Application |
|-------|---------------|-----------|-------------|
| **SQL basics** (PostgreSQL) | Week 1–2 (S2) | Ktor Database docs, PostgreSQL tutorial | Replace CSV with relational DB (features 1, 2, 4) |
| **Indexing & query optimisation** | Week 3–4 (S2) | PostgreSQL EXPLAIN, indexing strategies | Feature 3 (search); ensure <100ms response |
| **WebSockets (HTMX extension)** | Week 5–6 (S2) | hypermedia.systems Ch. 11; Ktor WebSocket plugin | Feature 5 (real-time updates) |
| **Service Workers & PWA** | Week 7–8 (S2) | MDN Service Worker guide; Workbox library | Feature 7 (offline mode) |
| **Deployment (Docker, Railway)** | Week 9–10 (S2) | Railway docs; Ktor deployment guide | Production hosting |

#### Path 2: HCI Researcher (Features 6, 8)
**Goal**: Conduct formal usability studies; publish research

| Skill | When to Learn | Resources | Application |
|-------|---------------|-----------|-------------|
| **Formal study design** (IRB, informed consent) | Week 1–2 (S2) | University ethics committee guidelines | Replicate Week 9 protocol with n=8+ participants |
| **Statistical analysis** (t-tests, ANOVA) | Week 3–4 (S2) | R/Python (scipy.stats); "Statistics for HCI" course | Compare median task times across conditions (HTMX vs React) |
| **Qualitative coding** (thematic analysis) | Week 5–6 (S2) | Braun & Clarke (2006) thematic analysis guide | Analyse pilot quotes from Week 9 |
| **AI transparency frameworks** | Week 7–8 (S2) | EU AI Act, Shneiderman (2020) Human-Centered AI | Feature 6 (AI suggestions) with user control |
| **Academic writing** (CHI, ASSETS format) | Week 9–10 (S2) | CHI paper examples; ACM SIGCHI templates | Write 4-page paper on findings; submit to CHI LBW |

#### Path 3: Accessibility Specialist (Features 8–9)
**Goal**: Become expert in WCAG, audit tools, inclusive design

| Skill | When to Learn | Resources | Application |
|-------|---------------|-----------|-------------|
| **Automated testing** (axe-core, Pa11y) | Week 1–2 (S2) | axe-core npm docs, Pa11y CI integration | Feature 8 (a11y dashboard); CI/CD pipeline |
| **Manual SR testing** (NVDA, JAWS, VoiceOver) | Week 3–4 (S2) | WebAIM screen reader guides; NVDA user guide | Re-test all features with 3 SR users |
| **ARIA patterns** (tabs, modals, tooltips) | Week 5–6 (S2) | W3C ARIA Authoring Practices Guide (APG) | Add modal dialogs, tooltips (with role=dialog, aria-labelledby) |
| **Colour contrast & theming** | Week 7–8 (S2) | Colour Contrast Analyser, CSS custom properties | Feature 9 (dark mode); ensure 4.5:1 in all themes |
| **A11y advocacy & training** | Week 9–10 (S2) | Write blog posts, give talks | Contribute to open-source projects; teach others |

**Choose your path** based on career goals, then add relevant skills to your Semester 2 learning plan.

---

### Motivation: Why Continue?

You've invested 6 weeks (Weeks 6–11) building a solid foundation. Here's why Semester 2 matters:

#### 1. **Portfolio Differentiation**
Most CS students can build a CRUD app. Few can demonstrate:
- **Evidence-led iteration** (pilot data → prioritised fixes → verification)
- **WCAG 2.2 AA compliance** (tested with real assistive tech)
- **Privacy by design** (UK GDPR, no PII, transparent data practices)
- **Server-first architecture** (progressive enhancement, no-JS parity)

Semester 2 features (pagination, search, AI integration) add **complexity** while maintaining **accessibility**—a rare combination that employers and grad schools value.

#### 2. **Research Opportunities**
Your Week 9 pilots produced **publishable data**. With a larger sample (n=8+, IRB approval), you could submit to:
- **CHI** (ACM Conference on Human Factors in Computing Systems) - Late-Breaking Work track
- **ASSETS** (ACM SIGACCESS Conference on Computers and Accessibility)
- **University undergraduate research conferences** (Leeds Laidlaw, national showcases)

**Example research question** (from your Semester 1 work):
> "Do server-first architectures (HTMX) improve screen reader usability compared to client-side SPAs (React)? A comparative task-based study (n=16, between-subjects)."

This extends your Week 9 methodology and could lead to co-authorship with module staff.

#### 3. **Industry Readiness**
Employers look for:
- **End-to-end ownership**: Requirements → design → implementation → evaluation → iteration
- **Cross-functional skills**: Code (Kotlin/Ktor), design (WCAG), research (pilots), writing (reflection)
- **Trade-off reasoning**: "I prioritised X over Y because [evidence]"

Semester 2 features demonstrate **production thinking**:
- Feature 1 (pagination): "I scaled to 10,000 tasks with acceptable latency"
- Feature 5 (real-time): "I implemented WebSockets while maintaining SR compatibility"
- Feature 6 (AI): "I integrated LLMs with transparent disclaimers and user override"

These are **STAR interview stories** (Situation, Task, Action, Result) that differentiate you from peers.

#### 4. **Personal Mastery**
COMP2850 is hard. You've learned:
- **Server-first thinking** (hypermedia as engine of state)
- **Inclusive design** (start with HTML, add ARIA only when needed)
- **Evidence-based iteration** (metrics > intuition)

Semester 2 is where you **master** these principles by applying them to harder problems (multi-user, real-time, AI). Mastery comes from deliberate practice, not from moving to a new topic.

---

### Concrete Next Steps (Week 12+)

**Before Semester 2 starts** (Winter break or Week 12):

1. **Choose 3 features** from the roadmap table (lines 911–920)
   - Pick based on career interest (full-stack, research, or a11y specialist path)
   - Add to backlog with priority scores (Impact + Inclusion - Effort)

2. **Learn 1 new skill** from your chosen path
   - Full-stack: Complete PostgreSQL tutorial (replace CSV in 1 feature)
   - Research: Read 3 CHI papers on accessibility evaluation methods
   - A11y: Master one ARIA pattern (modal dialog, tabs, or tooltips)

3. **Refine your portfolio**
   - Convert `README.md` to a static site (GitHub Pages, GitLab Pages)
   - Add "Semester 2 Roadmap" section citing your chosen features
   - Share with peers/mentors for feedback

4. **Find a collaborator** (optional)
   - Pair with a peer who chose a complementary path
   - Example: You (full-stack) + peer (a11y specialist) = feature 5 (real-time) with SR testing

5. **Set a measurable goal**
   - Example: "By Week 8 (S2), I will deploy a production instance with 100 real users and collect SUS scores"
   - Example: "By Week 10 (S2), I will submit a 4-page paper to CHI LBW"

**During Semester 2**:

- **Weeks 1–4**: Implement features 1–2 (pagination + one stretch feature)
- **Weeks 5–8**: Implement feature 3 or 4 (search or multi-user)
- **Weeks 9–10**: Evaluate, iterate, and document (repeat Week 9–10 process)
- **Week 11–12**: Final portfolio assembly, research paper (if applicable), deployment

**After Semester 2**:

- **Showcase**: Present at university research fair, publish blog post, submit to open-source conferences (FOSDEM, etc.)
- **Job applications**: Use portfolio as centerpiece of CV; link to live deployment
- **Grad school**: Use research paper as writing sample for HCI MSc/PhD applications

---

### Resources

**Backlog Management**
- **GitHub Issues/Projects**: Free issue tracker with labels, milestones, priorities
- **GitLab Issue Boards**: Kanban-style backlog (drag-and-drop prioritisation)
- **Notion/Trello**: If you prefer visual boards over code-based trackers

**Learning Paths**
- **Full-stack**: [Ktor Docs](https://ktor.io/), [PostgreSQL Tutorial](https://www.postgresqltutorial.com/), [Railway Deploy Guide](https://docs.railway.app/)
- **Research**: [CHI 2025 Call for Papers](https://chi2025.acm.org/), [ASSETS 2025](https://assets25.sigaccess.org/), [UX Research Methods by NNGroup](https://www.nngroup.com/articles/)
- **A11y**: [W3C ARIA Authoring Practices Guide](https://www.w3.org/WAI/ARIA/apg/), [axe-core npm](https://www.npmjs.com/package/axe-core), [Deque University](https://dequeuniversity.com/)

**Inspiration (Real-World Examples)**
- **Linear** (task manager): Server-first with real-time (demonstrates feature 5)
- **Basecamp** (project management): No-JS baseline, progressive enhancement (demonstrates feature 2)
- **GOV.UK Design System**: WCAG AAA components, open-source (demonstrates feature 8–9)

---

**Semester 2 begins soon—your foundation is strong. Choose your path, set measurable goals, and keep building with evidence and empathy.**

---

## Glossary Summary

| Term | Definition | Example/Context |
|------|------------|-----------------|
| **Evidence chain** | Traceable path from raw data → analysis → fix → verification | P3 pilot notes → WCAG violation → code commit → retest |
| **Reflective writing** | Connecting theory to practice by answering What/Why/How/Learning | "I added role=alert (WCAG 4.1.3) because P3 didn't hear errors" |
| **Portfolio** | Organised collection of artefacts with accompanying reflection | Code repo + evidence/ + assessment/2 packages + README |
| **Traceability** | Ability to link every claim to supporting evidence | "P3 took 127 s (see metrics.csv row 47)" |
| **Gradescope** | Submission platform with file size/format requirements | 100 MB limit, PDF preferred, flat directory structure |
| **Commit hash** | Unique identifier for a Git commit | `c8d1e4f` (first 7 chars of SHA-1) |
| **Regression testing** | Verifying fixes didn't break existing features | Keyboard, SR, no-JS paths all retested after validation fix |
| **Backlog** | Prioritised list of unfinished or future work | "Add undo feature" (P2, Effort: 4) |
| **WCAG compliance doc** | Mapping fixes to WCAG 2.2 success criteria | Fix 01 → 4.1.3 Status Messages (AA) |
| **Compression** | Reducing file size (images, PDFs) to meet submission limits | PNG: 8 MB → 2 MB via TinyPNG; PDF: 15 MB → 5 MB via Ghostscript |

---

## Reflection Questions

1. **Evidence chains**: Choose one finding from your Week 9 analysis. Trace the complete evidence chain from raw data (pilot ID, task, metric) to fix (commit hash) to verification (retest result). Is any link in the chain missing or weak?

2. **Reflective writing quality**: Reread your assessment and assessment reflections. For each, identify:
   - One place where you cited theory (WCAG, privacy principle, academic source)
   - One place where you described a mistake and correction
   - One place where you connected learning to future practice

3. **Portfolio organisation**: Imagine you are a marker with 50 portfolios to grade. Open your `README.md` and `evidence/` directory. Can you find any specific artefact (e.g., "P3 retest screenshot") in < 30 seconds? If not, what would improve navigation?

4. **Submission readiness**: If Gradescope rejects your ZIP due to file size, which files would you compress first? Why?

5. **Semester 2 planning**: Of the 3 goals you set in Activity 5, which excites you most? Which scares you most? Why?

---

## Further Reading

**Reflective practice**
- Moon, J. A. (2004). *A Handbook of Reflective and Experiential Learning: Theory and Practice*. Routledge.

**Evidence-based design**
- Shneiderman, B., Plaisant, C., Cohen, M., Jacobs, S., Elmqvist, N., & Diakopoulos, N. (2016). *Designing the User Interface: Strategies for Effective Human-Computer Interaction* (6th ed.). Pearson. (Ch. 4: Evaluation)

**Portfolio assessment**
- Paulson, F. L., Paulson, P. R., & Meyer, C. A. (1991). "What Makes a Portfolio a Portfolio?" *Educational Leadership*, 48(5), 60–63. <https://www.ascd.org/el/articles/what-makes-a-portfolio-a-portfolio>

**Accessibility compliance**
- W3C (2024). *How to Meet WCAG (Quick Reference)*. <https://www.w3.org/WAI/WCAG22/quickref/>
- GOV.UK (2024). *Making Your Service Accessible*. <https://www.gov.uk/service-manual/helping-people-to-use-your-service/making-your-service-accessible-an-introduction>

**Server-first architecture**
- Gross, C., Stepinski, A., & Akşimşek, D. (2023). *Hypermedia Systems*. <https://hypermedia.systems/> (Ch. 9: Practical Patterns; Ch. 11: Websockets)

---

## Lab Checklist

Before leaving lab, confirm:

- [ ] **assessment polished**: Critique feedback incorporated; evidence complete; reflection compiled to PDF
- [ ] **assessment assembled**: Fix docs written; regression checklist completed; verification data organised; reflection compiled to PDF
- [ ] **Repository tidy**: Commit history clean; `README.md` updated; `evidence/` organised by week
- [ ] **Submission ready**: `task1-submission.zip` and `task2-submission.zip` created; file sizes < 50 MB each; PDFs legible
- [ ] **Backlog updated**: Peer feedback + Semester 2 goals added; priorities assigned
- [ ] **Final reflection written**: 500-word summary of Weeks 6–11 journey (optional but recommended)
- [ ] **Backup created**: Repository tagged (`v1.0-task1`, `v1.0-task2`); ZIPs backed up to cloud

---

## Next Steps

1. **Gradescope submission**: Upload `task1-submission.zip` and `task2-submission.zip` by the deadline (check Minerva for exact date/time).
2. **Peer review** (if required): Some modules assign post-submission peer review; check Minerva announcements.
3. **Semester 2 prep**: Review your backlog and Semester 2 goals; prioritise 1–2 items to start over break.
4. **Portfolio showcase**: Consider converting your `README.md` to a static site (e.g., GitHub Pages) to show potential employers.
5. **Celebrate**: You've completed a rigorous HCI project with evaluation, iteration, and evidence chains. Well done!

---

## Acknowledgements

This lab draws on:
- **GOV.UK Design System** for accessible error handling patterns
- **hypermedia.systems (Gross et al., 2023)** for server-first architecture guidance
- **WCAG 2.2** for accessibility compliance mapping
- **University of Leeds Academic Skills Centre** for reflective writing frameworks

---

**Lab authored by**: COMP2850 Teaching Team, University of Leeds
**Last updated**: 2025-01-14
**Licence**: Academic use only (not for redistribution)
