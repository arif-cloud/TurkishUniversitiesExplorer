package com.example.universitiesapp.presentation.root

import android.widget.Toast
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

@Composable
fun permissionResultLauncher(): ManagedActivityResultLauncher<String, Boolean> {
    val context = LocalContext.current
    return rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission(),
        onResult = { isGranted ->
            Toast.makeText(context, "Call phone permission : $isGranted", Toast.LENGTH_SHORT).show()
        }
    )
}