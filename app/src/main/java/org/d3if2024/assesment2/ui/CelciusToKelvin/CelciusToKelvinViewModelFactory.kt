package org.d3if2024.assesment2.ui.CelciusToKelvin

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import org.d3if2024.assesment2.db.SuhuDao

class CelciusToKelvinViewModelFactory(private val itemDao: SuhuDao) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CelciusToKelvinViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return CelciusToKelvinViewModel(itemDao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}