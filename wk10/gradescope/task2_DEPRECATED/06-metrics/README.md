# Metrics — Before/After Data

**Purpose**: Quantitative data supporting claims of improvement from Week 9 baseline to Week 10 post-fix.

---

## Directory Structure

```
06-metrics/
├── README.md              ← You are here
├── pre/
│   └── analysis.csv       ← Week 9 baseline (from Week 10 Lab 1 Analyse.kt)
└── post/
    └── postchange.csv     ← Week 10 post-fix (from re-pilots, n=2)
```

---

## 1. Pre-Fix Data (`pre/analysis.csv`)

**Source**: Week 9 Lab 2 pilots (n=4-5) → Week 10 Lab 1 analysis script (`Analyse.kt`)

**Purpose**: Establish baseline metrics before redesign

**Expected columns**:

| Column | Type | Description | Example |
|--------|------|-------------|---------|
| `task_code` | String | Task identifier | `T1_filter`, `T2_edit`, `T3_add`, `T4_delete` |
| `js_mode` | Enum | JavaScript availability | `on`, `off` |
| `n_success` | Integer | Number of successful completions | `4` |
| `n_total` | Integer | Total attempts | `5` |
| `completion_rate` | Float | Success rate (n_success / n_total) | `0.80` (80%) |
| `median_ms` | Integer | Median completion time (ms) | `1400` |
| `mad_ms` | Integer | Median Absolute Deviation (ms) | `234` |
| `errors_validation` | Integer | Count of validation errors | `2` |
| `error_rate` | Float | Validation errors / total attempts | `0.33` (33%) |

**Example data**:

```csv
task_code,js_mode,n_success,n_total,completion_rate,median_ms,mad_ms,errors_validation,error_rate
T1_filter,on,5,5,1.00,1899,203,0,0.00
T2_edit,on,4,5,0.80,1400,234,2,0.33
T3_add,on,5,5,1.00,567,89,1,0.20
T4_delete,on,5,5,1.00,210,12,0,0.00
T2_edit,off,1,1,1.00,3456,0,1,1.00
```

**Key observations** (example):
- T2 (edit task) has **80% completion rate** (1 participant gave up after validation errors)
- T2 has **33% error rate** (2 validation errors out of 6 attempts)
- T2 no-JS variant has **100% error rate** (1/1 participants hit error, error summary not focusable)
- T1, T3, T4 have 100% completion rates (no major issues)

---

## 2. Post-Fix Data (`post/postchange.csv`)

**Source**: Week 10 Lab 2 re-pilots (n=2) after implementing fixes

**Purpose**: Demonstrate improvement from baseline

**Expected columns**: Same as `pre/analysis.csv`

**Example data**:

```csv
task_code,js_mode,n_success,n_total,completion_rate,median_ms,mad_ms,errors_validation,error_rate
T2_edit,on,2,2,1.00,1150,50,0,0.00
T2_edit,off,1,1,1.00,2890,0,0,0.00
```

**Key observations** (example):
- T2 completion rate improved: 80% → **100%** (all participants successful)
- T2 error rate improved: 33% → **0%** (no validation errors—better error feedback)
- T2 median time improved: 1400ms → **1150ms** (faster error correction)
- T2 no-JS error rate improved: 100% → **0%** (error summary now focusable)

---

## 3. Before/After Comparison

**This comparison goes in `03-before-after-summary.md`** (root of task2/ directory).

**Template**:

```markdown
# Before vs After — Summary

**Focus**: Task T2 (Edit task) — the primary target of Week 10 redesign

| Metric | Pre (Week 9, n=5) | Post (Week 10, n=2) | Δ | Goal Met? | Notes |
|--------|------------------|---------------------|---|-----------|-------|
| **Completion rate** | 80% (4/5) | 100% (2/2) | +20pp | ✅ Yes | All participants successful |
| **Error rate** | 33% (2/6) | 0% (0/2) | -33pp | ✅ Yes | No validation errors triggered |
| **Median time** | 1400ms | 1150ms | -250ms | ✅ Yes | 18% faster (goal: ≤1200ms) |
| **SR accessibility** | 0% (errors not announced) | 100% (errors announced) | +100pp | ✅ Yes | WCAG 4.1.3 compliance |

**Overall**: ✅ All goals met. T2 now fully accessible, faster, and error-free.
```

**Statistical notes**:

- **Small n** (n=2 post-fix): Typical for regression testing—goal is to verify fix works, not generalize to population
- **Completion rate**: 100% = promising, but limited sample
- **Error rate**: 0% = excellent, suggests error feedback improvements effective
- **Median time**: 18% faster = significant for such a short task (~14 seconds)
- **SR accessibility**: Most critical improvement—went from "completely broken" to "fully compliant"

---

## 4. Generating These Files

### Pre-Fix Data

**Week 10 Lab 1**: Run `Analyse.kt` script on Week 9 `metrics.csv`

```bash
cd wk10/lab-wk10/scripts
kotlin Analyse.kt
# Output: analysis.csv (copy to gradescope/task2/06-metrics/pre/)
```

**If script not available**, manually calculate:

1. **Completion rate**: Count `step=success` rows / total attempts per task
2. **Median time**: Sort `ms` values for `step=success`, take middle value
3. **MAD**: Calculate median of absolute deviations from median
4. **Error rate**: Count `step=validation_error` / total attempts

---

### Post-Fix Data

**Week 10 Lab 2**: Run re-pilots (n=2) using **same protocol as Week 9**

**Process**:
1. Implement fixes (40 min)
2. Regression testing (30 min) — ensure nothing broke
3. Re-pilots (30 min):
   - 1× keyboard-only participant
   - 1× no-JS participant
4. Extract data from `metrics.csv` (filter by session IDs)
5. Calculate summary stats (same as pre-fix)
6. Save as `postchange.csv`

**Example extraction**:

```bash
# Filter Week 10 re-pilot sessions
grep -E "P6_|P7_" data/metrics.csv > wk10/gradescope/task2/06-metrics/post/raw.csv

# Calculate summary (manual or script)
# Result: postchange.csv
```

---

## 5. Data Quality

**Before including metrics in Task 2 submission**:

- [ ] **Pre-fix data** matches Week 9 `05-findings.md` (consistency check)
- [ ] **Post-fix data** has ≥ 2 participants (minimum for meaningful comparison)
- [ ] **Same tasks** measured pre/post (T2 is priority, but include T1/T3/T4 if time permits)
- [ ] **Same protocol** used (comparability—can't compare apples to oranges)
- [ ] **No PII** in data (session IDs anonymous, no names/emails)

**Document any deviations** in `03-before-after-summary.md` notes column.

---

## 6. Interpretation Guidelines

**What "good" looks like**:

| Metric | Good Δ | Interpretation |
|--------|--------|----------------|
| **Completion rate** | +10pp to +20pp | More participants successfully complete task |
| **Error rate** | -20pp to -50pp | Fewer validation errors (better feedback/UX) |
| **Median time** | -10% to -30% | Faster task completion (more efficient) |
| **SR accessibility** | 0% → 100% | Went from broken to compliant (critical fix) |

**What to do if metrics don't improve**:

1. **Check regression tests**: Did fix break something else?
2. **Review pilot notes**: Qualitative data may reveal issues quantitative data misses
3. **Small n**: With n=2, one participant struggling skews results—look at individual sessions
4. **Re-verify fix**: Test with NVDA/keyboard yourself before assuming metrics are wrong

**Honesty > perfection**: If metrics don't improve, document why and what you'd do differently (LO12: Demonstrate professionalism).

---

## 7. Submission Checklist

Before submitting Task 2:

- [ ] `pre/analysis.csv` exists and matches Week 9 baseline
- [ ] `post/postchange.csv` exists with n ≥ 2 post-fix pilots
- [ ] `03-before-after-summary.md` compares key metrics with Δ column
- [ ] Metrics support claims in `01-redesign-brief.md` (evidence chain)
- [ ] Any anomalies or deviations documented in notes
- [ ] No PII in data files

---

## 8. References

- **Median vs Mean**: Use median (robust to outliers). MAD > std deviation for skewed data.
- **Statistical significance**: Not applicable with n=2—this is formative evaluation, not hypothesis testing.
- **Nielsen's 5-user rule**: 5 participants find ~85% of issues. n=2 sufficient for regression testing (verifying fixes work).

**Further reading**:
- [Nielsen: Why You Only Need to Test with 5 Users](https://www.nngroup.com/articles/why-you-only-need-to-test-with-5-users/)
- [Sauro: Measuring Usability with the SUS](https://measuringu.com/sus/)
- [W3C: Planning and Managing Web Accessibility](https://www.w3.org/WAI/planning-and-managing/)

---

**Author**: [Your name]
**Last updated**: [YYYY-MM-DD]
**Related files**: `01-redesign-brief.md`, `03-before-after-summary.md`, `05-findings.md` (Week 9)
