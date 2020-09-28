package id.ac.ui.cs.mobileprogramming.janitra.tktpl.motivation

import id.ac.ui.cs.mobileprogramming.janitra.tktpl.data.DummyMotivationDao
import id.ac.ui.cs.mobileprogramming.janitra.tktpl.data.Motivation

class MotivationRepository private constructor(private val motivationDao: DummyMotivationDao) {

    fun addMotivation(motivation: Motivation) {
        motivationDao.addMotivation(motivation)
    }

    fun getMotivation() = motivationDao.getMotivations()

    companion object {
        @Volatile private var instance: MotivationRepository? = null

        fun getInstance(motivationDao: DummyMotivationDao) =
            instance ?: synchronized(this) {
                instance ?: MotivationRepository(motivationDao).also { instance = it }
            }
    }
}