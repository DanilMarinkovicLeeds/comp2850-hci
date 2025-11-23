# Screenshot Annotations

**Purpose**: Provide alt text and context for all screenshots in this directory (accessibility + transparency).

---

## Template

For each screenshot, include:

1. **Filename**: `[filename].png`
2. **Alt text**: Brief description (for screen readers)
3. **Context**: When captured (pilot, task, timestamp)
4. **Issue**: What problem does this show?
5. **WCAG reference**: If applicable
6. **Backlog item**: Link to `backlog/backlog.csv` entry

---

## Example: t2-validation-error.png

**Alt text**: "Task edit form showing validation error below title input field. Error text reads 'Title is required' in red, but lacks role=alert attribute visible in browser inspector."

**Context**:
- Pilot 2 (P2_4d8e), Task T2 (Edit task)
- Timestamp: 2025-10-15 14:21:12
- Variant: Keyboard-only, JS-on, screen reader testing with NVDA

**Issue**:
Validation error message is visually present but **not announced by screen reader**. Participant (P2) submitted blank form, waited, then said: "I didn't hear any error message. Did it work?"

Browser inspector shows `<span class="error">Title is required</span>` without `role="alert"` or `aria-live` attribute.

**WCAG reference**:
- **4.1.3 Status Messages (AA)**: Status changes must be programmatically determinable and announced to assistive tech
- **3.3.1 Error Identification (A)**: Errors must be identified and described in text (✅ met visually, ❌ not for SR)

**Backlog item**: wk9-01 (see `backlog/backlog.csv`)

**Proposed fix**: Add `role="alert"` to error message span, associate with input via `aria-describedby`

---

## [Add your screenshots here]

### [filename].png

**Alt text**: "[Description]"

**Context**:
- Pilot [X] ([session_id]), Task [code] ([task name])
- Timestamp: [YYYY-MM-DD HH:MM:SS]
- Variant: [Standard / Keyboard-only / No-JS / Screen reader]

**Issue**: [What problem does this show?]

**WCAG reference**: [X.X.X] — [Criterion name]

**Backlog item**: [wk9-XX]

**Proposed fix**: [Specific mitigation]

---

## Accessibility Note

**Why annotate screenshots?**

1. **Screen reader users**: Cannot see images—need alt text
2. **Future reference**: Screenshots without context lose meaning over time
3. **Transparency**: Evidence must be traceable and verifiable
4. **Professional practice**: Industry standards require documentation

**Format**: Keep alt text concise (1-2 sentences), use context section for detail.

---

**Author**: [Your name]
**Last updated**: [YYYY-MM-DD]
