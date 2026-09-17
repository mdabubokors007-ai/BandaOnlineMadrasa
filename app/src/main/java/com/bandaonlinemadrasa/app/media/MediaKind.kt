package com.bandaonlinemadrasa.app.media

enum class MediaKind { IMAGE, VIDEO, DOCUMENT, SOFTWARE, UNKNOWN }

fun kindOf(category: String): MediaKind = when (category.uppercase()) {
    "IMAGE" -> MediaKind.IMAGE
    "VIDEO" -> MediaKind.VIDEO
    "DOCUMENT" -> MediaKind.DOCUMENT
    "SOFTWARE" -> MediaKind.SOFTWARE
    else -> MediaKind.UNKNOWN
}
