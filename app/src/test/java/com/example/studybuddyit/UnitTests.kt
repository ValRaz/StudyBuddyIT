package com.example.studybuddyit

import org.junit.Test
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import org.junit.Assert.*

// Local unit tests
class UnitTests {
    // Tests flashcard data class works as expected with same info cards
    @Test
    fun flashcardDataClass_equalityAndHashCode() {
        val f1 = Flashcard("What is Kotlin?", "A modern JVM language.")
        val f2 = Flashcard("What is Kotlin?", "A modern JVM language.")
        assertEquals(f1, f2)
        assertEquals(f1.hashCode(), f2.hashCode())
    }

    // Tests list order persistence through the JSON conversion process
    @Test
    fun gson_roundTrip_serializationPreservesList() {
        val originalList = listOf(
            Flashcard("Q1", "A1"),
            Flashcard("Q2", "A2"),
            Flashcard("Q3", "A3")
        )

        val gson = Gson()
        val json = gson.toJson(originalList)

        val type  = object : TypeToken<List<Flashcard>>() {}.type
        @Suppress("UNCHECKED_CAST")
        val loadedList = gson.fromJson<List<Flashcard>>(json, type)

        assertEquals(originalList, loadedList)
    }

    // Tests empty list detection and handling.
    @Test
    fun gson_emptyList_serializesToEmptyJsonArray() {
        val empty: List<Flashcard> = emptyList()
        val gson = Gson()
        val json = gson.toJson(empty)
        assertEquals("[]", json)
    }
}