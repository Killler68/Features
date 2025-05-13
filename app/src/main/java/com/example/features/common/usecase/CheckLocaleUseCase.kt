package com.example.features.common.usecase

interface CheckLocaleUseCase { //todo use case no need interface

    suspend operator fun invoke(): String
}