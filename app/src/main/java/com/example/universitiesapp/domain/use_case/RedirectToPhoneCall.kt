package com.example.universitiesapp.domain.use_case

import android.content.Context
import android.content.Intent
import android.net.Uri
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class RedirectToPhoneCall @Inject constructor(
    @ApplicationContext private val context : Context
) {
    operator fun invoke(phoneNumber : String) {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse("tel:$phoneNumber")).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        context.startActivity(intent)
    }
}