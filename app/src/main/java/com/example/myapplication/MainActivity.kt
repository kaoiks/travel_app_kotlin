package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Switch
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.example.myapplication.pages.authentication.LoginScreen
import com.example.myapplication.pages.authentication.RegisterScreen
import com.example.myapplication.pages.main.MainLayout
import com.example.myapplication.ui.theme.MyApplicationTheme
import com.example.myapplication.client.ApiClient
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapProperties
import com.google.maps.android.compose.MapType
import com.google.maps.android.compose.MapUiSettings
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MyApplicationTheme {
                MyApp()
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MyApp() {


    var loggedIn by remember { mutableStateOf(false) }
    var refreshToken by remember { mutableStateOf("") }
    var accessToken by remember { mutableStateOf("") }
    val context = LocalContext.current
    var tickets by remember { mutableStateOf(emptyList<ApiClient.Ticket>()) }
    var loading by remember { mutableStateOf(true) }


    LaunchedEffect(Unit) {
        withContext(Dispatchers.IO) {
            refreshToken = TokenManager.getRefreshToken(context) ?: ""
        }
    }
    LaunchedEffect(Unit) {
        withContext(Dispatchers.IO) {
            accessToken = TokenManager.getAccessToken(context) ?: ""
        }
    }

    if (refreshToken.isNotBlank()) {
        loggedIn = true;
    }
    if (loggedIn) {
//        accessToken = TokenManager.getAccessToken(context) ?: ""
//        ApiClient.setClientsJwt(accessToken)
////        Text(text = "Welcome to the app!")
//        LaunchedEffect(Unit) {
//            withContext(Dispatchers.IO) {
//                val fetchedTickets = ApiClient.getTickets(accessToken)
//                tickets = fetchedTickets
//                loading = false
//            }
//        }
//        if (loading) {
//            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
//                CircularProgressIndicator()
//            }
//        } else {
//            TicketList(tickets)
//        }
        MyApplicationTheme {
            MainLayout(onLogOut = { loggedIn = false })
        }
    } else {


        var currentScreen by remember { mutableStateOf(Screen.Register) }

        // Switch to the LoginScreen
        val switchToLogin = {
            currentScreen = Screen.Login
        }
        val switchToRegister = {
            currentScreen = Screen.Register
        }

        when (currentScreen) {
            Screen.Register -> {
                MyApplicationTheme {
                    RegisterScreen(
                        onLoggedIn = { loggedIn = true },
                        onSwitchToLogin = switchToLogin
                    )
                }
            }

            Screen.Login -> {
                MyApplicationTheme {
                    LoginScreen(
                        onLoggedIn = { loggedIn = true },
                        onSwitchToRegister = switchToRegister
                    )
                }
            }
        }
    }

}

enum class Screen {
    Register, Login
}