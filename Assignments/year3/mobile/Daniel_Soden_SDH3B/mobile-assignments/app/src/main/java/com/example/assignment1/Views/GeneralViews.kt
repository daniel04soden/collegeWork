package com.example.assignment1.Views

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.assignment1.Models.User
import com.example.assignment1.R
import java.time.LocalDate


@Composable
fun WelcomeCard(user: User, activity: String) {
    val today = LocalDate.now().toString()
    val (streak, lastEntryDate) = when (activity) {
        "Food" -> Pair(user.calorieStreak, user.lastFoodEntryDate)
        "Journal" -> Pair(user.journalStreak, user.lastJournalEntryDate)
        "Mediation" -> Pair(user.meditationStreak, user.lastMeditationEntryDate)
        "Home" -> Pair(user.consistencyScore, LocalDate.now().toString())
        else -> Pair(0, null)
    }
    val trackedToday = lastEntryDate == today

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(text = "Welcome ${user.fullName}!")
            if (activity == "Home") {
                Text(text = "Your consistency score is: ${user.consistencyScore}")
            }else{
                Text(text = "Streak: $streak days")
            }
            if (trackedToday) {
                Text(text = "Today: Tracked")
            } else {
                Text(text = "Today: Incomplete")
            }
        }
    }
}
@Composable
fun NavButton(itemName: String, itemStreak: Int, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable{onClick()},
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = itemName,
                    fontWeight = FontWeight.Bold, fontSize = 18.sp
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "Streak: $itemStreak", fontSize = 16.sp)
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

@Composable
fun OptionsDropDownMenu(
    expanded:Boolean,
    onDismiss: () -> Unit,
    onOptionSelected: (String) -> Unit,
    options: List<String>
){
    LocalContext.current
    DropdownMenu(
        expanded = expanded,
        onDismissRequest = {onDismiss()}
    ) {
        options.forEach {option->
            DropdownMenuItem(
                text = {Text(option)},
                onClick = {
                    onOptionSelected(option)
                    onDismiss()
                }
            )
        }
    }
}

@Composable
fun LoginImage() {
    Image(painter = painterResource( R.drawable.improve),
        contentDescription = "Scaled Image",
        modifier = Modifier
            .fillMaxWidth()
            .height(350.dp),
        contentScale = ContentScale.FillBounds
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ReusableDropdown(
    label: String,
    options: List<String>,
    selectedValue: String,
    onValueChange: (String) -> Unit,
    isError: Boolean,
    modifier: Modifier = Modifier
) {
    var expanded by remember { mutableStateOf(false) }

    Box(modifier = modifier) {
        ExposedDropdownMenuBox(
            expanded = expanded,
            onExpandedChange = { expanded = !expanded }
        ) {
            OutlinedTextField(
                value = selectedValue,
                onValueChange = {},
                readOnly = true,
                label = { Text(label) },
                trailingIcon = {
                    ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded)
                },
                colors = ExposedDropdownMenuDefaults.outlinedTextFieldColors(),
                isError = isError,
                modifier = Modifier
                    .menuAnchor()
                    .fillMaxWidth()
            )
            ExposedDropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                options.forEach { option ->
                    DropdownMenuItem(
                        text = { Text(option) },
                        onClick = {
                            onValueChange(option)
                            expanded = false
                        }
                    )
                }
            }
        }
    }
}
