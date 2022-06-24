package org.d3if2024.assesment2.ui.CelciusToReamur

import android.content.ClipData
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import androidx.lifecycle.asLiveData
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.d3if2024.assesment2.R
import org.d3if2024.assesment2.db.SuhuDao
import org.d3if2024.assesment2.db.SuhuEntity
import org.d3if2024.assesment2.model.HasilKonversiSuhu
import org.d3if2024.assesment2.model.KategoriSuhu
import org.d3if2024.assesment2.model.hitungKonversiSuhu

class CelciusToReamurViewModel(private val db: SuhuDao) : ViewModel() {
    private val hasilKonversi = MutableLiveData<HasilKonversiSuhu?>()

    fun getHasilKonversiSuhu(): LiveData<HasilKonversiSuhu?> = hasilKonversi

    fun hitungKonversiSuhuCelciusToReamur(suhuCeclius: Float) {
        val hasil = suhuCeclius*4/5
        val dataKonversi = SuhuEntity(
            suhuCelcius = suhuCeclius,
            hasilConvertCelcius = "${hasil}°R"
        )
        hasilKonversi.value = dataKonversi.hitungKonversiSuhu()
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                db.insert(dataKonversi)
            }
        }

        fun gethasilKonversi(): Float{
            return hasil
        }

        fun isEntryValid(suhuCelcius: String): Boolean {
            if (suhuCelcius.isBlank()) {
                return false
            }
            return true
        }
    }
}
