package com.example.features.common.repository

import com.example.features.common.database.user.model.User

interface UserRepository {

    suspend fun getUsers(): List<User>
    suspend fun getUserById(userId: Int): User? // check not nullable implementation
    suspend fun getUserByLoginAndPassword(login: String, password: String): User?
    suspend fun getUserByLogin(login: String): User?
    suspend fun createUser(login: String, password: String): Int
    suspend fun deleteUser(userId: Int)
}