package ru.teledoc.studio.mealdb.models

sealed class DataResult {
    data class Success<T>(val data: T) : DataResult()
    data class Failure(val error: Throwable) : DataResult()
}