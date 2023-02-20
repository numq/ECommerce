package com.numq.ecommerce.keyboard

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Backspace
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun NumeralKeyboard(
    onClick: (String) -> Unit,
    onBackspace: () -> Unit
) {
    Column(
        Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        val numbers = 1..9
        val zero = 0
        numbers.chunked(3).forEach { row ->
            Row(
                Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {
                row.forEach {
                    Box(
                        Modifier
                            .size(64.dp)
                            .clip(CircleShape)
                            .clickable { onClick("$it") }, contentAlignment = Alignment.Center
                    ) {
                        Text("$it", fontSize = 24.sp)
                    }
                }
            }
        }
        Row(
            Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Spacer(Modifier.size(64.dp))
            Box(
                Modifier
                    .size(64.dp)
                    .clip(CircleShape)
                    .clickable { onClick("$zero") }, contentAlignment = Alignment.Center
            ) {
                Text("$zero", fontSize = 24.sp)
            }
            Box(
                Modifier
                    .size(64.dp)
                    .clip(CircleShape)
                    .clickable { onBackspace() }, contentAlignment = Alignment.Center
            ) {
                Icon(
                    Icons.Default.Backspace,
                    "backspace"
                )
            }
        }
    }
}