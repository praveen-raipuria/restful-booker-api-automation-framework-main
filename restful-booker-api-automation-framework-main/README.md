# Restful Booker API Automation Framework

This is my personal API automation project built to learn and practice API test automation using Java and RestAssured.

---

## What This Project Does

This framework automates testing of the [Restful Booker API](https://restful-booker.herokuapp.com) which is a practice hotel booking API. It tests creating, reading, updating and deleting bookings automatically without doing it manually every time.

---

## Tools and Technologies Used

- **Java 21** — programming language
- **RestAssured 5.3.1** — for making and validating API calls
- **TestNG 7.8.0** — for running tests and managing test flow
- **Jackson** — for converting JSON responses into Java objects
- **Lombok** — to reduce repetitive code in model classes
- **Java Faker** — to generate random test data
- **Log4j2** — to log what is happening during test execution
- **Allure Reports** — to generate a visual HTML test report
- **Maven** — for managing dependencies and running tests

---

## Project Structure

```
restful-booker-api-automation-framework/
├── src/main/java/com/restfulbooker/
│   ├── clients/
│   │   ├── AuthClient.java         → gets the login token from API
│   │   └── BookingClient.java      → makes all booking API calls
│   ├── config/
│   │   └── ConfigManager.java      → reads settings from config.properties
│   ├── endpoints/
│   │   └── APIEndpoints.java       → stores all API URLs in one place
│   ├── models/
│   │   ├── AuthRequest.java        → login request body
│   │   ├── Booking.java            → booking request and response body
│   │   └── BookingDates.java       → checkin and checkout dates
│   ├── specs/
│   │   └── RequestFactory.java     → base setup for every API request
│   └── utils/
│       ├── FrameworkConstants.java → file paths and header constants
│       ├── LoggerUtils.java        → handles all logging
│       ├── RandomDataUtil.java     → generates random names, prices, dates
│       ├── RequestUtils.java       → GET POST PUT PATCH DELETE methods
│       └── TestDataBuilder.java    → reads test data from JSON file
│
├── src/test/java/com/restfulbooker/
│   ├── base/
│   │   └── BaseTest.java           → runs before all tests, sets up token
│   └── tests/
│       ├── BookingE2ETests.java    → tests full create to delete flow
│       └── NegativeBookingTests.java → tests invalid scenarios
│
├── src/test/resources/
│   ├── config.properties           → base URL, username, password, timeout
│   ├── log4j2.xml                  → logging settings
│   └── testdata/bookings.json      → test data for valid and invalid bookings
│
├── testng.xml                      → controls which tests run
└── pom.xml                         → all project dependencies
```

---

## What Gets Tested

**Happy Path Tests (BookingE2ETests.java)**

| Test | What it does |
|---|---|
| testCreateBooking | Creates a new booking and saves the ID |
| testGetBooking | Gets the booking we just created |
| testUpdateBooking | Updates all fields of the booking |
| testPartialUpdateBooking | Updates only some fields |
| testDeleteBooking | Deletes the booking |
| testVerifyDeletion | Confirms the booking is really gone |

**Negative Tests (NegativeBookingTests.java)**

| Test | What it does |
|---|---|
| testInvalidToken | Tries to delete with a wrong token — expects 403 |
| testGetNonExistentBooking | Tries to get a booking that does not exist — expects 404 |
| testCreateBookingMissingFields | Sends empty booking data — expects 500 |
| testUpdateWithoutToken | Tries to update without a token — expects 403 |

**Total: 10 test cases**

---

## How to Run

**Step 1 — Clone the project**
```bash
git clone https://github.com/SalunkeGaurav/restful-booker-api-automation-framework.git
```

**Step 2 — Open in IntelliJ and let Maven download dependencies**

**Step 3 — Run all tests**
```bash
mvn clean test
```

**Step 4 — See the Allure report**
```bash
allure serve target/allure-results
```

---

## How to Install Allure on Windows

```bash
scoop install allure
```

---

## Author

**Gaurav Salunke**
[LinkedIn](https://linkedin.com/in/gaurav-salunke-117594195) | [GitHub](https://github.com/SalunkeGaurav)
