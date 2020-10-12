package id.ac.ui.cs.mobileprogramming.janitra.tktpl.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "contact_details")
data class Contacts (
    @ColumnInfo(name = "name") var name: String,
    @ColumnInfo(name = "phone") var phone: Int,
    @ColumnInfo(name = "address") var address: String,
    @ColumnInfo(name = "company") var company: String,
    @ColumnInfo(name = "website") var website: String,
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id") var id: Int = 0)