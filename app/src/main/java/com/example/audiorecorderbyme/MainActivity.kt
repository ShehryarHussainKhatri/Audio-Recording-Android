package com.example.audiorecorderbyme

import android.Manifest
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.core.app.ActivityCompat
import com.example.audiorecorderbyme.ui.AudioScreen
import com.example.audiorecorderbyme.ui.theme.AudioRecorderByMeTheme

class MainActivity : ComponentActivity() {

    private var fileName: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //Taking permission to record audio
        ActivityCompat.requestPermissions(
            this,
            arrayOf(Manifest.permission.RECORD_AUDIO),
            0
        )

        //file path
        fileName = "${externalCacheDir?.absolutePath}/audio_record_test.3gp"

        val audioRecorder by lazy {
            Recorder(applicationContext, fileName)
        }

        val mediaPlayer by lazy {
            Player(fileName)
        }

        setContent {
            AudioRecorderByMeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    AudioScreen(audioRecorder = audioRecorder, mediaPlayer =  mediaPlayer)
                }
            }
        }
    }
}