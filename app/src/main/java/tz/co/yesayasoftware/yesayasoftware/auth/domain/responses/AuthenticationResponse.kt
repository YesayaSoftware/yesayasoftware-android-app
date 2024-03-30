package tz.co.yesayasoftware.yesayasoftware.auth.domain.responses

data class AuthenticationResponse(
    val id: Int,
    val name: String,
    val email: String,
    val token: String,
    val refreshToken: String
)
