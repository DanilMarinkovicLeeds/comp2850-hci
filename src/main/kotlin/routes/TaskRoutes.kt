package routes

<<<<<<< HEAD
import data.TaskRepository
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.pebbletemplates.pebble.PebbleEngine
import java.io.StringWriter

/**
 * NOTE FOR NON-INTELLIJ IDEs (VSCode, Eclipse, etc.):
 * IntelliJ IDEA automatically adds imports as you type. If using a different IDE,
 * you may need to manually add imports. The commented imports below show what you'll need
 * for future weeks. Uncomment them as needed when following the lab instructions.
 *
 * When using IntelliJ: You can ignore the commented imports below - your IDE will handle them.
 */

// Week 7+ imports (inline edit, toggle completion):
// import model.Task               // When Task becomes separate model class
// import model.ValidationResult   // For validation errors
// import renderTemplate            // Extension function from Main.kt
// import isHtmxRequest             // Extension function from Main.kt

// Week 8+ imports (pagination, search, URL encoding):
// import io.ktor.http.encodeURLParameter  // For query parameter encoding
// import utils.Page                       // Pagination helper class

// Week 9+ imports (metrics logging, instrumentation):
// import utils.jsMode              // Detect JS mode (htmx/nojs)
// import utils.logValidationError  // Log validation failures
// import utils.timed               // Measure request timing

// Note: Solution repo uses storage.TaskStore instead of data.TaskRepository
// You may refactor to this in Week 10 for production readiness

/**
 * Week 6 Lab 1: Simple task routes with HTMX progressive enhancement.
 *
 * **Teaching approach**: Start simple, evolve incrementally
 * - Week 6: Basic CRUD with Int IDs
 * - Week 7: Add toggle, inline edit
 * - Week 8: Add pagination, search
 */

fun Route.taskRoutes() {
    val pebble =
        PebbleEngine
            .Builder()
            .loader(
                io.pebbletemplates.pebble.loader.ClasspathLoader().apply {
                    prefix = "templates/"
                },
            ).build()

    /**
     * Helper: Check if request is from HTMX
     */
    fun ApplicationCall.isHtmx(): Boolean = 
        request.headers["HX-Request"]?.equals("true", ignoreCase = true) == true

    /**
     * GET /tasks - List all tasks
     * Returns full page (no HTMX differentiation in Week 6)
     */
    get("/tasks") {
        val model =
            mapOf(
                "title" to "Tasks",
                "tasks" to TaskRepository.all(),
            )
        val template = pebble.getTemplate("tasks/index.peb")
        val writer = StringWriter()
        template.evaluate(writer, model)
        call.respondText(writer.toString(), ContentType.Text.Html)
    }

    /**
     * POST /tasks - Add new task
     * Dual-mode: HTMX fragment or PRG redirect
     */
    post("/tasks") {
        val title = call.receiveParameters()["title"].orEmpty().trim()

        if (title.isBlank()) {
            // Validation error handling
            if (call.isHtmx()) {
                val error = """<div id="status" hx-swap-oob="true" role="alert" aria-live="assertive">
                    Title is required. Please enter at least one character.
                </div>"""
                return@post call.respondText(error, ContentType.Text.Html, HttpStatusCode.BadRequest)
            } else {
                // No-JS: redirect back (could add error query param)
                call.response.headers.append("Location", "/tasks")
                return@post call.respond(HttpStatusCode.SeeOther)
            }
        }

        val task = TaskRepository.add(title)

        if (call.isHtmx()) {
            // Return HTML fragment for new task
            val fragment = """<li id="task-${task.id}">
                <span>${task.title}</span>
                <form action="/tasks/${task.id}/delete" method="post" style="display: inline;"
                      hx-post="/tasks/${task.id}/delete"
                      hx-target="#task-${task.id}"
                      hx-swap="outerHTML">
                  <button type="submit" aria-label="Delete task: ${task.title}">Delete</button>
                </form>
            </li>"""

            val status = """<div id="status" hx-swap-oob="true">Task "${task.title}" added successfully.</div>"""

            return@post call.respondText(fragment + status, ContentType.Text.Html, HttpStatusCode.Created)
        }

        // No-JS: POST-Redirect-GET pattern (303 See Other)
        call.response.headers.append("Location", "/tasks")
        call.respond(HttpStatusCode.SeeOther)
    }

    /**
     * POST /tasks/{id}/delete - Delete task
     * Dual-mode: HTMX empty response or PRG redirect
     */
    post("/tasks/{id}/delete") {
        val id = call.parameters["id"]?.toIntOrNull()
        val removed = id?.let { TaskRepository.delete(it) } ?: false

        if (call.isHtmx()) {
            val message = if (removed) "Task deleted." else "Could not delete task."
            val status = """<div id="status" hx-swap-oob="true">$message</div>"""
            // Return empty content to trigger outerHTML swap (removes the <li>)
            return@post call.respondText(status, ContentType.Text.Html)
        }

        // No-JS: POST-Redirect-GET pattern (303 See Other)
        call.response.headers.append("Location", "/tasks")
        call.respond(HttpStatusCode.SeeOther)
    }

    // TODO: Week 7 Lab 1 Activity 2 Steps 2-5
    // Add inline edit routes here
    // Follow instructions in mdbook to implement:
    // - GET /tasks/{id}/edit - Show edit form (dual-mode)
    // - POST /tasks/{id}/edit - Save edits with validation (dual-mode)
    // - GET /tasks/{id}/view - Cancel edit (HTMX only)
}
=======
import io.ktor.http.ContentType
import io.ktor.http.HttpStatusCode
import io.ktor.http.encodeURLParameter
import io.ktor.server.application.ApplicationCall
import io.ktor.server.application.call
import io.ktor.server.request.receiveParameters
import io.ktor.server.response.respond
import io.ktor.server.response.respondRedirect
import io.ktor.server.response.respondText
import io.ktor.server.routing.Routing
import io.ktor.server.routing.delete
import io.ktor.server.routing.get
import io.ktor.server.routing.post
import isHtmxRequest
import model.Task
import model.ValidationResult
import renderTemplate
import storage.TaskStore
import utils.Page
import utils.jsMode
import utils.logValidationError
import utils.timed

private const val PAGE_SIZE = 10

private data class PaginatedTasks(
    val page: Page<Map<String, Any>>,
    val context: Map<String, Any>,
)

fun Routing.configureTaskRoutes(store: TaskStore = TaskStore()) {
    get("/tasks") { call.handleTaskList(store) }
    get("/") { call.respondRedirect("/tasks") }
    get("/tasks/fragment") { call.handleTaskFragment(store) }
    post("/tasks") { call.handleCreateTask(store) }
    get("/tasks/{id}/edit") { call.handleEditTask(store) }
    post("/tasks/{id}/edit") { call.handleUpdateTask(store) }
    get("/tasks/{id}/view") { call.handleViewTask(store) }
    post("/tasks/{id}/toggle") { call.handleToggleTask(store) }
    delete("/tasks/{id}") { call.handleDeleteTask(store) }  // HTMX path (RESTful)
    post("/tasks/{id}/delete") { call.handleDeleteTask(store) }  // No-JS fallback
    get("/tasks/search") { call.handleSearchTasks(store) }
}

/**
 * Week 8: Handle paginated task list view with HTMX fragment support.
 */
private suspend fun ApplicationCall.handleTaskList(store: TaskStore) {
    timed("T0_list", jsMode()) {
        val query = requestedQuery()
        val page = requestedPage()
        val paginated = paginateTasks(store, query, page)
        val html = renderTemplate("tasks/index.peb", paginated.context)
        respondText(html, ContentType.Text.Html)
    }
}

/**
 * Week 8: Handle task fragment route for live filtering + pagination updates.
 */
private suspend fun ApplicationCall.handleTaskFragment(store: TaskStore) {
    timed("T1_filter", jsMode()) {
        val query = requestedQuery()
        val page = requestedPage()
        if (!isHtmxRequest()) {
            respondRedirect(redirectPath(query, page))
            return@timed
        }

        val paginated = paginateTasks(store, query, page)
        val statusHtml = filterStatusFragment(query, paginated.page.totalItems)
        respondTaskArea(paginated, statusHtml)
    }
}

/**
 * Week 7 & 9: Create task, log instrumentation, refresh task area.
 */
private suspend fun ApplicationCall.handleCreateTask(store: TaskStore) {
    timed("T3_add", jsMode()) {
        val params = receiveParameters()
        val title = params["title"]?.trim() ?: ""
        val query = params["q"].toQuery()

        when (val validation = Task.validate(title)) {
            is ValidationResult.Error -> handleCreateTaskError(store, title, query, validation)
            ValidationResult.Success -> handleCreateTaskSuccess(store, title, query)
        }
    }
}

private suspend fun ApplicationCall.handleCreateTaskError(
    store: TaskStore,
    title: String,
    query: String,
    validation: ValidationResult.Error,
) {
    val outcome =
        when {
            title.isBlank() -> "blank_title"
            title.length < Task.MIN_TITLE_LENGTH -> "min_length"
            title.length > Task.MAX_TITLE_LENGTH -> "max_length"
            else -> "invalid_title"
        }
    logValidationError("T3_add", outcome)
    if (isHtmxRequest()) {
        val paginated = paginateTasks(store, query, 1)
        val statusHtml = messageStatusFragment(validation.message, isError = true)
        respondTaskArea(paginated, statusHtml)
    } else {
        response.headers.append("Location", redirectPath(query, 1))
        respond(HttpStatusCode.SeeOther)
    }
}

private suspend fun ApplicationCall.handleCreateTaskSuccess(
    store: TaskStore,
    title: String,
    query: String,
) {
    val task = Task(title = title)
    store.add(task)

    if (isHtmxRequest()) {
        val paginated = paginateTasks(store, query, 1)
        val statusHtml =
            messageStatusFragment(
                """Task "${task.title}" added successfully.""",
            )
        respondTaskArea(paginated, statusHtml, htmxTrigger = "task-added")
    } else {
        response.headers.append("Location", redirectPath(query, 1))
        respond(HttpStatusCode.SeeOther)
    }
}

/**
 * Handle task toggle (mark complete/incomplete).
 */
private suspend fun ApplicationCall.handleToggleTask(store: TaskStore) {
    timed("T2_edit", jsMode()) {
        val id =
            parameters["id"] ?: run {
                respond(HttpStatusCode.BadRequest, "Missing task ID")
                return@timed
            }

        val updated = store.toggleComplete(id)

        if (updated == null) {
            respond(HttpStatusCode.NotFound, "Task not found")
            return@timed
        }

        if (isHtmxRequest()) {
            val taskHtml =
                renderTemplate(
                    "tasks/_item.peb",
                    mapOf("task" to updated.toPebbleContext()),
                )

            val statusText = if (updated.completed) "marked complete" else "marked incomplete"
            val statusHtml =
                messageStatusFragment(
                    """Task "${updated.title}" $statusText.""",
                )

            respondText(taskHtml + "\n" + statusHtml, ContentType.Text.Html)
        } else {
            response.headers.append("Location", "/tasks")
            respond(HttpStatusCode.SeeOther)
        }
    }
}

/**
 * Handle task deletion.
 */
private suspend fun ApplicationCall.handleDeleteTask(store: TaskStore) {
    timed("T4_delete", jsMode()) {
        val id =
            parameters["id"] ?: run {
                respond(HttpStatusCode.BadRequest, "Missing task ID")
                return@timed
            }

        val task = store.getById(id)
        val deleted = store.delete(id)

        if (!deleted) {
            respond(HttpStatusCode.NotFound, "Task not found")
            return@timed
        }

        if (isHtmxRequest()) {
            val statusHtml =
                messageStatusFragment(
                    """Task "${task?.title ?: "Unknown"}" deleted.""",
                )
            respondText(statusHtml, ContentType.Text.Html)
        } else {
            response.headers.append("Location", "/tasks")
            respond(HttpStatusCode.SeeOther)
        }
    }
}

/**
 * Handle task search with query parameter.
 */
private suspend fun ApplicationCall.handleSearchTasks(store: TaskStore) {
    timed("T1_filter", jsMode()) {
        val query = requestedQuery()
        val page = requestedPage()
        val paginated = paginateTasks(store, query, page)

        if (isHtmxRequest()) {
            val statusHtml = filterStatusFragment(query, paginated.page.totalItems)
            respondTaskArea(paginated, statusHtml)
        } else {
            val html = renderTemplate("tasks/index.peb", paginated.context)
            respondText(html, ContentType.Text.Html)
        }
    }
}

private fun paginateTasks(
    store: TaskStore,
    query: String,
    page: Int,
): PaginatedTasks {
    val tasks =
        (if (query.isBlank()) store.getAll() else store.search(query))
            .map { it.toPebbleContext() }
    val pageData = Page.paginate(tasks, currentPage = page, pageSize = PAGE_SIZE)

    // Create context with both flat keys (for backwards compatibility) and nested page object (for templates)
    val context =
        pageData.toPebbleContext("tasks") +
            mapOf(
                "query" to query,
                "page" to
                    mapOf(
                        "items" to pageData.items,
                        "currentPage" to pageData.currentPage,
                        "totalPages" to pageData.totalPages,
                        "totalItems" to pageData.totalItems,
                    ),
            )
    return PaginatedTasks(pageData, context)
}

private suspend fun ApplicationCall.respondTaskArea(
    paginated: PaginatedTasks,
    statusHtml: String? = null,
    htmxTrigger: String? = null,
) {
    val fragment = renderTaskArea(paginated)
    val payload = if (statusHtml != null) fragment + "\n" + statusHtml else fragment

    if (htmxTrigger != null) {
        response.headers.append("HX-Trigger", htmxTrigger)
    }

    respondText(payload, ContentType.Text.Html)
}

private suspend fun ApplicationCall.renderTaskArea(paginated: PaginatedTasks): String {
    val context = paginated.context
    val listHtml = renderTemplate("tasks/_list.peb", context)
    val pagerHtml = renderTemplate("tasks/_pager.peb", context)
    return listHtml + "\n" + pagerHtml
}

private fun filterStatusFragment(
    query: String,
    total: Int,
): String =
    if (query.isBlank()) {
        """<div id="status" hx-swap-oob="true" role="status"></div>"""
    } else {
        val noun = if (total == 1) "task" else "tasks"
        """<div id="status" hx-swap-oob="true" role="status">Found $total $noun matching "$query".</div>"""
    }

private fun messageStatusFragment(
    message: String,
    isError: Boolean = false,
): String {
    val role = if (isError) "alert" else "status"
    val ariaLive = if (isError) """ aria-live="assertive"""" else """ aria-live="polite""""
    val cssClass = if (isError) """ class="error"""" else ""
    return """<div id="status" hx-swap-oob="true" role="$role"$ariaLive$cssClass>$message</div>"""
}

/**
 * Week 7: GET /tasks/{id}/edit - Show inline edit form
 */
private suspend fun ApplicationCall.handleEditTask(store: TaskStore) {
    val id = parameters["id"] ?: run {
        respond(HttpStatusCode.BadRequest)
        return
    }

    val task = store.getById(id)
    if (task == null) {
        respond(HttpStatusCode.NotFound)
        return
    }

    if (isHtmxRequest()) {
        // HTMX: return inline edit fragment
        val html = renderTemplate("tasks/_edit.peb", mapOf("task" to task.toPebbleContext()))
        respondText(html, ContentType.Text.Html)
    } else {
        // No-JS: redirect to list (would need edit mode support in index)
        respondRedirect("/tasks")
    }
}

/**
 * Week 7: POST /tasks/{id}/edit - Update task
 */
private suspend fun ApplicationCall.handleUpdateTask(store: TaskStore) {
    val id = parameters["id"] ?: run {
        respond(HttpStatusCode.BadRequest)
        return
    }

    val task = store.getById(id)
    if (task == null) {
        respond(HttpStatusCode.NotFound)
        return
    }

    val newTitle = receiveParameters()["title"]?.trim() ?: ""
    val validation = Task.validate(newTitle)

    if (validation is ValidationResult.Error) {
        if (isHtmxRequest()) {
            // HTMX: return edit form with error
            val html = renderTemplate("tasks/_edit.peb", mapOf(
                "task" to task.toPebbleContext(),
                "error" to validation.message
            ))
            respondText(html, ContentType.Text.Html)
        } else {
            // No-JS: redirect back (would need error handling)
            respondRedirect("/tasks")
        }
        return
    }

    // Update task
    val updated = task.copy(title = newTitle)
    store.update(updated)

    if (isHtmxRequest()) {
        // HTMX: return view fragment
        val html = renderTemplate("tasks/_item.peb", mapOf("task" to updated.toPebbleContext()))
        val status = """<div id="status" hx-swap-oob="true" role="status">Task updated successfully.</div>"""
        respondText(html + status, ContentType.Text.Html)
    } else {
        // No-JS: redirect to list
        respondRedirect("/tasks")
    }
}

/**
 * Week 7: GET /tasks/{id}/view - Cancel edit (return to view mode)
 * HTMX-only route for Cancel button (no-JS uses href="/tasks" fallback)
 */
private suspend fun ApplicationCall.handleViewTask(store: TaskStore) {
    val id = parameters["id"] ?: run {
        respond(HttpStatusCode.BadRequest)
        return
    }

    val task = store.getById(id)
    if (task == null) {
        respond(HttpStatusCode.NotFound)
        return
    }

    if (isHtmxRequest()) {
        val html = renderTemplate("tasks/_item.peb", mapOf("task" to task.toPebbleContext()))
        respondText(html, ContentType.Text.Html)
    } else {
        respondRedirect("/tasks")
    }
}

private fun redirectPath(
    query: String,
    page: Int,
): String {
    val params = mutableListOf<String>()
    if (query.isNotBlank()) params += "q=${query.encodeURLParameter()}"
    if (page > 1) params += "page=$page"
    return if (params.isEmpty()) "/tasks" else "/tasks?${params.joinToString("&")}"
}

private fun String?.toQuery(): String = this?.trim()?.takeIf { it.isNotEmpty() } ?: ""

private fun ApplicationCall.requestedQuery(): String = request.queryParameters["q"].toQuery()

private fun ApplicationCall.requestedPage(): Int =
    request.queryParameters["page"]?.toIntOrNull()?.takeIf { it > 0 } ?: 1
>>>>>>> minimal/main
