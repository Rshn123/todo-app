package com.example.loginpage.fragment.important

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.loginpage.DataViewModel

import com.example.loginpage.R
import kotlinx.android.synthetic.main.fragment_important.*

class ImportantFragment : Fragment() {
    // TODO: Rename and change types of parameters
    var dataViewModel: DataViewModel? = null
    lateinit var adapter: ImportantAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_important, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        adapter = ImportantAdapter(context!!)
        todo_recycle_view_fragment.layoutManager = LinearLayoutManager(context)
        todo_recycle_view_fragment.adapter = adapter
        dataViewModel = DataViewModel(context!!)
        dataViewModel = ViewModelProvider(
                activity!!,
                DataViewModel.Factory(context!!)
        ).get(DataViewModel::class.java)

        dataViewModel!!.allImportant.observe(activity!!, Observer {
            datas ->
            datas?.let {
                adapter.setDatas(it)

            }
        })
    }

}
