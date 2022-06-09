package cucumber.glue;


import com.mam.io.Library20.entity.Book;
import com.mam.io.Library20.service.LibraryService;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ViewBooksStepsDefinition {

    List<Book> actualBooks = new ArrayList<>();

    @Autowired
    TestRestTemplate testRestTemplate;

    @Autowired
    LibraryService libraryService;

    String baseUrl = "http://localhost:8080/books";

    @Given("^there are no books in the library$")
    public void thereAreNoBooksInTheLibrary(){
        libraryService.emptyLibrary();

    }

    @When("^i view books in the library$")
    public void iViewBooksInTheLibrary(){
        actualBooks = testRestTemplate.getForObject(baseUrl, List.class);
    }

    @Then("^i see an empty library$")
    public void iSeeAnEmptyLibrary(){
        assertEquals(0, actualBooks.size());
    }

    @Given("^there are books in the library$")
    public void thereAreBooksInTheLibrary(){
        libraryService.populateLibrary();
    }

    @When("^i view all books in the library$")
    public void iViewAllBooksInTheLibrary(){
        actualBooks = testRestTemplate.getForObject(baseUrl, List.class);
    }

    @Then("^i see the list of the books in the library$")
    public void iSeetheListOfTheBooksInTheLibrary(){
        System.out.println("The size is " + actualBooks.size());
        //assertTrue(0 != actualBooks.size());
    }
}
