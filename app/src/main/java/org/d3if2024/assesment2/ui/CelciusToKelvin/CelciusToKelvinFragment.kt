package org.d3if2024.assesment2.ui.CelciusToKelvin

import android.content.Intent
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import org.d3if2024.assesment2.R
import org.d3if2024.assesment2.databinding.FragmentCelciusToKelvinBinding
import org.d3if2024.assesment2.db.SuhuDb


class CelciusToKelvinFragment : Fragment() {
    private val viewModel: CelciusToKelvinViewModel by lazy{
        val db = SuhuDb.getInstance(requireContext())
        val factory = CelciusToKelvinViewModelFactory(db.itemDao)
        ViewModelProvider(this,factory)[CelciusToKelvinViewModel::class.java]
    }

    private lateinit var binding: FragmentCelciusToKelvinBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentCelciusToKelvinBinding.inflate(layoutInflater, container, false)
        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.calculateBtn.setOnClickListener {
            hitungKonversiSuhu()
            convertSuhuKelvin()
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
        val suhuCelcius = binding.celciusToKelvinEditText.text.toString()
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

    private fun convertSuhuKelvin(){
        val stringInTextField = binding.celciusToKelvinEditText.text.toString()
        val suhu = stringInTextField.toDoubleOrNull()

        // Hitung Conversi Suhu Celcius Ke Kelvin
        if (suhu == null || suhu == 0.0){
            displayConvert(0.0)
            return
        }
        var kelvin = suhu+273
        displayConvert(kelvin)
    }

    private fun hitungKonversiSuhu(){
        val suhuCelcius = binding.celciusToKelvinEditText.text.toString()
        viewModel.hitungKonversiSuhuCelciusToKelvin(suhuCelcius.toFloat())
    }


    private fun displayConvert(dataCelcius: Double) {
        binding.result.text = "Nilai Hasil Konversi: ${dataCelcius}" + "°K"
    }
}