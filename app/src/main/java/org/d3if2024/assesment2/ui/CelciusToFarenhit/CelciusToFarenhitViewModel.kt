package org.d3if2024.assesment2.ui.CelciusToFarenhit

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

class CelciusToFarenhitViewModel(private val db: SuhuDao) : ViewModel() {
    private val hasilKonversi = MutableLiveData<HasilKonversiSuhu?>()

    fun getHasilKonversiSuhu(): LiveData<HasilKonversiSuhu?> = hasilKonversi

    fun hitungKonversiSuhuCecliusToFarenhit(suhuCeclius: Float) {
//        val hasilConverCelcius = "Farenhit"/
        val hasil = (suhuCeclius * 9 / 5) + 32 // Hasil > Convert Dari Celcius Ke Farenhit
        val dataKonversi = SuhuEntity(
            suhuCelcius = suhuCeclius,
            hasilConvertCelcius = "${hasil.toString()}°F"
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
    fun getHasilBmi(): LiveData<HasilKonversiSuhu?> = hasilKonversi
}
