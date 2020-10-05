package id.ac.ui.cs.mobileprogramming.janitra.tktpl

import android.os.Bundle
import android.view.MenuItem
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.squareup.picasso.MemoryPolicy
import com.squareup.picasso.NetworkPolicy
import com.squareup.picasso.Picasso
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.newSingleThreadContext

class TimekeeperActivity : AppCompatActivity() {

    @kotlinx.coroutines.ObsoleteCoroutinesApi
    var thread = newSingleThreadContext("test") as CoroutineDispatcher

    @kotlinx.coroutines.ObsoleteCoroutinesApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_stopwatch)

        var imageView: ImageView = findViewById(R.id.question_image)
        var yesButton: Button = findViewById(R.id.yes_button)
        var noButton: Button = findViewById(R.id.no_button)

        getSupportActionBar()?.setDisplayHomeAsUpEnabled(true);
        getSupportActionBar()?.setDisplayShowHomeEnabled(true);

        Picasso.get().setLoggingEnabled(true)
        yesButton.setOnClickListener {
            triggerANR()
            Picasso.get()
                .load("https://www.pngkey.com/png/full/352-3525258_dont-symbol-clipart-best-casino.png")
                .placeholder(R.drawable.ic_noun_loading)
                .resize(64, 64)
                .memoryPolicy(MemoryPolicy.NO_CACHE, MemoryPolicy.NO_STORE)
                .networkPolicy(NetworkPolicy.NO_CACHE, NetworkPolicy.NO_STORE)
                .centerCrop()
                .into(imageView)
        }

        noButton.setOnClickListener {
            triggerANR()
            Picasso.get()
                .load("https://img.pngio.com/free-great-job-png-free-great-jobpng-transparent-images-15006-good-job-png-1400_1163.png")
                .placeholder(R.drawable.ic_noun_loading)
                .resize(64, 64)
                .memoryPolicy(MemoryPolicy.NO_CACHE, MemoryPolicy.NO_STORE)
                .networkPolicy(NetworkPolicy.NO_CACHE, NetworkPolicy.NO_STORE)
                .centerCrop()
                .into(imageView)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.getItemId() == android.R.id.home) {
            finish()
        }
        return super.onOptionsItemSelected(item)
    }

    @kotlinx.coroutines.ObsoleteCoroutinesApi
    fun triggerANR() = GlobalScope.launch(thread) {
        while (true) {
            Thread.sleep(1000000)
            break
        }
    }

    override fun onBackPressed() {
        Toast.makeText(getApplicationContext(), "Use Exit Button!", Toast.LENGTH_SHORT).show();
    }
}