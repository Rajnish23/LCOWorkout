package com.rajnish.mobileapps.lcoworkoutapp

import android.content.Context
import com.rajnish.mobileapps.lcoworkoutapp.db.entity.DayWiseEntity
import java.util.*

class LcoDatabaseParcer(val context: Context) {

   /* val biceps = mutableListOf<String>("Curls",
        "Hammer Curls","Reverse Curls","Barbell Curls","Low Pulley Curls")
    val triceps = mutableListOf<String>("Push-Downs",
        "Bar Triceps","Triceps Dips","Triceps Pushdown", "Dumbbell Triceps")
    val chest = mutableListOf<String>("Bench Press",
        "Incline Presses","Push-Ups", "Incline Push-Ups", "Dumbbell Press")
    val shoulder = mutableListOf<String>()
    val back = mutableListOf<String>("Chin-Ups",
        "Deadlifts","Seated Rows","","")
    val leg = mutableListOf<String>()*/

    companion object{
/*
        val exerciseTypeList = listOf(

            //Biceps
            ExerciseType("Curls", R.drawable.curls1 ),
            ExerciseType("Curls", R.drawable.curls2 ),
            ExerciseType("Curls", R.drawable.curls3 ),
            ExerciseType("Hammer Curls", R.drawable.hammercurls1),
            ExerciseType("Hammer Curls", R.drawable.hammercurls2),
            ExerciseType("Hammer Curls", R.drawable.hammercurls3),
            ExerciseType("Reverse Curls", R.drawable.reversecurls1),
            ExerciseType("Reverse Curls", R.drawable.reversecurls2),
            ExerciseType("Reverse Curls", R.drawable.reversecurls3),
            ExerciseType("Barbell Curls", R.drawable.barbellcurls1),
            ExerciseType("Barbell Curls", R.drawable.barbellcurls2),
            ExerciseType("Barbell Curls", R.drawable.barbellcurls3),
            ExerciseType("LowPulley Curls", R.drawable.lowpulleycurls1),
            ExerciseType("LowPulley Curls", R.drawable.lowpulleycurls2),

            //Triceps
            ExerciseType("Push-Downs", R.drawable.pushdowns1),
            ExerciseType("Push-Downs", R.drawable.pushdowns2),
            ExerciseType("Bar Triceps", R.drawable.bartriceps1),
            ExerciseType("Bar Triceps", R.drawable.bartriceps2),
            ExerciseType("Bar Triceps", R.drawable.bartriceps3),
            ExerciseType("Triceps Dips", R.drawable.tricepsdips1),
            ExerciseType("Triceps Dips", R.drawable.tricepsdips2),
            ExerciseType("Triceps Pushdown", R.drawable.tricepspushdown1),
            ExerciseType("Triceps Pushdown", R.drawable.tricepspushdown2),
            ExerciseType("Dumbbell Triceps", R.drawable.dumbbelltricepsextension1),
            ExerciseType("Dumbbell Triceps", R.drawable.dumbbelltricepsextension2),

            //Chest
            ExerciseType("Bench Press", R.drawable.benchpresses1),
            ExerciseType("Bench Press", R.drawable.benchpresses2),
            ExerciseType("Incline Presses", R.drawable.inclinepresses1),
            ExerciseType("Incline Presses", R.drawable.inclinepresses2),
            ExerciseType("Incline Presses", R.drawable.inclinepresses3),
            ExerciseType("Push-Ups",R.drawable.push_ups1),
            ExerciseType("Push-Ups",R.drawable.push_ups2),
            ExerciseType("Decline Push-Ups", R.drawable.declinepushup1),
            ExerciseType("Decline Push-Ups", R.drawable.declinepushup2),
            ExerciseType("Dumbbell Press", R.drawable.dumbbellpresses1),
            ExerciseType("Dumbbell Press", R.drawable.dumbbellpresses2),

            //Back

            ExerciseType("Chin-Ups", R.drawable.chinups1),
            ExerciseType("Chin-Ups", R.drawable.chinups2),
            ExerciseType("Deadlifts", R.drawable.deadlifts1),
            ExerciseType("Deadlifts", R.drawable.deadlifts2),
            ExerciseType("Deadlifts", R.drawable.deadlifts3),
            ExerciseType("Deadlifts", R.drawable.deadlifts4),
            ExerciseType("Seated Rows", R.drawable.seatedrows1),
            ExerciseType("Seated Rows", R.drawable.seatedrows2),
            ExerciseType("Seated Rows", R.drawable.seatedrows3),
            ExerciseType("Seated Rows", R.drawable.seatedrows4),
            ExerciseType("Lat Pull-Downs", R.drawable.latpulldowns1),
            ExerciseType("Lat Pull-Downs", R.drawable.latpulldowns2),
            ExerciseType("Reverse Chin-Ups", R.drawable.reversechinups1),
            ExerciseType("Reverse Chin-Ups", R.drawable.reversechinups2),

            //Legs

            ExerciseType("Squat", R.drawable.squats1),
            ExerciseType("Squat", R.drawable.squats2),
            ExerciseType("Squat", R.drawable.squats3),
            ExerciseType("Angled Leg Presses", R.drawable.angledlegpresses1),
            ExerciseType("Angled Leg Presses", R.drawable.angledlegpresses2),
            ExerciseType("Angled Leg Presses", R.drawable.angledlegpresses3),
            ExerciseType("Dumbbell Squats", R.drawable.dumbbellsquats2),
            ExerciseType("Dumbbell Squats", R.drawable.dumbbellsquats3),
            ExerciseType("Hack Squats", R.drawable.hacksquats1),
            ExerciseType("Hack Squats", R.drawable.hacksquats2),
            ExerciseType("Single Leg Squat", R.drawable.singlelegsquat1),
            ExerciseType("Single Leg Squat", R.drawable.singlelegsquat2),

            //Shoulder
            ExerciseType("Seated Front Presses", R.drawable.seatedfrontpresses1),
            ExerciseType("Seated Front Presses", R.drawable.seatedfrontpresses2),
            ExerciseType("Upright Rows", R.drawable.uprightrow1),
            ExerciseType("Upright Rows", R.drawable.uprightrow2),
            ExerciseType("Upright Rows", R.drawable.uprightrow3),
            ExerciseType("Front Raises", R.drawable.frontraises1),
            ExerciseType("Front Raises", R.drawable.frontraises2),
            ExerciseType("Front Raises", R.drawable.frontraises3),
            ExerciseType("Arnold Press", R.drawable.arnoldpress1),
            ExerciseType("Arnold Press", R.drawable.arnoldpress2),
            ExerciseType("Arnold Press", R.drawable.arnoldpress3),
            ExerciseType("Lateral Raises", R.drawable.lateralraises1),
            ExerciseType("Lateral Raises", R.drawable.lateralraises2),
            ExerciseType("Lateral Raises", R.drawable.lateralraises3)
        )
*/

        val dayWiseExercise = listOf(
            //Monday
            DayWiseEntity(Calendar.MONDAY, "Curls", R.drawable.curl),
            DayWiseEntity(Calendar.MONDAY, "Hammer Curls", R.drawable.hammercurl),
            DayWiseEntity(Calendar.MONDAY, "Reverse Curls", R.drawable.reversecurls),
            DayWiseEntity(Calendar.MONDAY, "Barbell Curls", R.drawable.barbellcurls),
            DayWiseEntity(Calendar.MONDAY, "Low Pulley Curls", R.drawable.lowpulleycurls),
            //Tuesday
            DayWiseEntity(Calendar.TUESDAY, "Push-Downs", R.drawable.pushdowns),
            DayWiseEntity(Calendar.TUESDAY, "Bar Triceps", R.drawable.bartriceps),
            DayWiseEntity(Calendar.TUESDAY, "Triceps Dips", R.drawable.tricepdips),
            DayWiseEntity(Calendar.TUESDAY, "Triceps Pushdown", R.drawable.triceppushdowns),
            DayWiseEntity(Calendar.TUESDAY, "Dumbbell Triceps", R.drawable.dumbbelltricep),
            //Wednesday
            DayWiseEntity(Calendar.WEDNESDAY, "Bench Press", R.drawable.benchpress),
            DayWiseEntity(Calendar.WEDNESDAY, "Incline Presses", R.drawable.inclinedpress),
            DayWiseEntity(Calendar.WEDNESDAY, "Push-Ups", R.drawable.pushups),
            DayWiseEntity(Calendar.WEDNESDAY, "Declined Push-Ups", R.drawable.declinepushups),
            DayWiseEntity(Calendar.WEDNESDAY, "Dumbbell Press", R.drawable.dumbbellpress),
            //Thursday
            DayWiseEntity(Calendar.THURSDAY, "Chin-Ups", R.drawable.chinups),
            DayWiseEntity(Calendar.THURSDAY, "Deadlifts", R.drawable.deadlift),
            DayWiseEntity(Calendar.THURSDAY, "Seated Rows", R.drawable.seatedrows),
            DayWiseEntity(Calendar.THURSDAY, "Lat Pull-Downs", R.drawable.latpulldowns),
            DayWiseEntity(Calendar.THURSDAY, "Reverse Chin-Ups", R.drawable.reversechinups),
            //Friday
            DayWiseEntity(Calendar.FRIDAY, "Squat", R.drawable.squats),
            DayWiseEntity(Calendar.FRIDAY, "Angled Leg Presses", R.drawable.anglelegs),
            DayWiseEntity(Calendar.FRIDAY, "Dumbbell Squats", R.drawable.dumblesquat),
            DayWiseEntity(Calendar.FRIDAY, "Hack Squats", R.drawable.hacksquats),
            DayWiseEntity(Calendar.FRIDAY, "Single Leg Squat", R.drawable.singlesquat),
            //Saturday
            DayWiseEntity(Calendar.SATURDAY, "Seated Front Presses", R.drawable.seatedfrontpresses),
            DayWiseEntity(Calendar.SATURDAY, "Upright Rows", R.drawable.uprightrows),
            DayWiseEntity(Calendar.SATURDAY, "Front Raises", R.drawable.frontraises),
            DayWiseEntity(Calendar.SATURDAY, "Arnold Press", R.drawable.arnoldpress),
            DayWiseEntity(Calendar.SATURDAY, "Lateral Raises", R.drawable.lateralraises)
        )
    }




}