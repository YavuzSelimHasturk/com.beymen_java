package stepDefinitions;

import io.cucumber.java.en.*;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.ui.Select;
import pages.BeymenPages;
import utils.ConfigReader;
import utils.Driver;
import utils.ReusableMethods;

import java.io.FileWriter;
import java.io.IOException;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import static org.junit.Assert.*;

public class BeymenStepDefinitions {
    String fiyat;
    BeymenPages beymen = new BeymenPages();
    final static Logger logger = LogManager.getLogger(BeymenStepDefinitions.class);

    @Given("kullanici Beymen sayfasina gider")
    public void kullanici_beymen_sayfasina_gider() {

        Driver.getDriver().get(ConfigReader.getProperty("beymenUrl"));
        logger.info("Beymen anasayfasina gidildi");
    }
    @Then("Cerezleri kabul eder ve cinsiyet secimini yapar")
    public void cerezleriKabulEderVeCinsiyetSeciminiYapar() {

        ReusableMethods.bekle(3);
        beymen.cerezKabul.click();
        ReusableMethods.bekle(2);
        beymen.genderManButton.click();
        logger.info("Cerezler kabul edildi ve cinsiyet secimi yapildi");
    }
    @Given("Beymen anasayfasinin acildigini kontrol eder")
    public void beymen_anasayfasinin_acildigini_kontrol_eder() {
        String str= "Beymen";

        assertTrue(Driver.getDriver().getTitle().contains(str));
        logger.info("Beymen anasayfasinin dogru bir sekilde acildigi kontrol edildi");
    }
    @Given("Arama kutucuguna {string} kelimesini girer ve aratir")
    public void arama_kutucuguna_kelimesini_girer_ve_aratir(String aranacakUrun) {
        aranacakUrun = ConfigReader.getProperty("aranacakBeymen");
        beymen.searchbox.sendKeys(aranacakUrun+ Keys.ENTER);

        logger.info("Aranacak urun ismi girildi ve arama yapildi");
    }
    @Then("Sonuca gore sergilenen urunlerden rastgele bir urun secer")
    public void sonuca_gore_sergilenen_urunlerden_rastgele_bir_urun_secer() {
        ReusableMethods.bekle(2);
        beymen.firstProduct.click();
        logger.info("Aranan urun sonuclarindan biri secildi");
    }

    @Then("Secilen urunu sepete ekler")
    public void secilen_urunu_sepete_ekler() {
        fiyat = beymen.price.getText();
        fiyat=fiyat.replaceAll("\\D","")+"00TL";
        fiyat=fiyat.replaceAll("//S","");

        beymen.urunBedeni.click();
        ReusableMethods.bekle(2);
        beymen.sepeteEkle.click();
        ReusableMethods.bekle(3);
        beymen.sepetim.click();

        logger.info("Secilen urun sepete eklendi");
    }
    @Then("Urun sayfasindaki fiyat ile sepette yer alan urunun fiyatinin dogrulugunu karsilastirir")
    public void urun_sayfasindaki_fiyat_ile_sepette_yer_alan_urunun_fiyatinin_dogrulugunu_karsilastirir() {
        String sepettekiFiyat =beymen.sepettekiUrunFiyati.getText().replaceAll("\\W","");
        assertEquals(fiyat,sepettekiFiyat);
        logger.info("Urun sayfasindaki fiyat ile sepette yer alan urunun fiyatinin dogrulugu karsilastirildi ");
    }
    @Then("Adet sayisini arttirarak urun adedinin {int} oldugunu dogrular")
    public void adet_sayisini_arttirarak_urun_adedinin_oldugunu_dogrular(Integer sayi) {

        String birimFiyatStr = beymen.sepettekiUrunFiyati.getText();
        String sepettekiUrunStr = birimFiyatStr.replaceAll("\\D","");
        int birimFiyat = Integer.parseInt(sepettekiUrunStr);
        //System.out.println(birimFiyat);
        ReusableMethods.bekle(3);

        String secim = sayi + " adet";
        Select select = new Select(beymen.sepettekiUrunuArtirButonu);
        select.selectByVisibleText(secim);

        ReusableMethods.bekle(3);
        Driver.getDriver().navigate().refresh();
        ReusableMethods.bekle(3);
        String toplamFiyatStr = beymen.sepettekiUrunFiyati.getText();
        String sepettekiUrunStrs = toplamFiyatStr.replaceAll("\\D","");
        int toplamFiyat = Integer.parseInt(sepettekiUrunStrs);
        //System.out.println(toplamFiyat);
        assertEquals((toplamFiyat / birimFiyat), (int) sayi);

        logger.info("Urun adet sayisi artirilarak urun adedinin dogru oldugu kontrol edildi");
    }
    @Then("Urunu sepetten silerek sepetin bos oldugunu kontrol eder")
    public void urunu_sepetten_silerek_sepetin_bos_oldugunu_kontrol_eder() {

        beymen.sepettekiUrunSilButonu.click();

        assertTrue(beymen.sepetinizdeUrunBulunmamaktadir.isDisplayed());
        logger.info("Urun sepetten silinerek sepetin bos oldugu kontrol edildi");

    }


}
