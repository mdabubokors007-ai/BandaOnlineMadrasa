package com.bandaonlinemadrasa.app.media

import android.content.Context
import android.content.Intent

object ShareHelper {
    fun shareLink(context: Context, title: String, url: String) {
        val intent = Intent(Intent.ACTION_SEND).apply {
            type = "text/plain"
            putExtra(Intent.EXTRA_SUBJECT, title)
            putExtra(Intent.EXTRA_TEXT, url)
        }
        context.startActivity(Intent.createChooser(intent, "শেয়ার করুন"))
    }
}
