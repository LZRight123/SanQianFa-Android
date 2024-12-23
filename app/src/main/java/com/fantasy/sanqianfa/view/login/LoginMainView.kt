package com.fantasy.sanqianfa.view.login


import androidx.compose.foundation.background
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import cafe.adriel.voyager.core.screen.Screen
import com.fantasy.components.base.BaseScreen
import com.fantasy.components.theme.CXColor
import com.fantasy.components.widget.CXButton
import com.fantasy.components.widget.CXScaffold
import com.fantasy.sanqianfa.manager.userManager
import com.fantasy.sanqianfa.routeToMain

class LoginMainView: BaseScreen() {
    @Composable
    override fun body() {
        CXScaffold(
            topBar = {}
        ) {
            CXButton(
                onClick =  {
                    userManager.mockSuccess()
                    routeToMain()
                },
                modifier = Modifier
                    .background(CXColor.main, CircleShape)
                    .padding(16.dp)
            ) {
                 Text(text ="登录")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun Preview() {
    LoginMainView().Content()
}