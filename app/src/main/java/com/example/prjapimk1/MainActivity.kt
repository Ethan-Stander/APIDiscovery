package com.example.prjapimk1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import com.google.gson.Gson
import java.net.URL
import java.util.concurrent.Executors
import android.widget.TextView
import com.github.kittinunf.fuel.core.extensions.jsonBody
import com.github.kittinunf.fuel.httpPost

data class PostUser(var ID:String, var Amount:String, var PractiseID: String) {
}
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
            Handler(Looper.getMainLooper()).post{
                show.text = stringBuilder.toString()
            }
            Post()
        }
    }
    fun Post()
    {

        val executor = Executors.newSingleThreadExecutor()
        val user = PostUser("12345","100","12345")
        val (_,_, result) = "https://opsc.azurewebsites.net/Dis.php".httpPost()
            .jsonBody(Gson().toJson(user).toString())
            .responseString()
        Handler(Looper.getMainLooper()).post{
            var Text = findViewById<TextView>(R.id.txtOutput)
            Text.setText(result.toString())
        }


    }
}
