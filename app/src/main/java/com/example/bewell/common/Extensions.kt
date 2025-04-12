package com.example.bewell.common

import android.os.Build
import androidx.annotation.RequiresApi
import java.time.Instant
import java.time.ZoneId
import java.time.ZonedDateTime

@RequiresApi(Build.VERSION_CODES.O)
fun Long.getDayOfMonthFromTimestamp(): Int {
    val dateTime: ZonedDateTime = Instant.ofEpochMilli(this)
        .atZone(ZoneId.systemDefault()) // or use ZoneId.of("UTC")
    return dateTime.dayOfMonth
}