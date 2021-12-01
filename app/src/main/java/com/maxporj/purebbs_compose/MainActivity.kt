package com.maxporj.purebbs_compose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.maxporj.purebbs_compose.route.GetNavHost
import com.maxporj.purebbs_compose.ui.theme.PurebbscomposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            App()
        }
    }
}

@Composable
fun App() {
    PurebbscomposeTheme {
        val navController = rememberNavController()
        val backstackEntry = navController.currentBackStackEntryAsState()

        val result = remember { mutableStateOf("") }
        val expanded = remember { mutableStateOf(false)}
        val liked = remember { mutableStateOf(true) }

        Scaffold(
            topBar = {
                TopAppBar(
                    title = {
                        Text(text = "app bar")
                    },
                    navigationIcon = {
                        IconButton(onClick = { /* doSomething() */ }) {
                            Icon(Icons.Filled.Menu, contentDescription = null)
                        }
                    },
                    actions = {
                        // RowScope here, so these icons will be placed horizontally
                        IconButton(onClick = { /* doSomething() */ }) {
                            Icon(Icons.Filled.Create, contentDescription = "Localized description")
                        }
                        IconButton(onClick = { /* doSomething() */ }) {
                            Icon(Icons.Filled.Favorite, contentDescription = "Localized description")
                        }
                        IconButton(onClick = { /* doSomething() */ }) {
                            Icon(Icons.Filled.Delete, contentDescription = "Localized description")
                        }
                        IconButton(onClick = { /* doSomething() */ }) {
                            Icon(Icons.Filled.Settings, contentDescription = "Localized description")
                        }
                        IconButton(onClick = { /* doSomething() */ }) {
                            Icon(Icons.Filled.Share, contentDescription = "Localized description")
                        }
                    }
                )
            }
        ) {
            innerPadding ->
            GetNavHost(navController, modifier = Modifier.padding(innerPadding))
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    PurebbscomposeTheme {
        Greeting("Android")
    }
}