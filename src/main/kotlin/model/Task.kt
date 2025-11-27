package model

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.UUID

/**
 * Task data model.
 * Represents a single todo item in the task manager.
 *
 * **Privacy note**: No PII stored. Tasks are anonymous and associated
 * only with session IDs (also anonymous).
 *
 * @property id Unique identifier (UUID format)
 * @property title Task title (3-100 characters, validated)
 * @property completed Whether task is done
 * @property createdAt Timestamp of creation
 */
data class Task(
    val id: String = UUID.randomUUID().toString(),
    val title: String,
    val completed: Boolean = false,
    val createdAt: LocalDateTime = LocalDateTime.now(),
) {
    companion object {
        /**
         * Minimum allowed length for task title.
         */
        const val MIN_TITLE_LENGTH = 3

        /**
         * Maximum allowed length for task title.
         */
        const val MAX_TITLE_LENGTH = 100

        /**
         * Validate task title against business rules.
         *
         * **WCAG 2.2 AA compliance**:
         * - Clear, specific error messages (3.3.1)
         * - Errors should be linked to inputs via aria-describedby (3.3.1)
         *
         * **Rules**:
         * - Required (cannot be blank)
         * - Minimum 3 characters
         * - Maximum 100 characters
         *
         * @param title Title to validate
         * @return ValidationResult.Success or ValidationResult.Error(message)
         */
        fun validate(title: String): ValidationResult =
            when {
                title.isBlank() ->
                    ValidationResult.Error("Title is required. Please enter a task description.")

                title.length < MIN_TITLE_LENGTH ->
                    ValidationResult.Error(
                        "Title must be at least $MIN_TITLE_LENGTH characters. Currently: ${title.length} characters.",
                    )

                title.length > MAX_TITLE_LENGTH ->
                    ValidationResult.Error(
                        "Title must be less than $MAX_TITLE_LENGTH characters. Currently: ${title.length} characters.",
                    )

                else -> ValidationResult.Success
            }
    }

    /**
     * Convert task to CSV row format.
     * Used by TaskStore for persistence.
     *
     * **CSV escaping**:
     * - Title is quoted
     * - Double quotes within title are escaped as ""
     *
     * @return CSV row string (no trailing newline)
     */
    fun toCSV(): String {
        val formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME
        val escapedTitle = title.replace("\"", "\"\"")
        return "$id,\"$escapedTitle\",$completed,${createdAt.format(formatter)}"
    }

    /**
     * Convert task to Pebble template context map.
     * Used when rendering templates.
     *
     * **Template usage**:
     * ```pebble
     * <li id="task-{{ task.id }}">
     *   <span>{{ task.title }}</span>
     *   <time datetime="{{ task.createdAtISO }}">{{ task.createdAt }}</time>
     * </li>
     * ```
     *
     * @return Map suitable for Pebble template context
     */
    fun toPebbleContext(): Map<String, Any> =
        mapOf(
            "id" to id,
            "title" to title,
            "completed" to completed,
            "createdAt" to createdAt.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")),
            "createdAtISO" to createdAt.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME),
        )
}

/**
 * Validation result for task operations.
 * Sealed class ensures exhaustive when() expressions.
 */
sealed class ValidationResult {
    /**
     * Validation passed, operation can proceed.
     */
    data object Success : ValidationResult()

    /**
     * Validation failed with specific error message.
     *
     * @property message Human-readable error for display to person using system
     */
    data class Error(
        val message: String,
    ) : ValidationResult()
}
