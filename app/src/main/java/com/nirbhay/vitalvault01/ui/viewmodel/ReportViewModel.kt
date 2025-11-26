package com.nirbhay.vitalvault01.ui.viewmodel



import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nirbhay.vitalvault01.data.ReportDao
import com.nirbhay.vitalvault01.data.ReportEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ReportViewModel @Inject constructor(
    private val reportDao: ReportDao
) : ViewModel() {

    // 1. Real-time list of reports from Database
    val reports: StateFlow<List<ReportEntity>> = reportDao.getAllReports()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    // 2. Function to Save a Report
    fun addReport(title: String, doctor: String, date: String) {
        viewModelScope.launch {
            val newReport = ReportEntity(
                title = title,
                doctorName = doctor,
                dateOfConsultancy = date,
                type = "General"
            )
            reportDao.insertReport(newReport)
        }
    }
}