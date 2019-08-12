package com.pleon.traceln

import javafx.application.Application
import javafx.fxml.FXMLLoader
import javafx.scene.Parent
import javafx.scene.Scene
import javafx.stage.Stage

// For list of official Github languages and their colors see [https://github.com/ozh/github-colors]
//     and [https://github.com/github/linguist/blob/master/lib/linguist/languages.yml]
// For how Github language detection works see [https://github.com/github/linguist]
// And for using Github REST api see [https://developer.github.com/v3/repos/]

fun main(args: Array<String>) {
    Application.launch(Main::class.java, *args)
}

class Main : Application() {
    override fun start(primaryStage: Stage) {
        val root: Parent = FXMLLoader.load(javaClass.getResource("/fxml/scene-main.fxml"))
        primaryStage.title = "Traceln"
        primaryStage.scene = Scene(root, 300.0, 275.0)
        primaryStage.show()
    }
}
