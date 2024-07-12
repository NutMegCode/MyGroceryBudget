package com.nutmeg.mygrocerybudget.presentation.mainScreen

import android.util.Log
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
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.nutmeg.mygrocerybudget.utils.NutMegTextField
import com.nutmeg.mygrocerybudget.utils.TextfieldData
import java.text.NumberFormat
import java.util.Locale

data class Item(
    var name: String = "",
    var qty: Int = 1,
    var price: Double = 0.0,
    var itemTotal: Double = 0.0

)

@Composable
fun mainScreen() {

    var itemList by remember { mutableStateOf(emptyList<Item>()) }

    var showPopUp by remember { mutableStateOf(false) }

    val budgetTextfieldData = remember { TextfieldData() }

    val nameTextfieldData = remember { TextfieldData() }
    val quantityTextfieldData = remember { TextfieldData() }
    val priceTextfieldData = remember { TextfieldData() }


    val total = itemList.sumOf { it.itemTotal }

    val remaining = budgetTextfieldData.dpFormattedText.toDouble() - total

    Log.d("RandomString", "remaining is $remaining")

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


                    if (showPopUp) {
                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .wrapContentHeight()
                                .padding(20.dp),
                            colors = CardDefaults.cardColors(containerColor = Color.Blue.copy(alpha = 0.2f))
                        ) {
                            Column(
                                Modifier
                                    .fillMaxWidth()
                                    .wrapContentHeight()
                                    .padding(10.dp)
                            ) {
                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    Column(Modifier.width(60.dp)) {
                                        Text(text = "Name")
                                    }
                                    NutMegTextField().StringField(
                                        data = nameTextfieldData,
                                        imeAction = ImeAction.Next
                                    )
                                }
                                Row(Modifier.padding(top = 10.dp),verticalAlignment = Alignment.CenterVertically) {
                                    Column(Modifier.width(60.dp)) {
                                        Text(text = "Quantity")
                                    }
                                    NutMegTextField().IntField(
                                        data = quantityTextfieldData,
                                        imeAction = ImeAction.Next
                                    )
                                }
                                Row(Modifier.padding(top = 10.dp),verticalAlignment = Alignment.CenterVertically) {
                                    Column(Modifier.width(60.dp)) {
                                        Text(text = "Price")
                                    }
                                    NutMegTextField().CurrencyField(
                                        data = priceTextfieldData,
                                        imeAction = ImeAction.Done
                                    )
                                }

                                Row(
                                    Modifier
                                        .fillMaxWidth()
                                        .padding(top = 10.dp)) {
                                    Column(Modifier.weight(5f),horizontalAlignment = Alignment.CenterHorizontally) {
                                        NutMegStyledButton(
                                            title = "Discard",
                                            color = Color.Red,
                                            full = false
                                        ) {
                                            nameTextfieldData.text = ""
                                            quantityTextfieldData.text = ""
                                            priceTextfieldData.text = ""
                                            showPopUp = false
                                        }
                                    }

                                    Column(Modifier.weight(5f),horizontalAlignment = Alignment.CenterHorizontally) {
                                        NutMegStyledButton(
                                            title = "Save",
                                            full = false
                                        ) {
                                            val newItem = Item(
                                                name = nameTextfieldData.text,
                                                qty = quantityTextfieldData.text.toInt(),
                                                price = priceTextfieldData.dpFormattedText.toDouble(),
                                                itemTotal = priceTextfieldData.dpFormattedText.toDouble() * quantityTextfieldData.text.toDouble()
                                            )

                                            itemList =
                                                itemList.toMutableList().apply { add(newItem) }

                                            nameTextfieldData.text = ""
                                            quantityTextfieldData.text = ""
                                            priceTextfieldData.text = ""

                                            showPopUp = false
                                        }
                                    }


                                }


                            }

                        }
                    } else {


                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .wrapContentHeight()
                                .padding(20.dp),
                            colors = CardDefaults.cardColors(containerColor = Color.Blue.copy(alpha = 0.2f))
                        ) {
                            Column(Modifier.padding(12.dp)) {
                                Row(Modifier.padding(bottom = 12.dp)) {
                                    Column(
                                        Modifier.weight(5f),
                                        horizontalAlignment = Alignment.CenterHorizontally
                                    ) {
                                        Text(text = "Budget", style = TextStyle(fontSize = 24.sp))
                                    }

                                    Column(
                                        Modifier.weight(5f),
                                        horizontalAlignment = Alignment.CenterHorizontally
                                    ) {
                                        Text(text = "Remaining", style = TextStyle(fontSize = 24.sp))
                                    }
                                }

                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    Column(
                                        Modifier.weight(5f),
                                        horizontalAlignment = Alignment.CenterHorizontally,
                                        verticalArrangement = Arrangement.Center
                                    ) {
                                        NutMegTextField().CurrencyField(
                                            data = budgetTextfieldData,
                                            textStyle = TextStyle(fontSize = 24.sp),
                                            imeAction = ImeAction.Done
                                        )
                                    }

                                    Column(
                                        Modifier.weight(5f),
                                        horizontalAlignment = Alignment.CenterHorizontally,
                                        verticalArrangement = Arrangement.Center
                                    ) {
                                        Text(text = doubleToCurrencyFormatter(remaining), style = TextStyle(fontSize = 26.sp))
                                    }
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
                                verticalAlignment = Alignment.CenterVertically
                            ) {
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
                                    verticalArrangement = Arrangement.Center
                                ) {
                                    Text(text = "Qty")
                                }
                                Column(
                                    Modifier
                                        .fillMaxHeight()
                                        .weight(2f),
                                    horizontalAlignment = Alignment.CenterHorizontally,
                                    verticalArrangement = Arrangement.Center
                                ) {
                                    Text(text = "Price")
                                }
                                Column(
                                    Modifier
                                        .fillMaxHeight()
                                        .weight(2f),
                                    horizontalAlignment = Alignment.CenterHorizontally,
                                    verticalArrangement = Arrangement.Center
                                ) {
                                    Text(text = "Total")
                                }
                            }

                            LazyColumn {

                                items(itemList.size) { index ->
                                    ItemCell(
                                        itemList[index]
                                    ) {
                                        itemList =
                                            itemList.toMutableList().apply { removeAt(index) }
                                    }
                                }
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
                    if (!showPopUp)
                    NutMegStyledButton(title = "Add") {
                        showPopUp = true
                    }
                }
            },

            )
    }

@Composable
fun ItemCell(item: Item, deleteAction: (itemNumber: Int) -> Unit = {}){
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
            Text(text = item.name)
        }
        Column(
            Modifier
                .fillMaxHeight()
                .weight(2f),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center) {
            Text(text = item.qty.toString())
        }
        Column(
            Modifier
                .fillMaxHeight()
                .weight(2f),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center) {
            Text(text = doubleToCurrencyFormatter(item.price))
        }
        Column(
            Modifier
                .fillMaxHeight()
                .weight(2f),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center) {
            Text(text = doubleToCurrencyFormatter(item.itemTotal))
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

        val buttonColor: Color = if (full) color.copy(alpha = 0.5f) else Color.Transparent
        val textColor: Color = if (full) Color.White else color.copy(alpha = 0.5f)
        val borderColor: Color = color.copy(alpha = 0.5f)
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

fun stringToCurrencyFormatter(text: String = "00.00") : String{
    val cleanedText = text.replace(Regex("[^0-9.]+"), "")

    val currencyFormat = NumberFormat.getCurrencyInstance(Locale.getDefault())
    val transformedText = currencyFormat.format(cleanedText.toDouble())
    return transformedText.toString()
}

fun doubleToCurrencyFormatter(text: Double = 0.00) : String{

    val currencyFormat = NumberFormat.getCurrencyInstance(Locale.getDefault())
    val transformedText = currencyFormat.format(text)
    return transformedText.toString()
}
