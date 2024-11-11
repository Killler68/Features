package com.example.features.common.repository

import com.example.features.common.database.user.tuple.CreateUseTuple
import com.example.features.common.database.user.tuple.DeleteUserTuple
import com.example.features.common.database.user.model.User
import com.example.features.common.database.user.UserDao
import com.example.features.common.database.user.model.toUser
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class UserRepositoryImpl(
    private val userDao: UserDao
) : UserRepository {

    override suspend fun getUsers(): List<User> =
        withContext(Dispatchers.IO) {
            val user = userDao.getUsers()
            user.map { it.toUser() }.toList()
        }

    override suspend fun getUserById(userId: Int): User =
        withContext(Dispatchers.IO) {
            val user = userDao.getUserById(userId)?.toUser()
            user ?: throw Exception("User $userId is not found")
        }

    override suspend fun getUserByLoginAndPassword(login: String, password: String): User =
        withContext(Dispatchers.IO) {
            val user = userDao.getUserByLoginAndPassword(login, password)?.toUser()
            user ?: throw Exception("User $login or $password not found")
        }

    override suspend fun createUser(login: String, password: String): User =
        withContext(Dispatchers.IO) {
            userDao.createUser(
                CreateUseTuple(
                    login = login,
                    password = password
                )
            )
            val createUser = userDao.getUsers().last().toUser()
            createUser
        }

    override suspend fun deleteUser(userId: Int) =
        withContext(Dispatchers.IO) {
            userDao.deleteUser(
                DeleteUserTuple(
                    id = userId
                )
            )
        }
}