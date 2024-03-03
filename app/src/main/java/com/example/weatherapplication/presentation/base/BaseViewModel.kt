package com.example.weatherapplication.presentation.base

import androidx.lifecycle.ViewModel
import com.example.weatherapplication.domain.base.ExceptionHandler
import com.example.weatherapplication.domain.base.Result
import com.example.weatherapplication.domain.base.Error
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.channels.consumeEach
import kotlin.coroutines.CoroutineContext

@ObsoleteCoroutinesApi
abstract class BaseViewModel(private val exceptionHandler: ExceptionHandler) : ViewModel(), CoroutineScope {
    private val job = Job()

    protected abstract val receivedChannel: ReceiveChannel<Result<Any, Error>>

    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.Main + CoroutineExceptionHandler { _, throwable ->
            exceptionHandler.onException(throwable)
        }

    abstract fun resolve(value: Result<Any, Error>)

    init {
        processStream()
    }

    private fun processStream() {
        launch(coroutineContext) {
            receivedChannel.consumeEach {
                resolve(it)
                if (it is Result.Failuare) {
                    it.errorData.tr?.let { tr ->
                        exceptionHandler.onException(tr)
                    }
                    if (it.errorData.code != 0) {
                        exceptionHandler.onException(Exception("Error ${it.errorData.code} with message ${it.errorData.message}"))
                    }
                }
            }
        }
    }

    override fun onCleared() {
        receivedChannel.cancel()
        coroutineContext.cancel()
        super.onCleared()
    }
}