package com.nirbhay.vitalvault01.ui.share



import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ContentCopy
import androidx.compose.material.icons.filled.Download
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.QrCode2
import androidx.compose.material.icons.filled.Share
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
import androidx.compose.ui.unit.sp
import com.nirbhay.vitalvault01.ui.component.PatientNavBar
import com.nirbhay.vitalvault01.ui.theme.BrandBlack
import com.nirbhay.vitalvault01.ui.theme.BrandDarkGray
import com.nirbhay.vitalvault01.ui.theme.BrandLightBlue
import com.nirbhay.vitalvault01.ui.theme.BrandPrimary
import com.nirbhay.vitalvault01.ui.theme.BrandWhite
import com.nirbhay.vitalvault01.ui.theme.VitalVaultTheme

@Composable
fun ShareScreen(
    onBackClick: () -> Unit = {},
    onNavigateBottomBar: (String) -> Unit = {}


) {
    Scaffold(
        containerColor = Color(0xFFF8F9FA),
        bottomBar = {
            PatientNavBar(
                currentRoute = "patient_share",
                onNavigate = onNavigateBottomBar
            )
        }
        // Slightly gray background to make white cards pop
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .verticalScroll(rememberScrollState())
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // 1. Custom Header
            ShareHeader(onBackClick)

            Spacer(modifier = Modifier.height(32.dp))

            // 2. QR Code Section
            Icon(
                imageVector = Icons.Default.QrCode2,
                contentDescription = "QR Code",
                modifier = Modifier.size(200.dp),
                tint = BrandBlack
            )

            Spacer(modifier = Modifier.height(32.dp))

            // 3. Action Buttons (Copy, Share, Download)
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                ActionButton(icon = Icons.Default.ContentCopy, label = "Copy link")
                ActionButton(icon = Icons.Default.Share, label = "Share")
                ActionButton(icon = Icons.Default.Download, label = "Download")
            }

            Spacer(modifier = Modifier.height(32.dp))

            // 4. Pending Approvals Section
            AccessContainer(title = "Pending Approvals") {
                DoctorRequestRow(name = "Dr. Mehta", onClick = {})
                Divider(color = Color(0xFFEEEEEE))
                DoctorRequestRow(name = "Dr. Sharma", onClick = {})
            }

            Spacer(modifier = Modifier.height(24.dp))

            // 5. Doctors with Access Section
            AccessContainer(title = "Doctors with Access") {
                DoctorAccessRow(name = "Dr. Verma", timeRemaining = "1 day", onClick = {})
                Divider(color = Color(0xFFEEEEEE))
                DoctorAccessRow(name = "Dr. Reddy", timeRemaining = "4 hours", onClick = {})
            }

            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}

@Composable
fun ShareHeader(onBackClick: () -> Unit) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Surface(
            onClick = onBackClick,
            shape = RoundedCornerShape(12.dp),
            border = BorderStroke(1.dp, Color(0xFFE0E0E0)),
            color = BrandWhite,
            modifier = Modifier.size(48.dp)
        ) {
            Box(contentAlignment = Alignment.Center) {
                Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back", tint = BrandBlack)
            }
        }
        Spacer(modifier = Modifier.width(16.dp))
        Text(
            text = "Share Your Records",
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold,
            color = BrandBlack
        )
    }
}

@Composable
fun ActionButton(icon: ImageVector, label: String, onClick: () -> Unit = {}) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Surface(
            onClick = onClick,
            shape = CircleShape,
            border = BorderStroke(1.dp, Color(0xFFE0E0E0)),
            color = BrandWhite,
            modifier = Modifier.size(60.dp)
        ) {
            Box(contentAlignment = Alignment.Center) {
                Icon(icon, contentDescription = label, tint = BrandDarkGray, modifier = Modifier.size(28.dp))
            }
        }
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = label, style = MaterialTheme.typography.bodySmall, color = BrandDarkGray)
    }
}

@Composable
fun AccessContainer(title: String, content: @Composable ColumnScope.() -> Unit) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = BrandWhite),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(modifier = Modifier.padding(20.dp)) {
            Text(
                text = title,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                color = BrandBlack,
                modifier = Modifier.padding(bottom = 16.dp)
            )
            content()
        }
    }
}

@Composable
fun DoctorAvatar() {
    Box(
        modifier = Modifier
            .size(48.dp)
            .clip(CircleShape)
            .background(BrandLightBlue),
        contentAlignment = Alignment.Center
    ) {
        Icon(Icons.Default.Person, null, tint = BrandPrimary)
    }
}

// Row for "Pending Approvals"
@Composable
fun DoctorRequestRow(name: String, onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        DoctorAvatar()
        Spacer(modifier = Modifier.width(12.dp))
        Text(text = name, style = MaterialTheme.typography.bodyLarge, fontWeight = FontWeight.Medium, color = BrandBlack, modifier = Modifier.weight(1f))
        OutlinedButton(
            onClick = onClick,
            shape = RoundedCornerShape(50),
            border = BorderStroke(1.dp, BrandDarkGray),
            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 0.dp)
        ) {
            Text("Grant Access", color = BrandDarkGray, fontSize = 12.sp)
        }
    }
}

// Row for "Doctors with Access"
@Composable
fun DoctorAccessRow(name: String, timeRemaining: String, onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        DoctorAvatar()
        Spacer(modifier = Modifier.width(12.dp))
        Column(modifier = Modifier.weight(1f)) {
            Text(text = name, style = MaterialTheme.typography.bodyLarge, fontWeight = FontWeight.Medium, color = BrandBlack)
            Text(text = "Time Remaining: $timeRemaining", style = MaterialTheme.typography.bodySmall, color = BrandDarkGray)
        }
        OutlinedButton(
            onClick = onClick,
            shape = RoundedCornerShape(50),
            border = BorderStroke(1.dp, BrandDarkGray),
            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 0.dp)
        ) {
            Text("Remove Access", color = BrandDarkGray, fontSize = 12.sp)
        }
    }
}

