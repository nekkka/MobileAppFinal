package com.example.finalprojectstocks.domain.datastore

interface SharedPreferenceService {
    fun getInt(): Int

    fun updateInt(newValue: Int)
}