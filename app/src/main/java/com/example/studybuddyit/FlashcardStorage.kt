package com.example.studybuddyit

import android.content.Context
import androidx.core.content.edit
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

object FlashcardStorage {
    private const val PREFS_NAME = "flashcards_prefs"
    private const val KEY_LIST   = "flashcard_list"

    //Saves the list of flashcards as JSON with asynchronous writing
    fun saveFlashcards(context: Context, list: List<Flashcard>) {
        val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        val json  = Gson().toJson(list)
        prefs.edit {
            putString(KEY_LIST, json)
        }
    }

    // Loads the mutable list from json
    fun loadFlashcards(context: Context): MutableList<Flashcard> {
        val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        val json  = prefs.getString(KEY_LIST, null)
        return if (json.isNullOrEmpty()) {
            mutableListOf()
        } else {
            val type = object : TypeToken<MutableList<Flashcard>>() {}.type
            Gson().fromJson<MutableList<Flashcard>>(json, type)
        }
    }
}
