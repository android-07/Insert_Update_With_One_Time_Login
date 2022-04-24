package com.example.insert_update_with_one_time_login

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DBHelper(var context: Context): SQLiteOpenHelper(context, DB_NAME,null, DB_VERSION) {
    companion object {
        private var DB_NAME = "VehicleDB"
        private var TB_NAME = "VehicleDetail"
        private var DB_VERSION = 2
        private var V_ID = "id"
        private var V_NAME = "v_name"
        private var V_MILLAGE = "v_millage"
        private var V_FUECAP = "v_fuecap"
    }

    override fun onCreate(p0: SQLiteDatabase?) {
        var query =
            "CREATE TABLE $TB_NAME($V_ID INTEGER PRIMARY KEY AUTOINCREMENT, $V_NAME TEXT, $V_MILLAGE INTEGER, $V_FUECAP INTEGER)";
        p0?.execSQL(query);
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        var query = "DROP TABLE $TB_NAME IF EXISTS"
        p0?.execSQL(query)
        onCreate(p0)
    }

    fun insert(Vehicle: Vehicle): Boolean {
        var db = writableDatabase
        var cv = ContentValues()
        cv.put(V_NAME, Vehicle.v_name)
        cv.put(V_MILLAGE, Vehicle.v_millage)
        cv.put(V_FUECAP, Vehicle.v_fuecap)

        var flag = db.insert(TB_NAME, null, cv)
        if (flag > 0) {
            return true
        } else {
            return false
        }
    }

    fun retriveAll(): ArrayList<Vehicle> {
        var db = readableDatabase
        var cursor = db.query(TB_NAME, null, null, null, null, null, null)
        var arr: ArrayList<Vehicle> = ArrayList()
        while (cursor.moveToNext()) {
            var id = cursor.getInt(0)
            var name = cursor.getString(1)
            var millage = cursor.getInt(2)
            var fuecap = cursor.getInt(3)
            var vehicle = Vehicle(id, name, millage, fuecap)
            arr.add(vehicle)
        }
        return arr
    }

    fun update(Vehicle: Vehicle)
    {
        var db=writableDatabase
        var cv= ContentValues()
        cv.put(V_ID,Vehicle.v_id)
        cv.put(V_NAME,Vehicle.v_name)
        cv.put(V_MILLAGE,Vehicle.v_millage)
        cv.put(V_FUECAP,Vehicle.v_fuecap)
        db.update(TB_NAME,cv,"$V_ID=${Vehicle.v_id}",null)
        db.close()
    }

}
