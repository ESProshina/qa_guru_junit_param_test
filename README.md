# 📝 Student Registration Form Tests

Проект с автоматизированными тестами на Java + Selenide для формы регистрации студента (https://demoqa.com/automation-practice-form)

## 🚀 Технологии

- **Java 21**
- **JUnit 5** - тестовый фреймворк
- **Selenide** - для UI тестирования
- **Gradle** - система сборки
- **Allure** - для отчетов (опционально)

## 📋 Описание

Проект содержит автоматизированные тесты для проверки формы регистрации студента:
- Позитивные тесты (успешное заполнение формы)
- Негативные тесты (валидация обязательных полей)
- Параметризованные тесты с различными наборами данных из CSV файлов

## 🧪 Структура тестов

### Обычные тесты
- `successfulFillFormTest()` - полное заполнение формы
- `successfulMandatoryFieldsTest()` - заполнение только обязательных полей
- `negativeTestWhenFirstNameIsEmpty()` - проверка валидации имени
- `negativeTestWhenLastNameIsEmpty()` - проверка валидации фамилии
- `negativeTestWhenMobileIsEmpty()` - проверка валидации телефона
- `negativeTestWhenGenderIsEmpty()` - проверка валидации пола

### Параметризованные тесты
- `fillFormWithDifferentDataTest()` - проверка полной формы с разными данными из CSV
- `mandatoryFieldsWithDifferentDataTest()` - проверка обязательных полей с разными данными из CSV

## 📂 CSV файлы с тестовыми данными

Тестовые данные хранятся в папке `src/test/resources/test_data/`:

| Файл | Описание |
|------|----------|
| `successfulFillFormTest.csv` | Данные для полного заполнения формы |
| `successfulMandatoryFieldsTest.csv` | Данные для обязательных полей |

### Пример данных в CSV:
```csv
firstName,lastName,email,gender,mobileNumber,day,month,year,subject,hobby
Elena,Black,elena@black.com,Female,9876543210,3,June,2000,English,Music

🗂️ Структура проекта
text
qa_guru_java_homework/
├── src/
│   └── test/
│       ├── java/
│       │   └── tests/
│       │       ├── TestBase.java          # Базовый класс с настройками
│       │       └── TestStudentRegistrationForm.java  # Тесты
│       └── resources/
│           └── test_data/
│               ├── successfulFillFormTest.csv
│               └── successfulMandatoryFieldsTest.csv
├── build.gradle
├── gradlew
├── gradlew.bat
└── README.md


}
🎯 Особенности
✅ Использование параметризованных тестов с CSV

✅ Разные дата-провайдеры (@CsvFileSource, @ValueSource, @MethodSource)

✅ Обработка баннеров и рекламы через JavaScript

✅ Проверка обязательных полей

✅ Проверка валидации форм

После исправления:
✅ Созданы параметризованные тесты с разными дата-провайдерами:

@ValueSource — для дней рождения и телефонов

@CsvSource — для месяцев и годов

@CsvFileSource — для данных из CSV файлов

@MethodSource — для предметов и хобби

@EnumSource — для полов

✅ Убраны обычные тесты — остались только параметризованные

✅ Все тесты проходят — форма регистрации работает корректно



