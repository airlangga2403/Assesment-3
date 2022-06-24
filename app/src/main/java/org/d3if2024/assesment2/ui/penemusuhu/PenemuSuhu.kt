package org.d3if2024.assesment2.ui.penemusuhu

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.penemu_suhu.*
import org.d3if2024.assesment2.databinding.PenemuSuhuBinding

class PenemuSuhu : AppCompatActivity() {
    private lateinit var binding: PenemuSuhuBinding
    private val CHANNEL_ID = "i.apps.notifications"
    private val description = "Test notification"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = PenemuSuhuBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.progressBar.visibility = View.VISIBLE
        if (checkForInternet(this)) {
            binding.progressBar.visibility = View.GONE
            Picasso.get()
                .load("https://upload.wikimedia.org/wikipedia/commons/thumb/8/81/Headshot_of_Anders_Celsius.jpg/791px-Headshot_of_Anders_Celsius.jpg")
                .into(img11)
            Picasso.get()
                .load("https://upload.wikimedia.org/wikipedia/commons/thumb/b/b1/Rene_reaumur.jpg/330px-Rene_reaumur.jpg")
                .into(img12)
            Picasso.get()
                .load("https://upload.wikimedia.org/wikipedia/commons/b/bd/Fahrenheit_small.jpg")
                .into(img13)
            Picasso.get()
                .load("https://upload.wikimedia.org/wikipedia/commons/thumb/a/a0/Lord_Kelvin_photograph.jpg/330px-Lord_Kelvin_photograph.jpg")
                .into(img14)
        } else {
            binding.scrollview1.visibility = View.GONE
            binding.notficationConnectivity.visibility = View.VISIBLE
            binding.progressBar.visibility = View.VISIBLE
            Toast.makeText(this, "Please Activated You Interenet Connection", Toast.LENGTH_SHORT)
                .show()
        }
    }


    private fun checkForInternet(context: Context): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val network = connectivityManager.activeNetwork ?: return false
            val activeNetwork = connectivityManager.getNetworkCapabilities(network) ?: return false

            return when {
                activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                else -> false
            }
        } else {
            @Suppress("DEPRECATION") val networkInfo =
                connectivityManager.activeNetworkInfo ?: return false
            @Suppress("DEPRECATION")
            return networkInfo.isConnected
        }
    }


}