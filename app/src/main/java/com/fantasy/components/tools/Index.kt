package com.fantasy.components.tools

import android.app.Activity
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.res.Resources
import android.os.Build
import android.os.VibrationEffect
import android.os.Vibrator
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.statusBars
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.blankj.utilcode.util.ActivityUtils
import com.blankj.utilcode.util.KeyboardUtils
import com.fantasy.sanqianfa.BuildConfig
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.koin.core.component.KoinComponent
import org.koin.core.component.get
import java.text.DecimalFormat


val isDebugBuilder get() = BuildConfig.DEBUG

@Deprecated("只有preview的时候用")
var inPreview: Boolean = false

const val appName = "模版项目"

val screenWith: Int
    @Composable
    get() = LocalConfiguration.current.screenWidthDp

val screenHeight: Int
    @Composable
    get() = LocalConfiguration.current.screenHeightDp

val canBlur: Boolean = Build.VERSION.SDK_INT >= Build.VERSION_CODES.S
/**
 * 状态栏的高度
 */
val safeAreaTop: Dp
    @Composable
    get() = WindowInsets.statusBars.getTop(LocalDensity.current).toDp()

/**
 * 手机物理按键的高度, 有的手机没有则为0
 */
val safeAreaBottom: Dp
    @Composable
    get() = WindowInsets.navigationBars.getBottom(LocalDensity.current).toDp()

val tabBarHeight = 48.dp
val navBarHeight = 51.dp
val dropdownMenuDefaultWidth = 212.dp
val bottomSpacer: Dp
    @Composable
    get() = safeAreaBottom + tabBarHeight + 56.dp

suspend fun localDelay(millis: Long) {
    withContext(Dispatchers.IO) {
        delay(timeMillis = millis)
    }
}

//@Composable
//fun Int.toDp(): Dp = with(LocalDensity.current) { toDp() }
@Composable
fun Int.toPx(): Float = with(LocalDensity.current) { dp.toPx()}
//@Composable
//fun Float.toDp(): Dp = with(LocalDensity.current) { toDp() }
@Composable
fun Float.toPx(): Float = with(LocalDensity.current) { dp.toPx()}
fun Int.toDp(): Dp = (this / Resources.getSystem().displayMetrics.density).dp

/**
 * 毫秒级时间戳
 */
val mockTimestamp: Long
    get() = listOf(
        1679912382000,
        1680492066000,
        1641180066000,
        1669177266000,
    ).random()

/**
 * 格式化 数字
 */
fun Double.numberFormat(pattern: String = "0.#"): String {
    val formatter = DecimalFormat(pattern)
    return formatter.format(this)
}

fun Float.numberFormat(pattern: String = "0.#") = toDouble().numberFormat(pattern)

val getContext get() = ContextTool.getContext()
private object ContextTool: KoinComponent {
    fun getContext() = get<Context>()
}

val currentActivity: Activity?
    @Composable
    get() = LocalContext.current as? Activity

fun hideKeyboard() = KeyboardUtils.hideSoftInput(ActivityUtils.getTopActivity())
/**
 * 	VibrationEffect.EFFECT_CLICK: 点击效果的震动模式。
 * 	VibrationEffect.EFFECT_DOUBLE_CLICK: 双击效果的震动模式。
 * 	VibrationEffect.EFFECT_TICK: 轻微的滴答效果的震动模式。
 * 	VibrationEffect.EFFECT_HEAVY_CLICK: 重型点击效果的振幅值，用于模拟更强烈的点击或触摸反馈。
 */
val vibrator get() = getContext.getSystemService(Vibrator::class.java) as Vibrator
fun mada(effectId: Int = VibrationEffect.EFFECT_CLICK) {
    MainScope().launch {
        vibrator.vibrate(VibrationEffect.createPredefined(effectId))
    }
}

fun copyToClipboard(content: String, showToast: Boolean = false) {
    val clipboard =
        getContext.getSystemService(Context.CLIPBOARD_SERVICE) as? ClipboardManager
    clipboard?.setPrimaryClip(ClipData.newPlainText("label", content))
    if (showToast) {
        Apphelper.toast("复制成功")
    }
}