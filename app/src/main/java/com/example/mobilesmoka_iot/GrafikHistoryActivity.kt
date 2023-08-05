package com.example.mobilesmoka_iot;

import android.graphics.Color
import android.graphics.Paint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.Description
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.components.YAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter
import com.google.firebase.database.*
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class GrafikHistoryActivity : AppCompatActivity() {
    private lateinit var img_back: ImageView
    private lateinit var lCHistory: LineChart
    private lateinit var lCJarak: LineChart
    private lateinit var lCPH: LineChart
    private lateinit var lCSuhu: LineChart
    private lateinit var lCTurbidity: LineChart
    private lateinit var xValues: List<String>
    private lateinit var databaseReference: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_grafik_history)

        img_back = findViewById(R.id.img_back)
        lCHistory = findViewById(R.id.chart)
        lCJarak = findViewById(R.id.chartJ)
        lCPH = findViewById(R.id.chartP)
        lCSuhu = findViewById(R.id.chartS)
        lCTurbidity = findViewById(R.id.chartT)

        chartAll()
        chartJarak()
        chartPH()
        chartSuhu()
        chartTurbidity()

        img_back.setOnClickListener {
            onBackPressed()
        }
    }

     fun chartAll() {
         xValues = ArrayList()

         val desc = Description()
         desc.text = ""
         desc.setPosition(150f, 15f)
         lCHistory.description = desc
         lCHistory.axisRight.setDrawLabels(false)

         val yAxis: YAxis = lCHistory.axisLeft
         yAxis.axisMinimum = 0f
         yAxis.axisLineWidth = 2f
         yAxis.axisLineColor = Color.BLACK

         val entriesJarak: MutableList<Entry> = ArrayList()
         val entriesPH: MutableList<Entry> = ArrayList()
         val entriesSuhu: MutableList<Entry> = ArrayList()
         val entriesTurbidity: MutableList<Entry> = ArrayList()

         // Initialize Firebase database reference
         databaseReference = FirebaseDatabase.getInstance().getReference("History")

         // Query the database to get the 5 latest data
         databaseReference.orderByChild("datetime").limitToLast(7).addListenerForSingleValueEvent(object :
             ValueEventListener {
             override fun onDataChange(snapshot: DataSnapshot) {
                 (xValues as ArrayList<String>).clear()

                 snapshot.children.forEach { dataSnapShot ->
                     val historyData = dataSnapShot.getValue(HistoryData::class.java)
                     historyData?.let {
                         (xValues as ArrayList<String>).add(it.datetime)
                         entriesJarak.add(Entry((xValues.size - 1).toFloat(), it.jarak.toFloat()))
                         entriesPH.add(Entry((xValues.size - 1).toFloat(), it.ph.toFloat()))
                         entriesSuhu.add(Entry((xValues.size - 1).toFloat(), it.suhu.toFloat()))
                         entriesTurbidity.add(Entry((xValues.size - 1).toFloat(), it.turbidity.toFloat()))
                     }
                 }

                 val xAxis: XAxis = lCHistory.xAxis
                 xAxis.position = XAxis.XAxisPosition.BOTTOM
                 xAxis.valueFormatter = IndexAxisValueFormatter(xValues)
                 xAxis.labelRotationAngle = 90.0f
                 xAxis.labelCount = xValues.size
                 xAxis.granularity = 1f

                 val datasetJarak = LineDataSet(entriesJarak, "Jarak")
                 datasetJarak.color = Color.BLUE

                 val datasetPH = LineDataSet(entriesPH, "pH")
                 datasetPH.color = Color.RED

                 val datasetSuhu = LineDataSet(entriesSuhu, "Suhu")
                 datasetSuhu.color = Color.GREEN

                 val datasetTurbidity = LineDataSet(entriesTurbidity, "Turbidity")
                 datasetTurbidity.color = Color.YELLOW

                 val lineData = LineData(datasetJarak, datasetPH, datasetSuhu, datasetTurbidity)
                 lCHistory.data = lineData
                 lCHistory.legend.isEnabled = true // Enable legend
                 lCHistory.legend.textSize = 12f // Set legend text size
                 lCHistory.setExtraOffsets(0f, 0f, 0f, 12f)
                 lCHistory.invalidate()
             }

             override fun onCancelled(error: DatabaseError) {
                 // Handle the error
             }
         })

     }

    fun chartJarak() {
        xValues = ArrayList()

        val desc = Description()
        desc.text = ""
        desc.setPosition(150f, 15f)
        lCJarak.description = desc
        lCJarak.axisRight.setDrawLabels(false)

        val yAxis: YAxis = lCJarak.axisLeft
        yAxis.axisMinimum = 0f
        yAxis.axisLineWidth = 2f
        yAxis.axisLineColor = Color.BLACK

        val entriesJarak: MutableList<Entry> = ArrayList()

        // Initialize Firebase database reference
        databaseReference = FirebaseDatabase.getInstance().getReference("History")

        // Query the database to get the 5 latest data
        databaseReference.orderByChild("datetime").limitToLast(7).addListenerForSingleValueEvent(object :
            ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                (xValues as ArrayList<String>).clear()

                snapshot.children.forEach { dataSnapShot ->
                    val historyData = dataSnapShot.getValue(HistoryData::class.java)
                    historyData?.let {
                        (xValues as ArrayList<String>).add(it.datetime)
                        entriesJarak.add(Entry((xValues.size - 1).toFloat(), it.jarak.toFloat()))
                    }
                }

                val xAxis: XAxis = lCJarak.xAxis
                xAxis.position = XAxis.XAxisPosition.BOTTOM
                xAxis.valueFormatter = IndexAxisValueFormatter(xValues)
                xAxis.labelRotationAngle = 90.0f
                xAxis.labelCount = xValues.size
                xAxis.granularity = 1f

                val datasetJarak = LineDataSet(entriesJarak, "Jarak")
                datasetJarak.color = Color.BLUE


                val lineData = LineData(datasetJarak, )
                lCJarak.data = lineData
                lCJarak.legend.isEnabled = true // Enable legend
                lCJarak.legend.textSize = 12f // Set legend text size
                lCJarak.setExtraOffsets(0f, 0f, 0f, 12f)
                lCJarak.invalidate()
            }

            override fun onCancelled(error: DatabaseError) {
                // Handle the error
            }
        })


    }

    fun chartPH() {
        xValues = ArrayList()

        val desc = Description()
        desc.text = ""
        desc.setPosition(150f, 15f)
        lCPH.description = desc
        lCPH.axisRight.setDrawLabels(false)

        val yAxis: YAxis = lCPH.axisLeft
        yAxis.axisMinimum = 0f
        yAxis.axisLineWidth = 2f
        yAxis.axisLineColor = Color.BLACK

        val entriesPH: MutableList<Entry> = ArrayList()

        // Initialize Firebase database reference
        databaseReference = FirebaseDatabase.getInstance().getReference("History")

        // Query the database to get the 5 latest data
        databaseReference.orderByChild("datetime").limitToLast(7).addListenerForSingleValueEvent(object :
            ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                (xValues as ArrayList<String>).clear()

                snapshot.children.forEach { dataSnapShot ->
                    val historyData = dataSnapShot.getValue(HistoryData::class.java)
                    historyData?.let {
                        (xValues as ArrayList<String>).add(it.datetime)
                        entriesPH.add(Entry((xValues.size - 1).toFloat(), it.ph.toFloat()))
                    }
                }

                val xAxis: XAxis = lCPH.xAxis
                xAxis.position = XAxis.XAxisPosition.BOTTOM
                xAxis.valueFormatter = IndexAxisValueFormatter(xValues)
                xAxis.labelRotationAngle = 90.0f
                xAxis.labelCount = xValues.size
                xAxis.granularity = 1f


                val datasetPH = LineDataSet(entriesPH, "pH")
                datasetPH.color = Color.RED


                val lineData = LineData(datasetPH)

                lCPH.data = lineData
                lCPH.legend.isEnabled = true // Enable legend
                lCPH.legend.textSize = 12f // Set legend text size
                lCPH.setExtraOffsets(0f, 0f, 0f, 12f)
                lCPH.invalidate()
            }

            override fun onCancelled(error: DatabaseError) {
                // Handle the error
            }
        })

    }

    fun chartSuhu() {
        xValues = ArrayList()

        val desc = Description()
        desc.text = ""
        desc.setPosition(150f, 15f)
        lCSuhu.description = desc
        lCSuhu.axisRight.setDrawLabels(false)

        val yAxis: YAxis = lCSuhu.axisLeft
        yAxis.axisMinimum = 0f
        yAxis.axisLineWidth = 2f
        yAxis.axisLineColor = Color.BLACK

        val entriesSuhu: MutableList<Entry> = ArrayList()

        // Initialize Firebase database reference
        databaseReference = FirebaseDatabase.getInstance().getReference("History")

        // Query the database to get the 5 latest data
        databaseReference.orderByChild("datetime").limitToLast(7).addListenerForSingleValueEvent(object :
            ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                (xValues as ArrayList<String>).clear()

                snapshot.children.forEach { dataSnapShot ->
                    val historyData = dataSnapShot.getValue(HistoryData::class.java)
                    historyData?.let {
                        (xValues as ArrayList<String>).add(it.datetime)
                        entriesSuhu.add(Entry((xValues.size - 1).toFloat(), it.suhu.toFloat()))
                    }
                }

                val xAxis: XAxis = lCSuhu.xAxis
                xAxis.position = XAxis.XAxisPosition.BOTTOM
                xAxis.valueFormatter = IndexAxisValueFormatter(xValues)
                xAxis.labelRotationAngle = 90.0f
                xAxis.labelCount = xValues.size
                xAxis.granularity = 1f


                val datasetSuhu = LineDataSet(entriesSuhu, "Suhu")
                datasetSuhu.color = Color.GREEN


                val lineData = LineData(datasetSuhu)
                lCSuhu.data = lineData
                lCSuhu.legend.isEnabled = true // Enable legend
                lCSuhu.legend.textSize = 12f // Set legend text size
                lCSuhu.setExtraOffsets(0f, 0f, 0f, 12f) // Add padding to top, left, right, and bottom of the chart
                lCSuhu.invalidate()
            }

            override fun onCancelled(error: DatabaseError) {
                // Handle the error
            }
        })

    }

    fun chartTurbidity() {
        xValues = ArrayList()

        val desc = Description()
        desc.text = ""
        desc.setPosition(150f, 15f)
        lCTurbidity.description = desc

        lCTurbidity.axisRight.setDrawLabels(false)

        val yAxis: YAxis = lCTurbidity.axisLeft
        yAxis.axisMinimum = 0f
        yAxis.axisLineWidth = 2f
        yAxis.axisLineColor = Color.BLACK

        val entriesTurbidity: MutableList<Entry> = ArrayList()

        // Initialize Firebase database reference
        databaseReference = FirebaseDatabase.getInstance().getReference("History")

        // Query the database to get the 5 latest data
        databaseReference.orderByChild("datetime").limitToLast(7).addListenerForSingleValueEvent(object :
            ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                (xValues as ArrayList<String>).clear()

                snapshot.children.forEach { dataSnapShot ->
                    val historyData = dataSnapShot.getValue(HistoryData::class.java)
                    historyData?.let {
                        (xValues as ArrayList<String>).add(it.datetime)
                        entriesTurbidity.add(Entry((xValues.size - 1).toFloat(), it.turbidity.toFloat()))
                    }
                }

                val xAxis: XAxis = lCTurbidity.xAxis
                xAxis.position = XAxis.XAxisPosition.BOTTOM
                xAxis.valueFormatter = IndexAxisValueFormatter(xValues)
                xAxis.labelRotationAngle = 90.0f
                xAxis.setLabelCount(xValues.size, true) // Set the label count and enable auto-adjustment
                xAxis.granularity = 1f
                xAxis.setDrawLabels(true) // Make sure the X-axis labels are drawn

                val datasetTurbidity = LineDataSet(entriesTurbidity, "Turbidity")
                datasetTurbidity.color = Color.YELLOW

                val lineData = LineData( datasetTurbidity)

                lCTurbidity.data = lineData
                lCTurbidity.legend.isEnabled = true // Enable legend
                lCTurbidity.legend.textSize = 12f // Set legend text size
                lCTurbidity.setExtraOffsets(0f, 0f, 0f, 12f) // Add padding to top, left, right, and bottom of the chart

                lCTurbidity.invalidate()
            }

            override fun onCancelled(error: DatabaseError) {
                // Handle the error
            }
        })
    }


}

data class HistoryData(
    val datetime: String = "",
    val jarak: Double = 0.0,
    val ph: Double = 0.0,
    val suhu: Double = 0.0,
    val turbidity: Double = 0.0,
)
