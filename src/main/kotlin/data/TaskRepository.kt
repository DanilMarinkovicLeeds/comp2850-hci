package data

import java.io.File
import java.util.concurrent.atomic.AtomicInteger

/**
 * Simple task data model for Week 7.
 *
 * **Week 7**: Editable title field
 * **Week 8 evolution**: Add `createdAt` timestamp for sorting
 */
data class Task(
    val id: Int,
    var title: String,
)

/**
 * In-memory repository with CSV persistence.
 *
<<<<<<< HEAD
 * **Simple approach for Week 6**: Singleton object with integer IDs
=======
 * **Week 7**: Added find() and update() methods for inline edit
>>>>>>> minimal/main
 * **Week 10 evolution**: Refactor to class with UUID for production-readiness
 */
object TaskRepository {
    private val file = File("data/tasks.csv")
    private val tasks = mutableListOf<Task>()
    private val idCounter = AtomicInteger(1)

    init {
        file.parentFile?.mkdirs()
        if (!file.exists()) {
            file.writeText("id,title\n")
        } else {
            file.readLines().drop(1).forEach { line ->
                val parts = line.split(",", limit = 2)
                if (parts.size == 2) {
                    val id = parts[0].toIntOrNull() ?: return@forEach
                    tasks.add(Task(id, parts[1]))
                    idCounter.set(maxOf(idCounter.get(), id + 1))
                }
            }
        }
    }

    fun all(): List<Task> = tasks.toList()

    fun add(title: String): Task {
        val task = Task(idCounter.getAndIncrement(), title)
        tasks.add(task)
        persist()
        return task
    }

    fun delete(id: Int): Boolean {
        val removed = tasks.removeIf { it.id == id }
        if (removed) persist()
        return removed
    }

    fun find(id: Int): Task? = tasks.find { it.id == id }

    fun update(task: Task) {
        tasks.find { it.id == task.id }?.let { it.title = task.title }
        persist()
    }

    /**
     * Week 8: Search tasks by title
     */
    fun search(query: String): List<Task> {
        if (query.isBlank()) return all()
        return tasks.filter { it.title.contains(query, ignoreCase = true) }.toList()
    }

    private fun persist() {
        file.writeText("id,title\n" + tasks.joinToString("\n") { "${it.id},${it.title}" })
    }
}
