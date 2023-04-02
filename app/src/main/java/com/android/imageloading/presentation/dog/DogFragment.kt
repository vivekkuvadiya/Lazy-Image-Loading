package com.android.imageloading.presentation.dog

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.android.imageloading.R
import com.android.imageloading.databinding.FragmentDogBinding
import com.android.lazyimageloading.LazyImage

class DogFragment :Fragment(R.layout.fragment_dog){

    lateinit var binding:FragmentDogBinding
    val dogArg:DogFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentDogBinding.bind(view)

        val dog = dogArg.dog

        LazyImage.get(requireContext()).load(dog.url!!).into(binding.ivDog)
        binding.tvDogName.text = dog.name

    }

}