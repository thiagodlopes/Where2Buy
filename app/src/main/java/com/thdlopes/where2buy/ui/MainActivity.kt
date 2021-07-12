package com.thdlopes.where2buy.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import com.google.android.material.navigation.NavigationView
import com.google.firebase.auth.FirebaseAuth
import com.thdlopes.where2buy.R
import com.thdlopes.where2buy.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private lateinit var binding: ActivityMainBinding
    lateinit var toggle: ActionBarDrawerToggle

    private  lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)

        firebaseAuth = FirebaseAuth.getInstance()
        val navigationView: NavigationView = findViewById(R.id.navigationView)

        toggle = ActionBarDrawerToggle(this, binding.drawerLayout, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        binding.drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        navigationView.setNavigationItemSelectedListener(this)

        setToolbarTitle("Listas de Compras")
        changeFragment(ListFragment())
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(toggle.onOptionsItemSelected(item)){
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {

        binding.drawerLayout.closeDrawer(GravityCompat.START)

        if(item.itemId == R.id.profileFragment){
            setToolbarTitle("Meu Perfil")
            changeFragment(ProfileFragment())
        }
        if(item.itemId == R.id.listFragment){
            setToolbarTitle("Listas de Compras")
            changeFragment(ListFragment())
        }
        if(item.itemId == R.id.productFragment){
            setToolbarTitle("Produtos")
            changeFragment(ProductFragment())
        }
        if(item.itemId == R.id.marketFragment){
            setToolbarTitle("Mercados")
            changeFragment(MarketFragment())
        }
        if(item.itemId == R.id.logout){
            firebaseAuth.signOut()
            startActivity(Intent(this, SignInActivity::class.java))
            Toast.makeText(this, "Faça Login para consultar os dados", Toast.LENGTH_SHORT).show()
        }
        return true
    }

    private fun setToolbarTitle(title: String){
        supportActionBar?.title = title
    }

    private fun changeFragment(frag: Fragment){
        val fragment = supportFragmentManager.beginTransaction()
        fragment.replace(R.id.fragment_container,frag).commit()
    }
}