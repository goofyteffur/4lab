package com.example.a5lab

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.util.Locale

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    CalculatorScreen()
                }
            }
        }
    }
}

@Composable
fun CalculatorScreen() {
    var textN by remember { mutableStateOf("") }
    var result by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            text = "Дано натуральное число n. Вычислить:",
            fontSize = 16.sp
        )
        Text(
            text = "1/1! + 1/2! + 1/3! + ... + 1/n!",
            fontSize = 18.sp,
            modifier = Modifier.padding(vertical = 16.dp)
        )

        TextField(
            value = textN,
            onValueChange = { textN = it },
            label = { Text("Введите n") },
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                val n = textN.toIntOrNull()
                if (n != null && n > 0) {
                    result = calculateSum(n)
                } else {
                    result = "Ошибка: введите натуральное число"
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Вычислить", fontSize = 18.sp)
        }

        Spacer(modifier = Modifier.height(24.dp))

        if (result.isNotEmpty()) {
            Text(
                text = "Результат:",
                fontSize = 18.sp
            )
            Text(
                text = result,
                fontSize = 20.sp,
                modifier = Modifier.padding(top = 8.dp)
            )
        }
    }
}

// Функция вычисления
fun calculateSum(n: Int): String {
    var factorial = 1.0
    var sum = 0.0

    for (k in 1..n) {
        factorial *= k
        sum += 1.0 / factorial
    }

    return String.format(Locale.US, "%.6f", sum)
}