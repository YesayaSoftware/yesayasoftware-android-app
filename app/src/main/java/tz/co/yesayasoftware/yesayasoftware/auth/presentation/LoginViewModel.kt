package tz.co.yesayasoftware.yesayasoftware.auth.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import okhttp3.ResponseBody
import tz.co.yesayasoftware.yesayasoftware.auth.domain.requests.LoginRequest
import tz.co.yesayasoftware.yesayasoftware.auth.domain.responses.AuthenticationResponse
import tz.co.yesayasoftware.yesayasoftware.auth.domain.usecases.auth.LoginUseCase
import tz.co.yesayasoftware.yesayasoftware.exceptions.ValidationError
import tz.co.yesayasoftware.yesayasoftware.utils.Resource
import javax.inject.Inject
import okhttp3.MediaType


@HiltViewModel
class LoginViewModel
@Inject constructor(
    private val useCase: LoginUseCase,
    private val validationError: ValidationError
) : ViewModel() {
    private val _state = MutableStateFlow(LoginState())
    val state: StateFlow<LoginState> = _state

    fun login(
        request: LoginRequest
    ) {
        useCase(
            request
        ).onEach { response ->
            when (response) {
                is Resource.Error<AuthenticationResponse> -> {
                    val apiError = response.errors?.let { validationError.convertErrors(it) }

                    for (error in apiError?.errors?.entries!!) {
                        when (error.key) {
                            "email" -> {
                                _state.value = _state.value.copy(
                                    emailError = error.value[0]
                                )
                            }

                            "password" -> {
                                _state.value = _state.value.copy(
                                    passwordError = error.value[0]
                                )
                            }
                        }
                    }

                    _state.value = _state.value.copy(
                        error = response.message.toString()
                    )

                    _state.value = _state.value.copy(
                        isLoading = false
                    )
                }

                is Resource.Loading -> {
                    _state.value = _state.value.copy(
                        isLoading = true
                    )
                }

                is Resource.Success -> {
                    _state.value = _state.value.copy(
                        success = response.data.toString()
                    )
                }
            }

        }.launchIn(viewModelScope)
    }
}