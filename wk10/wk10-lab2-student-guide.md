# Week 10 • Lab 2 — Student Guide: Redesign, Re-Verify & Package assessment

![COMP2850](https://img.shields.io/badge/COMP2850-HCI-blue)
![Week 10](https://img.shields.io/badge/Week-10-orange)
![Lab 2](https://img.shields.io/badge/Lab-2-green)
![Guide](https://img.shields.io/badge/Type-Student_Guide-purple)

> **Purpose**: Week 10 Lab 2 is about implementing your priority redesigns from Lab 1, re-verifying with accessibility testing, and assembling your assessment submission package.

---

## Deliverables

- ✅ Priority fixes implemented (2-3 from wk10-lab1 priorities)
- ✅ Re-verification evidence (`wk10/evidence/`)
- ✅ assessment draft package (`wk10/assessment/`)
- ✅ Updated backlog with "Fixed" status

---

## Part 1: Implement Priority Fixes (60 minutes)

From `wk10/redesign/priorities.md`, implement your MUST FIX items.

### Example Fix 1: Add No-JS Confirmation

**Before** (POST /tasks):
```kotlin
// No-JS path - no confirmation shown
call.respondRedirect("/tasks")
```

**After**:
```kotlin
// No-JS path - add success message via query param
call.respondRedirect("/tasks?msg=task_added")
```

**In template** (`tasks/index.peb`):
```pebble
{% if msg == "task_added" %}
<div role="alert" class="success">Task added successfully.</div>
{% endif %}
```

### Example Fix 2: Clarify Cancel Button

**Before**:
```pebble
<a href="/tasks">Cancel</a>
```

**After**:
```pebble
<a href="/tasks">Cancel (discard changes)</a>
```

**Checklist per fix**:
- [ ] Code changed
- [ ] Tested in browser (HTMX + no-JS)
- [ ] Screenshot captured (before/after)

---

## Part 2: Re-Verify Accessibility (30 minutes)

After implementing fixes, re-run audits:

### axe DevTools Re-Scan
1. Open `/tasks` page
2. Run axe scan
3. Compare to Week 7 report
4. Screenshot results

### Manual WCAG Re-Check
- [ ] Keyboard navigation still works
- [ ] Screen reader announces new messages
- [ ] No-JS parity maintained

**Document** in `wk10/evidence/reverification.md`:

```markdown
# Re-Verification — Week 10

## axe DevTools
**Before**: 5 violations
**After**: 2 violations (fixed: labels, contrast, confirmation)
**Remaining**: Link purpose (deferred), zoom reflow (minor)

## Manual Testing
- ✅ Keyboard: All fixes reachable via Tab
- ✅ Screen reader: New success messages announced
- ✅ No-JS: Confirmation now shown

## Regression Check
- ✅ Previous fixes (Week 7) still work
- ✅ No new issues introduced
```

**Checklist**:
- [ ] axe re-scan complete
- [ ] Manual testing done
- [ ] Regression checked
- [ ] Evidence documented

---

## Part 3: Assemble Assessment Package (40 minutes)

**Create** `wk10/assessment/outline.md`:

```markdown
# assessment Draft Outline

## Section 1: Redesign Rationale (20%)

### 1.1 Priority Selection
- [ ] Explain how priorities chosen (Week 9 data + severity + WCAG)
- [ ] Link to `wk10/redesign/priorities.md`

### 1.2 Evidence from Week 9
- [ ] Quantitative data showing issues (success rates, confidence)
- [ ] Qualitative quotes from participants
- [ ] WCAG criteria failed

---

## Section 2: Implementation (30%)

### 2.1 Fix 1: No-JS Confirmation
- [ ] Before code snippet
- [ ] After code snippet
- [ ] Screenshot comparison
- [ ] How it addresses WCAG 4.1.3

### 2.2 Fix 2: [Your fix]
[Repeat structure]

### 2.3 Trade-Offs
- [ ] What was sacrificed (e.g., URL gets query params, slightly longer)
- [ ] Why acceptable (better UX, WCAG compliance)

---

## Section 3: Verification (30%)

### 3.1 Re-Testing
- [ ] axe before/after comparison
- [ ] Manual WCAG checklist results
- [ ] Regression testing evidence

### 3.2 Accessibility Impact
- [ ] Who benefits (people using keyboard, no-JS, screen readers)
- [ ] Inclusion metrics improved

---

## Section 4: Reflection (20%)

### 4.1 Process Critique
- [ ] What worked well in redesign?
- [ ] Challenges faced?
- [ ] Time management

### 4.2 Future Work
- [ ] Deferred items from backlog
- [ ] If had more time, what next?

---

## Files to Include

- [ ] `wk10/redesign/priorities.md`
- [ ] Code snippets (before/after)
- [ ] `wk10/evidence/reverification.md`
- [ ] Screenshots from `wk10/evidence/`
- [ ] Updated `backlog/backlog.csv`

**Word count target**: ~2000-2500 words
```

**Checklist**:
- [ ] Assessment outline created
- [ ] All sections listed
- [ ] Files identified

---

## Commit & Continue

```bash
git add wk10/ src/ templates/
git commit -m "feat(wk10-lab2): priority fixes implemented and verified

- Fixed no-JS confirmation (added success message)
- Fixed edit inline no-JS failure (debugged PRG flow)
- Clarified Cancel button label
- Re-verified with axe and manual WCAG testing
- Created assessment draft outline

Ready for Week 11 studio crit and final submission."
```

**Next**: Week 11 Lab 1 - Evidence-led studio crit.
