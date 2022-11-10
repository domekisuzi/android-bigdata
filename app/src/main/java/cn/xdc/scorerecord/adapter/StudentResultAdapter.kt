package cn.xdc.scorerecord.adapter

import android.content.Context
import android.content.Intent
import android.text.Layout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import cn.xdc.scorerecord.R
import cn.xdc.scorerecord.activity.DetailActivity
import cn.xdc.scorerecord.bean.But
import java.io.Serializable

/**
 *   author:domekisuzi
 *   time:2022/8/19
 */
class StudentResultAdapter(private val buts:List<But>,private val context: Context): RecyclerView.Adapter<StudentResultAdapter.ViewHolder>() {
     inner class  ViewHolder(view:View):RecyclerView.ViewHolder(view){
         val score: Button =  view.findViewById<Button>(R.id.item_score)
         val name  = view.findViewById<TextView>(R.id.but_name)

     }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context) .inflate(R.layout.item_student_result,parent,false)
        return  ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val but = buts[position]
        holder.name.text = but.order
        holder.score.text = but.score.toInt().toString()
        holder.score.setOnClickListener {
            /**
             * 开启详情界面
             *
             */

            val intent = Intent(context,DetailActivity::class.java)
            intent.putExtra("but",but)

            context.startActivity(intent)

        }
    }

    override fun getItemCount(): Int   = buts.size
}