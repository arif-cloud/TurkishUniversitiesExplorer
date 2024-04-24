package com.example.universitiesapp.presentation.home.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import com.example.universitiesapp.R
import com.example.universitiesapp.ui.theme.Typography

@Composable
fun UniversityDetailItem(
    title : String,
    value : String?,
    onClickWebsiteItem : () -> Unit,
    onClickPhoneItem : () -> Unit
) {
    val iconResource = when (title) {
        "phone" -> R.drawable.phone_icon
        "fax" -> R.drawable.fax_icon
        "website" -> R.drawable.website_icon
        "email" -> R.drawable.email_icon
        "address" -> R.drawable.address_icon
        "rector" -> R.drawable.rector_icon
        else -> R.drawable.default_icon
    }
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(4.dp),
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                if (title == "website")
                    onClickWebsiteItem()
                if (title == "phone") {
                    onClickPhoneItem()
                }
            }
    ) {
        Icon(
            imageVector = ImageVector.vectorResource(id = iconResource),
            contentDescription = "phone_icon"
        )
        Text(
            text = "${title.replaceFirstChar { it.uppercase() }} : $value",
            style = Typography.bodyMedium
        )
    }
}