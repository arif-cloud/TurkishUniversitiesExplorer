package com.example.universitiesapp.presentation.home.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.universitiesapp.R
import com.example.universitiesapp.domain.model.University
import com.example.universitiesapp.presentation.root.screen.Screen
import com.example.universitiesapp.ui.theme.Orange
import com.example.universitiesapp.ui.theme.Typography
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

@Composable
fun UniversityItem(
    university: University,
    isExpanded: Boolean,
    isFavorite: Boolean,
    onClickExpand: () -> Unit,
    onClickFavoriteIcon: () -> Unit,
    onPhoneCall: () -> Unit,
    navController: NavController,
) {
    Card(
        shape = RoundedCornerShape(10.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primaryContainer),
        modifier = Modifier.clickable { if (!university.allFieldsEmpty()) onClickExpand() }
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(5.dp), modifier = Modifier.padding(5.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(5.dp),
                verticalAlignment = Alignment.CenterVertically,
                content = {
                    if (!university.allFieldsEmpty()) {
                        Icon(imageVector = if (isExpanded) ImageVector.vectorResource(id = R.drawable.minus_icon) else Icons.Default.Add,
                            contentDescription = "plus_icon",
                            tint = Orange
                        )
                    }
                    Text(
                        text = university.name ?: "",
                        style = Typography.titleSmall,
                        modifier = Modifier.weight(1F),
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                    Icon(
                        imageVector = if (isFavorite) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                        contentDescription = "favorite_icon",
                        modifier = Modifier.clickable { onClickFavoriteIcon() }
                    )
                }
            )
            if (isExpanded) {
                val universityDetailMap = university.toMap()
                val encodedUrl = URLEncoder.encode(university.website, StandardCharsets.UTF_8.toString())
                universityDetailMap.forEach { (key, value) ->
                    UniversityDetailItem(
                        title = key,
                        value = value,
                        onClickWebsiteItem = { navController.navigate("${Screen.WebView.route}/${university.name}/$encodedUrl") },
                        onClickPhoneItem = { onPhoneCall() }
                    )
                }
            }
        }
    }
}