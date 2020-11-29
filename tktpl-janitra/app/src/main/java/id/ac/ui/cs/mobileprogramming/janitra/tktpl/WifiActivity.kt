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
    private lateinit var wifiManager: WifiManager
    private lateinit var mScanResult: List<ScanResult>
    private lateinit var recyclerView: RecyclerView
    private lateinit var btn: Button
    private lateinit var retrofit: ApiService
    private val listWifi = mutableListOf<WifiModel>()
    private val wifiScanReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            if (intent?.action!! == WifiManager.SCAN_RESULTS_AVAILABLE_ACTION) {
                mScanResult = wifiManager.scanResults
            }
            val success = intent.getBooleanExtra(WifiManager.EXTRA_RESULTS_UPDATED, false)
            if (success) {
                scanSuccess()
            } else {
                scanFail()
            }
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_wifi)
        wifiManager = getSystemService(Context.WIFI_SERVICE) as WifiManager
        btn = findViewById(R.id.button)
        recyclerView = findViewById(R.id.recycler_view)
        retrofit = RetrofitClient.RETROFIT_SERVICE

        if(checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED)
        {
            requestPermissions(arrayOf(Manifest.permission.ACCESS_COARSE_LOCATION), 87)
        }

        val intentFilter = IntentFilter(WifiManager.SCAN_RESULTS_AVAILABLE_ACTION)
        registerReceiver(wifiScanReceiver, intentFilter)
        wifiManager.isWifiEnabled = true
        wifiManager.startScan()

        btn.setOnClickListener {
            GlobalScope.launch(Main) {
                val response: Response<ResponseModel> = retrofit.submitList(listWifi)
                if (response.isSuccessful) {
                    Toast.makeText(this@WifiActivity, "List of wifi nearby submitted to request bin", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this@WifiActivity, "Failed to post wifi list", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun scanSuccess() {
        val result = wifiManager.scanResults
        for (res in result) {
            val wifiModel = WifiModel()
            wifiModel.name = res.SSID
            listWifi.add(wifiModel)
        }
        recyclerView.layoutManager = LinearLayoutManager(this)
        val recyclerAdapter =  RecyclerAdapter(this, result)
        recyclerView.adapter = recyclerAdapter
        btn.visibility = View.VISIBLE
        unregisterReceiver(wifiScanReceiver)
    }

    private fun scanFail() {
        Toast.makeText(this@WifiActivity, "Failed to get list of wifi nearby", Toast.LENGTH_SHORT).show()
    }
}