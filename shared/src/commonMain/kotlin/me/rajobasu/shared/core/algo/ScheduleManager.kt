package me.rajobasu.shared.core.algo

import me.rajobasu.shared.core.model.Schedule
import me.rajobasu.shared.core.model.Task

class ScheduleManager {
    private val taskList = mutableListOf<Task>()

    fun addTask(task: Task): Boolean {
        currentSchedule = generateSchedule( taskList.apply {
            add(task)
        })
        return true
    }

    var currentSchedule: Schedule = Schedule(listOf())
}