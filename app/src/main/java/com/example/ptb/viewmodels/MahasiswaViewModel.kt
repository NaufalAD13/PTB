package com.example.ptb.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ptb.data.TokenDataStore
import com.example.ptb.models.MahasiswaData
import com.example.ptb.models.PengajuanData
import com.example.ptb.models.UpdateStatusPengajuanRequest
import com.example.ptb.service.MahasiswaService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.toRequestBody
import javax.inject.Inject

@HiltViewModel
class MahasiswaViewModel @Inject constructor(
    private val tokenDataStore: TokenDataStore,
    private val mahasiswaService: MahasiswaService
) : ViewModel() {

    private val _mahasiswaList = MutableStateFlow<List<MahasiswaData>>(emptyList())
    val mahasiswaList: StateFlow<List<MahasiswaData>> = _mahasiswaList

    private val _pengajuan = MutableStateFlow<PengajuanData?>(null)
    val pengajuan: StateFlow<PengajuanData?> = _pengajuan

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> = _errorMessage

    fun fetchMahasiswaList() {
        viewModelScope.launch {
            val token = tokenDataStore.accessToken.firstOrNull()
            if (!token.isNullOrEmpty()) {
                try {
                    val response = mahasiswaService.getMahasiswaList("Bearer $token")
                    _mahasiswaList.value = response.data
                } catch (e: Exception) {
                    e.printStackTrace()
                    _errorMessage.value = "Data mahasiswa tidak ditemukan"
                }
            } else {
                _errorMessage.value = "Token tidak ditemukan."
            }
        }
    }

    fun getPengajuanMahasiwa(id: Int) {
        viewModelScope.launch {
            val token = tokenDataStore.accessToken.firstOrNull()
            if (!token.isNullOrEmpty()) {
                try {
                    val response = mahasiswaService.getPengajuanMahasiswa("Bearer $token", id)
                    if (response.isSuccessful) {
                        val pengajuanResponse = response.body()
                        if (pengajuanResponse?.data != null) {
                            _pengajuan.value = pengajuanResponse.data
                        } else {
                            _errorMessage.value = "Data pengajuan tidak ditemukan."
                        }
                    } else {
                        _errorMessage.value = "Gagal mengambil data pengajuan: ${response.message()}"
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                    _errorMessage.value = "Terjadi kesalahan: ${e.message}"
                }
            } else {
                _errorMessage.value = "Token tidak ditemukan"
            }
        }
    }

    fun updateStatusPengajuan(
        id: Int,
        status: String = "tolak",
        onSucces: () -> Unit,
        onError: (String) -> Unit
    ) {
        viewModelScope.launch {
            val token = tokenDataStore.accessToken.firstOrNull()
            if (!token.isNullOrEmpty()) {
                try {
                    val request = UpdateStatusPengajuanRequest(
                        status = status
                    )

                    val response = mahasiswaService.updateStatusPengajuan("Bearer $token", id, request)

                    if (response.isSuccessful) {
                        onSucces()
                    } else {
                        onError("Gagal mengupdate status: ${response.message()}")
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                    onError("Terjadi kesalahan: ${e.message}")
                }
            } else {
                _errorMessage.value = "Token tidak ditemukan"
            }
        }
    }

    fun addSeminar(
        idTugasAkhir: String,
        namaPenguji: String,
        tanggalSeminar: String,
        komentar: String,
        tempat: String,
        surat: MultipartBody.Part? = null,
        onSuccess: () -> Unit,
        onError: (String) -> Unit
    ) {
        viewModelScope.launch {
            val token = tokenDataStore.accessToken.firstOrNull()
            if (!token.isNullOrEmpty()) {
                try {
                    val idTugasAkhirRequestBody = idTugasAkhir.toRequestBody(MultipartBody.FORM)
                    val namaPengujiRequestBody = namaPenguji.toRequestBody(MultipartBody.FORM)
                    val tanggalSeminarRequestBody = tanggalSeminar.toRequestBody(MultipartBody.FORM)
                    val statusSeminarRequestBody = "disetujui".toRequestBody(MultipartBody.FORM)
                    val tempatRequestBody = tempat.toRequestBody(MultipartBody.FORM)
                    val komentarRequestBody = komentar.toRequestBody(MultipartBody.FORM)

                    mahasiswaService.addSeminar(
                        token = "Bearer $token",
                        idTugasAkhir = idTugasAkhirRequestBody,
                        namaPenguji = namaPengujiRequestBody,
                        tanggalSeminar = tanggalSeminarRequestBody,
                        statusSeminar = statusSeminarRequestBody,
                        tempatSeminar = tempatRequestBody,
                        komentar = komentarRequestBody,
                        surat = surat
                    )

                    onSuccess()

                } catch (e:Exception) {
                    e.printStackTrace()
                    onError("Terjadi kesalahan: ${e.message}")
                }
            } else {
                onError("Token tidak ditemukan")
            }
        }
    }

}