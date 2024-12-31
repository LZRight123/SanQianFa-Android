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

@Composable
fun LearningView() {
    CXScaffold(
        topBar = {}
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .verticalScroll(rememberScrollState())
                .background(CXColor.b1)
                .padding(horizontal = 16.dp)
        ) {
            // 标题
            Text(
                text = "✨ 三钱法入门指南",
                style = CXFont.f1.v1.f1c.alignCenter,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 20.dp),
            )

            // 简介部分
            ContentSection(
                icon = "🎲",
                title = "什么是三钱法",
                content = "三钱法是一种简单而古老的占卜方法，使用三枚铜钱进行卦象演算。这种方法源自《周易》，是传统易经占卜的简化版本。"
            )

            // 原理说明
            ContentSection(
                icon = "⚡️",
                title = "基本原理",
                content = """
                    三枚铜钱同时投掷，根据正反面组合得出阴阳爻：
                    
                    🔵 三正（圆圈）→ 老阳
                    🔵 二正一反 → 少阴
                    🔵 一正二反 → 少阳
                    🔵 三反 → 老阴
                """.trimIndent()
            )

            // 操作步骤
            ContentSection(
                icon = "📝",
                title = "具体步骤",
                content = """
                    1️⃣ 准备三枚铜钱
                    2️⃣ 诚心默念求卦事项
                    3️⃣ 将铜钱置于手心摇晃
                    4️⃣ 同时投掷三枚铜钱
                    5️⃣ 记录每次结果
                    6️⃣ 重复六次完成卦象
                """.trimIndent()
            )

            // 注意事项
            ContentSection(
                icon = "⚠️",
                title = "注意事项",
                content = """
                    🌟 占卜时应保持虔诚恭敬的心态
                    🌟 每次求卦前要集中精神
                    🌟 记录结果要准确无误
                    🌟 解卦时需结合具体情况
                """.trimIndent()
            )
            
            Spacer(modifier = Modifier.height(20.dp))
        }
    }
}

@Composable
private fun ContentSection(
    icon: String,
    title: String,
    content: String,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(bottom = 16.dp)
            .background(CXColor.b2, shape = RoundedCornerShape(12.dp))
            .padding(16.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(bottom = 12.dp)
        ) {
            Text(
                text = icon,
                style = CXFont.f2.v1.f1c,
                modifier = Modifier.padding(end = 8.dp)
            )
            Text(
                text = title,
                style = CXFont.f2.v2.f1c,
            )
        }
        
        Text(
            text = content,
            style = CXFont.f3.v2.f1c,
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Preview
@Composable
fun LearningViewPreview() {
    LearningView()
}