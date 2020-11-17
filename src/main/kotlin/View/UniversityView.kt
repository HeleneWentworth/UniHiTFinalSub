package View

import javafx.beans.property.*
import javafx.collections.ObservableList
import javafx.beans.property.SimpleStringProperty
import javafx.geometry.Pos
import javafx.scene.paint.Color
import javafx.scene.text.TextAlignment
import person.Person
import subject.Subject
import university.UniHiT
import university.UniversityViewModel
import tornadofx.*
import university.UniHiTViewModel

class UniversityView: View() {

        val uniHiTViewModel = UniHiTViewModel(UniHiT())

//Bindings
var fund = SimpleStringProperty()
var subjects = SimpleStringProperty()
var diploma = SimpleStringProperty()
var degree = SimpleStringProperty()
var acad = SimpleStringProperty()
var admin = SimpleStringProperty()
var allPeople = SimpleStringProperty()
val people = SimpleListProperty<SimpleObjectProperty<Person>>()
val moneyFees = SimpleStringProperty()
val staffPay = SimpleStringProperty()
val endMonthPro = SimpleStringProperty()
val endPoolFunds = SimpleStringProperty()
val peopleList = SimpleStringProperty()
val subjectList = SimpleStringProperty()

var debug = SimpleStringProperty()
var searchBar = SimpleStringProperty()


fun update(){
    fund.set("Funds R" + uniHiTViewModel.getUniversityPool().toString())
    subjects.set("Subjects: " + uniHiTViewModel.getSubjectsCount().toString())
    diploma.set("Diploma Students: " + uniHiTViewModel.getDiStudentCount().toString())
    degree.set("Degree Students: " + uniHiTViewModel.getDeStudentCount().toString())
    acad.set("Academic Staff: " + uniHiTViewModel.getAcStaffCount().toString())
    admin.set("Administrative Staff: " + uniHiTViewModel.getAdStaffCount().toString())
    allPeople.set("All people: " + uniHiTViewModel.getAllPeople().toString())
    people.set(uniHiTViewModel.getPeople())

    println(uniHiTViewModel.getPersonByNo(1)?.name)
    val num = uniHiTViewModel.getAllPeople()

    peopleList.set("")
    for (i in people){
        if (searchBar.value == ""){
            println(i.value.name)
            var type = ""
            if (i.value.type == 0){
                type = " Diploma Student "
            }
            if (i.value.type == 1){
                type = " Degree Student "
            }
            if (i.value.type == 2){
                type = " Academic Staff "
            }
            if (i.value.type == 3){
                type = " Administrative Staff "
            }

            if (peopleList.value == ""){
                peopleList.set( i.value.name + " | ID: " + i.value.id + " | " + type + "| has " + uniHiTViewModel.getPersonSubjects(i.value.id) + " Subjects")
            } else{
                peopleList.set(peopleList.value.toString() + '\n' + i.value.name + " | ID: " + i.value.id + " | " + type + "| has " + uniHiTViewModel.getPersonSubjects(i.value.id) + " Subjects")
            }
        }else{
            println("Search")
            if (i.value.name.toLowerCase() == searchBar.value.toLowerCase() ){
                println(i.value.name)
                var type = ""
                if (i.value.type == 0){
                    type = " Diploma Student "
                }
                if (i.value.type == 1){
                    type = " Degree Student "
                }
                if (i.value.type == 2){
                    type = " Academic Staff "
                }
                if (i.value.type == 3){
                    type = " Administrative Staff "
                }

                if (peopleList.value == ""){
                    peopleList.set( i.value.name + " | " + type + "| has " + uniHiTViewModel.getPersonSubjects(i.value.id) + " Subjects")
                } else{
                    peopleList.set(peopleList.value.toString() + '\n' + i.value.name + " | " + type + "| has " + uniHiTViewModel.getPersonSubjects(i.value.id) + " Subjects")
                }
            }
        }


    }

    println(peopleList)
    subjectList.set("")
    var sub = uniHiTViewModel.getSubjectsCount()
    var i = 0
    while (i < sub){
        var str = ""
        str = "Name: " + uniHiTViewModel.getSubjectsName(i) + " |ID: " + i.toString()  + " |Code: " + uniHiTViewModel.getSubjectsCode(i) + " |Credits: " + uniHiTViewModel.getSubjectsCredits(i) + " |Hours " + uniHiTViewModel.getSubjectsHours(i) + " |R " + uniHiTViewModel.getSubjectsPrice(i)
        println(str)

        if (subjectList.value == ""){
            subjectList.set(str)
        } else{
            subjectList.set(subjectList.value + '\n'+str)
        }
        println(i)
        i++
    }
    println(subjectList.value)

    moneyFees.set("Students will pay R" + uniHiTViewModel.getMoneyFees().toString())
    staffPay.set("Staff will be Payed R" + uniHiTViewModel.getStaffPayment().toString())
    var projection =   uniHiTViewModel.getMoneyFees() - uniHiTViewModel.getStaffPayment()
    endMonthPro.set("End of Month Projection R$projection")
    var poolProjected = uniHiTViewModel.getUniversityPool() - uniHiTViewModel.getStaffPayment() - uniHiTViewModel.getMoneyFees()
    endPoolFunds.set("Projected Pool Funds R" + poolProjected)
}

override val root = vbox {
    searchBar.set("")
//        debug.set("Debug Dialog"+ '\n' + "TEST TEST TEST")
    val subject = uniHiTViewModel.subject.value
    borderpane {
        update()
        print((people[0].name))


        left = vbox() {
            setPrefSize(200.0, 960.0)
            useMaxHeight = true

            label ("UniHiT"){
                setPrefSize(200.0, 30.0)
                vboxConstraints {
                    marginTop = 15.0
                    marginBottom = 15.0
                    alignment = Pos.CENTER
                }
                style{
                    textFill = Color.rgb(3, 252, 198)
                    textAlignment = TextAlignment.CENTER
                }
            }

            label (uniHiTViewModel.getUniversityName()){
                setPrefSize(200.0, 10.0)
                vboxConstraints {
                    marginTop = 15.0
                    marginBottom = 15.0
                    alignment = Pos.CENTER
                }
                style{
                    textFill = Color.rgb(3, 252, 198)
                    textAlignment = TextAlignment.CENTER
                }
            }

            label (){
                textProperty().bind(fund)
                setPrefSize(200.0, 10.0)
                vboxConstraints {
                    marginTop = 15.0
                    marginBottom = 20.0
                    alignment = Pos.CENTER
                }
                style{
                    textFill = Color.rgb(3, 252, 198)
                    textAlignment = TextAlignment.CENTER
                }
            }

            label (){
                textProperty().bind(subjects)
                setPrefSize(200.0, 10.0)
                vboxConstraints {
                    marginTop = 10.0
                    marginBottom = 10.0
                    alignment = Pos.CENTER
                }
                style{
                    textFill = Color.rgb(3, 252, 198)
                    textAlignment = TextAlignment.CENTER
                }
            }
            label (){
                textProperty().bind(diploma)
                setPrefSize(200.0, 10.0)
                vboxConstraints {
                    marginTop = 10.0
                    marginBottom = 10.0
                    alignment = Pos.CENTER
                }
                style{
                    textFill = Color.rgb(3, 252, 198)
                    textAlignment = TextAlignment.CENTER
                }
            }

            label (){
                textProperty().bind(degree)
                setPrefSize(200.0, 10.0)
                vboxConstraints {
                    marginTop = 15.0
                    marginBottom = 15.0
                    alignment = Pos.CENTER
                }
                style{
                    textFill = Color.rgb(3, 252, 198)
                    textAlignment = TextAlignment.CENTER
                }
            }

            label (){
                textProperty().bind(acad)
                setPrefSize(200.0, 10.0)
                vboxConstraints {
                    marginTop = 15.0
                    marginBottom = 15.0
                    alignment = Pos.CENTER
                }
                style{
                    textFill = Color.rgb(3, 252, 198)
                    textAlignment = TextAlignment.CENTER
                }
            }

            label (){
                textProperty().bind(admin)
                setPrefSize(200.0, 10.0)
                vboxConstraints {
                    marginTop = 15.0
                    marginBottom = 15.0
                    alignment = Pos.CENTER
                }
                style{
                    textFill = Color.rgb(3, 252, 198)
                    textAlignment = TextAlignment.CENTER
                }
            }

            label (){
                textProperty().bind(allPeople)
                setPrefSize(200.0, 10.0)
                vboxConstraints {
                    marginTop = 15.0
                    marginBottom = 15.0
                    alignment = Pos.CENTER
                }
                style{
                    textFill = Color.rgb(3, 252, 198)
                    textAlignment = TextAlignment.CENTER
                }
            }

            style{
                backgroundColor += Color.rgb(116, 139, 153)
            }

        }

        top = vbox{
            setPrefSize(100.0, 30.0)
            useMaxWidth = true
            style{
                backgroundColor += Color.rgb(116, 139, 153)
            }
        }


        center = vbox{
            setPrefSize(1000.0, 960.0)
            useMaxWidth = true
            style{
                backgroundColor += Color.rgb(116, 139, 153)
            }

            borderpane {

                left = vbox {
                    label ("Users"){
                        style{
                            textFill = Color.rgb(255, 255, 255)
                        }
                    }
                    val model = ViewModel()
                    val search = model.bind { SimpleStringProperty() }
                    form{
                        setPrefSize(50.0, 10.0)
                        fieldset {
                            field("Search"){
                                style{
                                    textFill = Color.rgb(255, 255, 255)
                                }
                                textfield(search)
                            }

                        }

                        button("Search") {
                            action {
                                uniHiTViewModel.search(search.value.toString())
                                println("Search")
                                debug.set(search.value.toString())
                                searchBar.set(search.value.toString())
                                search.value = ""
                                update()
                            }

                        }

                    }

                    textarea () {
                        textProperty().bind(peopleList)
                        isWrapText = true

                    }

                    setPrefSize(450.0, 320.0)

                    vboxConstraints {
                        marginTop = 25.0
                        marginLeft = 35.0
                        marginRight = 35.0
                        marginBottom = 20.0
                    }
                    style{
                        backgroundColor += Color.rgb(92, 184, 127)
                    }
                }
                right = vbox {
                    label ("Subjects"){
                        style{
                            textFill = Color.rgb(255, 255, 255)
                        }
                    }

                    textarea () {
                        textProperty().bind(subjectList)
                        isWrapText = true
                    }

                    setPrefSize(450.0, 302.0)

                    vboxConstraints {
                        marginTop = 35.0
                        marginLeft = 35.0
                        marginRight = 35.0
                        marginBottom = 35.0
                    }
                    style{
                        backgroundColor += Color.rgb(92, 184, 127)
                    }
                }

                vboxConstraints {
                    marginTop = 20.0
                    marginLeft = 30.0
                    marginRight = 30.0
                    marginBottom = 20.0
                    alignment = Pos.CENTER
                }


            }

            borderpane {

                left = vbox {
                    label ("Register people"){
                        style{
                            textFill = Color.rgb(255, 255, 255)
                        }
                    }
                    val model = ViewModel()
                    val name = model.bind { SimpleStringProperty() }
                    val type = model.bind { SimpleStringProperty() }

                    form{
                        fieldset {
                            field("Name"){
                                textfield(name)
                            }

                            field("Type"){
                                textfield(type)
                            }
                        }

                        button("Add Person") {
                            action {
                                println(uniHiTViewModel.getAllPeople().toString() + " Before Add")
                                uniHiTViewModel.addPerson(name.value.toString(),type.value.toInt())
                                println("Added Person")

                                println(uniHiTViewModel.getAllPeople().toString() + " After Add")
                                name.value = ""
                                type.value = ""

                                update()

                            }
                        }
                    }

                    setPrefSize(450.0, 320.0)

                    vboxConstraints {
                        marginTop = 25.0
                        marginLeft = 35.0
                        marginRight = 35.0
                        marginBottom = 25.0
                    }
                    style{
                        backgroundColor += Color.rgb(92, 184, 127)
                    }
                }
                right = vbox {
                    label ("Create a new subject"){
                        style{
                            textFill = Color.rgb(255, 255, 255)
                        }
                    }

                    val model = ViewModel()
                    val name = model.bind { SimpleStringProperty() }
                    val code = model.bind { SimpleStringProperty() }
                    val credits = model.bind { SimpleStringProperty() }
                    val hours = model.bind { SimpleStringProperty() }
                    val price = model.bind { SimpleStringProperty() }


                    form{
                        fieldset {
                            field("Name"){
                                textfield(name)
                            }

                            field("Code"){
                                textfield(code)
                            }
                            field("Credits"){
                                textfield(credits)
                            }
                            field("Hours"){
                                textfield(hours)
                            }
                            field("Price"){
                                textfield(price)
                            }
                        }

                        button("Add Subject") {
                            action {
                                println(uniHiTViewModel.getSubjectsCount().toString() + " Before Add")
                                uniHiTViewModel.addSubject(name.value.toString(), code.value.toString(), credits.value.toInt(), hours.value.toInt(), price.value.toFloat())
                                println("Added Person")

                                println(uniHiTViewModel.getSubjectsCount().toString() + " After Add")
                                name.value = ""
                                code.value = ""
                                credits.value = ""
                                hours.value = ""
                                price.value = ""
                                update()

                            }
                        }
                    }

                    setPrefSize(450.0, 302.0)

                    vboxConstraints {
                        marginTop = 35.0
                        marginLeft = 35.0
                        marginRight = 35.0
                        marginBottom = 35.0
                    }
                    style{
                        backgroundColor += Color.rgb(92, 184, 127)
                    }
                }

                vboxConstraints {
                    marginTop = 20.0
                    marginLeft = 30.0
                    marginRight = 30.0
                    marginBottom = 20.0
                    alignment = Pos.CENTER
                }


            }

            borderpane {

                left = vbox {
                    label ("End of Month Balance"){
                        style{
                            textFill = Color.rgb(255, 255, 255)
                        }
                    }

                    label (){
                        textProperty().bind(moneyFees)
                        style{
                            textFill = Color.rgb(255, 255, 255)
                        }
                    }

                    label (){
                        textProperty().bind(staffPay)
                        style{
                            textFill = Color.rgb(255, 255, 255)
                        }
                    }
                    label (){
                        textProperty().bind(endMonthPro)
                        style{
                            textFill = Color.rgb(255, 255, 255)
                        }
                    }

                    label (){
                        textProperty().bind(endPoolFunds)
                        style{
                            textFill = Color.rgb(255, 255, 255)
                        }
                    }

                    setPrefSize(450.0, 320.0)

                    vboxConstraints {
                        marginTop = 25.0
                        marginLeft = 35.0
                        marginRight = 35.0
                        marginBottom = 25.0
                        alignment = Pos.CENTER
                    }
                    style{
                        backgroundColor += Color.rgb(92, 184, 127)
                    }
                }
                right = vbox {
                    label ("Add subject to people"){
                        style{
                            textFill = Color.rgb(255, 255, 255)
                        }
                    }

                    val model = ViewModel()
                    val name = model.bind { SimpleStringProperty() }
                    val id = model.bind { SimpleStringProperty() }


                    form{
                        fieldset {
                            field("Persons Id"){
                                textfield(name)
                            }

                            field("Subject Id"){
                                textfield(id)
                            }
                        }

                        button("Add Subject") {
                            action {
                                println(uniHiTViewModel.getRegisteredSubjectCount().toString() + " Before Add")
                                uniHiTViewModel.addSubjectToPerson(name.value.toInt(),id.value.toInt())
                                println("Added Person")

                                println(uniHiTViewModel.getRegisteredSubjectCount().toString() + " After Add")
                                name.value = ""
                                id.value = ""
                                update()

                            }
                        }
                    }

                    setPrefSize(450.0, 302.0)

                    vboxConstraints {
                        marginTop = 25.0
                        marginLeft = 35.0
                        marginRight = 35.0
                        marginBottom = 25.0
                    }
                    style{
                        backgroundColor += Color.rgb(92, 184, 127)
                    }
                }

                vboxConstraints {
                    marginTop = 35.0
                    marginLeft = 35.0
                    marginRight = 35.0
                    marginBottom = 35.0
                    alignment = Pos.CENTER
                }
            }
        }
      }
   }
}

