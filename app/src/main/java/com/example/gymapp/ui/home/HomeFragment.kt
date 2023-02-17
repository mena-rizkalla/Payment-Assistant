package com.example.gymapp.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.gymapp.factory.HomeFactory
import com.example.gymapp.R
import com.example.gymapp.adapter.SubsAdapter
import com.example.gymapp.database.SubscribersDatabase
import com.example.gymapp.databinding.FragmentHomeBinding


class HomeFragment : Fragment() {

    private lateinit var adapter : SubsAdapter
    private var _binding : FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment

        _binding = FragmentHomeBinding.inflate(layoutInflater,container,false)
        val view = binding.root



        val application = requireNotNull(this.activity).application
        val dao = SubscribersDatabase.getInstance(application).subscribeDao
        val factory = HomeFactory(dao)
        val homeViewModel = ViewModelProvider(this,factory).get(HomeViewModel::class.java)
        binding.recyclerView.layoutManager = LinearLayoutManager(application)
        homeViewModel.sublists.observe(viewLifecycleOwner , Observer {
            it.let {
                adapter = SubsAdapter(application,it)
                binding.recyclerView.adapter = adapter
            }
        })


        binding.addFloat.setOnClickListener {

            this.findNavController().navigate(R.id.action_homeFragment2_to_detailFragment)

        }


        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}