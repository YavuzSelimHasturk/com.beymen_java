Feature: Beymen Test
  @wip
  Scenario: Beymen Test

    Given kullanici Beymen sayfasina gider
    Then Cerezleri kabul eder ve cinsiyet secimini yapar
    And Beymen anasayfasinin acildigini kontrol eder
    And Arama kutucuguna "aranacak Urun" kelimesini girer ve aratir
    Then Sonuca gore sergilenen urunlerden rastgele bir urun secer
    And Secilen urunu sepete ekler
    And Urun sayfasindaki fiyat ile sepette yer alan urunun fiyatinin dogrulugunu karsilastirir
    And Adet sayisini arttirarak urun adedinin 2 oldugunu dogrular
    Then Urunu sepetten silerek sepetin bos oldugunu kontrol eder