package com.example.features.profile.data.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "profile")
data class ProfileData(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo(name = "userId") val userId: Int,
    @ColumnInfo(name = "login") val userLogin: String,
    val email: String,
    val name: String,
    val age: String,
    val city: String,
    val nationality: String
)
