package tests;

import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;

import java.util.stream.Stream;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class TestStudentRegistrationForm extends TestBase {

    private final String successfulMessage = "Thanks for submitting the form";

    //


    @ValueSource(strings = {"1", "15", "20"})
    @ParameterizedTest(name = "Заполнение формы с днем рождения: {0}")
    public void fillFormWithDifferentDaysTest(String day) {
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
        $$(".react-datepicker__day:not(.react-datepicker__day--outside-month)")
                .findBy(text(day)).click();
        $("#subjectsInput").setValue(subject).pressEnter();
        executeJavaScript("arguments[0].click();", $("#hobbiesWrapper").find(byText(hobby)));
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
        $(".table-responsive").$(byText("Date of Birth")).parent()
                .shouldHave(text(day + " " + month + "," + year));

        $("#closeLargeModal").click();
    }


    @CsvSource({
            "January, 1990",
            "March, 1995",
            "July, 2000"
    })
    @ParameterizedTest(name = "Заполнение формы с датой: {0} {1}")
    public void fillFormWithDifferentMonthsAndYearsTest(String month, String year) {
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
        String day = "15";
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
        $$(".react-datepicker__day:not(.react-datepicker__day--outside-month)")
                .findBy(text(day)).click();
        $("#subjectsInput").setValue(subject).pressEnter();
        executeJavaScript("arguments[0].click();", $("#hobbiesWrapper").find(byText(hobby)));
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
        $(".table-responsive").$(byText("Date of Birth")).parent()
                .shouldHave(text(day + " " + month + "," + year));

        $("#closeLargeModal").click();
    }


    @CsvFileSource(resources = "/test_data/successfulFillFormTest.csv", numLinesToSkip = 1)
    @ParameterizedTest(name = "Заполнение формы из CSV: {0} {1}")
    public void fillFormFromCsvFileTest(String firstName, String lastName,
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
        $$(".react-datepicker__day:not(.react-datepicker__day--outside-month)")
                .findBy(text(String.valueOf(day))).click();
        $("#subjectsInput").setValue(subject).pressEnter();
        executeJavaScript("arguments[0].click();", $("#hobbiesWrapper").find(byText(hobby)));
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


    @MethodSource("subjectsAndHobbiesProvider")
    @ParameterizedTest(name = "Заполнение формы с предметом: {0} и хобби: {1}")
    public void fillFormWithDifferentSubjectsAndHobbiesTest(String subject, String hobby) {
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
        $$(".react-datepicker__day:not(.react-datepicker__day--outside-month)")
                .findBy(text(day)).click();
        $("#subjectsInput").setValue(subject).pressEnter();
        executeJavaScript("arguments[0].click();", $("#hobbiesWrapper").find(byText(hobby)));
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
        $(".table-responsive").$(byText("Subjects")).parent().shouldHave(text(subject));
        $(".table-responsive").$(byText("Hobbies")).parent().shouldHave(text(hobby));

        $("#closeLargeModal").click();
    }

    //

    @ValueSource(strings = {"1234567890", "9876543210", "5555555555"})
    @ParameterizedTest(name = "Обязательные поля с телефоном: {0}")
    public void mandatoryFieldsWithDifferentPhonesTest(String mobileNumber) {
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


    @CsvFileSource(resources = "/test_data/successfulMandatoryFieldsTest.csv", numLinesToSkip = 1)
    @ParameterizedTest(name = "Обязательные поля: {0} {1} (Пол: {3})")
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


    @EnumSource(GenderData.class)
    @ParameterizedTest(name = "Обязательные поля с полом: {0}")
    public void mandatoryFieldsWithDifferentGendersTest(GenderData genderData) {
        open("/automation-practice-form");

        executeJavaScript("""
                document.getElementById('fixedban')?.remove();
                document.querySelector('footer')?.remove();
                document.querySelectorAll('[class*="ad"], [class*="banner"], iframe').forEach(el => el.remove());
                """);

        String firstName = "Elena";
        String lastName = "Black";
        String email = "elena@black.com";
        String mobileNumber = "9876543210";

        $("#firstName").setValue(firstName);
        $("#lastName").setValue(lastName);
        $("#userEmail").setValue(email);
        $("[id=genterWrapper] [value=" + genderData.getGender() + "]").click();
        $("#userNumber").setValue(mobileNumber);

        executeJavaScript("arguments[0].click();", $("button[id=submit]"));

        $(".modal-content").shouldBe(visible);
        $(".modal-title").shouldHave(text(successfulMessage));
        $(".table-responsive").$(byText("Student Name")).parent()
                .shouldHave(text(firstName + " " + lastName));
        $(".table-responsive").$(byText("Student Email")).parent().shouldHave(text(email));
        $(".table-responsive").$(byText("Gender")).parent().shouldHave(text(genderData.getGender()));
        $(".table-responsive").$(byText("Mobile")).parent().shouldHave(text(mobileNumber));

        $("#closeLargeModal").click();
    }



    static Stream<Arguments> subjectsAndHobbiesProvider() {
        return Stream.of(
                Arguments.of("English", "Music"),
                Arguments.of("Maths", "Sports"),
                Arguments.of("Physics", "Reading")
        );
    }



    enum GenderData {
        MALE("Male"),
        FEMALE("Female");

        private final String gender;

        GenderData(String gender) {
            this.gender = gender;
        }

        public String getGender() {
            return gender;
        }

        @Override
        public String toString() {
            return gender;
        }
    }
}