package com.fantasy.sanqianfa.view.main


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.fantasy.components.base.BaseScreen
import com.fantasy.components.base.BaseViewModel
import com.fantasy.components.tools.cxlog
import com.fantasy.components.widget.CXScaffold
import com.fantasy.sanqianfa.components.SQSmallButton
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

// 记住一个原则： 永远不要在实际应用开发中组赛主线程，这些方法只是为了演示
// viewmodelscope 的好处
// 1. 作用域是安全的，当viewmodel销毁时，协程会自动取消。 不需要手动管理携程生命周期， 不需要担心内存泄漏。
// 2. 结构化并发，可以在viewmodel中创建多个协程。

class SuspendViewModel : BaseViewModel() {

    fun service() {
        viewModelScope.launch {
            val a = async { executeRequestA() }
            val b = async { executeRequestB() }
            val c = async { executeRequestC() }
            val d = async { executeRequestD() }
            awaitAll(a, b, c, d)
        }


    }

    // 模拟网络请求 A
    private suspend fun executeRequestA() {
        delay(1000) // 模拟网络延迟
        cxlog("Request A Result")
    }

    // 模拟网络请求 B
    private suspend fun executeRequestB() {
        delay(1500) // 模拟网络延迟
        cxlog("Request B Result")
    }

    // 模拟网络请求 C
    private suspend fun executeRequestC() {
        delay(2000) // 模拟网络延迟
        cxlog("Request C Result")
    }

    // 模拟网络请求 D
    private suspend fun executeRequestD() {
        delay(1000) // 模拟网络延迟
        cxlog("Request D Result")
    }
}

class SuspendView : BaseScreen() {
    @Composable
    override fun body() {
        val vm: SuspendViewModel = viewModel()
        CXScaffold(
            title = "学习 Suspend",
        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center
            ) {
                SQSmallButton(
                    text = "开始",
                ) {
                    vm.service()
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun Preview() {
    SuspendView().body()
}