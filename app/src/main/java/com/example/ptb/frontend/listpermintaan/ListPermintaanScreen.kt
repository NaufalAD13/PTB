package com.example.ptb.frontend.listpermintaan

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.ptb.R
import com.example.ptb.frontend.component.BottomBar
import com.example.ptb.frontend.component.CardList
import com.example.ptb.frontend.component.TopBar
import com.example.ptb.viewmodels.MahasiswaViewModel


@Composable
fun ListPermintaanSeminar(
    viewModel: MahasiswaViewModel = hiltViewModel(),
    navController: NavController
) {
    val mahasiswaList by viewModel.mahasiswaList.collectAsState()
    val errorMessage by viewModel.errorMessage.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.fetchMahasiswaList()
    }

    Scaffold(
        topBar = {
            TopBar(
                text = "Permintaan Seminar",
                onBackClick = { navController.popBackStack() }
            )
        },
        bottomBar = { BottomBar(navController = navController) }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(Color(0xFFFDF5F5)),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            contentPadding = PaddingValues(16.dp)
        ) {
            items(mahasiswaList) { mahasiswa ->
                CardList(
                    iconResId = R.drawable.graduating_student_1,
                    name = mahasiswa.nama,
                    id = mahasiswa.nim,
                    onClick = { navController.navigate("detail-permintaan/${mahasiswa.id}") }
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ListPermintaanSeminarPrev() {
    val navController = rememberNavController()
    ListPermintaanSeminar(navController = navController)
}
