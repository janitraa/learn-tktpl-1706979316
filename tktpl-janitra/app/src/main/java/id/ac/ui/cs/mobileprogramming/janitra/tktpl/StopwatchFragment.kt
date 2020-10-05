package id.ac.ui.cs.mobileprogramming.janitra.tktpl

import android.os.Bundle
import android.os.SystemClock
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Chronometer
import androidx.fragment.app.Fragment

class StopwatchFragment: Fragment(), View.OnClickListener {

    private lateinit var chronometer: Chronometer
    private var startStopButton: Button? = null
    private var resetButton: Button? = null
    var running: Boolean = false
    var pause: Long = 0

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val rootView = inflater.inflate(R.layout.fragment_stopwatch, container, false)

        chronometer = rootView.findViewById(R.id.chronometer)
        startStopButton = rootView.findViewById(R.id.start_stop_button)
        resetButton = rootView.findViewById(R.id.reset_button)

        startStopButton!!.setOnClickListener(this)
        resetButton!!.setOnClickListener(this)

        return rootView
    }

    override fun onClick(mView: View) {
        when (mView.id) {
            R.id.start_stop_button -> {
                var button: Button = mView.findViewById(R.id.start_stop_button)
                if (!running) {
                    chronometer.setBase(SystemClock.elapsedRealtime() - pause)
                    chronometer.start()
                    running = true
                    button.setText("stop")
                } else {
                    chronometer.stop()
                    pause = SystemClock.elapsedRealtime() - chronometer.getBase()
                    running = false
                    button.setText("start")
                }
            }
            R.id.reset_button -> {
                chronometer.setBase(SystemClock.elapsedRealtime())
                pause = 0
            }
        }
    }
}