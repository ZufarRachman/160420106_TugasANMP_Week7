package com.example.anmp_week4.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.anmp_week4.R
import com.example.anmp_week4.viewmodel.CarsViewModel


class CarListFragment : Fragment() {
    private lateinit var viewModel:CarsViewModel
    private val adapter = CarListAdapter(arrayListOf())

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_car_list, container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(CarsViewModel::class.java)
        viewModel.refresh()

        val recViewCar = view.findViewById<RecyclerView>(R.id.recViewCar)
        recViewCar.layoutManager = LinearLayoutManager(context)
        recViewCar.adapter = adapter

        val refreshLayoutCar = view.findViewById<SwipeRefreshLayout>(R.id.refreshLayoutCar)

        observeViewModel()

        refreshLayoutCar.setOnRefreshListener {
            viewModel.refresh()
            refreshLayoutCar.isRefreshing = false
        }
    }
    fun observeViewModel() {
        viewModel.carLD.observe(viewLifecycleOwner, Observer {
            adapter.updateCarList(it)
        })
        viewModel.carLoadErrorLD.observe(viewLifecycleOwner, Observer {
            val txtError = view?.findViewById<TextView>(R.id.txtError)
            if(it == true) {
                txtError?.visibility = View.VISIBLE
            } else {
                txtError?.visibility = View.GONE
            }
        })
        viewModel.loadingLD.observe(viewLifecycleOwner, Observer {
            val progressLoad = view?.findViewById<ProgressBar>(R.id.progressLoadCar)
            val recView = view?.findViewById<RecyclerView>(R.id.recViewCar)
            if(it == true) {
                recView?.visibility = View.GONE
                progressLoad?.visibility = View.VISIBLE
            } else {
                recView?.visibility = View.VISIBLE
                progressLoad?.visibility = View.GONE
            }
        })
    }
}