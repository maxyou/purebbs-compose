package com.maxporj.purebbs_compose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.maxporj.purebbs_compose.ui.route.GetNavHost
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
        val expanded = remember { mutableStateOf(false) }
        val liked = remember { mutableStateOf(true) }

        Scaffold(
            backgroundColor = colorResource(R.color.purple_200),
            topBar = {
                TopAppBar(
                    title = {
                        Text(text = "Top app bar")
                    },
                    navigationIcon = {
                        // show drawer icon
                        IconButton(
                            onClick = {
                                result.value = "Drawer icon clicked"
                            }
                        ) {
                            Icon(Icons.Filled.Menu, contentDescription = "")
                        }
                    },
                    actions = {
                        IconButton(onClick = {
                            result.value = " Play icon clicked"
                        }) {
                            Icon(Icons.Filled.PlayArrow, contentDescription = "")
                        }

                        IconToggleButton(
                            checked = liked.value,
                            onCheckedChange = {
                                liked.value = it
                                if (liked.value) {
                                    result.value = "Liked"
                                } else {
                                    result.value = "Unliked"
                                }
                            }
                        ) {
                            val tint by animateColorAsState(
                                if (liked.value) Color(0xFF7BB661)
                                else Color.LightGray
                            )
                            Icon(
                                Icons.Filled.Favorite,
                                contentDescription = "Localized description",
                                tint = tint
                            )
                        }

                        Box(
                            Modifier
                                .wrapContentSize(Alignment.TopEnd)
                        ) {
                            IconButton(onClick = {
                                expanded.value = true
                                result.value = "More icon clicked"
                            }) {
                                Icon(
                                    Icons.Filled.MoreVert,
                                    contentDescription = "Localized description"
                                )
                            }

                            DropdownMenu(
                                expanded = expanded.value,
                                onDismissRequest = { expanded.value = false },
                            ) {
                                DropdownMenuItem(onClick = {
                                    expanded.value = false
                                    result.value = "First item clicked"
                                }) {
                                    Text("First Item")
                                }

                                DropdownMenuItem(onClick = {
                                    expanded.value = false
                                    result.value = "Second item clicked"
                                }) {
                                    Text("Second item")
                                }

                                Divider()

                                DropdownMenuItem(onClick = {
                                    expanded.value = false
                                    result.value = "Third item clicked"
                                }) {
                                    Text("Third item")
                                }

                                Divider()

                                DropdownMenuItem(onClick = {
                                    expanded.value = false
                                    result.value = "Fourth item clicked"
                                }) {
                                    Text("Fourth item")
                                }
                            }
                        }
                    },

                    backgroundColor = Color(0xFDCD7F32),
                    elevation = AppBarDefaults.TopAppBarElevation
                )
            },
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