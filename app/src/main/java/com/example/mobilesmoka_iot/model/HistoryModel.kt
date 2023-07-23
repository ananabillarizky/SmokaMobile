package com.example.mobilesmoka_iot.model

data class HistoryModel (
    val timestamp: String ="",
    val suhuAir: String="",
    val phair: String="",
    val kekeruhanAir: String="",
    val pakan: String=""
){
    constructor() : this("","","","","")
}
