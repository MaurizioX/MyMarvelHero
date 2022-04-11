package mzx.mymarvel.mapper

import java.text.DateFormat
import java.util.*
import javax.inject.Inject

class DateMapper @Inject constructor() {

    private val df: DateFormat = DateFormat.getDateInstance(DateFormat.MEDIUM)

    fun mediumFormat(date: Date): String = df.format(date)
}