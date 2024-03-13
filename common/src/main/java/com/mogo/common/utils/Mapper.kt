package com.mogo.common.utils

interface Mapper<F, T> {
    fun map(data: F): T
}