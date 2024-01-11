package com.example.wastebin

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.graphics.Color
import android.graphics.Typeface
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

import com.github.mikephil.charting.animation.Easing
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.components.LegendEntry
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter
import com.github.mikephil.charting.formatter.LargeValueFormatter
import com.github.mikephil.charting.formatter.PercentFormatter
import com.github.mikephil.charting.utils.MPPointF
import info.mqtt.android.service.MqttAndroidClient
//import org.eclipse.paho.android.service.MqttAndroidClient
import org.eclipse.paho.client.mqttv3.IMqttActionListener
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken
import org.eclipse.paho.client.mqttv3.IMqttToken
import org.eclipse.paho.client.mqttv3.MqttCallback
import org.eclipse.paho.client.mqttv3.MqttException
import org.eclipse.paho.client.mqttv3.MqttMessage

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [f_home.newInstance] factory method to
 * create an instance of this fragment.
 */
class f_home : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    private lateinit var mqttAndroidClient: MqttAndroidClient
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_f_home, container, false)
        //piechart1
        val btn = view.findViewById<Button>(R.id.streaming_connectBroker)
        connect(requireContext())
        btn.setOnClickListener {
            connect(requireContext())
        }
        var pieChart = view.findViewById<PieChart>(R.id.pieChart)
        // on below line we are setting user percent value,
        // setting description as enabled and offset for pie chart
        pieChart.setUsePercentValues(true)
        pieChart.getDescription().setEnabled(false)
        pieChart.setExtraOffsets(5f, 10f, 5f, 5f)

        // on below line we are setting drag for our pie chart
        pieChart.setDragDecelerationFrictionCoef(0.95f)

        // on below line we are setting hole
        // and hole color for pie chart
        pieChart.setDrawHoleEnabled(true)
        pieChart.setHoleColor(Color.WHITE)

        // on below line we are setting circle color and alpha
        pieChart.setTransparentCircleColor(Color.WHITE)
        pieChart.setTransparentCircleAlpha(110)

        // on  below line we are setting hole radius
        pieChart.setHoleRadius(58f)
        pieChart.setTransparentCircleRadius(61f)

        // on below line we are setting center text
        pieChart.setDrawCenterText(true)

        // on below line we are setting
        // rotation for our pie chart
        pieChart.setRotationAngle(0f)

        // enable rotation of the pieChart by touch
        pieChart.setRotationEnabled(true)
        pieChart.setHighlightPerTapEnabled(true)

        // on below line we are setting animation for our pie chart
        pieChart.animateY(1400, Easing.EaseInOutQuad)

        // on below line we are disabling our legend for pie chart
        pieChart.legend.isEnabled = false
        pieChart.setEntryLabelColor(Color.WHITE)
        pieChart.setEntryLabelTextSize(12f)

        // on below line we are creating array list and
        // adding data to it to display in pie chart
        val entries: ArrayList<PieEntry> = ArrayList()
        entries.add(PieEntry(53f))
        entries.add(PieEntry(0f))

        // on below line we are setting pie data set
        val dataSet = PieDataSet(entries, "Mobile OS")

        // on below line we are setting icons.
        dataSet.setDrawIcons(false)

        // on below line we are setting slice for pie
        dataSet.sliceSpace = 3f
        dataSet.iconsOffset = MPPointF(0f, 40f)
        dataSet.selectionShift = 5f

        // add a lot of colors to list
        val colors: ArrayList<Int> = ArrayList()
        colors.add(resources.getColor(R.color.biru))
        colors.add(resources.getColor(R.color.grey))

        // on below line we are setting colors.
        dataSet.colors = colors

        // on below line we are setting pie data set
        val data = PieData(dataSet)
        data.setValueFormatter(PercentFormatter())
        data.setValueTextSize(15f)
        data.setValueTypeface(Typeface.DEFAULT_BOLD)
        data.setValueTextColor(Color.BLACK)
        pieChart.setData(data)

        // undo all highlights
        pieChart.highlightValues(null)

        // loading chart
        pieChart.invalidate()

        //piechart2
        var pieChart2 = view.findViewById<PieChart>(R.id.pieChart2)

        // on below line we are setting user percent value,
        // setting description as enabled and offset for pie chart
        pieChart2.setUsePercentValues(true)
        pieChart2.getDescription().setEnabled(false)
        pieChart2.setExtraOffsets(5f, 10f, 5f, 5f)

        // on below line we are setting drag for our pie chart
        pieChart2.setDragDecelerationFrictionCoef(0.95f)

        // on below line we are setting hole
        // and hole color for pie chart
        pieChart2.setDrawHoleEnabled(true)
        pieChart2.setHoleColor(Color.WHITE)

        // on below line we are setting circle color and alpha
        pieChart2.setTransparentCircleColor(Color.WHITE)
        pieChart2.setTransparentCircleAlpha(110)

        // on  below line we are setting hole radius
        pieChart2.setHoleRadius(58f)
        pieChart2.setTransparentCircleRadius(61f)

        // on below line we are setting center text
        pieChart2.setDrawCenterText(true)

        // on below line we are setting
        // rotation for our pie chart
        pieChart2.setRotationAngle(0f)

        // enable rotation of the pieChart by touch
        pieChart2.setRotationEnabled(true)
        pieChart2.setHighlightPerTapEnabled(true)

        // on below line we are setting animation for our pie chart
        pieChart2.animateY(1400, Easing.EaseInOutQuad)

        // on below line we are disabling our legend for pie chart
        pieChart2.legend.isEnabled = false
        pieChart2.setEntryLabelColor(Color.WHITE)
        pieChart2.setEntryLabelTextSize(12f)

        // on below line we are creating array list and
        // adding data to it to display in pie chart
        val entries2: ArrayList<PieEntry> = ArrayList()
        entries2.add(PieEntry(80f))
        entries2.add(PieEntry(20f))

        // on below line we are setting pie data set
        val dataSet2 = PieDataSet(entries, "Mobile OS")

        // on below line we are setting icons.
        dataSet2.setDrawIcons(false)

        // on below line we are setting slice for pie
        dataSet2.sliceSpace = 3f
        dataSet2.iconsOffset = MPPointF(0f, 40f)
        dataSet2.selectionShift = 5f

        // add a lot of colors to list
        val colors2: ArrayList<Int> = ArrayList()
        colors2.add(resources.getColor(R.color.oves))
        colors2.add(resources.getColor(R.color.grey))

        // on below line we are setting colors.
        dataSet2.colors = colors2

        // on below line we are setting pie data set
        val data2 = PieData(dataSet2)
        data2.setValueFormatter(PercentFormatter())
        data2.setValueTextSize(15f)
        data2.setValueTypeface(Typeface.DEFAULT_BOLD)
        data2.setValueTextColor(Color.BLACK)
        pieChart2.setData(data2)

        // undo all highlights
        pieChart2.highlightValues(null)

        // loading chart
        pieChart2.invalidate()

        //Bar Chart////
        var barChartView = view.findViewById<BarChart>(R.id.chartConsumptionGraph)
        val barWidth: Float
        val barSpace: Float
        val groupSpace: Float
        val groupCount = 12

        barWidth = 0.15f
        barSpace = 0.07f
        groupSpace = 0.56f

        var xAxisValues = ArrayList<String>()
        xAxisValues.add("Jan")
        xAxisValues.add("Feb")
        xAxisValues.add("Mar")
        xAxisValues.add("Apr")
        xAxisValues.add("May")
        xAxisValues.add("June")
        xAxisValues.add("Jul")
        xAxisValues.add("Aug")
        xAxisValues.add("Sep")
        xAxisValues.add("Oct")
        xAxisValues.add("Nov")
        xAxisValues.add("Dec")

        var yValueGroup1 = ArrayList<BarEntry>()
        var yValueGroup2 = ArrayList<BarEntry>()

        // draw the graph
        var barDataSet1: BarDataSet
        var barDataSet2: BarDataSet


        yValueGroup1.add(BarEntry(1f, floatArrayOf(9.toFloat(), 3.toFloat())))
        yValueGroup2.add(BarEntry(1f, floatArrayOf(2.toFloat(), 7.toFloat())))


        yValueGroup1.add(BarEntry(2f, floatArrayOf(3.toFloat(), 3.toFloat())))
        yValueGroup2.add(BarEntry(2f, floatArrayOf(4.toFloat(), 15.toFloat())))

        yValueGroup1.add(BarEntry(3f, floatArrayOf(3.toFloat(), 3.toFloat())))
        yValueGroup2.add(BarEntry(3f, floatArrayOf(4.toFloat(), 15.toFloat())))

        yValueGroup1.add(BarEntry(4f, floatArrayOf(3.toFloat(), 3.toFloat())))
        yValueGroup2.add(BarEntry(4f, floatArrayOf(4.toFloat(), 15.toFloat())))


        yValueGroup1.add(BarEntry(5f, floatArrayOf(9.toFloat(), 3.toFloat())))
        yValueGroup2.add(BarEntry(5f, floatArrayOf(10.toFloat(), 6.toFloat())))

        yValueGroup1.add(BarEntry(6f, floatArrayOf(11.toFloat(), 1.toFloat())))
        yValueGroup2.add(BarEntry(6f, floatArrayOf(12.toFloat(), 2.toFloat())))


        yValueGroup1.add(BarEntry(7f, floatArrayOf(11.toFloat(), 7.toFloat())))
        yValueGroup2.add(BarEntry(7f, floatArrayOf(12.toFloat(), 12.toFloat())))


        yValueGroup1.add(BarEntry(8f, floatArrayOf(11.toFloat(), 9.toFloat())))
        yValueGroup2.add(BarEntry(8f, floatArrayOf(12.toFloat(), 8.toFloat())))


        yValueGroup1.add(BarEntry(9f, floatArrayOf(11.toFloat(), 13.toFloat())))
        yValueGroup2.add(BarEntry(9f, floatArrayOf(12.toFloat(), 12.toFloat())))

        yValueGroup1.add(BarEntry(10f, floatArrayOf(11.toFloat(), 2.toFloat())))
        yValueGroup2.add(BarEntry(10f, floatArrayOf(12.toFloat(), 7.toFloat())))

        yValueGroup1.add(BarEntry(11f, floatArrayOf(11.toFloat(), 6.toFloat())))
        yValueGroup2.add(BarEntry(11f, floatArrayOf(12.toFloat(), 5.toFloat())))

        yValueGroup1.add(BarEntry(12f, floatArrayOf(11.toFloat(), 2.toFloat())))
        yValueGroup2.add(BarEntry(12f, floatArrayOf(12.toFloat(), 3.toFloat())))


        barDataSet1 = BarDataSet(yValueGroup1, "")
        barDataSet1.setColors(Color.BLUE, Color.RED)
        barDataSet1.label = "2016"
        barDataSet1.setDrawIcons(false)
        barDataSet1.setDrawValues(false)



        barDataSet2 = BarDataSet(yValueGroup2, "")

        barDataSet2.label = "2017"
        barDataSet2.setColors(Color.YELLOW, Color.RED)

        barDataSet2.setDrawIcons(false)
        barDataSet2.setDrawValues(false)

        var barData = BarData(barDataSet1, barDataSet2)

        barChartView.description.isEnabled = false
        barChartView.description.textSize = 0f
        barData.setValueFormatter(LargeValueFormatter())
        barChartView.setData(barData)
        barChartView.getBarData().setBarWidth(barWidth)
        barChartView.getXAxis().setAxisMinimum(0f)
        barChartView.getXAxis().setAxisMaximum(12f)
        barChartView.groupBars(0f, groupSpace, barSpace)
        //   barChartView.setFitBars(true)
        barChartView.getData().setHighlightEnabled(false)
        barChartView.invalidate()

        // set bar label
        var legend = barChartView.legend
        legend.setVerticalAlignment(Legend.LegendVerticalAlignment.BOTTOM)
        legend.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT)
        legend.setOrientation(Legend.LegendOrientation.HORIZONTAL)
        legend.setDrawInside(false)

        var legenedEntries = arrayListOf<LegendEntry>()

        legenedEntries.add(LegendEntry("Logam", Legend.LegendForm.SQUARE, 8f, 8f, null, Color.RED))
        legenedEntries.add(
            LegendEntry(
                "Non Logam",
                Legend.LegendForm.SQUARE,
                8f,
                8f,
                null,
                Color.YELLOW
            )
        )

        legend.setCustom(legenedEntries)

        legend.setYOffset(2f)
        legend.setXOffset(2f)
        legend.setYEntrySpace(0f)
        legend.setTextSize(5f)

        val xAxis = barChartView.getXAxis()
        xAxis.setGranularity(1f)
        xAxis.setGranularityEnabled(true)
        xAxis.setCenterAxisLabels(true)
        xAxis.setDrawGridLines(false)
        xAxis.textSize = 9f

        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM)
        xAxis.setValueFormatter(IndexAxisValueFormatter(xAxisValues))

        xAxis.setLabelCount(12)
        xAxis.mAxisMaximum = 12f
        xAxis.setCenterAxisLabels(true)
        xAxis.setAvoidFirstLastClipping(true)
        xAxis.spaceMin = 4f
        xAxis.spaceMax = 4f

        barChartView.setVisibleXRangeMaximum(12f)
        barChartView.setVisibleXRangeMinimum(12f)
        barChartView.setDragEnabled(true)

        //Y-axis
        barChartView.getAxisRight().setEnabled(false)
        barChartView.setScaleEnabled(true)

        val leftAxis = barChartView.getAxisLeft()
        leftAxis.setValueFormatter(LargeValueFormatter())
        leftAxis.setDrawGridLines(false)
        leftAxis.setSpaceTop(1f)
        leftAxis.setAxisMinimum(0f)


        barChartView.data = barData
        barChartView.setVisibleXRange(1f, 12f)

        return view
    }

    fun connect(applicationContext: Context) {
        mqttAndroidClient =
            MqttAndroidClient(applicationContext, "tcp://broker.hivemq.com", "45565")

        mqttAndroidClient.setCallback(object : MqttCallback {
            override fun connectionLost(cause: Throwable?) {
                Log.d("shandy", "connection loss:" + cause?.message.toString())

            }

            override fun messageArrived(topic: String?, message: MqttMessage?) {
                Log.d("shandy", "message:" + message.toString())
                val newValue = message?.toString()?.toFloatOrNull()

                // Check if the conversion is successful
                if (newValue != null) {
                    // Update your chart here
                    updatePieChart(newValue)

                }
            }

            override fun deliveryComplete(token: IMqttDeliveryToken?) {
                Log.d("shandy", "complete:" + token.toString())
            }

        })
        try {
            val token = mqttAndroidClient.connect()
            token.actionCallback = object : IMqttActionListener {
                override fun onSuccess(asyncActionToken: IMqttToken) {
                    Log.i("Connection", "success ")
                    subscribe("188ultra1")
                    //connectionStatus = true
                    // Give your callback on connection established here
                }

                override fun onFailure(asyncActionToken: IMqttToken, exception: Throwable) {
                    //connectionStatus = false
                    Log.i("Connection", "failure")
                    // Give your callback on connection failure here
                    exception.printStackTrace()
                }
            }
        } catch (e: MqttException) {
            // Give your callback on connection failure here
            e.printStackTrace()
        }
    }

    fun subscribe(topic: String, qos: Int = 1) {
        try {
            mqttAndroidClient.subscribe(topic, qos, null, object : IMqttActionListener {
                override fun onSuccess(asyncActionToken: IMqttToken?) {
                    Log.d("shandy", "Subscribed to $topic")
                }

                override fun onFailure(asyncActionToken: IMqttToken?, exception: Throwable?) {
                    Log.d("shandy", "Failed to subscribe $topic")
                }
            })
        } catch (e: MqttException) {
            e.printStackTrace()
        }
    }

    fun updatePieChart(value: Float) {
        // Mendapatkan pieChart dari tata letak
        // Mendapatkan pieChart dari tata letak
        val pieChart = view?.findViewById<PieChart>(R.id.pieChart)

// Check if pieChart is not null
        if (pieChart != null) {
            // Mendapatkan data dari pieChart
            val data = pieChart.data

            // Check if data is not null
            if (data != null) {
                // Mendapatkan dataset dari data
                val dataSet = data.getDataSetByIndex(0) as PieDataSet

                // Membersihkan semua entri yang ada
                dataSet.clear()

                // Mengubah persentase menjadi desimal dan menambahkan entri baru
                val entry = PieEntry(value.toFloat())  // misalnya, value = 50
                dataSet.addEntry(entry)

                // Memuat ulang chart
                data.notifyDataChanged()
                pieChart.notifyDataSetChanged()
                pieChart.invalidate()
            }
        }
    }




    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment f_home.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            f_home().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}