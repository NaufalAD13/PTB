package com.example.ptb.service

import com.example.ptb.models.MahasiswaResponse
import com.example.ptb.models.PengajuanResponse
import com.example.ptb.models.UpdateStatusPengajuanRequest
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Part
import retrofit2.http.Path

interface MahasiswaService {

    @GET("mahasiswa/namaMahasiswa/status")
    suspend fun getMahasiswaList(
        @Header("Authorization") token: String
    ) : MahasiswaResponse

    @GET("mahasiswa/namaMahasiswa/{id}/pengajuan")
    suspend fun getPengajuanMahasiswa(
        @Header("Authorization") toker: String,
        @Path("id") id: Int
    ) : Response<PengajuanResponse>

    @PUT("mahasiswa/pengajuan/{id}/status")
    suspend fun updateStatusPengajuan(
        @Header("Authorization") token: String,
        @Path("id") id: Int,
        @Body request: UpdateStatusPengajuanRequest
    ) : Response<Unit>

    @Multipart
    @POST("mahasiswa/seminar")
    suspend fun addSeminar(
        @Header("Authorization") token: String,
        @Part("id_tugas_akhir") idTugasAkhir: RequestBody,
        @Part("nama_penguji") namaPenguji: RequestBody,
        @Part("tanggal_seminar") tanggalSeminar: RequestBody,
        @Part("status_seminar") statusSeminar: RequestBody,
        @Part("tempat_seminar") tempatSeminar: RequestBody,
        @Part("komentar") komentar: RequestBody,
        @Part surat: MultipartBody.Part? = null
    )

}