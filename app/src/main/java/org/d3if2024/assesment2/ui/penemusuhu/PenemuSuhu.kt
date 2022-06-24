package org.d3if2024.assesment2.ui.penemusuhu

import android.app.NotificationManager
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
import org.d3if2024.assesment2.R
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
            binding.progressBar.visibility = View.VISIBLE
            Toast.makeText(this, "Please Activated You Interenet Connection", Toast.LENGTH_SHORT)
                .show()
        }
//        var builder =  NotificationCompat.Builder(this, CHANNEL_ID)
//            .setSmallIcon(R.drawable.celcius)
//            .setContentTitle("My notification")
//            .setContentText("Much longer text that cannot fit one line...")
//            .setStyle(NotificationCompat.BigTextStyle()
//                .bigText("Much longer text that cannot fit one line..."))
//            .setPriority(NotificationCompat.PRIORITY_DEFAULT)

    }


    private fun checkForInternet(context: Context): Boolean {

        // register activity with the connecti vity manager service
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        // if the android version is equal to M
        // or greater we need to use the
        // NetworkCapabilities to check what type of
        // network has the internet connection
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

            // Returns a Network object corresponding to
            // the currently active default data network.
            val network = connectivityManager.activeNetwork ?: return false

            // Representation of the capabilities of an active network.
            val activeNetwork = connectivityManager.getNetworkCapabilities(network) ?: return false

            return when {
                // Indicates this network uses a Wi-Fi transport,
                // or WiFi has network connectivity
                activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true

                // Indicates this network uses a Cellular transport. or
                // Cellular has network connectivity
                activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true

                // else return false
                else -> false
            }
        } else {
            // if the android version is below M
            @Suppress("DEPRECATION") val networkInfo =
                connectivityManager.activeNetworkInfo ?: return false
            @Suppress("DEPRECATION")
            return networkInfo.isConnected
        }
    }


}