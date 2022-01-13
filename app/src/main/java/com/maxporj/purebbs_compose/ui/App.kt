package com.maxporj.purebbs_compose.ui

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
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
import com.maxporj.purebbs_compose.R
import com.maxporj.purebbs_compose.config.MyViewModel
import com.maxporj.purebbs_compose.ui.route.GetNavHost
import com.maxporj.purebbs_compose.ui.theme.PurebbscomposeTheme

@Composable
fun App(myViewModel: MyViewModel) {
    PurebbscomposeTheme {

//        val myViewModel: MyViewModel = viewModel(LocalContext.current as ComponentActivity)

        val navController = rememberNavController()
        val backstackEntry = navController.currentBackStackEntryAsState()

        val result = remember { mutableStateOf("") }
        val expanded = remember { mutableStateOf(false) }
        val openDialog = remember { mutableStateOf(false)  }

        Scaffold(
            backgroundColor = colorResource(R.color.purple_200),
            topBar = {
                TopAppBar(
                    title = {
                        Text(text = "Top app bar")
                    },
                    navigationIcon = {
                        IconButton(
                            onClick = {
            //                if(navController.previousBackStackEntry != null){
                                if (myViewModel.canNavigateBack.value) {
                                    navController.navigateUp()
                                    myViewModel.canNavigateBack.value = false
                                }
                            }
                        ) {
            //            if (navController.previousBackStackEntry != null){
                            if (myViewModel.canNavigateBack.value) {
                                Icon(
                                    imageVector = Icons.Filled.ArrowBack,
                                    contentDescription = "Back"
                                )
                            } else {
                                Icon(Icons.Filled.Menu, contentDescription = "")
                            }
                        }
                    },
                    actions = {
//                        IconButton(onClick = {
//                            result.value = " Play icon clicked"
//                        }) {
//                            Icon(Icons.Filled.PlayArrow, contentDescription = "")
//                        }

//                        IconToggleButton(
//                            checked = liked.value,
//                            onCheckedChange = {
//                                liked.value = it
//                                if (liked.value) {
//                                    result.value = "Liked"
//                                } else {
//                                    result.value = "Unliked"
//                                }
//                            }
//                        ) {
//                            val tint by animateColorAsState(
//                                if (liked.value) Color(0xFF7BB661)
//                                else Color.LightGray
//                            )
//                            Icon(
//                                Icons.Filled.Favorite,
//                                contentDescription = "Localized description",
//                                tint = tint
//                            )
//                        }

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
                                    result.value = "goto login"

                                    openDialog.value = true

                                }) {
                                    Text("Login")
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

        if (openDialog.value) {
            AlertDialog(
                onDismissRequest = {
                    // Dismiss the dialog when the user clicks outside the dialog or on the back
                    // button. If you want to disable that functionality, simply use an empty
                    // onCloseRequest.
                    openDialog.value = false
                },
                title = {
                    Text(text = "Dialog Title")
                },
                text = {
                    Text("Here is a text ")
                },
                confirmButton = {
                    Button(

                        onClick = {
                            openDialog.value = false
                        }) {
                        Text("This is the Confirm Button")
                    }
                },
                dismissButton = {
                    Button(

                        onClick = {
                            openDialog.value = false
                        }) {
                        Text("This is the dismiss Button")
                    }
                }
            )
        }
    }
}



//@Composable
//fun LoginDialog(){
//
//    AlertDialog(
//        onDismissRequest = {
//            // Dismiss the dialog when the user clicks outside the dialog or on the back
//            // button. If you want to disable that functionality, simply use an empty
//            // onCloseRequest.
//            openDialog.value = false
//        },
//        title = {
//            Text(text = "Dialog Title")
//        },
//        text = {
//            Text("Here is a text ")
//        },
//        confirmButton = {
//            Button(
//
//                onClick = {
//                    openDialog.value = false
//                }) {
//                Text("This is the Confirm Button")
//            }
//        },
//        dismissButton = {
//            Button(
//
//                onClick = {
//                    openDialog.value = false
//                }) {
//                Text("This is the dismiss Button")
//            }
//        }
//    )
//
//}

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