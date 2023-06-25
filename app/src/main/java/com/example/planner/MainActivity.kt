package com.example.planner

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.planner.ui.theme.PlannerTheme
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PlannerTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background) {
                   DaysOfTheWeek()
                }
            }
        }
    }
}

@Composable
fun DaysOfTheWeek() {
    val days = listOf<String>("Mon", "Tues", "Wed", "Thurs", "Fri", "Sat", "Sun")
    LazyRow(
        modifier = Modifier.fillMaxWidth()
    ) {
        for (day in days) {
            item {
                Button(
                    onClick = { /*TODO*/ },
                    modifier = Modifier.padding(5.dp)
                ) {
                    Text(text = day)
                }
            }
        }
    }
}

@Composable
fun Header() {
    Row(modifier = Modifier.fillMaxWidth()) {
        val plannerTypes = listOf<String>("Academic", "Personal", "Gym")
        val colorTypes = listOf<Color>(Color.Blue, Color.Yellow, Color.Red)
        var index by remember { mutableStateOf(0) }
        Button(
            onClick = { index++ },
            modifier = Modifier.background(color = colorTypes[index % colorTypes.size])
        ) {
            Text(text = plannerTypes[index % plannerTypes.size])
        }
        Text(text = "Planner")
    }
}

@Composable
fun TaskList() {
    LazyColumn {
        item {
            repeat(60) {
                Text(
                    text = "test task",
                    modifier = Modifier.padding(5.dp)
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colors.background
    ) {
        Column {
            Header()
            DaysOfTheWeek()
            TaskList()
        }
    }
}
