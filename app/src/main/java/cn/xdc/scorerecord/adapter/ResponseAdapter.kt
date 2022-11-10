package cn.xdc.scorerecord.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import cn.xdc.scorerecord.R
import cn.xdc.scorerecord.bean.Response

/**
 *   author:domekisuzi
 *   time:2022/9/2
 */
class ResponseAdapter (private val  responses:MutableList<Response>) : RecyclerView.Adapter<ResponseAdapter.ViewHolder>() {
    inner  class  ViewHolder(view: View):RecyclerView.ViewHolder(view){
        val name = view.findViewById<TextView>(R.id.response_name)
        val comment  = view.findViewById<ImageButton>(R.id.comment)
        val content = view.findViewById<TextView>(R.id.feedback_content)
        val delete = view.findViewById<ImageButton>(R.id.feedback_delete)
        val time =  view.findViewById<TextView>(R.id.feedback_time)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context) .inflate(R.layout.item_feedback,parent,false)

        return  ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {


        holder.time.text  = responses[position].date
        holder.name.text = responses[position].name
        holder.content.text= responses[position].content
        holder.delete.setOnClickListener {

        }
        holder.content.setOnClickListener {

        }

    }

    override fun getItemCount() = responses.size
}