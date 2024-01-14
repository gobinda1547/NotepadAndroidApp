package com.gobinda.notepad.ui.items

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun MenuDivider(
    height: Int = 1
) {
    Divider(
        modifier = Modifier
            .fillMaxWidth()
            .height(height.dp)
    )
}