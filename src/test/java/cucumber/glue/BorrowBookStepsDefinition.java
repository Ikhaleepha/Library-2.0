package cucumber.glue;

import com.mam.io.Library20.entity.Borrow;
import com.mam.io.Library20.service.LibraryService;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class BorrowBookStepsDefinition {

    @Autowired
    TestRestTemplate testRestTemplate;
    private final LibraryService libraryService;
    private String studentId;
    private String bookIsbn;
    private Borrow borrow;

    private String borrowUrl = "http://localhost:8080/borrow";

    public BorrowBookStepsDefinition(LibraryService libraryService) {
        this.libraryService = libraryService;
        studentId = "01";
        bookIsbn = "01231";
        borrow = new Borrow(studentId, bookIsbn);
    }

    @Before
    public void clearValues(){
        libraryService.reInitializeLibrary();
    }

    @Given("there are more than one copy of a book in the library")
    public void thereAreMoreThanOneCopyAfABookInTheLibrary() {
        //Set the number of copies to 10 (More than one)
        libraryService.setNumberOfCopies(bookIsbn, 10);

    }

    @When("i choose a book to add to my borrowed list")
    public void iChooseABookToAddToMyBorrowedList() {
        //libraryService.borrowBook(new Borrow(studentId, bookIsbn));
        String result1 = testRestTemplate.postForObject(borrowUrl ,borrow, String.class);
    }

    @Then("one copy of the book is added to my borrowed list")
    public void oneCopyOfTheBookIsAddedToMyBorrowedList() {
        assertTrue(libraryService.hasBorrowedBook(borrow));
    }

    @And("the library has at least one copy of the book left")
    public void theLibraryHasAtLeastOneCopyOfTheBookLeft() {
        assertTrue(libraryService.getNumberOfCopies(bookIsbn) > 0);
    }

    @Given("there is only one copy of a book in the library")
    public void thereIsOnlyOneCopyOfABookInTheLibrary() {
        libraryService.setNumberOfCopies(bookIsbn, 1);
    }


    @Then("the book is removed from the library")
    public void theBookIsRemovedFromTheLibrary() {
        assertFalse(libraryService.getNumberOfCopies(bookIsbn) > 0);
    }



}
