package com.fantasy.sanqianfa.components

import androidx.compose.foundation.Indication
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import com.fantasy.components.theme.CXColor
import com.fantasy.components.theme.CXFont
import com.fantasy.components.widget.CXButton

@Composable
fun SQMainButton(
    text: String,
    isShowShimmer: Boolean = false,
    enabled: Boolean = true,
    isLoading: Boolean = false,
    onClick: () -> Unit,
) {
    CXButton(
        onClick = onClick,
        backgroundColor = CXColor.f1,
        contentColor = CXColor.b1,
        isShowShimmer = isShowShimmer,
        enabled = enabled,
        isLoading = isLoading,
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier
                .padding(vertical = 16.dp)
                .fillMaxWidth()
        ) {
            Text(
                text = text,
                style = CXFont.f2.v1,
                color = CXColor.b1
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun SQDesignPreview() {
    Column(
        modifier = Modifier.padding(16.dp)
            .fillMaxSize()
    ) {
        SQMainButton(text = "主按钮") {

        }
    }
}