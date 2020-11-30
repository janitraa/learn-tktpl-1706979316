package id.ac.ui.cs.mobileprogramming.janitra.tktpl

import android.Manifest
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.content.pm.PackageManager
import android.net.wifi.ScanResult
import android.net.wifi.WifiManager
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import id.ac.ui.cs.mobileprogramming.janitra.tktpl.model.ResponseModel
import id.ac.ui.cs.mobileprogramming.janitra.tktpl.model.WifiModel
import id.ac.ui.cs.mobileprogramming.janitra.tktpl.retrofit.ApiService
import id.ac.ui.cs.mobileprogramming.janitra.tktpl.retrofit.RetrofitClient
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Response

class WifiActivity  : AppCompatActivity() {
    private lateinit var wifiMgr: WifiManager
    private lateinit var scanRslt: List<ScanResult>
    private lateinit var recyclerView: RecyclerView
    private lateinit var btnScan: Button
    private lateinit var btnSend: Button
    private lateinit var retrofit: ApiService
    private val wifiList = mutableListOf<WifiModel>()

    private val wifiScanRcvr = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            if (intent?.action!! == WifiManager.SCAN_RESULTS_AVAILABLE_ACTION) {
                scanRslt = wifiMgr.scanResults
            }
            val success = intent.getBooleanExtra(WifiManager.EXTRA_RESULTS_UPDATED, false)
            if (success) {
                sucessed()
            } else {
                failed()
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_wifi)

        wifiMgr = getSystemService(Context.WIFI_SERVICE) as WifiManager
        btnScan = findViewById(R.id.btnScan)
        btnSend = findViewById(R.id.btnSend)
        recyclerView = findViewById(R.id.recycler_view)
        retrofit = RetrofitClient.RETROFIT_SERVICE

        if(checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED)
        {
            requestPermissions(arrayOf(Manifest.permission.ACCESS_COARSE_LOCATION), 87)
        }

        btnScan.setOnClickListener {
            val intentFilter = IntentFilter(WifiManager.SCAN_RESULTS_AVAILABLE_ACTION)
            registerReceiver(wifiScanRcvr, intentFilter)
            wifiMgr.isWifiEnabled = true
            wifiMgr.startScan()
        }

        btnSend.setOnClickListener {
            GlobalScope.launch(Main) {
                val response: Response<ResponseModel> = retrofit.submitList(wifiList)
                if (response.isSuccessful) {
                    Toast.makeText(this@WifiActivity, "Submitted to request bin", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this@WifiActivity, "Failed to post wifi", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun sucessed() {
        val result = wifiMgr.scanResults
        for (res in result) {
            val wifiModel = WifiModel()
            wifiModel.name = res.SSID
            wifiList.add(wifiModel)
        }
        recyclerView.layoutManager = LinearLayoutManager(this)
        val recyclerAdapter =  RecyclerAdapter(this, result)
        runOnUiThread {
            recyclerAdapter.notifyDataSetChanged()
        }
        recyclerView.adapter = recyclerAdapter
        btnSend.visibility = View.VISIBLE
        unregisterReceiver(wifiScanRcvr)
    }

    private fun failed() {
        Toast.makeText(this@WifiActivity, "Failed to get wifi nearby", Toast.LENGTH_SHORT).show()
    }
}