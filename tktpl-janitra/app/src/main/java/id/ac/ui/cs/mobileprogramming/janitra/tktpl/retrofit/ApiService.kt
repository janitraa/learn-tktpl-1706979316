package id.ac.ui.cs.mobileprogramming.janitra.tktpl.retrofit

import id.ac.ui.cs.mobileprogramming.janitra.tktpl.model.ResponseModel
import id.ac.ui.cs.mobileprogramming.janitra.tktpl.model.WifiModel
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {
    @POST("/")
    suspend fun submitList(@Body wifiList: List<WifiModel>): Response<ResponseModel>
}