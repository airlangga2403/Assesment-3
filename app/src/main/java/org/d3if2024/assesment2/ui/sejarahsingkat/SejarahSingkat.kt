package org.d3if2024.assesment2.ui.sejarahsingkat

import android.app.Notification
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_sejarah_singkat.*
import kotlinx.android.synthetic.main.row_items.*
import org.d3if2024.assesment2.MainActivity
import org.d3if2024.assesment2.R
import org.d3if2024.assesment2.adapter.SejarahAdapter
import org.d3if2024.assesment2.databinding.ActivitySejarahSingkatBinding
import org.d3if2024.assesment2.network.ApiInterfacePenemuSuhu
import org.d3if2024.assesment2.network.DataPenemuSuhuItem
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

const val BASE_URL = "https://mocki.io/"

class SejarahSingkat : AppCompatActivity() {
    private val CHANNEL_ID = "i.apps.notifications"
    lateinit var sejarahAdapter: SejarahAdapter
    private lateinit var binding: ActivitySejarahSingkatBinding
    lateinit var linearLayoutManager: LinearLayoutManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySejarahSingkatBinding.inflate(layoutInflater)
        setContentView(binding.root)
        recyclerview.setHasFixedSize(true)
        linearLayoutManager = LinearLayoutManager(this)
        recyclerview.layoutManager = linearLayoutManager
        binding.progressBar.visibility = View.VISIBLE
        if (checkForInternet(this)){
//            binding.progressBar.visibility = View.GONE
            getMyData()
//            var builder = NotificationCompat.Builder(this, CHANNEL_ID)
//                .setSmallIcon(R.drawable.celcius)
//                .setContentTitle("textTitle")
//                .setContentText("textContent")
//                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
//
//            Notification.Builder(MainActivity.this, "channel_id")

        } else {
            binding.progressBar.visibility = View.VISIBLE
            Toast.makeText(this, "Please Activated You Interenet Connection", Toast.LENGTH_SHORT).show()
        }

    }
    private fun checkForInternet(context: Context): Boolean {

        // register activity with the connectivity manager service
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

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


    private fun getMyData() {
        val retrofitBuilder = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
            .create(ApiInterfacePenemuSuhu::class.java)

        val retrofitData = retrofitBuilder.getData()
//        ENQUUE CTRL + SHIFT + SPACE
        retrofitData.enqueue(object : Callback<List<DataPenemuSuhuItem>?> {
            override fun onResponse(
                call: Call<List<DataPenemuSuhuItem>?>,
                response: Response<List<DataPenemuSuhuItem>?>
            ) {
                val responseBody = response.body()!!
                if (response.isSuccessful) {
                    binding.progressBar.visibility = View.GONE
                    binding.progressBar.visibility = View.INVISIBLE
                    sejarahAdapter = SejarahAdapter(baseContext, responseBody)
                    sejarahAdapter.notifyDataSetChanged()
                    recyclerview.adapter = sejarahAdapter
                }
            }

            override fun onFailure(call: Call<List<DataPenemuSuhuItem>?>, t: Throwable) {
                binding.progressBar.visibility = View.VISIBLE
                Log.d("SEJARAH SINGKAT", "ONFAILURE" + t.message)
            }
        })
    }

//    private fun getMyData() {
//        val retrofitBuilder = Retrofit.Builder()
//            .addConverterFactory(GsonConverterFactory.create())
//            .baseUrl(BASE_URL)
//            .build()
//            .create(ApiInterface::class.java)
//
//        val retrofitData = retrofitBuilder.getData()
////        ENQUUE CTRL + SHIFT + SPACE
//        retrofitData.enqueue(object : Callback<List<MyDataItem>?> {
//            override fun onResponse(
//                call: Call<List<MyDataItem>?>,
//                response: Response<List<MyDataItem>?>
//            ) {
//                val responseBody = response.body()!!
//                val myStringBuilder = StringBuilder()
//                for (myData in responseBody){
//                    myStringBuilder.append(myData.title)
//                    myStringBuilder.append("\n")
//                    myStringBuilder.append(myData.body)
//                    myStringBuilder.append("\n")
//                }
//                textView2.text = myStringBuilder
//            }
//
//            override fun onFailure(call: Call<List<MyDataItem>?>, t: Throwable) {
//                Log.d("SEJARAH SINGKAT","ONFAILURE"+t.message)
//            }
//        })
//    }
}