# Week 11 • Lab 1 — Student Guide: Evidence-Led Studio Crit

![COMP2850](https://img.shields.io/badge/COMP2850-HCI-blue)
![Week 11](https://img.shields.io/badge/Week-11-orange)
![Lab 1](https://img.shields.io/badge/Lab-1-green)
![Guide](https://img.shields.io/badge/Type-Student_Guide-purple)

> **Purpose**: Week 11 Lab 1 is a peer critique session where you present your work (Week 6-10 journey), receive feedback, and refine your submissions before final deadline.

---

## Deliverables

- ✅ Crit presentation (5-7 minutes)
- ✅ Peer feedback notes (`wk11/crit/feedback-received.md`)
- ✅ Action plan from feedback (`wk11/crit/action-plan.md`)

---

## Part 1: Prepare Crit Presentation (30 minutes)

**Create** `wk11/crit/presentation-outline.md`:

```markdown
# Studio Crit Presentation Outline

## Slide 1: Overview (30 seconds)
**What I built**: Task manager with server-first architecture, HTMX progressive enhancement, WCAG 2.2 AA compliance

**Key features**: Add, edit, delete tasks + dual-mode (HTMX/no-JS) + accessibility (keyboard, screen reader)

---

## Slide 2: Needs-Finding (Week 6) (1 minute)
**Process**: Peer interviews → 5 job stories → inclusive backlog

**Example insight**: "When I submit a form, I want confirmation it worked so I don't refresh to check" (4/5 participants)

---

## Slide 3: Implementation Journey (Weeks 7-8) (1.5 minutes)
**Week 7**: Inline edit, ethics documentation, accessibility audit (axe + manual)

**Week 8**: Pagination, filtering, template partials

**Key challenge**: Maintaining dual-path parity (HTMX vs no-JS)

---

## Slide 4: Evaluation (Week 9) (1.5 minutes)
**Method**: 5 task-based pilots (3 HTMX, 2 no-JS)

**Quantitative**: 100% success on 3/4 tasks, 80% on inline edit (no-JS issue)

**Qualitative**: "Confirmation feedback critical" (4/5 participants)

**Screenshot**: Show pilot setup or data table

---

## Slide 5: Redesign (Week 10) (1.5 minutes)
**Priority fixes**:
1. Added no-JS confirmation message (→ confidence improved)
2. Fixed edit inline no-JS failure (→ 100% success)
3. Clarified Cancel button label

**Before/After**: Screenshot comparison

---

## Slide 6: Reflection & Questions (1 minute)
**What I learned**: Dual-path architecture is hard but essential for inclusion

**Trade-off I'd revisit**: Delete confirmation (no-JS has none - would add confirmation page if more time)

**Question for peers**: How did you handle no-JS error messages?

---

**Total**: ~7 minutes (leave 3 min for questions)
```

**Checklist**:
- [ ] Presentation outline written (6 slides max)
- [ ] Screenshots selected
- [ ] Practice run (time yourself)

---

## Part 2: Give/Receive Feedback (30 minutes)

### During Others' Presentations
**Take notes** in `wk11/crit/feedback-to-give.md`:

```markdown
# Feedback for [Peer Name]

## Strengths
- [What worked well? Specific examples]

## Questions
- [Clarifications needed]

## Suggestions
- [Constructive improvements, backed by WCAG or research]

---

**Feedback format**: "I noticed [observation]. Have you considered [suggestion]? This could help with [benefit]."

**Example**: "I noticed your delete has no confirmation in no-JS mode. Have you considered adding a /confirm page? This would meet WCAG 3.3.4 Error Prevention."
```

### When Receiving Feedback
**Record** in `wk11/crit/feedback-received.md`:

```markdown
# Feedback Received — Studio Crit

## From: [Peer 1]
**Strength**: "Dual-path architecture thoroughly documented"

**Question**: "How did you test screen reader with no-JS mode?"

**Suggestion**: "Consider adding focus management after delete (where does focus go?)"

---

## From: [Peer 2]
[Repeat]

---

## From: Staff
[Note any staff feedback]
```

**Checklist**:
- [ ] Gave feedback to 2-3 peers
- [ ] Recorded all feedback received
- [ ] Noted actionable items

---

## Part 3: Create Action Plan (20 minutes)

**Create** `wk11/crit/action-plan.md`:

```markdown
# Action Plan from Studio Crit

## High Priority (Do Before Submission)
- [ ] **Focus management after delete** (Peer 1 suggestion)
  - Current: Focus lost after delete
  - Fix: Move focus to next task or "Add Task" button
  - Effort: 30 min
  - Deadline: [Date]

- [ ] **Screen reader testing documentation** (Peer 1 question)
  - Add NVDA output to evidence folder
  - Test no-JS path explicitly
  - Effort: 20 min

---

## Medium Priority (If Time Permits)
- [ ] **Delete confirmation page for no-JS** (Peer 2)
  - Would improve WCAG 3.3.4 compliance
  - Effort: 1-2 hours
  - If not done: Document as future work in assessment

---

## Low Priority (Noted for Portfolio)
- [ ] **Progress indicator** (multiple peers mentioned)
  - Nice-to-have, not WCAG requirement
  - Defer to portfolio "future enhancements" section

---

## Not Actioning (With Rationale)
- **[Suggestion]**: [Why not implementing - time, scope, or disagreement with reasoning]
```

**Checklist**:
- [ ] Action items prioritised
- [ ] Deadlines set for high-priority items
- [ ] Rationale provided for deferred/rejected items

---

## Commit & Continue

```bash
git add wk11/
git commit -m "feat(wk11-lab1): studio crit presentation and feedback

- Prepared 7-minute presentation covering Weeks 6-10 journey
- Received feedback from 3 peers + staff
- Created action plan with prioritised improvements
- High-priority: focus management, SR testing documentation

Ready for final refinements in Week 11 Lab 2."
```

**Next**: Week 11 Lab 2 - Final wrap-up, portfolio, submission readiness.
