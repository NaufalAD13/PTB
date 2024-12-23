package com.example.ptb.frontend.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ptb.R

@Composable
fun SeminarSection(
    onJadwalClick: () -> Unit,
    onPermintaanClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Text(
            text = "Seminar",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )
        Spacer(modifier = Modifier.height(8.dp))
        Row(
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            MenuItem(
                iconRes = R.drawable.baseline_calendar_month_24,
                label = "Jadwal",
                onClick = onJadwalClick
            )
            MenuItem(
                iconRes = R.drawable.baseline_list_alt_24,
                label = "Permintaan",
                onClick = onPermintaanClick
            )
        }
    }
}