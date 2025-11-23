# Week 11 • Lab 1 — Evidence-Led Studio Crit

![COMP2850](https://img.shields.io/badge/COMP2850-HCI-blue)
![Week 11](https://img.shields.io/badge/Week-11-orange)
![Lab 1](https://img.shields.io/badge/Lab-1-green)
![Status](https://img.shields.io/badge/Status-Draft-yellow)



---

## Before Lab: Required Reading (10 mins)

📖 **Essential**
- [IDEO: The Art of Critique](https://www.ideo.com/blog/design-critique) (5 min)
- Review your assessment submission (submission-template.md with all sections complete)
- Review your before/after metrics (Section 5: Verification Results)

📖 **Quick reference**
- [Assistive Testing Checklist](../references/assistive-testing-checklist.md)
- [Evaluation Metrics Quick Reference](../references/evaluation-metrics-quickref.md)

---

## Introduction: From Evidence to Narrative

You've completed the HCI design cycle: audit → pilot → analyse → redesign → verify. **Today you present that work to peers**.

**This is not "show and tell"**. Studio critique is:
- **Evidence-led**: Every claim backed by data, screenshots, transcripts
- **Reflective**: Acknowledge limitations, trade-offs, remaining issues
- **Actionable**: Invite specific feedback, identify next steps

**Why this matters**:
- **Professional practice**: Design reviews are standard in industry (Google, GOV.UK, Microsoft all use critique protocols)
- **Accreditation**: External panels assess evidence chains—this is rehearsal
- **Peer learning**: Seeing 5-6 different solutions to same problem builds design vocabulary

**Format**: 15 min per team (5 min demo, 3 min metrics, 2 min accessibility proof, 5 min Q&A). Tight timing forces clarity.

> **Visual**: Evidence-led critique loop

{{#include ../references/visuals/crit-loop.md}}

<small>See [Process Visuals](../references/process-visuals.md#crit-loop) for captioned steps.</small>

---

## Learning Focus

### Lab Objectives
By the end of this session, you will have:
- Presented evidence chains clearly (problem → data → fix → verification)
- Demonstrated accessibility improvements (keyboard, SR, no-JS)
- Defended design decisions with data (before/after metrics)
- Given/received peer feedback using structured rubric
- Documented crit insights for portfolio

### Learning Outcomes Addressed
This lab contributes to the following module Learning Outcomes ([full definitions](../references/learning-outcomes.md)):

- **LO11**: Collaborate in teams — evidenced by peer critique participation
- **LO12**: Demonstrate professionalism — evidenced by constructive feedback + respectful critique
Maps to WCAG: 2.2 AA (demonstrating compliance)

---

## Key Concepts

### Design Critique

> **Design Critique** ([Glossary](../references/glossary.md))
>
> Structured peer review of design work. **Not personal feedback**—focuses on work, evidence, impact.
>
> **Characteristics**
> - **Specific**: "Error summary not keyboard-focusable" (good) vs "errors bad" (vague)
> - **Constructive**: Suggest alternatives, not just problems
> - **Evidence-based**: Reference data, WCAG, observations (not opinions)
> - **Respectful**: Assume positive intent, acknowledge constraints
>
> **Format** (this module)
> - Presenter shows work + evidence
> - Audience asks clarifying questions
> - Audience offers critique: "I noticed X, have you considered Y?"
> - Presenter notes feedback (doesn't defend in real-time)
>
> **HCI Connection**: Critique improves design through **external perspective**—you're too close to your work to see all issues.
>
> **Not a critique**
> - "I don't like the colour" (subjective preference without rationale)
> - "This sucks" (not constructive)
> - "You should have..." (hindsight, not helpful)
>
> **Good critique**
> - "The error message says 'Title required' but doesn't explain the max length constraint. Pilot P3 hit this—could you add that info?"
>
> 🔗 [Design Thinking: Critique vs Criticism](https://dschool.stanford.edu/resources/the-art-of-critique)

### Evidence-Led Presentation

> **Evidence-Led Presentation** ([Glossary](../references/glossary.md))
>
> Presentation style where every claim is backed by artifacts (data, screenshots, quotes).
>
> **Structure**
> 1. **Problem**: "T2 edit had 67% completion (0% no-JS)" ← metric
> 2. **Root cause**: "Validation errors not announced to SR" ← pilot quote + WCAG reference
> 3. **Fix**: "Added `role=alert`" ← code diff
> 4. **Verification**: "Post-change: 100% completion" ← after-metrics + screenshot
>
> **What NOT to do**
> - "We made it better" (vague—better how?)
> - "Users struggled" (who? how many? with what?)
> - "Fixed accessibility" (which criteria? verified how?)
>
> **HCI Connection**: Evidence separates **opinion** from **fact**. Stakeholders can question interpretation, but not the data.
>
> **Academic context**: External examiners check evidence chains. Missing evidence → lower marks, even if work was good.
>
> 🔗 [GOV.UK: Show the thing](https://gds.blog.gov.uk/2016/11/18/what-we-mean-when-we-say-show-the-thing/)

### Accessibility Demonstration

> **Accessibility Demonstration** ([Glossary](../references/glossary.md))
>
> Live proof that inclusive design works. **Show, don't tell**.
>
> **Examples**
> - **Keyboard**: Tab through form → submit error → Tab to error link → press Enter → focus lands on input
> - **Screen reader**: Turn on NVDA/Orca → navigate to form → submit error → SR announces "Alert. Title is required"
> - **No-JS**: Disable JavaScript → submit form → error summary appears and focused
>
> **Why live demo?** Screenshots can be faked. Live demo proves system works right now.
>
> **Backup plan**: If live demo fails (demo gremlins), have **video recording** or **annotated screenshots** ready.
>
> **What to show**
> - Before state (broken or suboptimal)
> - After state (fixed)
> - Evidence it meets WCAG (checklist row, transcript)
>
> **Common mistakes**
> - Only showing HTMX path (ignoring no-JS)
> - Not narrating what's happening ("I'm pressing Tab, now I'm on the error link...")
> - Assuming audience sees what you see (make focus indicators obvious, zoom in if needed)
>
> 🔗 [WebAIM: Demonstrating Accessibility](https://webaim.org/articles/demos/)

### Constructive Feedback

> **Constructive Feedback** ([Glossary](../references/glossary.md))
>
> Feedback that helps recipient improve. Has three parts: observation + impact + suggestion.
>
> **Formula**
> 1. **Observation**: "I noticed X" (specific, factual)
> 2. **Impact**: "This might affect Y" (consequence, not judgment)
> 3. **Suggestion**: "Have you considered Z?" (alternative, not mandate)
>
> **Example (good)**
> - Observation: "Your error summary uses `aria-live=assertive`, which I saw announced in your SR demo"
> - Impact: "In my testing, `assertive` interrupted mid-sentence when I was reading other content"
> - Suggestion: "Have you tested with `aria-live=polite` to see if it's less disruptive? Might be a good trade-off if errors aren't time-critical."
>
> **Example (bad)**
> - "You should use `polite` not `assertive`" ← prescriptive, no context
>
> **Receiving feedback**
> - **Listen**: Don't defend immediately (note it, reflect later)
> - **Clarify**: "Can you show me where you saw that?"
> - **Thank**: Even if you disagree, feedback is effort
>
> 🔗 [Kim Scott: Radical Candor](https://www.radicalcandor.com/) — Framework for constructive feedback

---

## Activity A: Prepare Presentation (20 min)

**Goal**: Assemble evidence into clear 5-slide narrative.

### Step 1: Create presentation outline (5 min)

**Use Markdown slides** (e.g., Marp) or **simple slide deck** (Google Slides, PowerPoint).

**5-slide structure**:

1. **Title slide** — Project name, team, date
2. **Problem + Evidence** — What was broken? (metrics, quotes, WCAG violations)
3. **Solution** — What changed? (code diff, screenshots)
4. **Verification** — How do we know it worked? (after-metrics, regression checklist)
5. **Next Steps** — What remains? (backlog, Semester 2 plans)

**Example**:

**Slide 2: Problem + Evidence**
```
# Problem: Validation Errors Not Accessible

**Week 9 Findings**:
- T2 (Edit Task): 67% completion overall, 0% completion (no-JS)
- Pilot P3: "Gave up after 2 validation errors—couldn't find error summary"
- Pilot P2 (NVDA): "SR didn't announce error message"

**Root Cause**:
- HTMX path: Missing `role=alert` (WCAG 4.1.3 violation)
- No-JS path: Error summary not keyboard-focusable (WCAG 3.2.1 violation)

**Impact**: Screen reader users, keyboard-only users, no-JS users excluded

[Screenshot: error summary with no focus indicator]
```

### Step 2: Select key evidence artifacts (10 min)

**From your `evidence/` folder (Section 6 of assessment)**:

**Screenshots** (3-4 max):
- Before: Error present but not accessible (no `role=alert` in devtools)
- After: Error with `role=alert` visible in devtools
- No-JS before: Error summary without focus
- No-JS after: Error summary with focus outline

**Metrics table**:
```
| Metric | Before | After | Δ |
|--------|--------|-------|---|
| T2 completion (no-JS) | 0% | 100% | +100% |
| T2 completion (all) | 67% | 100% | +33% |
| T2 error rate | 33% | 25% | -8% |
```

**Code diff** (1 key change):
```diff
- val status = """<div id="status" hx-swap-oob="true">Title is required.</div>"""
+ val status = """<div id="status" role="alert" aria-live="assertive" hx-swap-oob="true">
+   Title is required. Please enter at least one character.
+ </div>"""
```

**SR transcript snippet**:
```
Before: [form submitted, no announcement]
After: "Alert. Title is required. Please enter at least one character."
```

### Step 3: Prepare live demo script (5 min)

**Create `wk11/crit/demo-script.md`**:

```markdown
# Live Demo Script — Week 11 Studio Crit

**Timing**: 5 minutes

---

## Setup (30 seconds)
- Navigate to `/tasks`
- Show both browser windows: one with JS enabled, one with JS disabled (or toggle DevTools setting)
- Narrate: "I'll show the HTMX path first, then no-JS path"

---

## Demo 1: HTMX Error Handling (2 min)

**Steps**:
1. Click "Edit" on task "Submit invoices"
2. Clear title field (backspace)
3. Click "Save"
4. **Narrate**: "Notice status message appears: 'Title is required. Please enter at least one character.'"
5. Open DevTools Elements panel → show `<div id="status" role="alert" aria-live="assertive">`
6. **Narrate**: "The `role=alert` ensures screen readers announce this immediately"
7. (If SR available) Turn on NVDA/Orca, repeat steps 1-3
8. **Narrate**: "NVDA announces: 'Alert. Title is required...'"

**Evidence reference**: `05-evidence/sr-transcripts/after-nvda.txt`

---

## Demo 2: No-JS Error Handling (2 min)

**Steps**:
1. Disable JavaScript (DevTools → Settings → Disable JavaScript, hard refresh)
2. Click "Edit" on same task
3. Clear title, click "Save"
4. **Narrate**: "Page reloads. Notice error summary at top with focus outline—it's automatically focused."
5. Press Tab → focus moves to error link ("Title is required")
6. Press Enter → focus moves to `#title` input
7. **Narrate**: "Keyboard-only users can now navigate to the error and fix it. Before, this wasn't possible."

**Evidence reference**: `05-evidence/screenshots/after-nojs-error.png`

---

## Demo 3: Success Path (30 seconds)

**Steps**:
1. (Still in no-JS mode) Type new title: "Submit invoices by Friday"
2. Click "Save"
3. **Narrate**: "Full page reload, task updated. PRG pattern maintains history."
4. Show updated task in list

---

## Backup (if demo fails)

**Video**: `wk11/crit/demo-recording.mp4` (pre-recorded screen capture with narration)

**Screenshots**: Walk through annotated screenshots in `05-evidence/screenshots/` with narration
```

✋ **Stop and check**:
- [ ] 5-slide outline complete
- [ ] Evidence artifacts selected (screenshots, metrics, diffs)
- [ ] Live demo script written with narration
- [ ] Backup plan ready (video or annotated screenshots)

---

## Activity B: Studio Critique Session (60 min)

**Format**: 5-6 teams × 15 min each

### Presenter Role (when it's your turn)

**Timing breakdown**:
- **0:00-0:30**: Introduce problem (1 slide)
- **0:30-5:00**: Live demo (narrated, following script)
- **5:00-8:00**: Show metrics + evidence (slides 2-4)
- **8:00-10:00**: Accessibility proof (regression checklist, SR transcript)
- **10:00-15:00**: Q&A + critique

**Presenting tips**:
- **Narrate everything**: "I'm pressing Tab, now I'm on the error link..."
- **Point to evidence**: "This is documented in line 23 of our regression checklist..."
- **Acknowledge limitations**: "We didn't have time to fix wk9-02 (filter UX)—it's in our Semester 2 backlog"
- **Invite questions**: "What would you like me to clarify?"

**During Q&A**:
- **Don't defend**: Listen, note feedback, say "good point, I'll check that"
- **Clarify if needed**: "Can you show me which screenshot you're referring to?"
- **Thank reviewers**: Even if feedback stings, they're helping you improve

**Note feedback** in `wk11/crit/feedback-received.md`:
```markdown
## Feedback from Studio Crit — 2025-10-25

### Team Alpha
- **Q**: "Have you tested with VoiceOver (macOS) or just NVDA?"
- **Response**: Only tested NVDA. Should add VoiceOver testing to backlog.
- **Action**: Create backlog item wk11-01: "Verify SR compatibility with VoiceOver"

### Team Beta
- **Observation**: "Your `aria-live=assertive` interrupted my SR mid-sentence during filter demo"
- **Suggestion**: "Try `aria-live=polite` for non-critical messages?"
- **Response**: Good catch. Will test polite vs assertive for status messages.
- **Action**: Update wk10/lab-wk10/docs/redesign-brief.md notes section

### Staff
- **Q**: "Why did you prioritise T2 over T1 when T1 had higher error rate?"
- **Response**: T2 had 0% no-JS completion (complete block) vs T1 100% completion (just slower). Prioritised exclusion over efficiency.
- **Validation**: Staff agreed with prioritization framework logic.
```

### Reviewer Role (when watching others)

**Use `wk11/crit/peer-feedback-TEAM_NAME.md` template**:

```markdown
# Peer Feedback — Team [Name]

**Reviewer**: [Your name]
**Date**: 2025-10-25

---

## Summary

**Problem addressed**: [One sentence]

**Solution**: [One sentence]

**Evidence quality**: [Rating 1-5, brief justification]

---

## Strengths (What worked well)

1. [Specific observation with evidence reference]
2. [Specific observation with evidence reference]
3. [Specific observation with evidence reference]

**Example**:
- Clear before/after metrics table showing 67% → 100% completion
- Live SR demo proved `role=alert` works (heard announcement)
- Regression checklist complete (21/22 pass—impressive)

---

## Questions / Concerns

1. [Specific question or observation]
2. [Specific question or observation]

**Example**:
- Q: "You mentioned error rate dropped from 33% to 25%. Is that still high? What's causing remaining errors?"
- Concern: "Screenshot shows error summary but I didn't see it tested with keyboard in live demo. Can you Tab to it?"

---

## Suggestions (Constructive)

Use formula: Observation + Impact + Suggestion

1. [Observation] → [Impact] → [Suggestion]
2. [Observation] → [Impact] → [Suggestion]

**Example**:
- **Observation**: Error message says "Title is required" but pilot P2 submitted blank because they didn't see the input had focus.
- **Impact**: Focus indicator might be too subtle (low contrast).
- **Suggestion**: Have you checked focus indicator contrast with WCAG contrast checker? Might need to increase from blue (#0000ff) to darker blue (#0000aa) for 3:1 ratio against white background.

---

## Backlog / Next Steps

What should this team consider for Semester 2?

- [Item 1]
- [Item 2]

**Example**:
- Test with VoiceOver (macOS) and JAWS (Windows) to verify SR compatibility beyond NVDA
- Add client-side validation hints (maxlength counter) as progressive enhancement
- Investigate why 25% error rate persists—might be focus management issue

---

## Overall Assessment

**Evidence chain completeness**: [Rating 1-5]
- 5 = Complete traceability (data → analysis → fix → verification)
- 3 = Some gaps (e.g., missing SR transcript or before metrics)
- 1 = Weak evidence (claims not backed by data)

**Inclusion impact**: [Rating 1-5]
- 5 = Clear who benefits, why, backed by pilot observations
- 3 = General statements ("helps disabled people") without specifics
- 1 = No inclusion lens evident

**WCAG compliance**: [Pass/Partial/Fail]
- Pass = Demonstrated compliance (checklist, live demo)
- Partial = Some criteria met, gaps acknowledged
- Fail = Claims compliance but no evidence

**Recommended grade** (if this were assessment submission): [Estimate 0-100]

**Justification**: [2-3 sentences linking grade to evidence quality, inclusion impact, WCAG compliance]
```

**Reviewer tips**:
- **Be specific**: Reference line numbers, file names, WCAG criteria
- **Be kind**: Assume positive intent, acknowledge constraints
- **Be useful**: Suggest alternatives, not just problems
- **Be brief**: They have limited time to read feedback—bullet points better than essays

✋ **Stop and check** (after all presentations):
- [ ] Presented your work (or scheduled if absent)
- [ ] Filled peer feedback forms for 3-5 teams
- [ ] Noted feedback received in your own feedback-received.md
- [ ] Identified action items for Week 11 Lab 2

---

## Activity C: Post-Crit Reflection and Backlog Update (15 min)

**Goal**: Process feedback and update backlog.

### Step 1: Review feedback received (5 min)

**Open `wk11/crit/feedback-received.md`** and identify themes:

**Categorize**:
- **Critical**: Issues that invalidate claims (e.g., "You said 100% completion but didn't test with SR")
- **High priority**: Valid suggestions that would significantly improve work
- **Medium**: Nice-to-haves, future work
- **Low/Out-of-scope**: Interesting but not actionable this semester

**Example categorization**:
```markdown
## Feedback Themes

### Critical (Address before assessment submission)
- Team Beta: "aria-live=assertive too intrusive" → Test polite vs assertive
- Staff: "No evidence of VoiceOver testing" → Either test or document limitation

### High Priority (Semester 2)
- Team Alpha: "Focus indicator contrast 2.8:1 (below 3:1)" → Log backlog item, fix early Sem 2
- Team Gamma: "Error rate still 25%—why?" → Investigate root cause (focus management?)

### Medium
- Team Delta: "Add maxlength counter" → Progressive enhancement, nice-to-have

### Low/Out-of-Scope
- Team Epsilon: "Redesign entire UI with dark mode" → Out of scope for this module
```

### Step 2: Update backlog (5 min)

**Add new items** to `backlog/backlog.csv`:

```csv
wk11-01,11,high,a11y,"Verify SR compatibility with VoiceOver (macOS)",,"open","wk11/crit/feedback-received.md Team Alpha","Test with VoiceOver, document findings",false

wk11-02,11,medium,a11y,"Focus indicator contrast below WCAG AAA (2.8:1)",1.4.11,open,"wk11/crit/feedback-received.md Team Beta; WCAG 2.2 Focus Appearance","Increase focus outline to darker blue (#0000aa) for 3:1 contrast",true

wk11-03,11,high,ux,"Remaining 25% error rate on T2—investigate cause",,"open","submission-template.md Section 5 Part B; wk11/crit/feedback-received.md Staff","Run additional pilot focusing on error triggers, check focus management",false
```

**Mark completed feedback actions**:
```csv
wk9-01,9,high,a11y,"Validation errors not announced by SR",4.1.3,fixed,"data/metrics.csv; evidence/sr-transcripts/after-nvda.txt","Addressed in Week 10 Lab 2: added role=alert + aria-live=assertive",true
```

### Step 3: Write reflection (5 min)

**Update `wk11/reflection.md`**:

```markdown
# Week 11 Lab 1 Reflection — Studio Crit

## Presentation Experience

**What went well**:
- Live demo worked smoothly (both HTMX and no-JS paths)
- Metrics table clearly showed 67% → 100% improvement
- Audience understood the inclusion impact (no-JS users excluded → now included)

**What was challenging**:
- Nerves made me rush through SR demo—should have slowed down to let audience hear announcement
- Didn't anticipate VoiceOver compatibility question—assumed NVDA coverage was sufficient
- Q&A revealed gap: didn't investigate why error rate only improved to 25% (not <10% as hoped)

## Feedback Received

**Most valuable critique**:
Team Beta's observation about `aria-live=assertive` being too intrusive. I experienced this myself during testing but dismissed it—their feedback validated my concern. Will test `polite` alternative.

**Most surprising critique**:
Staff asked why I prioritised T2 over T1 when T1 had higher error rate. I explained: T2 had 0% no-JS completion (exclusion) vs T1 100% completion (efficiency). Staff agreed but noted I should have made this explicit in presentation. Lesson: state prioritization criteria upfront.

**Hardest to hear**:
Team Alpha noted focus indicator contrast issue (2.8:1, below 3:1). I checked this in Week 8 but forgot to fix it. Embarrassing oversight—logged wk11-02 to address.

## Changes for assessment Submission

Based on feedback:
1. **Add VoiceOver testing note** to `02-regression-checklist.csv`: "Tested with NVDA (Windows) and Orca (Linux). VoiceOver (macOS) not tested—recommend for Semester 2."
2. **Investigate `aria-live=polite`** alternative: Run quick test, update `01-redesign-brief.md` notes if polite works better
3. **Document focus indicator issue**: Add note to `03-before-after-summary.md`: "Known issue: focus indicator contrast 2.8:1 (WCAG AAA). Logged wk11-02 for Semester 2 fix."

## Giving Feedback

**Teams I reviewed**: Alpha, Beta, Gamma

**Most interesting observation**:
Team Gamma used UMUX-Lite subjective measure (2 questions, 1-7 scale). We didn't—might add for Semester 2 to capture perceived usability alongside objective metrics.

**Common pattern**:
3/5 teams struggled with no-JS demos (forgot to disable JS, or JS disabled but cached). Reinforces importance of verification scripts like `wk08/lab-w8/scripts/nojs-check.md`.

## Next Steps

**Before Lab 2** (Final submissions):
1. Test `aria-live=polite` vs `assertive` (30 min)
2. Add VoiceOver limitation note to regression checklist (5 min)
3. Document focus indicator issue in before-after summary (10 min)
4. Final proofread of assessment bundle (30 min)

**Semester 2 priorities** (based on backlog):
- Fix focus indicator contrast (wk11-02)
- Investigate remaining error rate (wk11-03)
- Test VoiceOver compatibility (wk11-01)
- Add progressive enhancement hints (maxlength counter, real-time char count)
```

✋ **Stop and check**:
- [ ] Feedback themes identified and categorized
- [ ] Backlog updated with new items from critique
- [ ] Reflection written (what went well, challenges, changes for submission)
- [ ] Action items for Week 11 Lab 2 clear

---

## Commit & Reflect (5 min)

### Commit message

```bash
git add wk11/crit/ backlog/backlog.csv wk11/reflection.md

git commit -m "$(cat <<'EOF'
wk11s1: studio crit completed, feedback processed

- Presented inclusive redesign with live demo (HTMX + no-JS paths)
- Demonstrated accessibility improvements: role=alert SR announcement, keyboard-navigable error summary
- Showed before/after metrics: T2 completion 67% → 100%, no-JS parity restored
- Received feedback from 3 peer teams + staff (documented in feedback-received.md)
- Identified critical action items: test aria-live=polite, document VoiceOver limitation, note focus contrast issue
- Provided constructive feedback to 3 peer teams (Alpha, Beta, Gamma)
- Updated backlog with new items: wk11-01 (VoiceOver testing), wk11-02 (focus contrast), wk11-03 (error rate investigation)
- Reflected on presentation experience and critique process

Key takeaways:
- Evidence-led presentation effective (audience understood inclusion impact from metrics)
- Need to slow down during SR demos (rushed, hard for audience to hear)
- Prioritization criteria should be explicit upfront (T2 exclusion vs T1 efficiency)
- Focus indicator contrast oversight embarrassing but fixable (logged for Semester 2)

Ready for Week 11 Lab 2 final refinements and submission prep.


EOF
)"
```

---

## Looking Ahead: Week 11 Lab 2

Final session:
- **Polish assessment & 2** based on crit feedback
- **Assemble portfolio** with evidence chains
- **Practice submission** process (Gradescope upload, file check)
- **Module reflection** and Semester 2 planning

**Before Lab 2**:
- Address critical feedback (test polite, document limitations)
- Final proofread all submission files
- Check Gradescope submission requirements (file formats, naming)

---

## Further Reading & Resources

### Essential
- Review assessment bundle for gaps
- [IDEO: The Art of Critique](https://www.ideo.com/blog/design-critique)

### Presentation Skills
- [GOV.UK: Show the thing](https://gds.blog.gov.uk/2016/11/18/what-we-mean-when-we-say-show-the-thing/)
- [Nielsen: Presenting Usability Findings](https://www.nngroup.com/articles/presenting-findings/)

### Constructive Feedback
- [Kim Scott: Radical Candor](https://www.radicalcandor.com/)
- [Harvard Business Review: The Feedback Fallacy](https://hbr.org/2019/03/the-feedback-fallacy)

### Accessibility Demonstration
- [WebAIM: Demonstrating Accessibility](https://webaim.org/articles/demos/)
- [Deque: Screen Reader Demo Best Practices](https://www.deque.com/blog/screen-reader-demonstration-best-practices/)

---

## Glossary Summary

| Term | One-line definition |
|------|---------------------|
| **Design critique** | Structured peer review of design work; focuses on evidence, not person |
| **Evidence-led presentation** | Every claim backed by artifacts (data, screenshots, quotes) |
| **Accessibility demonstration** | Live proof that inclusive design works (show, don't tell) |
| **Constructive feedback** | Observation + Impact + Suggestion (not just criticism) |
| **Studio crit** | Group critique session common in design education and practice |
| **Before/after comparison** | Showing improvement through quantitative metrics |
| **Regression checklist** | Systematic verification that fixes didn't break other features |
| **Evidence chain** | Traceability from data → analysis → fix → verification |
| **Backlog refinement** | Updating backlog based on new feedback and discoveries |

---

**Lab complete!** You've presented your work with evidence, received constructive critique, and prepared for assessment refinement in Week 11 Lab 2.
