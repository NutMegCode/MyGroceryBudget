package com.nutmeg.mygrocerybudget.presentation.mainScreen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun mainScreen() {

    var itemList by remember { mutableStateOf(emptyList<Int>()) }

        Scaffold(
            topBar = {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(80.dp)
                        .background(Color.Cyan.copy(alpha = 0.2f)),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Text(text = "My Shop Budget")
                }
            },
            content = { paddingValues ->
                // Main content area

                Column(
                    Modifier
                        .fillMaxSize()
                        .padding(paddingValues)) {


                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentHeight()
                            .padding(20.dp),
                        colors = CardDefaults.cardColors(containerColor = Color.Blue.copy(alpha = 0.2f))
                    ) {
                        Row(
                            Modifier
                                .fillMaxWidth()
                                .wrapContentHeight()
                                .padding(vertical = 10.dp)) {
                            Column(Modifier.weight(5f), horizontalAlignment = Alignment.CenterHorizontally) {
                                Text(text = "Budget")
                                TextField(value = "$0.00", onValueChange = {})
                            }
                            Column(Modifier.weight(5f), horizontalAlignment = Alignment.CenterHorizontally) {
                                Text(text = "Remaining")
                                Text(text = "$0.00")
                            }

                        }

                    }

                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentHeight()
                            .padding(start = 20.dp, end = 20.dp, bottom = 20.dp),
                        colors = CardDefaults.cardColors(containerColor = Color.Blue.copy(alpha = 0.2f))
                    ) {
                        Row(
                            Modifier
                                .height(50.dp)
                                .fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically) {
                            Column(
                                Modifier
                                    .fillMaxHeight()
                                    .width(50.dp)
                                    ) {

                            }
                            Column(
                                Modifier
                                    .fillMaxHeight()
                                    .weight(2f),
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.Center
                            ) {
                                Text(text = "Name")
                            }
                            Column(
                                Modifier
                                    .fillMaxHeight()
                                    .weight(2f),
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.Center) {
                                Text(text = "Qty")
                            }
                            Column(
                                Modifier
                                    .fillMaxHeight()
                                    .weight(2f),
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.Center) {
                                Text(text = "Price")
                            }
                            Column(
                                Modifier
                                    .fillMaxHeight()
                                    .weight(2f),
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.Center) {
                                Text(text = "Item Total")
                            }
                        }

                        LazyColumn() {

                            items(itemList){ item ->
                                itemCell({itemList = itemList - 1})
                            }
                        }

                    }
                }


            },
            bottomBar = {
                // Bottom bar content
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(80.dp), horizontalArrangement = Arrangement.Center
                ) {
                    NutMegStyledButton(title = "Add") {

                        itemList = itemList + 1

                    }
                }
            },

            )
    }

@Composable
fun itemCell(deleteAction: (itemNumber: Int) -> Unit = {}){
    Row(
        Modifier
            .height(50.dp)
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically) {
        Column(
            Modifier
                .fillMaxHeight()
                .width(50.dp)
                .background(Color.Red.copy(alpha = 0.4f))
                .clickable {
                    deleteAction(1)
                }) {

        }
        Column(
            Modifier
                .fillMaxHeight()
                .weight(2f),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(text = "Bread")
        }
        Column(
            Modifier
                .fillMaxHeight()
                .weight(2f),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center) {
            Text(text = "1")
        }
        Column(
            Modifier
                .fillMaxHeight()
                .weight(2f),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center) {
            Text(text = "$0.00")
        }
        Column(
            Modifier
                .fillMaxHeight()
                .weight(2f),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center) {
            Text(text = "$0.00")
        }
    }
}

    @Composable
    fun NutMegStyledButton(
        title: String,
        modifier: Modifier = Modifier,
        color: Color = Color.Blue,
        enabled: Boolean = true,
        full: Boolean = true,
        onClick: () -> Unit
    ) {

        val buttonColor: Color = if (full) color else Color.Transparent
        val textColor: Color = if (full) Color.White else color
        val borderColor: Color = color
        val textSize = 14

        Button(
            modifier = modifier
                .height(48.dp)
                .wrapContentWidth(),
            shape = RoundedCornerShape(10.dp),
            border = BorderStroke(1.dp, if (enabled) borderColor else borderColor.copy(0.5f)),
            contentPadding = PaddingValues(10.dp),
            enabled = enabled,
            colors = ButtonDefaults.buttonColors(
                containerColor = buttonColor,
                disabledContainerColor = buttonColor
            ),
            onClick = onClick
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(text = title, color = textColor, fontSize = textSize.sp)
            }

        }
    }
