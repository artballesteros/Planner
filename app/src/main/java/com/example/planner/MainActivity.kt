package com.example.planner

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Button
import androidx.compose.material.ChipDefaults
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.FilterChip
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.ExperimentalMaterial3Api
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
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PlannerTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background) {
                    Column(
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Header()
                        DaysOfTheWeek()
                        TaskList()
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
@ExperimentalMaterial3Api
@Composable
fun DaysOfTheWeek() {
    val days = arrayOf<String>("Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday")
    var selectedDays by remember {
        mutableStateOf(BooleanArray(days.size))
    }
    FlowRow(
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp),
        horizontalArrangement = Arrangement.Center,
    ) {
        for (i in days.indices) {
            FilterChipDay(
                day = days[i],
                selected = selectedDays[i],
                onClick = { selectedDays[i] = !selectedDays[i] }
            )
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@ExperimentalMaterial3Api
@Composable
fun FilterChipDay(day: String, selected: Boolean, onClick: (Boolean) -> Unit) {
    FilterChip(
        selected = selected,
        onClick = { onClick(selected) },
        border = ChipDefaults.outlinedBorder,
        colors = ChipDefaults.outlinedFilterChipColors(),
        selectedIcon = {
            Icon(
                imageVector = Icons.Filled.Check,
                contentDescription = "Selected",
                modifier = Modifier.requiredSize(ChipDefaults.SelectedIconSize)
            )
        },
        modifier = Modifier.padding(4.dp)
    ) {
        Text(text = day)
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

@Composable
fun TaskItem() {

}

@OptIn(ExperimentalMaterial3Api::class)
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
