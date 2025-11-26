package com.nirbhay.vitalvault01.ui.home




import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.CalendarToday
import androidx.compose.material.icons.filled.Description
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.nirbhay.vitalvault01.data.ReportEntity
import com.nirbhay.vitalvault01.ui.component.PatientNavBar
import com.nirbhay.vitalvault01.ui.theme.*
import com.nirbhay.vitalvault01.ui.viewmodel.ReportViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ReportsListScreen(
    onBack: () -> Unit = {},
    onNavigateBottomBar: (String) -> Unit = {},
    // Inject ViewModel
    viewModel: ReportViewModel = hiltViewModel()
) {
    var showAddSheet by remember { mutableStateOf(false) }
    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)

    // Collect Data from DB
    val reportList by viewModel.reports.collectAsState()

    Scaffold(
        containerColor = BrandWhite,
        topBar = {
            TopAppBar(
                title = { Text("Medical Documents", fontWeight = FontWeight.Bold, style = MaterialTheme.typography.titleMedium) },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Surface(
                            shape = RoundedCornerShape(12.dp),
                            border = BorderStroke(1.dp, Color(0xFFE0E0E0)),
                            modifier = Modifier.size(40.dp)
                        ) {
                            Box(contentAlignment = Alignment.Center) {
                                Icon(Icons.AutoMirrored.Filled.ArrowBack, "Back", modifier = Modifier.size(20.dp))
                            }
                        }
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = BrandWhite)
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { showAddSheet = true },
                containerColor = BrandPrimary,
                shape = RoundedCornerShape(16.dp)
            ) {
                Icon(Icons.Default.Add, contentDescription = "Add Report", tint = Color.White)
            }
        },
        bottomBar = {
            PatientNavBar(currentRoute = "patient_reports", onNavigate = onNavigateBottomBar)
        }
    ) { paddingValues ->

        if (reportList.isEmpty()) {
            // Empty State
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text("No reports yet. Click + to add.", color = BrandDarkGray)
            }
        } else {
            // Real Grid
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                contentPadding = PaddingValues(
                    top = paddingValues.calculateTopPadding() + 16.dp,
                    bottom = 100.dp,
                    start = 20.dp,
                    end = 20.dp
                ),
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                verticalArrangement = Arrangement.spacedBy(24.dp)
            ) {
                items(reportList) { report ->
                    ReportGridItem(report)
                }
            }
        }

        if (showAddSheet) {
            ModalBottomSheet(
                onDismissRequest = { showAddSheet = false },
                sheetState = sheetState,
                containerColor = BrandWhite
            ) {
                AddReportContent(
                    onSave = { title, doctor, date ->
                        viewModel.addReport(title, doctor, date) // SAVE TO DB
                        showAddSheet = false
                    }
                )
            }
        }
    }
}

@Composable
fun ReportGridItem(report: ReportEntity) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1f)
                .clip(RoundedCornerShape(16.dp))
                .background(BrandLightBlue.copy(alpha = 0.3f))
                .border(1.dp, Color(0xFFEEEEEE), RoundedCornerShape(16.dp)),
            contentAlignment = Alignment.Center
        ) {
            Icon(Icons.Default.Description, null, tint = BrandPrimary.copy(alpha = 0.5f), modifier = Modifier.size(48.dp))
        }
        Spacer(modifier = Modifier.height(12.dp))
        Text(text = report.title, style = MaterialTheme.typography.bodyMedium, fontWeight = FontWeight.Bold, color = BrandBlack, maxLines = 1)
        Text(text = report.dateOfConsultancy, style = MaterialTheme.typography.bodySmall, color = BrandDarkGray)
    }
}

@Composable
fun AddReportContent(onSave: (String, String, String) -> Unit) {
    // State for inputs
    var title by remember { mutableStateOf("") }
    var doctor by remember { mutableStateOf("") }
    var date by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp)
            .padding(bottom = 32.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Add New Report", style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold, color = BrandBlack, modifier = Modifier.padding(bottom = 24.dp))

        // Inputs connected to state
        StyledTextFieldInput(label = "Report Title", value = title, onValueChange = { title = it })
        Spacer(modifier = Modifier.height(16.dp))
        StyledTextFieldInput(label = "Consulted BY", value = doctor, onValueChange = { doctor = it })
        Spacer(modifier = Modifier.height(16.dp))
        StyledTextFieldInput(label = "Date (DD/MM/YYYY)", value = date, onValueChange = { date = it }, trailingIcon = Icons.Default.CalendarToday)

        Spacer(modifier = Modifier.height(32.dp))

        Button(
            onClick = {
                if(title.isNotEmpty() && date.isNotEmpty()) {
                    onSave(title, doctor, date)
                }
            },
            modifier = Modifier.fillMaxWidth().height(56.dp),
            shape = RoundedCornerShape(12.dp),
            colors = ButtonDefaults.buttonColors(containerColor = BrandPrimary)
        ) {
            Text("Save Report", fontSize = 18.sp, fontWeight = FontWeight.Bold)
        }
    }
}

@Composable
fun StyledTextFieldInput(
    label: String,
    value: String,
    onValueChange: (String) -> Unit,
    trailingIcon: ImageVector? = null
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(label) },
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        colors = OutlinedTextFieldDefaults.colors(
            focusedContainerColor = BrandLightBlue.copy(alpha = 0.2f),
            unfocusedContainerColor = BrandLightBlue.copy(alpha = 0.2f),
            focusedBorderColor = BrandPrimary,
            unfocusedBorderColor = Color.Transparent
        ),
        trailingIcon = if (trailingIcon != null) { { Icon(trailingIcon, null, tint = BrandBlack) } } else null
    )
}