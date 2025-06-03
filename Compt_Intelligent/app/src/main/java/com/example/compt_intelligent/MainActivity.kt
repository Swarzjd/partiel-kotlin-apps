package com.example.compt_intelligent

import android.R.style
import android.annotation.SuppressLint
import android.graphics.Color.red
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.compt_intelligent.ui.theme.Compt_IntelligentTheme


class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Compt_IntelligentTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    CounterCard()
                }
            }
        }
    }
}

@Composable
fun CounterCard() {
    var counter by remember { mutableIntStateOf(0) }
    var maxInput by remember { mutableStateOf("") }
    val maxValue = maxInput.toIntOrNull() ?: Int.MAX_VALUE

    Card(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth(),
        elevation = CardDefaults.cardElevation(8.dp),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFF5F5F5))
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            TextField(
                value = maxInput,
                onValueChange = { maxInput = it },
                label = { Text("Max Value") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Counter: $counter",
                style = MaterialTheme.typography.titleMedium
            )

            if(counter == maxValue){
                Text(
                    text = "Limite atteinte !",
                    style = MaterialTheme.typography.bodyLarge,
                    color = Color.Red
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            Button(
                onClick = { if (counter < maxValue) counter++ },
                enabled = counter < maxValue
            ) {
                Text("Augmenter")
            }
            Button(
                onClick = {counter = 0}
            ){
                Text("Reset")
            }
        }
    }
}
