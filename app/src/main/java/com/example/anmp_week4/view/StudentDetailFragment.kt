package com.example.anmp_week4.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.anmp_week4.R
import com.example.anmp_week4.viewmodel.DetailViewModel
import com.squareup.picasso.Picasso
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.schedulers.Schedulers
import java.util.concurrent.TimeUnit



class StudentDetailFragment : Fragment() {
    private lateinit var detailView: DetailViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_student_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val studentId = arguments?.getString("studentId")

        detailView = ViewModelProvider(this).get(DetailViewModel::class.java)

        detailView.fetch(studentId.toString())
        observeViewModel()
    }

    fun observeViewModel() {
        val id = view?.findViewById<TextView>(R.id.txtID)
        val name = view?.findViewById<TextView>(R.id.txtName)
        val bod = view?.findViewById<TextView>(R.id.txtBoD)
        val phone = view?.findViewById<TextView>(R.id.txtPhone)
        val imgPhoto = view?.findViewById<ImageView>(R.id.imgStudent)
        detailView.studentLD.observe(viewLifecycleOwner, Observer { student ->
            id?.setText(student.id)
            name?.setText(student.name)
            bod?.setText(student.bod)
            phone?.setText(student.phone)
            Picasso.get().load(student.photoUrl).into(imgPhoto)
        })
        val btn = view?.findViewById<Button>(R.id.btnUpdate)
        btn?.setOnClickListener {
           Observable.timer(5, TimeUnit.SECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    Log.d("Messages", "five seconds")
                    MainActivity.showNotification("New Notofication",
                        "Student is updated",
                        R.drawable.baseline_save_24)
                }
        }
    }
}