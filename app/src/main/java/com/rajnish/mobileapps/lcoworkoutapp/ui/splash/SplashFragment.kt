package com.rajnish.mobileapps.lcoworkoutapp.ui.splash


import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.rajnish.mobileapps.lcoworkoutapp.PreferenceManager

import com.rajnish.mobileapps.lcoworkoutapp.R
import kotlinx.android.synthetic.main.fragment_splash.*

/**
 * A simple [Fragment] subclass.
 */
class SplashFragment : Fragment() {


    private lateinit var preferenceManager: PreferenceManager
    private lateinit var mViewModel: SplashViewModel

    companion object{
        val RANDOM_MODE = "Random"
        val DAYWISE_MODE = "DayWise"
        val DONE_MODE = "done"
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_splash, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mViewModel = ViewModelProvider(this)[SplashViewModel::class.java]
        preferenceManager = PreferenceManager(activity!!)
        intializeDb()

        random_mode_tn.setOnClickListener {
            val directions = SplashFragmentDirections.actionSplashFragmentToDashboardFragment(SplashFragment.RANDOM_MODE)
            findNavController()
                .navigate(directions)
        }

        day_mode_btn.setOnClickListener {

            val directions = SplashFragmentDirections.actionSplashFragmentToDashboardFragment(SplashFragment.DAYWISE_MODE)
            findNavController()
                .navigate(directions)
        }
    }

    private fun intializeDb() {

        if(!preferenceManager.getIsInitialized()){
            preferenceManager.setInitialized()
            mViewModel.deleteAll()
            mViewModel.insertAllData()
        }


    }


}
