package com.example.insert_update_with_one_time_login

import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_view_all.*
import kotlinx.android.synthetic.main.custom_dialog.*

class ViewAllActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_all)
        refreshRecycler()
    }
    private fun refreshRecycler() {
        var db=DBHelper(this)
        var arr=db.retriveAll()
        var vehicleadapter = VehicleAdapter(this,arr)
        myrecycle.adapter = vehicleadapter
    }

    fun update(position: Int)
    {
        var db=DBHelper(this)
        var arr:ArrayList<Vehicle> = db.retriveAll()
        var vehicle = arr.get(position)
        var dialog = Dialog(this)
        dialog.setCancelable(false)
        dialog.setContentView(R.layout.custom_dialog)
        dialog.txtUpadateId.setText(vehicle.v_id.toString())
        dialog.edtUpdateVName.setText(vehicle.v_name)
        dialog.edtUpdateVMillage.setText(vehicle.v_millage.toString())
        dialog.edtUpdateVFuecap.setText(vehicle.v_fuecap.toString())

        dialog.btnUpdate.setOnClickListener {
            var id = dialog.txtUpadateId.text.toString().toInt()
            var name = dialog.edtUpdateVName.text.toString()
            var millage = dialog.edtUpdateVMillage.text.toString().toInt()
            var fuecap = dialog.edtUpdateVFuecap.text.toString().toInt()
            var vehicle = Vehicle(id,name,millage,fuecap)
            db.update(vehicle)
            dialog.dismiss()
            refreshRecycler()
        }
        dialog.show()
    }
}