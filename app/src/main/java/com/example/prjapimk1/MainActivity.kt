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

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val executor = Executors.newSingleThreadExecutor()
        executor.execute{

            val url = URL("https://wordapidata.000webhostapp.com/?getuserdb")
            val json = url.readText()

                val userList = Gson().fromJson(json,Array<User>::class.java).toList();

            Handler(Looper.getMainLooper()).post{
                Log.d("AddNewUser", "Plan Json vars" + json.toString())
                Log.d("AddNewUser", "converted from")

            }




        }





    }
}