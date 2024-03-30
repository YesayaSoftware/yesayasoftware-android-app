package tz.co.yesayasoftware.yesayasoftware.auth.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import tz.co.yesayasoftware.yesayasoftware.auth.domain.requests.LoginRequest
import tz.co.yesayasoftware.yesayasoftware.utils.Resource

@Composable
fun LoginScreen() {
    val viewModel = viewModel<LoginViewModel>()
    val state by viewModel.state.collectAsState()

    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .padding(15.dp),
        color = Color.White
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.Start
        ) {
            TextField(
                modifier = Modifier.fillMaxWidth(),
                value = state.email,
                onValueChange = { /* Update value */ },
                label = { Text("Email") },
                isError = !state.emailError.isNullOrEmpty()
            )

            if (!state.emailError.isNullOrEmpty())
                Text(
                    text = state.emailError.toString(),
                    color = MaterialTheme.colorScheme.error
                )

            Spacer(modifier = Modifier.height(16.dp))

            TextField(
                modifier = Modifier.fillMaxWidth(),
                value = state.password,
                onValueChange = { /* Update value */ },
                label = { Text("Password") },
                visualTransformation = PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                isError = !state.passwordError.isNullOrEmpty()
            )

            if (!state.passwordError.isNullOrEmpty())
                Text(
                    text = state.passwordError.toString(),
                    color = MaterialTheme.colorScheme.error
                )

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = {
                    viewModel.login(
                        LoginRequest(state.email, state.password)
                    )
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Validate")
            }

            if (state.isLoading)
                CircularProgressIndicator(modifier = Modifier.padding(16.dp))

            if (!state.success.isNullOrBlank())
                Text(text = "Validation Successful: ${state.success}")
        }
    }
}


@Preview(showSystemUi = true)
@Composable
fun LoginScreenPreview() {
    LoginScreen()
}