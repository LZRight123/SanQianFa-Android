package com.fantasy.sanqianfa.view.main

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.fantasy.components.extension.alignCenter
import com.fantasy.components.extension.f1c
import com.fantasy.components.theme.CXColor
import com.fantasy.components.theme.CXFont
import com.fantasy.components.widget.CXScaffold
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.lazy.LazyColumn
import com.fantasy.components.extension.f2c

@Composable
fun LearningView() {
    CXScaffold(
        topBar = {}
    ) {
        LazyColumn(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp)
        ) {
            item {
                Text(
                    text = "三钱法",
                    style = CXFont.f3.v1.f2c.copy(letterSpacing = 3.sp),
                    modifier = Modifier
                        .padding(vertical = 16.dp)
                )
            }

            // Content sections
            item {
                ContentSection(
                    title = "什么是三钱法",
                    content = "三钱法是易经预测中的一种简便占卦方法，使用三枚铜钱来演算卦象。这种方法简单易学，是初学者入门易经预测的理想选择。"
                )
            }

            item {
                Spacer(modifier = Modifier.height(24.dp))
            }

            item {
                ContentSection(
                    title = "基本原理",
                    content = "• 准备工具：三枚相同的铜钱\n• 正面（阳）：数字面\n• 反面（阴）：文字面"
                )
            }

            item {
                Spacer(modifier = Modifier.height(24.dp))
            }

            item {
                ContentSection(
                    title = "爻的形成",
                    content = "• 三枚正面（3阳）：老阳 ○ (9)\n• 二正一反（2阳1阴）：少阳 ⚊ (7)\n• 一正二反（1阳2阴）：少阴 ⚋ (8)\n• 三枚反面（3阴）：老阴 × (6)"
                )
            }

            item {
                Spacer(modifier = Modifier.height(24.dp))
            }

            item {
                ContentSection(
                    title = "实践步骤",
                    content = "1. 净心：保持心静、专注\n2. 持钱：将三枚铜钱捧在手心\n3. 摇钱：默想问题，同时摇动铜钱\n4. 投掷：将钱币抛出\n5. 记录：观察钱币正反面，记录结果\n6. 重复：共进行六次，从下往上记录爻"
                )
            }

            item {
                Spacer(modifier = Modifier.height(24.dp))
            }

            item {
                ContentSection(
                    title = "注意事项",
                    content = "• 占卦时应保持虔诚、专注的心态\n• 问题要明确具体\n• 同一个问题短期内不要重复占卦\n• 记录时注意爻的顺序是从下往上"
                )
            }

            item {
                Spacer(modifier = Modifier.height(120.dp))
            }
        }
    }
}

@Composable
private fun ContentSection(
    title: String,
    content: String
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                color = CXColor.b2,
                shape = RoundedCornerShape(12.dp)
            )
            .padding(24.dp)
    ) {
        Text(
            text = title,
            style = CXFont.big3b.v1,
            color = CXColor.f1
        )
        
        Spacer(modifier = Modifier.height(12.dp))
        
        Text(
            text = content,
            style = CXFont.f2.v1,
            color = CXColor.f2,
        )
    }
}

@Preview
@Composable
fun LearningViewPreview() {
    LearningView()
}