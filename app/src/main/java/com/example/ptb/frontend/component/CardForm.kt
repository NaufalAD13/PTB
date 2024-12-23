package com.example.ptb.frontend.component

import android.content.Context
import android.net.Uri
import android.provider.OpenableColumns
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.tooling.preview.Preview
import com.example.ptb.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CardForm(
    name: String,
    nameChange: (String) -> Unit,
    dateTime: String,
    dateTimeChange: (String) -> Unit,
    tempat: String,
    tempatChange: (String) -> Unit,
    comment: String,
    commentChange: (String) -> Unit,
    onFilePicked: (Uri?, String) -> Unit,
    onSubmit: () -> Unit
) {

    val context = LocalContext.current
    val fileName = remember { mutableStateOf("") }
    val filePickerLauncher =
        rememberLauncherForActivityResult(ActivityResultContracts.OpenDocument()) { uri: Uri? ->
            if (uri != null) {
                fileName.value = getFileName(context, uri) ?: "Unknown File"
                onFilePicked(uri, fileName.value) // Kirim URI dan nama file ke screen utama
            }
        }

    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()
            .background(colorResource(R.color.yellow), RoundedCornerShape(16.dp)),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Form Penguji Seminar",
            style = MaterialTheme.typography.titleMedium.copy(fontSize = 18.sp),
            modifier = Modifier.padding(16.dp)
        )
        OutlinedTextField(
            value = name,
            onValueChange = nameChange,
            label = { Text("Nama") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            colors = TextFieldDefaults.textFieldColors(
                disabledLabelColor = Color.Black,
                containerColor = Color.White
            ),
            shape = RoundedCornerShape(16.dp)
        )
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            value = dateTime,
            onValueChange = dateTimeChange,
            label = { Text("Tanggal/Waktu") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            colors = TextFieldDefaults.textFieldColors(
                disabledLabelColor = Color.Black,
                containerColor = Color.White
            ),
            shape = RoundedCornerShape(16.dp)
        )
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            value = tempat,
            onValueChange = tempatChange,
            label = { Text("Tempat") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            colors = TextFieldDefaults.textFieldColors(
                disabledLabelColor = Color.Black,
                containerColor = Color.White
            ),
            shape = RoundedCornerShape(16.dp)
        )
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            value = comment,
            onValueChange = commentChange,
            label = { Text("Komentar") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            colors = TextFieldDefaults.textFieldColors(
                disabledLabelColor = Color.Black,
                containerColor = Color.White
            ),
            shape = RoundedCornerShape(16.dp)
        )
        Spacer(modifier = Modifier.height(8.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            TextField(
                value = fileName.value,
                onValueChange = { },
                enabled = false,
                modifier = Modifier.weight(1f),
                label = { Text("File") },
                colors = TextFieldDefaults.textFieldColors(
                    disabledLabelColor = Color.Black,
                    disabledTextColor = Color.Gray,
                    containerColor = Color.White
                ),
                shape = RoundedCornerShape(16.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))
            IconButton(
                onClick = { filePickerLauncher.launch(arrayOf("application/pdf", "application/msword", "application/vnd.openxmlformats-officedocument.wordprocessingml.document")) },
                modifier = Modifier.size(48.dp)
            ) {
                Icon(
                    painter = painterResource(R.drawable.baseline_upload_file_24),
                    contentDescription = "Upload File",
                    tint = Color.Black
                )
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = onSubmit,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .height(48.dp),
            colors = ButtonDefaults.buttonColors(containerColor = colorResource(R.color.peach)),
            shape = RoundedCornerShape(16.dp)
        ) {
            Text("Submit", color = Color.Black)
        }
        Spacer(modifier = Modifier.height(16.dp))
    }
}

// Fungsi untuk mendapatkan nama file dari URI
fun getFileName(context: Context, uri: Uri): String? {
    val cursor = context.contentResolver.query(uri, null, null, null, null)
    return cursor?.use {
        if (it.moveToFirst()) {
            val nameIndex = it.getColumnIndex(OpenableColumns.DISPLAY_NAME)
            if (nameIndex != -1) it.getString(nameIndex) else null
        } else null
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewCardForm() {
//    CardForm()
}
