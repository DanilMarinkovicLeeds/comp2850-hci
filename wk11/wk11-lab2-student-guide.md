# Week 11 • Lab 2 — Student Guide: Wrap-Up, Portfolio & Submission

![COMP2850](https://img.shields.io/badge/COMP2850-HCI-blue)
![Week 11](https://img.shields.io/badge/Week-11-orange)
![Lab 2](https://img.shields.io/badge/Lab-2-green)
![Guide](https://img.shields.io/badge/Type-Student_Guide-purple)

> **Purpose**: Week 11 Lab 2 is about final refinements from studio crit feedback, assembling your portfolio, and ensuring assessment & assessment are ready for review.

---

## Deliverables

- ✅ Crit action items completed
- ✅ assessment assessment refinement (`submission-template.md + evidence/`)
- ✅ assessment assessment refinement (`submission-template.md + evidence/`)
- ✅ Portfolio assembled (`wk11/portfolio/`)
- ✅ Submission checklist verified

---

## Part 1: Complete Crit Action Items (30 minutes)

From `wk11/crit/action-plan.md`, complete high-priority items:

### Example: Add Focus Management After Delete

**Code change** (`TaskRoutes.kt`):
```kotlin
post("/tasks/{id}/delete") {
    // ... delete logic ...

    if (call.isHtmx()) {
        // Instead of empty response, return focus hint
        val status = """
            <div id="status" hx-swap-oob="true">Task deleted.</div>
            <script>document.getElementById('title')?.focus()</script>
        """
        call.respondText(status, ContentType.Text.Html)
    } else {
        call.respondRedirect("/tasks")
    }
}
```

**Document** in `wk11/refinements.md`:
```markdown
# Post-Crit Refinements

## 1. Focus Management After Delete
**Issue**: Focus lost after delete (Peer 1 feedback)
**Fix**: Move focus to "Add Task" input
**Evidence**: Screenshot + NVDA test showing focus announcement
**Time**: 25 minutes
```

**Checklist**:
- [ ] All high-priority actions completed
- [ ] Retested with accessibility tools
- [ ] Documented in refinements file

---

## Part 2: Finalize assessment (30 minutes)

**Copy** `wk09/assessment/` → `submission-template.md + evidence/`

### Final Checks

- [ ] **Section 1 (Evaluation Plan)**: Tasks, metrics, protocol complete
- [ ] **Section 2 (Findings)**: Quant tables + qual themes + accessibility observations
- [ ] **Section 3 (Evidence Chains)**: All findings link data → backlog → WCAG
- [ ] **Section 4 (Reflection)**: Process critique + limitations + next steps
- [ ] **Word count**: 2500-3000 words
- [ ] **References**: All WCAG, academic sources cited
- [ ] **Evidence folder**: Screenshots, pilot notes, CSV data

### Quality Check

- [ ] No "user/users" terminology (person-first language throughout)
- [ ] UK spelling (organised, analyse, centre, colour)
- [ ] All screenshots labeled and referenced in text
- [ ] Tables formatted consistently
- [ ] Backlog CSV included

**Create** `submission-template.md + evidence/submission-checklist.md`:

```markdown
# assessment Submission Checklist

- [ ] Main document: PDF, 2500-3000 words
- [ ] Appendix A: Evaluation protocol (`wk09/research/protocol.md`)
- [ ] Appendix B: Pilot notes (`wk09/data/pilot-notes.md`)
- [ ] Appendix C: Evidence folder (screenshots, SR output)
- [ ] Appendix D: Updated backlog CSV
- [ ] All files uploaded to Gradescope
- [ ] Submitted before deadline: [YYYY-MM-DD HH:MM]
```

---

## Part 3: Finalize assessment (30 minutes)

**Copy** `wk10/assessment/` → `submission-template.md + evidence/`

### Final Checks

- [ ] **Section 1 (Redesign Rationale)**: Priority selection + Week 9 evidence
- [ ] **Section 2 (Implementation)**: Before/after code + screenshots + WCAG mapping
- [ ] **Section 3 (Verification)**: axe re-scan + manual WCAG + regression testing
- [ ] **Section 4 (Reflection)**: Process critique + trade-offs + future work
- [ ] **Word count**: 2000-2500 words
- [ ] **Code snippets**: Syntax-highlighted, properly formatted
- [ ] **Evidence folder**: Before/after screenshots, axe reports

### Quality Check

Same as assessment plus:
- [ ] Code changes clearly explained
- [ ] Trade-offs justified
- [ ] WCAG criteria explicitly referenced

---

## Part 4: Assemble Portfolio (40 minutes)

**Create** `wk11/portfolio/README.md`:

```markdown
# COMP2850 HCI Portfolio — [Your Name]

## Overview

This portfolio documents my HCI journey from Week 6 (needs-finding) to Week 11 (studio crit). Key themes: dual-path architecture, accessibility (WCAG 2.2 AA), evaluation-driven redesign.

---

## Week-by-Week Highlights

### Week 6: Foundations
- **Lab 1**: Built server-first task manager with HTMX progressive enhancement
- **Lab 2**: Conducted peer interviews, created 5 job stories, built inclusive backlog
- **Key learning**: Person-first language, privacy-by-design

### Week 7: Accessibility
- **Lab 1**: Implemented inline edit with accessible focus management
- **Lab 2**: Ran axe audit (found 5 violations), fixed 3 priority issues
- **Key learning**: WCAG 2.2 AA compliance requires systematic auditing

### Week 8: Scaling (student guide used)
- Pagination, filtering, template partials
- Bug fix: Add Task form target changed for pagination

### Week 9: Evaluation
- **Lab 1**: Created evaluation plan (4 tasks, 7 metrics, ethics protocol)
- **Lab 2**: Ran 5 pilots, analysed findings, drafted assessment
- **Key learning**: Small sample (n=5) still reveals critical usability issues

### Week 10: Redesign
- **Lab 1**: Prioritised 3 fixes based on Week 9 data
- **Lab 2**: Implemented fixes, re-verified, drafted assessment
- **Key learning**: Trade-offs inevitable (e.g., no-JS delete confirmation deferred)

### Week 11: Synthesis
- **Lab 1**: Studio crit, received peer feedback, created action plan
- **Lab 2**: Final refinements, submission prep

---

## Key Artefacts

- **Backlog**: `backlog/backlog.csv` (evolved from Week 6 to Week 11)
- **Evidence**: `wk*/evidence/` folders (screenshots, axe reports, pilot notes)
- **Code**: Git commits show incremental development
- **Documentation**: Protocol, task scenarios, findings analysis

---

## Reflection

**Biggest challenge**: Maintaining dual-path parity (HTMX vs no-JS) while adding features

**Most rewarding**: Week 9 pilots - seeing real people use my prototype and learning from their struggles

**What I'd do differently**: Start instrumentation earlier (Week 7) to capture more longitudinal data

**Professional growth**: Understanding that accessibility isn't a checklist - it's about inclusion from day one

---

## Future Enhancements (Semester 2)

- Progress indicator (motivational feedback)
- Filter persistence across sessions (reduce cognitive load)
- Delete confirmation page for no-JS (WCAG 3.3.4)
- Automated no-JS tests (Playwright)

---

**Repository**: [Link to your Git repo if public]
**Gradescope**: assessment [submission link], assessment [submission link]
```

**Checklist**:
- [ ] Portfolio README written
- [ ] Week highlights summarised
- [ ] Key artefacts listed
- [ ] Reflection thoughtful

---

## Part 5: Final Submission Checklist (20 minutes)

**Create** `wk11/FINAL-SUBMISSION-CHECKLIST.md`:

```markdown
# FINAL SUBMISSION CHECKLIST

## assessment: Evaluation & Findings
- [ ] Main document (PDF): 2500-3000 words
- [ ] All 4 sections complete
- [ ] Evidence folder included
- [ ] Uploaded to Gradescope
- [ ] Deadline: [YYYY-MM-DD HH:MM]

## assessment: Redesign & Verification
- [ ] Main document (PDF): 2000-2500 words
- [ ] Before/after code + screenshots
- [ ] Evidence folder included
- [ ] Uploaded to Gradescope
- [ ] Deadline: [YYYY-MM-DD HH:MM]

## Portfolio (Optional)
- [ ] README.md comprehensive
- [ ] Week highlights documented
- [ ] Reflection included

## Git Repository
- [ ] All code committed
- [ ] No sensitive data (check .gitignore)
- [ ] README with setup instructions
- [ ] Tags: `week6-baseline`, `week10-final`

## Quality Assurance
- [ ] Person-first language throughout
- [ ] UK spelling
- [ ] All WCAG references correct (e.g., 2.1.1 not 2.11)
- [ ] Screenshots labeled and captioned
- [ ] Code snippets syntax-highlighted

## Final Checks
- [ ] Printed/saved backup copy
- [ ] Submission confirmation email received
- [ ] Celebrated completion! 🎉
```

---

## Commit & Celebrate

```bash
git add wk11/ wk*/task*-final/
git commit -m "feat(wk11-lab2): assessment refinement ready

- Completed post-crit action items (focus management)
- Finalized assessment (evaluation & findings)
- Finalized assessment (redesign & verification)
- Assembled portfolio with week-by-week highlights
- All quality checks passed

COMP2850 HCI complete!"

git tag week11-final-submission
git push --tags
```

**Congratulations on completing COMP2850 HCI!** 🎓

