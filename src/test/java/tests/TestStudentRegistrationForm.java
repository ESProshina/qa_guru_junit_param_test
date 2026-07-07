package tests;

import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class TestStudentRegistrationForm extends TestBase {

    private final String successfulMessage = "Thanks for submitting the form";

    @Test
    void successfulFillFormTest() {
        open("/automation-practice-form");

        executeJavaScript("""
                document.getElementById('fixedban')?.remove();
                document.querySelector('footer')?.remove();
                document.querySelectorAll('[class*="ad"], [class*="banner"], iframe').forEach(el => el.remove());
                """);

        String firstName = "Elena";
        String lastName = "Black";
        String email = "elena@black.com";
        String gender = "Female";
        String mobileNumber = "9876543210";
        String year = "2000";
        String month = "June";
        String day = "3";
        String subject = "English";
        String hobby = "Music";
        String address = "Test Address 123";
        String state = "NCR";
        String city = "Delhi";

        $("#firstName").setValue(firstName);
        $("#lastName").setValue(lastName);
        $("#userEmail").setValue(email);
        $("[id=genterWrapper] [value=" + gender + "]").click();
        $("#userNumber").setValue(mobileNumber);
        $("#dateOfBirthInput").click();
        $(".react-datepicker__month-select").selectOption(month);
        $(".react-datepicker__year-select").selectOption(year);
        $(".react-datepicker__day.react-datepicker__day--00" + day).click();
        $("#subjectsInput").setValue(subject).pressEnter();
        $("#hobbiesWrapper").find(byText(hobby)).click();
        $("#uploadPicture").uploadFromClasspath("testbest.png");
        $("#currentAddress").setValue(address);
        $("#state").click();
        $("#state").parent().find(byText(state)).click();
        $("#city").click();
        $("#city").parent().find(byText(city)).click();

        executeJavaScript("arguments[0].click();", $("button[id=submit]"));

        $(".modal-content").shouldBe(visible);
        $(".modal-title").shouldHave(text(successfulMessage));
        $(".table-responsive").shouldHave(text("Student Name"));
        $(".table-responsive").shouldHave(text(firstName + " " + lastName));
        $(".table-responsive").shouldHave(text("Student Email"));
        $(".table-responsive").shouldHave(text(email));
        $(".table-responsive").shouldHave(text("Gender"));
        $(".table-responsive").shouldHave(text(gender));
        $(".table-responsive").shouldHave(text("Mobile"));
        $(".table-responsive").shouldHave(text(mobileNumber));
        $(".table-responsive").shouldHave(text("Date of Birth"));
        $(".table-responsive").shouldHave(text(day + " " + month + "," + year));
        $(".table-responsive").shouldHave(text("Subjects"));
        $(".table-responsive").shouldHave(text(subject));
        $(".table-responsive").shouldHave(text("Hobbies"));
        $(".table-responsive").shouldHave(text(hobby));
        $(".table-responsive").shouldHave(text("Picture"));
        $(".table-responsive").shouldHave(text("testbest.png"));
        $(".table-responsive").shouldHave(text("Address"));
        $(".table-responsive").shouldHave(text(address));
        $(".table-responsive").shouldHave(text("State and City"));
        $(".table-responsive").shouldHave(text(state + " " + city));
        $("#closeLargeModal").click();
    }

    @Test
    void successfulMandatoryFieldsTest() {
        open("/automation-practice-form");

        executeJavaScript("""
                document.getElementById('fixedban')?.remove();
                document.querySelector('footer')?.remove();
                document.querySelectorAll('[class*="ad"], [class*="banner"], iframe').forEach(el => el.remove());
                """);

        String firstName = "Elena";
        String lastName = "Black";
        String email = "elena@black.com";
        String gender = "Female";
        String mobileNumber = "9876543210";

        $("#firstName").setValue(firstName);
        $("#lastName").setValue(lastName);
        $("#userEmail").setValue(email);
        $("[id=genterWrapper] [value=" + gender + "]").click();
        $("#userNumber").setValue(mobileNumber);

        executeJavaScript("arguments[0].click();", $("button[id=submit]"));

        $(".modal-content").shouldBe(visible);
        $(".modal-title").shouldHave(text(successfulMessage));
        $(".table-responsive").shouldHave(text("Student Name"));
        $(".table-responsive").shouldHave(text(firstName + " " + lastName));
        $(".table-responsive").shouldHave(text("Student Email"));
        $(".table-responsive").shouldHave(text(email));
        $(".table-responsive").shouldHave(text("Gender"));
        $(".table-responsive").shouldHave(text(gender));
        $(".table-responsive").shouldHave(text("Mobile"));
        $(".table-responsive").shouldHave(text(mobileNumber));
        $("#closeLargeModal").click();

    }


    @CsvFileSource(resources = "/test_data/successfulFillFormTest.csv", numLinesToSkip = 1)
    @ParameterizedTest(name = "Заполнение формы: {0} {1} (Пол: {3}, Дата: {6} {5}, {7})")
    public void fillFormWithDifferentDataTest(String firstName, String lastName,
                                              String email, String gender,
                                              String mobileNumber, int day,
                                              String month, String year,
                                              String subject, String hobby) {
        open("/automation-practice-form");

        executeJavaScript("""
                document.getElementById('fixedban')?.remove();
                document.querySelector('footer')?.remove();
                document.querySelectorAll('[class*="ad"], [class*="banner"], iframe').forEach(el => el.remove());
                """);

        String address = "Test Address";
        String state = "NCR";
        String city = "Delhi";

        $("#firstName").setValue(firstName);
        $("#lastName").setValue(lastName);
        $("#userEmail").setValue(email);
        $("[id=genterWrapper] [value=" + gender + "]").click();
        $("#userNumber").setValue(mobileNumber);
        $("#dateOfBirthInput").click();
        $(".react-datepicker__month-select").selectOption(month);
        $(".react-datepicker__year-select").selectOption(year);
        //
        String daySelector = ".react-datepicker__day:not(.react-datepicker__day--outside-month)";
        $$(daySelector).findBy(text(String.valueOf(day))).click();
        $("#subjectsInput").setValue(subject).pressEnter();
        $("#hobbiesWrapper").find(byText(hobby)).click();
        $("#uploadPicture").uploadFromClasspath("testbest.png");
        $("#currentAddress").setValue(address);
        $("#state").click();
        $("#state").parent().find(byText(state)).click();
        $("#city").click();
        $("#city").parent().find(byText(city)).click();

        executeJavaScript("arguments[0].click();", $("button[id=submit]"));

        $(".modal-content").shouldBe(visible);
        $(".modal-title").shouldHave(text(successfulMessage));
        $(".table-responsive").$(byText("Student Name")).parent()
                .shouldHave(text(firstName + " " + lastName));
        $(".table-responsive").$(byText("Student Email")).parent().shouldHave(text(email));
        $(".table-responsive").$(byText("Gender")).parent().shouldHave(text(gender));
        $(".table-responsive").$(byText("Mobile")).parent().shouldHave(text(mobileNumber));
        $(".table-responsive").$(byText("Date of Birth")).parent()
                .shouldHave(text(day + " " + month + "," + year));
        $(".table-responsive").$(byText("Subjects")).parent().shouldHave(text(subject));
        $(".table-responsive").$(byText("Hobbies")).parent().shouldHave(text(hobby));

        $("#closeLargeModal").click();
    }


    @CsvFileSource(resources = "/test_data/successfulMandatoryFieldsTest.csv", numLinesToSkip = 1)
    @ParameterizedTest(name = "Обязательные поля: {0} {1} (Пол: {3}, Телефон: {4})")
    public void mandatoryFieldsWithDifferentDataTest(String firstName, String lastName,
                                                     String email, String gender,
                                                     String mobileNumber) {
        open("/automation-practice-form");

        executeJavaScript("""
                document.getElementById('fixedban')?.remove();
                document.querySelector('footer')?.remove();
                document.querySelectorAll('[class*="ad"], [class*="banner"], iframe').forEach(el => el.remove());
                """);

        $("#firstName").setValue(firstName);
        $("#lastName").setValue(lastName);
        $("#userEmail").setValue(email);
        $("[id=genterWrapper] [value=" + gender + "]").click();
        $("#userNumber").setValue(mobileNumber);

        executeJavaScript("arguments[0].click();", $("button[id=submit]"));

        $(".modal-content").shouldBe(visible);
        $(".modal-title").shouldHave(text(successfulMessage));
        $(".table-responsive").$(byText("Student Name")).parent()
                .shouldHave(text(firstName + " " + lastName));
        $(".table-responsive").$(byText("Student Email")).parent().shouldHave(text(email));
        $(".table-responsive").$(byText("Gender")).parent().shouldHave(text(gender));
        $(".table-responsive").$(byText("Mobile")).parent().shouldHave(text(mobileNumber));

        $("#closeLargeModal").click();
    }
}