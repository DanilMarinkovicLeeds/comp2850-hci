# Evidence Artefacts

**Purpose**: Collect and organize all evidence referenced in `05-findings.md` (screenshots, pilot notes, consent logs).

---

## Directory Structure

```
06-evidence/
├── README.md                    ← You are here
├── screenshots/                 ← Visual evidence (annotated, no PII)
│   ├── t1-filter-results.png
│   ├── t2-validation-error.png
│   ├── t2-validation-error-nojs.png
│   ├── t3-add-success.png
│   ├── t4-delete-confirmation.png
│   └── annotations.md           ← Alt text and descriptions for screenshots
├── pilot-notes/                 ← Qualitative observations per participant
│   ├── P1-notes.md
│   ├── P2-notes.md
│   ├── P3-notes.md
│   ├── P4-notes.md
│   └── P5-notes.md
└── consent-log.md               ← Participant consent tracking (optional, may be in research/)
```

---

## 1. Screenshots

### Purpose
Visual evidence of:
- Accessibility issues (missing focus indicators, error messages without `role="alert"`)
- Usability issues (confusing UI, unclear feedback)
- Success states (positive observations to keep in redesign)

### Guidelines

**Required for each screenshot**:
1. **Filename**: Descriptive (e.g., `t2-validation-error-nojs.png` not `screenshot1.png`)
2. **Annotation**: Entry in `annotations.md` with alt text + context
3. **Privacy**: **No PII** (crop out names, emails, student IDs, session tokens)
4. **Context**: Include enough UI to understand the issue (not tiny crops)

**How to capture**:
- Use browser DevTools screenshot feature (Ctrl+Shift+P → "Screenshot")
- Or Snipping Tool / macOS Screenshot (Cmd+Shift+4)
- Save as PNG (better quality than JPG for UI)

**Example annotations.md entry**:
```markdown
## t2-validation-error.png

**Alt text**: "Task edit form showing validation error below title input field. Error text reads 'Title is required' but lacks role=alert attribute."

**Context**: Pilot 2 (P2_4d8e), Task T2 (Edit), timestamp 14:21:12

**Issue**: Error message visible but not announced by screen reader (NVDA). Participant quote: "I didn't hear any error message."

**WCAG**: 4.1.3 Status Messages (AA) — status changes must be announced to assistive tech

**Backlog item**: wk9-01
```

---

## 2. Pilot Notes

### Purpose
Capture qualitative observations during pilots:
- Direct quotes from participants (think-aloud)
- Facilitator observations (hesitation, confusion, errors)
- Timestamps for key events
- Accessibility barriers observed

### Template

**File**: `pilot-notes/P[X]-notes.md`

```markdown
# Pilot [X] Notes

**Session ID**: P[X]_[code]
**Date**: YYYY-MM-DD
**Time**: HH:MM–HH:MM
**Variant**: [Standard HTMX / Keyboard-only / No-JS / Screen reader]
**Facilitator**: [Name]
**Observer**: [Name]
**Consent confirmed**: [Yes/No]

---

## Pre-Session

- [Any setup issues? Participant questions?]
- [Technical check: JS mode, screen reader, keyboard?]

---

## Task 1 ([Code]): [Task name]

**Start time**: HH:MM:SS
**End time**: HH:MM:SS
**Duration**: [X]s
**Outcome**: [Success / Fail / Partial]

**Observations**:
- [HH:MM] [Event description]
- [HH:MM] Participant quote: "[Exact words]"
- [HH:MM] [Facilitator observation]

**Issues**:
- [Any errors, confusion, accessibility barriers?]

**Success criteria met**: [Yes/No/Partial] — [Which criteria?]

---

## Task 2 ([Code]): [Task name]

[Repeat structure]

---

## Task 3 ([Code]): [Task name]

[Repeat structure]

---

## Task 4 ([Code]): [Task name]

[Repeat structure]

---

## Post-Session Debrief (5 min)

**Subjective ratings** (1–5 scale):

- Overall confidence completing tasks: [X] / 5
- Ease of use: [X] / 5
- Would recommend to peers: [X] / 5

**Open questions**:

1. "What was most confusing or frustrating?"
   - Response: "[Quote]"

2. "What worked well?"
   - Response: "[Quote]"

3. "Any accessibility barriers?" (if keyboard/SR pilot)
   - Response: "[Quote]"

**Participant suggestions**:
- [Any ideas for improvement?]

---

## Facilitator Reflection

- [What surprised you?]
- [Any protocol issues? Leading questions?]
- [Data quality concerns?]

---

## Evidence Links

**Quantitative data**: `04-results.csv` lines [X-Y] (session_id=[P[X]_code])
**Screenshots**: [List relevant files]
**Backlog items**: [wk9-XX, wk9-YY] (see `backlog/backlog.csv`)
```

---

## 3. Consent Log

### Purpose
Track participant consent and session metadata (may be stored in `research/consent-log.md` instead).

### Template

```markdown
# Consent Log

**Module**: COMP2850 HCI
**Activity**: Peer Pilots (Week 9 Lab 2)
**Date**: YYYY-MM-DD

---

## Consent Process

All participants provided **blanket consent** via module-wide form (Week 6 Lab 2). No additional consent required for peer-only activities.

**Ethical considerations**:
- Anonymous session IDs only (no PII collected)
- Right to withdraw at any time
- Data used for coursework only (not published)
- Screenshots scrubbed of personal info

---

## Participant Log

| Participant Code | Session ID | Date | Time | Variant | Consent | Notes |
|-----------------|-----------|------|------|---------|---------|-------|
| P1 | P1_[code] | YYYY-MM-DD | HH:MM | Standard | ✅ | [Any issues?] |
| P2 | P2_[code] | YYYY-MM-DD | HH:MM | Keyboard-only | ✅ | [Any issues?] |
| P3 | P3_[code] | YYYY-MM-DD | HH:MM | No-JS | ✅ | [Any issues?] |
| P4 | P4_[code] | YYYY-MM-DD | HH:MM | Standard | ✅ | [Any issues?] |
| P5 | P5_[code] | YYYY-MM-DD | HH:MM | Screen reader | ✅ | [Any issues?] |

**Total participants**: [n]

---

## Data Retention

- **Raw data** (`metrics.csv`, pilot notes): Stored locally, deleted after module completion
- **Evidence pack** (Task 1 submission): Submitted to Gradescope, retained per university policy
- **PII**: None collected (session IDs are anonymous)
```

---

## Privacy Guidelines

**Before including any file in this directory**:

1. ✅ **Check for PII**: Names, emails, student IDs, session tokens
2. ✅ **Scrub screenshots**: Crop/blur personal info
3. ✅ **Anonymize quotes**: Use participant codes (P1, P2) not names
4. ✅ **Verify session IDs**: Random codes (P1_7a9f) not traceable

**UK GDPR compliance** (Data Protection Act 2018):
- Collect only necessary data (metrics, observations)
- Store securely (local files, not cloud without encryption)
- Delete after use (post-submission)
- Respect withdrawal rights (remove participant data if requested)

---

## Evidence Chains

**All findings in `05-findings.md` must link to evidence in this directory**.

**Example chain**:
```
Finding: Validation errors not announced by SR
    ↓
Evidence 1: 06-evidence/pilot-notes/P2-notes.md line 12 (quote)
Evidence 2: 06-evidence/screenshots/t2-validation-error.png (visual)
Evidence 3: 04-results.csv line 127 (quantitative)
    ↓
Backlog: wk9-01 (see backlog/backlog.csv)
```

**No evidence = no claim**. If you didn't observe it or measure it, don't claim it.

---

## Submission Checklist

Before submitting Task 1 to Gradescope:

- [ ] All screenshots have annotations in `annotations.md`
- [ ] All pilot notes follow template structure
- [ ] No PII in any file (screenshots, notes, logs)
- [ ] Consent confirmed for all participants
- [ ] Every finding in `05-findings.md` links to evidence here
- [ ] File names descriptive (not `IMG_1234.jpg`)

---

**Author**: [Your name]
**Last updated**: [YYYY-MM-DD]
**Related files**: `05-findings.md`, `04-results.csv`, `backlog/backlog.csv`
