package org.d3if2024.assesment2.model

import org.d3if2024.assesment2.db.SuhuEntity

fun SuhuEntity.hitungKonversiSuhu(): HasilKonversiSuhu {

    return HasilKonversiSuhu(suhuCelcius,hasilConvertCelcius)
}
