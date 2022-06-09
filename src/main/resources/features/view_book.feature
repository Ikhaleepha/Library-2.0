Feature: User can view books in library


  Scenario: There are NO books in the library
    Given there are no books in the library
    When i view books in the library
    Then i see an empty library

  Scenario: There are books in the library
    Given there are books in the library
    When i view books in the library
    Then i see the list of the books in the library




