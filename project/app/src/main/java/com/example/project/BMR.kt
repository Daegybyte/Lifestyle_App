package com.example.project
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
        return 655.1 + (9.563 * weight) + (1.850 * height) - (4.67 * age)
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
        return 66.47 + (13.75 * weight) + (5.003 * height) - (6.755 * age)
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