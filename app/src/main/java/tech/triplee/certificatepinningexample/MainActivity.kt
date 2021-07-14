package tech.triplee.certificatepinningexample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import okhttp3.*
import java.io.IOException

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val certificatePinner = CertificatePinner.Builder().add("triplee.tech", "sha256/AAAA").build()
        val client = OkHttpClient.Builder().certificatePinner(certificatePinner).build()
        client.newCall(Request.Builder().url("https://triplee.tech").build()).enqueue(object :
            Callback {
            override fun onFailure(call: Call, e: IOException) {
                e.printStackTrace()
            }

            override fun onResponse(call: Call, response: Response) {
                response.use {
                    if (!response.isSuccessful) throw IOException("Unexpected code $response")

                    for ((name, value) in response.headers) {
                        println("$name: $value")
                    }

                    println(response.body!!.string())
                }
            }
        })
        setContentView(R.layout.activity_main)
    }
}