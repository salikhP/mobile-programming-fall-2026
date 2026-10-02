package org.example

open class LibraryItem(
    open val id: Int,
    open val title: String
): Describable {

    override fun description(): String {
        return "$id - $title"
    }
}

