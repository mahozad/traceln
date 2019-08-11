package com.pleon.traceln

import javafx.collections.FXCollections
import javafx.event.Event
import javafx.fxml.FXML
import javafx.scene.chart.PieChart
import javafx.scene.control.TextField
import java.nio.file.Path

class Controller {

    @FXML private lateinit var pieChart: PieChart
    @FXML private lateinit var pathTextField: TextField

    fun onButtonClick(actionEvent: Event) {
        val traverser = Traverser(Path.of(pathTextField.text))
        val inspector = Inspector()
        val adder = Adder()

        while (traverser.hasNext()) {
            val file = traverser.next()
            if (inspector.isTextFile(file)) {
                adder.add(inspector.getType(file), inspector.countLines(file))
            }
        }

        val pieChartData = FXCollections.observableArrayList(adder.getResult().map {
            PieChart.Data(it.name, it.lines.toDouble())
        })
        pieChart.data = pieChartData
        pieChart.title = "Code Composition"
    }
}
