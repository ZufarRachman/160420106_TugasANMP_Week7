package com.example.anmp_week4.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.anmp_week4.R
import com.example.anmp_week4.model.CarModelItem
import com.squareup.picasso.Picasso

class CarListAdapter(val cars:ArrayList<CarModelItem>)
    : RecyclerView.Adapter<CarListAdapter.CarViewHolder>() {
    class CarViewHolder(view: View): RecyclerView.ViewHolder(view){
        val txtMake: TextView
        val txtModel: TextView
        val txtPrice: TextView
        val txtColor: TextView

        init {
            txtMake = view.findViewById(R.id.txtMake)
            txtModel = view.findViewById(R.id.txtModel)
            txtPrice = view.findViewById(R.id.txtPrice)
            txtColor = view.findViewById(R.id.txtColor)

        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.car_list_item, parent, false)
        return CarViewHolder(view)
    }

    override fun getItemCount(): Int {
        return cars.size
    }

    override fun onBindViewHolder(holder: CarViewHolder, position: Int) {
        holder.txtMake.text = "Brand: " + cars[position].make
        holder.txtModel.text = "Model: " + cars[position].model
        holder.txtColor.text = "Color: " + cars[position].color
        holder.txtPrice.text = "Price: " + cars[position].price.toString()

    }
    fun updateCarList(newCarList: ArrayList<CarModelItem>) {
        cars.clear()
        cars.addAll(newCarList)
        notifyDataSetChanged()
    }
}