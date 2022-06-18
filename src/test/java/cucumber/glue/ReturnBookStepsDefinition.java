package cucumber.glue;

import com.mam.io.Library20.entity.Borrow;
import com.mam.io.Library20.entity.ReturnBook;
import com.mam.io.Library20.entity.Student;
import com.mam.io.Library20.service.LibraryService;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;

public class ReturnBookStepsDefinition {

    @Autowired
    TestRestTemplate testRestTemplate;
    @Autowired
    LibraryService libraryService;


    private String borrowUrl = "http://localhost:8080/borrow";
    private String returnUrl = "http://localhost:8080/returnBook";
    private String getStudentUrl = "http://localhost:8080/students/01";

    private int numberOfBook1;
    private int numberOfBook2;

    @Before
    public void clearValues(){
        libraryService.reInitializeLibrary();
    }

    @Given("i have {int} books in my borrowed list")
    public void i_have_books_in_my_borrowed_list(Integer numberOfBooks) {

        String studentId = "01";
        String book1Isbn = "01234";
        String book2Isbn = "01235";

        numberOfBook1 = libraryService.getNumberOfCopies(book1Isbn);
        numberOfBook2 = libraryService.getNumberOfCopies(book2Isbn);

        Borrow borrowBook1 = new Borrow(studentId, book1Isbn);
        Borrow borrowBook2 = new Borrow(studentId, book2Isbn);

        String result1 = testRestTemplate.postForObject(borrowUrl ,borrowBook1, String.class);
        String result2 = testRestTemplate.postForObject(borrowUrl ,borrowBook2, String.class);

        ResponseEntity<Student> studentResponse = testRestTemplate.getForEntity(getStudentUrl, Student.class);

        assertEquals(numberOfBooks, studentResponse
                                .getBody()
                                .getBorrowedBooks()
                                .size()
        );
    }
    @When("i return one book to the library")
    public void i_return_one_book_to_the_library() {
        //return Book 01234
        String studentId = "01";
        String bookToReturn = "01234";

        ReturnBook returnBook = new ReturnBook(studentId, bookToReturn);

        ResponseEntity<String> result = testRestTemplate.postForEntity(returnUrl, returnBook, String.class);

    }
    @Then("the book is removed from my borrowed list")
    public void the_book_is_removed_from_my_borrowed_list() {
        String studentId = "01";
        String bookReturned = "01234";

        //Assert that the student no longer has the book in his borrowed list
        assertFalse(
                libraryService.getStudent(studentId)
                               .get()
                               .hasBorrowedBook(bookReturned));
    }
    @And("the library reflects the updated stock of the book")
    public void the_library_reflects_the_updated_stock_of_the_book() {
        String bookReturned = "01234";
        //Verify that the number of Copies is set to its original value
        assertEquals(numberOfBook1, libraryService.getNumberOfCopies(bookReturned));
    }


    @When("I return both books to the library")
    public void i_return_both_books_to_the_library() {
        String studentId = "01";
        String book1Isbn = "01234";
        String book2Isbn = "01235";

        ReturnBook returnBook1 = new ReturnBook(studentId, book1Isbn);
        ReturnBook returnBook2 = new ReturnBook(studentId, book2Isbn);

        ResponseEntity<String> result1 = testRestTemplate.postForEntity(returnUrl, returnBook1, String.class);
        ResponseEntity<String> result2 = testRestTemplate.postForEntity(returnUrl, returnBook2, String.class);
    }
    @Then("my borrowed list is empty")
    public void my_borrowed_list_is_empty() {
        String studentId = "01";
        assertEquals(0, libraryService
                .getStudent(studentId)
                .get()
                .getBorrowedBooks()
                .size());
    }
    @Then("the library reflects the updated stock of the books")
    public void the_library_reflects_the_updated_stock_of_the_books() {
        String book1Isbn = "01234";
        String book2Isbn = "01235";
        assertAll(
                ()-> assertEquals(numberOfBook1, libraryService.getNumberOfCopies(book1Isbn)),
                ()-> assertEquals(numberOfBook2, libraryService.getNumberOfCopies(book2Isbn))
        );
    }

}
