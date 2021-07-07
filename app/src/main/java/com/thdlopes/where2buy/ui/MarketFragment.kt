package com.thdlopes.where2buy.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.thdlopes.where2buy.data.MarketViewModel
import com.thdlopes.where2buy.databinding.FragmentMarketBinding

class MarketFragment : Fragment() {

    private var _binding: FragmentMarketBinding? = null
    private val binding get() = _binding!!

    private val adapter = MarketAdapter()

    private lateinit var viewModel: MarketViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentMarketBinding.inflate(inflater, container, false)
        viewModel = ViewModelProviders.of(this).get(MarketViewModel::class.java)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.recyclerViewMarkets.adapter = adapter

        binding.addButton.setOnClickListener {
            AddMarketDialogFragment().show(childFragmentManager, "Add Market Dialog")
        }

        viewModel.market.observe(viewLifecycleOwner, Observer {
            adapter.addMarket(it)
        })
        viewModel.getRealtimeUpdate()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}