package com.fantasy.sanqianfa.view.login


import androidx.annotation.DrawableRes
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.togetherWith
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.fantasy.components.base.BaseScreen
import com.fantasy.components.base.BaseViewModel
import com.fantasy.components.base.RequestState
import com.fantasy.components.extension.randomString
import com.fantasy.components.tools.cxlog
import com.fantasy.components.widget.CXScaffold
import com.fantasy.sanqianfa.R
import com.fantasy.sanqianfa.api.LoginAPI
import com.fantasy.sanqianfa.api.networking.Networking
import com.fantasy.sanqianfa.manager.LocalUserManager
import com.fantasy.sanqianfa.manager.userManager
import com.fantasy.sanqianfa.routeToMain
import kotlinx.coroutines.launch

enum class LoginStep {
    wellcome0,
    wellcome1,
    wellcome2,
    phoneNumber,
    smsCode;

    val wellComeText: String
        get() = when (this) {
            wellcome0 -> randomString(32)
            wellcome1 -> randomString(32)
            wellcome2 -> randomString(32)
            phoneNumber -> "请输入手机号"
            smsCode -> "请输入验证码"
        }

    @get:DrawableRes
    val image: Int
        get() = when (this) {
            wellcome0 -> R.drawable.wellcome_0
            wellcome1 -> R.drawable.wellcome_1
            wellcome2 -> R.drawable.wellcome_2
            phoneNumber -> R.drawable.wellcome_2
            smsCode -> R.drawable.wellcome_2
        }
}

class LoginViewModel : BaseViewModel() {
    var loginStep by mutableStateOf(LoginStep.wellcome0)

    var phoneNumberInput by mutableStateOf("15207118888")
    var smsCodeInput by mutableStateOf("9999")
    fun nextStep() {
        when (loginStep) {
            LoginStep.wellcome0 -> loginStep = LoginStep.wellcome1
            LoginStep.wellcome1 -> loginStep = LoginStep.wellcome2
            LoginStep.wellcome2 -> loginStep = LoginStep.phoneNumber
            LoginStep.phoneNumber -> getSMSCode()
            LoginStep.smsCode -> login()
        }
    }

    // 发送验证码
    fun getSMSCode() {
        // 成功了
        viewModelScope.launch {
            requestState = RequestState.loading
            val res = Networking.create<LoginAPI>().request_sms_code(
                LoginAPI.Params(phone_number = phoneNumberInput)
            )
            requestState = RequestState.ok
            loginStep = LoginStep.smsCode
        }
    }

    // 登录
    fun login() {
        viewModelScope.launch {
            requestState = RequestState.loading
            val res = Networking.create<LoginAPI>().signup_and_login_with_mobile_phone_and_sms_code(
                LoginAPI.Params(phone_number = phoneNumberInput, sms_code = smsCodeInput)
            )
            requestState = RequestState.ok
            res.data?.let {
                userManager.loginSuccess(it)
                // 获取用户资料
                userManager.fetchRemoteAndRefreshUser()
                routeToMain()
            }
        }
    }
}

class LoginView : BaseScreen() {
    @Composable
    override fun body() {
        val vm = viewModel<LoginViewModel>()

        CXScaffold(topBar = {}) {
            AnimatedContent(
                targetState = vm.loginStep,
                label = "",
                transitionSpec = {
                    slideInHorizontally { it } togetherWith slideOutHorizontally { -it }
                }
            ) { step ->
                when (step) {
                    LoginStep.wellcome0,
                    LoginStep.wellcome1,
                    LoginStep.wellcome2 -> WellComeView()
                    LoginStep.phoneNumber -> PhoneNumberInputView()
                    LoginStep.smsCode -> SMSCodeInputView()
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun Preview() {
    LoginView().Content()
}