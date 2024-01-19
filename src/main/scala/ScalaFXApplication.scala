import scalafx.Includes.*
import scalafx.application.JFXApp3
import scalafx.scene.Scene
import scalafx.scene.layout.VBox
import scalafx.scene.control.Label
import scalafx.geometry.Pos.Center

object ScalaFXApplication extends JFXApp3:
  override def start(): Unit =
    stage = new JFXApp3.PrimaryStage:
      title = "A ScalaFX application"
      width = 800
      height = 600
      scene = new Scene:
        root =
          new VBox:
            alignment = Center
            children = Label("Hello World!")
