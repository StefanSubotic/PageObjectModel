import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class InventoryTest extends BaseTest {
    LoginPage loginPage;
    CheckOneStepOne checkOneStepOne;

    InventoryPage inventoryPage;
    CartPage cartPage;


    @BeforeMethod
    public void SetUp()
    {
        driver = browserOpen();
        loginPage = new LoginPage(driver);
        inventoryPage = new InventoryPage(driver);
        cartPage = new CartPage(driver);
        checkOneStepOne = new CheckOneStepOne(driver);
        loginPage.LoginOnPage();

    }
    @Test
    public void AddToCartTwoProducts()
    {
        //Act
       /* loginPage.setUserName("standard_user");
        loginPage.setPassword("secret_sauce");*/

        //Arange
        //loginPage.clickLogin();
        inventoryPage.clickBackpack();
        inventoryPage.clickLight();

        //Assert
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/inventory.html");
        Assert.assertEquals(inventoryPage.getCartNumber(), "2");

    }
    @Test
    public void removeProduct()
    {
       /* loginPage.setUserName("standard_user");
        loginPage.setPassword("secret_sauce");
        loginPage.clickLogin();*/
        inventoryPage.clickBackpack();
        inventoryPage.clickLight();
        inventoryPage.removebackpack();

        Assert.assertEquals(inventoryPage.getCartNumber(), "1");

    }

    @Test
    public void sortProduct()
    {
       /* loginPage.setUserName("standard_user");
        loginPage.setPassword("secret_sauce");
        loginPage.clickLogin();*/
        inventoryPage.sortProducts("Price (high to low)");

        Assert.assertEquals(inventoryPage.getPrice(), "$49.99");
    }

    @Test
    public void getLowPrice()
    {
        /*loginPage.setUserName("standard_user");
        loginPage.setPassword("secret_sauce");
        loginPage.clickLogin();*/
        inventoryPage.sortProducts("Price (low to high)");

        Assert.assertEquals(inventoryPage.getPrice(), "$7.99");
    }

    @Test
    public void BuyProductsToTheEnd()
    {
        inventoryPage.clickLight();
        inventoryPage.clickBackpack();
        inventoryPage.clickCart();
        cartPage.clickCheckout();
        checkOneStepOne.setForm("Marko", "Naumovic", "11000");
        checkOneStepOne.clickFinish();

        Assert.assertEquals(checkOneStepOne.getMessage(), "Thank you for your order!");
    }
    @Test
    public void BuyProductsWithoutData()
    {
        inventoryPage.clickLight();
        inventoryPage.clickBackpack();
        inventoryPage.clickCart();
        cartPage.clickCheckout();
        checkOneStepOne.setForm("", "", "");


        Assert.assertEquals(checkOneStepOne.getError(), "Error: First Name is required");
    }
    @AfterMethod
    public void after()
    {
        driver.quit();
    }
}
