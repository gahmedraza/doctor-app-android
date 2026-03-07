package com.raza.zikr

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.ComponentActivity
import com.raza.zikr.navigation.ZikrNavGraph
import com.raza.zikr.ui.theme.ZikrappandroidTheme

class HomActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ZikrappandroidTheme() {
                ZikrNavGraph()
            }
        }
    }
}