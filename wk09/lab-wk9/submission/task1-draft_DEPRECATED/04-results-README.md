# Results Data

**File**: `04-results.csv`

**Purpose**: Raw quantitative data from peer pilot sessions, captured via server-side instrumentation.

---

## CSV Schema

**Column Definitions**:

| Column | Type | Description | Example Values |
|--------|------|-------------|----------------|
| `ts_iso` | Timestamp (ISO 8601 UTC) | Event timestamp | `2025-10-15T14:18:23.456Z` |
| `session_id` | String | Anonymous participant identifier | `P1_7a9f`, `P2_4d8e` |
| `request_id` | String | Request trace ID for debugging | `r001`, `r002` |
| `task_code` | String | Task identifier | `T1_filter`, `T2_edit`, `T3_add`, `T4_delete` |
| `step` | Enum | Event type | `success`, `validation_error`, `fail` |
| `outcome` | String | Specific error type (if applicable) | `blank_title`, `max_length`, empty for success |
| `ms` | Integer | Duration in milliseconds | `567`, `1847` |
| `http_status` | Integer | HTTP response code | `200`, `400`, `500` |
| `js_mode` | Enum | JavaScript availability | `on`, `off` |

---

## Data Collection Method

- **Instrumentation**: Server-side logging via `Logger.kt` (Week 9 Lab 1)
- **Anonymization**: Session IDs generated randomly (no PII)
- **Privacy**: UK GDPR compliant (Data Protection Act 2018)
- **Storage**: Local CSV files only (no external services)

---

## Session Information

**To be completed after pilots**:

| Session ID | Variant | JS Mode | Date | Notes |
|------------|---------|---------|------|-------|
| P1_xxxx | Standard (HTMX, mouse) | on | YYYY-MM-DD | [Any issues?] |
| P2_xxxx | Keyboard-only | on | YYYY-MM-DD | [Any issues?] |
| P3_xxxx | No-JS | off | YYYY-MM-DD | [Any issues?] |
| P4_xxxx | Standard | on | YYYY-MM-DD | [Any issues?] |
| P5_xxxx | Screen reader (optional) | on | YYYY-MM-DD | [Any issues?] |

---

## Example Data

```csv
ts_iso,session_id,request_id,task_code,step,outcome,ms,http_status,js_mode
2025-10-15T14:18:23.456Z,P1_7a9f,r001,T3_add,success,,567,200,on
2025-10-15T14:19:45.789Z,P1_7a9f,r002,T1_filter,success,,1847,200,on
2025-10-15T14:21:12.123Z,P1_7a9f,r003,T2_edit,validation_error,blank_title,0,400,on
2025-10-15T14:21:34.456Z,P1_7a9f,r004,T2_edit,success,,1234,200,on
2025-10-15T14:22:10.789Z,P1_7a9f,r005,T4_delete,success,,210,200,on
```

---

## Data Quality Notes

**Document any anomalies, exclusions, or data quality issues here**:

- [ ] All session IDs present (P1, P2, P3, ...)
- [ ] All task codes present (T1, T2, T3, T4) per session
- [ ] Timestamps in chronological order
- [ ] JS mode matches variant (off for no-JS pilots)
- [ ] Durations plausible (not negative, not absurdly high)

**Anomalies**:
- [To be completed: e.g., "Pilot 4, T2: Missing log entry—server crashed, used stopwatch: 17s"]

**Exclusions**:
- [To be completed: e.g., "None (all data usable)" or list excluded data]

---

## Analysis

**Summary statistics**: See `05-findings.md` for:
- Completion rates per task
- Median times (with MAD)
- Error rates
- JS-on vs JS-off comparison
- Accessibility observations

**Next steps**: Week 10 Lab 1 will use `Analyse.kt` script for automated statistical analysis.
