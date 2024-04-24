package com.example.universitiesapp.domain.use_case

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.core.app.ActivityCompat
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class MakePhoneCall @Inject constructor(
    @ApplicationContext private val context: Context,
) {
    operator fun invoke(
        phoneNumber: String,
        phoneCallPermissionResultLauncher: ManagedActivityResultLauncher<String, Boolean>,
    ) {
        val basePhoneNumber = phoneNumber.split("-")[0]
        if (ActivityCompat.checkSelfPermission(
                context,
                Manifest.permission.CALL_PHONE
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            val action = Intent.ACTION_CALL
            val intent = Intent(
                action,
                Uri.parse("tel:$basePhoneNumber")
            ).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            context.startActivity(intent)
        } else {
            phoneCallPermissionResultLauncher.launch(
                Manifest.permission.CALL_PHONE
            )
        }
    }
}