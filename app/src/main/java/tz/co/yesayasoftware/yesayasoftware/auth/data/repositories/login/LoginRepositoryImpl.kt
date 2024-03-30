package tz.co.yesayasoftware.yesayasoftware.auth.data.repositories.login

import retrofit2.Response
import tz.co.yesayasoftware.yesayasoftware.auth.data.sources.remote.ApiService
import tz.co.yesayasoftware.yesayasoftware.auth.domain.requests.LoginRequest
import tz.co.yesayasoftware.yesayasoftware.auth.domain.responses.AuthenticationResponse

class LoginRepositoryImpl(
    private val api: ApiService
): LoginRepository {
    override suspend fun login(request: LoginRequest): Response<AuthenticationResponse> {
        return api.login(
            request
        )
    }
}