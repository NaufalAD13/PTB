package com.example.ptb.frontend.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
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
import com.example.ptb.R

@Composable
fun DetailCard(
    name: String,
    studentId: String,
    title: String,
    advisor: String,
    onRejectClick: () -> Unit,
    onSetExaminerClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .width(324.dp)
            .height(500.dp),
        shape = RoundedCornerShape(16.dp),
    ) {
        Column(
            modifier = Modifier
                .background(colorResource(R.color.white))
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Column (
                modifier = Modifier
                    .background(colorResource(R.color.yellow))
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ){
                Spacer(modifier = Modifier.height(8.dp))
                Box(
                    modifier = Modifier
                        .size(100.dp)
                        .background(color = Color.White, shape = CircleShape)
                        .padding(16.dp),
                    contentAlignment = Alignment.Center,
                ) {
                    Image(
                        painter = painterResource(R.drawable.graduating_student_1),
                        contentDescription = null,
                        modifier = Modifier.size(48.dp)
                    )
                }
                Spacer(modifier = Modifier.height(8.dp))

                Text(text = name, fontSize = 18.sp, fontWeight = FontWeight.Bold)
                Text(text = studentId, fontSize = 14.sp, color = Color.Gray)

                Spacer(modifier = Modifier.height(16.dp))
            }
            Spacer(modifier = Modifier.height(16.dp))

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                horizontalAlignment = Alignment.Start
            ) {
                Text(text = "Judul :", fontWeight = FontWeight.Bold, fontSize = 16.sp)
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = title, fontSize = 14.sp, modifier = Modifier.padding(bottom = 8.dp))

                Text(text = "Pembimbing :", fontWeight = FontWeight.Bold, fontSize = 16.sp)
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = advisor, fontSize = 14.sp)
            }

            Spacer(modifier = Modifier.weight(1f))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.CenterHorizontally)
                    .padding(16.dp),
                horizontalArrangement = Arrangement.Absolute.SpaceBetween
            ) {
                ButtonAction(
                    text = "Tolak",
                    backgroundColor = Color.Red,
                    textColor = colorResource(id = R.color.white),
                    modifier = Modifier
                        .weight(1f)
                        .height(50.dp),
                    onClick = onRejectClick
                )
                ButtonAction(
                    text = "Pilih Penguji",
                    backgroundColor = Color(0xFFFFC107),
                    textColor = colorResource(id = R.color.white),
                    modifier = Modifier
                        .weight(1f)
                        .height(50.dp),
                    onClick = onSetExaminerClick
                )
            }


        }
    }
}

@Preview
@Composable
fun DetailCardPreview() {
    DetailCard(
        name = "Alice Guo",
        studentId = "1982374882",
        title = "Penerapan Dashboard pada restoran kentang",
        advisor = "Husnil Kamil, MT",
        onRejectClick = {},
        onSetExaminerClick = {}
    )
}
