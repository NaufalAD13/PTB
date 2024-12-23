package com.example.ptb.frontend.formpenguji

import android.net.Uri
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.ptb.frontend.component.BottomBar
import com.example.ptb.frontend.component.CardForm
import com.example.ptb.frontend.component.TopBar
import com.example.ptb.viewmodels.MahasiswaViewModel
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.toRequestBody

@Composable
fun FormPengujiSeminarScreen(
    viewModel: MahasiswaViewModel = hiltViewModel(),
    id: Int,
    navController: NavController
) {
    var name by remember { mutableStateOf("") }
    var dateTime by remember { mutableStateOf("") }
    var tempat by remember { mutableStateOf("") }
    var comment by remember { mutableStateOf("") }
    var fileUri by remember { mutableStateOf<Uri?>(null) }
    var fileName by remember { mutableStateOf("") }

    val context = LocalContext.current

    Scaffold(
        topBar = {
            TopBar(
                text = "Form Penguji Seminar",
                onBackClick = {navController.popBackStack()}
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
                CardForm (
                    name = name,
                    nameChange = { name = it },
                    dateTime = dateTime,
                    dateTimeChange = { dateTime = it },
                    tempat = tempat,
                    tempatChange = { tempat = it },
                    comment = comment,
                    commentChange = { comment = it },
                    onFilePicked = { uri, pickedFileName ->
                        fileUri = uri
                        fileName = pickedFileName
                    },
                    onSubmit = {
                        // Validasi input
                        if (name.isEmpty() || dateTime.isEmpty() || tempat.isEmpty()) {
                            Toast.makeText(context, "Semua field harus diisi.", Toast.LENGTH_SHORT).show()
                            return@CardForm
                        }

                        // Konversi fileUri menjadi MultipartBody.Part
                        val surat = fileUri?.let { uri ->
                            val contentResolver = context.contentResolver
                            val inputStream = contentResolver.openInputStream(uri)
                            val fileBody = inputStream?.readBytes()?.toRequestBody("application/pdf".toMediaTypeOrNull())
                            MultipartBody.Part.createFormData("surat", fileName, fileBody!!)
                        }

                        viewModel.addSeminar(
                            idTugasAkhir = id.toString(),
                            namaPenguji = name,
                            tanggalSeminar = dateTime,
                            tempat = tempat,
                            komentar = comment,
                            surat = surat,
                            onSuccess = {
                                Toast.makeText(context, "Seminar berhasil ditambahkan.", Toast.LENGTH_SHORT).show()
                                navController.navigate("list-permintaan"){
                                    popUpTo("list-permintaan"){ inclusive = true }
                                }
                            },
                            onError = { errorMessage ->
                                Toast.makeText(context, errorMessage, Toast.LENGTH_SHORT).show()
                            }
                        )
                    }
                )
                }
            }
        }
}

@Preview(showBackground = true)
@Composable
fun FormPengujiSeminarScreenPreview() {
//    FormPengujiSeminarScreen( )
}
