package com.kennethss.movie.domain.movie.usecase

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filterIsInstance
import kotlinx.coroutines.flow.filterNot
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach

suspend fun <R> withResult(action: suspend () -> R) = try {
    Result.Success(action.invoke())
} catch (e: Exception) {
    Result.Error(e)
}

fun <T> Flow<T>.mapToResult() = map { item ->
    if (item is Throwable) {
        Result.Error(Exception(item))
    } else {
        Result.Success(item)
    }
}

fun <T> Flow<Result<T>>.onLoading(action: suspend () -> Unit) = onEach {
    if (it is Result.Loading) {
        action.invoke()
    }
}

fun <T> Flow<Result<T>>.onSuccess(action: suspend (T) -> Unit) = onEach {
    if (it is Result.Success) {
        action.invoke(it.data)
    }
}

fun <T> Flow<Result<T>>.onError(action: suspend (Throwable) -> Unit) = onEach {
    if (it is Result.Error) {
        action.invoke(it.throwable)
    }
}

fun <T> Flow<Result<T>>.onErrorResult(action: suspend (Result.Error) -> Unit) = onEach {
    if (it is Result.Error) {
        action.invoke(it)
    }
}

fun <T> Flow<Result<T>>.filterNotLoading() = filterNot { it is Result.Loading }

fun <T> Flow<Result<T>>.filterSuccessDataOrThrowIfError() = throwIfError().filterSuccessData()

fun <T> Flow<Result<T>>.throwIfError() = onEach { if (it is Result.Error) throw it.throwable }

fun <T> Flow<Result<T>>.filterSuccessData() = filterIsInstance<Result.Success<T>>().map { it.data }
