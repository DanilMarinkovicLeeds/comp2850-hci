# Week 10 • Lab 1 — Analyse Metrics, Prioritise Fixes, Plan Inclusive Redesign

![COMP2850](https://img.shields.io/badge/COMP2850-HCI-blue)
![Week 10](https://img.shields.io/badge/Week-10-orange)
![Lab 1](https://img.shields.io/badge/Lab-1-green)
![Status](https://img.shields.io/badge/Status-Draft-yellow)



---

## Before Lab: Required Reading (15 mins)

📖 **Essential**:
- Review [Evaluation Metrics Quick Reference](../references/evaluation-metrics-quickref.md) (formulas for median, MAD, error rates)
- Review Week 9 Lab 2 findings (`wk09/assessment/05-findings.md`)
- [Nielsen: Prioritising Web Usability Issues](https://www.nngroup.com/articles/how-to-rate-the-severity-of-usability-problems/)

📖 **Contextual**:
- [GOV.UK: Using data to improve your service](https://www.gov.uk/service-manual/measuring-success/using-data-to-improve-your-service)
- [W3C: Prioritising Accessibility Issues](https://www.w3.org/WAI/test-evaluate/report/)

---

## Introduction: From Data to Decisions

Week 9 collected raw pilot data: logs, times, errors, quotes. **Today you turn that data into actionable insights**.

**Data analysis is NOT just number-crunching**. It's:
- **Interpretation**: What do the numbers *mean* for real people?
- **Prioritisation**: Which issues matter most for inclusion + usability?
- **Planning**: What specific changes will fix the highest-impact problems?

**Why this matters**:
- **assessment**: Requires before/after metrics + evidence of data-driven redesign
- **Week 10 Lab 2**: You'll implement Priority 1 fixes—planning today prevents thrashing tomorrow
- **Professional practice**: Product decisions require justification (stakeholders ask "why this fix?")

**Inclusion lens**: Numbers alone don't show **who** is excluded. Must combine quantitative (completion rates, times) with qualitative (SR didn't announce, keyboard trap) to understand barriers.

---

## Learning Focus

### Lab Objectives
By the end of this session, you will have:
- Calculated summary statistics (median, MAD, completion rate, error rate) per task
- Analysed pilot data (quantitative + qualitative)
- Interpreted metrics to identify inclusion impacts (SR users, keyboard-only, no-JS)
- Prioritised issues using (Impact + Inclusion) – Effort matrix
- Created redesign brief with evidence chains

### Learning Outcomes Addressed
This lab contributes to the following module Learning Outcomes ([full definitions](../references/learning-outcomes.md)):

- **LO6**: Apply iterative design — evidenced by data-driven prioritisation
- **LO8**: Design and execute evaluation — evidenced by metric analysis
- **LO9**: Apply inclusive design — evidenced by inclusion weighting in prioritisation

---

## Key Concepts

### Descriptive Statistics

> **Descriptive Statistics** [GLOSSARY]
>
> Summarize and describe main features of a dataset. **Not inferential** (we don't generalize beyond our 5 pilots).
>
> **Key measures for HCI**:
> - **Median**: Middle value when sorted (50th percentile)
> - **MAD**: Median Absolute Deviation (robust measure of spread)
> - **Range**: Min–max (shows extremes)
> - **Count**: Number of observations (sample size)
>
> **Why median, not mean?** Completion times are often skewed (outliers from distractions, confusion). Median represents "typical" experience.
>
> **Example**:
> Task times: [12s, 15s, 18s, 20s, 120s]
> - Mean = 37s ← skewed by 120s outlier
> - Median = 18s ← typical experience
>
> **HCI Connection**: Descriptive stats make evaluation findings **communicable**—stakeholders understand "typical task takes 18s" better than raw logs.
>
> 🔗 [Measuring UX: Median](https://measuringux.com/median/)

### Median Absolute Deviation (MAD)

> **MAD (Median Absolute Deviation)** [GLOSSARY]
>
> Robust measure of variability. Less sensitive to outliers than standard deviation.
>
> **Formula**:
> ```
> MAD = median(|x_i - median(x)|)
> ```
>
> **Steps**:
> 1. Find median of dataset
> 2. Calculate absolute deviations: `|each value - median|`
> 3. Find median of those deviations
>
> **Example**:
> Times: [12s, 15s, 18s, 20s, 25s]
> 1. Median = 18s
> 2. Deviations: |12-18|=6, |15-18|=3, |18-18|=0, |20-18|=2, |25-18|=7
> 3. MAD = median([6, 3, 0, 2, 7]) = 3s
>
> **Interpretation**: Low MAD (e.g., 2s) = consistent experiences. High MAD (e.g., 15s) = varied experiences—often signals accessibility barriers (some participants breeze through, others struggle).
>
> **HCI Connection**: MAD flags **inclusion issues**—if SR users take 40s and sighted users take 15s, MAD will be high.
>
> 🔗 [Wikipedia: Median Absolute Deviation](https://en.wikipedia.org/wiki/Median_absolute_deviation)

### Completion Rate

> **Completion Rate** [GLOSSARY]
>
> Proportion of task attempts that succeeded.
>
> **Formula**:
> ```
> Completion rate = n_success / n_total_attempts
> ```
>
> **Range**: 0 (no one succeeded) to 1.0 (everyone succeeded)
>
> **Example**:
> - 4 participants completed T2 successfully
> - 1 participant gave up (marked `fail` in logs)
> - Total: 5 attempts
> - Completion rate = 4/5 = 0.80 = 80%
>
> **Interpretation**:
> - **1.0**: Task is achievable by all participants (baseline expectation)
> - **0.8–0.99**: Minor issues—some participants struggled but most succeeded
> - **<0.8**: Serious usability/accessibility problem—1 in 5+ people can't complete
>
> **Split by js_mode**: If `js_mode=on` has 1.0 but `js_mode=off` has 0.5, you have **parity failure** (no-JS path broken).
>
> **HCI Connection**: Completion rate is **effectiveness** measure (ISO 9241-11). If people can't complete tasks, system is not usable.
>
> 🔗 [ISO 9241-11: Usability Definitions](https://www.iso.org/standard/63500.html)

### Error Rate

> **Error Rate** [GLOSSARY]
>
> Proportion of task attempts that triggered validation errors.
>
> **Formula**:
> ```
> Error rate = n_validation_errors / (n_success + n_validation_errors)
> ```
>
> **Example**:
> - T3 (Add Task): 5 success, 1 validation_error (blank title submitted)
> - Error rate = 1 / (5 + 1) = 0.17 = 17%
>
> **Interpretation**:
> - **0–10%**: Acceptable—rare mistakes
> - **10–30%**: Moderate—form could be clearer
> - **>30%**: High—likely affordance issues, missing labels, or confusing instructions
>
> **Root causes**:
> - Missing affordances ("I didn't know it was required")
> - Unclear constraints ("How long can the title be?")
> - Focus management ("I accidentally submitted blank")
> - Cognitive load ("Too many fields, got confused")
>
> **HCI Connection**: Error rate measures **efficiency** (ISO 9241-11). High error rates → wasted time, frustration, accessibility barriers.
>
> 🔗 [WCAG 3.3: Input Assistance](https://www.w3.org/WAI/WCAG22/Understanding/input-assistance)

### Prioritisation Framework

> **Prioritisation Framework** [GLOSSARY]
>
> Systematic method to rank backlog items by urgency/importance.
>
> **This module's framework**:
> ```
> Priority score = (Impact + Inclusion) - Effort
> ```
>
> **Dimensions (1–5 scale)**:
> - **Impact**: How many people affected? How severe?
>   - 5 = Blocks task completion for most participants
>   - 3 = Slows down or frustrates some participants
>   - 1 = Minor annoyance, rare
> - **Inclusion**: Does it disproportionately affect disabled people?
>   - 5 = SR/keyboard/cognitive disability users can't complete
>   - 3 = Affects some disabled participants (e.g., low vision)
>   - 1 = Affects everyone equally
> - **Effort**: How hard to fix?
>   - 5 = Major refactor, >8 hours
>   - 3 = Moderate, 2–4 hours
>   - 1 = Quick fix, <1 hour
>
> **Score interpretation**:
> - **8–10**: Critical—fix immediately (Week 10 Lab 2)
> - **5–7**: High priority—fix if time permits
> - **<5**: Defer or document as known issue
>
> **Example**:
> Issue: "Validation errors not announced by SR"
> - Impact: 5 (blocks T2 completion for SR users)
> - Inclusion: 5 (disproportionately affects SR users)
> - Effort: 2 (add `role=alert`, update template)
> - **Score**: (5+5)-2 = **8** → **Priority 1**
>
> **HCI Connection**: Prioritisation makes **inclusion explicit**—not just "fix the bugs" but "fix barriers first."
>
> 🔗 [W3C: Prioritising Accessibility Issues](https://www.w3.org/WAI/test-evaluate/report/#prioritise)

### Evidence Chain (Revisited)

> **Evidence Chain** [GLOSSARY]
>
> Traceability from raw data → analysis → finding → fix → verification.
>
> **Week 10 adds analysis layer**:
> 1. **Raw data**: `metrics.csv` shows `T2_edit, js_mode=off, completion_rate=0.5`
> 2. **Analysis**: `analysis/analysis.csv` confirms: median 2300ms, MAD 450ms, error rate 0.5
> 3. **Interpretation**: No-JS path lacks accessible error feedback (pilot notes confirm)
> 4. **Finding**: "No-JS edit has 50% completion due to non-focusable error summary"
> 5. **Prioritisation**: Impact=5, Inclusion=5, Effort=2 → Score=8 (Priority 1)
> 6. **Fix plan**: Add `tabindex="-1"` to error summary, auto-focus on page load
> 7. **Verification** (Week 10 Lab 2): Retest with no-JS, measure completion rate ≥0.9
>
> **assessment requires**:
> - Before metrics (Week 9)
> - Analysis + prioritisation (Week 10 Lab 1)
> - After metrics (Week 10 Lab 2)
> - Evidence chain documented throughout
>
> 🔗 Review Week 9 Lab 2 for initial evidence chain guidance

---

## Activity A: Prepare Analysis Workspace (5 min)

**Goal**: Set up directory structure and verify data integrity before analysis.

### Step 1: Create analysis directory (2 min)

```bash
mkdir -p analysis
mkdir -p wk10/lab-wk10/docs
mkdir -p wk10/assessment
```

### Step 2: Verify raw data (3 min)

**Open `data/metrics.csv`** and check:
- [ ] Contains rows for all 5 pilots (session IDs: P1, P2, P3, P4, P5)
- [ ] All task codes present (T1, T2, T3, T4)
- [ ] `step` values valid (`success`, `validation_error`, `fail`)
- [ ] Durations plausible (not negative, not >10000ms unless noted)
- [ ] `js_mode` correctly set (`on` for Pilots 1,2,4,5; `off` for Pilot 3)

**If issues found**: Review Week 9 `data-notes.md` for documented anomalies. Exclude corrupt rows or correct manually.

✋ **Stop and check**:
- [ ] `analysis/` directory exists
- [ ] `data/metrics.csv` verified complete
- [ ] Ready to run analysis script

---

## Activity B: Calculate Summary Statistics (25 min)

**Goal**: Generate `analysis/analysis.csv` with median, MAD, completion, error rates per task.

### Step 1: Run analysis script (15 min)

**Option A: Use provided Kotlin script** (if available at `wk10/lab-wk10/scripts/Analyse.kt`):

```bash
kotlinc wk10/lab-wk10/scripts/Analyse.kt -include-runtime -d analyse.jar
java -jar analyse.jar
```

**Option B: Manual calculation with spreadsheet**:

1. Import `data/metrics.csv` into Google Sheets / Excel
2. Create pivot table grouping by `task_code` + `js_mode`
3. Calculate for each group:

**Median time** (filter `step=success` only):
```
=MEDIAN(IF((task=$A2)*(step="success"), ms, ""))
```

**MAD** (median absolute deviation):
```
1. Calculate median for group
2. Create column: ABS(ms - median)
3. MEDIAN of that column
```

**Completion rate**:
```
=COUNTIF(step, "success") / (COUNTIF(step, "success") + COUNTIF(step, "fail"))
```

**Error rate**:
```
=COUNTIF(step, "validation_error") / (COUNTIF(step, "success") + COUNTIF(step, "validation_error"))
```

**Option C: Python script**:

```python
import pandas as pd
import numpy as np

# Load data
df = pd.read_csv('data/metrics.csv')

# Filter success rows for timing
success = df[df['step'] == 'success']

# Group by task and js_mode
grouped = success.groupby(['task_code', 'js_mode'])

# Calculate statistics
stats = grouped['ms'].agg([
    ('n_success', 'count'),
    ('median_ms', 'median'),
    ('mad_ms', lambda x: np.median(np.abs(x - np.median(x))))
]).reset_index()

# Calculate completion rate (per task+js_mode)
total_attempts = df.groupby(['task_code', 'js_mode']).size().reset_index(name='n_total')
success_count = df[df['step']=='success'].groupby(['task_code', 'js_mode']).size().reset_index(name='n_success')
completion = total_attempts.merge(success_count, on=['task_code', 'js_mode'], how='left')
completion['completion_rate'] = completion['n_success'] / completion['n_total']

# Calculate error rate
errors = df[df['step']=='validation_error'].groupby(['task_code', 'js_mode']).size().reset_index(name='n_errors')
error_rate = success_count.merge(errors, on=['task_code', 'js_mode'], how='left').fillna(0)
error_rate['error_rate'] = error_rate['n_errors'] / (error_rate['n_success'] + error_rate['n_errors'])

# Merge all
final = stats.merge(completion[['task_code', 'js_mode', 'completion_rate']], on=['task_code', 'js_mode'])
final = final.merge(error_rate[['task_code', 'js_mode', 'error_rate']], on=['task_code', 'js_mode'])

# Save
final.to_csv('analysis/analysis.csv', index=False)
print(final)
```

### Step 2: Verify output (5 min)

**Open `analysis/analysis.csv`**:

**Expected columns**:
```csv
task_code,js_mode,n_success,median_ms,mad_ms,completion_rate,error_rate
T1_filter,on,5,1899,203,1.00,0.00
T1_filter,off,1,3245,0,1.00,0.00
T2_edit,on,4,1400,184,0.80,0.33
T2_edit,off,0,,,0.00,
T3_add,on,5,567,78,1.00,0.20
T3_add,off,1,3456,0,1.00,0.00
T4_delete,on,5,210,12,1.00,0.00
T4_delete,off,1,198,0,1.00,0.00
```

**Sanity checks**:
- [ ] Median times plausible (T3 < T2 < T1, since T3 is simplest)
- [ ] No-JS times slower than JS-on (expected—full page reloads)
- [ ] Completion rates ≤1.0
- [ ] Error rates between 0 and 1
- [ ] No NaN/blank for tasks with data

**If anomalies**: Cross-reference with `data/metrics.csv`. Document in `analysis/data-notes.md`.

### Step 3: Add "all" js_mode aggregate (5 min)

Calculate combined stats (all participants regardless of JS):

```python
# Add 'all' js_mode (combines on+off)
all_stats = success.groupby(['task_code'])['ms'].agg([
    ('n_success', 'count'),
    ('median_ms', 'median'),
    ('mad_ms', lambda x: np.median(np.abs(x - np.median(x))))
]).reset_index()
all_stats['js_mode'] = 'all'
# ... repeat for completion/error rates, append to analysis.csv
```

✋ **Stop and check**:
- [ ] `analysis/analysis.csv` exists with correct columns
- [ ] All tasks present (T1, T2, T3, T4)
- [ ] Stats calculated for `js_mode=on`, `off`, `all`
- [ ] Values plausible (no negative times, completion ≤1.0)

---

## Activity C: Interpret Metrics with Inclusion Lens (25 min)

**Goal**: Write narrative interpretation of statistics linking to inclusion barriers.

### Step 1: Create summary document (5 min)

**Create `analysis/summary.md`**:

```markdown
# Pilot Data Analysis Summary — Week 10

**Study**: Peer pilots (n=5) conducted Week 9
**Purpose**: Identify usability and accessibility barriers through quantitative + qualitative analysis

---

## Summary Statistics

[Insert tables from analysis.csv]

## Task-by-Task Interpretation

[Analysis for each task with inclusion lens]

## Priority Findings

[Top 3-5 issues for redesign]
```

### Step 2: Build metrics tables (10 min)

**Copy data from `analysis/analysis.csv` into Markdown tables**:

```markdown
## Task Completion Times (Median ± MAD)

| Task | JS-On | JS-Off | All | Notes |
|------|-------|--------|-----|-------|
| T1 (Filter) | 1899ms ± 203ms (n=5) | 3245ms ± 0ms (n=1) | 2015ms ± 380ms (n=6) | No-JS 71% slower |
| T2 (Edit) | 1400ms ± 184ms (n=4) | — (n=0) | 1400ms ± 184ms (n=4) | No-JS: 0% completion |
| T3 (Add) | 567ms ± 78ms (n=5) | 3456ms ± 0ms (n=1) | 850ms ± 890ms (n=6) | No-JS 6× slower |
| T4 (Delete) | 210ms ± 12ms (n=5) | 198ms ± 0ms (n=1) | 208ms ± 12ms (n=6) | Consistent |

## Completion & Error Rates

| Task | JS-On Completion | JS-Off Completion | JS-On Errors | JS-Off Errors |
|------|------------------|-------------------|--------------|---------------|
| T1 | 5/5 (100%) | 1/1 (100%) | 0% | 0% |
| T2 | 4/5 (80%) | 0/1 (0%) | 33% | — |
| T3 | 5/5 (100%) | 1/1 (100%) | 20% | 0% |
| T4 | 5/5 (100%) | 1/1 (100%) | 0% | 0% |
```

### Step 3: Write task-by-task interpretations (10 min)

**For each task, write 2-3 paragraphs**:

**Template**:
```markdown
### Task T2: Edit Task

**Quantitative findings**:
- JS-on: 80% completion (4/5), median 1400ms, 33% error rate
- JS-off: 0% completion (0/1)—participant gave up after 2 validation errors
- MAD 184ms (moderate variability—some participants breezed through, others struggled)

**Qualitative evidence** (from `pilots/P2-notes.md`, `pilots/P3-notes.md`):
- P2 (keyboard-only): "Blank submission error—SR didn't announce error message"
- P3 (no-JS): "Gave up after 2 validation errors—error summary not focusable, couldn't find it with Tab"

**Inclusion impact**: **Critical**
- Screen reader users: Error messages not announced (`role="status"` missing)
- Keyboard-only users: Error summary exists but not in tab order (`tabindex="-1"` missing)
- No-JS users: Cannot complete task—error summary not focusable, no auto-focus on page load

**WCAG violations**:
- 3.3.1 Error Identification (Level A): Errors not programmatically determinable
- 4.1.3 Status Messages (Level AA): Validation errors not announced
- 3.2.1 On Focus (Level A): Focus not managed after error

**Root cause**: Dual-path error handling incomplete. HTMX path has `hx-swap-oob` status but no `role=alert`. No-JS path renders error summary but doesn't set `tabindex="-1"` or auto-focus.

**Proposed fix** (Priority 1):
- Add `role="alert"` to HTMX error responses
- Add `tabindex="-1"` to no-JS error summary `<div id="error-summary">`
- Auto-focus error summary on page load (server-side or small progressive enhancement script)
- Add `aria-describedby` linking input to error message

**Expected impact**: Completion rate ≥90% for all variants, error rate <10% (with improved affordances).
```

**Repeat for all 4 tasks**, prioritising those with issues.

✋ **Stop and check**:
- [ ] Metrics tables complete and formatted
- [ ] Task-by-task interpretations written
- [ ] Inclusion impacts identified (SR, keyboard, no-JS, cognitive)
- [ ] WCAG violations referenced
- [ ] Root causes hypothesized

---

## Activity D: Prioritise Backlog with Scoring (20 min)

**Goal**: Rank backlog items by Priority score = (Impact + Inclusion) - Effort.

### Step 1: Create prioritisation spreadsheet (5 min)

**Create `analysis/prioritisation.csv`**:

```csv
id,title,task_code,problem,impact,inclusion,effort,score,evidence,proposed_fix,candidate_fix
```

### Step 2: Score top issues (10 min)

**For each issue identified in Activity C**:

1. **Impact** (1–5): How many participants affected? How severely?
2. **Inclusion** (1–5): Disproportionate effect on disabled participants?
3. **Effort** (1–5): Time to implement fix?
4. **Score**: (Impact + Inclusion) - Effort

> **🎯 Worked Examples: Scoring Prioritisation**
>
> | Issue | Impact | Inclusion | Effort | Score | Rationale |
> |-------|--------|-----------|--------|-------|-----------|
> | **Validation errors not announced (SR)** | **5** | **5** | 2 | **8** | Impact=5 (all SR users blocked on edit task), Inclusion=5 (SR-specific barrier), Effort=2 (add `role=alert`), Score=(5+5)-2=8 |
> | **No-JS error summary not focusable** | **5** | **5** | 2 | **8** | Impact=5 (all no-JS users affected), Inclusion=5 (keyboard/no-mouse users can't navigate), Effort=2 (add `tabindex=-1`), Score=(5+5)-2=8 |
> | **Filter result count not announced** | **3** | **4** | 1 | **6** | Impact=3 (minor: doesn't block task, but confusing), Inclusion=4 (SR users miss feedback), Effort=1 (move text to live region), Score=(3+4)-1=6 |
> | **Auto-search confuses some** | **3** | **2** | 2 | **3** | Impact=3 (affects 2/5 pilots, minor confusion), Inclusion=2 (not equity-specific), Effort=2 (add button + help text), Score=(3+2)-2=3 |
> | **No-JS delete lacks confirmation** | **2** | **2** | 4 | **0** | Impact=2 (rare: only no-JS + delete use case), Inclusion=2 (low risk, recoverable), Effort=4 (new route + page + tests), Score=(2+2)-4=0 → **Deprioritise** |
>
> **Key distinctions**:
> - **Impact vs Inclusion**: Impact = "how many people?" / Inclusion = "who is disproportionately affected?"
>   - Example 1: High impact (5) + high inclusion (5) = SR validation errors block **everyone** using SR
>   - Example 4: Medium impact (3) + low inclusion (2) = auto-search confusion is **general usability** issue, not equity-specific
> - **When Effort > Benefit**: Example 5 scores 0 (even though impact+inclusion=4) because effort=4 makes ROI negative
> - **Tie-breaking**: Examples 1 & 2 both score 8 → prioritise together if they relate to same task (T2_edit)
>
> **Decision-making**:
> - **Priority 1** (fix in Lab 2): Score ≥8 + WCAG violation + feasible in 2 hours
> - **Priority 2** (semester 2 backlog): Score 5-7
> - **Deprioritise**: Score <5 or effort outweighs benefit

**Example entries**:

```csv
wk9-01,"Validation errors not announced by SR",T2_edit,"SR users can't identify validation errors",5,5,2,8,"analysis/summary.md#T2; pilots/P2-notes.md L12","Add role=alert + aria-describedby to error messages",true

wk9-03,"No-JS error summary not focusable",T2_edit,"Keyboard users can't navigate to error summary in no-JS mode",5,5,2,8,"analysis/summary.md#T2; pilots/P3-notes.md L10","Add tabindex=-1, auto-focus on page load",true

wk9-05,"Filter result count not announced",T1_filter,"SR users don't hear how many results remain after filtering",3,4,1,6,"pilots/P2-notes.md L8","Move result count into live region (role=status)",false

wk9-02,"Filter auto-search confuses some",T1_filter,"Some participants expected explicit Apply button",3,2,2,3,"pilots/P1-notes.md L6","Add visible Apply button or help text",false

wk9-04,"No-JS delete has no confirmation",T4_delete,"No-JS participants can't confirm before deleting (documented trade-off)",2,2,4,0,"pilots/P3-notes.md L12; wk08/docs/prototyping-constraints.md","Add /tasks/{id}/delete/confirm page",false
```

### Step 3: Rank and mark candidates (5 min)

**Sort by `score` descending**.

**Mark top 2-3 items** as `candidate_fix=true` (these are Priority 1 for Week 10 Lab 2).

**Criteria for Priority 1**:
- Score ≥8
- WCAG Level A or AA violation
- Blocks task completion for disabled participants
- Feasible to fix in 2-hour lab session

**In example above**: wk9-01 and wk9-03 both score 8, both relate to T2 edit, both fixable together → **Priority 1**.

✋ **Stop and check**:
- [ ] `analysis/prioritisation.csv` complete with scores
- [ ] Issues ranked by score (highest first)
- [ ] 2-3 Priority 1 items marked `candidate_fix=true`
- [ ] Evidence links present for all items

---

## Activity E: Draft Inclusive Redesign Brief (20 min)

**Goal**: Document planned fix with measurable goals and acceptance criteria.

### Step 1: Create redesign brief document (5 min)

**Create `wk10/lab-wk10/docs/redesign-brief.md`**:

```markdown
# Inclusive Redesign Brief — Week 10 Lab 2

**Target**: [Issue title from prioritisation.csv]
**Priority**: 1 (Score: X)
**Assignee**: [Your name/pair]
**Date**: 2025-10-20

---

## Problem Statement

[2-3 sentences describing the issue backed by data]

---

## Goal

[Measurable target improvement]

---

## Inclusion Impact

[Who benefits and why]

---

## Proposed Changes

[Specific implementation steps]

---

## Acceptance Criteria

[How will we know it's fixed?]

---

## Verification Plan

[Testing protocol]

---

## Risk & Constraints

[Trade-offs, technical limitations]
```

### Step 2: Fill in sections (15 min)

**Using T2 edit accessibility issue as example**:

```markdown
# Inclusive Redesign Brief — Week 10 Lab 2

**Target**: Validation errors not announced by screen readers (wk9-01, wk9-03)
**Priority**: 1 (Score: 8)
**Assignee**: [Your name]
**Date**: 2025-10-20

---

## Problem Statement

Task T2 (Edit Task) has 80% completion for JS-on participants and 0% completion for JS-off participants. Analysis shows:
- 33% error rate (2 validation errors in 6 attempts)
- Median time 1400ms with MAD 184ms (moderate variability)
- Qualitative evidence: "SR didn't announce error message" (P2), "Error summary not focusable" (P3)

**Root cause**: Validation error messages not accessible. HTMX path lacks `role="alert"`. No-JS path renders error summary but doesn't focus it or make it keyboard-navigable.

**WCAG violations**: 3.3.1 Error Identification (A), 4.1.3 Status Messages (AA), 3.2.1 On Focus (A)

---

## Goal

**Target metrics** (Week 10 Lab 2 verification):
- T2 completion rate ≥90% for all variants (JS-on, JS-off, keyboard-only, SR)
- T2 error rate <10% (improved affordances reduce accidental blank submissions)
- Zero WCAG 3.3.1 / 4.1.3 violations on retest

**Success means**: Screen reader users hear validation errors immediately. Keyboard-only users can navigate to error summary with Tab. No-JS users see error summary on page load with focus.

---

## Inclusion Impact

**Who benefits**:
- **Screen reader users** (estimated 2-5% of UK population): Can identify and recover from errors independently
- **Keyboard-only users** (motor disabilities, power users): Can navigate to error without mouse
- **Cognitive disabilities**: Clear, announced errors reduce confusion and repeated mistakes
- **Low-bandwidth users** (no-JS fallback): Can complete tasks even with JS disabled

**Equity**: Current design excludes disabled participants—completion rate 0% for no-JS, SR users struggled. Fix restores parity.

---

## Proposed Changes

### Change 1: Add ARIA live region for HTMX errors

**File**: `src/main/kotlin/routes/Tasks.kt`

**Before**:
```kotlin
if (title.isBlank()) {
    val status = """<div id="status" hx-swap-oob="true">Title is required.</div>"""
    return@post call.respondText(status, ContentType.Text.Html, HttpStatusCode.BadRequest)
}
```

**After**:
```kotlin
if (title.isBlank()) {
    val status = """<div id="status" role="alert" aria-live="assertive" hx-swap-oob="true">Title is required. Please enter at least one character.</div>"""
    return@post call.respondText(status, ContentType.Text.Html, HttpStatusCode.BadRequest)
}
```

**Rationale**: `role="alert"` + `aria-live="assertive"` ensures SR announces error immediately.

---

### Change 2: Make no-JS error summary keyboard-focusable

**File**: `templates/tasks/index.peb`

**Before**:
```twig
{% if error %}
<div class="error-summary" id="error-summary">
  <h2>There is a problem</h2>
  <ul>
    <li><a href="#title">Title is required</a></li>
  </ul>
</div>
{% endif %}
```

**After**:
```twig
{% if error %}
<div class="error-summary" id="error-summary" tabindex="-1" role="alert" aria-live="assertive">
  <h2>There is a problem</h2>
  <ul>
    <li><a href="#title">{% if msg == "too_long" %}Title is too long (max 200 characters){% else %}Title is required{% endif %}</a></li>
  </ul>
</div>
<script>
  // Progressive enhancement: auto-focus error summary (no-JS users rely on visual scan)
  if (document.getElementById('error-summary')) {
    document.getElementById('error-summary').focus();
  }
</script>
{% endif %}
```

**Rationale**:
- `tabindex="-1"` makes div focusable programmatically (not in natural tab order)
- `role="alert"` + `aria-live="assertive"` announces to SR on page load
- Tiny `<script>` auto-focuses error (progressive enhancement—still works if JS fails)

---

### Change 3: Link input to error message

**File**: `templates/tasks/index.peb`

**Update input**:
```twig
<input id="title" name="title" type="text"
       {% if error == "title" %}aria-invalid="true" aria-describedby="title-error"{% endif %}
       required>
{% if error == "title" %}
<p id="title-error" class="error-message" role="alert">
  {% if msg == "too_long" %}Title is too long (max 200 characters){% else %}Title is required{% endif %}
</p>
{% endif %}
```

**Rationale**: `aria-describedby` links input to error message. SR reads error when focus lands on input.

---

## Acceptance Criteria

### Functional
- [ ] Blank title submission triggers validation error (both HTMX and no-JS)
- [ ] HTMX error appears in `#status` live region with `role="alert"`
- [ ] No-JS error summary appears at top of page
- [ ] Error summary has `tabindex="-1"` and receives focus on page load

### Accessibility (WCAG 2.2 Level AA)
- [ ] **3.3.1 Error Identification (A)**: Error described in text ✓
- [ ] **4.1.3 Status Messages (AA)**: Error announced by SR without focus change ✓
- [ ] **3.2.1 On Focus (A)**: Focus managed predictably (lands on error summary) ✓
- [ ] **1.3.1 Info & Relationships (A)**: `aria-describedby` links input to error ✓

### Testing Protocol
- [ ] **Keyboard-only**: Tab to form, submit blank, Tab to error summary link, press Enter → focus lands on `#title` input
- [ ] **Screen reader (NVDA/Orca)**: Submit blank → SR announces "Alert. Title is required. Please enter at least one character."
- [ ] **No-JS**: Disable JS, submit blank → page reloads with error summary focused, Tab order correct
- [ ] **Re-run T2 pilot**: 3 participants (1 SR, 1 keyboard, 1 no-JS), measure completion rate

---

## Verification Plan

### Quantitative (Week 10 Lab 2)
Re-run T2 (Edit Task) with 3 participants:
1. **P6**: Standard (HTMX, mouse, JS-on)
2. **P7**: Keyboard-only + NVDA (SR)
3. **P8**: No-JS

**Measure**:
- Completion rate (target: 3/3 = 100%)
- Error rate (target: <1 error across 3 attempts = <33%)
- Median time (expect similar to Week 9: ~1400ms for JS-on)

**Document in**: `wk10/lab-wk10/research/verification-notes.md`

### Qualitative
- Capture SR output (transcript snippet) confirming error announcement
- Screenshot of no-JS error summary with focus indicator visible
- Note any remaining confusion or hesitation

### Backlog Update
Mark wk9-01 and wk9-03 as `status=fixed`, append evidence links:
```csv
wk9-01,...,fixed,"wk10/lab-wk10/research/verification-notes.md; wk10/lab-wk10/evidence/sr-error-announcement.png"
```

---

## Risk & Constraints

### Technical Constraints
- **No major refactor**: Must stay within 2-hour lab session
- **Server-first principle**: Keep HTMX enhancement, ensure no-JS parity
- **Template complexity**: Adding `aria-*` attributes increases template size—acceptable trade-off for accessibility

### Potential Issues
- **Progressive enhancement script**: Tiny `<script>` auto-focuses error. If JS fails to load, user must visually scan for error summary—**acceptable fallback** (error still visible, focusable with Tab).
- **Multiple errors**: Currently handles one error field. If expanding to multiple fields, need error summary list—**defer to future** (out of scope for Week 10).

### Trade-offs Accepted
- **No client-side validation**: Sticking with server-side only. Could add `maxlength` attribute for instant feedback—**defer** (progressive enhancement for later).

---

## Success Criteria Summary

**Definition of Done**:
1. Code changes committed with tests passing
2. Verification pilots completed (n=3)
3. T2 completion rate ≥90% (all variants)
4. Zero WCAG 3.3.1 / 4.1.3 violations
5. Evidence captured (screenshots, SR transcripts, metrics)
6. Backlog updated (wk9-01, wk9-03 marked fixed)
7. assessment evidence pack updated with before/after data

**If not met**: Document blockers, revert changes, choose lower-priority fix.
```

✋ **Stop and check**:
- [ ] Redesign brief complete with all sections
- [ ] Problem backed by Week 9 data
- [ ] Changes specific (file paths, before/after code)
- [ ] Acceptance criteria measurable
- [ ] Verification plan includes quantitative + qualitative testing

---

## Commit & Reflect (10 min)

### Commit message

```bash
git add analysis wk10/lab-wk10/docs/redesign-brief.md backlog/backlog.csv

git commit -m "$(cat <<'EOF'
wk10s1: analysed metrics, prioritised fixes, drafted redesign brief

- Generated analysis/analysis.csv: medians, MAD, completion/error rates per task+js_mode
- Interpreted metrics with inclusion lens: T2 edit has 0% no-JS completion, 33% error rate
- Identified WCAG violations: 3.3.1, 4.1.3 (validation errors not announced/focusable)
- Prioritised backlog using (Impact+Inclusion)-Effort: wk9-01/wk9-03 score=8 (Priority 1)
- Drafted inclusive redesign brief targeting T2 validation error accessibility
- Proposed changes: role=alert for HTMX, tabindex=-1 + auto-focus for no-JS, aria-describedby linking
- Defined acceptance criteria: ≥90% completion, zero WCAG violations on retest

Key findings:
- T2 (Edit): 80% JS-on completion, 0% JS-off completion → parity failure
- T2 error rate: 33% (blank submissions due to poor affordances)
- T1 (Filter): 100% completion but no-JS 71% slower (acceptable—functional parity maintained)

Ready for Week 10 Lab 2 implementation and verification.


EOF
)"
```

### Reflection questions

**Answer in `wk10/reflection.md`**:

1. **Data interpretation**: What surprised you most in the numbers? Were any findings unexpected based on Week 9 observations?

2. **Inclusion lens**: How did the prioritisation framework change which issues you focused on? Would you have chosen differently without the Inclusion dimension?

3. **Trade-offs**: Which issues did you deprioritise and why? How comfortable are you with those decisions?

4. **Redesign confidence**: How confident are you that the proposed fix will achieve ≥90% completion? What uncertainties remain?

5. **Evidence chains**: Can you trace wk9-01 from raw `metrics.csv` → `analysis/summary.md` → `prioritisation.csv` → `redesign-brief.md`? Is anything missing?

6. **Week 10 Lab 2 readiness**: What could go wrong during implementation? How will you mitigate?

---

## Looking Ahead: Week 10 Lab 2

Next session:
- **Implement** Priority 1 fix (T2 validation error accessibility)
- **Verify** with 3 participants (SR, keyboard, no-JS)
- **Measure** completion rate, error rate, qualitative observations
- **Update** backlog with verification evidence
- **Prepare** assessment draft pack (before/after metrics, code diffs, evidence)

**Before Lab 2**:
- Review redesign brief (`wk10/lab-wk10/docs/redesign-brief.md`)
- Refresh ARIA syntax (`role="alert"`, `aria-describedby`, `aria-live`)
- Prepare verification pilot script (similar to Week 9 protocol)
- Check that Week 9 data is committed (don't lose baseline!)

---

## Further Reading & Resources

### Essential
- Review [Evaluation Metrics Quick Reference](../references/evaluation-metrics-quickref.md) (formulas reference)
- [GOV.UK: Using data to improve your service](https://www.gov.uk/service-manual/measuring-success/using-data-to-improve-your-service)

### Statistical Analysis
- [Measuring UX](https://measuringux.com/) — Quantitative UX metrics handbook
- [Statistics for HCI](https://hci-stats.com/) — Practical guide for small-sample HCI studies

### Prioritisation
- [Nielsen: Prioritising Web Usability Problems](https://www.nngroup.com/articles/how-to-rate-the-severity-of-usability-problems/)
- [W3C: Prioritising Accessibility Issues](https://www.w3.org/WAI/test-evaluate/report/#prioritise)
- [GOV.UK: Prioritising user research findings](https://www.gov.uk/service-manual/user-research/analyse-a-research-session#prioritise-findings)

### Inclusive Design
- [Microsoft Inclusive Design Toolkit](https://www.microsoft.com/design/inclusive/)
- [W3C: Involving Users in Evaluating Web Accessibility](https://www.w3.org/WAI/test-evaluate/involving-users/)

### Academic
- **Lazar et al. (2017).** *Research Methods in Human-Computer Interaction* (2nd ed.). Chapter 11: Statistical analysis
- **Sauro & Lewis (2016).** *Quantifying the User Experience* (2nd ed.). Chapters 2-5: Metrics selection and interpretation

---

## Glossary Summary

| Term | One-line definition |
|------|---------------------|
| **Descriptive statistics** | Summarize and describe main features of a dataset (median, range, count) |
| **Median** | Middle value in sorted dataset; 50th percentile; resistant to outliers |
| **MAD (Median Absolute Deviation)** | Robust measure of variability; median of |value - median| |
| **Completion rate** | Proportion of task attempts that succeeded (effectiveness measure) |
| **Error rate** | Proportion of attempts that triggered validation errors |
| **Prioritisation framework** | Systematic method to rank backlog items by (Impact+Inclusion)-Effort |
| **Evidence chain** | Traceability from raw data → analysis → finding → fix → verification |
| **Impact** | How many people affected and how severely (1-5 scale) |
| **Inclusion** | Does issue disproportionately affect disabled people? (1-5 scale) |
| **Effort** | Time/complexity to implement fix (1-5 scale) |

---

**Lab complete!** You have data-driven insights, prioritised backlog, and a detailed redesign plan. Week 10 Lab 2 will implement and verify the fixes.
