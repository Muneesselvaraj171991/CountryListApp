package com.country.countryLlst.ui

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.country.countryLlst.R
import com.country.countryLlst.data.Country


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun DashboardScreen(list : List<Country>) {
    val selectedIndex = remember { mutableStateOf(0) }
    Surface(modifier = Modifier.fillMaxSize()) {
        Scaffold(
            topBar = {
                CustomTopAppBar()
            },
            content = {
                Surface(modifier = Modifier.fillMaxSize(), color = colorPrimary) {
                    Card(
                        backgroundColor = ghost_white,
                        modifier = Modifier.fillMaxSize(),
                    ) {
                        Box(modifier = Modifier.padding(bottom = 96.dp)) {
                            when (selectedIndex.value) {
                                0 -> {
                                    LazyColumn(list)
                                }
                                1 -> {
                                   // LazyColumn(list)
                                }
                                2 -> {
                                }
                                3 -> {
                                }
                            }
                        }

                    }
                }
            },
            bottomBar = {
                CustomBottomBar(selectedIndex = selectedIndex)
            },

            )

    }
}

@Composable
fun CustomTopAppBar() {
    TopAppBar(
        elevation = 0.dp,
        modifier = Modifier.fillMaxWidth(),
        title = {
            Box(modifier = Modifier.fillMaxWidth()) {
                Text(
                    text = stringResource(id = R.string.app_name) ,
                    modifier = Modifier.align(Alignment.Center),
                    color = Color.White,
                    style = TextStyle(
                        fontStyle = FontStyle.Italic,
                        fontFamily = FontFamily(Font((R.font.josefin_sans_semibold_italic))),
                        fontSize = 22.sp
                    )
                )
//                IconButton(
//                    modifier = Modifier.align(Alignment.CenterEnd),
//                    onClick = {
//
//                    }
//                ) {
//                    Image(
//                        painter = painterResource(id = R.drawable.ic_search),
//                        contentDescription = stringResource(id = R.string.dashboard_search)
//                    )
//                }
            }
        },
        backgroundColor = colorPrimary,
    )
}
@Composable
fun CustomSearch(){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp)
    ) {


        Text(
            text = "Search Country",
            style = MaterialTheme.typography.subtitle1.copy(color = gray),
            modifier = Modifier.padding(bottom = 10.dp, top = 10.dp)
        )

        CustomStyleTextField(
            "Search",
            R.drawable.ic_search,
            KeyboardType.Text,
            VisualTransformation.None
        )

    }
}
@Composable
fun CustomBottomBar(selectedIndex: MutableState<Int>) {

    val listItems = listOf(stringResource(id = R.string.list), stringResource(id = R.string.location), stringResource(
        id = R.string.Map))

    Card(
        elevation = 0.dp,
        shape = RoundedCornerShape(20.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .height(64.dp)
    ) {
        BottomNavigation(backgroundColor = Color.White) {
            listItems.forEachIndexed { index, label ->
                val isSelected = selectedIndex.value == index
                BottomNavigationItem(
                    icon = {
                        when (index) {
                            0 -> {
                                TabIcons(
                                    ImageBitmap.imageResource(id = R.drawable.ic_home),
                                    isSelected
                                )
                            }
                            1 -> {
                                TabIcons(
                                    ImageBitmap.imageResource(id = R.drawable.ic_location),
                                    isSelected
                                )
                            }
                            2 -> {
                                TabIcons(
                                    ImageBitmap.imageResource(id = R.drawable.ic_location),
                                    isSelected
                                )
                            }

                        }
                    },
                    selected = isSelected,
                    onClick = { selectedIndex.value = index },
                    alwaysShowLabel = false
                )
            }
        }
    }
}

@Composable
fun TabIcons(icon: ImageBitmap, isTintColor: Boolean) {
    if (isTintColor) {
        Image(
            modifier = Modifier.wrapContentSize(),
            bitmap = icon,
            colorFilter = ColorFilter.tint(colorPrimary),
            contentScale = ContentScale.Fit,
            contentDescription = stringResource(id = R.string.tb_icon_if)
        )
    } else {
        Image(
            modifier = Modifier.wrapContentSize(),
            bitmap = icon,
            contentScale = ContentScale.Fit,
            contentDescription = stringResource(id = R.string.tb_icon_else)
        )
    }
}



