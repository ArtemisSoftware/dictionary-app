package com.artemissoftware.feature_splash.presentation

import android.view.animation.OvershootInterpolator
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import com.artemissoftware.dictionaryapp.Greeting
import com.artemissoftware.dictionaryapp.R
import com.artemissoftware.dictionaryapp.Screen
import com.artemissoftware.dictionaryapp.ui.theme.DIctionaryAppTheme
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(
    navController:  NavHostController
){

    val scale = remember {
        Animatable(initialValue = 0f)
    }

    LaunchedEffect(key1 = true){
        scale.animateTo(targetValue = 1.2f,
        animationSpec = tween(durationMillis = 2999, easing = {
            OvershootInterpolator(2f).getInterpolation(it)
        } ))

        delay(3L)


        navController.navigate(route = Screen.DictionaryScreen.route) {
            popUpTo(Screen.SplashScreen.route) { inclusive = true } //remove from the back stack
        }
    }



    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ){
        
        Image(
            painter = painterResource(id = R.drawable.ic_dictionary),
            contentDescription = "Logo",
            modifier = Modifier.scale(scale.value)
        )
        
    }
    
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    //SplashScreen()
}