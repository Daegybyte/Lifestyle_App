package com.example.project

import org.junit.Test

import org.junit.Assert.*

internal class BMRTest {

    @Test
    fun calculateBMRMen() {
        val bmr = BMR()
        val result = bmr.calculateBMRMen(25, 183.0, 80.0)
        assertEquals(1913.0, result, 10.0)
    }

    @Test
    fun calculateBMRWomen() {
        val bmr = BMR()
        val result = bmr.calculateBMRWomen(25, 183.0, 80.0)
        assertEquals(1652.0, result, 10.0)
    }

    @Test
    fun calculateCaloriesSedentary() {
        val bmr = BMR()
        //male
        var result = bmr.calculateCaloriesSedentary(1913.0)
        assertEquals(2296.0, result, 10.0)
        //female
        result = bmr.calculateCaloriesSedentary(1652.0)
        assertEquals(1982.0, result, 10.0)
    }

    @Test
    fun calculateCaloriesMildActivity() {
        val bmr = BMR()
        //male
        var result = bmr.calculateCaloriesMildActivity(1913.0)
        assertEquals(2630.0, result, 10.0)
        //female
        result = bmr.calculateCaloriesMildActivity(1652.0)
        assertEquals(2272.0, result, 10.0)
    }

    @Test
    fun calculateCaloriesModerateActivity() {
        val bmr = BMR()
        //male
        var result = bmr.calculateCaloriesModerateActivity(1913.0)
        assertEquals(2965.0, result, 10.0)
        //female
        result = bmr.calculateCaloriesModerateActivity(1652.0)
        assertEquals(2561.0, result, 10.0)
    }

    @Test
    fun calculateCaloriesHeavyActivity() {
        val bmr = BMR()
        //male
        var result = bmr.calculateCaloriesModerateActivity(1913.0)
        assertEquals(3252.0, result, 10.0)
        //female
        result = bmr.calculateCaloriesModerateActivity(1652.0)
        assertEquals(2808.0, result, 10.0)
    }

    @Test
    fun calculateCaloriesExtremeActivity() {
        val bmr = BMR()
        //male
        var result = bmr.calculateCaloriesModerateActivity(1913.0)
        assertEquals(3635.0, result, 10.0)
        //female
        result = bmr.calculateCaloriesModerateActivity(1652.0)
        assertEquals(3139.0, result, 10.0)

    }
}