package routes

import configureTemplating
import io.ktor.client.request.get
import io.ktor.client.request.header
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.client.statement.bodyAsText
import io.ktor.http.HttpStatusCode
import io.ktor.server.application.Application
import io.ktor.server.application.ApplicationCallPipeline
import io.ktor.server.application.call
import io.ktor.server.application.install
import io.ktor.server.routing.routing
import io.ktor.server.sessions.Sessions
import io.ktor.server.sessions.cookie
import io.ktor.server.sessions.get as getSession // Aliased to avoid conflict with client.get
import io.ktor.server.sessions.sessions
import io.ktor.server.sessions.set as setSession // Aliased to avoid conflict with client HTTP methods
import io.ktor.server.testing.testApplication
import storage.TaskStore
import utils.ReqIdKey
import utils.SessionData
import java.io.File
import kotlin.test.AfterTest
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

/**
 * Integration tests for TaskRoutes.
 *
 * **Coverage areas**:
 * - HTMX endpoints (POST with HX-Request header)
 * - No-JS endpoints (standard POST-Redirect-GET)
 * - Search and pagination
 * - Accessibility attributes (ARIA roles, labels)
 *
 * **IMPORTANT**: These are minimal test stubs for reference.
 * Students should expand with comprehensive integration tests.
 *
 * **Testing patterns**:
 * - HTMX mode: Check for HX-Trigger response headers, partial HTML
 * - No-JS mode: Check for 303 redirects, full page HTML
 * - Accessibility: Verify role="status", aria-live, button labels
 */
class TaskRoutesTest {
    private val testDataFile = File("data/test-tasks.csv")

    @BeforeTest
    fun setup() {
        // Clean up test data before each test
        testDataFile.delete()
    }

    @AfterTest
    fun cleanup() {
        // Clean up test data after each test
        testDataFile.delete()
    }

    @Test
    fun `GET tasks returns task list page`() =
        testApplication {
            application {
                configureTestApplication()
            }
            val response = client.get("/tasks")
            assertEquals(HttpStatusCode.OK, response.status)
            assertTrue(response.bodyAsText().contains("Task Manager"))
        }

    @Test
    fun `POST tasks with HTMX adds task and returns partial HTML`() =
        testApplication {
            application {
                configureTestApplication()
            }
            val response =
                client.post("/tasks") {
                    header("HX-Request", "true")
                    header("Content-Type", "application/x-www-form-urlencoded")
                    setBody("title=Test+task")
                }

            assertEquals(HttpStatusCode.OK, response.status)
            val body = response.bodyAsText()
            assertTrue(body.contains("Test task"))
            // Should contain HTMX trigger for status message
            assertTrue(response.headers["HX-Trigger"]?.contains("task-added") == true)
        }

    @Test
    fun `POST tasks without HTMX redirects after adding task`() =
        testApplication {
            application {
                configureTestApplication()
            }
            val response =
                client.post("/tasks") {
                    header("Content-Type", "application/x-www-form-urlencoded")
                    setBody("title=Test+task+nojs")
                }

            // Should redirect to /tasks (POST-Redirect-GET pattern)
            assertEquals(HttpStatusCode.SeeOther, response.status)
            assertEquals("/tasks", response.headers["Location"])
        }

    @Test
    fun `POST tasks toggle with HTMX returns updated task item`() =
        testApplication {
            application {
                configureTestApplication()
            }
            // First add a task
            client.post("/tasks") {
                header("HX-Request", "true")
                header("Content-Type", "application/x-www-form-urlencoded")
                setBody("title=Toggle+test")
            }

            // Then toggle it (implementation depends on actual route structure)
            // This is a stub - actual test would need task ID
            // val toggleResponse = client.post("/tasks/{id}/toggle") { ... }
            // assertTrue(toggleResponse.bodyAsText().contains("completed"))
        }

    @Test
    fun `GET tasks search with query returns filtered results`() =
        testApplication {
            application {
                configureTestApplication()
            }
            // Add some tasks first with HTMX (to avoid redirects)
            client.post("/tasks") {
                header("HX-Request", "true")
                header("Content-Type", "application/x-www-form-urlencoded")
                setBody("title=Meeting+with+team")
            }

            client.post("/tasks") {
                header("HX-Request", "true")
                header("Content-Type", "application/x-www-form-urlencoded")
                setBody("title=Write+report")
            }

            // Search for "meeting"
            val response = client.get("/tasks?q=meeting")
            assertEquals(HttpStatusCode.OK, response.status)
            val body = response.bodyAsText()

            assertTrue(body.contains("Meeting with team"))
            assertFalse(body.contains("Write report"))
        }

    @Test
    fun `GET tasks with pagination parameter returns correct page`() =
        testApplication {
            application {
                configureTestApplication()
            }
            // Add multiple tasks
            repeat(15) { i ->
                client.post("/tasks") {
                    header("Content-Type", "application/x-www-form-urlencoded")
                    setBody("title=Task+$i")
                }
            }

            // Request page 2
            val response = client.get("/tasks?page=2")
            assertEquals(HttpStatusCode.OK, response.status)
            // Should contain pagination controls
            assertTrue(response.bodyAsText().contains("page"))
        }

    @Test
    fun `POST tasks with invalid title returns validation error`() =
        testApplication {
            application {
                configureTestApplication()
            }
            val response =
                client.post("/tasks") {
                    header("HX-Request", "true")
                    header("Content-Type", "application/x-www-form-urlencoded")
                    setBody("title=ab") // Too short (< 3 characters)
                }

            assertEquals(HttpStatusCode.OK, response.status)
            val body = response.bodyAsText()
            // Should contain error message
            assertTrue(body.contains("at least 3 characters") || body.contains("error"))
        }

    /**
     * Configure test application with minimal setup.
     * Only includes essential plugins and routing for testing.
     */
    private fun Application.configureTestApplication() {
        // Install sessions (required by timing utilities)
        install(Sessions) {
            cookie<SessionData>("user_session")
        }

        // Configure Pebble templating (required by renderTemplate)
        configureTemplating()

        // Create test-specific TaskStore (isolated from production data)
        val testStore = TaskStore(testDataFile)

        // Configure routing with task routes
        routing {
            // Setup interceptor for session and request ID (like in Main.kt)
            intercept(ApplicationCallPipeline.Setup) {
                if (call.sessions.getSession<SessionData>() == null) {
                    call.sessions.setSession(SessionData())
                }
                if (call.attributes.getOrNull(ReqIdKey) == null) {
                    call.attributes.put(ReqIdKey, "test-req-${System.currentTimeMillis()}")
                }
                proceed()
            }

            configureTaskRoutes(testStore)
        }
    }
}
