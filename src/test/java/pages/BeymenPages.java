package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.Driver;

public class BeymenPages {

    public BeymenPages()   {
        PageFactory.initElements(Driver.getDriver(),this);;
    }

    @FindBy(xpath = "//button[@id='onetrust-accept-btn-handler']")
    public WebElement cerezKabul;

    @FindBy(id="genderManButton")
    public WebElement genderManButton;

    @FindBy(xpath = "//input[@aria-controls='3-suggestions']")
    public WebElement searchbox;

    @FindBy(xpath = "(//*[@class='m-productCard__photo'])[1]")
    public WebElement firstProduct;

    @FindBy(xpath = "//*[@class='o-productDetail__title']")
    public WebElement productDetail;

    @FindBy (xpath = "//*[@id='priceNew']")
    public WebElement price;

    @FindBy(xpath = "//span[text()='L']")
    public WebElement urunBedeni;

    @FindBy(xpath = "//a[@title='Sepetim']")
    public WebElement sepetim;

    @FindBy(xpath = "//button[@id='addBasket']")
    public WebElement sepeteEkle;

    @FindBy(xpath = "//span[@class='m-productPrice__salePrice']")
    public WebElement sepettekiUrunFiyati;

    @FindBy(xpath = "//*[@id='quantitySelect0-key-0']")
    public WebElement sepettekiUrunuArtirButonu;

    @FindBy (xpath = "//button[@id='removeCartItemBtn0-key-0']")
    public WebElement sepettekiUrunSilButonu;

    @FindBy(xpath = "//*[text()='Sepetinizde Ürün Bulunmamaktadır']")
    public WebElement sepetinizdeUrunBulunmamaktadir;
}
