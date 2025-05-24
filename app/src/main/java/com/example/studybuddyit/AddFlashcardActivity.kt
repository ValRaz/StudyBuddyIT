package com.example.studybuddyit

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.studybuddyit.ui.theme.StudyBuddyITTheme

class AddFlashcardActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            StudyBuddyITTheme {
                AddFlashcardScreen()
            }
        }
    }
}

@Composable
fun AddFlashcardScreen() {
    var question by remember { mutableStateOf("") }
    var answer   by remember { mutableStateOf("") }
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Card for getting user input to add a question
        Card(
            colors    = CardDefaults.cardColors(containerColor = Color.White),
            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
            modifier  = Modifier
                .fillMaxWidth()
                .padding(vertical = 4.dp)
        ) {
            OutlinedTextField(
                value = question,
                onValueChange = {
                    if (it.length <= 300) question = it
                },
                label = { Text("Question") },
                placeholder = { Text("Max 300 char") },
                modifier = Modifier.fillMaxWidth(),
                maxLines = 5,
                colors = OutlinedTextFieldDefaults.colors(
                    focusedContainerColor = Color.White,
                    unfocusedContainerColor = Color.White,
                    disabledContainerColor = Color.White,
                    focusedTextColor  = Color.Black,
                    unfocusedTextColor = Color.Black,
                    disabledTextColor = Color.Black
                )
            )
        }

        Spacer(Modifier.height(8.dp))

        // Card for getting user input to add an answer
        Card(
            colors = CardDefaults.cardColors(containerColor = Color.White),
            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 4.dp)
        ) {
            OutlinedTextField(
                value = answer,
                onValueChange = {
                    if (it.length <= 300) answer = it
                },
                label = { Text("Answer") },
                placeholder = { Text("Max 300 char") },
                modifier = Modifier.fillMaxWidth(),
                maxLines = 5,
                colors = OutlinedTextFieldDefaults.colors(
                    focusedContainerColor = Color.White,
                    unfocusedContainerColor = Color.White,
                    disabledContainerColor = Color.White,
                    focusedTextColor = Color.Black,
                    unfocusedTextColor = Color.Black,
                    disabledTextColor = Color.Black
                )
            )
        }

        Spacer(Modifier.height(16.dp))

        Button(onClick = {
            // Input validation preventing blank inputs
            if (question.isBlank() || answer.isBlank()) {
                Toast.makeText(
                    context,
                    "Please fill in both fields",
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                // Adds the data keys for data handling
                val resultIntent = Intent().apply {
                    putExtra("QUESTION_KEY", question)
                    putExtra("ANSWER_KEY", answer)
                }
                val activity = context as? Activity
                activity?.setResult(Activity.RESULT_OK, resultIntent)
                activity?.finish()
            }
        },
            enabled = question.isNotBlank() && answer.isNotBlank()
        ) {
            Text("Save")
        }
    }
}