package com.example.features.common.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.features.authorization.domain.GetUserByLoginAndPasswordUseCase
import com.example.features.common.database.user.model.User
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import timber.log.Timber
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext

class SharedViewModel(
    private val getUserByLoginAndPasswordUseCase: GetUserByLoginAndPasswordUseCase
) : ViewModel() {

    private val _currentUser = MutableStateFlow<User?>(null)
    val currentUser: StateFlow<User?> get() = _currentUser

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> get() = _error

    private var isManualUserSet = false

    fun getUser(login: String, password: String, callback: (Boolean, Int) -> Unit) {
        viewModelScope.launchSafely {
            try {
                val user = getUserByLoginAndPasswordUseCase(login, password)
                if (user != null) {
                    _currentUser.value = user
                    _error.value = null
                    callback(true, user.id)
                } else {
                    _error.value = "Пользователь не найден"
                    callback(false, 0)
                }
            } catch (e: Exception) {
                _error.value = "Ошибка: ${e.message}"
                callback(false, 0)
            }
        }
    }

    fun setCurrentUser(user: User?) {
        isManualUserSet = true
        _currentUser.value = user
    }
}

private val defaultExceptionHandler by lazy {
    CoroutineExceptionHandler { _, throwable -> Timber.e(throwable.message) }
}


fun CoroutineScope.launchSafely(
    context: CoroutineContext = EmptyCoroutineContext,
    start: CoroutineStart = CoroutineStart.DEFAULT,
    onError: ((Throwable) -> Unit)? = null,
    block: suspend CoroutineScope.() -> Unit
): Job {
    return launch(
        context = context + if (onError == null) {
            defaultExceptionHandler
        } else {
            CoroutineExceptionHandler { context, throwable ->
                onError.invoke(throwable)
                defaultExceptionHandler.handleException(context, throwable)
            }
        },
        start = start,
        block = block
    )
}
