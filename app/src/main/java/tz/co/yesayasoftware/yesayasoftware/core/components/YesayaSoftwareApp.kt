package tz.co.yesayasoftware.yesayasoftware.core.components

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import tz.co.yesayasoftware.yesayasoftware.auth.presentation.LoginScreen
import tz.co.yesayasoftware.yesayasoftware.core.presentation.navigation.Screen
import tz.co.yesayasoftware.yesayasoftware.core.presentation.navigation.YesayaSoftwareAppRouter

@Composable
fun YesayaSoftwareApp() {
    Surface (
        modifier = Modifier.fillMaxSize(),
        color = Color.White
    ) {
        Crossfade(
            targetState = YesayaSoftwareAppRouter.currentScreen,
            label = "Yesaya Software App"
        ) { currentState ->
            when(currentState.value) {
                Screen.LoginScreen -> LoginScreen()
            }
        }
    }
}