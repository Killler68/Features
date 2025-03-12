package com.example.features.registration.viewmodel

interface CreateUserUseCase {

    suspend operator fun invoke(login: String, password: String): Int
}