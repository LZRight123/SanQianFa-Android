package com.fantasy.sanqianfa.view.main

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.fantasy.components.extension.f1c
import com.fantasy.components.theme.CXColor
import com.fantasy.components.theme.CXFont
import com.fantasy.components.tools.Apphelper
import com.fantasy.sanqianfa.components.SQSmallButton
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun HomeAskView(
    vm: MainViewModel = viewModel()
) {
    AnimatedVisibility(
        visible = vm.showInputCard,
        label ="",
        enter = fadeIn(animationSpec = tween(400)),
        exit = fadeOut(animationSpec = tween(400))
    )  {
        Box(
            modifier = Modifier
                .background(CXColor.b1.copy(0.5f))
                .fillMaxSize()
        )
    }

    AnimatedVisibility(
        visible = vm.showInputCard,
        enter = slideInVertically(
            initialOffsetY = { it },
        ) + fadeIn(animationSpec = tween(800)),
        exit = slideOutVertically(
            targetOffsetY = { it },
        ) + fadeOut(animationSpec = tween(400))
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            HomeAskViewContent()
        }
    }
}

@Composable
private fun HomeAskViewContent(
    vm: MainViewModel = viewModel()
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .clip(RoundedCornerShape(18.dp))
                .background(CXColor.b1)
                .padding(horizontal = 24.dp)
                .padding(top = 48.dp, bottom = 32.dp)
        ) {
            Text(
                text = "输入当下的疑惑：",
                style = CXFont.f3.v1.f1c.copy(letterSpacing = 3.sp),
            )

            Spacer(modifier = Modifier.height(12.dp))

            BasicTextField(
                value = vm.userInput,
                onValueChange = { vm.userInput = it },
                modifier = Modifier
                    .height(120.dp)
                    .fillMaxWidth(),
                textStyle = CXFont.f1.v1.copy(color = CXColor.f1),
                cursorBrush = SolidColor(CXColor.f1)
            )

            Spacer(modifier = Modifier.height(12.dp))

            SQSmallButton(text = "起卦") {
                vm.showInputCard = false
                Apphelper.toast(vm.userInput)
                vm.userInput = ""

            }
        }

        Spacer(modifier = Modifier.height(12.dp))

        Text(
            text = "心诚则灵",
            style = CXFont.f3.v1.copy(letterSpacing = 3.sp),
            color = CXColor.f1
        )
    }

}

@Preview
@Composable
fun HomeAskViewPreview() {
    val vm: MainViewModel = viewModel()
    vm.showInputCard = true
    HomeAskView()
}