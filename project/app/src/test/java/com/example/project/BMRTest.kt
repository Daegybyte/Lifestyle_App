package com.example.project

import org.junit.Test

import org.junit.Assert.*

internal class BMRTest {

    private val delta = 2.0
    private val dude1 = Triple(25, 183.0, 80.0)
    private val gal1 = Triple(25, 183.0, 80.0)
    
    @Test
    fun calculateBMRMen() {
        val bmr = BMR()
        val result = bmr.calculateBMRMen(dude1.first, dude1.second, dude1.third)
        assertEquals(1913.0, result, delta)
    }

    @Test
    fun calculateBMRWomen() {
        val bmr = BMR()
        val result = bmr.calculateBMRWomen(gal1.first, gal1.second, gal1.third)
        assertEquals(1652.0, result, delta)
    }

    @Test
    fun calculateCaloriesSedentary() {
        val bmr = BMR()
        //male
        var result = bmr.calculateCaloriesSedentary(1913.0)
        assertEquals(2296.0, result, delta)
        //female
        result = bmr.calculateCaloriesSedentary(1652.0)
        assertEquals(1982.0, result, delta)
    }

    @Test
    fun calculateCaloriesMildActivity() {
        val bmr = BMR()
        //male
        var result = bmr.calculateCaloriesMildActivity(1913.0)
        assertEquals(2630.0, result, delta)
        //female
        result = bmr.calculateCaloriesMildActivity(1652.0)
        assertEquals(2272.0, result, delta)
    }

    @Test
    fun calculateCaloriesModerateActivity() {
        val bmr = BMR()
        //male
        var result = bmr.calculateCaloriesModerateActivity(1913.0)
        assertEquals(2965.0, result, delta)
        //female
        result = bmr.calculateCaloriesModerateActivity(1652.0)
        assertEquals(2561.0, result, delta)
    }

    @Test
    fun calculateCaloriesHeavyActivity() {
        val bmr = BMR()
        //male
        var result = bmr.calculateCaloriesModerateActivity(1913.0)
        assertEquals(3252.0, result, delta)
        //female
        result = bmr.calculateCaloriesModerateActivity(1652.0)
        assertEquals(2808.0, result, delta)
    }

    @Test
    fun calculateCaloriesExtremeActivity() {
        val bmr = BMR()
        //male
        var result = bmr.calculateCaloriesModerateActivity(1913.0)
        assertEquals(3635.0, result, delta)
        //female
        result = bmr.calculateCaloriesModerateActivity(1652.0)
        assertEquals(3139.0, result, delta)

    }
}