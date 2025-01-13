package com.example.features.profile.viewmodel

import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.features.common.database.profile.model.UserAdditionalInfo
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class UserAdditionalInfoViewModel(
    private val createProfileUser: CreateUserAdditionalInfoUseCase,
    private val getUserAdditionalInfoById: GetUserAdditionalInfoByIdUseCase,
    private val updateUserAdditionalInfoUseCase: UpdateUserAdditionalInfoUseCase
) : ViewModel() {

    var email = mutableStateOf("")
    var name = mutableStateOf("")
    var age = mutableStateOf("")
    var city = mutableStateOf("")
    var nationality = mutableStateOf("")

    private val _currentUserAdditionalInfo = MutableStateFlow<UserAdditionalInfo?>(null)
    val currentUserAdditionalInfo: StateFlow<UserAdditionalInfo?> get() = _currentUserAdditionalInfo

    private val _userInfo = MutableStateFlow<UserAdditionalInfo?>(null)
    val userInfo: StateFlow<UserAdditionalInfo?> = _userInfo

    var editingAdditionalId = mutableStateOf<Int?>(null)

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> get() = _error

    var num = mutableIntStateOf(0)

    fun loadUserInfo(userId: Int) {
        viewModelScope.launch {
            val userInfo = getUserAdditionalInfoById(userId)
            _userInfo.value = userInfo
        }
    }

    fun addAdditionalInfo(
        userId: Int,
        name: String,
        age: String,
        city: String,
        nationality: String
    ) {
        viewModelScope.launch {
            createProfileUser(
                userId,
                email = "",
                name = name,
                age = age,
                city = city,
                nationality = nationality
            )
        }
    }

    fun updateUserAdditionalInfo(userAdditionalInfo: UserAdditionalInfo) {
        viewModelScope.launch {
            updateUserAdditionalInfoUseCase(userAdditionalInfo)
            loadUserInfo(userId = userAdditionalInfo.userId)
            editingAdditionalId.value = null
        }
    }
}

//    fun createUserAdditionalInfo(userId: Int) {
//        viewModelScope.launch {
//            val updatedInfo = UserAdditionalInfo(
//                id = userId,
//                email = email.value,
//                name = name.value,
//                age = age.value,
//                city = city.value,
//                nationality = nationality.value
//            )
//            createProfileUser(updatedInfo.email, updatedInfo.name, updatedInfo.age, updatedInfo.city, updatedInfo.nationality)
//            loadUserAdditionalInfo(userId)
//        }
//    }
//
//    fun loadUserAdditionalInfo(userId: Int) {
//        viewModelScope.launch {
//            try {
//                val info = getUserAdditionalInfoById(userId)
//                _currentUserAdditionalInfo.value = info
//                email.value = info.email
//                name.value = info.name
//                age.value = info.age
//                city.value = info.city
//                nationality.value = info.nationality
//            } catch (e: Exception) {
//                _error.value = e.message
//            }
//        }
//    }

