package org.d3if2024.assesment2.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import org.d3if2024.assesment2.R
import org.d3if2024.assesment2.databinding.FragmentHomeBinding
import org.d3if2024.assesment2.ui.penemusuhu.PenemuSuhu
import org.d3if2024.assesment2.ui.sejarahsingkat.SejarahSingkat


/**
 * A simple [Fragment] subclass.
 * Use the [HomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HomeFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private lateinit var binding: FragmentHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(layoutInflater, container, false)
        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.celciusToFarenhitBtn.setOnClickListener { findNavController().navigate(R.id.action_homeFragment_to_celciusToFarenhitFragment2) }
        binding.celciusToKelvinBtn.setOnClickListener { findNavController().navigate(R.id.action_homeFragment_to_celciusToKelvinFragment2) }
        binding.celciusToReamurBtn.setOnClickListener { findNavController().navigate(R.id.action_homeFragment_to_celciusToReamurFragment) }
/// Sejarah Singkat Suhu
        binding.sejarahSingkatSuhuBtn.setOnClickListener {
            val intent = Intent(requireActivity(), SejarahSingkat::class.java)
            startActivity(intent)

        }
        binding.fotoPenemuSuhu.setOnClickListener {
            val intent = Intent(requireActivity(), PenemuSuhu::class.java)
            startActivity(intent)
        }
        binding.switchDarkMode.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.histori -> {
                findNavController().navigate(R.id.action_homeFragment_to_historiFragment)
                return true
            }
            R.id.tentang_aplikasi -> {
                findNavController().navigate(R.id.action_homeFragment_to_aboutMeFragment)
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}