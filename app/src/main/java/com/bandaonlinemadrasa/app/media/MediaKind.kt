package com.bandaonlinemadrasa.app.media

enum class MediaKind {
    VIDEO, IMAGE, DOCUMENT, SOFTWARE, UNKNOWN
}

fun kindOf(category: String): MediaKind = when (category.lowercase()) {
    "video", "videos" -> MediaKind.VIDEO
    "image", "images", "photo", "photos" -> MediaKind.IMAGE
    "document", "documents", "pdf", "book", "books" -> MediaKind.DOCUMENT
    "software", "app", "apps" -> MediaKind.SOFTWARE
    else -> MediaKind.UNKNOWN
}
