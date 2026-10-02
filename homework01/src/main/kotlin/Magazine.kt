package org.example

class Magazine (
    override val id: Int,
    override val title: String,
    val issueNumber: Int
) : LibraryItem(id, title) {

    override fun description(): String {
        return "$title, issue #$issueNumber"
    }
}
