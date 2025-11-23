# Screenshot Annotations — Task 2

**Purpose**: Provide alt text and context for all screenshots (accessibility + transparency).

---

## Template

For each screenshot, include:

1. **Filename**: `[filename].png`
2. **Alt text**: Brief description (for screen readers)
3. **Context**: When captured, what task, what variant
4. **What it shows**: Detailed description of issue or fix
5. **Relevance**: Why this matters for evidence chain
6. **Related files**: Links to findings, brief, diffs

---

## Example: before-validation-error.png

**Alt text**: "Task edit form showing validation error 'Title is required' in red text below title input. Browser inspector panel shows error span element without role='alert' attribute."

**Context**:
- **When**: Week 9 baseline (or recreated from pre-fix code)
- **Task**: T2 (Edit task)
- **Variant**: Standard (HTMX, mouse, JS-on)
- **Browser**: Chrome 120, DevTools open

**What it shows**:
Validation error message is **visually present** but lacks `role="alert"` attribute. Browser inspector confirms `<span class="error">Title is required</span>` without ARIA attributes.

This means:
- ✅ Sighted users can see error
- ❌ Screen reader users hear **no announcement** (error is "silent")
- ❌ WCAG 4.1.3 (Status Messages AA) violation

**Relevance**:
- Supports Week 9 finding A1: "Validation errors not announced by screen readers"
- Shows **before state** for before/after comparison
- Proves WCAG violation with browser inspector evidence

**Related files**:
- Week 9 `05-findings.md` section A1
- `01-redesign-brief.md` problem statement
- `04-key-diffs.md` Change 1 (validation error accessibility)
- `screen-reader-transcripts/before-nvda-validation.txt` (no announcement captured)

---

## Example: after-validation-error.png

**Alt text**: "Task edit form showing validation error 'Title is required' in larger, high-contrast red text below title input. Browser inspector panel shows error span element WITH role='alert' and input element WITH aria-describedby='title-hint title-error'."

**Context**:
- **When**: Week 10 post-fix
- **Task**: T2 (Edit task)
- **Variant**: Standard (HTMX, mouse, JS-on)
- **Browser**: Chrome 120, DevTools open

**What it shows**:
Validation error message now has `role="alert"` and input has `aria-describedby="title-hint title-error"`. Browser inspector confirms:
- `<span id="title-error" class="error" role="alert">Title is required</span>`
- `<input ... aria-describedby="title-hint title-error">`

This means:
- ✅ Sighted users see improved error (larger, higher contrast)
- ✅ Screen reader users hear **automatic announcement** ("Alert. Title is required.")
- ✅ SR users hear error again when focused on input (via aria-describedby)
- ✅ WCAG 4.1.3 (Status Messages AA) **compliance**

**Relevance**:
- Shows **after state** for before/after comparison
- Proves WCAG compliance with browser inspector evidence
- Demonstrates fix implementation (not just claims)

**Related files**:
- `01-redesign-brief.md` Change 1 (after code snippet)
- `04-key-diffs.md` Change 1 (validation error accessibility)
- `screen-reader-transcripts/after-nvda-validation.txt` (announcement captured)
- `03-before-after-summary.md` (sr_accessibility: 0% → 100%)

---

## [Add your screenshots here]

### [filename].png

**Alt text**: "[Description]"

**Context**:
- **When**: [Week 9 baseline / Week 10 post-fix]
- **Task**: [T1/T2/T3/T4]
- **Variant**: [Standard / Keyboard-only / No-JS / Screen reader]
- **Browser**: [Chrome/Firefox/Edge + version]

**What it shows**: [Detailed description]

**Relevance**: [Why this matters]

**Related files**: [Links to other evidence]

---

## Accessibility Note

**Why annotate screenshots?**

1. **Screen reader users**: Cannot see images—need alt text
2. **Future reference**: Screenshots without context lose meaning over time
3. **Transparency**: Evidence must be traceable and verifiable
4. **Professional practice**: Industry standards require documentation
5. **Academic integrity**: Demonstrates you understand what you're showing

**Format**: Keep alt text concise (1-2 sentences), use context/relevance sections for detail.

---

**Author**: [Your name]
**Last updated**: [YYYY-MM-DD]
