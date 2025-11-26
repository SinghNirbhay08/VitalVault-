package com.nirbhay.vitalvault01.ui.doctor

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material.icons.filled.LocalHospital
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.SnippetFolder
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.nirbhay.vitalvault01.ui.theme.BrandBlack
import com.nirbhay.vitalvault01.ui.theme.BrandDarkGray
import com.nirbhay.vitalvault01.ui.theme.BrandLightBlue
import com.nirbhay.vitalvault01.ui.theme.BrandPrimary
import com.nirbhay.vitalvault01.ui.theme.BrandWhite
import com.nirbhay.vitalvault01.ui.theme.VitalVaultTheme

@Composable
fun DoctorHomeScreen(
    onScanQrClick: () -> Unit = {},
    onReportDetailClick: (String) -> Unit = {}
) {
    Scaffold(
        containerColor = BrandWhite,
        topBar = { DoctorTopBar() }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 20.dp),
            verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            // 1. Hero Overview Card
            item {
                OverviewCard()
            }

            // 2. Scan QR Button
            item {
                ScanQrButton(onClick = onScanQrClick)
            }

            // 3. Recent Reports Header
            item {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.Default.SnippetFolder,
                        contentDescription = null,
                        tint = BrandPrimary,
                        modifier = Modifier.size(28.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "Recent Reports",
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold,
                        color = BrandBlack
                    )
                }
            }

            // 4. Patient List Items (Mock Data matching design)
            item { PatientStatusCard("John Doe", "1234", "Active", onReportDetailClick) }
            item { PatientStatusCard("Jane Smith", "5678", "Expired", onReportDetailClick) }
            item { PatientStatusCard("Mike Ross", "9012", "Requested", onReportDetailClick) }

            item { Spacer(modifier = Modifier.height(20.dp)) } // Bottom padding
        }
    }
}

@Composable
fun DoctorTopBar() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp, vertical = 16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            // Logo
            Icon(
                imageVector = Icons.Default.LocalHospital,
                contentDescription = "Logo",
                tint = BrandPrimary,
                modifier = Modifier.size(32.dp)
            )
            Spacer(modifier = Modifier.width(12.dp))
            Column {
                Text(
                    text = "Hi John!",
                    style = MaterialTheme.typography.bodyMedium,
                    color = BrandDarkGray
                )
                Text(
                    text = "Good morning!",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold,
                    color = BrandBlack
                )
            }
        }

        // Notification Icon with Badge
        Box {
            Icon(
                imageVector = Icons.Default.Notifications,
                contentDescription = "Notifications",
                tint = BrandPrimary,
                modifier = Modifier.size(28.dp)
            )
            // Red Badge
            Box(
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .size(14.dp)
                    .background(Color.Red, CircleShape)
                    .border(1.dp, BrandWhite, CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Text("2", color = Color.White, fontSize = 9.sp, fontWeight = FontWeight.Bold)
            }
        }
    }
}

@Composable
fun OverviewCard() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(160.dp)
            .clip(RoundedCornerShape(20.dp))
            .background(
                Brush.horizontalGradient(
                    colors = listOf(BrandPrimary, Color(0xFF4CB8C4)) // Blue to Cyan gradient
                )
            )
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = "Today's Overview",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
                Spacer(modifier = Modifier.height(12.dp))
                OverviewText("Pending Access Requests: 3")
                OverviewText("Patients Viewed Today: 12")
                OverviewText("New Shared Reports: 5")
            }

            // Doctor Avatar Placeholder
            Box(
                modifier = Modifier
                    .size(80.dp)
                    .background(Color.White.copy(alpha = 0.2f), CircleShape)
                    .padding(4.dp)
                    .clip(CircleShape)
                    .background(BrandLightBlue),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Default.Person,
                    contentDescription = "Doctor",
                    tint = BrandPrimary,
                    modifier = Modifier.size(50.dp)
                )
            }
        }
    }
}

@Composable
fun OverviewText(text: String) {
    Text(
        text = text,
        style = MaterialTheme.typography.bodyMedium,
        color = Color.White,
        modifier = Modifier.padding(bottom = 4.dp)
    )
}

@Composable
fun ScanQrButton(onClick: () -> Unit) {
    Surface(
        onClick = onClick,
        modifier = Modifier.fillMaxWidth().height(60.dp),
        shape = RoundedCornerShape(12.dp),
        color = Color(0xFFF5F5F5), // Light Gray bg
        border = BorderStroke(1.dp, Color(0xFFE0E0E0))
    ) {
        Row(
            modifier = Modifier.fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            // Blue Plus Circle
            Box(
                modifier = Modifier
                    .size(32.dp)
                    .background(BrandPrimary, CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Icon(Icons.Default.Add, null, tint = Color.White)
            }
            Spacer(modifier = Modifier.width(12.dp))
            Text(
                text = "Scan QR Code",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                color = BrandBlack
            )
        }
    }
}

@Composable
fun PatientStatusCard(
    name: String,
    id: String,
    status: String,
    onClick: (String) -> Unit
) {
    // Determine Color based on Status
    val statusColor = when (status) {
        "Active" -> Color(0xFF4CAF50) // Green
        "Expired" -> Color(0xFF757575) // Gray/Red (Using Gray for expired typically)
        "Requested" -> BrandPrimary // Blue
        else -> BrandBlack
    }

    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = BrandLightBlue.copy(alpha = 0.5f)),
        elevation = CardDefaults.cardElevation(0.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                // Name & ID
                Column {
                    Text(
                        text = buildAnnotatedString {
                            append("Name: ")
                            withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                                append(name)
                            }
                        },
                        style = MaterialTheme.typography.bodyLarge,
                        color = BrandBlack
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = buildAnnotatedString {
                            append("Patient ID: ")
                            withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                                append(id)
                            }
                        },
                        style = MaterialTheme.typography.bodyMedium,
                        color = BrandBlack
                    )
                }

                // Status
                Text(
                    text = buildAnnotatedString {
                        append("Status: ")
                        withStyle(style = SpanStyle(color = statusColor, fontWeight = FontWeight.Bold)) {
                            append(status)
                        }
                    },
                    style = MaterialTheme.typography.bodyMedium,
                    color = BrandBlack
                )
            }

            Spacer(modifier = Modifier.height(12.dp))

            // Report Detail Button (Aligned End)
            Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.CenterEnd) {
                Surface(
                    onClick = { onClick(id) },
                    shape = RoundedCornerShape(20.dp),
                    border = BorderStroke(1.dp, BrandBlack),
                    color = Color.Transparent
                ) {
                    Row(
                        modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Report detail",
                            style = MaterialTheme.typography.bodySmall,
                            fontWeight = FontWeight.Bold
                        )
                        Icon(
                            imageVector = Icons.Default.ChevronRight,
                            contentDescription = null,
                            modifier = Modifier.size(16.dp)
                        )
                    }
                }
            }
        }
    }
}

