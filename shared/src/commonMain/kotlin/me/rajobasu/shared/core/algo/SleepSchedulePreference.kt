package me.rajobasu.shared.core.algo

class SleepSchedulePreference(
    val startTime: Time,
    val endTime: Time
) {
    fun getSleepPeriods(): List<Pair<Time, Int>> {
        return if (startTime.getTotalMinutes() > endTime.getTotalMinutes()) {
            listOf(
                Pair(startTime, DAY_END.minus(startTime).getTotalMinutes()), Pair(
                    DAY_BEGIN, endTime.minus(DAY_BEGIN).getTotalMinutes()
                )
            )
        } else listOf(Pair(startTime, endTime.minus(startTime).getTotalMinutes()))
    }
}

