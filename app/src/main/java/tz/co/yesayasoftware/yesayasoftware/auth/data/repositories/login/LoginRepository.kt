package tz.co.yesayasoftware.yesayasoftware.auth.data.repositories.login

import retrofit2.Call
import retrofit2.Response
import tz.co.yesayasoftware.yesayasoftware.auth.domain.requests.LoginRequest
import tz.co.yesayasoftware.yesayasoftware.auth.domain.responses.AuthenticationResponse

interface LoginRepository {
    suspend fun login(
        request: LoginRequest
    ): Response<AuthenticationResponse>
}