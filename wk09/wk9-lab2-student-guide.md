# Week 9 • Lab 2 — Student Guide: Run Pilots & Draft Assessment

![COMP2850](https://img.shields.io/badge/COMP2850-HCI-blue)
![Week 9](https://img.shields.io/badge/Week-9-orange)
![Lab 2](https://img.shields.io/badge/Lab-2-green)
![Guide](https://img.shields.io/badge/Type-Student_Guide-purple)

> **Purpose**: Week 9 Lab 2 is where you execute your evaluation plan - run pilots with 3-5 participants, collect data, analyse findings, and draft your assessment evidence pack.

---

## Deliverables

By the end of this lab:
- ✅ Pilot data from 3-5 participants (`wk09/data/pilot-notes.md`)
- ✅ Analysed findings (`wk09/analysis/findings.md`)
- ✅ Evidence chain documentation (`wk09/analysis/evidence-chains.md`)
- ✅ Draft assessment pack outline (`wk09/assessment/`)
- ✅ Updated backlog with pilot findings

---

## Part 1: Run Pilots (60 minutes)

### Setup Checklist

Before first participant:
- [ ] Server running: `./gradlew run`
- [ ] Protocol document open (`wk09/research/protocol.md`)
- [ ] Stopwatch/timer ready
- [ ] Notebook + pen for observations
- [ ] Tasks scenarios printed or on screen (`wk09/research/tasks.md`)

### Per-Participant Procedure (20 minutes each)

1. **Consent** (2 min): Read consent script, confirm verbal agreement, assign pseudonym (P1, P2...)
2. **Setup mode** (1 min):
   - Participant 1, 3, 5 → HTMX (JS enabled)
   - Participant 2, 4 → No-JS (disable in DevTools)
3. **Orientation** (2 min): "Testing interface, not you. Think aloud. Can stop anytime."
4. **Execute tasks** (12 min): For each task T1-T4:
   - Read scenario aloud
   - Start timer
   - Observe (note clicks, confusion, comments)
   - Stop timer when complete OR give up
   - Ask: "1-5, how confident you completed correctly?"
5. **Debrief** (3 min): "Most confusing part? What worked well?"

### Data Recording Template

**Create** `wk09/data/pilot-notes.md`:

<details>
<summary>Click to expand: Pilot Notes Template</summary>

```markdown
# Pilot Data — Week 9

## Participant P1
**Mode**: HTMX
**Date**: [YYYY-MM-DD HH:MM]
**Consent**: ✅ Verbal confirmed
**Duration**: 18 minutes

---

### Task 1: Filter and Complete
**Start**: 10:30:00 → **End**: 10:30:45 = **45 seconds**
**Success**: 1 (completed)
**Errors**: 0
**Confidence**: 4/5
**Observations**:
- Hesitated finding filter box ("Where's the search?")
- Clicked checkbox, immediate visual feedback good
- Said "Oh nice, it just works!" (positive)

---

### Task 2: Add New Task
**Start**: 10:31:00 → **End**: 10:31:25 = **25 seconds**
**Success**: 1
**Errors**: 1 (tried to submit blank, saw error)
**Confidence**: 5/5
**Observations**:
- Typed title, pressed Enter (didn't click button - good!)
- Blank submit → error message showed (red, "Title required")
- Corrected, submitted successfully
- Status message: "Task added" - SR announced (tested with NVDA)

---

[Repeat for T3, T4]

---

### Debrief Notes
**Most confusing**: "Cancel button in edit mode - wasn't sure if it would save or not"
**Most helpful**: "Instant feedback when I add tasks, no waiting"
**Accessibility**: Used keyboard only (requested). All features reachable. Focus visible.

---

## Participant P2
**Mode**: No-JS
[Repeat structure]

---

[Repeat for P3-P5]
```

</details>

**Checklist**:
- [ ] 3-5 pilots completed
- [ ] All times, success, errors, confidence recorded
- [ ] Think-aloud quotes captured
- [ ] Mode (HTMX/no-JS) noted per participant

---

## Part 2: Analyse Findings (40 minutes)

### Aggregate Data

**Create** `wk09/analysis/findings.md`:

<details>
<summary>Click to expand: Findings Analysis Template</summary>

```markdown
# Pilot Findings Analysis — Week 9

**Participants**: 5 (3 HTMX, 2 No-JS)
**Date range**: [YYYY-MM-DD to YYYY-MM-DD]

---

## Quantitative Summary

### Task 1: Filter and Complete
| Metric | HTMX (n=3) | No-JS (n=2) | Overall |
|--------|------------|-------------|---------|
| Mean time (s) | 42 | 58 | 48 |
| Success rate | 100% | 100% | 100% |
| Mean errors | 0.3 | 0 | 0.2 |
| Mean confidence | 4.3/5 | 4.0/5 | 4.2/5 |

**Interpretation**: Task 1 successful for all. HTMX slightly faster (no page reload). Low error rate. High confidence.

---

### Task 2: Add New Task
| Metric | HTMX | No-JS | Overall |
|--------|------|-------|---------|
| Mean time (s) | 28 | 35 | 30.6 |
| Success rate | 100% | 100% | 100% |
| Mean errors | 0.7 | 1.0 | 0.8 |
| Mean confidence | 4.7/5 | 3.5/5 | 4.2/5 |

**Interpretation**: High success but errors common (validation). HTMX confidence higher (instant feedback). No-JS participants less sure (PRG redirect, no confirmation message).

---

[Repeat for T3, T4]

---

## Qualitative Themes

### Theme 1: Confirmation Feedback Critical
**Evidence**: 4/5 participants mentioned needing "confirmation it worked"
**Quotes**:
- P1 (HTMX): "I like seeing 'Task added' immediately"
- P3 (No-JS): "I had to scroll down to check it was there - not obvious"

**Design implication**: No-JS path needs explicit success message (PRG currently shows none).

---

### Theme 2: Edit Cancel Button Confusing
**Evidence**: 3/5 participants hesitated on Cancel button
**Quotes**:
- P2: "Will Cancel save my changes or lose them?"
- P4: "I clicked it to test - wasn't sure"

**Design implication**: Button label needs clarification ("Cancel (discard changes)")

---

### Theme 3: Keyboard Access Excellent
**Evidence**: 2 participants tested keyboard-only (requested). Both succeeded all tasks.
**Quotes**:
- P5 (keyboard-only): "Tab order makes sense, focus always visible"

**Design implication**: Keep current keyboard implementation.

---

## Accessibility Observations

### Screen Reader (NVDA)
- ✅ Labels announced correctly ("Task title, edit text")
- ✅ Status messages announced ("Task added successfully")
- ❌ Error messages not announced in no-JS path (redirect loses `role="alert"`)

### Keyboard Navigation
- ✅ All features reachable
- ✅ Focus visible
- ⚠️ Tab order logical but Edit→Save→Cancel could be clearer

---

## Prioritised Issues

Based on frequency + severity:

1. **High**: No confirmation message in no-JS path (affects 2/2 no-JS participants, low confidence)
2. **Medium**: Cancel button ambiguous (3/5 confused, but completed anyway)
3. **Low**: Edit button focus order (minor hesitation, all succeeded)
```

</details>

**Checklist**:
- [ ] Quantitative summary per task (times, success, errors, confidence)
- [ ] Qualitative themes identified (3-5 themes)
- [ ] Accessibility observations noted
- [ ] Prioritised issues list

---

## Part 3: Evidence Chains (20 minutes)

Link raw data → finding → backlog item → WCAG (if applicable).

**Create** `wk09/analysis/evidence-chains.md`:

<details>
<summary>Click to expand: Evidence Chains Template</summary>

```markdown
# Evidence Chains — Week 9

## Chain 1: No Confirmation in No-JS Path

**Raw data**: P3 (No-JS) confidence = 2/5, said "Had to scroll to check". P4 (No-JS) confidence = 3/5, refreshed page to verify.

**Finding**: No-JS participants lack confidence because PRG redirect shows no explicit success message.

**Backlog item**: ID 15, "No confirmation after form submission (no-JS)", Severity: High, Inclusion risk: Cognitive, Low digital literacy

**WCAG**: 3.3.3 Error Suggestion (AA) - Not error, but related (success feedback equally important)

**Evidence file**: `wk09/data/pilot-notes.md` lines 45-48 (P3), lines 89-92 (P4)

**Screenshot**: `wk09/evidence/nojs-no-confirmation.png`

---

## Chain 2: Cancel Button Ambiguous Label

**Raw data**: P2, P4, P5 all hesitated on Cancel (pilot notes timestamps show 3-5 second pause). P2 quote: "Will it save or lose changes?"

**Finding**: "Cancel" label doesn't clarify whether changes are discarded.

**Backlog item**: ID 16, "Edit Cancel button label unclear", Severity: Medium, Inclusion risk: Cognitive

**WCAG**: 2.4.6 Headings and Labels (AA) - Labels should be descriptive

**Evidence file**: `wk09/data/pilot-notes.md` lines 22-23 (P2), lines 67-68 (P4)

**Screenshot**: `wk09/evidence/edit-cancel-ambiguous.png`

---

[Repeat for 3-5 key findings]
```

</details>

**Checklist**:
- [ ] 3-5 evidence chains documented
- [ ] Each chain links data → finding → backlog → WCAG
- [ ] Screenshots/quotes referenced

---

## Part 4: Draft Assessment Pack (30 minutes)

Start assembling your assessment submission (due end Week 10).

**Create** `wk09/assessment/outline.md`:

<details>
<summary>Click to expand: Task 1 Draft Outline</summary>

```markdown
# Assessment Draft Outline

## Section 1: Evaluation Plan (15%)

### 1.1 Test Tasks (5%)
- [ ] Copy from `wk09/research/tasks.md`
- [ ] Ensure 3-4 tasks with scenarios, success criteria
- [ ] Highlight inclusion focus per task

### 1.2 Metrics (5%)
- [ ] Copy from `wk09/research/measures.md`
- [ ] Define quantitative (time, success, errors) + qualitative (think-aloud)
- [ ] Justify why these metrics

### 1.3 Protocol (5%)
- [ ] Copy from `wk09/research/protocol.md`
- [ ] Include consent process, procedure, ethics checklist
- [ ] Note UK GDPR compliance

---

## Section 2: Findings (40%)

### 2.1 Quantitative Results (15%)
- [ ] Summary tables per task (times, success, errors, confidence)
- [ ] Compare HTMX vs No-JS
- [ ] Identify outliers / problematic tasks

### 2.2 Qualitative Analysis (15%)
- [ ] 3-5 themes from think-aloud
- [ ] Direct quotes as evidence
- [ ] Link themes to design implications

### 2.3 Accessibility Observations (10%)
- [ ] Keyboard navigation results
- [ ] Screen reader testing notes
- [ ] WCAG criteria met/failed

---

## Section 3: Evidence Chains (25%)

### 3.1 High-Priority Findings (15%)
- [ ] Evidence chain for each (data → finding → backlog → WCAG)
- [ ] Screenshots showing issues
- [ ] Participant quotes

### 3.2 Backlog Integration (10%)
- [ ] Show how findings update backlog
- [ ] Severity + inclusion risk scores
- [ ] Candidate fixes for Week 10

---

## Section 4: Reflection (20%)

### 4.1 Process Critique (10%)
- [ ] What went well in pilots?
- [ ] What would you change?
- [ ] Limitations (small sample, peer pilots)

### 4.2 Next Steps (10%)
- [ ] Prioritised redesign plan (Week 10)
- [ ] Which issues to fix first and why
- [ ] Trade-offs to document

---

## Files to Include

- [ ] `wk09/research/tasks.md`
- [ ] `wk09/research/measures.md`
- [ ] `wk09/research/protocol.md`
- [ ] `wk09/data/pilot-notes.md`
- [ ] `wk09/analysis/findings.md`
- [ ] `wk09/analysis/evidence-chains.md`
- [ ] Screenshots from `wk09/evidence/`
- [ ] Updated `backlog/backlog.csv`

**Total word count target**: ~2500-3000 words
**Due**: [Check Gradescope deadline]
```

</details>

**Checklist**:
- [ ] Assessment outline created
- [ ] All required sections listed
- [ ] Evidence files identified

---

## Commit & Continue

```bash
git add wk09/
git commit -m "feat(wk9-lab2): pilot execution, analysis, Task 1 draft

- Completed 5 pilots (3 HTMX, 2 No-JS)
- Collected quantitative data (times, success, errors, confidence)
- Analysed qualitative themes from think-aloud observations
- Documented 5 evidence chains linking data to backlog
- Created draft assessment outline with all required sections

Ready for Week 10 redesign implementation."
```

**Next**: Week 10 Lab 1 - Analyse metrics and prioritise redesign.
