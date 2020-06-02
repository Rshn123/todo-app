package com.example.loginpage.fragment.myday
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
import kotlinx.android.synthetic.main.fragment_add_to_day_fragmnt.*

class AddToDayFragmnt : Fragment() {

    var dataViewModel: DataViewModel? = null
    lateinit var adapter: AddToMyDayAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_to_day_fragmnt, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        adapter = AddToMyDayAdapter(context!!)
        todo_recycle_view_fragment.layoutManager = LinearLayoutManager(context)
        todo_recycle_view_fragment.adapter = adapter
        dataViewModel = DataViewModel(context!!)
        dataViewModel = ViewModelProvider(
                activity!!,
                DataViewModel.Factory(context!!)
        ).get(DataViewModel::class.java)

        dataViewModel!!.allMyDayData.observe(activity!!, Observer {
            datas ->
            datas?.let {
                adapter.setDatas(it)
            }
        })
    }

}
