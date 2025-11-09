package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.JavascriptExecutor;

public class RegistrationPage extends BasePage {

    public RegistrationPage(WebDriver driver) {
        super(driver);
    }

    private By firstName = By.id("firstName");
    private By lastName = By.id("lastName");
    private By email = By.id("userEmail");
    private By mobile = By.id("userNumber");
    private By submitButton = By.id("submit");
    private By successModal = By.id("example-modal-sizes-title-lg");

    public void open() {
        navigateTo("https://demoqa.com/automation-practice-form");
        // Xóa quảng cáo gây che khuất
        ((JavascriptExecutor) driver).executeScript(
                "document.querySelectorAll('iframe, #fixedban, .advertisement').forEach(e => e.remove());"
        );
    }

    // Hàm chọn giới tính động
    public void selectGender(String gender) {
        if (gender == null || gender.trim().isEmpty()) return;
        By genderOption = By.xpath("//label[text()='" + gender + "']");
        clickWithJS(genderOption);
    }

    public void fillForm(String fName, String lName, String emailAddr, String gender, String phone) {
        type(firstName, fName == null ? "" : fName);
        type(lastName, lName == null ? "" : lName);
        type(email, emailAddr == null ? "" : emailAddr);
        if (gender != null && !gender.trim().isEmpty())
            selectGender(gender);
        type(mobile, phone == null ? "" : phone);
        scrollTo(submitButton);
        click(submitButton);
    }

    public boolean isSuccessModalDisplayed() {
        try {
            return waitForVisibility(successModal).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public String getModalText() {
        try {
            return waitForVisibility(successModal).getText();
        } catch (Exception e) {
            return "";
        }
    }
}
