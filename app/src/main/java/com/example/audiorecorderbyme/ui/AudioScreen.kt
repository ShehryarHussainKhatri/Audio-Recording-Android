package com.example.audiorecorderbyme.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.audiorecorderbyme.Player
import com.example.audiorecorderbyme.Recorder

@Composable
fun AudioScreen(audioViewModel: AudioViewModel = viewModel(), audioRecorder: Recorder, mediaPlayer: Player) {
    val viewUiState by audioViewModel.uiState.collectAsState()

    var enableRecordButton = true
    var enablePlayButton = true

    if(viewUiState.isRecording) {
        enableRecordButton = false
    }

    if(viewUiState.isPlaying) {
        enablePlayButton = false
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(enabled = enableRecordButton, onClick = {
            if(viewUiState.isPlaying) {
                mediaPlayer.stopPlaying()
            }
            audioViewModel.onStartRecording()
            audioRecorder.startRecording()
        }) {
            Text(text = "Start recording")
        }

        Button(enabled = !enableRecordButton, onClick = {
            audioViewModel.onStopRecording()
            audioRecorder.stopRecording()
        }) {
            Text(text = "Stop recording")
        }

        Button(enabled = enablePlayButton,onClick = {
            if(viewUiState.isRecording) {
                audioRecorder.stopRecording()
            }
            audioViewModel.onStartPlaying()
            mediaPlayer.startPlaying()
        }) {
            Text(text = "Start Playing")
        }

        Button(enabled = !enablePlayButton, onClick = {
            audioViewModel.onStopPlaying()
            mediaPlayer.stopPlaying()
        }) {
            Text(text = "Stop Playing")
        }
    }
}