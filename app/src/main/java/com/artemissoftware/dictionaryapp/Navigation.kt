package com.artemissoftware.dictionaryapp

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.artemissoftware.dictionaryapp.feature_dictionary.presentation.CachedWordsScreen
import com.artemissoftware.dictionaryapp.feature_dictionary.presentation.DictionaryScreen
import com.artemissoftware.feature_splash.presentation.SplashScreen

@Composable
fun Navigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Screen.SplashScreen.route
    ) {
        composable(Screen.SplashScreen.route) {
            SplashScreen(navController = navController)
        }
        composable(Screen.DictionaryScreen.route) {
            //DictionaryScreen()
            CachedWordsScreen()
        }


    }
}