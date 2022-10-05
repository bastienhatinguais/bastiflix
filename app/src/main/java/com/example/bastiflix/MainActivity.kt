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
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
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
                        NavHost(
                            navController = navController,
                            startDestination = "accueil") {
                            composable("accueil") {
                                Accueil(navController, windowSizeClass)
                            }
                            composable(BottomNavItem.Films.route) {
                                NavigationLayout(){Films(navController)};
                            }
                            composable(BottomNavItem.Series.route) {
                                NavigationLayout(){Series(navController)};
                            }
                            composable(BottomNavItem.Acteurs.route) {
                                NavigationLayout(){Acteurs(navController)};
                            }
                            composable("film/{id}"){navBackStackEntry ->
                                FilmDetail(id = navBackStackEntry.arguments?.getString("id"))
                            }
                        }                    }
                }

            }
        }
    }

    @Composable
    fun NavigationLayout(content: @Composable() () -> Unit){
        Surface(color = MaterialTheme.colors.background) {
            val navController = rememberNavController()
            Scaffold(
                bottomBar = { BottomNavigationBar(navController) }
            ) {
                content()
            }
        }
    }



