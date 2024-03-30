package tz.co.yesayasoftware.yesayasoftware.auth.presentation

data class LoginState(
    val email: String = "",
    val emailError: String? = null,
    val password: String = "",
    val passwordError: String? = null,
    val isLoading: Boolean = false,
    val success: String? = null,
    val error: String = ""
)
