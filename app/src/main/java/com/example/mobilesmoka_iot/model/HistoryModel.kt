package com.example.mobilesmoka_iot.model

data class HistoryModel (
    val datetime: String ="",
    val jarak: Long=0,
    val keteranganJarak: String="",
    val keteranganSuhu: String="",
    val keteranganTurbidity: String="",
    val keteranganpH: String="",
    val ph: Long=0,
    val suhu: Long=0,
    val turbidity: Long=0

){
    constructor() : this("",0,"","","","",0,0,0,)
}