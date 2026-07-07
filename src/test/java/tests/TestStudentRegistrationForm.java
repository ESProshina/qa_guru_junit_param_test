package tests;

import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;

import java.util.stream.Stream;

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

    @Test
    void negativeTestWhenFirstNameIsEmpty() {
        open("/automation-practice-form");

        executeJavaScript("""
                document.getElementById('fixedban')?.remove();
                document.querySelector('footer')?.remove();
                document.querySelectorAll('[class*="ad"], [class*="banner"], iframe').forEach(el => el.remove());
                """);

        String lastName = "Black";
        String email = "elena@black.com";
        String gender = "Female";
        String mobileNumber = "9876543210";

        $("#lastName").setValue(lastName);
        $("#userEmail").setValue(email);
        $("[id=genterWrapper] [value=" + gender + "]").click();
        $("#userNumber").setValue(mobileNumber);
        $("#submit").scrollTo();

        executeJavaScript("arguments[0].click();", $("button[id=submit]"));

        $(".modal-content").shouldNotBe(visible);
        SelenideElement firstNameField = $("#firstName");
        firstNameField.shouldHave(attribute("required"));
        boolean isValid = executeJavaScript("return arguments[0].checkValidity();", firstNameField);
        assert !isValid : "Field should be invalid when empty";
    }

    @Test
    void negativeTestWhenLastNameIsEmpty() {
        open("/automation-practice-form");

        executeJavaScript("""
                document.getElementById('fixedban')?.remove();
                document.querySelector('footer')?.remove();
                document.querySelectorAll('[class*="ad"], [class*="banner"], iframe').forEach(el => el.remove());
                """);

        String firstName = "Elena";
        String email = "elena@black.com";
        String gender = "Female";
        String mobileNumber = "9876543210";

        $("#firstName").setValue(firstName);
        $("#userEmail").setValue(email);
        $("[id=genterWrapper] [value=" + gender + "]").click();
        $("#userNumber").setValue(mobileNumber);
        $("#submit").scrollTo();

        executeJavaScript("arguments[0].click();", $("button[id=submit]"));

        $(".modal-content").shouldNotBe(visible);
        SelenideElement lastNameField = $("#lastName");
        lastNameField.shouldHave(attribute("required"));
        boolean isValid = executeJavaScript("return arguments[0].checkValidity();", lastNameField);
        assert !isValid : "Field should be invalid when empty";
    }

    @Test
    void negativeTestWhenMobileIsEmpty() {
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
        $("#submit").scrollTo();

        executeJavaScript("arguments[0].click();", $("button[id=submit]"));

        $(".modal-content").shouldNotBe(visible);
        SelenideElement mobileField = $("#userNumber");
        mobileField.shouldHave(attribute("required"));
        boolean isValid = executeJavaScript("return arguments[0].checkValidity();", mobileField);
        assert !isValid : "Field should be invalid when empty";
    }

    @Test
    void negativeTestWhenGenderIsEmpty() {
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
        $("#userNumber").setValue(mobileNumber);
        $("#submit").scrollTo();

        executeJavaScript("arguments[0].click();", $("button[id=submit]"));

        $(".modal-content").shouldNotBe(visible);
        $("#gender-radio-1").shouldNotBe(checked);
        $("#gender-radio-2").shouldNotBe(checked);
        $("#gender-radio-3").shouldNotBe(checked);
        boolean isFormValid = executeJavaScript("return document.querySelector('form').checkValidity();");
        assert !isFormValid : "Form should be invalid when gender not selected";
    }

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


    @EnumSource(StateData.class)
    @ParameterizedTest(name = "Заполнение формы со штатом: {0}")
    public void fillFormWithDifferentStatesTest(StateData stateData) {
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
        $("#state").parent().find(byText(stateData.getState())).click();
        $("#city").click();
        $("#city").parent().find(byText(city)).click();

        executeJavaScript("arguments[0].click();", $("button[id=submit]"));

        $(".modal-content").shouldBe(visible);
        $(".modal-title").shouldHave(text(successfulMessage));
        $(".table-responsive").$(byText("Student Name")).parent()
                .shouldHave(text(firstName + " " + lastName));
        $(".table-responsive").$(byText("State and City")).parent()
                .shouldHave(text(stateData.getState() + " " + city));

        $("#closeLargeModal").click();
    }


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



    enum StateData {
        NCR("NCR"),
        UTTAR_PRADESH("Uttar Pradesh"),
        RAJASTHAN("Rajasthan");

        private final String state;

        StateData(String state) {
            this.state = state;
        }

        public String getState() {
            return state;
        }

        @Override
        public String toString() {
            return state;
        }
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