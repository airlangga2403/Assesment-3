package org.d3if2024.assesment2.ui.histori

import android.app.NotificationManager
import android.os.Bundle
import android.view.*
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import org.d3if2024.assesment2.R
import org.d3if2024.assesment2.adapter.HistoriAdapter
import org.d3if2024.assesment2.databinding.FragmentHistoriBinding
import org.d3if2024.assesment2.db.SuhuDb
import org.d3if2024.assesment2.helper.BaseApplication


class HistoriFragment : Fragment() {
    private val viewModel: HistoriViewModel by lazy{
        val db = SuhuDb.getInstance(requireContext())
        val factory = HistoriViewModelFactory(db.itemDao)
        ViewModelProvider(this,factory)[HistoriViewModel::class.java]
    }
    private lateinit var notificationManager: NotificationManagerCompat

    private lateinit var binding: FragmentHistoriBinding
    private lateinit var myAdapter: HistoriAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHistoriBinding.inflate(
            layoutInflater,
            container, false
        )
        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        myAdapter = HistoriAdapter()
        with(binding.recyclerView) {
            addItemDecoration(DividerItemDecoration(context, RecyclerView.VERTICAL))
            adapter = myAdapter
            setHasFixedSize(true)
        }

        notificationManager = NotificationManagerCompat.from(requireActivity())

        viewModel.data.observe(viewLifecycleOwner, {
            binding.emptyView.visibility = if (it.isEmpty()) View.VISIBLE else View.GONE
            myAdapter.submitList(it)
        })
    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.histori_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.menu_hapus){
            hapusData()
            return true
        }
        return super.onOptionsItemSelected(item)
    }


    private fun hapusData() {
        MaterialAlertDialogBuilder(requireContext())
            .setMessage(R.string.konfirmasi_hapus)
            .setPositiveButton(getString(R.string.hapus)) { _, _ ->
                viewModel.hapusData()
                val builder = NotificationCompat.Builder(requireActivity(), BaseApplication.CHANNEL_ID)
                    .setSmallIcon(R.drawable.celcius)
                    .setContentTitle("Hapus Data Histori")
                    .setContentText("Anda Telah Menghapus Data Histori")
                    .setPriority(NotificationCompat.PRIORITY_HIGH)
                    .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                val notification = builder.build()
                notificationManager.notify(1, notification)
            }
            .setNegativeButton(getString(R.string.batal)) { dialog, _ ->
                dialog.cancel()
            }
            .show()
    }

}