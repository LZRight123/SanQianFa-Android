package com.fantasy.sanqianfa.view.divination


import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.background
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.graphicsLayer
import androidx.lifecycle.viewmodel.compose.viewModel
import com.fantasy.components.base.BaseScreen
import com.fantasy.components.base.BaseViewModel
import com.fantasy.components.extension.compose.Icon
import com.fantasy.components.extension.f1c
import com.fantasy.components.extension.f2c
import com.fantasy.components.extension.main
import com.fantasy.components.extension.randomString
import com.fantasy.components.theme.CXColor
import com.fantasy.components.theme.CXFont
import com.fantasy.components.tools.cxlog
import com.fantasy.components.widget.CXScaffold
import com.fantasy.sanqianfa.R
import com.fantasy.sanqianfa.components.Hexagram
import com.fantasy.sanqianfa.components.SQGua
import com.fantasy.sanqianfa.components.SQSmallButton
import com.fantasy.sanqianfa.components.YANG
import com.fantasy.sanqianfa.components.YIN
import com.fantasy.sanqianfa.manager.DivinationTool
import com.fantasy.sanqianfa.manager.YaoType
import io.github.sagar_viradiya.koreography

class DivinationViewModel : BaseViewModel() {
    var running by mutableStateOf(false)
        private set
    var coinFace by mutableStateOf((1..3).map { true })

    // yao 为卦象，共 6 个，用于起卦，用来存错coinFace为正面的个数
    var yao = mutableStateListOf(
        YaoType.未定,
        YaoType.未定,
        YaoType.未定,
        YaoType.未定,
        YaoType.未定,
        YaoType.未定,
    )
    var divinationIndex by mutableIntStateOf(0)
    val isOver get() = divinationIndex == 6
    var gua by mutableStateOf<Hexagram?>(null)
    var bianGua by mutableStateOf<Hexagram?>(null)


    fun divination() {
        if (isOver) {
            //解卦
            return
        }

        if (running) return

        running = true
        coinFace = (1..3).map { listOf(false, true).random() }
        cxlog("coinFace: $coinFace")
    }

    fun divinationEnd() {
        if (!running) return

        running = false
        val faceCount = coinFace.count { it }
        yao[divinationIndex] = DivinationTool.getYaoType(faceCount)
        divinationIndex += 1
        if (isOver) {
            gua = DivinationTool.getOriginalHexagram(yao)
            bianGua = DivinationTool.getChangedHexagram(yao)
        }
    }
}

class DivinationView(val question: String) : BaseScreen() {
    @Composable
    override fun body() {
        val vm: DivinationViewModel = viewModel()

        Box(
            modifier = Modifier
                .background(CXColor.b1)
                .systemBarsPadding()
                .fillMaxSize()
                .padding(16.dp)
        ) {
            if (vm.isOver) {
                Text(text = question, style = CXFont.big1b.v1.f1c)
            } else {
                coins()
            }

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxWidth()
            ) {

                Spacer(modifier = Modifier.weight(1f))
                result()
                Spacer(modifier = Modifier.weight(1f))
                SQSmallButton(text = if (vm.isOver) "解卦" else "起卦（${vm.divinationIndex + 1}/6）") {
                    vm.divination()
                }
            }
        }

    }

    @Composable
    fun result(vm: DivinationViewModel = viewModel()) {
        AnimatedVisibility(
            visible =  vm.isOver,
            enter = fadeIn(tween(1000)),
            exit = fadeOut(),
            modifier = Modifier.scale(3f)
        ) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                vm.gua?.let {
                    Column(
                        verticalArrangement = Arrangement.spacedBy(12.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = "此刻",
                            style = CXFont.f1.v1.main,
                            modifier = Modifier.scale(0.6f)
                        )
                        SQGua(hexagram = it)
                    }
                }
                vm.bianGua?.let {
                    Column(
                        verticalArrangement = Arrangement.spacedBy(12.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = "未来",
                            style = CXFont.f1.v1.main,
                            modifier = Modifier.scale(0.6f)
                        )
                        SQGua(hexagram = it)
                    }
                }

            }
        }
        if (vm.isOver) {


        } else {
            Column(
                verticalArrangement = Arrangement.spacedBy(12.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxWidth()
            ) {
                vm.yao.reversed().forEach { type ->
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(12.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        when (type) {
                            YaoType.老阳, YaoType.少阳 -> YANG()
                            YaoType.少阴, YaoType.老阴 -> YIN()
                            YaoType.未定 -> {}
                        }

                        Text(text = type.name, style = CXFont.f3.v1.f2c)

                    }
                }
            }
        }

    }

    @Composable
    fun coins(vm: DivinationViewModel = viewModel()) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            repeat(3) { index ->
                val animation = remember {
                    Animatable(0f)
                }

                /**
                 * animation.value 在 0-90f, 270-360f间为正面，否则为反面
                 *
                 * frontOpacity 的值在 0-90f ,270f-360f 间为 1f，否则为 0f
                 * backOpacity 的值在 0-90f ,270f-360f 间为 0f，否则为 1f
                 */
                val frontOpacity by remember {
                    derivedStateOf {
                        when (animation.value % 360f) {
                            in 0f..90f, in 270f..360f -> 1f
                            else -> 0f
                        }
                    }
                }
                val ko = remember(vm.running) {
                    koreography {
                        move(animation, 0f, animationSpec = tween(0))
                        val coin = 360f * (3..6).random() + (if (vm.coinFace[index]) 0f else 180f)
                        move(
                            animation,
                            coin,
                            animationSpec = tween((150..180).random())
                        )
                    }
                }
                LaunchedEffect(vm.running) {
                    if (vm.running) {
                        ko.dance(this) {
                            vm.divinationEnd()
                        }
                    }
                }
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .graphicsLayer {
                            rotationY = animation.value
                            cameraDistance = 15f * density
                        }
                        .clip(CircleShape)
                        .background(CXColor.main)
                        .weight(1f)
                        .aspectRatio(1f)
                ) {
                    // f
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier
                            .alpha(frontOpacity)
                            .background(CXColor.main)
                            .fillMaxSize()
                    ) {
                        Icon(id = R.drawable.tabbar_profile, size = 56)
                    }
                    // b
                    Box(
                        modifier = Modifier
                            .alpha(1 - frontOpacity)
                            .background(CXColor.blue)
                            .fillMaxSize()
                    ) {

                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun Preview() {
    DivinationView(question = randomString()).body()
}