package com.maxporj.purebbs_compose.ui

import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.maxporj.purebbs_compose.R
import com.maxporj.purebbs_compose.config.MyViewModel
import com.maxporj.purebbs_compose.ui.route.GetNavHost
import com.maxporj.purebbs_compose.ui.theme.PurebbscomposeTheme

@Composable
fun App(myViewModel: MyViewModel) {
    PurebbscomposeTheme {

        val navController = rememberNavController()

        val openDialog = remember { mutableStateOf(false) }

        ShowScaffold(myViewModel, navController, openDialog)

        ShowLogin(openDialog)
    }
}

@Composable
private fun ShowLogin(openDialog: MutableState<Boolean>) {

    val thisContext = LocalContext.current

    if (openDialog.value) {
        LoginDialog(
            onDismiss = {
                openDialog.value = !openDialog.value
                Toast.makeText(thisContext, "Dialog dismissed!", Toast.LENGTH_SHORT)
                    .show()
            },
            onNegativeClick = {
                openDialog.value = !openDialog.value
                Toast.makeText(thisContext, "Negative Button Clicked!", Toast.LENGTH_SHORT)
                    .show()

            },
            onPositiveClick = { color ->
                openDialog.value = !openDialog.value
                Toast.makeText(thisContext, "Selected color: $color", Toast.LENGTH_SHORT)
                    .show()
            }
        )
    }
}

@Composable
private fun LoginDialog(
    onDismiss: () -> Unit,
    onNegativeClick: () -> Unit,
    onPositiveClick: (Color) -> Unit
) {
    var red by remember { mutableStateOf(0f) }
    var green by remember { mutableStateOf(0f) }
    var blue by remember { mutableStateOf(0f) }

    val color = Color(
        red = red.toInt(),
        green = green.toInt(),
        blue = blue.toInt(),
        alpha = 255
    )

    Dialog(onDismissRequest = onDismiss) {

        Card(
            elevation = 8.dp,
            shape = RoundedCornerShape(12.dp)
        ) {

            Column(modifier = Modifier.padding(8.dp)) {

                Text(
                    text = "Select Color",
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp,
                    modifier = Modifier.padding(8.dp)
                )
                Spacer(modifier = Modifier.height(8.dp))

                // Color Selection
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {


                    Column {

                        Text(text = "Red ${red.toInt()}")
                        Slider(
                            value = red,
                            onValueChange = { red = it },
                            valueRange = 0f..255f,
                            onValueChangeFinished = {}
                        )
                        Spacer(modifier = Modifier.height(8.dp))

                        Text(text = "Green ${green.toInt()}")
                        Slider(
                            value = green,
                            onValueChange = { green = it },
                            valueRange = 0f..255f,
                            onValueChangeFinished = {}
                        )
                        Spacer(modifier = Modifier.height(8.dp))

                        Text(text = "Blue ${blue.toInt()}")
                        Slider(
                            value = blue,
                            onValueChange = { blue = it },
                            valueRange = 0f..255f,
                            onValueChangeFinished = {}
                        )

                        Spacer(modifier = Modifier.height(8.dp))
                        Surface(
                            border = BorderStroke(1.dp, Color.DarkGray),
                            color = color,
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(40.dp)
                        ) {}
                    }
                }

                // Buttons
                Row(
                    horizontalArrangement = Arrangement.End,
                    modifier = Modifier.fillMaxWidth()
                ) {

                    TextButton(onClick = onNegativeClick) {
                        Text(text = "CANCEL")
                    }
                    Spacer(modifier = Modifier.width(4.dp))
                    TextButton(onClick = {
                        onPositiveClick(color)
                    }) {
                        Text(text = "OK")
                    }
                }
            }
        }
    }
}

@Composable
private fun ShowScaffold(
    myViewModel: MyViewModel,
    navController: NavHostController,
    openDialog: MutableState<Boolean>
) {
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
                            // if(navController.previousBackStackEntry != null){
                            if (myViewModel.canNavigateBack.value) {
                                navController.navigateUp()
                                myViewModel.canNavigateBack.value = false
                            }
                        }
                    ) {
                        // if (navController.previousBackStackEntry != null){
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


                    Box(
                        Modifier
                            .wrapContentSize(Alignment.TopEnd)
                    ) {

                        val expanded = remember { mutableStateOf(false) }

                        IconButton(onClick = {
                            expanded.value = true
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
                                openDialog.value = true

                            }) {
                                Text("Login")
                            }

                            DropdownMenuItem(onClick = {
                                expanded.value = false
                            }) {
                                Text("Second item")
                            }

                            Divider()

                            DropdownMenuItem(onClick = {
                                expanded.value = false
                            }) {
                                Text("Third item")
                            }

                            Divider()

                            DropdownMenuItem(onClick = {
                                expanded.value = false
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
    ) { innerPadding ->
        GetNavHost(navController, modifier = Modifier.padding(innerPadding))
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