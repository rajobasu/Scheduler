package me.rajobasu.shared.core.commands

import kotlinx.datetime.toLocalDateTime
import me.rajobasu.shared.core.algo.ScheduleManager
import me.rajobasu.shared.core.model.Task
import me.rajobasu.shared.core.model.TaskType

sealed class Command {
    abstract fun execute(scheduleManager: ScheduleManager): Boolean
}

class AddDeadlineCommand(
    private var arguments: List<String>
) : Command() {
    private val task: Task

    init {
        if (arguments.size != 3) {
            throw IllegalArgumentException("Incorrect number of paramaters for Add Command")
        }
        arguments = arguments.map { x -> x.trim() }

        val expectedTime = arguments[0].toInt()
        val deadline = arguments[1].replace(' ', 'T').toLocalDateTime()
        val description = arguments[2]

        task = Task(
            estimatedTimeInMinutes = expectedTime,
            deadline = deadline,
            description = description,
            taskType = TaskType.FluidTask.DeadlineTask,
        )
    }

    override fun execute(scheduleManager: ScheduleManager) = scheduleManager.addTask(task)
}


class AddFixedCommand(
    private var arguments: List<String>
) : Command() {
    private val task: Task

    init {
        if (arguments.size != 3) {
            throw IllegalArgumentException("Incorrect number of paramaters for Add Command")
        }
        arguments = arguments.map { x -> x.trim() }

        val startTime = arguments[0].replace(' ', 'T').toLocalDateTime()
        val expectedTime = arguments[1].toInt()
        val description = arguments[2]

        task = Task(
            estimatedTimeInMinutes = expectedTime,
            startTime = startTime,
            description = description,
            taskType = TaskType.FixedTask.LectureTask,
        )
    }

    override fun execute(scheduleManager: ScheduleManager) = scheduleManager.addTask(task)
}

class RefreshCommand : Command() {
    override fun execute(scheduleManager: ScheduleManager): Boolean {
        scheduleManager.refresh()
        return true
    }

}





