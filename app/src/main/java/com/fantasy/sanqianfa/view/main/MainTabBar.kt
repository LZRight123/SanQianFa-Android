package com.fantasy.sanqianfa.view.main

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.lifecycle.viewmodel.compose.viewModel
import com.fantasy.components.extension.color
import com.fantasy.components.extension.compose.Icon
import com.fantasy.components.theme.CXColor
import com.fantasy.components.theme.CXFont
import com.fantasy.components.tools.Apphelper
import com.fantasy.components.tools.screenWith
import com.fantasy.components.widget.CXButton
import com.fantasy.sanqianfa.model.TabBarType

@Composable
fun MainTabBar() {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .padding(vertical = 8.dp)
            .navigationBarsPadding()
    ) {
        TabBarType.entries.forEach { type ->
            if (type == TabBarType.add) {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier.width((screenWith * 0.2 + 24).dp)
                ) {
                    CXButton(
                        onClick = {
                            Apphelper.toast(msg = "临时起卦")
                        },
                        modifier = Modifier
                            .background(CXColor.f1, CircleShape)
                            .requiredSize(58.dp)
                    ) {
                        Icon(
                            id = type.icon,
                            tint = CXColor.b1,
                            size = 24
                        )
                    }
                }
            } else {
                TabBarItem(
                    type = type,
                    modifier = Modifier.weight(1f)
                )
            }
        }
    }
}

@Composable
private fun TabBarItem(
    type: TabBarType,
    modifier: Modifier = Modifier,
    vm: MainViewModel = viewModel()
) {
    val selected = type == vm.currentTabBar
    CXButton(
        onClick = {
            vm.currentTabBar = type
        },
        modifier = modifier
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(8.1371.dp),
        ) {
            Icon(
                id = type.icon,
                size = 24,
                tint = if (selected) CXColor.main else CXColor.f2
            )
            Text(
                text = type.title,
                style = CXFont.f3.v1.color(
                    color = if (selected) CXColor.main else CXColor.f2
                )
            )
        }
    }
}


@Preview
@Composable
fun MainTabBarPreview() {
    MainTabBar()
}