@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.studybuddyit

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.studybuddyit.ui.theme.StudyBuddyITTheme

// Simple data class for a flashcard
data class Flashcard(val question: String, val answer: String)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            StudyBuddyITTheme {
                AppContent()
            }
        }
    }
}

@Composable
fun AppContent() {
    // Obtain a Context for launching activities and showing toasts
    val context = LocalContext.current

    // Holds mutable list of flashcards in memory
    val flashcards = remember {
        mutableStateListOf(
            Flashcard("What is Kotlin?", "A modern JVM language."),
            Flashcard("Capital of Italy?", "Rome"),
            Flashcard("Who wrote '1984'?", "George Orwell")
        )
    }

    // Tracks current card index and answer visibility
    var currentCardIndex by remember { mutableStateOf(0) }
    var showAnswer by remember { mutableStateOf(false) }

    // Launcher to open AddFlashcardActivity and get results back
    val addCardLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val data = result.data
            val q = data?.getStringExtra("QUESTION_KEY").orEmpty()
            val a = data?.getStringExtra("ANSWER_KEY").orEmpty()
            if (q.isNotBlank() && a.isNotBlank()) {
                flashcards.add(Flashcard(q, a))
                currentCardIndex = flashcards.lastIndex
                showAnswer = false
            }
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("StudyBuddyIT") })
        }
    ) { innerPadding ->
        FlashcardScreen(
            flashcards = flashcards,
            currentIndex = currentCardIndex,
            showAnswer = showAnswer,
            onToggleAnswer = { showAnswer = !showAnswer },
            onNextCard = {
                currentCardIndex = (currentCardIndex + 1) % flashcards.size
                showAnswer = false
            },
            onAddCard = {
                val intent = Intent(context, AddFlashcardActivity::class.java)
                addCardLauncher.launch(intent)
            },
            modifier = Modifier
                .padding(innerPadding)
                .padding(16.dp)
        )
    }
}

@Composable
fun FlashcardScreen(
    flashcards: List<Flashcard>,
    currentIndex: Int,
    showAnswer: Boolean,
    onToggleAnswer: () -> Unit,
    onNextCard: () -> Unit,
    onAddCard: () -> Unit,
    modifier: Modifier = Modifier
) {

    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // Displays the question
        Text(
            text = flashcards[currentIndex].question,
            fontSize = 24.sp,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        // Displays answer when toggled.
        if (showAnswer) {
            Text(
                text = flashcards[currentIndex].answer,
                fontSize = 20.sp,
                modifier = Modifier.padding(bottom = 16.dp)
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Displays the row of buttons
        Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
            Button(onClick = onToggleAnswer) {
                Text(if (showAnswer) "Hide Answer" else "Show Answer")
            }
            Button(onClick = onNextCard) {
                Text("Next Card")
            }
        }

        Spacer(modifier = Modifier.height(32.dp))

        // Launches the add card screen
        Button(onClick = onAddCard) {
            Text("Add New Card")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun FlashcardScreenPreview() {
    StudyBuddyITTheme {
        FlashcardScreen(
            flashcards = listOf(Flashcard("Preview Q?", "Preview A")),
            currentIndex = 0,
            showAnswer = true,
            onToggleAnswer = {},
            onNextCard = {},
            onAddCard = {},
            modifier = Modifier.padding(16.dp)
        )
    }
}