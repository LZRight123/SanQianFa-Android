package com.fantasy.sanqianfa.view.main

import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.fantasy.components.aamedium.bottomFadingEdge
import com.fantasy.components.extension.compose.CXPaddingValues
import com.fantasy.components.extension.compose.addHazeContent
import com.fantasy.components.extension.randomString
import com.fantasy.components.theme.CXColor
import com.fantasy.components.theme.CXFont
import com.fantasy.components.widget.CXScaffold
import com.fantasy.sanqianfa.R
import com.fantasy.sanqianfa.components.SQIcon

@Composable
fun HomeView(tabBarPadding: Dp = 0.dp) {
    CXScaffold(
        topBar = { }
    ) {
        LazyColumn(
            contentPadding = CXPaddingValues(
                all = 16.dp,
                bottom = tabBarPadding
            ),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            modifier = Modifier
                .fillMaxWidth(),
        ) {
            item { HeaderSection() }
            item { QuickTestSection() }
            items(12) { CardItem() }
        }
    }
}

@Composable
private fun HeaderSection() {
    Column(
        modifier = Modifier.padding(bottom = 12.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(
            text = "三钱法",
            style = CXFont.big1b.v1
        )
        Text(
            text = "lvl1 - 今日免费 0 / 1",
            style = CXFont.f2.v1.copy(color = CXColor.f2)
        )
    }
}

@Composable
private fun QuickTestSection() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(160.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        QuickTestItem(
            color = CXColor.green,
            iconName = R.drawable.home_today,
            title = "今日运势",
            modifier = Modifier
                .weight(1f)
                .fillMaxHeight()
        )
        QuickTestItem(
            color = CXColor.main,
            iconName = R.drawable.home_money,
            title = "财运速测",
            modifier = Modifier
                .weight(1f)
                .fillMaxHeight()
        )
        QuickTestItem(
            color = CXColor.blue,
            iconName = R.drawable.home_love,
            title = "爱情走向",
            modifier = Modifier
                .weight(1f)
                .fillMaxHeight()
        )
    }
}

@Composable
private fun QuickTestItem(
    color: Color,
    @DrawableRes iconName: Int,
    title: String,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(24.dp))
            .background(color),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            SQIcon(id = iconName, size = 44)
            Text(
                text = title,
                style = CXFont.f1b.v1
            )
        }
    }
}

@Composable
private fun CardItem() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(24.dp))
            .background(CXColor.b2)
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Text(
            text = "卦相分享",
            style = CXFont.f3.v1.copy(color = CXColor.f2, letterSpacing = 3.sp),
        )

        Text(
            text = randomString(4, 24), // 这里需要实现随机中文字符串生成
            style = CXFont.big2b.v1
        )

        Text(
            text = randomString(24, 120), // 这里需要实现随机中文字符串生成
            style = CXFont.f2.v1.copy(color = CXColor.f2)
        )

        HexagramCircle()
    }
}

@Composable
private fun HexagramCircle() {
    Box(
        modifier = Modifier
            .size(120.dp)
            .clip(CircleShape)
            .background(CXColor.main),
        contentAlignment = Alignment.Center
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(4.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            repeat(6) {
                Box(
                    modifier = Modifier
                        .width(32.dp)
                        .height(3.dp)
                        .background(CXColor.white)
                )
            }
        }
    }
}

@Preview
@Composable
fun HomeViewPreview() {
    MainView().body()
}