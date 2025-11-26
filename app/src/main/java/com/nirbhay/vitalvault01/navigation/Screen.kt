package com.nirbhay.vitalvault01.navigation


sealed class Screen(val route: String) {
    object Splash : Screen("splash")
    object Onboarding : Screen("onboarding")
    object RoleSelection : Screen("role_selection")

    // Auth
    object Login : Screen("login/{role}") {
        fun createRoute(role: String) = "login/$role"
    }
    object SignUp : Screen("signup/{role}") {
        fun createRoute(role: String) = "signup/$role"
    }
    object PatientRegistration : Screen("patient_registration")
    object DoctorRegistration : Screen("doctor_registration")

    // Patient Flow
    object PatientHome : Screen("patient_home")
    object ReportsList : Screen("patient_reports")
    object PatientProfile : Screen("patient_profile")
    object Share : Screen("patient_share")

    // Doctor Flow
    object DoctorHome : Screen("doctor_home")
    object DoctorProfile : Screen("doctor_profile")
}