package com.example.loginpage.fragment.myday

import com.example.loginpage.room.Data
import com.example.loginpage.R
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.loginpage.Main3Activity
import kotlinx.android.synthetic.main.todo_detail_layout.view.*
import java.util.Collections.emptyList

class AddToMyDayAdapter(val context: Context) : RecyclerView.Adapter<AddToMyDayAdapter.ViewHolder>() {
    var todoList = emptyList<Data>();
    lateinit var sharedPreferences: SharedPreferences

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R .layout.todo_detail_layout, parent, false))
    }

    override fun getItemCount(): Int {
        return todoList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.title.text = todoList[position].title
        sharedPreferences = context.getSharedPreferences("importantTodo", 0)
//        holder.bind(context, todoList[position])

        holder.itemView.delete_data_imageview_layout.visibility = View.GONE

        holder.itemView.textView_title.setOnClickListener {
            val intent = Intent(context, Main3Activity::class.java)
            intent.putExtra("id", todoList[position].id)
            intent.putExtra("number", todoList[position].number)
            context.startActivity(intent)
        }
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        // Holds the TextView that will add each animal to
        val title = view.textView_title
        val important = view.findViewById(R.id.important_imageView) as ImageView

//        fun bind(context: Context, data: Data) {
//            when {
//                data.isImportant -> {
//                    Log.d("011", "asdfsdf")
//                    important.background = ContextCompat.getDrawable(context, R.drawable.ic_star_black_24dp)
//                }
//                !data.isImportant -> {
//                    Log.d("011", "f")
//                    important.background = ContextCompat.getDrawable(context, R.drawable.ic_star_border_black_24dp)
//
//                }
//            }
//        }
    }

    internal fun setDatas(data: List<Data>) {
        this.todoList = data
        notifyDataSetChanged()
    }

    internal fun getId(position: Int): String {
        return this.todoList[position].id
    }
}
