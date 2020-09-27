package id.ac.ui.cs.mobileprogramming.janitra.tktpl.data

// Private primary constructor inaccessible from other classes
class DummyDatabase private constructor() {

    // All the DAOs go here!
    var motivationDao = DummyMotivationDao()
        private set

    companion object {
        // @Volatile - Writes to this property are immediately visible to other threads
        @Volatile private var instance: DummyDatabase? = null

        // The only way to get hold of the DummyDatabase object
        fun getInstance() =
        // Already instantiated? - return the instance
            // Otherwise instantiate in a thread-safe manner
            instance ?: synchronized(this) {
                // If it's still not instantiated, finally create an object
                // also set the "instance" property to be the currently created one
                instance ?: DummyDatabase().also { instance = it }
            }
    }
}