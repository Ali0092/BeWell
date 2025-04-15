package com.example.bewell.common

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import java.text.SimpleDateFormat
import java.time.Instant
import java.time.ZoneId
import java.time.ZonedDateTime
import java.util.Calendar
import java.util.Date
import java.util.Locale

@RequiresApi(Build.VERSION_CODES.O)
fun Long.getDayOfMonthFromTimestamp(): Int {
    val dateTime: ZonedDateTime = Instant.ofEpochMilli(this)
        .atZone(ZoneId.systemDefault()) // or use ZoneId.of("UTC")
    return dateTime.dayOfMonth
}


@SuppressLint("DefaultLocale")
fun Float.valueInThreeDecimalPoints() : Float {
    return String.format("%.3f", this).toFloat()
}

fun Long.formatDateFromMillis(): String {
    val dateFormat = SimpleDateFormat("dd MMM, yyyy", Locale.ENGLISH)
    return dateFormat.format(Date(this))
}

fun Long.getDateDifferences(): List<Long> {
    val dateList = mutableListOf<Long>()

    // Get calendar instances for input date and current date
    val inputCalendar = Calendar.getInstance().apply {
        timeInMillis = this@getDateDifferences
        // Set time to start of day
        set(Calendar.HOUR_OF_DAY, 0)
        set(Calendar.MINUTE, 0)
        set(Calendar.SECOND, 0)
        set(Calendar.MILLISECOND, 0)
    }

    val currentCalendar = Calendar.getInstance().apply {
        // Set time to start of day
        set(Calendar.HOUR_OF_DAY, 0)
        set(Calendar.MINUTE, 0)
        set(Calendar.SECOND, 0)
        set(Calendar.MILLISECOND, 0)
    }

    // If today's date is same or before the input date, return an empty list
    if (!inputCalendar.before(currentCalendar)) {
        return dateList
    }

    // Add all days from input date+1 up to today (inclusive)
    val tempCalendar = inputCalendar.clone() as Calendar
    tempCalendar.add(Calendar.DAY_OF_MONTH, 1) // Start from the day after input date

    while (tempCalendar.timeInMillis <= currentCalendar.timeInMillis) {
        dateList.add(tempCalendar.timeInMillis)
        tempCalendar.add(Calendar.DAY_OF_MONTH, 1)
    }

    return dateList
}
