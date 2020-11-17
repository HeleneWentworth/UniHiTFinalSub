import View.MainView
import javafx.stage.Stage
import tornadofx.*

class MainProgram : App(MainView::class){

    override fun start(stage: Stage) {
        with(stage){
            minWidth = 1024.0
            height = 1000.0
            super.start(stage)
        }
    }

}