package com.thdlopes.where2buy.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.database.FirebaseDatabase
import java.lang.Exception

class MarketViewModel: ViewModel() {

    private val dbmarkets = FirebaseDatabase.getInstance().getReference(NODE_MARKETS)

    private val _result = MutableLiveData<Exception?>()
    val result: LiveData<Exception?> get() = _result

    fun addMarket(market: Market){
        market.id = dbmarkets.push().key

        dbmarkets.child(market.id!!).setValue(market).addOnCompleteListener{
            if(it.isSuccessful){
                _result.value = null
            } else {
                _result.value = it.exception
            }
        }
    }
}