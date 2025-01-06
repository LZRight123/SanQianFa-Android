package com.fantasy.sanqianfa.view.main


import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.lifecycle.viewmodel.compose.viewModel
import cafe.adriel.voyager.core.screen.Screen
import com.fantasy.components.aamedium.bottomFadingEdge
import com.fantasy.components.base.BaseScreen
import com.fantasy.components.base.BaseViewModel
import com.fantasy.components.extension.compose.addHazeContent
import com.fantasy.components.extension.compose.cxBlur
import com.fantasy.components.extension.f1c
import com.fantasy.components.theme.CXFont
import com.fantasy.components.widget.CXScaffold
import com.fantasy.sanqianfa.model.TabBarType

class MainViewModel : BaseViewModel() {
    var currentTabBar by mutableStateOf(TabBarType.home)
    var showInputCard by mutableStateOf(false)
    var userInput by mutableStateOf("我这个月的桃花怎么样？")
}

class MainView : BaseScreen() {
    @Composable
    override fun body() {
        val vm: MainViewModel = viewModel()
        val blur by animateDpAsState(
            targetValue = if (vm.showInputCard) 20.dp else 0.dp,
            label = "",
            animationSpec = tween(200)
        )
        CXScaffold(
            topBar = {},
            bottomBar = {
                MainTabBar()
            },
            modifier = Modifier.cxBlur(blur)
        ) { innePadding ->
            AnimatedContent(
                targetState = vm.currentTabBar,
                label = "",
                transitionSpec = {
                    fadeIn() togetherWith fadeOut()
                },
                modifier = Modifier
                    .addHazeContent()
                    .bottomFadingEdge()
            ) {
                when (it) {
                    TabBarType.home -> HomeView(tabBarPadding = innePadding.calculateBottomPadding())
                    TabBarType.things -> HistrotyView(tabBarPadding = innePadding.calculateBottomPadding())
                    TabBarType.add -> {}
                    TabBarType.learn -> LearningView()
                    TabBarType.profile -> ProfileView()
                }
            }
        }

        HomeAskView()
    }

    @Composable
    fun DebugText(text: String) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            Text(text = text, style = CXFont.big1.v1.f1c)
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun Preview() {
    MainView().Content()
}