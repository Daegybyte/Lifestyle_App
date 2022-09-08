package com.example.project

import android.util.Log

/**
This is a class for calculating the Basal Metabolic rate of a person
 */
class BMR {

    private var bmr: Double = 0.0
    private var weight: Double = 0.0
    private var height: Int = 0
    private var age: Int = 0

    private var sedentary = 1.2
    private var mildActivity = 1.375
    private var moderateActivity = 1.55
    private var heavyActivity = 1.725
    private var extremeActivity = 1.9

    /**
     * function to calculate the BMR of a man
     * returns Double
     * @param age: Int
     * @param height: Float
     * @param weight: Double
     * @return Double
     */
    fun calculateBMRMen(age: Int, height: Double, weight: Double): Double {
        bmr =  655.1 + (9.563 * weight) + (1.850 * height) - (4.67 * age)
        Log.d("BMR", "calculateBMRMen: $bmr")
        return bmr
    }

    /**
     * function to calculate the BMR of a woman
     * returns Double
     * @param age: Int
     * @param height: Float
     * @param weight: Double
     * @return Double
     */
    fun calculateBMRWomen(age: Int, height: Double, weight: Double): Double {
        bmr = 66.47 + (13.75 * weight) + (5.003 * height) - (6.755 * age)
        Log.d("BMR", "calculateBMRWomen: $bmr")
        return bmr
    }

    /**
     * calculates the calorie needs of a sedentar person
     * @param bmr: Double
     * @return Double
     */
    fun calculateCaloriesSedentary(bmr: Double): Double {
        val ret = bmr * sedentary
        Log.d("BMR", "calculateCaloriesSedentary: $ret")
        return ret
    }

    /**
     * calculates the calorie needs of a person with mild activity
     * @param bmr: Double
     * @return Double
     */
    fun calculateCaloriesMildActivity(bmr: Double): Double {
        val ret = (bmr * mildActivity) - 1.375
        Log.d("BMR", "calculateCaloriesMildActivity: $ret")
        return ret
    }

    /**
     * calculates the calorie needs of a person with moderate activity
     * @param bmr: Double
     * @return Double
     */
    fun calculateCaloriesModerateActivity(bmr: Double): Double {
        val ret = (bmr * moderateActivity) - 1.55
        Log.d("BMR", "calculateCaloriesModerateActivity: $ret")
        return ret
    }

    /**
     * calculates the calorie needs of a person with heavy activity
     * @param bmr: Double
     * @return Double
     */
    fun calculateCaloriesHeavyActivity(bmr: Double): Double {
        val ret = (bmr * heavyActivity)
        Log.d("BMR", "calculateCaloriesHeavyActivity: $ret")
        return ret
    }

    /**
     * calculates the calorie needs of a person with extreme activity
     * @param bmr: Double
     * @return Double
     */
    fun calculateCaloriesExtremeActivity(bmr: Double): Double {
        val ret = (bmr * extremeActivity)
        Log.d("BMR", "calculateCaloriesExtremeActivity: $ret")
        return ret
    }
}