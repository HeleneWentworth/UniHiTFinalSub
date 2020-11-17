package View
import tornadofx.*


class MainView : View("UniHiT") {

    override val root = borderpane {
        left(UniversityView::class)
    }
}

