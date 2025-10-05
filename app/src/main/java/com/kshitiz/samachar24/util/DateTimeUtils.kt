package com.kshitiz.samachar24.util

import android.text.format.DateUtils
import java.text.SimpleDateFormat
import java.util.Locale

object DateTimeUtils {

    // RSS date format is often RFC 822. Cache the formatter for performance.
    private val rssSdf = SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss Z", Locale.ENGLISH)


    /**
     * Parses an RFC 822 date string from an RSS feed.
     * @return The time in milliseconds since the epoch, or 0L on failure.
     */
    fun getTimeFromRssDate(pubDate: String): Long {
        // Use try-catch for parsing, which is more idiomatic and potentially faster than a null check.
        return try {
            rssSdf.parse(pubDate)?.time ?: 0L
        } catch (_: Exception) {
            0L
        }
    }

    fun getRelativeTime(pubDate: String): String {
        return try {
            val date = rssSdf.parse(pubDate)
            val now = System.currentTimeMillis()
            DateUtils.getRelativeTimeSpanString(
                date!!.time,
                now,
                DateUtils.MINUTE_IN_MILLIS,
                DateUtils.FORMAT_ABBREV_RELATIVE
            ).toString()
        } catch (_: Exception) {
            // Fallback for different date formats or parsing errors
            pubDate.substringBefore(" ") // Just show "Day," as a simple fallback
        }
    }
}