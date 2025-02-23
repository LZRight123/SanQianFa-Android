package com.fantasy.sanqianfa.view.main

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.fantasy.components.base.BaseScreen
import com.fantasy.components.theme.CXColor
import com.fantasy.components.theme.CXFont
import com.fantasy.components.widget.CXScaffold
import com.fantasy.sanqianfa.manager.userManager
import java.time.format.DateTimeFormatter

class UserInfoView: BaseScreen() {
    @Composable
    override fun body() {
        val userInfo = userManager.userInfo
        
        CXScaffold(
            title = "个人中心",
        ) { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                // 头像和用户名区域
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(CXColor.b2, RoundedCornerShape(12.dp))
                        .padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .size(64.dp)
                            .clip(CircleShape)
                            .background(CXColor.b3),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = Icons.Default.Person,
                            contentDescription = null,
                            tint = CXColor.f2,
                            modifier = Modifier.size(32.dp)
                        )
                    }
                    Column {
                        Text(
                            text = userInfo.username,
                            style = CXFont.big3b.v1,
                            color = CXColor.f1
                        )
                        Text(
                            text = userInfo.phone_number,
                            style = CXFont.f2.v1,
                            color = CXColor.f2
                        )
                    }
                }

                // 用户信息列表
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(CXColor.b2, RoundedCornerShape(12.dp))
                        .padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    InfoItem("用户 ID", userInfo.shortid.ifEmpty { userInfo.id })
                    InfoItem("账号状态", if (userInfo.is_active) "已激活" else "未激活")
                    InfoItem("用户类型", if (userInfo.is_superuser) "超级管理员" else "普通用户")
                    InfoItem("注册时间", userInfo.created_at.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")))
                    if (userInfo.description.isNotEmpty()) {
                        InfoItem("个人简介", userInfo.description)
                    }
                }
            }
        }
    }

    @Composable
    private fun InfoItem(label: String, value: String) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = label,
                style = CXFont.f2.v1,
                color = CXColor.f2
            )
            Text(
                text = value,
                style = CXFont.f2.v1,
                color = CXColor.f1
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun Preview() {
    UserInfoView().body()
}