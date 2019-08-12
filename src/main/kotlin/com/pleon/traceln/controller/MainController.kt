package com.pleon.traceln.controller

import com.pleon.traceln.component.Tracer
import com.pleon.traceln.model.Entry
import javafx.collections.FXCollections.observableArrayList
import javafx.fxml.FXML
import javafx.scene.chart.PieChart
import javafx.scene.control.TextField
import java.nio.file.Path

class MainController {

    @FXML private lateinit var pieChart: PieChart
    @FXML private lateinit var pathTextField: TextField

    fun trace() {
        val result = Tracer().trace(Path.of(pathTextField.text))
        pieChart.data = observableArrayList(result.asPieChartData())
    }

    private fun List<Entry>.asPieChartData() = map {
        PieChart.Data("${it.name}: ${it.lines}", it.lines.toDouble())
    }

}
