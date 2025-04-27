package com.example.unscramble.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.unscramble.data.SCORE_INCREASE
import com.example.unscramble.data.allWords
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlin.math.log

class GameViewModel: ViewModel() {
    private val _uiState = MutableStateFlow(GameUiState())
    // firstly using StateFlow<T> to make it immutable already then assigning an immutable cast of a MutableStateFlow
    val uiState: StateFlow<GameUiState> = _uiState.asStateFlow()

    private lateinit var currentWord : String
    private var usedWords: MutableSet<String> = mutableSetOf()

    var userGuess by mutableStateOf("")
        private set

    init {
        println("---> init GameViewModel")
        resetGame()
    }

    // pick a word from the available list and shuffle it
    private fun pickRandomWordAndShuffle() : String {
        // store currentWord fetched after randomly picking from list of words
        currentWord = allWords.random()
        println("---> Current Word: ${currentWord}")
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
        println("---> after shuffling: ${String(tempWord)}")
        return String(tempWord)
    }

    // this function will be used to start or restart a new game clearing the usedWords and initializing the _uiState
    fun resetGame() {
        usedWords.clear()
        _uiState.value = GameUiState(currentScrambledWord = pickRandomWordAndShuffle())
    }

    fun updateUserGuess(guessedWord: String) {
        userGuess = guessedWord
    }

    // check whether the word guessed by user is valid or not
    fun checkUserGuess() {
        if(userGuess.equals(currentWord, ignoreCase = true)) {
            // increase score for correct guess
            val updatedScore = _uiState.value.score.plus(SCORE_INCREASE)

            // updating game state
            updateGameState(updatedScore)
        }
        else {
            // user's guess is wrong, show an error
            // updating the local instance i.e. _uiState, with isGuessedWordWrong = true
            _uiState.update { currentState -> currentState.copy(isGuessedWordWrong = true) }
        }

        // Reset user guess
        updateUserGuess("")
    }

    private fun updateGameState(updatedScore: Int) {
        // updating the local instate of UIState with
        //  -the guessed word status,
        //  -next scrambled word to be displayed,
        //  -updated score
        //  -updated unscrambled word count
        _uiState.update { currentState ->
            currentState.copy(
                isGuessedWordWrong = false,
                currentScrambledWord = pickRandomWordAndShuffle(),
                currentWordCount = currentState.currentWordCount.inc(),
                score = updatedScore
            )
        }
    }

    fun skipWord() {
        updateGameState(_uiState.value.score)

        // clearing the current visible response on the input field
        updateUserGuess("")
    }
}