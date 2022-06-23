package org.d3if2024.assesment2.ui.penemusuhu

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.penemu_suhu.*
import org.d3if2024.assesment2.R

class PenemuSuhu : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.penemu_suhu)
        Picasso.get()
            .load("https://upload.wikimedia.org/wikipedia/commons/thumb/8/81/Headshot_of_Anders_Celsius.jpg/791px-Headshot_of_Anders_Celsius.jpg")
            .into(img11)
        Picasso.get()
            .load("https://upload.wikimedia.org/wikipedia/commons/thumb/b/b1/Rene_reaumur.jpg/330px-Rene_reaumur.jpg")
            .into(img12);
        Picasso.get()
            .load("https://upload.wikimedia.org/wikipedia/commons/b/bd/Fahrenheit_small.jpg")
            .into(img13);
        Picasso.get()
            .load("https://upload.wikimedia.org/wikipedia/commons/thumb/a/a0/Lord_Kelvin_photograph.jpg/330px-Lord_Kelvin_photograph.jpg")
            .into(img14);
    }



}