package com.example.features.common.database.user

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.features.common.database.user.model.UserData
import com.example.features.common.database.user.tuple.CreateUseTuple
import com.example.features.common.database.user.tuple.DeleteUserTuple


@Dao
interface UserDao {

    @Query("SELECT * FROM user_table ")
    suspend fun getUsers(): List<UserData>

    @Query("SELECT * FROM user_table WHERE id = :userId")
    suspend fun getUserById(userId: Int): UserData?

    @Query("SELECT * FROM user_table WHERE login = :login AND password = :password")
    suspend fun getUserByLoginAndPassword(login: String, password: String): UserData?

    @Insert(entity = UserData::class)
    suspend fun createUser(userTuple: CreateUseTuple): Long

    @Delete(entity = UserData::class)
    suspend fun deleteUser(deleteUserTuple: DeleteUserTuple)
}