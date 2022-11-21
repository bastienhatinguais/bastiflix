package com.example.bastiflix

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.arch.core.util.Function
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import com.example.bastiflix.ui.theme.MapremiereapplicationTheme
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.bastiflix.component.TopBar
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
                Surface(color = MaterialTheme.colors.background, modifier=Modifier.fillMaxSize()) {
                    NavHost(
                        navController = navController,
                        startDestination = "accueil"
                    ) {
                        composable("accueil") {
                            Accueil(navController, windowSizeClass)
                        }
                        composable(BottomNavItem.Movies.route) {
                            NavigationLayout(navController) { Movies(navController) };
                        }
                        composable(BottomNavItem.Series.route) {
                            NavigationLayout(navController) { Series(navController) };
                        }
                        composable(BottomNavItem.Peoples.route) {
                            NavigationLayout(navController) { Peoples(navController) };
                        }
                        composable("movie/{id}") { navBackStackEntry ->
                            MovieDetail(id = navBackStackEntry.arguments?.getString("id"), navController)
                        }
                        composable("serie/{id}") { navBackStackEntry ->
                            SerieDetail(id = navBackStackEntry.arguments?.getString("id"), navController)
                        }
                        composable("people/{id}") { navBackStackEntry ->
                            PeopleDetail(id = navBackStackEntry.arguments?.getString("id"), navController)
                        }
                    }
                }
            }

        }
    }
}

@Composable
fun NavigationLayout(navController: NavHostController, content: @Composable() () -> Unit) {
        Scaffold(
            bottomBar = { BottomNavigationBar(navController) },
            topBar = { TopBar(navController) },
        ) {
            Box(modifier = Modifier.fillMaxSize()
                .padding(10.dp)
                ) {
                content();
            }
    }
}



