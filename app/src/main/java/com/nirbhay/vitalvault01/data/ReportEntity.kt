package com.nirbhay.vitalvault01.data



import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "reports")
data class ReportEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val title: String,
    val doctorName: String,
    val dateOfConsultancy: String,
    val type: String = "General", // e.g., Lab, Prescription
    val filePath: String = "" // For Phase 2 (Actual file storage)
)