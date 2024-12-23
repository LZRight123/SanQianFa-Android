package com.fantasy.sanqianfa.model

import androidx.annotation.DrawableRes
import com.fantasy.sanqianfa.R


enum class TabBarType {
    home, things, add, learn, profile,
    ;
    val title get() = when (this) {
        home -> "起卦"
        things -> "历史"
        add -> ""
        learn -> "原理"
        profile -> "我的"
    }

    @get:DrawableRes
    val icon: Int get() = when (this) {
        home -> R.drawable.tabbar_home
        things -> R.drawable.tabbar_history
        add -> R.drawable.add
        learn -> R.drawable.tabbar_learn
        profile -> R.drawable.tabbar_profile
    }
}
