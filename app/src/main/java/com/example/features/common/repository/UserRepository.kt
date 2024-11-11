package com.example.features.common.repository

import com.example.features.common.database.user.model.User

interface UserRepository {

    suspend fun getUsers(): List<User>
    suspend fun getUserById(userId: Int): User?
    suspend fun getUserByLoginAndPassword(login: String, password: String): User?
    suspend fun createUser(login: String, password: String): User
    suspend fun deleteUser(userId: Int)
}