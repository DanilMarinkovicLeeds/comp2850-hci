package storage

import model.Task
import java.io.File
import kotlin.test.AfterTest
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull
import kotlin.test.assertTrue

/**
 * Unit tests for TaskStore (CSV persistence).
 *
 * **Coverage areas**:
 * - CRUD operations (add, update, delete)
 * - CSV parsing and serialisation
 * - Search and filter operations
 * - Pagination logic
 *
 * **IMPORTANT**: These are minimal test stubs for reference.
 * Students should expand with edge cases, concurrent access, etc.
 */
class TaskStoreTest {
    private lateinit var testFile: File
    private lateinit var store: TaskStore

    @BeforeTest
    fun setup() {
        // Create temporary test file
        testFile = File.createTempFile("test-tasks", ".csv")
        testFile.deleteOnExit()
        store = TaskStore(testFile)
    }

    @AfterTest
    fun teardown() {
        // Clean up test file
        testFile.delete()
    }

    @Test
    fun `add task persists to CSV`() {
        val task = Task(title = "Test task")
        store.add(task)

        val tasks = store.getAll()
        assertEquals(1, tasks.size)
        assertEquals("Test task", tasks[0].title)
    }

    @Test
    fun `getById returns correct task`() {
        val task = Task(id = "test-123", title = "Test task")
        store.add(task)

        val retrieved = store.getById("test-123")
        assertNotNull(retrieved)
        assertEquals("Test task", retrieved.title)
    }

    @Test
    fun `delete removes task from storage`() {
        val task = Task(id = "test-456", title = "To be deleted")
        store.add(task)

        store.delete("test-456")

        val tasks = store.getAll()
        assertEquals(0, tasks.size)
    }

    @Test
    fun `toggleComplete updates completion status`() {
        val task = Task(id = "test-789", title = "Toggle me", completed = false)
        store.add(task)

        store.toggleComplete("test-789")

        val updated = store.getById("test-789")
        assertNotNull(updated)
        assertEquals(true, updated.completed)
    }

    @Test
    fun `search filters tasks by query`() {
        store.add(Task(title = "Meeting with team"))
        store.add(Task(title = "Write report"))
        store.add(Task(title = "Team meeting notes"))

        val results = store.search("meeting")
        assertEquals(2, results.size)
        assertTrue(results.all { it.title.contains("meeting", ignoreCase = true) })
    }

    @Test
    fun `update modifies existing task`() {
        val task = Task(id = "test-update", title = "Original title", completed = false)
        store.add(task)

        val updated = task.copy(title = "Updated title", completed = true)
        val result = store.update(updated)

        assertTrue(result)
        val retrieved = store.getById("test-update")
        assertNotNull(retrieved)
        assertEquals("Updated title", retrieved.title)
        assertEquals(true, retrieved.completed)
    }
}
