package com.rajnish.mobileapps.lcoworkoutapp.ui.dashboard.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.rajnish.mobileapps.lcoworkoutapp.R
import com.rajnish.mobileapps.lcoworkoutapp.db.entity.DayWiseEntity
import com.rajnish.mobileapps.lcoworkoutapp.loadGif

class WorkoutAdapter : ListAdapter<DayWiseEntity,
        WorkoutAdapter.RandomWorkoutViewHolder>(object : DiffUtil.ItemCallback<DayWiseEntity>(){
    override fun areItemsTheSame(oldItem: DayWiseEntity, newItem: DayWiseEntity): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: DayWiseEntity, newItem: DayWiseEntity): Boolean {
        return oldItem.dayId == newItem.dayId
    }

}) {

    class RandomWorkoutViewHolder(val view : View) : RecyclerView.ViewHolder(view) {
        fun bind(exerciseType: DayWiseEntity){
            val textView = view.findViewById<TextView>(R.id.exercise_tv)
            textView.setText(exerciseType.exercisesName)

            val exerciseImg = view.findViewById<ImageView>(R.id.exercise_img)
            exerciseImg.loadGif(view.context, exerciseType.gifResourceId)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RandomWorkoutViewHolder {
        val View = LayoutInflater.from(parent.context)
            .inflate(R.layout.gym_item_layout, parent, false)
        return RandomWorkoutViewHolder(view = View)
    }

    override fun onBindViewHolder(holder: RandomWorkoutViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

}