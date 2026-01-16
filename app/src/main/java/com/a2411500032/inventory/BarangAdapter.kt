package com.a2411500032.inventory

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class BarangAdapter(private val listBarang: List<BarangModel>) :
    RecyclerView.Adapter<BarangAdapter.BarangViewHolder>() {

    class BarangViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvNama = view.findViewById<TextView>(R.id.tvNamaBarang)
        val tvStok = view.findViewById<TextView>(R.id.tvStok)
        val tvHarga = view.findViewById<TextView>(R.id.tvHarga)
        val tvDeskripsi = view.findViewById<TextView>(R.id.tvDeskripsi)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BarangViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_barang, parent, false)
        return BarangViewHolder(view)
    }

    override fun onBindViewHolder(holder: BarangViewHolder, position: Int) {
        val barang = listBarang[position]
        holder.tvNama.text = barang.nama_barang
        holder.tvStok.text = "Stok: ${barang.stok}"
        holder.tvHarga.text = "Rp ${barang.harga}"
        holder.tvDeskripsi.text = barang.deskripsi ?: "Tidak ada deskripsi"
    }

    override fun getItemCount(): Int = listBarang.size
}