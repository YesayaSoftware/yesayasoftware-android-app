package tz.co.yesayasoftware.yesayasoftware.auth.data.sources.remote

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST
import tz.co.yesayasoftware.yesayasoftware.auth.domain.requests.LoginRequest
import tz.co.yesayasoftware.yesayasoftware.auth.domain.responses.AuthenticationResponse

interface ApiService {
    @Headers("Content-Type: application/json")
    @POST("login")
    suspend fun login(
        @Body request: LoginRequest
    ): Response<AuthenticationResponse>
}