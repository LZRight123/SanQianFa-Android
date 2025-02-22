package com.fantasy.sanqianfa.manager

import com.fantasy.components.tools.toJsonString
import com.fantasy.sanqianfa.AppConfig
import com.fantasy.sanqianfa.api.networking.Networking
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody
import okhttp3.Response
import okhttp3.sse.EventSource
import okhttp3.sse.EventSourceListener
import okhttp3.sse.EventSources

sealed class Status {
    data object Open : Status()
    data class Event(val id: String?, val type: String?, val data: String?) : Status()
    data object Close : Status()
    data class Failure(val throwable: Throwable) : Status()

    val desc: String
        get() = when (this) {
            is Open -> "SSE 连接已打开"
            is Event -> "收到事件：id=$id, 类型=$type, 数据=$data"
            is Close -> "SSE 连接已关闭"
            is Failure -> "SSE 连接失败：${throwable.message}"
        }
}

object SSEController {
    private val client = Networking.generatorOKHttpClient()

    private fun buildSSERequest(
        url: String, body: Any? = null
    ): Request =
        Request.Builder().url(AppConfig.baseUrl + "/" + url)
            .header("Accept", "text/event-stream")
            .header("Cache-Control", "no-cache")
            .header("Connection", "keep-alive")
            .header("Content-Type", "application/json")
            .apply {
                if (body != null) {
                    post(toJsonString(body).toRequestBody("application/json".toMediaType()))
                }
            }.build()

    fun connect(
        url: String,
        body: Any?,
        block: (Status) -> Unit
    ) = EventSources.createFactory(client)
        .newEventSource(buildSSERequest(url, body), object : EventSourceListener() {
            override fun onOpen(eventSource: EventSource, response: Response) {
                block(Status.Open)
            }

            override fun onEvent(
                eventSource: EventSource, id: String?, type: String?, data: String
            ) {
                block(Status.Event(id, type, data))
            }

            override fun onClosed(eventSource: EventSource) {
                block(Status.Close)
            }

            override fun onFailure(eventSource: EventSource, t: Throwable?, response: Response?) {
                block(Status.Failure(t ?: Exception("Unknown error")))
            }
        })
}