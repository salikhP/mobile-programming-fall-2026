package org.example

data class Book(
    override val id: Int,
    override val title: String,
    val author: String,
    val pages: Int,
    val genre: String,
    var isBorrowed: Boolean = false
) : LibraryItem(id, title) {
    override fun description(): String {
        return "$title by $author, $pages pages"
    }
}
