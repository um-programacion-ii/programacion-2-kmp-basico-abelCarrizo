package org.basic.project

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.navigator.currentOrThrow

@Composable
fun App() {
    MaterialTheme {
        Navigator(
            screen = MainScreen()
        )
    }
}

class MainScreen: Screen {

    @Composable
    override fun Content() {

        val navigator: Navigator = LocalNavigator.currentOrThrow

        var name: String by remember { mutableStateOf(value = "") }
        var lastname: String by remember { mutableStateOf("") }

        Column (
            modifier = Modifier
                .background(color = MaterialTheme.colorScheme.primaryContainer)
                .safeContentPadding()
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            TextField(
                value = name,
                onValueChange = {
                    name = it
                }
            )

            Spacer(modifier = Modifier.height(40.dp))

            AnimatedVisibility(name.isNotEmpty()) {
                Text(
                    text = "Version animada $name",
                    fontSize = 24.sp
                )
            }

            Spacer(
                modifier = Modifier.height(40.dp)
            )

            TextField(
                value = lastname,
                onValueChange = {
                    lastname = it
                }
            )

            AnimatedVisibility(lastname.isNotEmpty()) {
                Text(
                    text = "Vesión animada 2: $lastname",
                    fontSize = 24.sp
                )
            }

            Spacer(Modifier.height(30.dp))

            Button(
                onClick = {
                    navigator.push(SecondScreen())
                }
            ) {
                Text(
                    text = "Navegar 2.Pantalla"
                )
            }

            Spacer(Modifier.height(20.dp))

            // Nuevo botón hacia la tercera pantalla
            Button(
                onClick = {
                    navigator.push(ThirdScreen())
                }
            ) {
                Text(
                    text = "Navegar 3.Pantalla"
                )
            }
        }
    }
}

class SecondScreen: Screen {

    @Composable
    override fun Content() {

        val navigator: Navigator = LocalNavigator.currentOrThrow

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Yellow),
            verticalArrangement = Arrangement.SpaceAround,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Segunda pantalla",
                fontSize = 20.sp,
                color = Color.Black
            )

            Spacer(
                modifier = Modifier
                    .height(20.dp)
            )

            Button(
                onClick = {
                    navigator.pop()
                }
            ) {
                Text(
                    text = "Volver 1.Pantalla"
                )
            }
        }
    }
}

class ThirdScreen : Screen {

    @Composable
    override fun Content() {

        val navigator: Navigator = LocalNavigator.currentOrThrow

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Cyan),
            verticalArrangement = Arrangement.SpaceAround,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Tercera pantalla",
                fontSize = 20.sp,
                color = Color.Black
            )

            Spacer(
                modifier = Modifier
                    .height(20.dp)
            )

            Button(
                onClick = {
                    // Al hacer pop hasta vaciar el stack, volvemos a la MainScreen
                    navigator.popUntilRoot()
                }
            ) {
                Text(
                    text = "Volver 1.Pantalla"
                )
            }
        }
    }
}