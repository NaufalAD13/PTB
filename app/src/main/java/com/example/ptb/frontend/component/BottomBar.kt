package com.example.ptb.frontend.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.ptb.R

@Composable
fun BottomBar(modifier: Modifier = Modifier, navController: NavController ) {

    Row(
        modifier = Modifier.run {
            fillMaxWidth()
                .background(colorResource(R.color.peach))
        },
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ) {
        BottomBarItem(
            iconRes = R.drawable.baseline_home_24,
            label = "Home",
            onClick = { navController.navigate("home") }
        )
        BottomBarItem(
            iconRes = R.drawable.baseline_list_alt_24,
            label = "Permintaan",
            onClick = { navController.navigate("list-permintaan") }
        )
        BottomBarItem(
            iconRes = R.drawable.baseline_people_24,
            label = "Mahasiswa",
            onClick = {}
        )
        BottomBarItem(
            iconRes = R.drawable.baseline_account_circle_24,
            label = "Profile",
            onClick = {}
        )
    }
}

@Composable
fun BottomBarItem(iconRes: Int, label: String, onClick: () -> Unit) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .padding(8.dp)
            .clickable { onClick() }
    ) {
        Icon(
            painter = painterResource(id = iconRes),
            contentDescription = label,
            tint = Color.Black,
            modifier = Modifier
                .size(24.dp)
                .background(Color.Transparent, CircleShape)
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = label,
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )
    }
}

@Preview
@Composable
private fun BottomBarPrev() {
//   BottomBar()
}
