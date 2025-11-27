package utils

/**
 * Week 8: Pagination utility for task lists.
 *
 * **Purpose**: Calculate page bounds, total pages, provide context for templates.
 *
 * **Usage**:
 * ```kotlin
 * val allTasks = store.getAll()
 * val page = Page.paginate(allTasks, currentPage = 1, pageSize = 10)
 * // page.items = tasks 0-9
 * // page.currentPage = 1
 * // page.totalPages = 5 (if 50 tasks total)
 * ```
 */
data class Page<T>(
    val items: List<T>,
    val currentPage: Int,
    val totalPages: Int,
    val totalItems: Int,
    val pageSize: Int,
) {
    val hasPrevious: Boolean get() = currentPage > 1
    val hasNext: Boolean get() = currentPage < totalPages
    val previousPage: Int get() = if (hasPrevious) currentPage - 1 else 1
    val nextPage: Int get() = if (hasNext) currentPage + 1 else totalPages

    // Week 8: Aliases for template compatibility (match mdbook naming)
    val pageNumber: Int get() = currentPage
    val pageCount: Int get() = totalPages
    val itemCount: Int get() = totalItems

    /**
     * Generate page number list for pagination controls.
     * Shows: [1] ... [current-1] [current] [current+1] ... [totalPages]
     *
     * **Example**: If on page 5 of 10: [1, ..., 4, 5, 6, ..., 10]
     */
    fun pageNumbers(): List<PageNumber> {
        val numbers = mutableListOf<PageNumber>()

        // Always show first page
        numbers.add(PageNumber.Number(1))

        // Show ellipsis if gap between 1 and current-1
        if (currentPage > 3) {
            numbers.add(PageNumber.Ellipsis)
        }

        // Show current-1, current, current+1
        for (i in maxOf(2, currentPage - 1)..minOf(totalPages - 1, currentPage + 1)) {
            numbers.add(PageNumber.Number(i))
        }

        // Show ellipsis if gap between current+1 and last
        if (currentPage < totalPages - 2) {
            numbers.add(PageNumber.Ellipsis)
        }

        // Always show last page (if more than 1 page)
        if (totalPages > 1) {
            numbers.add(PageNumber.Number(totalPages))
        }

        return numbers.distinct()
    }

    companion object {
        /**
         * Create paginated subset of items.
         *
         * @param items Full list of items
         * @param currentPage Page number (1-indexed)
         * @param pageSize Items per page (default 10)
         * @return Page object with subset of items + metadata
         */
        fun <T> paginate(
            items: List<T>,
            currentPage: Int = 1,
            pageSize: Int = 10,
        ): Page<T> {
            val totalItems = items.size
            val totalPages = if (totalItems == 0) 1 else (totalItems + pageSize - 1) / pageSize

            // Clamp current page to valid range
            val validPage = currentPage.coerceIn(1, totalPages)

            // Calculate slice bounds
            val startIndex = (validPage - 1) * pageSize
            val endIndex = minOf(startIndex + pageSize, totalItems)

            val pageItems =
                if (startIndex < totalItems) {
                    items.subList(startIndex, endIndex)
                } else {
                    emptyList()
                }

            return Page(
                items = pageItems,
                currentPage = validPage,
                totalPages = totalPages,
                totalItems = totalItems,
                pageSize = pageSize,
            )
        }
    }

    /**
     * Convert page to Pebble template context.
     */
    fun toPebbleContext(itemsKey: String = "items"): Map<String, Any> =
        mapOf(
            itemsKey to items,
            "currentPage" to currentPage,
            "totalPages" to totalPages,
            "totalItems" to totalItems,
            "pageSize" to pageSize,
            "hasPrevious" to hasPrevious,
            "hasNext" to hasNext,
            "previousPage" to previousPage,
            "nextPage" to nextPage,
            "pageNumbers" to
                pageNumbers().map {
                    when (it) {
                        is PageNumber.Number -> mapOf("type" to "number", "value" to it.value)
                        PageNumber.Ellipsis -> mapOf("type" to "ellipsis")
                    }
                },
        )
}

/**
 * Sealed class for pagination controls.
 * Allows rendering [...] ellipsis between page numbers.
 */
sealed class PageNumber {
    data class Number(
        val value: Int,
    ) : PageNumber()

    data object Ellipsis : PageNumber()
}
