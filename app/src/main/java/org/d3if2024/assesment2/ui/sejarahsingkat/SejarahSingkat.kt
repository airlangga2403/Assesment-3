package org.d3if2024.assesment2.ui.sejarahsingkat

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_sejarah_singkat.*
import org.d3if2024.assesment2.R
import org.d3if2024.assesment2.adapter.SejarahAdapter
import org.d3if2024.assesment2.network.ApiInterfacePenemuSuhu
import org.d3if2024.assesment2.network.DataPenemuSuhuItem
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

const val BASE_URL = "https://mocki.io/"


class SejarahSingkat : AppCompatActivity() {
    private var progressBar: ProgressBar? = null
    lateinit var sejarahAdapter: SejarahAdapter
    lateinit var linearLayoutManager: LinearLayoutManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sejarah_singkat)
        progressBar = findViewById<ProgressBar>(R.id.progressBar) as ProgressBar
        recyclerview.setHasFixedSize(true)

        linearLayoutManager = LinearLayoutManager(this)
        recyclerview.layoutManager = linearLayoutManager
        getMyData()
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
                    progressBar?.visibility = View.VISIBLE
                    sejarahAdapter = SejarahAdapter(baseContext, responseBody)
                    sejarahAdapter.notifyDataSetChanged()
                    recyclerview.adapter = sejarahAdapter
                }
//                progressBar?.visibility = View.INVISIBLE

            }

            override fun onFailure(call: Call<List<DataPenemuSuhuItem>?>, t: Throwable) {
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