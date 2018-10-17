package com.company.p_10_strategy

// 전략 정의
interface Strategy{
    fun nextHand() : Hand
    fun study(win: Boolean)
}