package com.nirbhay.vitalvault01



import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp // <--- THIS MUST BE HERE
class VitalVaultApp : Application()