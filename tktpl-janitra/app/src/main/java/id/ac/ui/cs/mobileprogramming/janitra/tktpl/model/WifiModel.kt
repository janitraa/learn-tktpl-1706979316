package id.ac.ui.cs.mobileprogramming.janitra.tktpl.model

import com.google.gson.annotations.SerializedName

class WifiModel {
    @SerializedName("name")
    var name: String? = null
}

class ResponseModel {
    @SerializedName("success")
    val response: Boolean? = null
}