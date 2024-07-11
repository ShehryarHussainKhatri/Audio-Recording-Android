package com.example.audiorecorderbyme.ui

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class AudioViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(AudioUiState())
    val uiState: StateFlow<AudioUiState> = _uiState.asStateFlow()

    fun onStartRecording() {
        _uiState.value = AudioUiState(isRecording = true)
    }

    fun onStopRecording() {
        _uiState.value = AudioUiState(isRecording = false)
    }

    fun onStartPlaying() {
        _uiState.value = AudioUiState(isPlaying = true)
    }

    fun onStopPlaying() {
        _uiState.value = AudioUiState(isPlaying = false)
    }
}