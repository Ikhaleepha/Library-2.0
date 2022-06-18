Feature: User can borrow a copy of a book from the library

  Scenario: There are more than copies of a book in the library
    Given there are more than one copy of a book in the library
    When i choose a book to add to my borrowed list
    Then one copy of the book is added to my borrowed list
    And the library has at least one copy of the book left

  Scenario: There is one copy of a book in the library
    Given there is only one copy of a book in the library
    When i choose a book to add to my borrowed list
    #When  i choose a book with one copy to add to my borrowed list
    Then  one copy of the book is added to my borrowed list
    And the book is removed from the library
