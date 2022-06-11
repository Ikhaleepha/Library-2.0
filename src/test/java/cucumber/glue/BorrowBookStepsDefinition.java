package cucumber.glue;

import com.mam.io.Library20.entity.Borrow;
import com.mam.io.Library20.service.LibraryService;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class BorrowBookStepsDefinition {

    private final LibraryService libraryService;
    private String studentId;
    private String bookIsbn;
    private Borrow borrow;

    public BorrowBookStepsDefinition(LibraryService libraryService) {
        this.libraryService = libraryService;
        studentId = "01";
        bookIsbn = "01231";
        borrow = new Borrow(studentId, bookIsbn);
    }

    /*@Before
    public void initializeBookAndStudent(){
        studentId = "01";
        bookIsbn = "01231";
        borrow = new Borrow(studentId, bookIsbn);
    }*/

    @Given("there are more than one copy of a book in the library")
    public void thereAreMoreThanOneCopyAfABookInTheLibrary() {
        //Set the number of copies to 10 (More than one)
        libraryService.setNumberOfCopies(bookIsbn, 10);

    }

    @When("i choose a book to add to my borrowed list")
    public void iChooseABookToAddToMyBorrowedList() {

        System.out.println("Student ISBN " + borrow.getStudentId());
        System.out.println("Book ISBN " + borrow.getBookIsbn());

        System.out.println(studentId);
        System.out.println(bookIsbn);
        libraryService.borrowBook(new Borrow(studentId, bookIsbn));
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
        // Write code here that turns the phrase above into concrete actions
        libraryService.setNumberOfCopies(bookIsbn, 1);
    }

    @When("^i choose a book with one copy to add to my borrowed list$")
    public void iChooseABookWithOneCopyToAddToMyBorrowedList(){
        studentId = "02";
        bookIsbn = "01231";

        libraryService.setNumberOfCopies(bookIsbn,1);
        libraryService.borrowBook(new Borrow(studentId, bookIsbn));
    }

    @Then("the book is removed from the library")
    public void theBookIsRemovedFromTheLibrary() {
        assertFalse(libraryService.getNumberOfCopies(bookIsbn) > 0);
    }



}
