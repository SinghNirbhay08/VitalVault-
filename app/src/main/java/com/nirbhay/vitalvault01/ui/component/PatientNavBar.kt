package com.nirbhay.vitalvault01.ui.component



import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Folder
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.QrCode // Changed to standard QrCode for visibility
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.nirbhay.vitalvault01.ui.theme.BrandDarkGray
import com.nirbhay.vitalvault01.ui.theme.BrandLightBlue
import com.nirbhay.vitalvault01.ui.theme.BrandPrimary
import com.nirbhay.vitalvault01.ui.theme.BrandWhite

@Composable
fun PatientNavBar(
    currentRoute: String,
    onNavigate: (String) -> Unit
) {
    NavigationBar(
        containerColor = BrandWhite,
        tonalElevation = 8.dp
    ) {
        // 1. Home
        NavigationBarItem(
            selected = currentRoute == "patient_home",
            onClick = { onNavigate("patient_home") },
            icon = { Icon(Icons.Default.Home, contentDescription = "Home") },
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = BrandPrimary,
                selectedTextColor = BrandPrimary,
                indicatorColor = BrandLightBlue,
                unselectedIconColor = BrandDarkGray
            ),
            label = { Text("Home") }
        )

        // 2. Records
        NavigationBarItem(
            selected = currentRoute == "patient_reports",
            onClick = { onNavigate("patient_reports") },
            icon = { Icon(Icons.Default.Folder, contentDescription = "Records") },
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = BrandPrimary,
                selectedTextColor = BrandPrimary,
                indicatorColor = BrandLightBlue,
                unselectedIconColor = BrandDarkGray
            ),
            label = { Text("Records") }
        )

        // 3. Share (QR)
        NavigationBarItem(
            selected = currentRoute == "patient_share",
            onClick = { onNavigate("patient_share") },
            icon = { Icon(Icons.Default.QrCode, contentDescription = "Share") },
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = BrandPrimary,
                selectedTextColor = BrandPrimary,
                indicatorColor = BrandLightBlue,
                unselectedIconColor = BrandDarkGray
            ),
            label = { Text("Share") }
        )

        // 4. Profile
        NavigationBarItem(
            selected = currentRoute == "patient_profile",
            onClick = { onNavigate("patient_profile") },
            icon = { Icon(Icons.Default.Person, contentDescription = "Profile") },
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = BrandPrimary,
                selectedTextColor = BrandPrimary,
                indicatorColor = BrandLightBlue,
                unselectedIconColor = BrandDarkGray
            ),
            label = { Text("Profile") }
        )
    }
}