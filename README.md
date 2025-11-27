# COMP2850 Task Manager

Clean working implementation of COMP2850 task management application.

## Quick Start

```bash
./gradlew run
```

Navigate to **http://localhost:8080/tasks**

## What's Included

- Task CRUD operations (create, read, update, delete)
- Search and pagination
- Server-side validation
- Logger for Week 9 assessment metrics collection
- HTMX progressive enhancement
- No-JavaScript fallback mode

## For Week 9-10 Assessment

This code is ready for your HCI evaluation work:

1. **Logger Setup**: Metrics automatically logged to `data/metrics.csv`
2. **Session IDs**: Set in browser console: `document.cookie = "sid=P1_xxxx"`
3. **All features work**: Add, edit, delete, search, pagination
4. **No-JS mode**: Works with JavaScript disabled

Focus on evaluation planning, pilot testing, and analysis.

## Requirements

- Java 21+
- Gradle (included via wrapper)

## Stopping the Server

Press `Ctrl+C` in the terminal running the application.
