package com.example.loginpage.fragment.important


import com.example.loginpage.room.Data
import com.example.loginpage.R
import android.content.Context
import android.content.SharedPreferences
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.todo_detail_layout.view.*

class ImportantAdapter(val context: Context) : RecyclerView.Adapter<ImportantAdapter.ViewHolder>() {
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
        holder.bind(context, todoList[position])

//        holder.itemView.delete_data_imageview_layout.setOnClickListener {
//            mainActivity = MainActivity()
//            mainActivity.updateImportant(todoList[position].id)
//
//        }
//
//        holder.itemView.textView_title.setOnClickListener {
//            val intent = Intent(context, Main3Activity::class.java)
//            intent.putExtra("id", todoList[position].id)
//            context.startActivity(intent)
//        }
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        // Holds the TextView that will add each animal to
        val title = view.textView_title
        val important = view.findViewById(R.id.delete_data_imageview) as ImageView

        fun bind(context: Context, data: Data) {
            when {
                data.isImportant -> {
                    important.background = ContextCompat.getDrawable(context, R.drawable.ic_star_black_24dp)
                }
                !data.isImportant -> {
                    important.background = ContextCompat.getDrawable(context, R.drawable.ic_star_border_black_24dp)

                }
            }
        }
    }

    internal fun setDatas(data: List<Data>) {
        this.todoList = data
        notifyDataSetChanged()
    }

    internal fun getId(position: Int): String {
        return this.todoList[position].id
    }
}
