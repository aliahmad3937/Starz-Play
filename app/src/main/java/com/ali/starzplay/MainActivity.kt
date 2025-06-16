package com.ali.starzplay

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.ali.starzplay.ui.navigation.AppNavHost
import com.ali.starzplay.ui.theme.StarzPlayTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            StarzPlayTheme {
                AppNavHost()
            }
        }
    }
}