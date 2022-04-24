package com.example.insert_update_with_one_time_login

data class Vehicle (var v_name:String,var v_millage:Int,var v_fuecap:Int)
{
    var v_id:Int=0
    constructor(v_id:Int,v_name: String,v_millage: Int,v_fuecap: Int):this(v_name,v_millage,v_fuecap)
    {
        this.v_id = v_id
    }
}