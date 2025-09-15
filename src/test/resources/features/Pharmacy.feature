Feature: Find local pharmacy
  Scenario Outline: User is searching for a local pharmacy on duty
    Given user is in main page
    When user searches for "Nöbetçi Eczane" service
    And user goes to the "Nöbetçi Eczane" page recommended to them
    When user enters district "<district>"
    And user enters date "<date>"
    And user clicks find button
    Then user should see at least one pharmacy
    Examples:
      | district | date |
      | BALIKESİR | 15/09/2025 |
      | İZMİR | 15/09/2025 |