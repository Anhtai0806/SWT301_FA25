package com.swp.myleague.selenium;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;

import com.swp.myleague.pages.SignupPage;
import com.swp.myleague.utils.DriverFactory;

public class AuthControllerSeleniumTest {

    private WebDriver driver;
    private SignupPage signupPage;

    @BeforeEach
    void setUp() {
        driver = DriverFactory.createDriver();
        signupPage = new SignupPage(driver);
    }

    @AfterEach
    void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    // ========== TEST CASE 1: Đăng ký thành công với email hợp lệ ==========
    @Test
    @DisplayName("TC1: Đăng ký thành công với email hợp lệ")
    void testSignup_ValidEmail_Success() throws InterruptedException {
        // Given
        String username = "testuser_" + System.currentTimeMillis();
        String email = "test_" + System.currentTimeMillis() + "@example.com";
        String password = "password123";

        // When
        signupPage.navigateToSignup();
        signupPage.signup(username, email, password);
        Thread.sleep(2000); // Wait for page to load

        // Then
        assertTrue(signupPage.isSuccessMessageDisplayed() || 
                   signupPage.getSuccessMessage().contains("Vui lòng kiểm tra email"),
                   "Nên hiển thị message thành công");
    }

    // ========== TEST CASE 2: Email invalid format - hiển thị error ==========
    @Test
    @DisplayName("TC2: Email invalid format - hiển thị error")
    void testSignup_InvalidEmailFormat_ShowsError() throws InterruptedException {
        String username = "testuser_" + System.currentTimeMillis();
        String password = "password123";

        // Test empty email
        signupPage.navigateToSignup();
        signupPage.signup(username, "", password);
        Thread.sleep(2000);
        assertTrue(signupPage.hasAlertWithError() || 
                   signupPage.isErrorMessageDisplayed() || 
                   signupPage.getErrorMessage().contains("vui lòng nhập đúng định dạng"),
                   "Nên hiển thị error message hoặc alert cho empty email");

        // Test email không có @
        username = "testuser_" + System.currentTimeMillis();
        signupPage.navigateToSignup();
        signupPage.signup(username, "invalidemail.com", password);
        Thread.sleep(2000);
        assertTrue(signupPage.hasAlertWithError() || 
                   signupPage.isErrorMessageDisplayed() || 
                   signupPage.getErrorMessage().contains("vui lòng nhập đúng định dạng"),
                   "Nên hiển thị error message hoặc alert cho email không có @");

        // Test email không có domain
        username = "testuser_" + System.currentTimeMillis();
        signupPage.navigateToSignup();
        signupPage.signup(username, "user@", password);
        Thread.sleep(2000);
        assertTrue(signupPage.hasAlertWithError() || 
                   signupPage.isErrorMessageDisplayed() || 
                   signupPage.getErrorMessage().contains("vui lòng nhập đúng định dạng"),
                   "Nên hiển thị error message hoặc alert cho email không có domain");

        // Test email không có TLD
        username = "testuser_" + System.currentTimeMillis();
        signupPage.navigateToSignup();
        signupPage.signup(username, "user@domain", password);
        Thread.sleep(2000);
        assertTrue(signupPage.hasAlertWithError() || 
                   signupPage.isErrorMessageDisplayed() || 
                   signupPage.getErrorMessage().contains("vui lòng nhập đúng định dạng"),
                   "Nên hiển thị error message hoặc alert cho email không có TLD");

        // Test email có nhiều @
        username = "testuser_" + System.currentTimeMillis();
        signupPage.navigateToSignup();
        signupPage.signup(username, "user@@example.com", password);
        Thread.sleep(2000);
        assertTrue(signupPage.hasAlertWithError() || 
                   signupPage.isErrorMessageDisplayed() || 
                   signupPage.getErrorMessage().contains("vui lòng nhập đúng định dạng"),
                   "Nên hiển thị error message hoặc alert cho email có nhiều @");
    }

    // ========== TEST CASE 3: Email TLD invalid - hiển thị error ==========
    @Test
    @DisplayName("TC3: Email TLD invalid - hiển thị error")
    void testSignup_EmailInvalidTLD_ShowsError() throws InterruptedException {
        String username = "testuser_" + System.currentTimeMillis();
        String password = "password123";

        // Test TLD quá dài
        signupPage.navigateToSignup();
        signupPage.signup(username, "user@example.toolongtld", password);
        Thread.sleep(2000);
        assertTrue(signupPage.hasAlertWithError() || 
                   signupPage.isErrorMessageDisplayed() || 
                   signupPage.getErrorMessage().contains("vui lòng nhập đúng định dạng"),
                   "Nên hiển thị error message hoặc alert cho TLD quá dài");

        // Test TLD quá ngắn
        username = "testuser_" + System.currentTimeMillis();
        signupPage.navigateToSignup();
        signupPage.signup(username, "user@example.c", password);
        Thread.sleep(2000);
        assertTrue(signupPage.hasAlertWithError() || 
                   signupPage.isErrorMessageDisplayed() || 
                   signupPage.getErrorMessage().contains("vui lòng nhập đúng định dạng"),
                   "Nên hiển thị error message hoặc alert cho TLD quá ngắn");
    }

    // ========== TEST CASE 4: Username đã tồn tại - hiển thị error ==========
    @Test
    @DisplayName("TC4: Username đã tồn tại - hiển thị error")
    void testSignup_UsernameExists_ShowsError() throws InterruptedException {
        // Given: Sử dụng username đã tồn tại trong database
        String existingUsername = "tai"; // Username đã tồn tại trong DB
        String newEmail = "newemail_" + System.currentTimeMillis() + "@example.com";
        String password = "password123";

        // When: Thử đăng ký với username đã tồn tại nhưng email khác
        signupPage.navigateToSignup();
        signupPage.signup(existingUsername, newEmail, password);
        Thread.sleep(3000); // Wait for server response

        // Then: Nên hiển thị error message "Username đã được sử dụng"
        String errorMessage = signupPage.getErrorMessage();
        assertTrue(signupPage.isErrorMessageDisplayed(), 
                   "Nên hiển thị error message. Error message nhận được: " + errorMessage);
        assertTrue(errorMessage.contains("Username đã được sử dụng") || 
                   errorMessage.contains("Username"),
                   "Error message nên chứa 'Username đã được sử dụng', nhưng nhận được: " + errorMessage);
    }

    // ========== TEST CASE 5: Email đã tồn tại - hiển thị error ==========
    @Test
    @DisplayName("TC5: Email đã tồn tại - hiển thị error")
    void testSignup_EmailExists_ShowsError() throws InterruptedException {
        // Given: Sử dụng email đã tồn tại trong database
        String newUsername = "newuser_" + System.currentTimeMillis();
        String existingEmail = "anhtai80605@gmail.com"; // Email đã tồn tại trong DB
        String password = "password123";

        // When: Thử đăng ký với email đã tồn tại nhưng username khác
        signupPage.navigateToSignup();
        signupPage.signup(newUsername, existingEmail, password);
        Thread.sleep(3000); // Wait for server response

        // Then: Nên hiển thị error message "Email đã được sử dụng"
        String errorMessage = signupPage.getErrorMessage();
        assertTrue(signupPage.isErrorMessageDisplayed(), 
                   "Nên hiển thị error message. Error message nhận được: " + errorMessage);
        assertTrue(errorMessage.contains("Email đã được sử dụng") || 
                   errorMessage.contains("Email"),
                   "Error message nên chứa 'Email đã được sử dụng', nhưng nhận được: " + errorMessage);
    }

    // ========== TEST CASE 6: Username và Email đều đã tồn tại - ưu tiên username ==========
    @Test
    @DisplayName("TC6: Username và Email đều đã tồn tại - ưu tiên hiển thị error username")
    void testSignup_BothUsernameAndEmailExist_ShowsUsernameError() throws InterruptedException {
        // Given: Sử dụng cả username và email đã tồn tại trong database
        String existingUsername = "tai"; // Username đã tồn tại trong DB
        String existingEmail = "anhtai80605@gmail.com"; // Email đã tồn tại trong DB
        String password = "password123";

        // When: Thử đăng ký với cả username và email đều đã tồn tại
        signupPage.navigateToSignup();
        signupPage.signup(existingUsername, existingEmail, password);
        Thread.sleep(3000); // Wait for server response

        // Then: Nên hiển thị error message "Username đã được sử dụng" (ưu tiên username)
        String errorMessage = signupPage.getErrorMessage();
        assertTrue(signupPage.isErrorMessageDisplayed(), 
                   "Nên hiển thị error message. Error message nhận được: " + errorMessage);
        assertTrue(errorMessage.contains("Username đã được sử dụng") || 
                   errorMessage.contains("Username"),
                   "Error message nên ưu tiên hiển thị 'Username đã được sử dụng', nhưng nhận được: " + errorMessage);
    }
}

