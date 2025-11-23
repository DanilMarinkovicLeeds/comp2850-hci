# Week 9 • Lab 1 — Student Guide: Evaluation Planning & Instrumentation

![COMP2850](https://img.shields.io/badge/COMP2850-HCI-blue)
![Week 9](https://img.shields.io/badge/Week-9-orange)
![Lab 1](https://img.shields.io/badge/Lab-1-green)
![Guide](https://img.shields.io/badge/Type-Student_Guide-purple)

> **Purpose**: Week 9 Lab 1 is about planning your evaluation - creating test tasks, defining metrics, writing an ethical protocol, and adding instrumentation to capture data during Week 9 Lab 2 pilots.

---

## Deliverables

By the end of this lab:
- ✅ `wk09/research/tasks.md` - 3-4 realistic test scenarios
- ✅ `wk09/research/measures.md` - Metrics definition
- ✅ `wk09/research/protocol.md` - Evaluation protocol (consent + procedure)
- ✅ `wk09/research/data-notes.md` - Data recording guidance
- ✅ Basic instrumentation code (optional logging in routes)

---

## Quick Start: Create Files

```bash
mkdir -p wk09/research wk09/evidence
touch wk09/research/tasks.md
touch wk09/research/measures.md
touch wk09/research/protocol.md
touch wk09/research/data-notes.md
```

---

## Part 1: Define Test Tasks (20 minutes)

Based on your backlog priorities, create 3-4 realistic tasks.

**Copy into** `wk09/research/tasks.md`:

<details>
<summary>Click to expand: Test Tasks Template</summary>

```markdown
# Evaluation Tasks — Week 9

## Task 1: Filter and Complete
**Scenario**: You've just finished your COMP2850 HCI lab write-up.

**Instructions**:
1. Find the task titled "Write HCI lab notes"
2. Mark it as complete
3. Verify it's marked correctly

**Success criteria**:
- Participant finds correct task within 30 seconds
- Toggle works (checkbox/button responds)
- Visual confirmation shown (strikethrough, badge, status message)

**Inclusion focus**: Keyboard access, screen reader announcement

---

## Task 2: Add New Task
**Scenario**: You've just been assigned a new coursework deadline.

**Instructions**:
1. Add a new task: "Submit COMP2870 Assignment 2"
2. Confirm it appears in the list

**Success criteria**:
- Form submission works (HTMX + no-JS)
- New task appears immediately (or after reload for no-JS)
- Confirmation message shown

**Inclusion focus**: Error handling (if blank title), status announcements

---

## Task 3: Edit Task Inline
**Scenario**: You made a typo in a task title.

**Instructions**:
1. Find task "Buy milk"
2. Edit it to "Buy oat milk"
3. Save the change

**Success criteria**:
- Edit mode activates (input appears)
- Save updates the title
- Focus returns to edited task

**Inclusion focus**: Focus management, keyboard-only editing, Cancel button

---

## Task 4: Delete Completed Task
**Scenario**: You've completed "Email supervisor" and want to remove it.

**Instructions**:
1. Find task "Email supervisor"
2. Delete it
3. Confirm it's gone

**Success criteria**:
- Confirmation shown (HTMX) OR form submits (no-JS)
- Task removed from list
- Status message announced

**Inclusion focus**: Confirmation (HTMX has `hx-confirm`, no-JS has none - documented trade-off)

---

## Metrics per Task

For each task, record:
- **Time-on-task** (seconds from start to completion)
- **Success** (1 = completed, 0 = abandoned)
- **Error count** (validation errors, wrong clicks)
- **Mode** (HTMX or no-JS)
```

</details>

**Checklist**:
- [ ] 3-4 tasks written with scenarios
- [ ] Success criteria defined
- [ ] Inclusion focus identified per task

---

## Part 2: Define Metrics (25 minutes)

**Copy into** `wk09/research/measures.md`:

<details>
<summary>Click to expand: Measures Template</summary>

```markdown
# Evaluation Metrics — Week 9

## Quantitative Measures

### 1. Time-on-Task (Efficiency)
**Definition**: Seconds from task start to successful completion
**How to collect**: Manual stopwatch OR instrumentation (timestamp diff)
**Analysis**: Mean, median, outliers per task

**Why it matters**: Long times indicate usability problems or confusion

---

### 2. Task Success Rate (Effectiveness)
**Definition**: Percentage of participants who complete task without help
**How to collect**: Binary (1 = success, 0 = fail/abandon)
**Analysis**: Success % per task, identify problematic tasks

**Why it matters**: <80% success = serious usability issue

---

### 3. Error Rate (Accuracy)
**Definition**: Number of validation errors, wrong clicks, back-tracking per task
**How to collect**: Manual tally during observation OR server logs (400 errors)
**Analysis**: Mean errors per task, categorise by type

**Why it matters**: Frequent errors = unclear interface or poor error prevention

---

### 4. Mode Comparison (HTMX vs No-JS)
**Definition**: Time and success differences between JS-enabled and no-JS paths
**How to collect**: Run each participant in one mode, compare groups
**Analysis**: t-test or Mann-Whitney U test (if N > 10 per group)

**Why it matters**: Validates dual-path parity claim

---

## Qualitative Measures

### 5. Post-Task Confidence (Subjective)
**Definition**: 5-point Likert scale: "I am confident I completed the task correctly"
**How to collect**: Verbal question after each task
**Analysis**: Mean score per task, identify low-confidence tasks

**Why it matters**: Low confidence despite success = poor feedback design

---

### 6. Think-Aloud Observations
**Definition**: Verbal comments during task ("Where's the Save button?", "I'm confused")
**How to collect**: Note-taking during pilot
**Analysis**: Thematic coding (confusion points, positive remarks)

**Why it matters**: Reveals WHY metrics are good/bad

---

### 7. Accessibility Barriers (Observational)
**Definition**: Moments where keyboard/SR participant struggles
**How to collect**: Note when Tab order confuses, focus lost, SR silent
**Analysis**: List barriers, map to WCAG criteria

**Why it matters**: Direct evidence of inclusion failures

---

## Data Collection Plan

**Per participant**:
- Task 1-4: Time, success, errors, confidence, mode
- Think-aloud notes: Freeform observations
- Accessibility notes: Specific barriers encountered

**Sample size**: 3-5 participants (peer pilots)

**Storage**: `data/metrics.csv` (local, .gitignored)
```

</details>

**Checklist**:
- [ ] 4+ quantitative metrics defined
- [ ] 2+ qualitative measures defined
- [ ] Data collection plan specified

---

## Part 3: Evaluation Protocol (30 minutes)

**Copy into** `wk09/research/protocol.md`:

<details>
<summary>Click to expand: Protocol Template</summary>

```markdown
# Evaluation Protocol — Week 9 Pilots

**Module**: COMP2850 HCI
**Activity**: Task-based usability pilots
**Date**: [YYYY-MM-DD]
**Researcher**: [Your Name]

---

## Purpose

Evaluate task manager usability and accessibility through structured pilots. Data will inform Week 10 redesign and assessment submission.

## Consent (GDPR/Data Protection Act 2018)

**Lawful basis**: Legitimate interest (educational research) + informed consent

**Before starting**, confirm:
- [ ] Purpose explained (evaluate task manager, not testing you)
- [ ] Tasks described (4 scenarios, ~15 minutes)
- [ ] Data collected listed (times, clicks, observations - no PII)
- [ ] Right to withdraw explained (stop anytime, data deleted)
- [ ] Participant verbally consents

**Record**: Participant pseudonym (P1, P2...), date, consent confirmed (initials)

---

## Procedure

### Setup (5 minutes)
1. **Assign mode**: Participant 1 → HTMX, Participant 2 → No-JS (alternate)
2. **Disable JS if needed**: Chrome DevTools → Settings → Disable JavaScript
3. **Set session ID**: Manual cookie OR URL param (`?sid=P1`)
4. **Open task manager**: http://localhost:8080/tasks

### Orientation (2 minutes)
"You'll complete 4 tasks with a task manager. I'll time you and take notes. **Think aloud** - say what you're thinking ('Where's the Edit button?', 'This is confusing'). There are no wrong answers - we're testing the interface, not you. You can stop anytime."

### Task Execution (12 minutes)
For each task (T1-T4):
1. **Read scenario** aloud from `tasks.md`
2. **Start timer** when participant begins
3. **Observe** (note clicks, hesitations, comments)
4. **Stop timer** when task complete OR participant gives up
5. **Ask confidence**: "On a scale 1-5, how confident are you that you completed correctly?"
6. **Record**: Time, success (1/0), errors, confidence, notes

### Debrief (3 minutes)
"Thanks! Any final thoughts? What was most confusing? What worked well?"

**Total time**: ~20 minutes per participant

---

## Data Recording

### Manual Notes Template
```
Participant: [P1, P2, P3...]
Mode: [HTMX / No-JS]
Date: [YYYY-MM-DD]
Duration: [~20 min]

Task 1: [Start time HH:MM:SS] → [End time HH:MM:SS] = [X seconds]
Success: [1/0]
Errors: [count]
Confidence: [1-5]
Notes: [Observations, quotes]

[Repeat for T2-T4]

Post-pilot notes:
- Accessibility barriers:
- Positive moments:
- Redesign ideas:
```

### Automated Instrumentation (Optional)
If you added logging to routes:
- `data/metrics.csv`: timestamp, task_id, action, duration, status
- `data/errors.csv`: timestamp, task_id, error_type, message

---

## Risks & Mitigations

| Risk | Mitigation |
|------|-----------|
| Participant feels tested | Emphasise "testing interface, not you" |
| Task too hard, participant frustrated | Allow skip after 2 min struggle |
| Data breach (PII in notes) | Use pseudonyms only, `.gitignore` data files |
| Observer bias | Neutral prompts ("What are you thinking?"), don't lead |

---

## Ethics Checklist

- [ ] Consent obtained verbally before start
- [ ] Pseudonyms used (P1, P2...), no real names
- [ ] Data stored locally (not cloud), Git repo private
- [ ] Right to withdraw explained
- [ ] No coercion (peers can decline without penalty)
- [ ] Data deletion plan stated (end of semester OR anonymised)

---

**Reference**: BPS Code of Human Research Ethics (2021), UK GDPR (2018)
```

</details>

**Checklist**:
- [ ] Protocol written with setup, procedure, debrief
- [ ] Consent process documented
- [ ] Data recording template provided
- [ ] Ethics checklist complete

---

## Part 4: Data Recording Guidance (15 minutes)

**Copy into** `wk09/research/data-notes.md`:

<details>
<summary>Click to expand: Data Notes Template</summary>

```markdown
# Data Recording Notes — Week 9

## Manual Recording (Required)

For each participant, record in notebook:
- **Task times**: Use stopwatch, record to nearest second
- **Success**: Binary 1/0 (did they complete correctly?)
- **Errors**: Tally clicks on wrong elements, validation errors shown
- **Confidence**: Ask "1-5, how confident?" after each task
- **Think-aloud**: Note direct quotes in "quotation marks"

## Automated Instrumentation (Optional)

If you add logging to routes:

### Example: Log Task Creation Time
```kotlin
post("/tasks") {
    val startTime = System.currentTimeMillis()
    // ... validation, create task ...
    val endTime = System.currentTimeMillis()
    val duration = endTime - startTime

    // Log to CSV
    logMetric("task_create", duration, success = true)
}
```

### Log Format (`data/metrics.csv`)
```csv
timestamp,session_id,task_id,action,duration_ms,success,mode
2025-01-15T10:30:15,P1,T2,task_create,1230,true,htmx
2025-01-15T10:31:45,P1,T3,task_edit,2100,true,htmx
```

## Observational Codes

Use these shorthand codes in notes:
- **KBD**: Keyboard navigation issue
- **SR**: Screen reader problem
- **FOC**: Focus management issue
- **ERR**: Validation error shown
- **CONF**: Participant expressed confusion
- **POS**: Positive comment

Example: "P2 [KBD] couldn't find Cancel button with Tab"

## Anomalies to Note

- Browser crashed / page froze
- Participant asked clarifying question (we answered)
- Network delay (slow response)
- Observer error (forgot to start timer)

**Record all anomalies** so analysis accounts for them.

---

**Storage**: Keep notes in physical notebook during pilots, transcribe to digital after.
**Backup**: Photos of notebook pages saved to `wk09/evidence/notes-backup/`
```

</details>

**Checklist**:
- [ ] Manual recording template provided
- [ ] Optional instrumentation example given
- [ ] Observational codes defined
- [ ] Anomaly guidance clear

---

## Part 5: Optional Instrumentation (30 minutes)

If you want automated metrics, add logging to routes. Example:

<details>
<summary>Click to expand: Simple Logging Code</summary>

**Create** `src/main/kotlin/utils/MetricsLogger.kt`:

```kotlin
package utils

import java.io.File
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

object MetricsLogger {
    private val logFile = File("data/metrics.csv")
    private val formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME

    init {
        if (!logFile.exists()) {
            logFile.parentFile?.mkdirs()
            logFile.writeText("timestamp,session_id,task_id,action,duration_ms,success,mode\n")
        }
    }

    fun log(
        sessionId: String,
        taskId: String,
        action: String,
        durationMs: Long,
        success: Boolean,
        mode: String
    ) {
        val timestamp = LocalDateTime.now().format(formatter)
        val line = "$timestamp,$sessionId,$taskId,$action,$durationMs,$success,$mode\n"
        logFile.appendText(line)
    }
}
```

**Use in routes**:
```kotlin
post("/tasks") {
    val start = System.currentTimeMillis()
    val sessionId = call.sessions.get<String>("sessionId") ?: "unknown"
    val mode = if (call.isHtmx()) "htmx" else "nojs"

    // ... create task logic ...

    val duration = System.currentTimeMillis() - start
    MetricsLogger.log(sessionId, "T2", "task_create", duration, success = true, mode)

    // ... respond ...
}
```

</details>

**Note**: Instrumentation is optional. Manual recording is sufficient for Week 9.

---

## Commit & Continue

```bash
git add wk09/
git commit -m "feat(wk9-lab1): evaluation planning and instrumentation

- Created 4 test tasks with scenarios and success criteria
- Defined 7 metrics (time, success, errors, confidence, think-aloud)
- Documented evaluation protocol with consent and procedure
- Provided data recording templates and observational codes
- Added optional instrumentation code example

Ready for Week 9 Lab 2 pilots."
```

**Next**: Week 9 Lab 2 - Run pilots, collect data, analyse, draft assessment evidence.
