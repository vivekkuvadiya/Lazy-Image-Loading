package com.android.imageloading.presentation.alldogs

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.android.imageloading.R
import com.android.imageloading.databinding.FragmentAllDogsBinding
import com.android.imageloading.presentation.alldogs.adapter.DogsAdapter
import com.android.imageloading.utils.Resource
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AllDogsFragment:Fragment() {

    private lateinit var binding:FragmentAllDogsBinding
    private val viewModel: AllDogsViewModel by viewModels()
    private lateinit var dogsAdapter: DogsAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater,
            R.layout.fragment_all_dogs,
            container,
            false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupDogsRecyclerview()

        setDogObserver()

        dogsAdapter.onClick = {
            findNavController().navigate(AllDogsFragmentDirections.actionAllDogsFragmentToDogFragment(
                it))
        }

    }

    private fun setupDogsRecyclerview() = binding.rvDogs.apply {
        dogsAdapter = DogsAdapter()
        adapter = dogsAdapter
        layoutManager = GridLayoutManager(requireContext(),2)
    }

    private fun setDogObserver(){
        viewModel.dogs.observe(viewLifecycleOwner){
            it?.let {
                when(it){
                    is Resource.Success -> {
                        binding.progress.visibility = View.GONE
                        dogsAdapter.setDogs(it.data)
                    }
                    is Resource.Error ->{
                        binding.progress.visibility = View.GONE
                        Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()
                    }
                    is Resource.Loading -> {
                        binding.progress.visibility = View.VISIBLE
                    }
                }
            }
        }
    }

}