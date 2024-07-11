package com.example.audiorecorderbyme

import android.content.Context
import android.media.MediaRecorder
import android.util.Log
import java.io.IOException

private const val LOG_TAG = "Recorder Class"

class Recorder(private val context: Context, private val fileName: String) {

    private var recorder: MediaRecorder? = null

    fun startRecording() {
        recorder = MediaRecorder(context).apply {
            setAudioSource(MediaRecorder.AudioSource.MIC)
            setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP)
            setOutputFile(fileName)
            setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB)

            try {
                prepare()
            } catch (e: IOException) {
                Log.e(LOG_TAG, "prepare() failed")
            }

            start()
        }
    }

    fun stopRecording() {
        recorder?.apply {
            stop()
            release()
        }
        recorder = null
    }
}