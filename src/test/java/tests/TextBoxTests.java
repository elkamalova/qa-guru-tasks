package tests;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class TextBoxTests {

    static Logger log = LoggerFactory.getLogger(TextBoxTests.class);

    @BeforeAll
    static void setUpConfig() {
        log.info("@BeforeAll");
        Configuration.browser = "chrome";
        Configuration.startMaximized = true;
    }

    @Test
    void successfulSubmitFormTest() {
        open("https://demoqa.com/automation-practice-form");

        $("#firstName").setValue("Elka");
        $("#lastName").setValue("Sun");
        $("#userEmail").setValue("elka@mail.com");
        $("#gender-radio-2").parent().click();
        $("#userNumber").setValue("1234567890");
        $("#dateOfBirthInput").click();
        $(".react-datepicker__month-select").selectOption("January");
        $(".react-datepicker__year-select").selectOption("1990");
        $("[aria-label=\"Choose Monday, January 1st, 1990\"]").click();
        $("[id=subjectsInput]").setValue("English").pressEnter();
        $("[for='hobbies-checkbox-1']").click();
        $("[for='hobbies-checkbox-2']").click();
        $("#uploadPicture").uploadFile(new File("src/test/resources/1.png"));
        $("[id=currentAddress]").setValue("Samara");
        $("#react-select-3-input").setValue("Haryana").pressEnter();
        $("#react-select-4-input").setValue("Karnal").pressEnter();
        $("[id=submit]").click();


        $("[id=example-modal-sizes-title-lg]").shouldHave(text("Thanks for submitting the form"));
        $(".table-responsive").shouldHave(text("Label"), text("Values"));
        $(".table-responsive").shouldHave(text("Student Name"), text("Elka Sun"));
        $(".table-responsive").shouldHave(text("Student Email"), text("elka@mail.com"));
        $(".table-responsive").shouldHave(text("Gender"), text("Female"));
        $(".table-responsive").shouldHave(text("Mobile"), text("1234567890"));
        $(".table-responsive").shouldHave(text("Date of Birth"), text("01 January,1990"));
        $(".table-responsive").shouldHave(text("Subjects"), text("English"));
        $(".table-responsive").shouldHave(text("Hobbies"), text("Sports, Reading"));
        $(".table-responsive").shouldHave(text("Picture"), text("1.png"));
        $(".table-responsive").shouldHave(text("Address"), text("Samara"));
        $(".table-responsive").shouldHave(text("State and City"), text("Haryana Karnal"));

    }

}
