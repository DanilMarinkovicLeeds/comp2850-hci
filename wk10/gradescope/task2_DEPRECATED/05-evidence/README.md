# Evidence Artefacts — Task 2

**Purpose**: Visual and qualitative evidence supporting before/after claims in redesign brief and findings.

---

## Directory Structure

```
05-evidence/
├── README.md                              ← You are here
├── screenshots/
│   ├── before-validation-error.png        ← Week 9 state (issue visible)
│   ├── after-validation-error.png         ← Week 10 state (issue fixed)
│   ├── before-error-no-focus.png          ← No-JS error handling (before)
│   ├── after-error-summary-focus.png      ← No-JS error handling (after)
│   ├── contrast-before.png                ← Color Contrast Analyser showing 3.5:1 (fail)
│   ├── contrast-after.png                 ← Color Contrast Analyser showing 7.1:1 (pass AAA)
│   └── annotations.md                     ← Alt text + context for all screenshots
├── screen-reader-transcripts/
│   ├── before-nvda-validation.txt         ← NVDA output before fix
│   └── after-nvda-validation.txt          ← NVDA output after fix
└── code-snippets/
    ├── routes-before.kt                   ← Relevant code snippets (before)
    ├── routes-after.kt                    ← Relevant code snippets (after)
    └── template-diffs.md                  ← Template changes with annotations
```

---

## 1. Screenshots

### Purpose

Provide visual evidence of:
- **Before state**: Accessibility issues from Week 9 pilots
- **After state**: Fixes implemented in Week 10
- **Contrast analysis**: Color Contrast Analyser results
- **Browser inspector**: ARIA attributes, role assignments
- **Assistive tech**: Screen reader announcement regions

### Guidelines

**Required for each screenshot**:
1. **Filename**: Descriptive (e.g., `before-validation-error.png` not `screenshot1.png`)
2. **Annotation**: Entry in `screenshots/annotations.md` with alt text + context
3. **Privacy**: **No PII** (crop out names, emails, student IDs, session tokens)
4. **Context**: Include enough UI to understand the issue/fix

**Recommended screenshots**:

| Screenshot | Purpose | When to Capture |
|-----------|---------|-----------------|
| `before-validation-error.png` | Show error without role="alert" | Week 9 baseline (or recreate from code) |
| `after-validation-error.png` | Show error with role="alert" + aria-describedby | Week 10 after fix |
| `after-error-summary.png` | Show error summary with links to fields | Week 10 after fix (no-JS path) |
| `contrast-before.png` | Colour Contrast Analyser showing 3.5:1 (fail) | Before fix |
| `contrast-after.png` | Colour Contrast Analyser showing 7.1:1 (pass AAA) | After fix |
| `inspector-aria-attributes.png` | Browser DevTools showing aria-describedby, role="alert" | After fix (verify implementation) |
| `zoom-200-before.png` | Page at 200% zoom showing issues | Before fix (optional) |
| `zoom-200-after.png` | Page at 200% zoom working correctly | After fix (optional) |

---

## 2. Screen Reader Transcripts

### Purpose

Capture exact SR announcements to prove errors are (or aren't) announced.

### Format

**File**: `screen-reader-transcripts/before-nvda-validation.txt`

```
NVDA Screen Reader Transcript — Before Fix
===========================================

Test: Submit blank task edit form (T2)
SR: NVDA 2024.1
Browser: Chrome 120
Date: 2025-10-15 (Week 9 baseline)

---

User action: Press Tab to title input
NVDA: "Title, edit, blank"

User action: Press Tab to Submit button
NVDA: "Save, button"

User action: Press Enter (submit blank form)
NVDA: [SILENCE — no announcement]
NVDA: "Edit task, heading level 1"
NVDA: "Title, edit, blank"

^ ERROR: Error message "Title is required" is visible on screen but NOT announced.
User has no way to know validation failed without sighted assistance.

---

WCAG Violation: 4.1.3 Status Messages (AA)
```

**File**: `screen-reader-transcripts/after-nvda-validation.txt`

```
NVDA Screen Reader Transcript — After Fix
==========================================

Test: Submit blank task edit form (T2)
SR: NVDA 2024.1
Browser: Chrome 120
Date: 2025-10-15 (Week 10 post-fix)

---

User action: Press Tab to title input
NVDA: "Title, edit, blank. Max 100 characters"

User action: Press Tab to Submit button
NVDA: "Save, button"

User action: Press Enter (submit blank form)
NVDA: "Alert. Title is required."  ← ✅ Error announced via role="alert"
NVDA: "There is a problem, heading level 2"
NVDA: "List with 1 item. Link. Title is required."

User action: Press Tab to title input (focus moves to error summary link)
NVDA: "Title, edit, blank. Max 100 characters. Title is required."  ← ✅ Error read via aria-describedby

^ SUCCESS: Error announced immediately via role="alert", and read again when field focused.
User can detect and correct error independently.

---

WCAG Compliance: ✅ 4.1.3 Status Messages (AA)
```

### How to Capture

1. **Enable SR logging** (NVDA: NVDA Menu → Tools → Speech Viewer)
2. **Perform task** following Week 9 protocol
3. **Copy Speech Viewer output** to .txt file
4. **Annotate** with user actions and observations

---

## 3. Code Snippets

### Purpose

Show specific code changes that fixed accessibility issues (supplement to `04-key-diffs.md`).

### Guidelines

- Keep snippets focused (5-20 lines)
- Highlight key changes with comments
- Include file paths and line numbers

**Example** (`code-snippets/template-diffs.md`):

```markdown
## Template Changes: tasks/edit.peb

### Before (Week 9)

File: `templates/tasks/edit.peb` lines 15-20

\`\`\`html
{% if error %}
  <span class="error">{{ error }}</span>  ← ❌ No role="alert"
{% endif %}

<input type="text" id="title" name="title">  ← ❌ No aria-describedby
\`\`\`

### After (Week 10)

File: `templates/tasks/edit.peb` lines 15-25

\`\`\`html
{% if errors.title %}
  <span id="title-error" class="error" role="alert">  ← ✅ Added role="alert"
    {{ errors.title }}
  </span>
{% endif %}

<input type="text" id="title" name="title"
       aria-describedby="title-hint title-error">  ← ✅ Added aria-describedby
\`\`\`

**Impact**: Screen readers now announce errors automatically and read them when field is focused.
```

---

## 4. Annotations

### Purpose

Provide alt text and context for all screenshots (accessibility + transparency).

**File**: `screenshots/annotations.md`

**Template**:

```markdown
## [filename].png

**Alt text**: "[Brief description for screen readers]"

**Context**:
- When captured: [Week 9 baseline / Week 10 post-fix]
- Task: [T2 edit task]
- Variant: [Standard / No-JS / Keyboard-only]

**What it shows**: [Detailed description of issue or fix]

**Relevance**: [Why this screenshot matters for evidence chain]

**Related files**: [Links to findings, brief, diffs]
```

---

## 5. Privacy Guidelines

**Before including any file in this directory**:

1. ✅ **Check for PII**: Names, emails, student IDs, session tokens
2. ✅ **Scrub screenshots**: Crop/blur personal info
3. ✅ **Anonymize transcripts**: Use participant codes (P1, P2) not names
4. ✅ **Verify session IDs**: Random codes (P1_7a9f) not traceable

**UK GDPR compliance** (Data Protection Act 2018):
- Collect only necessary data (screenshots, SR output)
- Store securely (local files, no cloud without encryption)
- Delete after use (post-submission)
- Respect withdrawal rights (remove participant data if requested)

---

## 6. Evidence Chains

**All claims in `01-redesign-brief.md` and `03-before-after-summary.md` must link to evidence here**.

**Example chain**:

```
Claim: "SR users could not detect validation errors in Week 9"
    ↓
Evidence 1: 05-evidence/screen-reader-transcripts/before-nvda-validation.txt (no announcement)
Evidence 2: 05-evidence/screenshots/before-validation-error.png (visual error without role="alert")
Evidence 3: Week 9 05-findings.md section A1 (quantitative data: 33% error rate)
    ↓
Fix: Added role="alert" to error messages (see 04-key-diffs.md)
    ↓
Verification 1: 05-evidence/screen-reader-transcripts/after-nvda-validation.txt (error announced)
Verification 2: 05-evidence/screenshots/after-validation-error.png (error with role="alert")
Verification 3: 06-metrics/post/postchange.csv (0% error rate, 100% completion)
```

**No evidence = no claim**.

---

## 7. Submission Checklist

Before submitting Task 2 to Gradescope:

- [ ] All screenshots have annotations in `screenshots/annotations.md`
- [ ] No PII in any file (screenshots, transcripts, code snippets)
- [ ] SR transcripts clearly labeled (before/after, SR type, date)
- [ ] Code snippets match changes described in `04-key-diffs.md`
- [ ] Every claim in `01-redesign-brief.md` links to evidence here
- [ ] File names descriptive (not `IMG_1234.jpg`)
- [ ] All evidence traceable to Week 9 baseline or Week 10 fixes

---

## 8. Recommended Tools

**Screenshot capture**:
- Browser DevTools: Ctrl+Shift+P → "Capture screenshot"
- Snipping Tool (Windows) / Screenshot (macOS: Cmd+Shift+4)
- Firefox: Right-click → "Take a Screenshot"

**SR transcript capture**:
- NVDA: NVDA Menu → Tools → Speech Viewer
- JAWS: Settings → Utilities → Speech History
- VoiceOver (macOS): Can use QuickTime screen recording with audio

**Contrast analysis**:
- [Colour Contrast Analyser](https://www.tpgi.com/color-contrast-checker/) (desktop app)
- [WebAIM Contrast Checker](https://webaim.org/resources/contrastchecker/) (web)
- Chrome DevTools: Inspect element → Styles → Color picker shows contrast ratio

**Code diffs**:
- `git diff` (if using version control)
- Side-by-side text editor (VS Code, IntelliJ IDEA)
- Online diff tools: https://www.diffchecker.com/

---

**Author**: [Your name]
**Last updated**: [YYYY-MM-DD]
**Related files**: `01-redesign-brief.md`, `03-before-after-summary.md`, `04-key-diffs.md`
