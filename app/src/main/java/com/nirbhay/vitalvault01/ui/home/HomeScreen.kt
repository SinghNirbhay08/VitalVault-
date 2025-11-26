package com.nirbhay.vitalvault01.ui.home

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material.icons.filled.Folder
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.SnippetFolder
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
// CORRECT IMPORT: components (plural)
import com.nirbhay.vitalvault01.ui.component.PatientNavBar
import com.nirbhay.vitalvault01.ui.theme.*
import com.nirbhay.vitalvault01.ui.viewmodel.ReportViewModel

@Composable
fun HomeScreen(
    onUploadClick: () -> Unit = {},
    onViewRecordsClick: () -> Unit = {},
    onReportDetailClick: () -> Unit = {},
    onNavigateBottomBar: (String) -> Unit = {},
    viewModel: ReportViewModel = hiltViewModel()
) {
    // Safely collect state
    val reports by viewModel.reports.collectAsState(initial = emptyList())
    val reportCount = reports.size

    Scaffold(
        containerColor = BrandWhite,
        topBar = { HomeTopBar() },
        bottomBar = {
            PatientNavBar(
                currentRoute = "patient_home",
                onNavigate = onNavigateBottomBar
            )
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 20.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            // 1. Vault Card
            item {
                VaultHeroCard(count = reportCount)
            }

            // 2. Action Buttons
            item {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    ActionButton(
                        text = "Upload Report",
                        icon = Icons.Default.Add,
                        onClick = onUploadClick,
                        modifier = Modifier.weight(1f)
                    )
                    ActionButton(
                        text = "View Records",
                        icon = Icons.Default.Folder,
                        onClick = onViewRecordsClick,
                        modifier = Modifier.weight(1f)
                    )
                }
            }

            // 3. Recent Header
            item {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(Icons.Default.SnippetFolder, null, tint = BrandPrimary, modifier = Modifier.size(24.dp))
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "Recent Reports",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold
                    )
                }
            }

            // 4. Placeholder Items
            item { RecentReportItem(title = "Blood Test", date = "12 April 2025", onDetailClick = onReportDetailClick) }
            item { RecentReportItem(title = "X-Ray Scan", date = "10 April 2025", onDetailClick = onReportDetailClick) }

            // 5. Access Section
            item { DoctorsAccessSection() }

            item { Spacer(modifier = Modifier.height(20.dp)) }
        }
    }
}

// ... (Rest of your helper Composables: HomeTopBar, VaultHeroCard, etc. Keep them below) ...
@Composable
fun HomeTopBar() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp, vertical = 16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(
                imageVector = Icons.Default.Add,
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

        Box {
            Icon(Icons.Default.Notifications, null, tint = BrandPrimary, modifier = Modifier.size(28.dp))
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
fun VaultHeroCard(count: Int) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(140.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(
                Brush.horizontalGradient(
                    colors = listOf(BrandPrimary, Color(0xFF4CB8C4))
                )
            )
            .padding(20.dp)
    ) {
        Column(modifier = Modifier.align(Alignment.TopStart)) {
            Text(
                text = "Your Medical Vault",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
            Spacer(modifier = Modifier.height(8.dp))

            // DYNAMIC COUNT
            Text(
                text = "$count records securely stored",
                style = MaterialTheme.typography.bodyMedium,
                color = Color.White,
                fontWeight = FontWeight.Medium
            )
        }

        Text(
            text = "Updated just now",
            style = MaterialTheme.typography.labelSmall,
            color = Color.White.copy(alpha = 0.8f),
            modifier = Modifier.align(Alignment.BottomEnd)
        )
    }
}

@Composable
fun ActionButton(
    text: String,
    icon: ImageVector,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Surface(
        onClick = onClick,
        modifier = modifier.height(56.dp),
        shape = RoundedCornerShape(12.dp),
        border = BorderStroke(1.dp, Color(0xFFEEEEEE)),
        color = BrandWhite,
        shadowElevation = 2.dp
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.padding(horizontal = 8.dp)
        ) {
            Box(
                modifier = Modifier
                    .size(24.dp)
                    .background(BrandPrimary, CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Icon(icon, null, tint = Color.White, modifier = Modifier.size(16.dp))
            }
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = text,
                style = MaterialTheme.typography.labelLarge,
                fontWeight = FontWeight.Bold,
                color = BrandBlack
            )
        }
    }
}

@Composable
fun RecentReportItem(title: String, date: String, onDetailClick: () -> Unit) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = BrandLightBlue.copy(alpha = 0.4f)),
        elevation = CardDefaults.cardElevation(0.dp)
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column {
                Text(
                    text = buildAnnotatedString {
                        append("Title: ")
                        withStyle(SpanStyle(fontWeight = FontWeight.Bold)) { append("\"$title\"") }
                    },
                    style = MaterialTheme.typography.bodyMedium,
                    color = BrandBlack
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "Date: $date",
                    style = MaterialTheme.typography.bodyMedium,
                    color = BrandBlack
                )
            }

            Surface(
                onClick = onDetailClick,
                shape = RoundedCornerShape(20.dp),
                border = BorderStroke(1.dp, BrandDarkGray),
                color = Color.Transparent
            ) {
                Row(
                    modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text("Report detail", style = MaterialTheme.typography.labelSmall, color = BrandBlack)
                    Icon(Icons.Default.ChevronRight, null, modifier = Modifier.size(16.dp), tint = BrandBlack)
                }
            }
        }
    }
}

@Composable
fun DoctorsAccessSection() {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = BrandWhite),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = "Doctors With Current Access: 1",
                style = MaterialTheme.typography.titleSmall,
                fontWeight = FontWeight.Bold,
                color = BrandBlack
            )
            Spacer(modifier = Modifier.height(12.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .border(1.dp, Color(0xFFEEEEEE), RoundedCornerShape(12.dp))
                    .padding(12.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .size(40.dp)
                        .background(BrandLightBlue, CircleShape),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(Icons.Default.Person, null, tint = BrandPrimary)
                }

                Spacer(modifier = Modifier.width(12.dp))

                Column(modifier = Modifier.weight(1f)) {
                    Text("Dr. Mehta", fontWeight = FontWeight.Bold, fontSize = 14.sp)
                    Text("Time remaining: 12:43", fontSize = 12.sp, color = BrandDarkGray)
                }

                OutlinedButton(
                    onClick = {},
                    shape = RoundedCornerShape(8.dp),
                    contentPadding = PaddingValues(horizontal = 8.dp),
                    modifier = Modifier.height(32.dp)
                ) {
                    Text("Remove access", fontSize = 10.sp, color = BrandBlack)
                }
            }
        }
    }
}