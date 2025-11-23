# Week 9 • Lab 1 — Evaluation Plan, Metrics Schema, and Instrumentation

![COMP2850](https://img.shields.io/badge/COMP2850-HCI-blue)
![Week 9](https://img.shields.io/badge/Week-9-orange)
![Lab 1](https://img.shields.io/badge/Lab-1-green)
![Status](https://img.shields.io/badge/Status-Draft-yellow)



---

## Before Lab: Required Reading (20 mins)

📖 **Essential**
- Pull Week 9 starter repo branch (baseline instrumentation stubs):
- [Nielsen Norman Group: How to Conduct a Usability Test](https://www.nngroup.com/articles/usability-testing-101/)
- [W3C: Measuring Accessibility](https://www.w3.org/WAI/test-evaluate/metrics/)
- Review [Evaluation Metrics Quick Reference](../references/evaluation-metrics-quickref.md)
- Review [Consent and PII FAQ](../references/consent-pii-faq.md)

📖 **Contextual**:
- [hypermedia.systems: Instrumentation](https://hypermedia.systems/) (optional chapter if available)
- [GOV.UK: Planning user research](https://www.gov.uk/service-manual/user-research/plan-a-research-session)

---

## Introduction: From Prototype to Evidence

Weeks 6–8 built a functional, accessible task list prototype. **Now the critical question**: Does it actually work for real people?

**HCI is empirical**. We can't claim "this is usable" without evidence. This week you:
1. Design an evaluation plan (what to measure, how)
2. Instrument your prototype to capture objective data
3. Prepare for peer pilots (Week 9 Lab 2)

**Why this matters**:
- **Assessment** requires quantitative data (completion times, error rates) + qualitative insights
- **Week 10 redesign** depends on identifying real bottlenecks (not guesses)
- **Assessment portfolio (due end Week 10)** needs evidence chains: problem → measurement → fix → verification
- **Industry practice**: Product decisions backed by data, not opinions

**Ethical imperative**: Evaluation must respect privacy. We follow **low-risk peer study protocols**—no recordings, no PII, informed consent, opt-out honoured.

---

## Learning Focus

### Lab Objectives

> **Staff reference**: Full instrumentation implementation lives in the [solution repository](../../resources/code-resources.md#week-9).
By the end of this session, you will have:
- Designed task-based evaluation protocol with 3+ tasks and clear success criteria
- Defined metrics (time-on-task, errors, SUS, confidence)
- Written an ethical, repeatable protocol for peer pilots
- Instrumented codebase to capture metrics (server-side logging)
- Verified instrumentation captures data correctly for JS-on and JS-off paths

### Learning Outcomes Addressed
This lab contributes to the following module Learning Outcomes ([full definitions](../references/learning-outcomes.md)):

- **LO1**: Differentiate people-centred methods — evidenced by method selection rationale
- **LO8**: Design and execute evaluation — evidenced by protocol + metrics + instrumentation
- **LO13**: Integrate HCI with SE — evidenced by server-side instrumentation code

---

## Key Concepts

### Usability Evaluation

> **Usability Evaluation** [GLOSSARY]
>
> Systematic assessment of how well people can use a system to achieve goals. **Formative** (improve design during development) vs **Summative** (measure final quality).
>
> **This module uses formative evaluation**: gather data during development (Week 9), redesign (Week 10), verify improvements (Week 10/11).
>
> **Components**:
> - **Tasks**: Realistic scenarios with measurable outcomes
> - **Metrics**: Objective (time, errors) + subjective (satisfaction, confidence)
> - **Participants**: Peers, representative of target population
> - **Protocol**: Step-by-step procedure ensuring consistency
>
> **HCI Connection**: ISO 9241-11 defines usability as "extent to which a product can be used by specified users to achieve specified goals with effectiveness, efficiency, and satisfaction."
>
> **Academic rigour**: Evaluation design determines validity of findings. Poor tasks → biased data → wrong redesign decisions.
>
> 🔗 [ISO 9241-11:2018 Usability](https://www.iso.org/standard/63500.html)
> 🔗 [Nielsen: Usability 101](https://www.nngroup.com/articles/usability-101-introduction-to-usability/)

### Task-Based Evaluation

> **Task-Based Evaluation** [GLOSSARY]
>
> Participants complete realistic tasks while researchers observe and measure. Contrasts with feature walkthroughs or preference surveys.
>
> **Task characteristics**:
> - **Realistic**: Matches actual use context ("Find invoices" not "Use filter")
> - **Measurable**: Clear success condition (found correct result, completed within time)
> - **Scoped**: Completable in 2-5 minutes per task
> - **Representative**: Covers critical flows identified in backlog
>
> **Example**:
> ```
> Task T1: Search for Tasks
> Scenario: "You need to find all tasks containing the word 'invoice'.
>            Use the filter to show only matching tasks and count how many remain."
> Success: Reports correct count within 2 minutes, no validation errors
> ```
>
> **Why not feature-based?** "Test the filter" is vague—participants don't know when they've succeeded. Task-based evaluation mirrors real-world goals.
>
> **HCI Connection**: Tasks ground evaluation in **ecological validity**—findings generalize to real use.
>
> 🔗 [Nielsen: Task Scenarios for Usability Testing](https://www.nngroup.com/articles/task-scenarios-usability-testing/)

### Objective vs Subjective Metrics

> **Objective Metrics** [GLOSSARY]
>
> Measurable, observable data: completion time, error count, HTTP status codes. **No interpretation required**.
>
> **Examples**:
> - **Time-on-task**: Milliseconds from task start to completion (server-timed)
> - **Completion rate**: Did participant achieve goal? (0 = fail, 1 = success)
> - **Error rate**: `validation_error / (success + validation_error)`
> - **Click/keystroke count**: Efficiency measure (lower is better for same outcome)
>
> **Benefits**: Repeatable, comparable across participants, statistically analysable
> **Limitations**: Don't capture frustration, confusion, satisfaction
>
> ---
>
> **Subjective Metrics** [GLOSSARY]
>
> Self-reported feelings, perceptions, attitudes. Require interpretation.
>
> **Examples**:
> - **Confidence rating**: "How confident are you that you completed the task correctly?" (1–5 scale)
> - **Difficulty rating**: "How difficult was this task?" (1–7 scale)
> - **Satisfaction**: Post-session questionnaire (SUS, UMUX-Lite)
> - **Qualitative notes**: Open-ended feedback ("I wasn't sure if it saved")
>
> **Benefits**: Capture affective response, uncover unexpected issues
> **Limitations**: Vary by person, memory biases, social desirability
>
> **HCI Connection**: Both required. ISO 9241-11 defines usability as **effectiveness** (objective: completion), **efficiency** (objective: time), and **satisfaction** (subjective).
>
> 🔗 [Measuring UX](https://measuringux.com/) — Quantitative UX metrics handbook

### Server-Side Instrumentation

> **Server-Side Instrumentation** [GLOSSARY]
>
> Logging application events at the server (not client). Data captured regardless of JavaScript availability.
>
> **Why server-side?**
> - **Reliability**: Can't be blocked by ad blockers, disabled JS, browser crashes
> - **Privacy**: No third-party analytics (e.g., Google Analytics) that track across sites
> - **Parity**: Same data structure for HTMX and no-JS paths
> - **Control**: You own the data (no GDPR concerns with external processors)
>
> **Pattern**:
> ```kotlin
> post("/tasks/{id}/edit") {
>     val start = System.currentTimeMillis()
>     // ... validation, update logic ...
>     val duration = System.currentTimeMillis() - start
>     Logger.write(session, reqId, "T2_edit", "success", "", duration, 200, jsMode)
> }
> ```
>
> **Log structure** (CSV):
> ```csv
> ts_iso,session_id,request_id,task_code,step,outcome,ms,http_status,js_mode
> 2025-10-13T14:23:01Z,abc123,r001,T1_filter,success,,1847,200,on
> 2025-10-13T14:23:15Z,abc123,r002,T2_edit,validation_error,blank_title,234,400,on
> ```
>
> **HCI Connection**: Instrumentation enables **empirical HCI**—quantify behaviour at scale (beyond 5 pilot peers).
>
> 🔗 [Kohavi et al.: Online Controlled Experiments](https://www.exp-platform.com/) — Industry practice

### Privacy by Design (Revisited)

> **Privacy by Design** [GLOSSARY]
>
> Build data minimization and privacy into system architecture (not bolt on later).
>
> **Week 9 requirements**:
> - **Anonymous session IDs**: Random tokens (e.g., `sid=X7kL9p`), not names/emails
> - **No PII in logs**: No IP addresses, device fingerprints, real names
> - **Opt-out mechanism**: Participants can request data deletion (delete rows matching session_id)
> - **Local storage**: Logs stay in private repo, not synced to public forks
> - **Consent clarity**: Protocol explains what's logged and why
>
> **Example — BAD**:
> ```csv
> timestamp,email,task,duration
> 2025-10-13,alice@leeds.ac.uk,T1,1800
> ```
> ❌ Email is PII, violates GDPR
>
> **Example — GOOD**:
> ```csv
> ts_iso,session_id,request_id,task_code,step,outcome,ms,http_status,js_mode
> 2025-10-13T14:23:01Z,X7kL9p,r001,T1_filter,success,,1800,200,on
> ```
> ✅ Anonymous, minimal, fit-for-purpose
>
> **UK context**: Data Protection Act 2018 + UK GDPR require **lawful basis** for processing. Low-risk peer studies at universities typically use "legitimate interest" or "consent" basis. **Must document** in protocol.
>
> 🔗 Review [Consent and PII FAQ](../references/consent-pii-faq.md) for full guidance
> 🔗 [ICO: Privacy by Design](https://ico.org.uk/for-organisations/uk-gdpr-guidance-and-resources/accountability-and-governance/guide-to-accountability-and-governance/accountability-and-governance/data-protection-by-design-and-default/)

### Median vs Mean

> **Median** [GLOSSARY]
>
> Middle value in sorted dataset. **Resistant to outliers**.
>
> **Example**: Task completion times [10s, 12s, 15s, 18s, 120s]
> - Mean = (10+12+15+18+120)/5 = **35s** ← skewed by 120s outlier
> - Median = **15s** ← middle value, represents typical experience
>
> **Why use median in HCI?** Completion times often have outliers (someone distracted, interrupted, confused). Median tells you what most people experienced.
>
> **Median Absolute Deviation (MAD)**: Robust measure of spread.
> ```
> MAD = median(|x_i - median(x)|)
> ```
> Less sensitive to outliers than standard deviation.
>
> **HCI Connection**: Report median + MAD for timing data. Use mean for Likert scales (confidence ratings) where outliers are meaningful.
>
> 🔗 [Measuring UX: Why Median?](https://measuringux.com/median/)

---

## Activity A: Define Evaluation Tasks (30 min)

**Goal**: Create 3-4 realistic tasks that cover critical flows and accessibility concerns.

### Step 1: Review backlog and audit findings (10 min)

Open `backlog/backlog.csv` and identify:
- High-priority features (create, edit, delete, filter)
- Accessibility fixes from Week 7 (inline edit, status announcements)
- No-JS parity concerns from Week 8

**Example backlog snippet**:
```csv
id,week,priority,category,description,wcag,status
wk7-03,7,high,a11y,"Status messages not announced in no-JS mode",4.1.3,fixed
wk8-01,8,high,parity,"Delete confirmation missing in no-JS",3.3.4,open
wk8-05,8,medium,ux,"Filter doesn't show 'no results' message",3.3.1,open
```

**Identify task candidates**:
- **T1**: Filter tasks (tests search, result announcement, no-results state)
- **T2**: Edit task inline (tests validation, focus management, status announcement)
- **T3**: Add task (tests create flow, PRG, error handling)
- **T4**: Delete task (tests confirmation, dual-path, status)

### Step 2: Write task scenarios (15 min)

**Create `wk09/lab-wk9/research/tasks.md`**:

```markdown
# Evaluation Tasks — Week 9

## Task T1: Filter Tasks

**Scenario**:
"You've been asked to find all tasks containing the word 'report'. Use the filter box to show only matching tasks, then count how many tasks remain."

**Setup**:
- Pre-populate task list with 10 tasks, 3 containing "report" in title
- Example: "Submit expense report", "Draft annual report", "Review quarterly report", plus 7 others

**Success criteria**:
- Participant uses filter box (types "report")
- Participant reports correct count (3 tasks)
- Completed within 2 minutes
- No validation errors

**Metrics**:
- Time from page load to stating count (ms)
- Completion (0 = fail, 1 = success)
- Validation errors (count)
- Confidence rating (1–5): "How confident are you that you found all matching tasks?"

**Accessibility checks**:
- Result count announced by screen reader?
- Keyboard-only completion possible?
- Works with JS disabled?

---

## Task T2: Edit Task Title

**Scenario**:
"The task 'Submit invoices' has a typo. Change it to 'Submit invoices by Friday' and save the change."

**Setup**:
- Task ID 5: "Submit invoices" (visible in list)
- Participant must click Edit, change text, save

**Success criteria**:
- Participant activates edit mode
- Participant updates title correctly
- Change persists after save
- Completed within 90 seconds
- No validation errors

**Metrics**:
- Time from click Edit to save confirmation (ms)
- Completion (0/1)
- Validation errors (e.g., blank title submitted by mistake)
- Confidence rating (1–5)

**Accessibility checks**:
- Status message "Updated [title]" announced?
- Focus remains on/near edited task?
- Works with keyboard only?
- Works with JS disabled?

---

## Task T3: Add New Task

**Scenario**:
"You need to remember to 'Call supplier about delivery'. Add this as a new task."

**Setup**:
- Empty or partially filled task list
- Form visible at top of page

**Success criteria**:
- Participant types exact title (or close match)
- Submits form
- New task appears in list
- Completed within 60 seconds

**Metrics**:
- Time from focus in input to confirmation (ms)
- Completion (0/1)
- Validation errors (if they submit blank by accident)
- Confidence rating (1–5)

**Accessibility checks**:
- Status message "Added [title]" announced?
- Form remains usable after error (if triggered)?
- Works with JS disabled (PRG)?

---

## Task T4: Delete Task

**Scenario**:
"The task 'Test entry' is no longer needed. Delete it from the list."

**Setup**:
- Task ID 8: "Test entry" (visible in list)

**Success criteria**:
- Participant clicks Delete button
- Confirms deletion (HTMX path) or submits form (no-JS)
- Task removed from list
- Completed within 45 seconds

**Metrics**:
- Time from click Delete to confirmation (ms)
- Completion (0/1)
- Confirmation dialog acknowledged (HTMX only)
- Confidence rating (1–5)

**Accessibility checks**:
- Delete button has accessible name ("Delete task: Test entry")?
- Status message "Deleted [title]" announced (HTMX)?
- Works with keyboard only?
- Works with JS disabled (no confirmation, but functions)?

---

## Task Order

**Recommended sequence**:
1. **Warm-up** (not timed): "Browse the task list and familiarize yourself with the interface."
2. T3 (Add) — Low cognitive load, builds confidence
3. T1 (Filter) — Medium complexity, tests search
4. T2 (Edit) — Tests inline interaction, validation
5. T4 (Delete) — Destructive action, tests confirmation
6. **Debrief** (qualitative): Open-ended questions

**Counterbalance** if testing multiple participants: alternate T1/T2 order to avoid learning effects.

---

## Notes for Facilitator

- **Do not help** unless participant is completely stuck (>3 min). Note as "facilitated" in observations.
- **Think-aloud optional**: Ask participants to narrate their thoughts if comfortable. Don't force.
- **Screen reader users**: Allow extra time for navigation. Log SR-specific observations separately.
- **Keyboard-only**: Offer keyboard-only variant to 1-2 participants for comparison.
- **No-JS**: Test at least 1 participant with JS disabled to verify parity.

---

## Success Definitions

**Completion codes**:
- `1` = Task fully completed, correct outcome
- `0.5` = Partial completion (e.g., found filter but wrong count)
- `0` = Failed or abandoned

**Time bounds**:
- T1: 120s
- T2: 90s
- T3: 60s
- T4: 45s

If participant exceeds time, prompt: "Would you like to continue, or shall we move to the next task?"
```

### Step 3: Validate tasks with team (5 min)

Walk through each task with your pair/team:
- Are scenarios realistic?
- Are success criteria measurable?
- Do they cover your backlog priorities?
- Can they be completed in allocated time?

Adjust wording if anything is ambiguous.

✋ **Stop and check**:
- [ ] 3-4 tasks defined with clear scenarios
- [ ] Success criteria objective and measurable
- [ ] Metrics list includes objective + subjective
- [ ] Accessibility checks specified per task
- [ ] Task order and timing planned

---

## Activity B: Define Metrics and Measures (20 min)

**Goal**: Specify exactly how you'll calculate each metric. Prevents ambiguity during analysis (Week 10).

**Create `wk09/lab-wk9/research/measures.md`**:

```markdown
# Metrics Definitions — Week 9

Reference: [Evaluation Metrics Quick Reference](../references/evaluation-metrics-quickref.md)

---

## Objective Metrics

### 1. Completion Rate

**Definition**: Proportion of participants who successfully complete the task.

**Calculation**:
```
Completion rate = (# successes) / (# attempts)
```

**Data source**: Manual observation + server logs (look for `step=success`)

**Reporting**: Percentage per task (e.g., "T1: 4/5 = 80%")

**Split by**:
- JS-on vs JS-off
- Keyboard-only vs mouse
- Screen reader vs visual

---

### 2. Time-on-Task

**Definition**: Duration from task start to completion or abandonment.

**Calculation**:
- **Server-timed**: `ms` column in metrics.csv (start to success event)
- **Backup**: Facilitator stopwatch (start when participant reads scenario, stop when they say "done")

**Reporting**:
- **Median** (primary): Middle value, resistant to outliers
- **MAD**: Median absolute deviation for spread
- **Range**: Min-max for context

**Example**:
```
T1 (Filter):
  Median: 24s
  MAD: 6s
  Range: 12s–58s (n=5)
```

**Split by**: JS-on vs JS-off (expect no-JS to be slower due to full page reloads)

---

### 3. Error Rate

**Definition**: Proportion of attempts that trigger validation errors.

**Calculation**:
```
Error rate = (# validation_error events) / (# total attempts)
```

**Data source**: `data/metrics.csv` where `step=validation_error`

**Reporting**: Percentage per task + qualitative notes on error type

**Example**:
```
T3 (Add Task):
  Error rate: 2/5 = 40%
  Errors: 1× blank title, 1× exceeded max length
```

**HCI insight**: High error rates → poor affordances, unclear constraints, or accessibility issues

---

### 4. Validation Error Count

**Definition**: Number of validation errors per participant per task.

**Calculation**: Count rows in `metrics.csv` with `step=validation_error` for given session + task

**Reporting**: Mean errors per task

**Example**:
```
T2 (Edit):
  Mean errors per participant: 0.4 (2 errors across 5 participants)
```

---

## Subjective Metrics

### 5. Confidence Rating

**Definition**: Self-reported confidence that task was completed correctly.

**Scale**: 1 (not at all confident) → 5 (very confident)

**Collection method**: Ask immediately after each task:
> "On a scale of 1 to 5, how confident are you that you completed that task correctly?"

**Reporting**:
- Mean + standard deviation
- Distribution (how many rated 1, 2, 3, 4, 5)

**Example**:
```
T1 (Filter):
  Mean confidence: 4.2 ± 0.8
  Distribution: 0×1, 0×2, 1×3, 2×4, 2×5
```

**HCI insight**: Low confidence despite successful completion → interface doesn't provide sufficient feedback

---

### 6. Difficulty Rating (optional)

**Definition**: Perceived difficulty of task.

**Scale**: 1 (very easy) → 7 (very difficult)

**Collection method**: Post-task question:
> "How difficult was that task?"

**Reporting**: Mean ± SD per task

---

### 7. Post-Session Satisfaction (optional)

**Method**: 2-question UMUX-Lite (if time permits):
1. "This system's capabilities meet my requirements" (1–7: strongly disagree → strongly agree)
2. "This system is easy to use" (1–7: strongly disagree → strongly agree)

**Calculation**: Average of the two responses (higher = better perceived usability)

**Reporting**: Mean score across all participants

**Note**: UMUX-Lite takes <30 seconds, validated proxy for SUS (System Usability Scale)

---

## Qualitative Observations

### 8. Facilitator Notes

**Capture**:
- Hesitations ("Participant paused 10s before clicking filter")
- Verbalizations ("I'm not sure if it saved")
- Accessibility issues ("Screen reader didn't announce result count")
- Workarounds ("Used Ctrl+F instead of built-in filter")

**Format**: Timestamped notes in `pilot-notes.md`

**Analysis**: Thematic coding in Week 10 (group similar issues, link to backlog items)

---

## Accessibility-Specific Metrics

### 9. Keyboard-Only Completion

**Definition**: Can task be completed using only keyboard (no mouse)?

**Measurement**: Binary per task (yes/no)

**Reporting**: "T1: Keyboard-accessible ✅" or "T3: Tab order broken, failed ✗"

---

### 10. Screen Reader Announcement Quality

**Definition**: Are status messages and result counts announced appropriately?

**Measurement**: Qualitative note per task (announced / not announced / partial)

**Reporting**: List issues with WCAG references (4.1.3 Status Messages)

---

## Data Integrity Checks

Before analysis (Week 10):
- **Completeness**: All tasks have `session_id`, `task_code`, `step`
- **Plausibility**: Times within expected ranges (12s–120s for T1)
- **Consistency**: JS-mode matches observed condition
- **Outliers**: Flag times >3× median for review

Document any anomalies in `wk09/lab-wk9/research/data-notes.md`

---

## Summary Table

| Metric | Type | Source | Calculation | Reporting |
|--------|------|--------|-------------|-----------|
| Completion rate | Objective | Manual + logs | successes / attempts | % per task |
| Time-on-task | Objective | Server logs | Median + MAD | Seconds |
| Error rate | Objective | Server logs | errors / attempts | % per task |
| Confidence | Subjective | Post-task question | Mean ± SD | 1–5 scale |
| Facilitator notes | Qualitative | Manual observation | Thematic coding | Categories |
| KB-only completion | Accessibility | Manual test | Binary | ✅/✗ |
| SR announcements | Accessibility | SR observation | Qualitative | Issues list |
```

✋ **Stop and check**:
- [ ] Every metric has clear definition
- [ ] Calculation methods specified
- [ ] Data sources identified (logs vs manual)
- [ ] Reporting format decided (median vs mean, etc.)
- [ ] Accessibility metrics included

---

## Activity C: Write Ethical Protocol (25 min)

**Goal**: Document the procedure so it's repeatable and ethical.

**Create `wk09/lab-wk9/research/protocol.md`**:

```markdown
# Peer Pilot Protocol — Week 9

## Study Overview

**Purpose**: Evaluate usability and accessibility of task list prototype with peer participants.

**Type**: Low-risk formative evaluation, peer-to-peer within module.

**Scope**:
- 5–6 participants (lab pairs)
- 4 tasks per session (~15–20 minutes)
- No audio/video recording
- No personally identifiable information collected

**Ethical approval**: Covered by module's blanket low-risk consent for peer learning activities (verified with module leader).

**Data retention**: Anonymised logs stored in private repo for academic year, deleted after module assessment complete.

---

## Participant Requirements

**Inclusion**:
- Enrolled in COMP2850
- Comfortable using web browsers
- Able to provide informed consent

**Exclusion**:
- None (module is inclusive by design)

**Accessibility accommodations**:
- Screen reader users: allowed extra time, SR-specific observations recorded separately
- Keyboard-only users: explicitly invited to test no-mouse variant
- No-JS users: at least one session conducted with JS disabled

---

## Consent Process

**Before starting** (read aloud):

> "Thanks for agreeing to pilot our prototype. This is a quick usability test—about 15 minutes. I'll ask you to complete 4 tasks while I observe and take notes. I'm testing the interface, not you, so there are no wrong answers.
>
> **What we're collecting**:
> - Task completion times (from server logs)
> - Whether you complete each task successfully
> - Errors or validation issues
> - Your confidence ratings after each task
> - My notes on any hesitations or accessibility issues
>
> **What we're NOT collecting**:
> - Your name, email, or student ID
> - Screen recordings or audio
> - Your device details beyond 'keyboard-only' or 'screen reader'
>
> I'll assign you a random session code (like `sid=X7kL9p`) which will appear in the logs. You can request that I delete all data linked to your session code at any time, even after today.
>
> **You can stop at any time**, no questions asked, and it won't affect your grade.
>
> Do you have any questions before we start?"

**Verbal consent**: "Are you happy to proceed?"

Record in `wk09/lab-wk9/research/consent-log.md`:
```
Date: 2025-10-15
Participant code: P1
Session ID: X7kL9p
Consent: Verbal consent given
Notes: Requested keyboard-only variant
```

**Opt-out path**: If participant requests deletion:
1. Open `data/metrics.csv`
2. Delete all rows where `session_id=X7kL9p`
3. Note in `consent-log.md`: "Data deleted on request [date]"

---

## Session Setup

**Environment**:
- Quiet space in lab (not open-plan area)
- Participant laptop/desktop with browser open to prototype
- Facilitator laptop for notes (don't share screen)

**Pre-pilot**:
1. Generate random session ID: `openssl rand -hex 3` → e.g., `7a9f2c`
2. Set cookie in participant browser:
   ```javascript
   document.cookie = "sid=7a9f2c; path=/";
   ```
3. Navigate to `/tasks` (should be pre-populated with seed data)
4. Position facilitator to side (not behind—feels invasive)

**Materials**:
- Printed task scenarios (or read aloud)
- `pilot-notes.md` template open
- Stopwatch (backup timing)

---

## Session Flow

### 0. Introduction (2 min)
- Consent process (see above)
- Explain think-aloud (optional): "Feel free to say what you're thinking as you go, but no pressure."
- Set expectations: "I won't help unless you're stuck for >3 minutes."

### 1. Warm-up (2 min, not timed)
"Take a minute to browse the task list. Click around, get familiar. Let me know when you're ready to start the timed tasks."

### 2. Task T3: Add Task (60s limit)
**Read scenario**:
"You need to remember to 'Call supplier about delivery'. Add this as a new task."

**Start timing** when participant focuses in input field (or reads scenario, if using stopwatch).

**Observe**:
- Do they find the form immediately?
- Do they submit blank by mistake?
- Do they notice the success confirmation?

**Post-task question**:
"On a scale of 1 to 5, how confident are you that you completed that correctly?"

**Record**: Completion (0/1), confidence, notes

---

### 3. Task T1: Filter Tasks (120s limit)
**Read scenario**:
"You've been asked to find all tasks containing the word 'report'. Use the filter to show only matching tasks, then count how many remain."

**Observe**:
- Do they find the filter box?
- Do they read the result count?
- Do they manually count items?

**Post-task**: Confidence rating

---

### 4. Task T2: Edit Task (90s limit)
**Read scenario**:
"The task 'Submit invoices' has a typo. Change it to 'Submit invoices by Friday' and save the change."

**Observe**:
- Do they find Edit button?
- Do they trigger validation errors?
- Do they check that change persisted?

**Post-task**: Confidence rating

---

### 5. Task T4: Delete Task (45s limit)
**Read scenario**:
"The task 'Test entry' is no longer needed. Delete it."

**Observe**:
- Confirmation dialog (HTMX) or direct submit (no-JS)?
- Do they verify deletion succeeded?

**Post-task**: Confidence rating

---

### 6. Debrief (3 min)
**Ask**:
1. "Which task felt most difficult?"
2. "Did anything surprise you or not work as you expected?"
3. "Were there any points where you weren't sure if something had worked?"
4. "(For SR/keyboard users) Did you encounter any accessibility barriers?"

**Record** verbatim quotes in notes.

**Thank participant**:
"That's really helpful, thank you. Your feedback will directly improve the prototype."

---

## Facilitator Guidelines

**Do**:
- Remain neutral (don't lead: "Did you see the status message?")
- Take detailed notes (timestamps, direct quotes)
- Allow silence (don't fill pauses)
- Note when you intervene ("Prompted after 3min stuck")

**Don't**:
- Explain the interface before tasks
- Show participant how to do something (defeats the test)
- Justify design choices ("It's supposed to work like this...")
- Make participant feel judged

**If participant is completely stuck**:
- Wait 3 minutes
- Ask: "What are you looking for?" (diagnostic question)
- If still stuck: "Let's move to the next task"
- **Mark task as failed, note reason**

---

## Data Recording

**Automated** (server logs → `data/metrics.csv`):
- Timestamp, session_id, task_code, step (start/success/validation_error), ms, js_mode

**Manual** (`wk09/lab-wk9/research/pilot-notes.md`):
```
Session: P1 (sid=7a9f2c)
Date: 2025-10-15
Variant: Keyboard-only, JS-on

| Time | Task | Observation | Tag |
|------|------|-------------|-----|
| 14:23 | T3 | Participant hesitated before submitting—unsure if 'Enter' or button | ux-feedback |
| 14:25 | T3 | Success message not noticed initially | a11y-status |
| 14:26 | T1 | Typed 'report' slowly, watching for instant results | ux-expectation |
| 14:27 | T1 | Screen reader announced "Showing 3 tasks" ✓ | a11y-pass |
| 14:29 | T2 | Clicked Edit, validation error triggered (blank submission) | error-handling |
| 14:30 | T2 | Recovered from error, completed successfully | resilience |

Debrief notes:
- "I liked that the filter worked without clicking a button"
- "I wasn't sure the edit saved—maybe make the message more obvious?"
- SR announced status messages correctly throughout
```

**Subjective ratings** (post-task, in notes):
```
Confidence ratings (1–5):
  T3: 5
  T1: 4
  T2: 3 ("not sure it saved")
  T4: 5
```

---

## Post-Session

**Immediate**:
1. Save notes to `wk09/lab-wk9/research/pilots/P1-notes.md`
2. Check `data/metrics.csv` for completeness (all tasks logged?)
3. Note any missing data or anomalies

**After all pilots**:
1. Copy `data/metrics.csv` to `wk09/assessment/results.csv`
2. Aggregate notes into themes (Week 10 lab)
3. Calculate medians, error rates (Week 10 lab)

---

## Accessibility Variants

### Keyboard-Only Session
- Participant uses Tab, Enter, Space only (no mouse)
- Observe tab order, focus visibility, keyboard traps
- Note any unreachable elements

### Screen Reader Session
- Participant uses NVDA (Windows) or Orca (Linux)
- Allow 2× time for navigation
- Note announcements, label quality, live region behaviour
- Capture SR output in notes (verbatim if possible)

### No-JS Session
- Disable JavaScript in browser settings before starting
- Expect slower times (full page reloads)
- Verify all tasks still function (parity check)
- Note any missing features or broken flows

---

## Risk Mitigation

**Participant distress**: If participant becomes frustrated:
- Reassure: "This is really helpful feedback—it's the interface, not you."
- Offer to skip task or stop session

**Technical failure**: If server crashes mid-session:
- Restart server, reload page
- Resume from next task (don't re-run completed tasks)
- Note incident in data-notes.md

**Data loss**: If logs don't record correctly:
- Use facilitator stopwatch times as backup
- Note in data-notes.md: "Session P3: server logs incomplete, used manual timing"

---

## Summary Checklist

Before each session:
- [ ] Generate session ID, set cookie
- [ ] Seed task database with test data
- [ ] Print or queue task scenarios
- [ ] Open pilot-notes template
- [ ] Confirm server running

During session:
- [ ] Read consent script, obtain verbal consent
- [ ] Record session metadata (P code, sid, date)
- [ ] Time each task (automated + backup)
- [ ] Collect confidence ratings
- [ ] Take qualitative notes with timestamps
- [ ] Debrief open questions

After session:
- [ ] Save notes to pilots/ directory
- [ ] Verify logs in metrics.csv
- [ ] Thank participant, reiterate opt-out path
```

✋ **Stop and check**:
- [ ] Consent process clear and ethical
- [ ] Session flow structured and timed
- [ ] Facilitator guidelines prevent bias
- [ ] Data recording specified (auto + manual)
- [ ] Accessibility variants documented
- [ ] Risk mitigation addressed

---

## Activity D: Implement Instrumentation (35 min)

**Goal**: Add server-side logging to capture task events.

### Step 1: Define schema (5 min)

**Create `wk09/lab-wk9/instr/schema.md`**:

```markdown
# Metrics CSV Schema

**File**: `data/metrics.csv`

**Columns**:
```csv
ts_iso,session_id,request_id,task_code,step,outcome,ms,http_status,js_mode
```

| Column | Type | Description | Example |
|--------|------|-------------|---------|
| `ts_iso` | ISO 8601 timestamp | Event timestamp (UTC) | `2025-10-13T14:23:01.832Z` |
| `session_id` | String (6-12 chars) | Anonymous session identifier | `X7kL9p` |
| `request_id` | String | Unique request identifier | `r001` |
| `task_code` | String | Task identifier from evaluation plan | `T1_filter`, `T2_edit`, `T3_add`, `T4_delete` |
| `step` | Enum | Event type | `start`, `success`, `validation_error`, `fail`, `server_error` |
| `outcome` | String | Specific outcome (for errors) | `blank_title`, `max_length`, empty for success |
| `ms` | Integer | Duration in milliseconds (start to event) | `1847` |
| `http_status` | Integer | HTTP status code | `200`, `400`, `500` |
| `js_mode` | Enum | JavaScript availability | `on` (HTMX), `off` (no-JS) |

**Example rows**:
```csv
ts_iso,session_id,request_id,task_code,step,outcome,ms,http_status,js_mode
2025-10-13T14:23:01.832Z,X7kL9p,r001,T1_filter,success,,1847,200,on
2025-10-13T14:25:12.123Z,X7kL9p,r002,T3_add,validation_error,blank_title,234,400,on
2025-10-13T14:26:03.456Z,X7kL9p,r003,T3_add,success,,567,200,on
2025-10-13T14:28:15.789Z,X7kL9p,r004,T2_edit,success,,1234,200,on
```

> **📊 Walkthrough: Understanding a User Session**
>
> Let's trace **one participant** (session `X7kL9p`) through their tasks:
>
> | # | Time | Task | What Happened | `step` | `outcome` | `ms` | `js_mode` |
> |---|------|------|---------------|--------|-----------|------|-----------|
> | **1** | 14:23:01 | **T1** (Filter) | ✅ Successfully filtered tasks | `success` | _(empty)_ | 1847ms | `on` (HTMX) |
> | **2** | 14:25:12 | **T3** (Add) | ❌ Tried to add blank title | `validation_error` | `blank_title` | 234ms | `on` (HTMX) |
> | **3** | 14:26:03 | **T3** (Add) | ✅ Retry succeeded | `success` | _(empty)_ | 567ms | `on` (HTMX) |
> | **4** | 14:28:15 | **T2** (Edit) | ✅ Edited task inline | `success` | _(empty)_ | 1234ms | `on` (HTMX) |
>
> **Observations**:
> - **Same `session_id`** (`X7kL9p`) = all rows belong to one participant's session
> - **Row 2 → Row 3**: User made a validation error (`blank_title`), then retried successfully
> - **`ms` column**: Row 2 is fast (234ms) because validation happens server-side instantly, Row 1 is slower (1847ms) because filtering queries the database
> - **`js_mode=on`**: This participant had JavaScript enabled (HTMX working)
> - **`outcome` empty for success**: Only populated when `step=validation_error` or `fail`
>
> **Why this matters for analysis**:
> - **Error rate**: 1 error / 4 attempts = 25% error rate for this participant
> - **Recovery**: User recovered from error (Row 3 success after Row 2 error) = good resilience
> - **Time-to-task**: Row 1 took 1.8 seconds (reasonable for filter operation)
> - **Comparison**: We can compare this participant's times against median times for T1, T2, T3, T4

**Notes**:
- `ms` measures server-side duration only (not client rendering)
- `start` events optional (can derive from first log per task per session)
- `outcome` field allows filtering by specific error types in analysis
```

### Step 2: Create Logger helper (10 min)

**Create `src/main/kotlin/utils/Logger.kt`**:

```kotlin
package utils

import io.ktor.http.HttpStatusCode
import java.io.File
import java.time.Instant
import java.time.format.DateTimeFormatter

/**
 * Simple CSV logger for HCI evaluation metrics.
 * Thread-safe, appends to data/metrics.csv.
 *
 * Privacy: Ensure session_id is anonymous (no PII).
 */
data class LogEntry(
    val sessionId: String,
    val requestId: String,
    val taskCode: String,
    val step: String,
    val outcome: String,
    val durationMs: Long,
    val statusCode: Int,
    val jsMode: String,
)

object Logger {
    private val out =
        File("data/metrics.csv").apply {
            parentFile?.mkdirs()
            if (!exists()) writeText("ts_iso,session_id,request_id,task_code,step,outcome,ms,http_status,js_mode\n")
        }

    @Synchronized
    fun write(entry: LogEntry) {
        val ts = DateTimeFormatter.ISO_INSTANT.format(Instant.now())
        out.appendText(
            "$ts,${entry.sessionId},${entry.requestId},${entry.taskCode},${entry.step}," +
                "${entry.outcome},${entry.durationMs},${entry.statusCode},${entry.jsMode}\n",
        )
    }

    fun validationError(
        sessionId: String,
        requestId: String,
        taskCode: String,
        outcome: String,
        jsMode: String,
    ) {
        write(
            LogEntry(
                sessionId = sessionId,
                requestId = requestId,
                taskCode = taskCode,
                step = "validation_error",
                outcome = outcome,
                durationMs = 0,
                statusCode = HttpStatusCode.BadRequest.value,
                jsMode = jsMode,
            )
        )
    }

    fun success(
        sessionId: String,
        requestId: String,
        taskCode: String,
        durationMs: Long,
        jsMode: String,
    ) {
        write(
            LogEntry(
                sessionId = sessionId,
                requestId = requestId,
                taskCode = taskCode,
                step = "success",
                outcome = "",
                durationMs = durationMs,
                statusCode = HttpStatusCode.OK.value,
                jsMode = jsMode,
            )
        )
    }
}
```

### Step 3: Create timing helper (10 min)

**Create `src/main/kotlin/utils/Timing.kt`**:

```kotlin
package utils

import io.ktor.server.application.*
import io.ktor.util.*

/**
 * Timing helper for HCI evaluation.
 * Wraps a block of code, measures duration, logs result.
 */

// AttributeKey for storing request start time
val RequestStartTimeKey = AttributeKey<Long>("RequestStartTime")

// AttributeKey for request ID
val RequestIdKey = AttributeKey<String>("RequestId")

/**
 * Extension function to time a block of code and log the result.
 *
 * Usage:
 *   call.timed(taskCode = "T1_filter", jsMode = "on") {
 *       // ... validation, processing, etc.
 *       // If validation fails, throw exception or return early
 *   }
 *
 * Automatically logs success or captures exceptions.
 */
suspend fun ApplicationCall.timed(
    taskCode: String,
    jsMode: String,
    block: suspend ApplicationCall.() -> Unit
) {
    val start = System.currentTimeMillis()
    call.attributes.put(RequestStartTimeKey, start)

    val session = request.cookies["sid"] ?: "anon"
    val reqId = attributes.getOrNull(RequestIdKey) ?: newReqId()

    try {
        block()
        val duration = System.currentTimeMillis() - start
        Logger.success(session, reqId, taskCode, duration, jsMode)
    } catch (e: Exception) {
        val duration = System.currentTimeMillis() - start
        Logger.write(session, reqId, taskCode, "server_error", e.message ?: "unknown", duration, 500, jsMode)
        throw e
    }
}

/**
 * Helper to detect JavaScript mode from HTMX header.
 */
fun ApplicationCall.isHtmx(): Boolean =
    request.headers["HX-Request"]?.equals("true", ignoreCase = true) == true

fun ApplicationCall.jsMode(): String =
    if (isHtmx()) "on" else "off"

/**
 * Generate a unique request ID.
 */
private var requestCounter = 0
fun newReqId(): String = "r${String.format("%04d", ++requestCounter)}"
```

### Step 4: Instrument routes (10 min)

**Update `src/main/kotlin/routes/Tasks.kt`** (example for POST /tasks):

```kotlin
post("/tasks") {
    val reqId = newReqId()
    call.attributes.put(RequestIdKey, reqId)

    val session = call.request.cookies["sid"] ?: "anon"
    val jsMode = call.jsMode()

    call.timed(taskCode = "T3_add", jsMode = jsMode) {
        val title = call.receiveParameters()["title"].orEmpty().trim()

        // Validation
        if (title.isBlank()) {
            Logger.validationError(session, reqId, "T3_add", "blank_title", 0, jsMode)
            if (call.isHtmx()) {
                val status = """<div id="status" hx-swap-oob="true">Title is required.</div>"""
                return@timed call.respondText(status, ContentType.Text.Html, HttpStatusCode.BadRequest)
            } else {
                return@timed call.respondRedirect("/tasks?error=title")
            }
        }

        if (title.length > 200) {
            Logger.validationError(session, reqId, "T3_add", "max_length", 0, jsMode)
            if (call.isHtmx()) {
                val status = """<div id="status" hx-swap-oob="true">Title too long (max 200 chars).</div>"""
                return@timed call.respondText(status, ContentType.Text.Html, HttpStatusCode.BadRequest)
            } else {
                return@timed call.respondRedirect("/tasks?error=title&msg=too_long")
            }
        }

        // Success path
        val task = repo.add(title)
        if (call.isHtmx()) {
            val item = PebbleRender.render("tasks/_item.peb", mapOf("t" to task))
            val status = """<div id="status" hx-swap-oob="true">Added "${task.title}".</div>"""
            call.respondText(item + status, ContentType.Text.Html)
        } else {
            call.respondRedirect("/tasks")
        }
    }
}
```

**Similarly instrument**:
- `GET /tasks` with `T1_filter` (if query param `q` present)
- `POST /tasks/{id}/edit` with `T2_edit`
- `DELETE /tasks/{id}` with `T4_delete`

**Note on task codes**:
- **Evaluation tasks** (T1-T4): These match the tasks defined in your evaluation protocol
  - `T1_filter` — Search and filter the task list
  - `T2_edit` — Edit or toggle task status
  - `T3_add` — Add a new task
  - `T4_delete` — Delete a task
- **Baseline logging** (optional): You may also log general interactions not part of the evaluation
  - `T0_list` — General task list view (not timed in evaluation)
  - Helps distinguish evaluation sessions from general usage analytics

✋ **Stop and check**:
- [ ] Logger.kt compiles and creates metrics.csv
- [ ] Timing.kt provides timed{} helper
- [ ] At least one route instrumented (POST /tasks)
- [ ] Validation errors logged explicitly

---

## Activity E: Dry Run and Verify (10 min)

**Goal**: Confirm instrumentation works before real pilots.

### Step 1: Set session cookie (2 min)

Open browser DevTools Console:
```javascript
document.cookie = "sid=DRY_RUN_01; path=/";
```

Reload page.

### Step 2: Execute test tasks (5 min)

**With JS enabled**:
1. Add task "Test task 1" → expect success row in metrics.csv
2. Submit empty form → expect validation_error row
3. Add task "Test task 2" → expect success row
4. Edit a task → expect T2_edit success row
5. Delete a task → expect T4_delete success row

**Disable JS**:
6. Repeat add task (empty + valid) → expect js_mode=off rows

### Step 3: Inspect logs (3 min)

Open `data/metrics.csv`:

**Expected columns**:
```csv
ts_iso,session_id,request_id,task_code,step,outcome,ms,http_status,js_mode
```

**Example rows**:
```csv
2025-10-13T15:01:23.456Z,DRY_RUN_01,r001,T3_add,success,,567,200,on
2025-10-13T15:01:45.789Z,DRY_RUN_01,r002,T3_add,validation_error,blank_title,0,400,on
2025-10-13T15:02:10.123Z,DRY_RUN_01,r003,T3_add,success,,432,200,on
2025-10-13T15:03:00.000Z,DRY_RUN_01,r004,T2_edit,success,,1234,200,on
2025-10-13T15:03:30.500Z,DRY_RUN_01,r005,T4_delete,success,,210,200,on
2025-10-13T15:04:15.800Z,DRY_RUN_01,r006,T3_add,success,,3456,200,off
```

**Verify**:
- [ ] All columns present
- [ ] Timestamps in ISO 8601 format
- [ ] session_id consistent (DRY_RUN_01)
- [ ] task_code matches your task definitions
- [ ] js_mode correct (on for HTMX, off for no-JS)
- [ ] Durations plausible (not negative, not absurdly high)
- [ ] HTTP status codes correct (200 success, 400 validation error)

**If anything is wrong**: Fix Logger.kt or route instrumentation, re-run dry run.

✋ **Stop and check**:
- [ ] Dry run completed with JS-on and JS-off paths
- [ ] metrics.csv contains valid rows
- [ ] Validation errors logged correctly
- [ ] Durations captured

---

## Commit & Reflect (10 min)

### Commit message

```bash
git add wk09/lab-wk9/research wk09/lab-wk9/instr src/main/kotlin/utils data/metrics.csv

git commit -m "$(cat <<'EOF'
wk9s1: evaluation plan + server-side instrumentation

- Defined 4 tasks (filter, edit, add, delete) with success criteria
- Documented objective + subjective metrics (completion, time, errors, confidence)
- Created ethical protocol with consent process, session flow, accessibility variants
- Implemented Logger.kt (CSV appender, thread-safe)
- Implemented Timing.kt (timed{} helper, jsMode detection)
- Instrumented POST /tasks with T3_add logging + validation errors
- Dry-run verified: metrics.csv captures correct data for JS-on/off paths
- Scaffolded assessment draft evidence pack (eval-plan.md, protocol.md)

Ready for peer pilots in Week 9 Lab 2.


EOF
)"
```

### Reflection questions

**Answer in `wk09/reflection.md`**:

1. **Metrics selection**: Which metrics will be most useful for identifying usability issues? Why did you prioritise objective vs subjective data?

2. **Ethical considerations**: What was most challenging about designing a privacy-respecting evaluation? How did you ensure no PII would be collected?

3. **Instrumentation trade-offs**: Server-side logging has limitations (doesn't capture client-side rendering time, scroll behaviour, etc.). What gaps might this create in your analysis?

4. **Task design**: How realistic are your task scenarios? Would they generalize to non-peer participants (e.g., non-CS students)?

5. **Accessibility**: How confident are you that your instrumentation will capture accessibility issues (SR announcements, keyboard traps)? What additional data would you need?

6. **Pilot readiness**: What could go wrong during Week 9 Lab 2 pilots? How have you mitigated those risks?

---

## Looking Ahead: Week 9 Lab 2 Pilots

Next session (Lab 2):
- Run 5–6 peer pilots using your protocol
- Collect metrics.csv data + qualitative notes
- Debrief participants
- Prepare draft evidence pack for assessment submission

**Before Lab 2**:
- Review protocol with your pair (practice reading consent script)
- Test dry-run one more time (ensure nothing broke)
- Prepare seed data (task list with known tasks for T1/T2/T4)
- Print task scenarios or have them ready to read

**Week 10** will analyse this data—solid planning now saves hours of confusion later.

---

## Further Reading & Resources

### Essential
- [Evaluation Metrics Quick Reference](../references/evaluation-metrics-quickref.md) — Median, MAD, error rate calculations
- [Consent and PII FAQ](../references/consent-pii-faq.md) — PII definitions, opt-out procedures
- [Nielsen: How Many Test Users?](https://www.nngroup.com/articles/how-many-test-users/) — 5 users find 85% of issues

### HCI Evaluation Methods
- **Lazar et al. (2017).** *Research Methods in Human-Computer Interaction* (2nd ed.). Chapters 5-6 (usability testing, metrics)
- [SIGCHI: Usability Evaluation](https://dl.acm.org/doi/book/10.5555/291224) — Academic grounding
- [Measuring UX](https://measuringux.com/) — Practical guide to quantitative UX

### Ethics & Privacy
- [ICO: Data Protection by Design](https://ico.org.uk/for-organisations/uk-gdpr-guidance-and-resources/accountability-and-governance/guide-to-accountability-and-governance/accountability-and-governance/data-protection-by-design-and-default/)
- [BPS Code of Ethics](https://www.bps.org.uk/guideline/code-ethics-and-conduct) — UK professional standards for research
- [University of Leeds Research Ethics](https://researchsupport.leeds.ac.uk/research-ethics/) — Institutional policy

### Instrumentation
- [Kohavi et al.: Online Controlled Experiments](https://www.exp-platform.com/) — A/B testing at scale (industry context)
- [Google Analytics 4: Measurement Protocol](https://developers.google.com/analytics/devguides/collection/protocol/ga4) — Example of event-based logging (we avoid third-party but shows patterns)

### Accessibility Evaluation
- [WebAIM: Evaluating Accessibility](https://webaim.org/articles/evaluatingcognitive/) — Cognitive disabilities focus
- [W3C: Involving Users in Evaluating Web Accessibility](https://www.w3.org/WAI/test-evaluate/involving-users/)

---

## Glossary Summary

| Term | One-line definition |
|------|---------------------|
| **Usability evaluation** | Systematic assessment of how well people can use a system to achieve goals |
| **Task-based evaluation** | Participants complete realistic scenarios while researchers observe and measure |
| **Objective metrics** | Measurable, observable data (time, errors, completion) without interpretation |
| **Subjective metrics** | Self-reported feelings, perceptions, attitudes (confidence, satisfaction) |
| **Server-side instrumentation** | Logging events at the server; reliable, privacy-respecting, works regardless of JS |
| **Privacy by Design** | Build data minimization and privacy into system architecture from the start |
| **Median** | Middle value in sorted dataset; resistant to outliers |
| **MAD** | Median Absolute Deviation; robust measure of spread |
| **Informed consent** | Participants understand what data is collected, how it's used, and can opt out |
| **Low-risk study** | Peer-to-peer evaluation with minimal data collection, no vulnerable groups |

---

**Lab complete!** You have a comprehensive evaluation plan, ethical protocol, and working instrumentation. Week 9 Lab 2 will collect real data from peer pilots.
