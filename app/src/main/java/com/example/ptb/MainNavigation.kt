package com.example.ptb

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.ptb.data.TokenDataStore
import com.example.ptb.frontend.detailpermintaan.DetailPermintaan
import com.example.ptb.frontend.formpenguji.FormPengujiSeminarScreen
import com.example.ptb.frontend.home.HomeScreen
import com.example.ptb.frontend.listpermintaan.ListPermintaanSeminar
import com.example.ptb.frontend.login.LoginScreen
import com.example.ptb.frontend.startup.StartScreen
import com.example.ptb.frontend.startup.StartupScreen
import kotlinx.coroutines.delay

@Composable
fun MainNavigation(tokenDataStore: TokenDataStore) {
    val token by tokenDataStore.accessToken.collectAsState(initial = null)
    val navController = rememberNavController()
    var isLoading by remember { mutableStateOf(true) }
    var initialDestination by remember { mutableStateOf("landing") }

    LaunchedEffect(Unit) {
        // Simulasi delay untuk menampilkan landing screen
        delay(1000)
        initialDestination = if (token.isNullOrBlank()) "start-screen" else "home"
        isLoading = false
    }


    NavHost(
        navController = navController,
        startDestination = if (isLoading) "landing" else initialDestination
    ) {
        composable("landing") {
            StartupScreen()
        }
        composable("start-screen") {
            StartScreen(navController = navController)
        }
        composable("login") {
            LoginScreen(navController = navController)
        }
        composable("home") {
            HomeScreen(navController = navController)
        }
        composable("list-permintaan") {
            ListPermintaanSeminar(navController = navController)
        }
        composable("detail-permintaan/{id}") { backStackEntry ->
            val id = backStackEntry.arguments?.getString("id")?.toIntOrNull()
            if (id != null) {
                DetailPermintaan(id = id, navController = navController)
            } else {
                println("Error: Invalid mahasiswa ID")
            }
        }
        composable("add-seminar/{id}") { backStackEntry ->
            val id = backStackEntry.arguments?.getString("id")?.toIntOrNull()
            if (id != null) {
                FormPengujiSeminarScreen(id = id, navController = navController)
            } else {
                println("Error: Invalid tugas akhir ID")
            }
        }
    }
}