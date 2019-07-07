package ru.skillbranch.devintensive.extensions

import java.lang.IllegalStateException
import java.text.SimpleDateFormat
import java.util.*

const val SECOND = 1000L
const val MINUTE = 60 * SECOND
const val HOUR = 60 * MINUTE
const val DAY = 24 * HOUR

fun Date.format(pattern:String? = "HH:mm:ss dd.MM.yy"):String{
    val dateFormat = SimpleDateFormat(pattern, Locale("ru"))
    return dateFormat.format(this)

}

fun Date.humanizeDiff(date:Date):String{
    val currentDate = this
    val diff = date.time - currentDate.time
    return when (diff){
        in 0..SECOND -> "только что"
        in SECOND..45 * SECOND -> "несколько секунд назад"
        in 45 * SECOND..75 * SECOND -> "минуту назад"
        in 75 * SECOND..45 * MINUTE -> {
            when(diff/ MINUTE){
                1L, 21L, 31L, 41L -> "${diff/ MINUTE} минуту назад"
                2L,3L,4L,22L,23L,24L,32L,33L,34L,42L,43L,44L -> "${diff/ MINUTE} минуты назад"
                else -> "${diff/ MINUTE} минут назад"

            }
        }
        in 45 * MINUTE..75 * MINUTE -> "час назад"
        in 75 * MINUTE..22 * HOUR -> {
            when(diff/ HOUR){
                1L,21L -> "${diff/ HOUR} час назад"
                2L,3L,4L -> "${diff/ HOUR} часа назад"
                else -> "${diff/ HOUR} часов назад"

            }
        }
        in 22 * HOUR..26 * HOUR -> "день назад"
        in 26 * HOUR..360 * DAY -> {
            if (diff/DAY/10!=1L){
                when(diff/DAY%10){
                    1L -> "${diff/ DAY} день назад"
                    2L,3L,4L -> "${diff/ DAY} дня назад"
                    else -> "${diff/ DAY} дней назад"

                }
            } else {"${diff/ DAY} дней назад"}}
        in 360 * DAY..Long.MAX_VALUE -> "более года назад"
        else -> throw IllegalStateException ("error")
    }
}

fun Date.add(value:Int, units:TimeUnits):Date{

    var time = this.time

    time += when (units){
        TimeUnits.SECOND -> value * SECOND
        TimeUnits.MINUTE -> value * MINUTE
        TimeUnits.HOUR -> value * HOUR
        TimeUnits.DAY -> value * DAY
    }
    this.time=time
    return this
}

enum class TimeUnits{
    SECOND,
    MINUTE,
    HOUR,
    DAY
}