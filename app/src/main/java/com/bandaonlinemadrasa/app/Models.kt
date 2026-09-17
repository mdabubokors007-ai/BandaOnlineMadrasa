package com.bandaonlinemadrasa.app

import kotlinx.serialization.Serializable

@Serializable
data class ContentItem(
    val id: String = "",
    val name: String = "",
    val description: String = "",
    val category: String = "",
    val filePath: String = "",
    val downloadEnabled: Boolean = true
)
