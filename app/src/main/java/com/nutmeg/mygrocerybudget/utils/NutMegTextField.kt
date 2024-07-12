package com.nutmeg.mygrocerybudget.utils

import android.icu.text.DecimalFormat
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.LocalTextStyle
import androidx.compose.material.OutlinedTextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

class NutMegTextField {

    @OptIn(ExperimentalComposeUiApi::class)
    @Composable
    fun StringField(
        modifier: Modifier = Modifier,
        onValueChange: () -> Unit = {},
        data: TextfieldData = TextfieldData(),
        imeAction: ImeAction = ImeAction.Done
    ){

        val focusManager = LocalFocusManager.current
        val keyboardController = LocalSoftwareKeyboardController.current

        OutlinedTextField(
            value = data.text,
            onValueChange = {

                data.text = it

                onValueChange()

            },
            modifier = modifier.fillMaxWidth(),
            textStyle = LocalTextStyle.current.copy(textAlign = TextAlign.Left),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction = imeAction),
            keyboardActions = KeyboardActions(
                onDone = {keyboardController?.hide()},
                onNext = {focusManager.moveFocus(FocusDirection.Down)}
            ),
            singleLine = true,
            shape = RoundedCornerShape(6.dp)
        )
    }

    @OptIn(ExperimentalComposeUiApi::class)
    @Composable
    fun CurrencyField(
        modifier: Modifier = Modifier,
        textStyle: TextStyle = LocalTextStyle.current,
        onValueChange: () -> Unit = {},
        data: TextfieldData = TextfieldData(),
        imeAction: ImeAction = ImeAction.Done
    ){

        val focusManager = LocalFocusManager.current
        val keyboardController = LocalSoftwareKeyboardController.current

        OutlinedTextField(
            value = data.text,
            onValueChange = {

            data.text = cleanForOnlyNumbersWithNoLeading0s(it)

            onValueChange()

        },
            modifier = modifier.fillMaxWidth(),
            textStyle = textStyle.copy(textAlign = TextAlign.Right),
            visualTransformation = DecimalInputFormatter("$"),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number,
                imeAction = imeAction),
            keyboardActions = KeyboardActions(
                onDone = {keyboardController?.hide()},
                onNext = {focusManager.moveFocus(FocusDirection.Down)}
            ),
            singleLine = true,
            shape = RoundedCornerShape(6.dp)
        )
    }

    @OptIn(ExperimentalComposeUiApi::class)
    @Composable
    fun IntField(
        modifier: Modifier = Modifier,
        onValueChange: () -> Unit = {},
        data: TextfieldData = TextfieldData(),
        imeAction: ImeAction = ImeAction.Done
    ){

        val focusManager = LocalFocusManager.current
        val keyboardController = LocalSoftwareKeyboardController.current

        OutlinedTextField(
            value = data.text,
            onValueChange = {

                data.text = cleanForOnlyNumbersWithNoLeading0s(it)

                onValueChange()

            },
            modifier = modifier.fillMaxWidth(),
            textStyle = LocalTextStyle.current.copy(textAlign = TextAlign.Right),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number,
                imeAction = imeAction),
            keyboardActions = KeyboardActions(
                onDone = {keyboardController?.hide()},
                onNext = {focusManager.moveFocus(FocusDirection.Down)}
            ),
            singleLine = true,
            shape = RoundedCornerShape(6.dp)
        )
    }

    private fun cleanForOnlyNumbersWithNoLeading0s(entry: String): String{
        return if (entry.startsWith("0")){
            ""
        }else{
            entry.replace(("[^0-9]").toRegex(), "")
        }
    }

}

class TextfieldData{
    var text: String by mutableStateOf("")
    val dpFormattedText get() = cleanDecimalString()

    private fun cleanDecimalString(): String{
        val intPart = text
            .dropLast(2)
            .ifEmpty { "0" }

        val fractionPart = text
            .takeLast(2).let{
                if (it.length != 2){
                    List(2 - it.length){
                        "0"
                    }.joinToString("") + it
                }else{
                    it
                }
            }

        return intPart + "." + fractionPart

    }
}



