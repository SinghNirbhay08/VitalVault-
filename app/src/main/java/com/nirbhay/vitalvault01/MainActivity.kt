package com.nirbhay.vitalvault01

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.nirbhay.vitalvault01.navigation.AppNavigation
import com.nirbhay.vitalvault01.ui.auth.LoginScreen
import com.nirbhay.vitalvault01.ui.auth.PatientRegistrationScreen
import com.nirbhay.vitalvault01.ui.auth.RoleSelectionScreen
import com.nirbhay.vitalvault01.ui.doctor.DoctorHomeScreen
import com.nirbhay.vitalvault01.ui.home.HomeScreen
import com.nirbhay.vitalvault01.ui.home.ReportsListScreen
import com.nirbhay.vitalvault01.ui.intro.OnboardingScreen
import com.nirbhay.vitalvault01.ui.share.ShareScreen
import com.nirbhay.vitalvault01.ui.splashScreen.SplashScreen

import com.nirbhay.vitalvault01.ui.theme.VitalVaultTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            VitalVaultTheme {

                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(top=16.dp)
                    ){
                        AppNavigation()


                }
            }
        }
    }
}

