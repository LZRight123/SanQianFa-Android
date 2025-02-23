package com.fantasy.sanqianfa.model

import android.os.Parcelable
import androidx.annotation.Keep
import kotlinx.parcelize.Parcelize


@Keep
@Parcelize
data class TokenModel(
    val id :String = "",
    val created_at: String = "",
    val updated_at: String = "",
    val access_token: String = "",
    val token_type: String = "" // bearer
): Parcelable
