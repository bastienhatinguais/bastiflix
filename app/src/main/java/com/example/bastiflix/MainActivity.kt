package com.example.bastiflix

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.arch.core.util.Function
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import com.example.bastiflix.ui.theme.MapremiereapplicationTheme
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.bastiflix.pages.*

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val viewModel: MainViewModel by viewModels()

        setContent {
            MapremiereapplicationTheme {
                val windowSizeClass = calculateWindowSizeClass(this)
                val navController = rememberNavController()
                Surface(color = MaterialTheme.colors.background) {
                    val navController = rememberNavController()
                    Scaffold(
                        bottomBar = { BottomNavigationBar(navController) }
                    ) {
                        NavHost(
                            navController = navController,
                            startDestination = "accueil") {
                            composable("accueil") {
                                Accueil(navController, windowSizeClass)
                            }
                            composable(BottomNavItem.Films.route) {
                                    Films(navController);

                            }
                            composable(BottomNavItem.Series.route) {
                                    Series(navController);

                            }
                            composable(BottomNavItem.Acteurs.route) {
                                    Acteurs(navController);

                            }
                        }                    }
                }

            }
        }
    }

    @Composable
    fun BootstrapBottomBar(content: @Composable() () -> Unit){
        Surface(color = MaterialTheme.colors.background) {
            val navController = rememberNavController()
            Scaffold(
                bottomBar = { BottomNavigationBar(navController) }
            ) {
                content()
            }
        }
    }
}
