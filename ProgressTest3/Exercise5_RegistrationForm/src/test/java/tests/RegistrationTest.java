package tests;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import pages.RegistrationPage;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@DisplayName("Automation Practice Form - Extended Test Cases")
public class RegistrationTest extends BaseTest {
    static RegistrationPage registrationPage;

    @BeforeAll
    static void initPage() {
        registrationPage = new RegistrationPage(driver);
    }

    @ParameterizedTest(name = "Form Test: {0} {1} ({2}) → Expected: {5}")
    @CsvFileSource(resources = "/registration-data.csv", numLinesToSkip = 1)
    @Order(1)
    void testRegistration(String firstName, String lastName, String email,
                          String gender, String phone, String expected) {

        registrationPage.open();
        registrationPage.fillForm(firstName, lastName, email, gender, phone);

        boolean successModal = registrationPage.isSuccessModalDisplayed();
        String modalText = registrationPage.getModalText().toLowerCase();

        switch (expected) {
            case "success" -> assertTrue(successModal,
                    "Expected success modal but none appeared");
            case "error_email" ->
                    assertFalse(modalText.contains("thanks"), "Email invalid but modal appeared");
            case "error_phone" ->
                    assertFalse(modalText.contains("thanks"), "Phone invalid but modal appeared");
            case "error_first_last" ->
                    assertFalse(modalText.contains("thanks"), "Missing name but modal appeared");
            case "error_gender" ->
                    assertFalse(modalText.contains("thanks"), "Missing gender but modal appeared");
            default -> fail("Unknown expected result: " + expected);
        }
    }
}
