package com.example.prjapimk1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import com.google.gson.Gson
import java.net.URL
import java.util.concurrent.Executor
import java.util.concurrent.Executors
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val show: TextView = findViewById(R.id.show) // Assuming 'show' is the ID of your TextView in XML layout

        val executor = Executors.newSingleThreadExecutor()
        executor.execute{

            val url = URL("https://wordapidata.000webhostapp.com/?userdb")
            val json = url.readText()

            val userList = Gson().fromJson(json, Array<User>::class.java).toList()

            val stringBuilder = StringBuilder()

            for (user in userList) {
                stringBuilder.append("Name: ${user.Name}\n")
                stringBuilder.append("Password: ${user.Password}\n")
                stringBuilder.append("Image URL: ${user.imageURL}\n\n")
            }

            Handler(Looper.getMainLooper()).post{
                show.text = stringBuilder.toString()
            }
        }
    }
}
