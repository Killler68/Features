package com.example.features.common.repository.task

import com.example.features.common.database.task.Task
import com.example.features.common.database.task.TaskDao
import com.example.features.notes.task.model.TaskModel

class TaskRepositoryImpl(
    private val taskDao: TaskDao
) : TaskRepository {

    override suspend fun getTasks(userId: Int): List<TaskModel> =
        taskDao.getTasks(userId).map {
            TaskModel(
                it.taskId,
                it.taskText,
                it.isComplete,
                it.userId
            )
        }


    override suspend fun createTask(task: TaskModel) {
        taskDao.createTask(
            Task(
                taskId = 0,
                taskText = task.taskText,
                isComplete = task.isComplete,
                userId = task.userId
            )
        )
    }

    override suspend fun deleteTask(task: TaskModel) {
        taskDao.deleteTask(taskId = task.taskId)

    }

    override suspend fun updateTask(task: TaskModel) {
        taskDao.updateTask(
            Task(
                taskId = task.taskId,
                taskText = task.taskText,
                isComplete = task.isComplete,
                userId = task.userId
            )
        )
    }
}