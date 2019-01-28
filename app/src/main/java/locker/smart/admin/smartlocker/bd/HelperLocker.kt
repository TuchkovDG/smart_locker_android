package locker.smart.admin.smartlocker.bd

import android.content.ContentValues
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import locker.smart.admin.smartlocker.App
import locker.smart.admin.smartlocker.model.LockerModel

class HelperLocker : SQLiteOpenHelper(App.instance.applicationContext, DATABASE_NAME, null, DATABASE_VERSION) {

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(("CREATE TABLE " + TABLE_LOCKERS + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY,"
                + COLUMN_UID + " TEXT,"
                + COLUMN_STATUS + " INTEGER" + ")"))
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS $TABLE_LOCKERS")
        onCreate(db)
    }

    fun addOrUpdateLockers(lock: LockerModel) {
        val db = this.writableDatabase

        val cursor = db.query(TABLE_LOCKERS, null, "$COLUMN_UID = ?",
                arrayOf(lock.uid), null, null, null)
        val cv = ContentValues()
        cv.put(COLUMN_UID, lock.uid)
        cv.put(COLUMN_STATUS, lock.status)
        if (cursor.moveToFirst()) {
            db.update(TABLE_LOCKERS, cv, "$COLUMN_UID = ?", arrayOf(lock.uid))
        } else {
            db.insert(TABLE_LOCKERS, null, cv)
        }
        cursor.close()

        db.close()
    }

    fun addOrUpdateLockers(lockers: ArrayList<LockerModel>) {
        val db = this.writableDatabase

        for (item: LockerModel in lockers) {
            val cursor = db.query(TABLE_LOCKERS, null, "$COLUMN_UID = ?",
                    arrayOf(item.uid), null, null, null)
            val cv = ContentValues()
            cv.put(COLUMN_UID, item.uid)
            cv.put(COLUMN_STATUS, item.status)
            if (cursor.moveToFirst()) {
                db.update(TABLE_LOCKERS, cv, "$COLUMN_UID = ?", arrayOf(item.uid))
            } else {
                db.insert(TABLE_LOCKERS, null, cv)
            }
            cursor.close()
        }

        db.close()
    }

    fun getAllLockers(): ArrayList<LockerModel> {
        val db = this.writableDatabase
        val cursor = db.query(TABLE_LOCKERS, null, null,
                null, null, null, null)

        val lockers: ArrayList<LockerModel> = ArrayList()

        if (cursor.moveToFirst()) {

            do {
                lockers.add(LockerModel(
                        cursor.getString(cursor.getColumnIndex(COLUMN_UID)),
                        cursor.getInt(cursor.getColumnIndex(COLUMN_STATUS))))
            } while (cursor.moveToNext())
            cursor.close()
            db.close()
        }

        return lockers
    }

    fun deleteAllLockers() {
        val db = this.writableDatabase
        db.delete(TABLE_LOCKERS, null, null)
        db.close()
    }

    fun deleteLockers(uid: String?) {
        val db = this.writableDatabase
        db.delete(TABLE_LOCKERS, "$COLUMN_UID = ?", arrayOf(uid))
        db.close()
    }

    companion object {

        private const val DATABASE_VERSION = 1
        private const val DATABASE_NAME = "lockersDB.db"
        private const val TABLE_LOCKERS = "lockers"

        private const val COLUMN_ID = "_id"
        private const val COLUMN_UID = "uid"
        private const val COLUMN_STATUS = "status"
    }
}