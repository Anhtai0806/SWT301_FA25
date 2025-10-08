
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import taidna.example.AccountService;

import static org.junit.jupiter.api.Assertions.*;

public class AccountServiceTest {

    static AccountService service;

    @BeforeAll
    static void initAll() {
        service = new AccountService();
        System.out.println("Bắt đầu kiểm thử AccountService...\n");
    }

    @AfterAll
    static void cleanupAll() {
        service = null;
        System.out.println("\nKết thúc toàn bộ kiểm thử.");
    }

    @DisplayName("Kiểm tra đăng ký tài khoản từ file test-data.csv")
    @ParameterizedTest(name = "Test {index} => username={0}, password={1}, email={2}, expected={3}")
    @CsvFileSource(resources = "/test-data.csv", numLinesToSkip = 1)
    void testRegisterAccount(String username, String password, String email, boolean expected) {
        boolean actual = service.registerAccount(username, password, email);

        // So sánh kết quả mong đợi và thực tế
        assertEquals(expected, actual,
                () -> String.format("❌ Sai kết quả: username=%s, password=%s, email=%s", username, password, email));

        // In log kết quả chi tiết
        String status = actual ? "✅ SUCCESS" : "❌ FAIL";
        System.out.printf("username=%-10s | password=%-10s | email=%-25s | expected=%-5s | actual=%-5s | %s%n",
                username, password, email, expected, actual, status);
    }
}
