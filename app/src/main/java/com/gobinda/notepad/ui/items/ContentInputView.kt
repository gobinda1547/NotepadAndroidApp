package com.gobinda.notepad.ui.items

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ContentInputView(
    text: String,
    hintText: String = "Type something..",
    onValueChanged: (String) -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = MaterialTheme.colorScheme.surface)
            .padding(16.dp)
    ) {
        BasicTextField(
            modifier = Modifier.fillMaxWidth(),
            value = text,
            onValueChange = onValueChanged,
            textStyle = TextStyle(
                color = MaterialTheme.colorScheme.onSurface,
                fontFamily = FontFamily.SansSerif,
                fontSize = 20.sp,
                fontStyle = FontStyle.Normal,
                fontWeight = FontWeight.Light
            ),
            cursorBrush = SolidColor(MaterialTheme.colorScheme.onSurface),
            singleLine = false,
        )
        if (text.isEmpty()) {
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = hintText,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                fontFamily = FontFamily.SansSerif,
                fontSize = 20.sp,
                fontStyle = FontStyle.Normal,
                fontWeight = FontWeight.Light,
            )
        }
    }
}