package com.demoqa.tests;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class TextBoxTests {

    @BeforeAll
    static void configure() {
        Configuration.baseUrl = "https://demoqa.com";
//        Configuration.timeout = 10000; // 10 seconds
//        Configuration.browser = "opera";
        Configuration.browserSize = "1920x1080";
    }

    @Test
    void fillFormTest() {
        open("/text-box");
//        $("[id=userName]").setValue("Egor");
        $("#userName").setValue("Egor");
        $("#userEmail").setValue("Egor@egor.com");
        $("#currentAddress").setValue("Some address 1");
        $("#permanentAddress").setValue("Another address 1");
        $("#submit").click();

        $("#output #name").shouldHave(text("Egor"));
//        $("#output").$("#name").shouldHave(text("Egor"));
//        $("#output").shouldHave(text("Egor"));
//        $("#name").shouldHave(text("Egor"));
        $("#output #email").shouldHave(text("Egor@egor.com"));
        $("#output #currentAddress").shouldHave(text("Some address 1"));
        $("#output #permanentAddress").shouldHave(text("Another address 1"));
    }

}
