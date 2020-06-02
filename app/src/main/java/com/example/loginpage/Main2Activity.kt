package com.example.loginpage

import android.graphics.Canvas
import android.os.Bundle
import android.view.*
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.loginpage.room.Data
import kotlinx.android.synthetic.main.activity_main2.*


class Main2Activity : Fragment() {

    var dataViewModel: DataViewModel? = null
    lateinit var adapter: ToDoAdapter
    var todoCountTextView: TextView? = null
    var importantMarkImageView: ImageView? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.activity_main2, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        //for navigation drawer
        adapter = ToDoAdapter(context!!)
        todoCountTextView = view!!.findViewById(R.id.textview_header)
        importantMarkImageView = view!!.findViewById(R.id.delete_data_imageview)

        dataViewModel = DataViewModel(context!!)

        dataViewModel = ViewModelProvider(
                activity!!,
                DataViewModel.Factory(context!!)
        ).get(DataViewModel::class.java)

        todo_recycle_view.layoutManager = LinearLayoutManager(context)
        todo_recycle_view.adapter = adapter

        dataViewModel!!.allData.observe(this, Observer { datas ->
            datas?.let {
                adapter.setDatas(it)
                todoCountTextView!!.text = "You Have ${adapter.itemCount} Task"
            }
        })

        submit_button.setOnClickListener {
            dataViewModel!!.insert(Data(item_add_input.text.toString()))
            item_add_input.text = null
        }

        val itemTouchHelper = mIth
        itemTouchHelper.attachToRecyclerView(todo_recycle_view)
    }

    val mIth = ItemTouchHelper(
            object : ItemTouchHelper.SimpleCallback(
                    0,
                    ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
            ) {
                override fun onMove(
                        recyclerView: RecyclerView,
                        viewHolder: RecyclerView.ViewHolder,
                        target: RecyclerView.ViewHolder
                ): Boolean {
                    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                }

                override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                    val position = adapter.getId(viewHolder.adapterPosition)
                    when (direction) {
                        ItemTouchHelper.LEFT -> deleteData(position);
                        ItemTouchHelper.RIGHT -> activity!!.finish();
                    }
                }

                override fun onChildDraw(
                        c: Canvas,
                        recyclerView: RecyclerView,
                        viewHolder: RecyclerView.ViewHolder,
                        dX: Float,
                        dY: Float,
                        actionState: Int,
                        isCurrentlyActive: Boolean
                ) {
                    super.onChildDraw(
                            c,
                            recyclerView,
                            viewHolder,
                            dX,
                            dY,
                            actionState,
                            isCurrentlyActive
                    )
                }
            })

    fun deleteData(id: String) {
        dataViewModel?.delete(id)
    }


}
