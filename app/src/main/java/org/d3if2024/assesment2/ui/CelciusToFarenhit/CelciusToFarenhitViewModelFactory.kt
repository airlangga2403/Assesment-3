package org.d3if2024.assesment2.ui.CelciusToFarenhit

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import org.d3if2024.assesment2.db.SuhuDao
import org.d3if2024.assesment2.db.SuhuDb

class CelciusToFarenhitViewModelFactory(private val itemDao: SuhuDao) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CelciusToFarenhitViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return CelciusToFarenhitViewModel(itemDao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}