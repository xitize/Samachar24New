package com.kshitiz.samachar24.util

import android.os.Build
import android.text.Html
import android.text.Spanned

object TextUtils {
    fun parseHtml(html: String): Spanned {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            Html.fromHtml(html, Html.FROM_HTML_MODE_LEGACY)
        } else {
            @Suppress("DEPRECATION")
            Html.fromHtml(html)
        }
    }
}