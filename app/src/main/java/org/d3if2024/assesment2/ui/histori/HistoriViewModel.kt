package org.d3if2024.assesment2.ui.histori

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.d3if2024.assesment2.db.SuhuDao
import org.d3if2024.assesment2.db.SuhuEntity
import org.d3if2024.assesment2.model.HasilKonversiSuhu
import org.d3if2024.assesment2.model.hitungKonversiSuhu

class HistoriViewModel(private val db: SuhuDao) : ViewModel() {
    private val hasilKonversiSuhu = MutableLiveData<HasilKonversiSuhu?>()

    val data = db.getKonversiSuhu()

    fun hapusData() = viewModelScope.launch {
        withContext(Dispatchers.IO){
            db.clearData()
        }
    }
}