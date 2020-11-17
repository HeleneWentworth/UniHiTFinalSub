package university

import javafx.beans.property.SimpleFloatProperty
import javafx.beans.property.SimpleListProperty
import javafx.beans.property.SimpleObjectProperty
import javafx.beans.property.SimpleStringProperty
import person.Person
import subject.Subject
import tornadofx.ViewModel

class University(name:String, pool:Float) {
    var nameProperty = SimpleStringProperty(this, "name", name)
    var poolProperty = SimpleFloatProperty(this, "pool", pool)
}
class UniversityViewModel(){

}