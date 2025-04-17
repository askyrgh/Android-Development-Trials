package com.example.unscramble.ui

import androidx.lifecycle.ViewModel
import com.example.unscramble.data.allWords
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class GameViewModel: ViewModel() {
    private val _uiState = MutableStateFlow(GameUiState())
    // firstly using StateFlow<T> to make it immutable already then assigning an immutable cast of a MutableStateFlow
    val uiState: StateFlow<GameUiState> = _uiState.asStateFlow()

    private lateinit var currentWord : String
    private var usedWords: MutableSet<String> = mutableSetOf()

    fun init() {
        resetGame()
    }

    // pick a word from the available list and shuffle it
    private fun pickRandomWordAndShuffle() : String {
        // store currentWord fetched after randomly picking from list of words
        currentWord = allWords.random()
        // check whether current word is already used or not, if yes recursively generate a new one and return that, else return it
        if (usedWords.contains(currentWord)) {
            return pickRandomWordAndShuffle()
        }
        else {
            usedWords.add(currentWord)
            return shuffleCurrentWord(currentWord)
        }
    }

    // Shuffle the current word provided as argument
    private fun shuffleCurrentWord(word: String): String {
        val tempWord = word.toCharArray()

        // Scramble the word
        tempWord.shuffle()
        while(tempWord.equals(word)) {
            tempWord.shuffle()
        }
        return String(tempWord)
    }

    // this function will be used to start or restart a new game clearing the usedWords and initializing the _uiState
    fun resetGame() {
        usedWords.clear()
        _uiState.value = GameUiState(currentScrambledWord = pickRandomWordAndShuffle())
    }
}