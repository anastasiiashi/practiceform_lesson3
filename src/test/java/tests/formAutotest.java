package tests;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.File;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class FormAutotest {

    @BeforeAll
    static void setup() {

        Configuration.startMaximized = true;
    }

    @Test
    void successfulSubmitFormTest() {
        //define the test data
        String firstName = "Anastasiia";
        String lastName = "Morozova";
        String email = "test@test.ru";
        String mobile = "9100000000";
        String subject = "Comp";
        String currentAddress = "25 Olson street";
        String state = "Haryana";
        String city = "Karnal";

        //open the website
        open("https://demoqa.com/automation-practice-form");

        //fill in the data
        $("#firstName").setValue(firstName);
        $("#lastName").setValue(lastName);
        $("#userEmail").setValue(email);
        $("[for=gender-radio-2]").click();
        $("#userNumber").setValue(mobile);

        //set the date of birth
        $("#dateOfBirthInput").click();
        $(".react-datepicker__month-select").selectOption("August");
        $(".react-datepicker__year-select").selectOption("1991");
        $(".react-datepicker__day.react-datepicker__day--013").click();

        //continue to fill in the form with test data
        $("#subjectsInput").setValue(subject).pressEnter();
        $("[for=\"hobbies-checkbox-2\"]").click();

        //upload the picture
        $("#uploadPicture").uploadFile(new File("src/test/resources/not_pass.jpg"));

        //end filling the form and press Submit button
        $("#currentAddress").setValue(currentAddress);
        $("#react-select-3-input").setValue(state).pressEnter();
        $("#react-select-4-input").setValue(city).pressEnter();
        $("#submit").scrollTo().click();

        //check if the filled in data is correct
        $(".table-responsive").shouldHave(
                text("Student Name"), text(firstName + " " + lastName),
                text("Student Email"), text(email),
                text("Gender"), text("Female"),
                text("Mobile"), text(mobile),
                text("Date of Birth"), text("13 August,1991"),
                text("Subjects"), text("Computer Science"),
                text("Hobbies"), text("Reading"),
                text("Picture"), text("not_pass.jpg"),
                text("Address"), text(currentAddress),
                text("State and City"), text(state + " " + city)
        );

}
    }
