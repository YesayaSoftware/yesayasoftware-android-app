package tz.co.yesayasoftware.yesayasoftware.core.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import dagger.hilt.android.AndroidEntryPoint
import tz.co.yesayasoftware.yesayasoftware.core.components.YesayaSoftwareApp
import tz.co.yesayasoftware.yesayasoftware.ui.theme.YesayaSoftwareTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            YesayaSoftwareTheme {
                YesayaSoftwareApp()
            }
        }
    }
}

@Preview
@Composable
fun PreviewSignUpScreen() {
    YesayaSoftwareApp()
}