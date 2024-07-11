package com.nutmeg.mygrocerybudget.presentation.mainScreen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun mainScreen() {

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
                            .height(120.dp)
                            .padding(20.dp),
                        colors = CardDefaults.cardColors(containerColor = Color.Blue.copy(alpha = 0.2f))
                    ) {
                        Row(Modifier.fillMaxSize().padding(20.dp)) {
                            Column(Modifier.weight(5f), horizontalAlignment = Alignment.CenterHorizontally) {
                                Text(text = "Budget")
                            }
                            Column(Modifier.weight(5f), horizontalAlignment = Alignment.CenterHorizontally) {
                                Text(text = "Remaining")
                            }

                        }

                    }

                    Card(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(start = 20.dp, end = 20.dp, bottom = 20.dp),
                        colors = CardDefaults.cardColors(containerColor = Color.Blue.copy(alpha = 0.2f))
                    ) {

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

                    }
                }
            },

            )
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
