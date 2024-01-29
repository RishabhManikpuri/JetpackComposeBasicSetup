package com.rishabh.jetpackcomposebasicsetup.utils

import android.text.format.DateUtils
import java.text.ParseException
import java.text.SimpleDateFormat
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import java.util.*
import java.util.concurrent.TimeUnit

object DateUtil {

    private const val dd_MM_yyyy_DASH = "dd-MM-yyyy"
    const val dd_MMM_yyyy_SPACE = "dd MMM yyyy"
    const val dd_MMMM_yyyy_SPACE = "dd MMMM yyyy"
    const val hh_mm_a_COLON = "hh:mm a"
    const val hh_a = "hh a"
    const val ONE_THOUSAND = 1000
    const val dd_MM_yyyy = "dd/MM/yyyy"
    const val hh_mm_ss_a_COLON = "hh:mm:ss a"
    const val MMM_dd_SPACE = "MMM dd"
    const val EEEE_dd_MMMM = "EEEE, dd MMMM"
    const val hh_SPACE="hh aa"
    const val dd_MMM_yyyy="dd MMM,yyyy"
    const val d_MMMM_yyyy="d MMMM,yyyy"
    const val hh_mm_ss = "HH:mm:ss"
    const val HOURS_MINUTE_FORMAT = "%02d:%02d %s"
    const val mm = "mm"
    const val HH_mm = "HH.mm"

    /**
     * Backup the system's timezone
     */
    private val defaultTimeZone by lazy { TimeZone.getDefault() }

    val calender by lazy { Calendar.getInstance() }

    fun getCurrentDate(pattern: String = dd_MM_yyyy_DASH): String {
        val dateFormat = SimpleDateFormat(pattern, Locale.US)
        return dateFormat.format(Date())
    }

    fun getFormattedDate(milliSeconds: Long, dateFormat: String) : Long {
        val utcFormatter = SimpleDateFormat(dateFormat)
        val calendar = Calendar.getInstance()
        calendar.timeInMillis = milliSeconds
        calendar[Calendar.HOUR] = 0
        calendar[Calendar.MINUTE] = 0
        calendar[Calendar.SECOND] = 0
        val date = utcFormatter.format(calendar.time)
        return utcFormatter.parse(date)?.time ?: 0
    }

    fun getFormattedDate(date: String, currentDateFormat: String, dateFormat: String): String {
        val cdf = SimpleDateFormat(currentDateFormat)
        val df = SimpleDateFormat(dateFormat)
        df.timeZone = TimeZone.getDefault()
        return try {
            val formattedDate = cdf.parse(date)

            df.format(formattedDate)
        } catch (e: Exception) {
            date
        }
    }

    fun getDateFromTimestamp(dateFormat: String, timestamp: Long,multiplier: Int = ONE_THOUSAND): String? {
        val date = Date(timestamp * multiplier)
        var outDate: String? = null
        try {
            val dateFormatter = SimpleDateFormat(dateFormat, Locale.ENGLISH)
            dateFormatter.timeZone = defaultTimeZone
            outDate = dateFormatter.format(date)
        } catch (e: ParseException) {
            e.printStackTrace()
        }
        return outDate
    }

    fun getTime(selectedHour: Int, selectedMinute: Int): String {
        val timeText = String.format(
            HOURS_MINUTE_FORMAT,
            if (selectedHour > 12) selectedHour - 12 else selectedHour,
            selectedMinute,
            if (selectedHour >= 12) "PM" else "AM"
        )
        return timeText
    }

    fun getDate(pattern: String = dd_MM_yyyy): String {
        val currentDate = SimpleDateFormat(pattern, Locale.getDefault()).format(Date())
        return currentDate.format(Date())
    }

    fun convertMillSecToMinutes(millis: Long): String {
        val seconds: Int = (millis / 1000).toInt()
        val second: Int = seconds % 60
        var minute: Int = seconds / 60
        if (minute >= 60) {
            val hour = minute / 60
            minute %= 60
            return (String.format("%02d", hour) +" : "+ String.format("%02d", minute))
        }
        return String.format("%02d", minute) + " : " + String.format("%02d", second)
    }

    fun convertMillsToMinutes(millis: Long): Long {
        return TimeUnit.MILLISECONDS.toMinutes(millis)
    }

    fun convertMillsToSeconds(millis: Long): Long {
        return TimeUnit.MILLISECONDS.toSeconds(millis) - TimeUnit.MINUTES.toSeconds(
            convertMillsToMinutes(millis)
        )
    }

    fun convertMillSecToSeconds(millis: Long):Long{
        return TimeUnit.MILLISECONDS.toSeconds(millis)
    }

    fun convertSecondsToMills(sec: Long):Long{
        return TimeUnit.SECONDS.toMillis(sec)
    }

    fun convertMinutesToMills(minutes: Long):Long{
        return TimeUnit.MINUTES.toMillis(minutes)
    }

    fun getMillisFromDate(date: Long, hour: Int, minute: Int): Long {
        val calender = Calendar.getInstance()
        calender.timeInMillis = date
        calender[Calendar.HOUR] = hour
        calender[Calendar.MINUTE] = minute
        return calender.timeInMillis
    }


    fun getHourMinute(date: Long?): String {
        if (date == null) {
            return ""
        }
        val calender = Calendar.getInstance()
        calender.timeInMillis = date
        val amPm = calender[Calendar.AM_PM]
        return if (amPm == Calendar.AM) {
            String.format(
                HOURS_MINUTE_FORMAT,
                calender[Calendar.HOUR],
                calender[Calendar.MINUTE],
                "AM"
            )
        } else {
            String.format(
                HOURS_MINUTE_FORMAT,
                calender[Calendar.HOUR],
                calender[Calendar.MINUTE],
                "PM"
            )
        }
    }

    fun convertSecondsToMin(seconds: Int): String? {
        val formatter: DateTimeFormatter = DateTimeFormatter.ofPattern(hh_mm_ss)
        val time: LocalTime = LocalTime.MIN.plusSeconds(seconds.toLong())
        val timeList = formatter.format(time).split(":")
        val hour = timeList[0]
        val min = timeList[1]
        val sec = timeList[2]
        if (hour.toInt() > 0) {
            return "${hour}:${min}"
        }
        if (min.toInt() < 60) {
            return "${min}:${sec}"
        }
        return ""
    }

    fun isSameDay(date: Long) = DateUtils.isToday(date * 1000)

    fun convertMinuteToHours(durInMin: Int): Triple<Int, Int, String> {
        val hours: Int = durInMin / 60
        val minutes: Int = durInMin % 60

        return if (minutes == 0) {
            Triple(hours, minutes, hours.toString())
        } else {
            Triple(hours, minutes, "$hours.$minutes")
        }
    }

}