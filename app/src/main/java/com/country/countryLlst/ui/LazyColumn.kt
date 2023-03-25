package com.country.countryLlst.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.transform.CircleCropTransformation
import com.country.countryLlst.R
import com.country.countryLlst.data.Country


@Composable
 fun LazyColumn(list : List<Country>) {
    LazyColumn(
        modifier = Modifier.fillMaxSize()
    ) {

        item {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(10.dp)
            ) {



                CustomStyleTextField(
                    stringResource(id = R.string.search_country),
                    R.drawable.ic_search,
                    KeyboardType.Text,
                    VisualTransformation.None
                )

            }
        }


        items(list.size) {
            CountryListCard(list[it])
        }



    }
}



@Composable
private fun CountryListCard(country: Country) {
    Card(
        shape = RoundedCornerShape(14.dp),
        backgroundColor = Color.White,
        modifier = Modifier
            .padding(10.dp)
            .fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            verticalAlignment = Alignment.CenterVertically

        ) {
            val painter = rememberAsyncImagePainter(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(country.flags?.png.toString())
                    .size(coil.size.Size.ORIGINAL).transformations(CircleCropTransformation())
                    .build()
            )

            Image(
                modifier = Modifier
                    .size(70.dp,70.dp),
                painter = painter,
                contentDescription = country.name?.common.toString()
            )
            Row(modifier = Modifier.padding(start = 16.dp)) {
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text =  country.name?.common.toString(),
                        style = TextStyle(
                            color = gray,
                            fontSize = 16.sp,
                            fontFamily = FontFamily(Font(R.font.helvetica_neue_medium))
                        )
                    )
                    Text(
                        text =  country.region.toString(),
                        style = TextStyle(
                            color = colorPrimary,
                            fontSize = 16.sp,
                            fontFamily = FontFamily(Font(R.font.helvetica_neue_medium))
                        )
                    )
                }

            }
        }
    }
}


