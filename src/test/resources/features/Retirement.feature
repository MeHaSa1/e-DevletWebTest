Feature: See your retirement plan
  Scenario: User wants to look at their retirement plan
    Given user is in main page
    When user clicks login button
    And user enters their credentials tc: "11111111111" password:"DenemePassword"
    When user searches for "Takasbank" service
    And user goes to the "Takasbank Bireysel Emeklilik Bilgilendirme İşlemleri (Takasbank İstanbul Takas ve Saklama Bankası A.Ş.)" page recommended to them
    Then user should be in retirement page