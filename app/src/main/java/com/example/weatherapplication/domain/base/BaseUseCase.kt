package com.example.weatherapplication.domain.base

import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.Result
import kotlin.coroutines.CoroutineContext

typealias SimpleResult = com.example.weatherapplication.domain.base.Result<Any, Error>


abstract class BaseUseCase<in Params>(private val exceptionHandler: ExceptionHandler) :
    CoroutineScope {
    private val parentJob = SupervisorJob()
    private val mainDispatcher = Dispatchers.Main
    private val backgroundDispatcher = Dispatchers.Default
    protected val resultChannel = Channel<SimpleResult>()

    val receiveChannel : ReceiveChannel<SimpleResult> = resultChannel

    override val coroutineContext: CoroutineContext
        get() = parentJob + mainDispatcher + CoroutineExceptionHandler { _, throwable ->
            exceptionHandler.onException(throwable)
        }

    protected abstract suspend fun run (params: Params)

    operator fun invoke(params : Params) {
        launch {
            withContext(backgroundDispatcher + CoroutineExceptionHandler { _, throwable ->
                exceptionHandler.onException(throwable)
            }) {
                try {
                    run(params)
                } catch (ex: Throwable) {
                    exceptionHandler.onException(ex)
                }
            }
        }
    }

    fun clear () {
        resultChannel.close()
        parentJob.cancel()
    }
}