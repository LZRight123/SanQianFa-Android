package com.fantasy.sanqianfa.api

import com.fantasy.components.network.NetworkResponse
import com.fantasy.sanqianfa.model.UserInfo
import retrofit2.http.GET

interface UserAPI {
    // 获取用户详细信息
    @GET("api/v1/user/profile")
    suspend fun getUserProfile(): NetworkResponse<UserInfo>
}