package com.fantasy.sanqianfa.view.login

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.fantasy.components.extension.compose.Icon
import com.fantasy.components.extension.compose.Image
import com.fantasy.components.extension.f1c
import com.fantasy.components.theme.CXColor
import com.fantasy.components.theme.CXFont
import com.fantasy.components.widget.CXButton
import com.fantasy.components.widget.CXScaffold
import com.fantasy.sanqianfa.R

@Composable
fun WellComeView(vm: LoginViewModel = viewModel()) {
    CXScaffold(
        topBar = {},
        bottomBar = {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 48.dp)
                    .navigationBarsPadding(),
                contentAlignment = Alignment.Center
            ) {
                CXButton(
                    onClick = {
                        vm.nextStep()
                    },
                    modifier = Modifier
                        .clip(CircleShape)
                        .size(44.dp),
                    backgroundColor = CXColor.main
                ) {
                    Icon(R.drawable.chevron_right, size = 16)
                }
            }
        }
    ) {
        Column(
            modifier = Modifier
                .padding(48.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(48.dp)
        ) {
            Spacer(modifier = Modifier.weight(1f))
            Image(
                id = vm.loginStep.image,
                contentScale = ContentScale.FillWidth,
                modifier = Modifier.padding(24.dp)
            )
            Text(
                text = vm.loginStep.wellComeText,
                style = CXFont.f1.v1.f1c
            )
            Spacer(modifier = Modifier.weight(1f))
        }
    }
}

@Preview
@Composable
fun WellComeViewPreview() {
    WellComeView()
}