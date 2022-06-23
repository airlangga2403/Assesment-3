package org.d3if2024.assesment2.ui.CelciusToFarenhit

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import org.d3if2024.assesment2.R
import org.d3if2024.assesment2.databinding.FragmentCelciusToFarenhitBinding
import org.d3if2024.assesment2.db.SuhuDb
import org.d3if2024.assesment2.model.KategoriSuhu


class CelciusToFarenhitFragment : Fragment() {

    private val viewModel: CelciusToFarenhitViewModel by lazy{
        val db = SuhuDb.getInstance(requireContext())
        val factory = CelciusToFarenhitViewModelFactory(db.itemDao)
        ViewModelProvider(this,factory)[CelciusToFarenhitViewModel::class.java]
    }

    private lateinit var binding: FragmentCelciusToFarenhitBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentCelciusToFarenhitBinding.inflate(layoutInflater, container, false)
        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.calculateBtn.setOnClickListener {
            hitungKonversiSuhu()
            convertSuhuFarenheit()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.apps_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.menu_share){
            shareData()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    private fun shareData(){
        val suhuCelcius = binding.celciusToFarenheitEditText.text.toString()
        val hasilConvertSuhu = binding.result.text.toString()
        var message = "Suhu Celcius : ${suhuCelcius} \n${hasilConvertSuhu}"
        val shareIntent = Intent(Intent.ACTION_SEND)
        shareIntent.setType("text/plain").putExtra(Intent.EXTRA_TEXT, message)
        if (shareIntent.resolveActivity(
                requireActivity().packageManager
            ) != null
        ) {
            startActivity(shareIntent)
        }
    }

    private fun convertSuhuFarenheit(){
        val stringInTextField = binding.celciusToFarenheitEditText.text.toString()
        val suhu = stringInTextField.toDoubleOrNull()

        // Hitung Conversi Suhu Celcius Ke Farenheit
        if (suhu == null || suhu == 0.0){
            displayConvert(0.0)
            return
        }
        var farenheit = (suhu*9/5)+32
        displayConvert(farenheit)
    }

    private fun hitungKonversiSuhu(){
        val suhuCelcius = binding.celciusToFarenheitEditText.text.toString()
        viewModel.hitungKonversiSuhuCecliusToFarenhit(suhuCelcius.toFloat())
    }


    private fun displayConvert(dataCelcius: Double) {
        binding.result.text = "Nilai Hasil Konversi Suhu: ${dataCelcius}" + "°F"
    }
}
