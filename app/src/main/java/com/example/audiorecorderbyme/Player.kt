package com.example.audiorecorderbyme

import android.media.MediaPlayer
import android.util.Log
import java.io.IOException

private const val LOG_TAG = "Player Class"

class Player(private val fileName: String) {

    private var player: MediaPlayer? = null

    fun startPlaying() {
        player = MediaPlayer().apply {
            try {
                setDataSource(fileName)
                prepare()
                start()
            } catch (e: IOException) {
                Log.e(LOG_TAG, "prepare() failed")
            }
        }
    }

    fun stopPlaying() {
        player?.stop()
        player?.release()
        player = null
    }

}