package com.fantasy.sanqianfa.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Indication
import androidx.compose.foundation.background
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import com.fantasy.components.extension.compose.Icon
import com.fantasy.components.extension.f1c
import com.fantasy.components.theme.CXColor
import com.fantasy.components.theme.CXFont
import com.fantasy.components.widget.CXButton
import com.fantasy.sanqianfa.R

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

/*
 struct SmallBtn: View {
        let action: () -> Void
        let text: String
        init(text: String, action: @escaping () -> Void) {
            self.text = text
            self.action = action
        }

        var body: some View {
            SQDesign.SQButton {
                action()
            } label: {
                Text(text)
                    .makeSQText(.SQ.f1b, color: .SQ.f1)
                    .padding(12)
                    .padding(.horizontal, 48)
                    .background(Color.SQ.main)
                    .clipShape(Capsule())
            }
        }
    }
 */
@Composable
fun SQSmallButton(
    text: String,
    onClick: () -> Unit
) {
    CXButton(
        onClick = onClick,
        modifier = Modifier
            .clip(CircleShape)
            .background(CXColor.main)
            .padding(horizontal = 48.dp)
            .padding (12.dp),
    ) {
        Text(
            text = text,
            style = CXFont.f1b.v1.f1c
        )
    }
}

@Composable
fun SQIcon(
    @DrawableRes id: Int,
    size: Int = 24,
    tint: Color = CXColor.f1
) {
    Icon(
        id = id,
        size = size,
        tint = tint
    )
}

@Preview(showBackground = true)
@Composable
private fun SQDesignPreview() {
    Column(
        verticalArrangement = Arrangement.spacedBy(12.dp),
        modifier = Modifier
            .padding(16.dp)
            .fillMaxSize()
    ) {
        SQMainButton(text = "主按钮") {

        }

        SQSmallButton(text = "小按钮") {

        }
        SQIcon(id = R.drawable.tabbar_home)
    }
}