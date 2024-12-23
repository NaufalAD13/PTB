package com.example.ptb.frontend.component

import android.annotation.SuppressLint
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.ptb.R.color

@Composable
fun ButtonAction(
    text: String,
    backgroundColor: Color = colorResource(id = color.peach),
    textColor: Color = colorResource(id = color.white),
    @SuppressLint("ModifierParameter") modifier: Modifier = Modifier,
    onClick: () -> Unit,
) {
    Button(
        modifier = Modifier,
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            containerColor = backgroundColor,
            contentColor = textColor
        ),
        shape = RoundedCornerShape(24.dp),
    ) {
        Text(text = text, color = textColor)
    }
}

@Preview
@Composable
private fun ButtonActionPrev() {
    ButtonAction(
        onClick={},
        text = "LogOut",
        backgroundColor = colorResource(id = color.peach),
        textColor = colorResource(id = color.white)
    )
}
