package com.a2411500032.inventory

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RegisterActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        val etUsername = findViewById<EditText>(R.id.etRegisterUsername)
        val etPassword = findViewById<EditText>(R.id.etRegisterPassword)
        val btnRegister = findViewById<Button>(R.id.btnRegister)
        val tvToLogin = findViewById<TextView>(R.id.tvToLogin)

        btnRegister.setOnClickListener {
            val user = etUsername.text.toString()
            val pass = etPassword.text.toString()

            if (user.isEmpty() || pass.isEmpty()) {
                Toast.makeText(this, "Harap isi username dan password", Toast.LENGTH_SHORT).show()
            } else {
                // Proses pendaftaran ke API
                prosesRegister(user, pass)
            }
        }

        // Kembali ke halaman login
        tvToLogin.setOnClickListener {
            finish() // Menutup activity ini dan kembali ke activity sebelumnya (Login)
        }
    }

    private fun prosesRegister(user: String, pass: String) {
        RetrofitClient.instance.register(user, pass).enqueue(object : Callback<ResponseModel> {
            override fun onResponse(call: Call<ResponseModel>, response: Response<ResponseModel>) {
                if (response.isSuccessful) {
                    val res = response.body()
                    if (res?.status == "success") {
                        Toast.makeText(this@RegisterActivity, "Berhasil Daftar! Silakan Login", Toast.LENGTH_LONG).show()
                        finish() // Tutup halaman register
                    } else {
                        Toast.makeText(this@RegisterActivity, res?.message, Toast.LENGTH_SHORT).show()
                    }
                }
            }

            override fun onFailure(call: Call<ResponseModel>, t: Throwable) {
                Toast.makeText(this@RegisterActivity, "Error: ${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }
}