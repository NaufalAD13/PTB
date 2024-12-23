package com.example.ptb.models

import com.google.gson.annotations.SerializedName

data class MahasiswaData(

    val id: Int,

    val nama: String,

    val nim: String,

    @SerializedName("jenis_kelamin")
    val jenisKelamin: String,

    val foto: String?

)
