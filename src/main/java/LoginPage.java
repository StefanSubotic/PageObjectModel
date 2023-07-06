import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage extends BasePage {

    @FindBy(id = "user-name")
    WebElement userName;

    @FindBy(id = "password")
    WebElement password;

    @FindBy(id = "login-button")
    WebElement loginButton;

    @FindBy(css=".error-message-container h3")
    WebElement errorMess;

    public LoginPage(ChromeDriver driver)
    {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void setUserName(String name)
    {
     userName.sendKeys(name);
    }
    public void setPassword(String pass)
    {
        password.sendKeys(pass);
    }
    public void clickLogin()
    {
        loginButton.submit();
    }

    public void LoginOnPage()
    {
        userName.sendKeys("standard_user");
        password.sendKeys("secret_sauce");
        loginButton.submit();
    }

    public String getError(){
        return errorMess.getText();

    }

}
