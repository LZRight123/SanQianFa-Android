package com.fantasy.sanqianfa.view.divination


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.fantasy.components.base.BaseScreen
import com.fantasy.components.base.BaseViewModel
import com.fantasy.components.extension.compose.CXPaddingValues
import com.fantasy.components.extension.f1c
import com.fantasy.components.extension.randomString
import com.fantasy.components.theme.CXColor
import com.fantasy.components.theme.CXFont
import com.fantasy.components.tools.cxlog
import com.fantasy.components.tools.fromJson
import com.fantasy.components.widget.CXMarkdown
import com.fantasy.sanqianfa.manager.SSEController
import com.fantasy.sanqianfa.manager.Status

class AnswerViewModel(
    val current: String,
    val future: String,
    val q: String
) : BaseViewModel() {
    var answer by mutableStateOf("")
        private set

    init {
        fetchAnswer()
    }

    data class AnswerRequest(
        val current: String,
        val future: String,
        val q: String
    )

    data class AnswerResponse(
        val content: String
    )

    fun fetchAnswer() {
        SSEController.connect(
            url = "api/v1/ai/divination",
            body = AnswerRequest(
                current = "乾卦",
                future = "坤卦",
                q = "我最近的事业发展如何？"
            )
        ) {
            cxlog(it.desc)
            when (it) {
                is Status.Event -> {
                   answer = fromJson<AnswerResponse>(it.data)?.content ?: ""
                }
                is Status.Failure -> answer = it.desc
                else -> {
                }
            }
        }
    }
}

class AnswerView(
    val current: String,
    val future: String,
    val q: String
) : BaseScreen() {
    @Composable
    override fun body() {
        val vm: AnswerViewModel = viewModel {
            AnswerViewModel(
                current = current,
                future = future,
                q = q
            )
        }

        LazyColumn(
            modifier = Modifier
                .padding(16.dp)
                .clip(RoundedCornerShape(18.dp))
                .background(CXColor.b2)
                .fillMaxSize(),
            contentPadding = CXPaddingValues(all = 16.dp),
        ) {
            item {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.Center
                ) {
                    CXMarkdown(
                        content = vm.answer,
                        style = CXFont.f1.v1.f1c
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun Preview() {
    AnswerView(
        current = "乾卦",
        future = "坤卦",
        q = "我最近的事业发展如何？"
    ).body()
}