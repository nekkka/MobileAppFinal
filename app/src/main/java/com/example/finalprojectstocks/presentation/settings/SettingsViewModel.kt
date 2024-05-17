package com.example.finalprojectstocks.presentation.settings

import androidx.lifecycle.ViewModel
import com.example.finalprojectstocks.domain.datastore.SharedPreferenceService
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val sharedPreferenceService: SharedPreferenceService
): ViewModel() {

    fun changeInterval(days: Int){
        sharedPreferenceService.updateInt(days)
    }

    fun getCurrentInterval(): Int{
        return sharedPreferenceService.getInt()
    }
}