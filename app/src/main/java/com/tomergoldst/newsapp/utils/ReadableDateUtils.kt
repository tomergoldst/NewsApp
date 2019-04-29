package com.tomergoldst.newsapp.utils

import android.content.Context
import android.text.format.DateUtils

import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date

object ReadableDateUtils {

    private val flagsDateTimeWithDayOfWeek = DateUtils.FORMAT_SHOW_DATE or
            DateUtils.FORMAT_ABBREV_MONTH or
            DateUtils.FORMAT_ABBREV_WEEKDAY or
            DateUtils.FORMAT_SHOW_WEEKDAY or
            DateUtils.FORMAT_SHOW_YEAR or
            DateUtils.FORMAT_SHOW_TIME

    private val flagsTimeOnly = DateUtils.FORMAT_SHOW_TIME

    val timeSimpleDateFormat: SimpleDateFormat
        get() = DateFormat.getTimeInstance(DateFormat.SHORT) as SimpleDateFormat

    fun getReadableTimeText(context: Context, date: Date): String {
        val calendar = Calendar.getInstance()
        calendar.time = date
        return getTimeText(context, calendar, timeSimpleDateFormat)
    }

    fun getReadableTimeText(context: Context, time: Long): String {
        val calendar = Calendar.getInstance()
        calendar.timeInMillis = time
        return getTimeText(context, calendar, timeSimpleDateFormat)
    }

    fun getTimeText(
        context: Context,
        calendar: Calendar,
        simpleDateFormat: SimpleDateFormat = timeSimpleDateFormat
    ): String {
        return try {
            simpleDateFormat.format(calendar)
        } catch (e: Exception) {
            getTimeFormatUsingDateUtils(context, calendar)
        }
    }

    private fun getTimeFormatUsingDateUtils(context: Context, calendar: Calendar): String {
        return DateUtils.formatDateTime(context, calendar.timeInMillis, flagsTimeOnly)
    }

    fun getDateText(calendar: Calendar): String {
        val df = DateFormat.getDateInstance()
        return df.format(calendar.time)
    }

    fun getDateTimeWithDayOfWeekFormat(context: Context, calendar: Calendar): String {
        return DateUtils.formatDateTime(
            context, calendar.timeInMillis,
            flagsDateTimeWithDayOfWeek
        )
    }

    fun getDateTimeWithDayOfWeekFormat(context: Context, timeInMillis: Long): String {
        return DateUtils.formatDateTime(
            context, timeInMillis,
            flagsDateTimeWithDayOfWeek
        )
    }

}
