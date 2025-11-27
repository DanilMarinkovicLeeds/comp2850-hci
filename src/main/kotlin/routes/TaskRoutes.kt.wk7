package routes

import data.TaskRepository
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.pebbletemplates.pebble.PebbleEngine
import java.io.StringWriter

/**
 * Week 7 Lab 1: Inline edit with HTMX progressive enhancement.
 *
 * **Teaching approach**: Start simple, evolve incrementally
 * - Week 6: Basic CRUD with Int IDs
 * - Week 7: Add inline edit with dual-mode rendering
 * - Week 8: Add pagination, search
 */

fun Route.taskRoutes() {
    val pebble = PebbleEngine.Builder()
        .loader(io.pebbletemplates.pebble.loader.ClasspathLoader().apply {
            prefix = "templates/"
        })
        .build()

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
        val model = mapOf(
            "title" to "Tasks",
            "tasks" to TaskRepository.all(),
            "sessionId" to "dev-session",  // Week 8: session info for footer
            "isHtmx" to call.isHtmx()       // Week 8: mode indicator for footer
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
                return@post call.respondRedirect("/tasks")
            }
        }

        val task = TaskRepository.add(title)

        if (call.isHtmx()) {
            // Return HTML fragment for new task using _item.peb partial
            val template = pebble.getTemplate("tasks/_item.peb")
            val writer = StringWriter()
            template.evaluate(writer, mapOf("task" to task))
            val status = """<div id="status" hx-swap-oob="true">Task "${task.title}" added successfully.</div>"""

            return@post call.respondText(writer.toString() + status, ContentType.Text.Html, HttpStatusCode.Created)
        }

        // No-JS: POST-Redirect-GET pattern
        call.respondRedirect("/tasks")
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

        // No-JS: POST-Redirect-GET pattern
        call.respondRedirect("/tasks")
    }

    /**
     * GET /tasks/{id}/edit - Show edit form
     * Dual-mode: HTMX returns edit fragment, no-JS returns full page with editingId
     */
    get("/tasks/{id}/edit") {
        val id = call.parameters["id"]?.toIntOrNull() ?: return@get call.respond(HttpStatusCode.NotFound)
        val task = TaskRepository.find(id) ?: return@get call.respond(HttpStatusCode.NotFound)

        val errorParam = call.request.queryParameters["error"]
        val errorMessage = when (errorParam) {
            "blank" -> "Title is required. Please enter at least one character."
            else -> null
        }

        if (call.isHtmx()) {
            // HTMX path: return edit fragment
            val template = pebble.getTemplate("tasks/_edit.peb")
            val model = mapOf("task" to task, "error" to errorMessage)
            val writer = StringWriter()
            template.evaluate(writer, model)
            call.respondText(writer.toString(), ContentType.Text.Html)
        } else {
            // No-JS path: full-page render with editingId
            val model = mapOf(
                "title" to "Tasks",
                "tasks" to TaskRepository.all(),
                "editingId" to id,
                "errorMessage" to errorMessage,
                "sessionId" to "dev-session",
                "isHtmx" to false
            )
            val template = pebble.getTemplate("tasks/index.peb")
            val writer = StringWriter()
            template.evaluate(writer, model)
            call.respondText(writer.toString(), ContentType.Text.Html)
        }
    }

    /**
     * POST /tasks/{id}/edit - Save task edits
     * Dual-mode: HTMX returns view fragment or edit with error, no-JS uses PRG
     */
    post("/tasks/{id}/edit") {
        val id = call.parameters["id"]?.toIntOrNull() ?: return@post call.respond(HttpStatusCode.NotFound)
        val task = TaskRepository.find(id) ?: return@post call.respond(HttpStatusCode.NotFound)
        val newTitle = call.receiveParameters()["title"].orEmpty().trim()

        // Validation
        if (newTitle.isBlank()) {
            if (call.isHtmx()) {
                // HTMX path: return edit fragment with error
                val template = pebble.getTemplate("tasks/_edit.peb")
                val model = mapOf(
                    "task" to task,
                    "error" to "Title is required. Please enter at least one character."
                )
                val writer = StringWriter()
                template.evaluate(writer, model)
                return@post call.respondText(writer.toString(), ContentType.Text.Html, HttpStatusCode.BadRequest)
            } else {
                // No-JS path: redirect with error flag
                return@post call.respondRedirect("/tasks/${id}/edit?error=blank")
            }
        }

        // Update task
        task.title = newTitle
        TaskRepository.update(task)

        if (call.isHtmx()) {
            // HTMX path: return view fragment + OOB status
            val viewTemplate = pebble.getTemplate("tasks/_item.peb")
            val viewWriter = StringWriter()
            viewTemplate.evaluate(viewWriter, mapOf("task" to task))
            val status = """<div id="status" hx-swap-oob="true">Task "${task.title}" updated successfully.</div>"""
            return@post call.respondText(viewWriter.toString() + status, ContentType.Text.Html)
        }

        // No-JS path: PRG redirect
        call.respondRedirect("/tasks")
    }

    /**
     * GET /tasks/{id}/view - Cancel edit (return to view mode)
     * HTMX-only route (cancel is just a link to /tasks in no-JS)
     */
    get("/tasks/{id}/view") {
        val id = call.parameters["id"]?.toIntOrNull() ?: return@get call.respond(HttpStatusCode.NotFound)
        val task = TaskRepository.find(id) ?: return@get call.respond(HttpStatusCode.NotFound)

        val template = pebble.getTemplate("tasks/_item.peb")
        val model = mapOf("task" to task)
        val writer = StringWriter()
        template.evaluate(writer, model)
        call.respondText(writer.toString(), ContentType.Text.Html)
    }
}
