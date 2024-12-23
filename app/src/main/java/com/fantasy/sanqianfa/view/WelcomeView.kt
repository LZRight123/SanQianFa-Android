package com.fantasy.sanqianfa.view


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import cafe.adriel.voyager.core.screen.Screen
import com.fantasy.components.extension.f1c
import com.fantasy.components.theme.CXColor
import com.fantasy.components.theme.CXFont
import com.fantasy.components.widget.CXLottieView
import com.fantasy.sanqianfa.R
import com.fantasy.sanqianfa.routeToMain
import kotlinx.coroutines.delay

class WelcomeView : Screen {
    @Composable
    override fun Content() {
        LaunchedEffect(Unit) {
            delay(500)
            routeToMain()
        }
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .background(CXColor.b1)
                .fillMaxSize()
        ) {
            CXLottieView(
                resId = R.raw.loading_gray,
                modifier = Modifier.fillMaxWidth(0.5f)
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "欢迎来到，三钱六问",
                style = CXFont.f1.v1.f1c
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun Preview() {
    WelcomeView().Content()
}