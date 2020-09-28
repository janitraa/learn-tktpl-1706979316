package id.ac.ui.cs.mobileprogramming.janitra.tktpl.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class DummyMotivationDao {
    private val motivationList = mutableListOf<Motivation>()
    private val motivations = MutableLiveData<List<Motivation>>()

    init {
        motivations.value = motivationList
    }

    fun addMotivation(motivation: Motivation) {
        motivationList.add(motivation)
        motivations.value = motivationList
    }

    fun getMotivations() = motivations as LiveData<List<Motivation>>
}