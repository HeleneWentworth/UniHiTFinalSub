package subject

import javafx.beans.property.SimpleFloatProperty
import javafx.beans.property.SimpleIntegerProperty
import javafx.beans.property.SimpleStringProperty
//import javafx.collections.ObservableList
import javafx.beans.property.*
import javafx.collections.ObservableList

//Subjects for people
class Subject (val id: Int, var name: String, var code: String,  var credits: Int, var hours: Int, var price: Float){
    val idProperty = SimpleIntegerProperty(this, "id", id)
    var nameProperty = SimpleStringProperty(this, "name", name)
    var codeProperty = SimpleStringProperty(this, "code", code)
    var creditsProperty = SimpleIntegerProperty(this, "credits", credits)
    var hoursProperty = SimpleIntegerProperty(this, "hours", hours)
    var priceProperty = SimpleFloatProperty(this, "price", price)
}