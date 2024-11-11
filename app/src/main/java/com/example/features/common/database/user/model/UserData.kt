package com.example.features.common.database.user.model

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "user_table")
data class UserData(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val login: String,
    val password: String
)
