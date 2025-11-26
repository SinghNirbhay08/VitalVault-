package com.nirbhay.vitalvault01.ui.home



import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Logout
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Help
import androidx.compose.material.icons.filled.History
import androidx.compose.material.icons.filled.Person
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
import com.nirbhay.vitalvault01.ui.component.PatientNavBar
import com.nirbhay.vitalvault01.ui.theme.BrandBlack
import com.nirbhay.vitalvault01.ui.theme.BrandDarkGray
import com.nirbhay.vitalvault01.ui.theme.BrandLightBlue
import com.nirbhay.vitalvault01.ui.theme.BrandPrimary
import com.nirbhay.vitalvault01.ui.theme.BrandWhite
import com.nirbhay.vitalvault01.ui.theme.VitalVaultTheme

@Composable
fun PatientProfileScreen(
    onLogoutClick: () -> Unit = {},
    onNavigateBottomBar: (String) -> Unit = {}
) {
    Scaffold(
        containerColor = BrandWhite,
        bottomBar = { PatientNavBar(
            currentRoute = "patient_share",
            onNavigate = onNavigateBottomBar
        ) } // Reusing the bottom bar for consistency
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // 1. Header Section with Avatar
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(220.dp)
                    .background(
                        color = BrandLightBlue.copy(alpha = 0.3f),
                        shape = RoundedCornerShape(bottomStart = 32.dp, bottomEnd = 32.dp)
                    ),
                contentAlignment = Alignment.Center
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Box(
                        modifier = Modifier
                            .size(100.dp)
                            .clip(CircleShape)
                            .background(BrandPrimary),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = Icons.Default.Person,
                            contentDescription = null,
                            tint = BrandWhite,
                            modifier = Modifier.size(60.dp)
                        )
                    }
                    Spacer(modifier = Modifier.height(12.dp))
                    Text(
                        text = "John Doe",
                        style = MaterialTheme.typography.headlineMedium,
                        fontWeight = FontWeight.Bold,
                        color = BrandPrimary
                    )
                    Text(
                        text = "Patient ID: 902184",
                        style = MaterialTheme.typography.bodyMedium,
                        color = BrandDarkGray
                    )
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            // 2. Health Stats Row
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                StatItem(label = "Age", value = "28")
                StatItem(label = "Blood", value = "O+")
                StatItem(label = "Weight", value = "72kg")
            }

            Spacer(modifier = Modifier.height(32.dp))

            // 3. Menu Options
            Column(modifier = Modifier.padding(horizontal = 24.dp)) {
                Text(
                    text = "Account",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    color = BrandBlack,
                    modifier = Modifier.padding(bottom = 12.dp)
                )

                ProfileMenuItem(icon = Icons.Default.Edit, title = "Edit Profile")
                ProfileMenuItem(icon = Icons.Default.History, title = "Medical History")
                ProfileMenuItem(icon = Icons.Default.Settings, title = "Settings")
                ProfileMenuItem(icon = Icons.Default.Help, title = "Help & Support")

                Spacer(modifier = Modifier.height(24.dp))

                // Logout Button
                Button(
                    onClick = onLogoutClick,
                    modifier = Modifier.fillMaxWidth().height(50.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFFEBEE)),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Icon(Icons.AutoMirrored.Filled.Logout, null, tint = Color.Red)
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("Logout", color = Color.Red, fontWeight = FontWeight.Bold)
                }
            }

            Spacer(modifier = Modifier.height(30.dp))
        }
    }
}

@Composable
fun StatItem(label: String, value: String) {
    Card(
        colors = CardDefaults.cardColors(containerColor = BrandWhite),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        modifier = Modifier.size(width = 100.dp, height = 80.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = value, style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold, color = BrandPrimary)
            Text(text = label, style = MaterialTheme.typography.bodySmall, color = BrandDarkGray)
        }
    }
}

@Composable
fun ProfileMenuItem(icon: ImageVector, title: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(40.dp)
                .background(BrandLightBlue.copy(alpha = 0.2f), CircleShape),
            contentAlignment = Alignment.Center
        ) {
            Icon(icon, null, tint = BrandPrimary, modifier = Modifier.size(20.dp))
        }
        Spacer(modifier = Modifier.width(16.dp))
        Text(title, style = MaterialTheme.typography.bodyLarge, color = BrandBlack, modifier = Modifier.weight(1f))
        Icon(Icons.Default.ChevronRight, null, tint = BrandDarkGray)
    }
}

@Preview
@Composable
fun PreviewPatientProfile() {
    VitalVaultTheme {
        PatientProfileScreen()
    }
}