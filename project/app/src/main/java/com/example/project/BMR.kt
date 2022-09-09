package com.example.project

/**
This is a class for calculating the Basal Metabolic rate of a person
 */
class BMR {

    private val sedentary = 1.2
    private val mildActivity = 1.375
    private val moderateActivity = 1.55
    private val heavyActivity = 1.7
    private val extremeActivity = 1.9

    /**
     * function to calculate the BMR of a man
     * returns Double
     * @param age: Int
     * @param height: Float
     * @param weight: Double
     * @return Double
     */
    fun calculateBMRMen(age: Int, height: Double, weight: Double): Double {
//        Log.d("BMR", "calculateBMRMen: $bmr")
        return (10 * weight) + (6.25 * height) - (5 * age) + 5

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
        return (10 * weight) + (6.25 * height) - (5 * age) - 161
    }

    /**
     * calculates the calorie needs of a sedentar person
     * @param bmr: Double
     * @return Double
     */
    fun calculateCaloriesSedentary(bmr: Double): Double {
        return bmr * sedentary
    }

    /**
     * calculates the calorie needs of a person with mild activity
     * @param bmr: Double
     * @return Double
     */
    fun calculateCaloriesMildActivity(bmr: Double): Double {
        return (bmr * mildActivity) - 1.375
    }

    /**
     * calculates the calorie needs of a person with moderate activity
     * @param bmr: Double
     * @return Double
     */
    fun calculateCaloriesModerateActivity(bmr: Double): Double {
        return (bmr * moderateActivity) - 1.55
    }

    /**
     * calculates the calorie needs of a person with heavy activity
     * @param bmr: Double
     * @return Double
     */
    fun calculateCaloriesHeavyActivity(bmr: Double): Double {
        return (bmr * heavyActivity)
    }

    /**
     * calculates the calorie needs of a person with extreme activity
     * @param bmr: Double
     * @return Double
     */
    fun calculateCaloriesExtremeActivity(bmr: Double): Double {
        return (bmr * extremeActivity)
    }
}