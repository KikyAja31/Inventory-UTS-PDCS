package com.a2411500032.inventory

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DashboardActivity : AppCompatActivity() {

    private lateinit var rvBarang: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        rvBarang = findViewById(R.id.rvBarang)
        rvBarang.layoutManager = LinearLayoutManager(this)

        ambilDataBarang()
    }

    private fun ambilDataBarang() {
        RetrofitClient.instance.getBarang().enqueue(object : Callback<BarangResponse> {
            override fun onResponse(call: Call<BarangResponse>, response: Response<BarangResponse>) {
                if (response.isSuccessful) {
                    val listData = response.body()?.data
                    if (listData != null) {
                        // Memasang data ke Adapter
                        rvBarang.adapter = BarangAdapter(listData)
                    }
                } else {
                    Toast.makeText(this@DashboardActivity, "Gagal mengambil data", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<BarangResponse>, t: Throwable) {
                Toast.makeText(this@DashboardActivity, "Kesalahan: ${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }
}