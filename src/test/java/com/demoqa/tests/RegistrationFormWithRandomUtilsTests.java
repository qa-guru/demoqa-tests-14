package com.demoqa.tests;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static com.demoqa.utils.RandomUtils.*;
import static java.lang.String.format;

public class RegistrationFormWithRandomUtilsTests extends TestBase {

    String firstName,
            lastName,
            userEmail,
            userNumber,
            day,
            month,
            year;

    @BeforeEach
    void prepareTestData() {
//        firstName = RandomStringUtils.randomAlphabetic(10);
        firstName = getRandomString(10); // UR17VAR1RT
        lastName = getRandomString(10);
        userEmail = getRandomEmail();
        userNumber = getRandomPhone();
        day = "30";
        month = "July";
        year = "2008";
    }

    @Test
    void fillFormTest() {
        open("/automation-practice-form");
        $(".practice-form-wrapper").shouldHave(text("Student Registration Form"));
        executeJavaScript("$('footer').remove()");
        executeJavaScript("$('#fixedban').remove()");

        $("#firstName").setValue(firstName);
        $("#lastName").setValue(lastName);
        $("#userEmail").setValue(userEmail);
        $("#genterWrapper").$(byText("Other")).click(); // best
        $("#userNumber").setValue(userNumber);
        $("#dateOfBirthInput").click();
        $(".react-datepicker__month-select").selectOption(month);
        $(".react-datepicker__year-select").selectOption(year);
//        $(".react-datepicker__day--0" + day + ":not(.react-datepicker__day--outside-month)").click();
        $(format(".react-datepicker__day--0%s:not(.react-datepicker__day--outside-month)", day)).click();
        $("#subjectsInput").setValue("Math").pressEnter();
        $("#hobbiesWrapper").$(byText("Sports")).click(); // best
        $("#uploadPicture").uploadFromClasspath("img/1.png");
        $("#currentAddress").setValue("Some address 1");
        $("#state").click();
        $("#stateCity-wrapper").$(byText("NCR")).click();
        $("#city").click();
        $("#stateCity-wrapper").$(byText("Delhi")).click();
        $("#submit").click();

        $(".modal-dialog").should(appear);
        $("#example-modal-sizes-title-lg").shouldHave(text("Thanks for submitting the form"));

        $(".table-responsive table").$(byText("Student Name"))
                .parent().shouldHave(text(firstName + " " + lastName));

//        String expectedFullName = firstName + " " + lastName;
        String expectedFullName = format("%s %s", firstName, lastName);
        $(".table-responsive table").$(byText("Student Name"))
                .parent().shouldHave(text(expectedFullName));
        $(".table-responsive table").$(byText("Student Email"))
                .parent().shouldHave(text(userEmail));

        String expectedDateOfBirth = format("%s %s,%s", day, month, year);
        $(".table-responsive table").$(byText("Date of Birth"))
                .parent().shouldHave(text(expectedDateOfBirth));
    }

}
