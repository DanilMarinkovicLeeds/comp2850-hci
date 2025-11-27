package model

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

/**
 * Unit tests for Task model.
 *
 * **Coverage areas**:
 * - Task creation and validation
 * - CSV serialisation
 * - Pebble context conversion
 *
 * **IMPORTANT**: These are minimal test stubs for reference.
 * Students should expand with comprehensive test cases.
 */
class TaskTest {
    @Test
    fun `validate accepts valid title`() {
        val result = Task.validate("Valid task title")
        assertTrue(result is ValidationResult.Success)
    }

    @Test
    fun `validate rejects blank title`() {
        val result = Task.validate("")
        assertTrue(result is ValidationResult.Error)
        if (result is ValidationResult.Error) {
            assertTrue(result.message.contains("required"))
        }
    }

    @Test
    fun `validate rejects title under 3 characters`() {
        val result = Task.validate("ab")
        assertTrue(result is ValidationResult.Error)
        if (result is ValidationResult.Error) {
            assertTrue(result.message.contains("at least 3 characters"))
        }
    }

    @Test
    fun `validate rejects title over 100 characters`() {
        val longTitle = "a".repeat(101)
        val result = Task.validate(longTitle)
        assertTrue(result is ValidationResult.Error)
        if (result is ValidationResult.Error) {
            assertTrue(result.message.contains("less than 100 characters"))
        }
    }

    @Test
    fun `toCSV escapes double quotes correctly`() {
        val task =
            Task(
                id = "test-123",
                title = "Task with \"quotes\"",
                completed = false,
            )
        val csv = task.toCSV()
        assertTrue(csv.contains("\"Task with \"\"quotes\"\"\""))
    }

    @Test
    fun `toPebbleContext contains all required fields`() {
        val task =
            Task(
                id = "test-456",
                title = "Test task",
                completed = true,
            )
        val context = task.toPebbleContext()

        assertEquals("test-456", context["id"])
        assertEquals("Test task", context["title"])
        assertEquals(true, context["completed"])
        assertTrue(context.containsKey("createdAt"))
        assertTrue(context.containsKey("createdAtISO"))
    }
}
