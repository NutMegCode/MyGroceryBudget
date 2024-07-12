package com.nutmeg.mygrocerybudget.utils

import android.icu.text.DecimalFormat
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation

class DecimalInputFormatter(
    private val prefix: String = ""
) : VisualTransformation {
    private val symbols = DecimalFormat().decimalFormatSymbols

    override fun filter(text: AnnotatedString): TransformedText {

        val inputText = text.text.removePrefix(prefix = prefix).replace(".", "")

        val intPart = inputText
            .dropLast(2)
            .reversed()
            .chunked(3)
            .joinToString(",")
            .reversed()
            .ifEmpty { "0" }

        val fractionPart = inputText.takeLast(2).let{
            if (it.length != 2){
                List(2 - it.length){
                    0
                }.joinToString("") + it
            } else {
                it
            }
        }

        val formattedNumber = prefix + intPart + "." + fractionPart

        val newText = AnnotatedString(
            text = formattedNumber,
            spanStyles = text.spanStyles,
            paragraphStyles = text.paragraphStyles
        )

        val offsetMapping = FixedCursorOffsetMapping(
            contentLength = inputText.length,
            formattedContentLenth = formattedNumber.length
        )

        return TransformedText(newText, offsetMapping)
    }

    private class FixedCursorOffsetMapping(
        private val contentLength: Int,
        private val formattedContentLenth: Int,
    ) : OffsetMapping {
        override fun originalToTransformed(offset: Int): Int = formattedContentLenth
        override fun transformedToOriginal(offset: Int): Int = contentLength
    }
}