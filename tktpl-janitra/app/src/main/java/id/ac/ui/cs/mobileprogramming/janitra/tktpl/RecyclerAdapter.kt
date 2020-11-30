package id.ac.ui.cs.mobileprogramming.janitra.tktpl

import android.content.Context
import android.net.wifi.ScanResult
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class RecyclerAdapter (var context: Context, var wifiList: List<ScanResult>): RecyclerView.Adapter<RecyclerAdapter.ListHolder>() {

    inner class ListHolder(view: View): RecyclerView.ViewHolder(view) {
        val wifiValue: TextView = view.findViewById(R.id.value)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_wifi, parent, false)
        return ListHolder(itemView)
    }

    override fun onBindViewHolder(holder: ListHolder, position: Int) {
        holder.wifiValue.text = wifiList[position].SSID
    }

    override fun getItemCount(): Int {
        return wifiList.size
    }
}