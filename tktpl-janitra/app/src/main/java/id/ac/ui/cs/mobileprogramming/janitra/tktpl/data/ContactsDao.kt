package id.ac.ui.cs.mobileprogramming.janitra.tktpl.data

import androidx.lifecycle.LiveData
import androidx.room.*
import androidx.room.OnConflictStrategy.REPLACE

@Dao
interface ContactsDao {

    @Query("SELECT * FROM contact_details ORDER BY name ASC")
    fun getAll(): LiveData<List<@JvmSuppressWildcards Contacts>>

    @Insert(onConflict = REPLACE)
    fun insert(contacts: Contacts)

    @Delete
    fun delete(contacts: Contacts)

    @Update
    fun update(contacts: Contacts)
}