package com.example.ptb.models

import com.google.gson.annotations.SerializedName

data class PengajuanData(

    @SerializedName("id_tugas_akhir")
    val idTugasAkhir: Int,

    @SerializedName("nama_pembimbing")
    val namaPembimbing: String,

    val judul: String,

    val mahasiswa: MahasiswaData

)
