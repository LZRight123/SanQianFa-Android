package com.fantasy.sanqianfa.view.main

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
import androidx.compose.ui.unit.Dp
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.fantasy.components.aamedium.bottomFadingEdge
import com.fantasy.components.extension.compose.CXPaddingValues
import com.fantasy.components.extension.compose.addHazeContent
import com.fantasy.components.extension.f2c
import com.fantasy.components.extension.randomString
import com.fantasy.components.theme.CXColor
import com.fantasy.components.theme.CXFont
import com.fantasy.components.widget.CXScaffold
import com.fantasy.sanqianfa.model.TabBarType

@Composable
fun HistrotyView(tabBarPadding: Dp = 0.dp) {
    CXScaffold(
        topBar = {}
    ) {
        LazyColumn(
            contentPadding = CXPaddingValues(
                all = 16.dp,
                bottom = tabBarPadding
            ),
            verticalArrangement = Arrangement.spacedBy(3.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .addHazeContent()
                .bottomFadingEdge()
                .fillMaxSize()
        ) {
            // Header
            item {
                Text(
                    text = "起卦历史",
                    style = CXFont.f3.v1.f2c.copy(letterSpacing = 3.sp),
                    modifier = Modifier
                        .padding(vertical = 16.dp)
                )
            }

            // History items
            items(12) {
                HistoryRow()
            }
        }
    }
}

@Composable
private fun HistoryRow() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                color = CXColor.b2,
                shape = RoundedCornerShape(12.dp)
            )
            .padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        // Title
        Text(
            text = randomString(4, 12),
            style = CXFont.big3b.v1,
            color = CXColor.f1
        )

        // Description
        Text(
            text = randomString(12, 32),
            style = CXFont.f2.v1,
            color = CXColor.f2,
            maxLines = 2
        )
    }
}

@Preview
@Composable
fun HistrotyViewPreview() {
    val vm: MainViewModel = viewModel()
    vm.currentTabBar = TabBarType.things
    MainView().body()
}