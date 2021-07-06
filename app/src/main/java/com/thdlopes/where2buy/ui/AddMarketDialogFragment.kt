package com.thdlopes.where2buy.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.thdlopes.where2buy.R
import com.thdlopes.where2buy.data.Market
import com.thdlopes.where2buy.data.MarketViewModel
import com.thdlopes.where2buy.databinding.FragmentAddMarketDialogBinding

class AddMarketDialogFragment : DialogFragment() {

    private var _binding: FragmentAddMarketDialogBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: MarketViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NO_TITLE, android.R.style.Theme_DeviceDefault_Light_Dialog_MinWidth)
      }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        _binding = FragmentAddMarketDialogBinding.inflate(inflater, container, false)

        viewModel = ViewModelProviders.of(this).get(MarketViewModel::class.java)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel.result.observe(viewLifecycleOwner, Observer {
            val message = if (it == null) {
                getString(R.string.added_market)
            }else{
                getString(R.string.error, it.message)
            }

            Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
            dismiss()
        })

        binding.buttonAddMarket.setOnClickListener {
            val name = binding.editTextMarketName.text.toString().trim()

            if(name.isEmpty()){
                binding.editTextMarketName.error = "Esse campo é obrigatório"
                return@setOnClickListener
            }

            val market = Market()
            market.name = name

            viewModel.addMarket(market)
        }
    }

}