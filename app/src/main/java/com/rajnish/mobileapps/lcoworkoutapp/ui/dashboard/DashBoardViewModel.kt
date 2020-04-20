package com.rajnish.mobileapps.lcoworkoutapp.ui.dashboard

import android.app.Application
import androidx.lifecycle.*
import com.rajnish.mobileapps.lcoworkoutapp.db.LCOGymDatabase
import com.rajnish.mobileapps.lcoworkoutapp.db.LCORepository
import com.rajnish.mobileapps.lcoworkoutapp.db.entity.DayWiseEntity

class DashBoardViewModel(application: Application) : AndroidViewModel(application) {

    private var workoutList: MutableList<DayWiseEntity> = mutableListOf()
    private val repository: LCORepository
    val workoutListObserver = MediatorLiveData<List<DayWiseEntity>>()
    private var currentSet : Int? = 0


    val currentExercise = MediatorLiveData<DayWiseEntity>()


    private val modeStateObserver = MutableLiveData<Mode>()

   /* fun updateTotalSet(noOfSet : Int){
        totalSet = noOfSet
    }*/

    val _modeState : LiveData<Mode>
        get() =  modeStateObserver

    init {
        val lcoDao = LCOGymDatabase.getInstance(application).getDao()
        repository = LCORepository(lcoDao)
    }

    private fun updateList() {
        workoutListObserver.value = workoutList
    }

    fun tellMeNextState(){
        if(workoutList.isEmpty()){
            modeStateObserver.value = Mode.MODE_DONE
        }
        else{
            modeStateObserver.value = Mode.MODE_INIT
        }
    }

    fun removeWorkListItem() {

        if(workoutList.isNotEmpty()) {
            currentExercise.value = workoutList.get(index = 0)
            workoutList.removeAt(0)
            updateList()
            tellMeNextState()
        }
    }

    fun getRandomWorkout() {
        workoutListObserver.removeSource(repository.getRandomWorkOut())
        workoutListObserver.addSource(repository.getRandomWorkOut()) {
            workoutList = it
            updateList()
        }
    }

    fun getDayWorkout() {
        workoutListObserver.removeSource(repository.getDayWorkout())
        workoutListObserver.addSource(repository.getDayWorkout()) {
            workoutList = it
            updateList()
        }
    }



    enum class Mode{
        MODE_INIT,
        MODE_BREAK,
        MODE_DONE
    }


}