package ru.skillbranch.devintensive.repositories

import android.content.SharedPreferences
import android.preference.PreferenceManager
import androidx.appcompat.app.AppCompatDelegate
import ru.skillbranch.devintensive.App
import ru.skillbranch.devintensive.models.Profile

object PreferencesRepository {
    private val FIRST_NAME = "FIRST_NAME"
    private val LAST_NAME = "LAST_NAME"
    private val ABOUT = "ABOUT"
    private val REPOSITORY = "REPOSITORY"
    private val RATING = "RATING"
    private val RESPECT = "RESPECT"
    private val APP_THEME = "APP_THEME"
    private val prefs: SharedPreferences by lazy {
        val ctx = App.applicationContext()
        PreferenceManager.getDefaultSharedPreferences(ctx)
    }

    fun getProfile(): Profile = Profile(
            prefs.getString(REPOSITORY, ""),
            prefs.getString(LAST_NAME, ""),
            prefs.getString(ABOUT, ""),
            prefs.getString(REPOSITORY, ""),
            prefs.getInt(RATING, 0),
            prefs.getInt(RESPECT, 0)
    )

    fun saveProfile(profile: Profile) {
        with(profile){
            putValue(FIRST_NAME  to firstName)
            putValue(LAST_NAME  to lastName)
            putValue(ABOUT  to about)
            putValue(REPOSITORY  to repository)
            putValue(RATING  to rating)
            putValue(RESPECT  to respect)
        }
    }

    private fun putValue(pair: Pair<String, Any>) = with(prefs.edit()){
        val key = pair.first
        val value = pair.second

        when (value){
            is String -> putString(key, value)
            is Int -> putInt(key, value)
            is Boolean -> putBoolean(key, value)
            is Long -> putLong(key, value)
            is Float -> putFloat(key, value)
            else -> error("wrong type")
        }

    }

    fun saveApptheme(value: Int) {
        putValue(APP_THEME to value)
    }

    fun getAppTheme(): Int{
        return prefs.getInt(APP_THEME,AppCompatDelegate.MODE_NIGHT_NO)
    }

}