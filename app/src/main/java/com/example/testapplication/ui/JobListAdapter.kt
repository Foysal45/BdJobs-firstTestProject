package com.example.testapplication.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.testapplication.R
import com.example.testapplication.api.model.Data
import com.example.testapplication.databinding.ItemJobListBinding
import java.text.SimpleDateFormat
import java.util.*

class JobListAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {


    private val dataList: MutableList<Data> = mutableListOf()
    var onItemClick: ((jobListClick: Data, position: Int) -> Unit)? = null
    private val ourFormat = SimpleDateFormat("dd MMM yyyy", Locale.US)
    private val givenFormat = SimpleDateFormat("MM/dd/yyyy",Locale.US)

    private val options = RequestOptions()
        .placeholder(R.drawable.ic_person_circle)
        .error(R.drawable.ic_person_circle)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding: ItemJobListBinding =
            ItemJobListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewModel(binding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is ViewModel) {
            val model = dataList[position]
            val binding = holder.binding

            binding.jobTitle.text = model.jobTitle.ifEmpty {
                "Job Title"
            }
            //val formattedDate = DigitConverter.formatDate(model.deadline, "dd-MM-yyyy", "dd-MM-yyyy")
           // binding.deadline.text = formattedDate

            val parsedDate = givenFormat.parse(model.deadline)
            val formattedDeadline = ourFormat.format(parsedDate)
            binding.deadline.text = formattedDeadline

            binding.jobExperience.text = "${model.maxExperience} to ${model.minExperience} year(s)"
            //binding?.IsFeatured?.text = if (model.IsFeatured) { "Yes" } else { "No" }
            if (model.IsFeatured){
                binding.cardView.setBackgroundResource(R.drawable.bg_stroke1)
            }else{
                ""
            }
            Glide.with(binding.imageProfile)
                .load(model.logo)
                .apply(options)
                .into(binding.imageProfile)
        }
    }

    override fun getItemCount(): Int = dataList.size

    inner class ViewModel(val binding: ItemJobListBinding) : RecyclerView.ViewHolder(binding.root) {
        init {
            binding.root.setOnClickListener {
                if (absoluteAdapterPosition != RecyclerView.NO_POSITION) {
                    onItemClick?.invoke(dataList[absoluteAdapterPosition], absoluteAdapterPosition)
                }
            }
        }

    }

    fun initLoad(list: List<Data>) {
        dataList.clear()
        dataList.addAll(list)
        notifyDataSetChanged()
    }
}