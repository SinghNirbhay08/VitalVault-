package com.nirbhay.vitalvault01.ui.doctor



import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Logout
import androidx.compose.material.icons.filled.Badge
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material.icons.filled.LocalHospital
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Schedule
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.nirbhay.vitalvault01.ui.theme.BrandBlack
import com.nirbhay.vitalvault01.ui.theme.BrandDarkGray
import com.nirbhay.vitalvault01.ui.theme.BrandLightBlue
import com.nirbhay.vitalvault01.ui.theme.BrandPrimary
import com.nirbhay.vitalvault01.ui.theme.BrandWhite
import com.nirbhay.vitalvault01.ui.theme.VitalVaultTheme

@Composable
fun DoctorProfileScreen(
    onLogoutClick: () -> Unit = {}
) {
    Scaffold(
        containerColor = BrandWhite
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // 1. Doctor Header
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(240.dp)
                    .background(BrandPrimary),
                contentAlignment = Alignment.Center
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Box(
                        modifier = Modifier
                            .size(100.dp)
                            .clip(CircleShape)
                            .background(BrandWhite),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = Icons.Default.Person,
                            contentDescription = null,
                            tint = BrandPrimary,
                            modifier = Modifier.size(60.dp)
                        )
                    }
                    Spacer(modifier = Modifier.height(12.dp))
                    Text(
                        text = "Dr. Strange",
                        style = MaterialTheme.typography.headlineMedium,
                        fontWeight = FontWeight.Bold,
                        color = BrandWhite
                    )
                    Text(
                        text = "Cardiologist",
                        style = MaterialTheme.typography.bodyLarge,
                        color = BrandWhite.copy(alpha = 0.8f)
                    )
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            // 2. Info Card
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = BrandLightBlue.copy(alpha = 0.2f)),
                elevation = CardDefaults.cardElevation(0.dp)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    DoctorInfoRow(Icons.Default.Badge, "License ID", "MED-883920")
                    Divider(modifier = Modifier.padding(vertical = 12.dp), color = BrandWhite)
                    DoctorInfoRow(Icons.Default.LocalHospital, "Hospital", "City General Hospital")
                }
            }

            Spacer(modifier = Modifier.height(32.dp))

            // 3. Menu Options
            Column(modifier = Modifier.padding(horizontal = 24.dp)) {
                Text(
                    text = "Practice Settings",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    color = BrandBlack,
                    modifier = Modifier.padding(bottom = 12.dp)
                )

                DoctorMenuItem(icon = Icons.Default.Schedule, title = "Manage Availability")
                DoctorMenuItem(icon = Icons.Default.Settings, title = "Account Settings")

                Spacer(modifier = Modifier.height(32.dp))

                // Logout Button
                OutlinedButton(
                    onClick = onLogoutClick,
                    modifier = Modifier.fillMaxWidth().height(50.dp),
                    shape = RoundedCornerShape(12.dp),
                    colors = ButtonDefaults.outlinedButtonColors(contentColor = Color.Red),
                    border = BorderStroke(1.dp, Color.Red)
                ) {
                    Icon(Icons.AutoMirrored.Filled.Logout, null)
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("Log Out")
                }
            }
        }
    }
}

@Composable
fun DoctorInfoRow(icon: ImageVector, label: String, value: String) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Icon(icon, null, tint = BrandPrimary, modifier = Modifier.size(24.dp))
        Spacer(modifier = Modifier.width(16.dp))
        Column {
            Text(text = label, style = MaterialTheme.typography.labelSmall, color = BrandDarkGray)
            Text(text = value, style = MaterialTheme.typography.bodyLarge, fontWeight = FontWeight.SemiBold, color = BrandBlack)
        }
    }
}

@Composable
fun DoctorMenuItem(icon: ImageVector, title: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(icon, null, tint = BrandBlack, modifier = Modifier.size(24.dp))
        Spacer(modifier = Modifier.width(16.dp))
        Text(title, style = MaterialTheme.typography.bodyLarge, color = BrandBlack, modifier = Modifier.weight(1f))
        Icon(Icons.Default.ChevronRight, null, tint = BrandDarkGray)
    }
}

@Preview
@Composable
fun PreviewDoctorProfile() {
    VitalVaultTheme {
        DoctorProfileScreen()
    }
}