package com.example.joydev

class TimeUtils {
    fun convertTime(time: String): String {
        val dateString = time.substring(0, 10).split("-")
        val monthNum = dateString[1].toInt()
        return dateString[2] + " " + getMonthsByNumber(monthNum) + " " + dateString[0]
    }

    private fun getMonthsByNumber(number: Int): String? {
        when(number) {
            1 -> return "Января"
            2 -> return "Февраля"
            3 -> return "Марта"
            4 -> return "Апреля"
            5 -> return "Мая"
            6 -> return "Июня"
            7 -> return "Июля"
            8 -> return "Августа"
            9 -> return "Сентября"
            10 -> return "Октября"
            11 -> return "Ноября"
            12 -> return "Декабря"
        }
        return null
    }
}