package com.example.studybuddyit

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.studybuddyit.ui.theme.StudyBuddyITTheme
import androidx.compose.ui.tooling.preview.Preview

// Simple data class for a flashcard
data class Flashcard(val question: String, val answer: String)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            StudyBuddyITTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize()
                ) { innerPadding ->
                    FlashcardScreen(
                        modifier = Modifier
                            .padding(innerPadding)
                            .padding(16.dp)
                    )
                }
            }
        }
    }
}

@Composable
fun FlashcardScreen(modifier: Modifier = Modifier) {
    // 1. Hold a mutable list of sample flashcards in Compose state
    val flashcards = remember {
        mutableStateListOf(
            Flashcard("What is Kotlin?", "A modern JVM language."),
            Flashcard("Capital of Italy?", "Rome"),
            Flashcard("Who wrote '1984'?", "George Orwell")
        )
    }
    // 2. Track the current card index and whether to show the answer
    var currentCardIndex by remember { mutableStateOf(0) }
    var showAnswer by remember { mutableStateOf(false) }

    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // Display the question
        Text(
            text = flashcards[currentCardIndex].question,
            fontSize = 24.sp,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        // Conditionally display the answer
        if (showAnswer) {
            Text(
                text = flashcards[currentCardIndex].answer,
                fontSize = 20.sp,
                modifier = Modifier.padding(bottom = 16.dp)
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Row of “Show/Hide Answer” and “Next Card” buttons
        Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
            Button(onClick = { showAnswer = !showAnswer }) {
                Text(if (showAnswer) "Hide Answer" else "Show Answer")
            }
            Button(onClick = {
                // Advance to next card, wrapping around
                currentCardIndex = (currentCardIndex + 1) % flashcards.size
                showAnswer = false
            }) {
                Text("Next Card")
            }
        }

        Spacer(modifier = Modifier.height(32.dp))

        // Placeholder for Day 4: launching the Add Card screen
        Button(onClick = { /* TODO: launch AddFlashcardActivity */ }) {
            Text("Add New Card")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun FlashcardScreenPreview() {
    StudyBuddyITTheme {
        // You can pass a simple Modifier to see padding
        FlashcardScreen(modifier = Modifier.padding(16.dp))
    }
}