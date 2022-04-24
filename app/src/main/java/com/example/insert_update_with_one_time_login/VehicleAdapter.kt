package com.example.insert_update_with_one_time_login

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.card_item.view.*

class VehicleAdapter(val context: Context, var arr:ArrayList<Vehicle>)
    : RecyclerView.Adapter<VehicleAdapter.PersonViewHolder>()

{
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PersonViewHolder {
        var inflater= LayoutInflater.from(parent.context)
        var view= inflater.inflate(R.layout.card_item,parent,false)
        return PersonViewHolder(view)
    }

    override fun onBindViewHolder(holder: PersonViewHolder, position: Int) {
        holder.bind(arr[position])

        holder.itemView.imgUpdate.setOnClickListener {
            if(context is ViewAllActivity)
            {
                context.update(position)
            }
        }
    }

    override fun getItemCount(): Int {
        return  arr.size
    }

    class PersonViewHolder(var view: View): RecyclerView.ViewHolder(view)
    {
        fun bind(v:Vehicle)
        {
            view.tvVID.setText(v.v_id.toString())
            view.tvVName.setText(v.v_name)
            view.tvVMillage.setText(v.v_millage.toString())
            view.tvVFuecap.setText(v.v_fuecap.toString())

        }
    }
}