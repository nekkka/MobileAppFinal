package com.example.finalprojectstocks.data.datastore

import android.content.Context
import android.content.SharedPreferences
import com.example.finalprojectstocks.domain.datastore.SharedPreferenceService
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class SharedPreferenceServiceImpl @Inject constructor(@ApplicationContext context: Context) :
    SharedPreferenceService {
    companion object {
        private const val PREFS_FILE_NAME = "MyAppPreferences"
        private const val INT_KEY = "MyIntKey"
    }

    private var sharedPreferences: SharedPreferences =
        context.getSharedPreferences(PREFS_FILE_NAME, Context.MODE_PRIVATE)

    private fun saveInt(value: Int) {
        sharedPreferences.edit().putInt(INT_KEY, value).apply()
    }

    override fun getInt(): Int {
        return sharedPreferences.getInt(INT_KEY, 7)
    }

    override fun updateInt(newValue: Int) {
        saveInt(newValue)
    }
}
