package id.ac.ui.cs.mobileprogramming.janitra.tktpl.data

class DummyDatabase private constructor() {

    var motivationDao = DummyMotivationDao()
        private set

    companion object {
        @Volatile private var instance: DummyDatabase? = null

        fun getInstance() =
            instance ?: synchronized(this) {
                instance ?: DummyDatabase().also { instance = it }
            }
    }
}