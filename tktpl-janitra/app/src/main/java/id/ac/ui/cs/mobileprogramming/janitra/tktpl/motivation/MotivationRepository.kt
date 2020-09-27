package id.ac.ui.cs.mobileprogramming.janitra.tktpl.motivation

import id.ac.ui.cs.mobileprogramming.janitra.tktpl.data.DummyMotivationDao
import id.ac.ui.cs.mobileprogramming.janitra.tktpl.data.Motivation

// FakeQuoteDao must be passed in - it is a dependency
// You could also instantiate the DAO right inside the class without all the fuss, right?
// No. This would break testability - you need to be able to pass a mock version of a DAO
// to the repository (e.g. one that upon calling getQuotes() returns a dummy list of quotes for testing)
// This is the core idea behind DEPENDENCY INJECTION - making things completely modular and independent.
class MotivationRepository private constructor(private val motivationDao: DummyMotivationDao) {

    // This may seem redundant.
    // Imagine a code which also updates and checks the backend.
    fun addMotivation(motivation: Motivation) {
        motivationDao.addMotivation(motivation)
    }

    fun getMotivation() = motivationDao.getMotivations()

    companion object {
        // Singleton instantiation you already know and love
        @Volatile private var instance: MotivationRepository? = null

        fun getInstance(motivationDao: DummyMotivationDao) =
            instance ?: synchronized(this) {
                instance ?: MotivationRepository(motivationDao).also { instance = it }
            }
    }
}