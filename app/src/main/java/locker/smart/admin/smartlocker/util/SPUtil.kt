package locker.smart.admin.smartlocker.util

import android.content.Context
import locker.smart.admin.smartlocker.App

object SPUtil {

    private const val PreferenceName = "SPUtil"
    private const val userUUID = "user_uuid"

    fun saveUserUUID(uuid: String) {
        val sp = App.instance.getSharedPreferences(PreferenceName,
                Context.MODE_PRIVATE)
        val editor = sp.edit()
        editor.putString(userUUID, uuid)
        editor.apply()
    }

    fun getUserUUID(): String? {
        val sp = App.instance.getSharedPreferences(PreferenceName,
                Context.MODE_PRIVATE)
        return sp.getString(userUUID, "")
    }
}