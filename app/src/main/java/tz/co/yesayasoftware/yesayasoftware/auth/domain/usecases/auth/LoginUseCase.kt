package tz.co.yesayasoftware.yesayasoftware.auth.domain.usecases.auth

import retrofit2.HttpException
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import tz.co.yesayasoftware.yesayasoftware.auth.data.repositories.login.LoginRepository
import tz.co.yesayasoftware.yesayasoftware.auth.domain.requests.LoginRequest
import tz.co.yesayasoftware.yesayasoftware.auth.domain.responses.AuthenticationResponse
import tz.co.yesayasoftware.yesayasoftware.utils.Resource
import javax.inject.Inject

class LoginUseCase
@Inject constructor(
    private val repository: LoginRepository
) {
    operator fun invoke(
        request: LoginRequest
    ): Flow<Resource<AuthenticationResponse>> = flow {
        emit(Resource.Loading())

        val response = repository.login(request)

        if (response.isSuccessful)
            emit(Resource.Success(response.body()))
        else
            when (response.code()) {
                400 -> {
                    emit(
                        Resource.Error(
                            message = response.code().toString(),
                            data = response.body(),
                            errors = response.errorBody()
                        )
                    )
                }

                422 -> {
                    emit(
                        Resource.Error(
                            message = response.code().toString(),
                            data = response.body(),
                            errors = response.errorBody()
                        )
                    )
                }
            }
    }
}