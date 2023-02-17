package com.example.gymapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.gymapp.adapter.SubsAdapter
import com.example.gymapp.database.SubscribersDatabase
import com.example.gymapp.databinding.FragmentHomeBinding
import com.example.gymapp.model.Subscriber
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class HomeFragment : Fragment() {

    private lateinit var adapter : SubsAdapter
    private lateinit var subscriber : List<Subscriber>
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

        GlobalScope.launch {
             subscriber = dao.getAll()
            adapter = SubsAdapter(application, subscriber)
            binding.recyclerView.adapter = adapter
        }

        binding.recyclerView.layoutManager = LinearLayoutManager(application)



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