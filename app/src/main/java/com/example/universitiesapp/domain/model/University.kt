package com.example.universitiesapp.domain.model


import com.google.gson.annotations.SerializedName

data class University(
    @SerializedName("adress")
    val address: String?,
    @SerializedName("email")
    val email: String?,
    @SerializedName("fax")
    val fax: String?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("phone")
    val phone: String?,
    @SerializedName("rector")
    val rector: String?,
    @SerializedName("website")
    val website: String?
) {
    fun allFieldsEmpty(): Boolean {
        return phone == "-" &&
                fax == "-" &&
                website == "-" &&
                email == "-" &&
                address == "-" &&
                rector == "-"
    }
    fun toMap(): Map<String, String?> {
        return mapOf(
            "phone" to phone,
            "fax" to fax,
            "website" to website,
            "email" to email,
            "address" to address,
            "rector" to rector
        )
    }
}