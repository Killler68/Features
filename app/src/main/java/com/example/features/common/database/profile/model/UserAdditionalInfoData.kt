package com.example.features.common.database.profile.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_additional_info")
data class UserAdditionalInfoData(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val email: String,
    val name: String,
    val age: String,
    val city: String,
    val nationality: String
)
