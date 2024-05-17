package com.example.finalprojectstocks.presentation

sealed class UIState<T>{
    data class Success<T>(val data: T): UIState<T>()
    data class Error<T>(val error: String): UIState<T>()
    class Loading<T>(): UIState<T>()
}