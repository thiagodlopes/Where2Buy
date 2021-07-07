package com.thdlopes.where2buy.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.database.ChildEventListener
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import java.lang.Exception

class MarketViewModel: ViewModel() {

    private val dbmarkets = FirebaseDatabase.getInstance().getReference(NODE_MARKETS)

    private val _result = MutableLiveData<Exception?>()
    val result: LiveData<Exception?> get() = _result

    private val _market = MutableLiveData<Market>()
    val market: LiveData<Market> get() = _market

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

    private val childEventListener = object: ChildEventListener{
        override fun onChildAdded(snapshot: DataSnapshot, previousChildName: String?) {
            val market = snapshot.getValue(Market::class.java)
            market?.id = snapshot.key
            _market.value = market!!
        }

        override fun onChildChanged(snapshot: DataSnapshot, previousChildName: String?) {

        }

        override fun onChildRemoved(snapshot: DataSnapshot) {

        }

        override fun onChildMoved(snapshot: DataSnapshot, previousChildName: String?) {

        }

        override fun onCancelled(error: DatabaseError) {

        }

    }

    fun getRealtimeUpdate(){
        dbmarkets.addChildEventListener(childEventListener)
    }

    override fun onCleared() {
        super.onCleared()
        dbmarkets.removeEventListener(childEventListener)
    }
}