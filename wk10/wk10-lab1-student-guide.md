# Week 10 • Lab 1 — Student Guide: Analyse Metrics & Prioritise Redesign

![COMP2850](https://img.shields.io/badge/COMP2850-HCI-blue)
![Week 10](https://img.shields.io/badge/Week-10-orange)
![Lab 1](https://img.shields.io/badge/Lab-1-green)
![Guide](https://img.shields.io/badge/Type-Student_Guide-purple)

> **Purpose**: Week 10 Lab 1 is about analysing your Week 9 pilot data, prioritising issues for redesign, and planning your Week 10 Lab 2 fixes.

---

## Deliverables

- ✅ `wk10/analysis/quantitative-summary.md` - Statistical analysis
- ✅ `wk10/analysis/qualitative-themes.md` - Thematic coding
- ✅ `wk10/redesign/priorities.md` - Prioritised redesign plan
- ✅ Updated `backlog/backlog.csv` with Week 9 findings

---

## Part 1: Quantitative Analysis (30 minutes)

**Create** `wk10/analysis/quantitative-summary.md`:

```markdown
# Quantitative Analysis — Week 10

## Task Success Rates
| Task | HTMX Success | No-JS Success | Overall |
|------|--------------|---------------|---------|
| T1: Filter & Complete | 100% (3/3) | 100% (2/2) | 100% |
| T2: Add Task | 100% (3/3) | 100% (2/2) | 100% |
| T3: Edit Inline | 100% (3/3) | 50% (1/2) | 80% |
| T4: Delete | 100% (3/3) | 100% (2/2) | 100% |

**Interpretation**: T3 (edit inline) has 50% failure in no-JS mode. Priority issue.

## Mean Time-on-Task
[Add table]

## Error Rates
[Add table]

## Confidence Ratings
[Add table]

## Statistical Tests (if applicable)
- Mann-Whitney U test: HTMX vs No-JS times for T2
- Result: U = 2.5, p = 0.18 (not significant with n=5)
```

**Checklist**:
- [ ] Success rates calculated per task and mode
- [ ] Mean times calculated
- [ ] Error rates tallied
- [ ] Confidence averages computed

---

## Part 2: Qualitative Themes (30 minutes)

**Create** `wk10/analysis/qualitative-themes.md`:

```markdown
# Qualitative Themes — Week 10

## Theme 1: Confirmation Feedback Critical
**Frequency**: 4/5 participants
**Severity**: High
**Evidence**: [Quotes from pilot notes]
**Recommendation**: Add explicit success message in no-JS path

## Theme 2: Cancel Button Ambiguous
**Frequency**: 3/5
**Severity**: Medium
**Evidence**: [Quotes]
**Recommendation**: Change label to "Cancel (discard changes)"

## Theme 3: [Add more themes]
```

**Checklist**:
- [ ] 3-5 themes identified
- [ ] Frequency and severity assigned
- [ ] Evidence (quotes) linked
- [ ] Recommendations listed

---

## Part 3: Prioritise Redesign (30 minutes)

**Create** `wk10/redesign/priorities.md`:

```markdown
# Redesign Priorities — Week 10 Lab 2

## Priority 1: No Confirmation in No-JS (MUST FIX)
**Issue**: P3, P4 low confidence, had to verify task added
**Evidence**: `wk09/data/pilot-notes.md` L45-48, L89-92
**WCAG**: 4.1.3 Status Messages (AA)
**Fix**: Add success message to PRG redirect (query param or session flash)
**Effort**: 1-2 hours

## Priority 2: Edit Inline Fails in No-JS (MUST FIX)
**Issue**: P4 couldn't complete T3 in no-JS mode
**Evidence**: 50% failure rate
**WCAG**: 2.1.1 Keyboard (A) - parity required
**Fix**: Debug no-JS edit flow, ensure PRG works
**Effort**: 1 hour

## Priority 3: Cancel Button Label (SHOULD FIX)
**Issue**: 3/5 confused
**Evidence**: Hesitation, quotes
**WCAG**: 2.4.6 Headings and Labels (AA)
**Fix**: Change to "Cancel (discard changes)"
**Effort**: 10 minutes

## Deferred (Post-Assessment or Semester 2)
- Filter persistence across sessions
- Progress indicator
```

**Checklist**:
- [ ] MUST FIX items identified (2-3 max for Lab 2 time)
- [ ] SHOULD FIX items listed
- [ ] Effort estimates provided
- [ ] Deferred items noted

---

## Commit & Continue

```bash
git add wk10/
git commit -m "feat(wk10-lab1): analysis and redesign prioritisation

- Analysed quantitative data (success, times, errors, confidence)
- Identified 5 qualitative themes from pilot observations
- Prioritised 3 MUST FIX issues for Week 10 Lab 2
- Updated backlog with Week 9 findings

Ready for Week 10 Lab 2 redesign implementation."
```

**Next**: Week 10 Lab 2 - Implement fixes, re-verify, package assessment.
