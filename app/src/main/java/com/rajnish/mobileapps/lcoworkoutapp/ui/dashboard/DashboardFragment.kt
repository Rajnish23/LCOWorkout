package com.rajnish.mobileapps.lcoworkoutapp.ui.dashboard


import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.media.MediaPlayer
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.transition.TransitionManager
import com.rajnish.mobileapps.lcoworkoutapp.*

import com.rajnish.mobileapps.lcoworkoutapp.ui.dashboard.adapter.WorkoutAdapter
import com.rajnish.mobileapps.lcoworkoutapp.ui.splash.SplashFragment
import kotlinx.android.synthetic.main.dialog_complete_layout.*
import kotlinx.android.synthetic.main.fragment_dashboard.*
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withTimeoutOrNull
import java.util.*

/**
 * A simple [Fragment] subclass.
 */
class DashboardFragment : Fragment() {

    private lateinit var preferenceManager: PreferenceManager
    private lateinit var handler: Handler
    private lateinit var MODE: String
    private lateinit var mViewModel: DashBoardViewModel
    private lateinit var workoutAdapter: WorkoutAdapter
    private lateinit var mediaPlayer: MediaPlayer
    private var noOfSets: Int = 3
    private var currentSet: Int = 0
    private var isMediaPlayerReleased = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_dashboard, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        MODE = DashboardFragmentArgs.fromBundle(arguments!!).workoutMode
        preferenceManager = PreferenceManager(activity!!)
        initViewModel()
        initViews()
        setupUI()
        handler = Handler(Looper.getMainLooper())

        if(preferenceManager.getDoneExerciseForDay()){
            showCompletedDialog()
        }
    }


    private fun initViews() {

        workoutAdapter = WorkoutAdapter()
        workout_recyclerview.adapter = workoutAdapter

        let_start_btn.setOnClickListener(starClickListener)

        refresh_btn.setOnClickListener {
            mViewModel.getRandomWorkout()
        }

        play_pause_btn.setOnClickListener {
            if(mediaPlayer.isPlaying){
                play_pause_btn.setImageResource(R.drawable.ic_play)
                mediaPlayer.pause()
            }
            else{
                play_pause_btn.setImageResource(R.drawable.ic_pause)
                mediaPlayer.start()
                playCycle()
            }
        }


        set_1_chip.setOnClickListener {
            noOfSets = 1
            hideChipGroup()
        }

        set_2_chip.setOnClickListener {
            noOfSets = 2
            hideChipGroup()
        }

        set_3_chip.setOnClickListener {
            noOfSets = 3
            hideChipGroup()
        }

        set_4_chip.setOnClickListener {
            noOfSets = 4
            hideChipGroup()
        }

    }

    private fun hideChipGroup() {
        refresh_btn.visibility = View.GONE
        let_start_btn.visibility = View.VISIBLE
        set_chip.visibility = View.GONE
    }

    private fun releasePlayer() {
        isMediaPlayerReleased = true
        mediaPlayer.release()
    }

    private val starClickListener = View.OnClickListener {

        if(currentSet == 0 || currentSet >= noOfSets - 1){
            currentSet = 0
            startNewExercise()
        }
        else{
            startNewSet()
        }

        let_start_btn.text = "Set ${currentSet}"

    }

    private fun startNewSet() {
        showUI()
        initializePlayer()
    }

    private fun startNewExercise() {
        showUI()
        mViewModel.removeWorkListItem()
    }


     private fun playCycle() {
        val mRunnable =  Runnable {
             mediaPlayer.let {
                 if(!isMediaPlayerReleased && it.isPlaying){
                     music_seekbar.progress = it.currentPosition
                     playCycle()
                 }
             }
         }
         if(!isMediaPlayerReleased) {
             handler.postDelayed(mRunnable, 1000)
         }
     }


    private fun initViewModel() {
        mViewModel = ViewModelProvider(this)[DashBoardViewModel::class.java]
        mViewModel.workoutListObserver.observe(viewLifecycleOwner, Observer {
            if (it == null || it.isEmpty())
                mViewModel.getRandomWorkout()
            else
                workoutAdapter.submitList(it)
            workoutAdapter.notifyDataSetChanged()
        })

        mViewModel.currentExercise.observe(viewLifecycleOwner, Observer {
            current_exercise_title.visibility = View.VISIBLE
            exercise_card_layout.visibility = View.VISIBLE
            current_exercise_title.text = it.exercisesName
            current_exercise_img.loadGif(current_exercise_img.context, it.gifResourceId)

        })

        mViewModel._modeState.observe(viewLifecycleOwner, Observer {
            when (it) {
                DashBoardViewModel.Mode.MODE_DONE -> {
                    hideUI()
                    showCompletedDialog()
                }
                DashBoardViewModel.Mode.MODE_INIT -> {
                    initializePlayer()
                }
                DashBoardViewModel.Mode.MODE_BREAK -> {
                    let_start_btn.text = getString(R.string.break_text)
                }
            }
        })
    }

    private fun showCompletedDialog() {
        preferenceManager.setDoneExerciseForDay(true)
        val builder = AlertDialog.Builder(activity!!)
        val view = layoutInflater.inflate(R.layout.dialog_complete_layout, null )
        builder.setView(view)

        val mAlertDialog = builder.create()
        mAlertDialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        val okBtn = view.findViewById<Button>(R.id.ok_btn)
        okBtn.setOnClickListener {
            mAlertDialog.dismiss()
            activity?.finish()
        }

        mAlertDialog.show()
    }

    private fun initializePlayer() {
        let_start_btn.isEnabled = false
        currentSet += 1
        when (currentSet) {
            0 -> {
                mediaPlayer = MediaPlayer.create(activity!!, R.raw.song_1)

            }
            1 -> {
                mediaPlayer = MediaPlayer.create(activity!!, R.raw.song_2)

            }
            2 -> {
                mediaPlayer = MediaPlayer.create(activity!!, R.raw.song_3)

            }
            3 -> {
                mediaPlayer = MediaPlayer.create(activity!!, R.raw.song_4)

            }
        }
        mediaPlayer.setOnCompletionListener(object : MediaPlayer.OnCompletionListener {
            override fun onCompletion(mp: MediaPlayer?) {
                releasePlayer()
                let_start_btn.text = getString(R.string.break_text)
                breakTime()
            }

        })
        mediaPlayer.let {
            music_seekbar.max = it.duration
            it.start()
            isMediaPlayerReleased = false
            playCycle()
        }
    }

    private fun breakTime() {
        hideUI()

        handler.postDelayed({
            let_start_btn.setText("Next")
            let_start_btn.isEnabled = true
        }, 40000)


    }

    private fun showUI() {
        TransitionManager.beginDelayedTransition(root_layout)
        play_pause_btn.visibility = View.VISIBLE
        music_seekbar.visibility = View.VISIBLE
        exercise_card_layout.visibility = View.VISIBLE
        current_exercise_title.visibility = View.VISIBLE
    }

    private fun hideUI() {
        TransitionManager.beginDelayedTransition(root_layout)
        play_pause_btn.visibility = View.GONE
        music_seekbar.visibility = View.GONE
        exercise_card_layout.visibility = View.GONE
        current_exercise_title.visibility = View.GONE
    }

    private fun setupUI() {

        if (MODE == SplashFragment.RANDOM_MODE) {
            mViewModel.getRandomWorkout()
        } else if (MODE == SplashFragment.DAYWISE_MODE) {
            mViewModel.getDayWorkout()
            refresh_btn.visibility = View.GONE
        }
    }


}
