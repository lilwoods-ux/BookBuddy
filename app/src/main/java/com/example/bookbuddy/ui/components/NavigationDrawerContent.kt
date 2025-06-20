package com.example.bookbuddy.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import com.example.bookbuddy.R
import com.example.bookbuddy.ui.theme.Black
import com.example.bookbuddy.ui.theme.Purple500

@Composable
fun NavigationDrawerContent(
    onPremiumClick: () -> Unit,
    onLogoutClick: () -> Unit,
    onCloseDrawer: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth(0.75f)
            .background(Black)
            .padding(16.dp)
    ) {
        Text(
            text = "BookBuddy",
            style = MaterialTheme.typography.headlineMedium,
            color = Purple500,
            modifier = Modifier.padding(bottom = 16.dp)
        )
        DrawerItem(
            icon = ImageVector.vectorResource(R.drawable.ic_premium),
            text = "Upgrade to Premium",
            onClick = {
                onPremiumClick()
                onCloseDrawer()
            }
        )
        Spacer(modifier = Modifier.height(8.dp))
        DrawerItem(
            icon = ImageVector.vectorResource(R.drawable.ic_logout),
            text = "Log Out",
            onClick = {
                onLogoutClick()
                onCloseDrawer()
            }
        )
    }
}

@Composable
private fun DrawerItem(icon: ImageVector, text: String, onClick: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .padding(vertical = 12.dp)
    ) {
        Icon(
            imageVector = icon,
            contentDescription = text,
            tint = Purple500,
            modifier = Modifier.padding(end = 16.dp)
        )
        Text(
            text = text,
            style = MaterialTheme.typography.bodyLarge,
            color = Purple500
        )
    }
}