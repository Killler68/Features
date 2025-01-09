package com.example.features.common.database.profile.model

data class UserAdditionalInfo(
    val id: Int,
    val userId: Int,
    val email: String,
    val name: String,
    val age: String,
    val city: String,
    val nationality: String
)

//val emptyUserAdditionalInfo = UserAdditionalInfo(0, 0,"", "", "","", "")


fun UserAdditionalInfoData.toUserAdditionalInfo() = UserAdditionalInfo(
    id, userId, email, name, age, city, nationality
)

