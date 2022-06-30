Feature: User can return books to the library

  Scenario: I have two books, return one book
    Given i have 2 books in my borrowed list
    When i return one book to the library
    Then the book is removed from my borrowed list
    And the library reflects the updated stock of the book

  Scenario: I have two books, return both books
    Given i have 2 books in my borrowed list
    When I return both books to the library
    Then my borrowed list is empty
    And the library reflects the updated stock of the books

