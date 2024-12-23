package com.example.ptb.frontend.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.ptb.R
import com.example.ptb.frontend.component.BottomBar
import com.example.ptb.frontend.component.SeminarSection
import com.example.ptb.viewmodels.UserViewModel


@Composable
fun HomeScreen(
    viewModel: UserViewModel = hiltViewModel(),
    navController: NavController
) {
    val user by viewModel.user.collectAsState()

    Scaffold(
        bottomBar = { BottomBar(navController = navController) }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(Color(0xFFFDF5F5))
        ) {
            item {
                HeaderSection(userName = user?.nama ?: "Nama")
            }
            item {
                SeminarSection(
                    onJadwalClick = {},
                    onPermintaanClick = { navController.navigate("list-permintaan") }
                )
            }
        }
    }
}

@Composable
fun HeaderSection(userName: String) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(colorResource( R.color.peach))
            .padding(16.dp)
    ) {
        Column {
            Text(
                text = "Halo,",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
            Text(
                text = userName,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Bagaimana kabar anda?",
                fontSize = 16.sp,
                color = Color.White
            )
        }
        androidx.compose.foundation.Image(
            painter = painterResource(id = R.drawable.graduating_student_1),
            contentDescription = "Profile Picture",
            modifier = Modifier
                .size(48.dp)
                .align(Alignment.TopEnd)
                .background(Color.Gray, CircleShape)
        )
    }
}


@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    val navController = rememberNavController()
    HomeScreen(navController = navController)
}

