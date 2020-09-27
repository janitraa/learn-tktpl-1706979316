package id.ac.ui.cs.mobileprogramming.janitra.tktpl.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class DummyMotivationDao {
    // A fake database table
    private val motivationList = mutableListOf<Motivation>()
    // MutableLiveData is from the Architecture Components Library
    // LiveData can be observed for changes
    private val motivations = MutableLiveData<List<Motivation>>()

    init {
        // Immediately connect the now empty quoteList
        // to the MutableLiveData which can be observed
        motivations.value = motivationList
    }

    fun addMotivation(motivation: Motivation) {
        motivationList.add(motivation)
        // After adding a quote to the "database",
        // update the value of MutableLiveData
        // which will notify its active observers
        motivations.value = motivationList
    }

    // Casting MutableLiveData to LiveData because its value
    // shouldn't be changed from other classes
    fun getMotivations() = motivations as LiveData<List<Motivation>>
}