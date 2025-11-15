package es.fjruiz.magictimer.utils

import android.content.Context
import android.media.RingtoneManager
import android.os.VibrationEffect
import android.os.Vibrator

object AlertLauncher {
    fun launch(context: Context) {
        val vibrator = context.getSystemService(Vibrator::class.java)
        val notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM)
        val ringtone = RingtoneManager.getRingtone(context, notification)

        vibrator.vibrate(VibrationEffect.createOneShot(1000L, 100))
        ringtone.play()
    }
}