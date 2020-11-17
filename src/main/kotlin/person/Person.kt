package person
import javafx.beans.property.SimpleIntegerProperty
import javafx.beans.property.SimpleStringProperty

// Person in university
class Person(val id: Int, var name: String, var type: Int) {

    val idProperty = SimpleIntegerProperty(this, "id", id)
    var nameProperty = SimpleStringProperty(this, "name", name)
    var typeProperty = SimpleIntegerProperty(this, "type", type)
}

