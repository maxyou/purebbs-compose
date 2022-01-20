package com.maxporj.purebbs_compose.ui

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberImagePainter
import coil.decode.SvgDecoder
import com.maxporj.purebbs_compose.R
import com.maxporj.purebbs_compose.config.Config
import com.maxporj.purebbs_compose.config.MyViewModel
import com.maxporj.purebbs_compose.net.HttpData
import com.maxporj.purebbs_compose.net.HttpService
import com.maxporj.purebbs_compose.ui.route.GetNavHost
import com.maxporj.purebbs_compose.ui.theme.PurebbscomposeTheme

@Composable
fun App(myViewModel: MyViewModel) {
    PurebbscomposeTheme {

        val navController = rememberNavController()

        val openDialog = remember { mutableStateOf(false) }

        ShowScaffold(myViewModel, navController, openDialog)

        if (openDialog.value) {
            ShowLogin(myViewModel = myViewModel) {
                openDialog.value = !openDialog.value
            }
        }
    }
}

@Composable
private fun ShowLogin(myViewModel: MyViewModel, onDismiss: () -> Unit) {

    val thisContext = LocalContext.current
    var name by remember { mutableStateOf("") }
    var pwd by remember { mutableStateOf("") }
    var code by remember { mutableStateOf("") }
    var showPwd by remember { mutableStateOf(false) }
    var loginError by remember { mutableStateOf("") }
    var showLoginError by remember { mutableStateOf(false) }
    var confirmCount by remember { mutableStateOf(0) }

    LaunchedEffect(confirmCount){
        if(confirmCount != 0){

            Log.d("PureBBS", "login confirm count: ${confirmCount}, name:${name}, pwd:${pwd}, code:${code}")

            val loginReturn = HttpService.api.login(HttpData.LoginData(
                name = name, password = pwd, code = code
            ))

            Log.d("PureBBS", "login return code: ${loginReturn?.code}")
            Log.d("PureBBS", "login return message: ${loginReturn?.message}")
            Log.d("PureBBS", "login return data: ${loginReturn?.data.toString()}")

            if(loginReturn!=null){
                if(loginReturn.code!=0){
                    loginError = "failed: ${loginReturn.message}"
                    showLoginError = true
                }else{
//                    myViewModel.loginRetrun.value = loginReturn
                    myViewModel.userInfo.value = myViewModel.getUserInfoFromLogin(loginReturn)
                    onDismiss()
                }
            }else{
                loginError = "login return null"
                showLoginError = true
            }

        }
    }


    LoginDialog(
        onDismiss = onDismiss,
        onNegativeClick = onDismiss,
        onPositiveClick = { confirmCount++ },
        name = name,
        onNameChange = { name = it },
        pwd = pwd,
        onPwdChange = { pwd = it},
        code = code,
        onCodeChange = { code = it},
        showPwd = showPwd,
        onShowPwdChange = { showPwd = it},
        loginError = loginError,
        showLoginError = showLoginError
    )
}

@Composable
private fun LoginDialog(
    onDismiss: () -> Unit,
    onNegativeClick: () -> Unit,
    onPositiveClick: () -> Unit,
    name:String,
    onNameChange:(String)->Unit,
    pwd:String,
    onPwdChange:(String)->Unit,
    code:String,
    onCodeChange:(String)->Unit,
    loginError:String,
    showLoginError:Boolean,
    showPwd:Boolean,
    onShowPwdChange:(Boolean)->Unit,
) {
    Dialog(onDismissRequest = onDismiss) {

        Log.d("PureBBS", "showLoginError: ${showLoginError}")
        Log.d("PureBBS", "loginError: ${loginError}")

        var codeDownloadCount by remember { mutableStateOf(0) }

        val path = Config.calcRandomCodeImgPath(codeDownloadCount)
        Log.d("PureBBS", "random code img path: ${path}")

        val painter = rememberImagePainter(
            data = path,
            builder = {
                decoder(SvgDecoder(LocalContext.current))
            }
        )

        Card(
            elevation = 8.dp,
            shape = RoundedCornerShape(12.dp)
        ) {

            Column(modifier = Modifier.padding(8.dp)) {

                Text(
                    text = "Login",
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp,
                    modifier = Modifier.padding(8.dp)
                )
                Spacer(modifier = Modifier.height(8.dp))

                OutlinedTextField(
                    value = name,
                    onValueChange = {
                        onNameChange(it)
                    },
                    singleLine = true,
                    maxLines = 1,
                    label = { Text("Name") }
                )

                Spacer(modifier = Modifier.height(8.dp))

                OutlinedTextField(
                    value = pwd,
                    onValueChange = {
                        onPwdChange(it)
                    },
                    singleLine = true,
                    maxLines = 1,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                    visualTransformation = if (showPwd) VisualTransformation.None else PasswordVisualTransformation(),
                    label = { Text("Password") }
                )

                Row {
                    Checkbox(
                        // below line we are setting
                        // the state of checkbox.
                        checked = showPwd,
                        // below line is use to add padding
                        // to our checkbox.
                        modifier = Modifier.padding(16.dp),
                        // below line is use to add on check
                        // change to our checkbox.
                        onCheckedChange = { onShowPwdChange(it) },
                    )
                    // below line is use to add text to our check box and we are
                    // adding padding to our text of checkbox
                    Text(text = "View Password", modifier = Modifier.padding(16.dp))
                }

                OutlinedTextField(
                    value = code,
                    onValueChange = {
                        onCodeChange(it)
                    },
                    singleLine = true,
                    maxLines = 1,
                    label = { Text("Code") }
                )

                Image(
                    painter = painter,
                    contentDescription = "image",
                    modifier = Modifier
                        .clickable { codeDownloadCount++ }
                        .width(250.dp)
                        .height(75.dp)
                        .wrapContentSize(align = Alignment.CenterStart, unbounded = false)
                        .padding(10.dp)
                )

                Text(
                    text = if(showLoginError) loginError else "",
                    modifier = Modifier
                        .padding(8.dp)
                        .height(30.dp)
                        .fillMaxWidth()
                )
//                if(showLoginError){
//                }

                // Buttons
                Row(
                    horizontalArrangement = Arrangement.End,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    TextButton(onClick = onNegativeClick) {
                        Text(text = "CANCEL")
                    }
                    Spacer(modifier = Modifier.width(4.dp))
                    TextButton(onClick = onPositiveClick) {
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
//    val loginRetrun by myViewModel.loginRetrun.collectAsState()
    val userInfo by myViewModel.userInfo.collectAsState()

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

                    if(userInfo!=null && userInfo!!.code == 0){

                        val path = Config.calcTopAppBarAvatarPath(userInfo!!)

                        val painter = rememberImagePainter(
                            data = path,
                            builder = {
                                crossfade(true)
                            }
                        )
//                        IconButton(onClick = {
//
//                        }) {
//                            Icon(painter = painter,
//                                contentDescription = "",
//                                modifier = Modifier
//                                .width(30.dp)
//                                .aspectRatio(1f)
//                                .clip(CircleShape))
//                        }

                        Image(
                            painter = painter,
                            contentDescription = "image",
                            modifier = Modifier
                                .clickable {  }
                                .width(30.dp)
                                .aspectRatio(1f)
                                .clip(CircleShape)
                        )
                    }


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

