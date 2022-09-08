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
    }

@Test
    fun calculateCaloriesMildActivity() {
    }

@Test
    fun calculateCaloriesModerateActivity() {
    }

@Test
    fun calculateCaloriesHeavyActivity() {
    }

@Test
    fun calculateCaloriesExtremeActivity() {
    }
}