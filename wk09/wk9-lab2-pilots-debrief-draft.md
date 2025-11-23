# Week 9 • Lab 2 — Peer Pilots, Debrief, and Assessment Draft Pack

![COMP2850](https://img.shields.io/badge/COMP2850-HCI-blue)
![Week 9](https://img.shields.io/badge/Week-9-orange)
![Lab 2](https://img.shields.io/badge/Lab-2-green)
![Status](https://img.shields.io/badge/Status-Draft-yellow)



---

## Before Lab: Required Reading (10 mins)

📖 **Essential**:
- Push/log Week 9 starter repo instrumentation before pilots
- Review your Week 9 Lab 1 protocol (`wk09/lab-wk9/research/protocol.md`)
- Review `references/consent-pii-faq.md`
- [Nielsen: How Many Test Users in Usability Studies?](https://www.nngroup.com/articles/how-many-test-users/)

📖 **Quick reference**:
- [Evaluation Metrics Quick Reference](../references/evaluation-metrics-quickref.md)
- [Assistive Testing Checklist](../references/assistive-testing-checklist.md)
- [Screenshot Evidence Guide](../references/screenshot-guide.md)

---

## Introduction: From Plan to Data

Week 9 Lab 1 built the evaluation infrastructure. **Today you execute**.

Running peer pilots is **the most critical empirical HCI activity** in this module:
- **Data collection**: Objective metrics (time, errors, completion) + subjective (confidence, satisfaction)
- **Qualitative insights**: Observe real struggles, accessibility barriers, unexpected behaviours
- **Evidence generation**: Logs, notes, screenshots for assessment portfolio (due end Week 10)

**Why this matters**:
- Week 10 redesign **depends on** identifying real problems (not assumed ones)
- Assessment grade depends on evidence quality (traceability from raw data → findings → mitigations)
- Professional practice: Decisions backed by data, not opinions

**Ethical imperative**: Participants are peers, not research subjects. Treat them respectfully, honour consent, protect privacy.

---

## Learning Focus

### Lab Objectives

> **Staff reference**: Sample data + completed pilot pack available in the [solution repository](../../resources/code-resources.md#week-9).
By the end of this session, you will have:
- Conducted 4 peer pilots following ethical protocol
- Collected quantitative data (time-on-task, errors, SUS) and qualitative observations
- Taken observer notes (quotes, errors, time-on-task)
- Debriefed with participants and synthesised findings
- Documented findings with evidence chains (raw data → issue → backlog item)
- Assembled draft assessment evidence pack

### Learning Outcomes Addressed
This lab contributes to the following module Learning Outcomes ([full definitions](../references/learning-outcomes.md)):

- **LO6**: Apply iterative design — evidenced by pilot data → findings synthesis
- **LO8**: Design and execute evaluation — evidenced by 4 pilot sessions + data
- **LO11**: Collaborate in teams — evidenced by peer pilot facilitation + observer role
- **LO12**: Demonstrate professionalism — evidenced by consent adherence + respectful facilitation

---

## Key Concepts

### Pilot Study

> **Pilot Study** [GLOSSARY]
>
> Small-scale preliminary study to test evaluation protocol and gather initial data. **Formative** (improve design) vs **Summative** (final quality assessment).
>
> **This module uses formative pilots**: Identify issues to fix in Week 10, not measure final quality.
>
> **Characteristics**:
> - Small sample (5–10 participants typical for qualitative insights)
> - Controlled tasks (defined in Week 9 Lab 1)
> - Mixed methods (quantitative metrics + qualitative observation)
> - Iterative (findings inform redesign)
>
> **Nielsen's 5-user rule**: 5 participants find ~85% of usability issues. Diminishing returns after that for formative testing.
>
> **HCI Connection**: Empirical HCI requires **ecological validity**—test with real people doing realistic tasks, not just hypothetical analysis.
>
> 🔗 [Nielsen: Why You Only Need to Test with 5 Users](https://www.nngroup.com/articles/why-you-only-need-to-test-with-5-users/)

### Qualitative vs Quantitative Data

> **Quantitative Data** [GLOSSARY]
>
> Numerical measurements: times, counts, percentages. **Objective**, statistically analysable.
>
> **Examples from this module**:
> - Task completion time: Median 24s (MAD 6s)
> - Error rate: 2/5 = 40%
> - Completion rate: 4/5 = 80%
>
> **Strengths**: Comparable, repeatable, supports statistical tests
> **Limitations**: Doesn't explain "why"—need qualitative data to interpret
>
> ---
>
> **Qualitative Data** [GLOSSARY]
>
> Non-numerical observations: quotes, behaviours, patterns. **Subjective**, interpretive.
>
> **Examples from this module**:
> - "Participant paused 10s, said 'I'm not sure if it saved'"
> - "Screen reader did not announce filter result count"
> - "Participant used Ctrl+F instead of built-in filter"
>
> **Strengths**: Reveals "why" issues occur, uncovers unexpected problems
> **Limitations**: Varies by participant, researcher bias, harder to generalize
>
> **HCI Connection**: Best practice = **mixed methods** (both quant + qual). Numbers show *what* happened, observations show *why*.
>
> 🔗 [Lazar et al.: Research Methods in HCI](https://dl.acm.org/doi/book/10.5555/2737875) — Chapter 9

### Think-Aloud Protocol

> **Think-Aloud Protocol** [GLOSSARY]
>
> Participants verbalize their thoughts while completing tasks. Reveals cognitive process, confusion points, expectations.
>
> **Types**:
> - **Concurrent**: Talk while doing task (can be distracting, slower)
> - **Retrospective**: Explain after completing (less disruptive but memory decay)
>
> **For this module**: **Optional concurrent** (invite, don't force). Some participants find it natural, others find it intrusive.
>
> **Example quote captured**:
> > "I'm looking for a filter... is this the search box? I'll try typing here."
>
> **HCI Connection**: Think-aloud reveals **mental models**—how people understand the interface vs how it actually works.
>
> **Accessibility note**: Think-aloud can be difficult for screen reader users (talking competes with SR audio). Allow silence.
>
> 🔗 [Nielsen: Thinking Aloud: The #1 Usability Tool](https://www.nngroup.com/articles/thinking-aloud-the-1-usability-tool/)

### Evidence Chain

> **Evidence Chain** [GLOSSARY]
>
> Traceability from raw data → finding → backlog item → fix → verification. Critical for academic rigour and professional practice.
>
> **Example chain**:
> 1. **Raw data**: `metrics.csv` shows `T2_edit, validation_error, blank_title` for session `WK9A03`
> 2. **Observation**: Pilot notes say "Participant didn't notice error message, submitted blank again"
> 3. **Finding**: "Error messages not accessible to screen readers (WCAG 4.1.3 violation)"
> 4. **Backlog item**: `wk9-05: Add role=alert to validation errors`
> 5. **Fix** (Week 10): Update template with `<p role="alert">`
> 6. **Verification**: Retest with screen reader, confirm announcement
>
> **Why this matters**:
> - **Assessment**: Markers check evidence chains (no evidence = no marks)
> - **Professional practice**: Design decisions require justification
> - **Accreditation**: External panels expect rigorous HCI process
>
> **HCI Connection**: Evidence chains demonstrate **systematic design process**, not ad-hoc changes.
>
> 🔗 [GOV.UK Service Manual: Using data in service design](https://www.gov.uk/service-manual/measuring-success/using-data-to-improve-your-service)

### Thematic Coding

> **Thematic Coding** [GLOSSARY]
>
> Systematic process of identifying patterns (themes) in qualitative data. Used to analyse pilot notes.
>
> **Process**:
> 1. **Read notes**: Familiarize yourself with all observations
> 2. **Code**: Label each observation with tags (e.g., "sr-announcement", "validation-error", "focus-management")
> 3. **Group**: Cluster similar codes into themes (e.g., all "sr-announcement" issues → theme: "Status feedback")
> 4. **Interpret**: What do these themes tell us about usability/accessibility?
>
> **Example** (simplified):
> ```
> Observation: "Participant didn't notice success message" → Code: feedback-timing
> Observation: "SR didn't announce deletion" → Code: sr-announcement
> Observation: "Participant asked 'did it save?'" → Code: feedback-clarity
>
> Theme: "Success feedback insufficient" (3 codes)
> Priority: High (affects confidence, inclusion)
> ```
>
> **This module**: Light-touch thematic coding in Week 10. Full formal coding (inter-rater reliability, etc.) is postgraduate-level.
>
> 🔗 [Braun & Clarke: Thematic Analysis](https://www.tandfonline.com/doi/abs/10.1191/1478088706qp063oa) — Academic reference

---

## Activity A: Pilot Preparation (10 min)

**Goal**: Ensure everything is ready before first participant arrives.

### Step 1: Technical setup (5 min)

1. **Start server**: `./gradlew run` (or IDE run configuration)
2. **Test routes**: Visit `/tasks`, confirm prototype works (add/edit/filter/delete)
3. **Clear old data** (optional): If `data/metrics.csv` contains dry-run rows, either delete them or note the starting row number so you know which rows are real pilot data

**Verify instrumentation**:
- Submit a test task → check `metrics.csv` for new row
- Submit empty form → check for `validation_error` row
- Disable JS, repeat → check `js_mode=off` appears

If anything's broken, **fix it now** (don't waste participants' time).

### Step 2: Materials ready (3 min)

**Physical/digital checklists**:
- [ ] Protocol document (`wk09/lab-wk9/research/protocol.md`) printed or on second screen
- [ ] Task scenarios ready to read aloud
- [ ] Consent script ready (memorize or have visible)
- [ ] `pilot-notes.md` template open in editor
- [ ] Stopwatch/timer (backup for server timing)
- [ ] Post-task questions printed (confidence rating 1–5)

**Participant setup**:
- [ ] Clean browser session (clear cookies, cache)
- [ ] Navigate to prototype homepage
- [ ] DevTools closed (don't intimidate participant with console)

### Step 3: Role allocation (2 min)

**Work in pairs or triads**:
- **Facilitator**: Reads scenarios, asks questions, manages timing
- **Note-taker**: Records observations, direct quotes, timestamps
- **Participant**: Completes tasks

**Rotate roles** after each pilot (everyone gets experience facilitating and participating).

✋ **Stop and check**:
- [ ] Server running, prototype functional
- [ ] metrics.csv logging correctly
- [ ] Protocol and materials ready
- [ ] Roles assigned

---

## Activity B: Run Pilot Sessions (60–80 min)

**Goal**: Conduct 5–6 peer pilots following your ethical protocol.

**Timing**:
- ~15 minutes per pilot (4 tasks + debrief)
- ~5 minutes between pilots (swap roles, generate new session ID)
- Total: 5 pilots × 20 min = ~100 min

**If time is tight**: Minimum 3 pilots (one with keyboard-only, one with JS-off, one standard).

### Pilot 1: Standard (HTMX, mouse, JS-on)

#### Setup (3 min)

1. **Generate session ID**:
   ```bash
   openssl rand -hex 3  # Example output: 7a9f2c
   ```
   Or use: `https://www.random.org/strings/` (6 chars, alphanumeric)

2. **Set cookie** in participant browser (DevTools Console):
   ```javascript
   document.cookie = "sid=P1_7a9f; path=/";
   ```

3. **Record in consent log** (`wk09/lab-wk9/research/consent-log.md`):
   ```markdown
   ## Pilot 1
   Date: 2025-10-15
   Participant code: P1
   Session ID: P1_7a9f
   Variant: Standard (HTMX, mouse, JS-on)
   Consent: Verbal consent given at 14:15
   Notes: None
   ```

#### Consent process (2 min)

**Read script** (from protocol.md):
> "Thanks for agreeing to pilot our prototype. This is a quick usability test—about 15 minutes. I'll ask you to complete 4 tasks while I observe and take notes. I'm testing the interface, not you, so there are no wrong answers.
>
> **What we're collecting**: task times, whether you complete successfully, any errors, your confidence ratings, and my notes on any issues.
>
> **What we're NOT collecting**: your name, email, student ID, or any recordings.
>
> Your session code is `P1_7a9f`. You can request data deletion anytime.
>
> **You can stop at any time.** Do you have questions?"

**Wait for verbal consent**: "Are you happy to proceed?"

If participant declines or seems uncertain, thank them and find another volunteer.

#### Warm-up (2 min, not timed)

"Take a minute to browse the task list. Click around, get familiar. Think aloud if you're comfortable—say what you're thinking—but no pressure. Let me know when you're ready for the first task."

**Note-taker observes**: Initial reactions, confusion points, do they notice key UI elements?

#### Task T3: Add Task (60s limit)

**Facilitator reads**:
> "You need to remember to 'Call supplier about delivery'. Add this as a new task."

**Start timing**: When participant focuses in input or begins typing.

**Note-taker records**:
- Timestamp (e.g., `14:18`)
- Did they find form immediately?
- Did they hesitate or look confused?
- Did they submit blank by mistake?
- Did they notice success confirmation?

**Post-task question**:
> "On a scale of 1 to 5, how confident are you that you completed that correctly?"

Record answer: `Confidence: 5`

**Check logs**: Open `data/metrics.csv`, verify new row with `task_code=T3_add, step=success, session_id=P1_7a9f`.

---

#### Task T1: Filter Tasks (120s limit)

**Facilitator reads**:
> "You've been asked to find all tasks containing the word 'report'. Use the filter to show only matching tasks, then count how many remain."

**Note-taker records**:
- How long to find filter box?
- Did they type "report" or "Report" (case sensitivity)?
- Did they notice result count indicator?
- Did they manually count items vs read count?

**Post-task question**: Confidence (1–5)

---

#### Task T2: Edit Task (90s limit)

**Facilitator reads**:
> "The task 'Submit invoices' has a typo. Change it to 'Submit invoices by Friday' and save the change."

**Note-taker records**:
- How quickly did they find Edit button?
- Any validation errors triggered?
- Did they verify edit saved?
- Any confusion about inline vs full-page edit?

**Post-task question**: Confidence (1–5)

---

#### Task T4: Delete Task (45s limit)

**Facilitator reads**:
> "The task 'Test entry' is no longer needed. Delete it."

**Note-taker records**:
- Confirmation dialog appeared? (HTMX)
- Did they expect confirmation?
- Did they verify deletion succeeded?

**Post-task question**: Confidence (1–5)

---

#### Debrief (3 min)

**Facilitator asks**:
1. "Which task felt most difficult?"
2. "Did anything surprise you or not work as expected?"
3. "Were there any points where you weren't sure if something had worked?"

**Record verbatim quotes** in notes:
```
Debrief P1:
- "T2 edit was hardest—I wasn't sure if it saved"
- "T1 filter was easy once I found the box"
- "Success messages were subtle, I had to look for them"
```

**Thank participant**:
> "That's really helpful, thank you. Your feedback will directly improve the prototype."

---

### Pilot 2: Keyboard-Only Variant

Repeat entire process with new participant, **new session ID** (e.g., `P2_4d8e`).

**Key difference**: Participant uses **Tab, Enter, Space only** (no mouse).

**Record variant** in consent log: `Variant: Keyboard-only, JS-on`

**Additional observations to capture**:
- Tab order logical?
- All interactive elements reachable?
- Focus visible on all stops?
- Any keyboard traps?
- Skip link working?

**Expected**: May be slower (tabbing takes time). Note accessibility issues (missing focus indicators, unreachable buttons).

---

### Pilot 3: No-JS Variant

**Key difference**: Disable JavaScript before starting.

**Setup**:
1. Chrome DevTools → Settings → Preferences → Disable JavaScript (checkbox)
2. Hard refresh (Ctrl+Shift+R / Cmd+Shift+R)

**Record variant**: `Variant: No-JS (JS-off)`

**Additional observations**:
- Full page reloads on every form submit?
- Error messages visible after redirect?
- PRG pattern working (refresh doesn't duplicate submission)?
- Confirmation missing for delete? (expected trade-off)

**Expected**: Slower task times (full page reloads). Verify `metrics.csv` shows `js_mode=off`.

---

### Pilots 4–6: Standard or Screen Reader

**Standard**: Repeat Pilot 1 process with new participants for more data points.

**Screen Reader** (if time permits):
- Participant uses NVDA (Windows) or Orca (Linux)
- Allow 2× time (SR navigation slower)
- Facilitator **silent** unless participant asks questions (talking competes with SR audio)

**SR-specific observations**:
- Are labels announced correctly?
- Are status messages announced (`role="status"`)?
- Are error messages linked to inputs (`aria-describedby`)?
- Can participant complete tasks independently?

**Record variant**: `Variant: Screen reader (NVDA), keyboard-only, JS-on`

---

### Between Pilots (5 min each)

1. **Save notes**: Copy notes to `wk09/lab-wk9/research/pilots/P1-notes.md` (or keep in single file with headings)
2. **Check logs**: Verify `metrics.csv` has rows for all completed tasks
3. **Swap roles**: Next person facilitates, previous facilitator becomes note-taker or participant
4. **Generate new session ID**: Never reuse (contaminates data)
5. **Clear browser state**: Delete cookies, close tabs, fresh start

---

### Facilitator Guidelines (Reference)

**Do**:
- Stay neutral (tone, facial expressions)
- Allow silence (don't fill pauses—people think quietly)
- Record exact quotes when possible
- Note timestamps for key events

**Don't**:
- Explain interface ("The filter is at the top")
- Justify design ("It's supposed to work like this")
- Lead participant ("Did you see the success message?")
- Show impatience (sighs, tapping, checking watch)

**If participant stuck (>3 min)**:
1. Ask diagnostic question: "What are you looking for?"
2. If still stuck: "Let's move to the next task"
3. Mark task as `completion=0`, note reason in pilot-notes

**If participant becomes frustrated**:
- Reassure: "This is really helpful feedback—it's the interface, not you."
- Offer break or stop session
- Never pressure to continue

---

✋ **Stop and check** (after completing pilots):
- [ ] 3–6 pilots completed with different variants
- [ ] Consent logged for each participant
- [ ] metrics.csv contains rows for all tasks (check session_ids)
- [ ] Pilot notes saved with timestamps, quotes, observations
- [ ] Subjective ratings captured (confidence 1–5)

---

## Activity C: Data Verification and Initial Analysis (15 min)

**Goal**: Ensure collected data is complete and usable before leaving lab.

### Step 1: Check metrics.csv completeness (5 min)

Open `data/metrics.csv` and verify:

**Expected structure**:
```csv
ts_iso,session_id,request_id,task_code,step,outcome,ms,http_status,js_mode
2025-10-15T14:18:23.456Z,P1_7a9f,r001,T3_add,success,,567,200,on
2025-10-15T14:19:45.789Z,P1_7a9f,r002,T1_filter,success,,1847,200,on
2025-10-15T14:21:12.123Z,P1_7a9f,r003,T2_edit,validation_error,blank_title,0,400,on
2025-10-15T14:21:34.456Z,P1_7a9f,r004,T2_edit,success,,1234,200,on
2025-10-15T14:22:10.789Z,P1_7a9f,r005,T4_delete,success,,210,200,on
```

**Checklist**:
- [ ] All session_ids present (P1, P2, P3, ...)
- [ ] All task_codes present (T1, T2, T3, T4) per session
- [ ] `step` values valid (success, validation_error, fail)
- [ ] Timestamps in chronological order
- [ ] `js_mode` matches variant (off for Pilot 3)
- [ ] Durations plausible (not negative, not absurdly high like 999999ms)

**If data missing**:
- Note in `wk09/lab-wk9/research/data-notes.md`:
  ```
  Pilot 2 (P2_4d8e): Task T4 not logged due to server restart. Used facilitator stopwatch time: 38s.
  ```

### Step 2: Calculate quick summary stats (5 min)

**Use spreadsheet or manual calculation**:

**Completion rates** (per task):
```
T1 (Filter):    5 success / 5 attempts = 100%
T2 (Edit):      4 success / 5 attempts = 80% (1 participant gave up)
T3 (Add):       5 success / 5 attempts = 100%
T4 (Delete):    5 success / 5 attempts = 100%
```

**Median times** (from `ms` column, filter `step=success`):
```
T1: [1847, 2103, 1654, 2345, 1899] → Median = 1899ms (~19s)
T2: [1234, 1567, 1123, 1890] → Median = 1400ms (~14s)
T3: [567, 432, 689, 543, 601] → Median = 567ms (~6s)
T4: [210, 198, 234, 221, 205] → Median = 210ms (~2s)
```

**Error rates**:
```
T2: 2 validation_error events / 6 total attempts = 33%
T3: 1 validation_error / 5 attempts = 20%
```

**Record in `wk09/lab-wk9/research/summary-stats.md`**:
```markdown
# Pilot Summary Stats (n=5)

## Completion Rates
| Task | Completion | Notes |
|------|-----------|-------|
| T1 (Filter) | 5/5 (100%) | All participants successful |
| T2 (Edit) | 4/5 (80%) | P3 gave up after 2 validation errors |
| T3 (Add) | 5/5 (100%) | 1 validation error (P2 submitted blank) |
| T4 (Delete) | 5/5 (100%) | No issues |

## Median Times (success only)
| Task | Median (ms) | Median (s) | Range |
|------|------------|------------|-------|
| T1 | 1899 | 19s | 16s–23s |
| T2 | 1400 | 14s | 11s–19s |
| T3 | 567 | 6s | 4s–7s |
| T4 | 210 | 2s | 2s–2.3s |

## Error Rates
| Task | Validation Errors | Rate | Notes |
|------|------------------|------|-------|
| T2 | 2 errors (P2, P3) | 33% | Blank title submitted |
| T3 | 1 error (P2) | 20% | Blank title submitted |

## JS-On vs JS-Off (T3 comparison)
- JS-on (n=4): Median 567ms
- JS-off (n=1, P3): 3456ms (full page reload)
- **Difference**: ~6× slower without JS (expected)
```

### Step 3: Flag anomalies (5 min)

**Look for**:
- Outliers: Times >3× median (distraction? confusion? technical issue?)
- Missing data: Tasks with no log entries
- Impossible values: Negative times, empty session_ids
- Inconsistencies: `js_mode=on` for Pilot 3 (should be `off`)

**Document in data-notes.md**:
```markdown
# Data Quality Notes

## Anomalies
- Pilot 1, T1: Duration 1847ms is within normal range ✓
- Pilot 3, T3: Duration 3456ms (no-JS) is expected (full page reload) ✓
- Pilot 4, T2: Missing log entry—server crashed, used stopwatch: 17s

## Exclusions
- None (all data usable)

## Notes
- P3 (no-JS) consistently slower—confirms dual-path performance difference
- P2 triggered 2 validation errors (blank submissions)—possible focus management issue
```

✋ **Stop and check**:
- [ ] metrics.csv verified complete
- [ ] Summary stats calculated (completion, median times, error rates)
- [ ] Anomalies documented
- [ ] Data quality acceptable for Week 10 analysis

---

## Activity D: Translate Findings to Backlog (20 min)

**Goal**: Create evidence chains from pilot observations to actionable backlog items.

### Step 1: Review pilot notes (5 min)

**Read through all pilot notes** (`pilots/P1-notes.md`, etc.) and highlight:
- **Accessibility issues**: SR didn't announce, keyboard trap, missing label
- **Usability issues**: Confusion, hesitation, unexpected behaviour
- **Error patterns**: Multiple participants hit same validation error
- **Positive observations**: What worked well (keep in redesign)

**Example notes**:
```markdown
## Pilot 1 (P1_7a9f)
- 14:18 T3: Participant hesitated before clicking "Add Task" button—unsure if Enter would work
- 14:19 T1: Typed "report", waited, then said "is it filtering automatically?"—expected to click button
- 14:21 T2: Validation error (blank submission), participant said "I didn't see an error message"
- 14:22 T4: Delete confirmation dialog appeared, participant confirmed without hesitation ✓

## Pilot 2 (P2_4d8e, keyboard-only)
- 14:35 T3: Tab order correct, focus visible ✓
- 14:37 T1: Result count not announced by screen reader (tested with NVDA for demo) ✗
- 14:39 T2: Blank submission error—SR didn't announce error message ✗
- 14:40 T4: Delete button accessible, confirmation worked ✓

## Pilot 3 (P3_1f2a, no-JS)
- 15:00 T3: Full page reload after submit—slower but functional ✓
- 15:02 T1: Filter worked with form submit—no issues ✓
- 15:04 T2: Gave up after 2 validation errors—error summary not focusable ✗
- 15:05 T4: No confirmation dialog (expected trade-off), task completed ✓
```

### Step 2: Identify themes (5 min)

**Group similar issues**:

| Theme | Code | Observations |
|-------|------|--------------|
| **Status feedback** | sr-announcement | P1: "didn't see error", P2: SR didn't announce, P3: error not focusable |
| **Filter expectations** | ux-expectation | P1: expected button, not auto-filter |
| **Validation errors** | error-handling | P2, P3: blank submissions frequent |
| **Keyboard accessibility** | a11y-keyboard | P2: tab order good ✓ |
| **No-JS parity** | parity-nojs | P3: slower but functional ✓ |

### Step 3: Create backlog items (10 min)

**Open `backlog/backlog.csv`** and add entries for significant issues.

**Template**:
```csv
id,week,priority,category,description,wcag,status,evidence,mitigation,candidate_fix
```

**Example entries**:

```csv
wk9-01,9,high,a11y,"Validation errors not announced by screen readers",4.1.3,open,"data/metrics.csv#P2_4d8e T2 validation_error; pilots/P2-notes.md L12; pilots/P3-notes.md L8","Add role=alert to error messages, aria-describedby for input association",true

wk9-02,9,medium,ux,"Filter auto-search confuses some participants (expected button)",,"open","pilots/P1-notes.md L6","Consider adding visible 'Apply' button or help text",false

wk9-03,9,high,a11y,"Error summary not keyboard-focusable in no-JS mode",3.2.1,open,"pilots/P3-notes.md L10; data/metrics.csv#P3_1f2a T2 fail","Add tabindex=-1 to error summary, focus on page load",true

wk9-04,9,low,ux,"Delete confirmation missing in no-JS (documented trade-off)",3.3.4,open,"pilots/P3-notes.md L12; wk08/docs/prototyping-constraints.md L78","Consider adding /tasks/{id}/delete/confirm page",false

wk9-05,9,high,a11y,"Result count after filter not announced to SR",4.1.3,open,"pilots/P2-notes.md L8","Move result count into live region (role=status)",true
```

**Key fields**:
- **id**: Unique identifier (wk9-01, wk9-02, ...)
- **priority**: high (blocks task completion or WCAG A/AA violation), medium (impacts efficiency), low (nice-to-have)
- **wcag**: Reference if applicable (3.3.1, 4.1.3, etc.)
- **evidence**: Direct links to data sources (file paths, line numbers, session IDs)
- **mitigation**: Proposed fix (specific, actionable)
- **candidate_fix**: `true` if you plan to implement in Week 10 (limit to 2-3)

✋ **Stop and check**:
- [ ] Pilot notes reviewed and themes identified
- [ ] backlog.csv updated with evidence-linked items
- [ ] Priority set based on inclusion impact + frequency
- [ ] 2-3 items marked as candidate fixes for Week 10

---

## Activity E: Assemble Assessment Draft Pack (20 min)

**Goal**: Create a complete evidence pack for assessment submission (to be refined and submitted by end Week 10).

**Directory structure**: `wk09/assessment/`

### Step 1: Copy evaluation plan materials (5 min)

**Files to include**:

1. **`01-evaluation-plan.md`**: Copy from `wk09/lab-wk9/research/tasks.md` + `measures.md`
   - Task definitions
   - Metrics definitions
   - Success criteria

2. **`02-protocol.md`**: Copy from `wk09/lab-wk9/research/protocol.md`
   - Consent process
   - Session flow
   - Facilitator guidelines
   - Ethical considerations

3. **`03-consent-log.md`**: Copy from `wk09/lab-wk9/research/consent-log.md`
   - Participant codes
   - Session IDs
   - Variants tested
   - Consent confirmation

### Step 2: Include quantitative data (5 min)

**Create `04-results.csv`**:

Option A: Copy relevant rows from `data/metrics.csv`:
```bash
grep -E "P1_|P2_|P3_|P4_|P5_" data/metrics.csv > wk09/assessment/04-results.csv
```

Option B: Symbolic link (keeps data in one place):
```bash
ln -s ../../../data/metrics.csv wk09/assessment/04-results.csv
```

**Include README.md** explaining columns:
```markdown
# Results Data

**File**: `04-results.csv`

**Columns**:
- `ts_iso`: Event timestamp (ISO 8601 UTC)
- `session_id`: Anonymous participant identifier (P1_7a9f, etc.)
- `request_id`: Request trace ID
- `task_code`: Task identifier (T1_filter, T2_edit, T3_add, T4_delete)
- `step`: Event type (success, validation_error, fail)
- `outcome`: Specific error type (blank_title, max_length, etc.)
- `ms`: Duration in milliseconds
- `http_status`: HTTP response code (200, 400, 500)
- `js_mode`: JavaScript availability (on, off)

**Sessions**:
- P1_7a9f: Standard (HTMX, mouse, JS-on)
- P2_4d8e: Keyboard-only (JS-on)
- P3_1f2a: No-JS (JS-off)
- P4_8c3b: Standard (HTMX, mouse, JS-on)
- P5_2a7d: Standard (HTMX, mouse, JS-on)

**Analysis**: See `05-findings.md` for summary statistics and interpretation.
```

### Step 3: Document findings (5 min)

**Create `05-findings.md`** summarizing key issues with evidence chains and WCAG references (see Week 9 Lab 1 for detailed example format).

### Step 4: Collect evidence artefacts (5 min)

**Create `06-evidence/` directory**:

```
wk09/assessment/06-evidence/
├── screenshots/
│   ├── t2-validation-error-nojs.png
│   ├── t1-filter-results.png
│   └── annotations.md (descriptions + alt text)
├── pilot-notes/
│   ├── P1-notes.md
│   ├── P2-notes.md
│   └── P3-notes.md
└── consent-log.md
```

**Remove any PII** from screenshots and notes.

✋ **Stop and check**:
- [ ] Evaluation plan + protocol copied to assessment/
- [ ] Quantitative data (metrics.csv) included or linked
- [ ] Findings document complete with statistics + evidence chains
- [ ] Evidence artefacts collected (screenshots, notes)
- [ ] All files sanitized (no PII)

---

## Commit & Reflect (10 min)

### Commit message

```bash
git add data/metrics.csv backlog/backlog.csv wk09/research wk09/assessment

git commit -m "$(cat <<'EOF'
wk9s2: completed peer pilots (n=5), assembled assessment draft pack

- Conducted 5 peer pilots: 3 standard (HTMX), 1 keyboard-only, 1 no-JS
- Collected quantitative data: completion rates (80-100%), median times (2s–19s), error rates (20-33% for T2/T3)
- Captured qualitative observations: validation error accessibility issues, status feedback insufficient, filter UX expectations
- Verified no-JS parity: functional but 6× slower (expected)
- Created evidence chains: raw data → findings → backlog items (wk9-01 to wk9-05)
- Assembled assessment draft pack: plan, protocol, results.csv, findings.md, evidence artefacts
- Identified Priority 1 fixes for Week 10: validation error accessibility (role=alert, aria-describedby, focusable summary)

Key findings:
- Validation errors not accessible to SR users (WCAG 4.1.3 violation)
- Error summary not keyboard-focusable in no-JS mode (WCAG 3.2.1)
- Status messages too subtle (affects confidence)
- High error rate on T2 edit (33%—blank submissions due to focus management)

Ready for Week 10 analysis and redesign.


EOF
)"
```

### Reflection questions

**Answer in `wk09/reflection.md`**:

1. **Pilot experience**: What surprised you most during pilots? Did participants struggle where you expected, or were there unexpected issues?

2. **Ethical practice**: How comfortable were participants? Did consent process feel sufficient? Any near-misses on PII collection?

3. **Data quality**: How confident are you in the quantitative data (metrics.csv)? Any concerns about accuracy, completeness, or bias?

4. **Qualitative insights**: What did observation reveal that logs couldn't capture? How valuable was think-aloud (if used)?

5. **Accessibility impact**: Which findings have highest inclusion impact? How would you prioritise fixes if you could only do one?

6. **Week 10 readiness**: What are your Priority 1 fixes? How will you verify they worked?

---

## Looking Ahead: Week 10 Analysis & Redesign

Next week:
- **Lab 1**: Analyse metrics in depth (median, MAD, error rates), prioritise backlog with inclusion × impact scores, plan redesign
- **Lab 2**: Implement Priority 1-2 fixes, re-verify accessibility, update backlog, finalise assessment submission

**Before Week 10**:
- Review [Evaluation Metrics Quick Reference](../references/evaluation-metrics-quickref.md) for analysis formulas
- Refresh WCAG 2.2 guidelines for fixes (3.3.1, 4.1.3)
- Think about trade-offs: which fixes are quick wins vs major refactors?

---

## Further Reading & Resources

### Essential
- Review Week 9 Lab 1 (evaluation planning) for context
- [Evaluation Metrics Quick Reference](../references/evaluation-metrics-quickref.md) — Median, MAD, error rate formulas
- [GOV.UK: Analysing user research](https://www.gov.uk/service-manual/user-research/analyse-a-research-session)

### HCI Evaluation
- [Nielsen: How to Conduct a Usability Test](https://www.nngroup.com/articles/usability-testing-101/)
- [Lazar et al.: Research Methods in HCI](https://dl.acm.org/doi/book/10.5555/2737875) — Chapters 9-10 (qualitative methods)
- [Measuring UX](https://measuringux.com/) — Quantitative analysis techniques

### Qualitative Analysis
- [Braun & Clarke: Thematic Analysis](https://www.tandfonline.com/doi/abs/10.1191/1478088706qp063oa)
- [GOV.UK: Analysing qualitative data](https://www.gov.uk/service-manual/user-research/analyse-a-research-session)

### Ethics
- Review `references/consent-pii-faq.md`
- [BPS Code of Ethics](https://www.bps.org.uk/guideline/code-ethics-and-conduct)

---

## Glossary Summary

| Term | One-line definition |
|------|---------------------|
| **Pilot study** | Small-scale preliminary study to test protocol and gather initial data |
| **Quantitative data** | Numerical measurements (times, counts, percentages); objective, statistical |
| **Qualitative data** | Non-numerical observations (quotes, behaviours, patterns); subjective, interpretive |
| **Think-aloud protocol** | Participants verbalize thoughts while completing tasks; reveals mental models |
| **Evidence chain** | Traceability from raw data → finding → backlog item → fix → verification |
| **Thematic coding** | Systematic process of identifying patterns (themes) in qualitative data |
| **Median** | Middle value in sorted dataset; resistant to outliers |
| **MAD** | Median Absolute Deviation; robust measure of spread |
| **Completion rate** | Proportion of participants who successfully complete a task |
| **Error rate** | Proportion of attempts that trigger validation errors |

---

**Lab complete!** You have real pilot data, evidence chains, and a draft assessment pack. Week 10 will analyse this data rigorously, implement prioritised fixes, and finalise your assessment submission (due end Week 10).
