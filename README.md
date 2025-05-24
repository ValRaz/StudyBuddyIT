# Overview

StudyBuddyIT is a simple, beginner-friendly flashcard app built with Kotlin and Jetpack Compose. 
It lets you quickly create, review, shuffle, and delete flashcards to aid your study sessions. 
On launch, you’ll see any saved flashcards; tap **Show Answer** to reveal the answer, 
**Next Card** to cycle through, **Shuffle** to randomize the deck, and long-press a card to 
delete it. If you have no cards yet, a friendly “No Cards – Please add flashcards” prompt 
guides you to start creating your own.

I created StudyBuddyIT to deepen my understanding of modern Android development. Particularly
Jetpack Compose for UI, Material 3 theming, and simple on-device persistence via SharedPreferences
+ Gson. Along the way I practiced Kotlin data classes, state management in Compose, Activity 
+ result APIs, and designing a clean, responsive and accessible layout.

[Software Demo Video](https://www.youtube.com/watch?v=vQUZE-yYdxk)

# Development Environment

- **IDE:** Android Studio
- **Language:** Kotlin
- **UI Toolkit:** Jetpack Compose (Material 3)
- **Persistence:** Android `SharedPreferences` with [Gson](https://github.com/google/gson) for JSON serialization
- **Testing:** JUnit4 for local JVM tests 
- **Build System:** Gradle Kotlin DSL

# Useful Websites

* [Jetpack Compose](https://developer.android.com/jetpack/compose) – official guide for Compose basics and Material 3.
* [Material 3 for Android](https://m3.material.io/) – color theming, typography, and component specs.
* [Android Developers: Activities and Intents](https://developer.android.com/guide/components/activities/intro-activities) – how to launch Add-Card screen and handle results.
* [Android SharedPreferences](https://developer.android.com/reference/android/content/SharedPreferences) – storing simple JSON blobs on-device.
* [Gson User Guide](https://github.com/google/gson/blob/master/UserGuide.md) – serializing Kotlin objects to/from JSON.
* [Kotlin Data Classes](https://kotlinlang.org/docs/data-classes.html) – auto-generated `equals`/`hashCode`/`toString`.
* [Android Testing on the JVM](https://developer.android.com/training/testing/unit-testing/local-code) – writing fast unit tests for non-Android code.

# Future Work

* **Room database integration** – replace SharedPreferences + Gson with a cleaner/ more modern 
data persistence option.
* **Search & categories** – tag flashcards by topic and add a search/filter capability to the UI.
* **Device configuration handling** – preserve deck state on device rotation.
* **Theming options** – user-selectable light/dark or custom color palettes.
* **Cloud sync** – back up decks to Firebase or a REST API for cross-device use.
* **Accessibility improvements** – better screen-reader support and adjustable font sizes.  