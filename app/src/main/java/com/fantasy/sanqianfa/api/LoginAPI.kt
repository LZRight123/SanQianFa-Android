package com.fantasy.sanqianfa.api

import androidx.annotation.Keep
import com.fantasy.components.network.NetworkResponse
import com.fantasy.sanqianfa.model.TokenModel
import retrofit2.http.Body
import retrofit2.http.POST

interface LoginAPI {
    // 发送登录验证码
    @POST("api/v1/login/request_sms_code")
    suspend fun request_sms_code(@Body body: Params): Any

    // 验证码注册&自动登录用户
    @POST("api/v1/login/signup_and_login_with_mobile_phone_and_sms_code")
    suspend fun signup_and_login_with_mobile_phone_and_sms_code(@Body body: Params): NetworkResponse<TokenModel>

    @Keep
    data class Params(
        val phone_number: String,
        val sms_code: String? = null,
    )
}