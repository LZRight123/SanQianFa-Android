package com.fantasy.sanqianfa.model

import android.os.Parcelable
import androidx.annotation.Keep
import kotlinx.parcelize.Parcelize
import java.time.LocalDateTime

@Keep
@Parcelize
data class UserInfo(
    val id: String = "",// "2bac17e6-eadd-442d-a1cf-22fb88c0daee",
    val is_deleted: Boolean = false,// false,
    val username: String = "",// "Fastapi模版App747297",
    val phone_number: String = "",// "13500000000",
    val is_superuser: Boolean = false,// false,
    val created_at: LocalDateTime = LocalDateTime.now(),// "2025-02-21T08:02:52.666420",
    val updated_at: LocalDateTime = LocalDateTime.now(),// "2025-02-21T08:02:52.666425",
    val description: String = "",// null,
    val is_active: Boolean = false,// true,
    val hashed_password: String = "",// "$2b$12$NBdkmFHCQy/e7rvI4tZQC.goPVXGqBy/ajg4LGbb1cIi5u16KQ4ke",
    val shortid: String = "",// null
) : Parcelable {

    companion object {
        val mock = UserInfo(
            id = "2bac17e6-eadd-442d-a1cf-22fb88c0daee",
            is_deleted = false,
            username = "Fastapi模版App747297",
            phone_number = "13500000000",
            is_superuser = false,
            created_at = LocalDateTime.now(),
            updated_at = LocalDateTime.now(),
            description = "",
            is_active = true,
            shortid = ""
        )
    }
}
