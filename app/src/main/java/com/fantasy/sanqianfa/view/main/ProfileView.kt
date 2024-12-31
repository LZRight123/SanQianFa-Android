package com.fantasy.sanqianfa.view.main

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchColors
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.fantasy.components.base.BaseViewModel
import com.fantasy.components.extension.boldBlack
import com.fantasy.components.extension.compose.CXPaddingValues
import com.fantasy.components.theme.CXColor
import com.fantasy.components.theme.CXFont
import com.fantasy.components.widget.CXScaffold
import com.fantasy.sanqianfa.R
import com.fantasy.sanqianfa.components.SQIcon
import com.fantasy.sanqianfa.model.ProfileSettingGroup
import com.fantasy.sanqianfa.model.ProfileSettingItem
import com.fantasy.sanqianfa.model.TabBarType

class ProfileViewModel : BaseViewModel() {
    var settingGroups by mutableStateOf(emptyList<ProfileSettingGroup>())
        private set

    init {
        setupSettingGroups()
    }

    private fun setupSettingGroups() {
        settingGroups = listOf(
            // 用户信息组
            ProfileSettingGroup(
                title = "",
                items = listOf(
                    ProfileSettingItem(
                        title = "个人资料",
                        rightText = null,
                        icon = "person.circle",
                        showArrow = true,
                        hasToggle = false,
                        action = { println("个人资料") }
                    ),
                    ProfileSettingItem(
                        title = "订阅会员",
                        rightText = "未订阅",
                        icon = "crown",
                        showArrow = true,
                        hasToggle = false,
                        action = { println("订阅会员") }
                    )
                )
            ),

            // 功能设置组
            ProfileSettingGroup(
                title = "功能设置",
                items = listOf(
                    ProfileSettingItem(
                        title = "离线下载",
                        rightText = null,
                        icon = "arrow.down.circle",
                        showArrow = false,
                        hasToggle = true,
                        isToggleOn = true
                    ),
                    ProfileSettingItem(
                        title = "自动播放",
                        rightText = null,
                        icon = "play.circle",
                        showArrow = false,
                        hasToggle = true,
                        isToggleOn = false
                    ),
                    ProfileSettingItem(
                        title = "清除缓存",
                        rightText = "23.5MB",
                        icon = "trash",
                        showArrow = true,
                        hasToggle = false
                    )
                )
            ),

            // 通知设置组
            ProfileSettingGroup(
                title = "通知设置",
                items = listOf(
                    ProfileSettingItem(
                        title = "推送通知",
                        rightText = null,
                        icon = "bell",
                        showArrow = false,
                        hasToggle = true,
                        isToggleOn = false
                    ),
                    ProfileSettingItem(
                        title = "学习提醒",
                        rightText = null,
                        icon = "clock",
                        showArrow = false,
                        hasToggle = true,
                        isToggleOn = true
                    ),
                    ProfileSettingItem(
                        title = "每日一卦",
                        rightText = null,
                        icon = "sun.max",
                        showArrow = false,
                        hasToggle = true,
                        isToggleOn = true
                    )
                )
            ),

            // 隐私安全组
            ProfileSettingGroup(
                title = "隐私安全",
                items = listOf(
                    ProfileSettingItem(
                        title = "指纹解锁",
                        rightText = null,
                        icon = "touchid",
                        showArrow = false,
                        hasToggle = true,
                        isToggleOn = false
                    ),
                    ProfileSettingItem(
                        title = "隐私设置",
                        rightText = null,
                        icon = "lock",
                        showArrow = true,
                        hasToggle = false
                    ),
                    ProfileSettingItem(
                        title = "数据备份",
                        rightText = "从不",
                        icon = "icloud",
                        showArrow = true,
                        hasToggle = false
                    )
                )
            ),

            // 其他设置组
            ProfileSettingGroup(
                title = "其他",
                items = listOf(
                    ProfileSettingItem(
                        title = "关于我们",
                        rightText = null,
                        icon = "info.circle",
                        showArrow = true,
                        hasToggle = false
                    ),
                    ProfileSettingItem(
                        title = "隐私政策",
                        rightText = null,
                        icon = "doc.text",
                        showArrow = true,
                        hasToggle = false
                    ),
                    ProfileSettingItem(
                        title = "用户协议",
                        rightText = null,
                        icon = "doc.plaintext",
                        showArrow = true,
                        hasToggle = false
                    ),
                    ProfileSettingItem(
                        title = "评分鼓励",
                        rightText = null,
                        icon = "star",
                        showArrow = true,
                        hasToggle = false
                    ),
                    ProfileSettingItem(
                        title = "分享给好友",
                        rightText = null,
                        icon = "square.and.arrow.up",
                        showArrow = true,
                        hasToggle = false
                    )
                )
            )
        )
    }
}

@Composable
fun ProfileView(
    viewModel: ProfileViewModel = viewModel()
) {
    CXScaffold(
        topBar = {
            Box(modifier = Modifier.statusBarsPadding().height(20.dp))
        }
    ) { innerPadding ->
        LazyColumn(
            contentPadding = CXPaddingValues(innerPadding = innerPadding),
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            item {
                Text(
                    text = "我的",
                    style = CXFont.big2.v1.boldBlack,
                    modifier = Modifier.padding(bottom = 12.dp)
                )
            }

            items(viewModel.settingGroups) { group ->
                SettingGroup(group = group)
            }

            item {
                Spacer(modifier = Modifier.height(120.dp))
            }
        }
    }
}

@Composable
private fun SettingGroup(group: ProfileSettingGroup) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(5.dp)
    ) {
        group.title?.let { title ->
            Text(
                text = title,
                style = CXFont.f3.v1.copy(color = CXColor.f2, letterSpacing = 3.sp),
            )
        }

        group.items.forEach { item ->
            SettingRow(item = item)
        }
    }
}

@Composable
private fun SettingRow(item: ProfileSettingItem) {
    var isToggleOn by remember { mutableStateOf(item.isToggleOn) }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(18.dp))
            .background(CXColor.b2)
            .clickable { item.action?.invoke() }
            .padding(horizontal = 16.dp, vertical = 16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = item.title,
            style = CXFont.f1.v1.copy(color = CXColor.f1)
        )

        Row(
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            item.rightText?.let { text ->
                Text(
                    text = text,
                    style = CXFont.f1.v1.copy(color = CXColor.f2)
                )
            }

            if (item.hasToggle) {
                Switch(
                    checked = isToggleOn,
                    onCheckedChange = { isToggleOn = it },
                    colors = SwitchDefaults.colors(
                        checkedIconColor = CXColor.random,
                        checkedTrackColor = CXColor.green,
                    )
                )
            }

            if (item.showArrow) {
                SQIcon(id = R.drawable.chevron_right)
            }
        }
    }
}

@Preview
@Composable
private fun Preview() {
    val vm: MainViewModel = viewModel()
    vm.currentTabBar = TabBarType.profile
    MainView().body()
}