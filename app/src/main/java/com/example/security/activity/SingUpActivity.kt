package com.example.security.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.security.User
import com.example.security.databinding.ActivitySingUpBinding
import com.example.security.network.RetrofitBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SingUpActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySingUpBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySingUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.singUpBtn.setOnClickListener { singUp() }

    }
    
    private fun singUp(){

        var id = binding.singUpId
        var pw = binding.singUpPw

        val dataPart = HashMap<String, String>()
        dataPart.put("id", id.text.toString())
        dataPart.put("password", pw.text.toString())

        val call = RetrofitBuilder.api.singUp(dataPart)
        call.enqueue( object : Callback<User> {
            override fun onResponse(call: Call<User>, response: Response<User>) {

                if (response.isSuccessful){
                    Log.d("RESPONSESuccess", response.body().toString())
                    Toast.makeText(this@SingUpActivity, "$response", Toast.LENGTH_SHORT).show()
                    startActivity(Intent(this@SingUpActivity, MainActivity::class.java))
                }else{
                    Log.d("RESPONSE", "FAILURE")
                }

            }

            override fun onFailure(call: Call<User>, t: Throwable) {
                Log.d("CONNECTION FAILURE", t.localizedMessage.toString())
            }

        })
        
    }
}