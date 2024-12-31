package com.fantasy.sanqianfa.model

import java.util.UUID

data class ProfileSettingGroup(
    val id: String = UUID.randomUUID().toString(),
    val title: String? = null,
    val items: List<ProfileSettingItem>
)

data class ProfileSettingItem(
    val id: String = UUID.randomUUID().toString(),
    val title: String,
    val rightText: String? = null,
    val icon: String? = null,
    val showArrow: Boolean = false,
    val hasToggle: Boolean = false,
    var isToggleOn: Boolean = false,
    val action: (() -> Unit)? = null
)

