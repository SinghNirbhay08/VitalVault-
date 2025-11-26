package com.nirbhay.vitalvault01.navigation



import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.nirbhay.vitalvault01.ui.auth.DoctorRegistrationScreen
import com.nirbhay.vitalvault01.ui.auth.LoginScreen
import com.nirbhay.vitalvault01.ui.auth.PatientRegistrationScreen
import com.nirbhay.vitalvault01.ui.auth.RoleSelectionScreen
import com.nirbhay.vitalvault01.ui.auth.SignUpScreen
import com.nirbhay.vitalvault01.ui.doctor.DoctorHomeScreen
import com.nirbhay.vitalvault01.ui.doctor.DoctorProfileScreen
import com.nirbhay.vitalvault01.ui.home.HomeScreen
import com.nirbhay.vitalvault01.ui.home.PatientProfileScreen
import com.nirbhay.vitalvault01.ui.home.ReportsListScreen
import com.nirbhay.vitalvault01.ui.intro.OnboardingScreen
import com.nirbhay.vitalvault01.ui.share.ShareScreen
import com.nirbhay.vitalvault01.ui.splashScreen.SplashScreen

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Screen.Splash.route
    ) {

        fun navigateToTab(navController: androidx.navigation.NavController, route: String) {
            navController.navigate(route) {
                // Pop up to the start destination of the graph to
                // avoid building up a large stack of destinations
                popUpTo(Screen.PatientHome.route) {
                    saveState = true
                }
                // Avoid multiple copies of the same destination when
                // reselecting the same item
                launchSingleTop = true
                // Restore state when reselecting a previously selected item
                restoreState = true
            }
        }
        // 1. Splash
        composable(Screen.Splash.route) {
            SplashScreen(
                onSplashFinished = {
                    navController.navigate(Screen.Onboarding.route) {
                        popUpTo(Screen.Splash.route) { inclusive = true }
                    }
                }
            )
        }

        // 2. Onboarding
        composable(Screen.Onboarding.route) {
            OnboardingScreen(
                onFinish = {
                    navController.navigate(Screen.RoleSelection.route) {
                        popUpTo(Screen.Onboarding.route) { inclusive = true }
                    }
                }
            )
        }

        // 3. Role Selection
        composable(Screen.RoleSelection.route) {
            RoleSelectionScreen(
                onPatientSelected = { navController.navigate(Screen.Login.createRoute("patient")) },
                onDoctorSelected = { navController.navigate(Screen.Login.createRoute("doctor")) }
            )
        }

        // 4. Login (Handles both Patient and Doctor based on argument)
        composable(
            route = Screen.Login.route,
            arguments = listOf(navArgument("role") { type = NavType.StringType })
        ) { backStackEntry ->
            val role = backStackEntry.arguments?.getString("role") ?: "patient"

            LoginScreen(
                onLoginClick = {
                    // Logic: If patient -> PatientHome, If doctor -> DoctorHome
                    val target = if (role == "patient") Screen.PatientHome.route else Screen.DoctorHome.route
                    navController.navigate(target) {
                        popUpTo(Screen.RoleSelection.route) { inclusive = true }
                    }
                },
                onSignUpClick = {
                    navController.navigate(Screen.SignUp.createRoute(role))
                }
            )
        }

        // 5. Sign Up
        composable(
            route = Screen.SignUp.route,
            arguments = listOf(navArgument("role") { type = NavType.StringType })
        ) { backStackEntry ->
            val role = backStackEntry.arguments?.getString("role") ?: "patient"

            SignUpScreen(
                onSignUpClick = {
                    // Go to specific registration screen
                    val target = if (role == "patient") Screen.PatientRegistration.route else Screen.DoctorRegistration.route
                    navController.navigate(target)
                },
                onLoginClick = {
                    navController.popBackStack() // Go back to Login
                }
            )
        }

        // 6. Complete Profile (Patient)
        composable(Screen.PatientRegistration.route) {
            PatientRegistrationScreen(
                onCompleteClick = {
                    navController.navigate(Screen.PatientHome.route) {
                        popUpTo(Screen.RoleSelection.route) { inclusive = true }
                    }
                }
            )
        }

        // 7. Complete Profile (Doctor)
        composable(Screen.DoctorRegistration.route) {
            DoctorRegistrationScreen(
                onCompleteClick = {
                    navController.navigate(Screen.DoctorHome.route) {
                        popUpTo(Screen.RoleSelection.route) { inclusive = true }
                    }
                }
            )
        }

        // --- PATIENT MAIN FLOW ---

        // 8. Patient Home
        composable(Screen.PatientHome.route) {
            HomeScreen(
                onUploadClick = { navController.navigate(Screen.ReportsList.route) },
                onViewRecordsClick = { navController.navigate(Screen.ReportsList.route) },
                onReportDetailClick = { /* TODO */ },
                onNavigateBottomBar = { route -> navigateToTab(navController, route) }
            )
        }

        // 9. Reports List
        composable(Screen.ReportsList.route) {
            ReportsListScreen(
                onBack = { navController.popBackStack() },
                onNavigateBottomBar = { route -> navigateToTab(navController, route) }
            )
        }

        // 10. Patient Profile
        composable(Screen.PatientProfile.route) {
            PatientProfileScreen(
                onLogoutClick = {
                    navController.navigate(Screen.RoleSelection.route) { popUpTo(0) }
                },
                onNavigateBottomBar = { route -> navigateToTab(navController, route) }
            )
        }

        // 11. Share Screen
        composable(Screen.Share.route) {
            ShareScreen(
                onBackClick = { navController.popBackStack() },
                onNavigateBottomBar = { route -> navigateToTab(navController, route) }
            )
        }




        // --- DOCTOR MAIN FLOW ---

        // 12. Doctor Home
        composable(Screen.DoctorHome.route) {
            DoctorHomeScreen(
                onScanQrClick = { /* Launch Camera TODO */ },
                onReportDetailClick = { /* View Patient Records TODO */ }
            )
        }

        // 13. Doctor Profile
        composable(Screen.DoctorProfile.route) {
            DoctorProfileScreen(
                onLogoutClick = {
                    navController.navigate(Screen.RoleSelection.route) {
                        popUpTo(0)
                    }
                }
            )
        }
    }
}