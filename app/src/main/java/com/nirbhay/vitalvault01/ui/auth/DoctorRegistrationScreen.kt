package com.nirbhay.vitalvault01.ui.auth



import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Badge
import androidx.compose.material.icons.filled.LocalHospital
import androidx.compose.material.icons.filled.MedicalServices
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Work
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.nirbhay.vitalvault01.ui.theme.BrandDarkGray
import com.nirbhay.vitalvault01.ui.theme.BrandPrimary
import com.nirbhay.vitalvault01.ui.theme.BrandWhite
import com.nirbhay.vitalvault01.ui.theme.VitalVaultTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DoctorRegistrationScreen(
    onCompleteClick: () -> Unit = {}
) {
    var fullName by remember { mutableStateOf("") }
    var licenseNumber by remember { mutableStateOf("") }
    var hospitalName by remember { mutableStateOf("") }
    var experience by remember { mutableStateOf("") }

    // Specialization Dropdown Logic
    var specialization by remember { mutableStateOf("") }
    var expanded by remember { mutableStateOf(false) }
    val specializations = listOf(
        "General Physician",
        "Cardiologist",
        "Dermatologist",
        "Neurologist",
        "Pediatrician",
        "Orthopedic"
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(BrandWhite)
            .padding(24.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(20.dp))

        // Header
        Text(
            text = "Doctor Profile",
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold,
            color = BrandPrimary
        )
        Text(
            text = "Verify your medical credentials",
            style = MaterialTheme.typography.bodyMedium,
            color = BrandDarkGray,
            modifier = Modifier.padding(top = 8.dp, bottom = 32.dp)
        )

        // Full Name (Dr. ...)
        OutlinedTextField(
            value = fullName,
            onValueChange = { fullName = it },
            label = { Text("Full Name (with Dr. prefix)") },
            leadingIcon = { Icon(Icons.Default.Person, null) },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(12.dp),
            singleLine = true
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Medical License Number
        OutlinedTextField(
            value = licenseNumber,
            onValueChange = { licenseNumber = it },
            label = { Text("Medical License Number") },
            leadingIcon = { Icon(Icons.Default.Badge, null) },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(12.dp),
            singleLine = true
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Specialization Dropdown
        ExposedDropdownMenuBox(
            expanded = expanded,
            onExpandedChange = { expanded = !expanded },
            modifier = Modifier.fillMaxWidth()
        ) {
            OutlinedTextField(
                value = specialization,
                onValueChange = {},
                readOnly = true,
                label = { Text("Specialization") },
                leadingIcon = { Icon(Icons.Default.MedicalServices, null) },
                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
                modifier = Modifier
                    .fillMaxWidth()
                    .menuAnchor(),
                shape = RoundedCornerShape(12.dp)
            )
            ExposedDropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                specializations.forEach { selection ->
                    DropdownMenuItem(
                        text = { Text(selection) },
                        onClick = {
                            specialization = selection
                            expanded = false
                        }
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Hospital Name
        OutlinedTextField(
            value = hospitalName,
            onValueChange = { hospitalName = it },
            label = { Text("Hospital / Clinic Name") },
            leadingIcon = { Icon(Icons.Default.LocalHospital, null) },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(12.dp),
            singleLine = true
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Experience
        OutlinedTextField(
            value = experience,
            onValueChange = { experience = it },
            label = { Text("Years of Experience") },
            leadingIcon = { Icon(Icons.Default.Work, null) },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(12.dp),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            singleLine = true
        )

        Spacer(modifier = Modifier.height(40.dp))

        // Complete Button
        Button(
            onClick = onCompleteClick,
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            shape = RoundedCornerShape(12.dp),
            colors = ButtonDefaults.buttonColors(containerColor = BrandPrimary)
        ) {
            Text("Complete Verification", fontSize = MaterialTheme.typography.titleMedium.fontSize)
        }

        Spacer(modifier = Modifier.height(20.dp))
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewDoctorRegistration() {
    VitalVaultTheme {
        DoctorRegistrationScreen()
    }
}