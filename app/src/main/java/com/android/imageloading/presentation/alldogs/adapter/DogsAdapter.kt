package com.android.imageloading.presentation.alldogs.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources
import androidx.recyclerview.widget.RecyclerView
import com.android.imageloading.R
import com.android.imageloading.databinding.ItemDogsBinding
import com.android.imageloading.domain.model.Dog
import com.android.lazyimageloading.LazyImage

class DogsAdapter:RecyclerView.Adapter<DogsAdapter.DogsViewHolder>(){

    private val dogs = mutableListOf<Dog>()
    var onClick:((Dog)->Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DogsViewHolder {
        return DogsViewHolder(ItemDogsBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: DogsViewHolder, position: Int) {
        holder.onBind(dogs[position])
    }

    override fun getItemCount(): Int {
        return dogs.size
    }

    fun setDogs(dogs: List<Dog>?) {
        this.dogs.clear()
        dogs?.let {
            this.dogs.addAll(it)
        }
        notifyDataSetChanged()
    }

    inner class DogsViewHolder(val binding:ItemDogsBinding):RecyclerView.ViewHolder(binding.root) {
        fun onBind(dog: Dog) {
            LazyImage.get(binding.image).load(dog.url!!).setPlaceHolder(AppCompatResources.getDrawable(binding.root.context,R.drawable.ic_downloading)!!) .into(binding.image)
            binding.root.setOnClickListener {
                onClick?.let { click -> click(dog) }
            }
        }

    }
}