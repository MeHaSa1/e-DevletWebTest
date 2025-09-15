Feature: Look at prices of a square meter of land
  Scenario Outline: User is searching for land prices
    Given user is in main page
    When user searches for "Arsa Metrekare" service
    And user goes to the "Arsa Metrekare Birim Değeri Sorgulama (Maltepe Belediyesi)" page recommended to them
    When user fills the form with values "<neighbourhood>" "<street>" "<year>"
    And user clicks find button
    Then user should see at least one land pricing
    Examples:
      | neighbourhood | street | year |
      | BÜYÜKBAKKALKÖY | ARICI Sokak | 2025 |
      | ESENKENT       | Tümü       | 2025 |
