package com.zecdev.starwarspeople

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.zecdev.starwarspeople.controller.Log
import com.zecdev.starwarspeople.model.Vehicle
import com.zecdev.starwarspeople.model.Character
import kotlinx.android.synthetic.main.recyclerview_vehicle_row.view.*

class VehiclesRecyclerAdapter constructor(character: Character): RecyclerView.Adapter<VehiclesRecyclerAdapter.VehicleHolder>()  {

    var vehicles: ArrayList<Vehicle>

    init
    {
        this.vehicles = ArrayList<Vehicle>()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VehicleHolder {
        val inflator = LayoutInflater.from(parent.context)
        val row = inflator.inflate(R.layout.recyclerview_vehicle_row, parent, false)
        return VehicleHolder(row)
    }

    override fun getItemCount(): Int {
        return vehicles.size
    }

    override fun onBindViewHolder(holder: VehicleHolder, position: Int) {
        val item = vehicles[position]
        holder.bindCharacter(item)
    }

    class VehicleHolder(v: View) : RecyclerView.ViewHolder(v), View.OnClickListener {

        private var view: View = v
        private var vehicle: Vehicle? = null

        init {
            v.setOnClickListener(this)
        }

        fun bindCharacter(vehicle: Vehicle) {
            this.vehicle = vehicle

            view.vehicleName.text = vehicle.name
            view.vehicleModel.text = vehicle.model

        }

        override fun onClick(v: View) {
            Log.d("RecyclerView Clicked")
        }
    }
}
