package com.example.ptb.frontend.detailpermintaan

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.ptb.frontend.component.BottomBar
import com.example.ptb.frontend.component.DetailCard
import com.example.ptb.frontend.component.TopBar
import com.example.ptb.viewmodels.MahasiswaViewModel

@Composable
fun DetailPermintaan(
    viewModel: MahasiswaViewModel = hiltViewModel(),
    navController: NavController,
    id: Int,
    context: Context = LocalContext.current
) {
    val pengajuan by viewModel.pengajuan.collectAsState()

    LaunchedEffect(id) {
        viewModel.getPengajuanMahasiwa(id)
    }

    Scaffold(
        topBar = {
            TopBar(
                text = "Detail Permintaan Seminar",
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
            horizontalAlignment = Alignment.CenterHorizontally,
            contentPadding = PaddingValues(16.dp),
        ) {
            item {
                DetailCard(
                    name = pengajuan?.mahasiswa?.nama ?: "Nama",
                    studentId = pengajuan?.mahasiswa?.nim ?: "Nim",
                    title = pengajuan?.judul ?: "Judul",
                    advisor = pengajuan?.namaPembimbing ?: "Pembimbing",
                    onRejectClick = {
                        viewModel.updateStatusPengajuan(
                            id = pengajuan!!.idTugasAkhir,
                            status = "tolak",
                            onSucces = {
                                Toast.makeText(context, "Berhasil ditolak", Toast.LENGTH_SHORT).show()
                                navController.navigate("list-permintaan") {
                                    popUpTo("list-permintaan"){ inclusive = true }
                                }
                            },
                            onError = { errorMessage ->
                                Toast.makeText(context, errorMessage, Toast.LENGTH_SHORT).show()
                            }
                        )
                    },
                    onSetExaminerClick = {
                        navController.navigate("add-seminar/${pengajuan?.idTugasAkhir}")
                    }
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DetailPagePreview() {
    val navController = rememberNavController()
    DetailPermintaan(id = 1, navController = navController)
}
