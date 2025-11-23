# Task 1: Evaluation Protocol

**Date**: [Date]
**Facilitator**: [Your name/student ID]
**Observer**: [Name if applicable, or "N/A"]

---

## Pre-Session Setup

### Equipment Checklist
- [ ] Laptop/device with application running (test before participants arrive)
- [ ] Browser with JavaScript enabled (for standard pilots)
- [ ] Browser with JavaScript disabled (for no-JS pilot)
- [ ] Screen reader installed and tested (NVDA/VoiceOver) if doing SR pilot
- [ ] Backup device ready in case of technical failure
- [ ] Timer/stopwatch for time-on-task measurement
- [ ] Notebook/laptop for observer notes
- [ ] Consent protocol printed or on-screen

### Data Collection Setup
- [ ] `data/metrics.csv` file created with header row
- [ ] Session ID generator ready (or pre-generated IDs: P1_xxxx, P2_yyyy, etc.)
- [ ] Pilot notes template ready (`wk09/lab-wk9/templates/pilot-notes-template.md`)

---

## Session Structure (15-20 mins per participant)

### 1. Welcome & Consent (3 mins)

**Script**:
> "Hi [Name], thanks for participating! This is a usability test for my HCI project—I'm evaluating the interface, not you. There are no wrong answers. Your feedback helps me identify what needs improving.
>
> Before we start:
> - This will take about 15-20 minutes
> - I'll ask you to complete 4 tasks using this web interface
> - I'll time how long tasks take and note any issues
> - Please think aloud—say what you're thinking as you work
> - You can stop at any time without penalty
> - All data is anonymous (session ID only, no names)
> - Do you consent to participate?"

**Confirm consent** (verbal "yes" recorded in notes, or signed form if required by ethics).

---

### 2. Context Setting (2 mins)

**Script**:
> "You'll be using a task management system. Imagine you're organizing your coursework—you can add tasks, filter by keyword, edit tasks, and delete completed ones. The interface works with and without JavaScript.
>
> [For no-JS pilot]: Your browser has JavaScript disabled to test accessibility—this is intentional.
> [For SR pilot]: You'll be using [NVDA/VoiceOver] to navigate the interface.
>
> I'll give you tasks one at a time. Please think aloud as you work. Ready?"

---

### 3. Task Execution (10 mins)

**For each task**:
1. Read task aloud
2. Participant attempts task (thinking aloud)
3. Observer notes:
   - Start time (when participant begins)
   - Actions taken (clicks, keystrokes, hesitations)
   - Errors (validation failures, wrong buttons clicked)
   - Quotes (participant comments, confusion expressions)
   - End time (task complete or abandoned)
4. Record outcome: `success`, `success-with-errors`, `abandoned`

**Task List** (see `03-tasks.md` for full definitions):

**Task 1** (T1): [Brief task title]
- **Start**: [HH:MM:SS]
- **Outcome**: [success / success-with-errors / abandoned]
- **End**: [HH:MM:SS]
- **Duration**: [seconds]
- **Errors**: [count]
- **Notes**: [observer notes]

**Task 2** (T2): [Brief task title]
- **Start**: [HH:MM:SS]
- **Outcome**: [success / success-with-errors / abandoned]
- **End**: [HH:MM:SS]
- **Duration**: [seconds]
- **Errors**: [count]
- **Notes**: [observer notes]

**Task 3** (T3): [Brief task title]
- **Start**: [HH:MM:SS]
- **Outcome**: [success / success-with-errors / abandoned]
- **End**: [HH:MM:SS]
- **Duration**: [seconds]
- **Errors**: [count]
- **Notes**: [observer notes]

**Task 4** (T4): [Brief task title]
- **Start**: [HH:MM:SS]
- **Outcome**: [success / success-with-errors / abandoned]
- **End**: [HH:MM:SS]
- **Duration**: [seconds]
- **Errors**: [count]
- **Notes**: [observer notes]

---

### 4. Post-Task Questionnaire (3 mins)

**Confidence ratings** (1-5 scale):

| Task | How confident were you that you completed it correctly? (1=not confident, 5=very confident) |
|------|----------------------------------------------------------------------------------------------|
| T1   | ☐1 ☐2 ☐3 ☐4 ☐5 |
| T2   | ☐1 ☐2 ☐3 ☐4 ☐5 |
| T3   | ☐1 ☐2 ☐3 ☐4 ☐5 |
| T4   | ☐1 ☐2 ☐3 ☐4 ☐5 |

**UMUX-Lite** (optional, for overall usability):
1. "This system's capabilities meet my requirements" (1=strongly disagree, 7=strongly agree): __
2. "This system is easy to use" (1=strongly disagree, 7=strongly agree): __

---

### 5. Debrief (2 mins)

**Script**:
> "Thanks so much! A few quick questions:
> - Was anything confusing or frustrating?
> - Did you notice any accessibility issues? (keyboard traps, missing feedback, etc.)
> - Any features you expected but didn't find?
> - Overall, how would you rate the experience? (1-5)"

**Capture verbatim quotes** for qualitative findings.

---

## Data Recording Format

### Metrics CSV
**File**: `data/metrics.csv`

**Columns**:
```
ts_iso,session_id,request_id,task_code,step,outcome,ms,http_status,js_mode
```

**Example row**:
```
2025-10-15T14:23:45Z,P1_7a9f,req_001,T1,add-task,success,4500,200,on
```

**Populate after session** using server logs + observer notes.

---

### Observer Notes
**File**: `wk09/lab-wk9/research/pilot-notes/P1_notes.md` (one per pilot)

**Structure**:
```markdown
# Pilot Notes: P1_7a9f

**Date**: 2025-10-15
**Time**: 14:18-14:35
**JS Mode**: on
**Special conditions**: None (standard pilot)

## Task 1: [title]
- 14:18 Started task, read prompt aloud
- 14:19 Typed "coursework report" into input
- 14:19 Clicked "Add Task" button
- 14:20 Task appeared in list, participant said "that was easy"
- **Outcome**: success, 2s, 0 errors

## Task 2: [title]
...

## Debrief Quotes
- "I expected the filter to have a search button"
- "The validation error was hard to see"
```

---

## Ethical Safeguards

### Privacy
- Use session IDs (P1_xxxx) only, never participant names
- No PII in logs, screenshots, or notes
- Store data locally, not in cloud services
- Delete data after analysis (6 weeks max retention)

### Consent
- Verbal consent confirmed before starting
- Remind participants they can stop anytime
- If participant withdraws mid-session, delete their data immediately

### Respect
- Use people-centred language ("participant", not "user")
- Thank participants genuinely
- If technical failure occurs, apologize and offer to reschedule (don't waste their time)

---

## Troubleshooting

| Problem | Solution |
|---------|----------|
| Application crashes mid-task | Note crash time, restart app, ask participant if they're OK to continue from where they left off |
| Participant asks "what should I do?" | Redirect: "What would you try first?" (don't give hints—observe natural behavior) |
| Task is impossible due to bug | Note the issue, let participant attempt, then explain: "That's actually a bug I need to fix—thanks for finding it!" |
| Participant completes task incorrectly but thinks they succeeded | Don't correct during session—note discrepancy for analysis |

---

## Post-Session Checklist

- [ ] Metrics logged to `data/metrics.csv`
- [ ] Observer notes saved with session ID filename
- [ ] Confidence ratings recorded
- [ ] Debrief quotes captured
- [ ] Thank-you sent to participant (if appropriate)
- [ ] Identify any critical issues that need immediate attention

---

**Next**: Repeat protocol for all participants (n=4 minimum), then proceed to Activity E (assemble Task 1 draft pack).
