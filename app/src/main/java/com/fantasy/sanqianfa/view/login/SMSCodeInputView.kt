package com.fantasy.sanqianfa.view.login

import androidx.compose.foundation.background
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.Modifier
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.Dp
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.ui.draw.clip
import androidx.lifecycle.viewmodel.compose.viewModel
import com.fantasy.components.extension.f1c
import com.fantasy.components.theme.CXColor
import com.fantasy.components.theme.CXFont
import com.fantasy.components.widget.CXTextFiled
import com.fantasy.sanqianfa.components.SQSmallButton

@Composable
fun SMSCodeInputView(vm: LoginViewModel = viewModel()) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(68.dp)
    ) {
        Spacer(modifier = Modifier.weight(1f))
        Text(
            text = "请输入你收到的验证码",
            style = CXFont.big3.v1.f1c
        )
        CXTextFiled(
            value = vm.phoneNumberInput,
            textStyle = CXFont.f1.v1.f1c,
            placeholder = "验证码",
            modifier = Modifier
                .clip(RoundedCornerShape(12.dp))
                .background(CXColor.b2)
                .padding(8.dp)
                .fillMaxWidth()
        ) {
            vm.phoneNumberInput = it
        }
        SQSmallButton(text = "登录") {
            vm.nextStep()
        }

        Spacer(modifier = Modifier.weight(1f))
    }
}

@Preview
@Composable
fun SMSCodeInputViewPreview() {
    SMSCodeInputView()
}