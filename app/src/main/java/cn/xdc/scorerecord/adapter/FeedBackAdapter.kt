package cn.xdc.scorerecord.adapter

import android.content.Context
import android.content.Intent

import android.os.Handler
import android.os.Looper
import android.os.Message
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import android.widget.ImageButton
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import cn.xdc.scorerecord.R
import cn.xdc.scorerecord.activity.ResponseActivity

import cn.xdc.scorerecord.bean.Feedback
import cn.xdc.scorerecord.util.*
import cn.xdc.scorerecord.view.FeedBackDialog
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 *   author:domekisuzi
 *   time:2022/8/29
 */
class FeedBackAdapter (private val feedbacks:MutableList<Feedback>, private val context: Context,private val name:String): RecyclerView.Adapter<FeedBackAdapter.ViewHolder>(){

    companion object{
        const val DELETE = 1
    }
    private val handler =  object : Handler(Looper.getMainLooper()){
        override fun handleMessage(msg: Message) {
            super.handleMessage(msg)
            when(msg.what){
                DELETE->{
                    //TODO("加一个刷新功能吧")
                }
            }
        }
    }

    inner class  ViewHolder(view: View):RecyclerView.ViewHolder(view){
        val comment  = view.findViewById<ImageButton>(R.id.comment)
        val content = view.findViewById<TextView>(R.id.feedback_content)
        val delete = view.findViewById<ImageButton>(R.id.feedback_delete)
        val name =  view.findViewById<TextView>(R.id.feedback_name)
        val time =  view.findViewById<TextView>(R.id.feedback_time)
}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context) .inflate(R.layout.item_feedback,parent,false)

        return  ViewHolder(view)
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        if (name != feedbacks[position].name){
            // 设置角标按钮
            holder.delete.isVisible = false
        }
        else{

            holder.delete.isVisible = true
            log(feedbacks.toString() + "position:"+position)
            holder.delete.setOnClickListener {

                RetrofitBuilder.create(Service::class.java).deleteFeedback(feedbacks[position].id).enqueue(object :Callback<Int>{
                    override fun onResponse(call: Call<Int>, response: Response<Int>) {
                        response.body()?.let {
                            if(it != 0){
                                "删除成功!".showToast()
                            }
                            else{
                                "删除失败了喵".showToast()
                            }
                            val message =  Message()
                            //删除
                            message.what = DELETE
                            handler.sendMessage(message)
                            feedbacks.removeAt(holder.adapterPosition)
                            notifyItemRemoved(holder.adapterPosition)
                            notifyDataSetChanged()
                        }
                    }

                    override fun onFailure(call: Call<Int>, t: Throwable) {
                        "删除失败，服务器无响应".showToast()
                    }
                })

            }
        }

        /**
         * 在这里跳转到response页面
         */
        holder.comment.setOnClickListener {

            val intent = Intent(context,ResponseActivity::class.java)
            RequestUtil.operate("查看反馈详情")
            intent.putExtra("studentName",name)
            intent.putExtra("feedback",feedbacks[position])
            context.startActivity(intent)
        }
        holder.content.text =  feedbacks[position].content
        //TODO("后续加个昵称之类的东西")
//        holder.name.text = feedbacks[position].name

        feedbacks[position].date?.toString().let {
            holder.time.text = it
        }
    }

    override fun getItemCount() =  feedbacks.size

    //TODO("增加动画")
    fun addItem(feedback: Feedback){
        //并非完全之策
        feedback.id =  feedbacks.last().id+1
        feedbacks.add(feedback)
        notifyItemInserted(feedbacks.indexOf(feedback))
        log(feedbacks.toString())
    }
}

//val feedBackDialog = FeedBackDialog(context)
//feedBackDialog.textTitle = "回复"
//
//feedBackDialog.onClickBottomListener =  object : FeedBackDialog.OnClickBottomListener{
//    override fun onPositiveClick() {
//        val response = cn.xdc.scorerecord.bean.Response(feedBackDialog.editContent,name)
//        RetrofitBuilder.create(Service::class.java).responseFeedback(feedbacks[holder.adapterPosition].id,response).enqueue(object :Callback<Int>{
//            override fun onResponse(call: Call<Int>, response: Response<Int>) {
//                response.body()?.let {
//                    if(it != 0){
//                        "回复成功!".showToast()
//                    }
//                    else{
//                        "回复失败".showToast()
//                    }
//                    feedBackDialog.cancel()
//                }
//            }
//            override fun onFailure(call: Call<Int>, t: Throwable) {
//                "删除失败，服务器无响应".showToast()
//            }
//        })
//    }
//    override fun onNegativeClick() {
//        feedBackDialog.cancel()
//    }
//}
//feedBackDialog.show()