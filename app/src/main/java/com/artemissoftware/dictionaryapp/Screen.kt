package com.artemissoftware.dictionaryapp

sealed class Screen(val route: String) {
    object SplashScreen : Screen("splash_screen")
    object DictionaryScreen : Screen("dictionary_screen")
    object CachedWordsScreen : Screen("cached_words_screen")
}