package rajeshkadiri.weathertestapi.utils

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.DatabaseUtils
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import rajeshkadiri.weathertestapi.model.Cities
import java.util.*

class DBHelper(context: Context?) : SQLiteOpenHelper(context, DATABASE_NAME, null, 1) {
    private val hp: HashMap<*, *>? = null
    override fun onCreate(db: SQLiteDatabase) {
        // TODO Auto-generated method stub
        db.execSQL(
                "create table wheather " +
                        "(id integer primary key, lat text,lng text,place text,timestamp text)"
        )
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        // TODO Auto-generated method stub
        db.execSQL("DROP TABLE IF EXISTS wheather")
        onCreate(db)
    }

    fun insertContact(lat: String?, lng: String?, place: String?, timestamp: String?): Boolean {
        val db = this.writableDatabase
        val contentValues = ContentValues()
        //  contentValues.put("name", name);
        //  contentValues.put("phone", phone);
        contentValues.put("lat", lat)
        contentValues.put("lng", lng)
        contentValues.put("place", place)
        contentValues.put("timestamp", timestamp)
        db.insert("wheather", null, contentValues)
        return true
    }

    fun getData(timestamp: String): Cursor {
        val db = this.readableDatabase
        return db.rawQuery("select * from wheather where timestamp='$timestamp'", null)
    }

    fun numberOfRows(): Int {
        val db = this.readableDatabase
        return DatabaseUtils.queryNumEntries(db, CITIES_TABLE_NAME).toInt()
    }

    /* public boolean updateContact (Integer id, String name, String phone, String email, String street,String place) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", name);
        contentValues.put("phone", phone);
        contentValues.put("email", email);
        contentValues.put("street", street);
        contentValues.put("place", place);
        db.update("contacts", contentValues, "id = ? ", new String[] { Integer.toString(id) } );
        return true;
    }*/
    fun deleteContact(timestamp: String): Int {
        val db = this.writableDatabase
        return db.delete("wheather",
                "timestamp = ? ", arrayOf(timestamp))
    }

    val allRecords: Cursor?
        get() {
            val users = ArrayList<Cities>()
            val selectQuery = "SELECT * FROM wheather"
            val db = this.writableDatabase
            val cursor = db.rawQuery(selectQuery, null)
            return if (cursor != null && cursor.moveToFirst()) {
                cursor
            } else {
                null
            }
        }

    companion object {
        const val DATABASE_NAME = "MyDBName.db"
        const val CITIES_TABLE_NAME = "cities"
        const val CONTACTS_COLUMN_ID = "id"
        const val CONTACTS_COLUMN_NAME = "name"
        const val CONTACTS_COLUMN_EMAIL = "email"
        const val CONTACTS_COLUMN_STREET = "street"
        const val CONTACTS_COLUMN_CITY = "place"
        const val CONTACTS_COLUMN_PHONE = "phone"
    }
}