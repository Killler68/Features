package com.example.features.common.usecase

interface CheckLocaleUseCase {

    suspend operator fun invoke(): String
}