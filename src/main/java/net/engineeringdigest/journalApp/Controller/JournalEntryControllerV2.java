package net.engineeringdigest.journalApp.Controller;

// Importing necessary dependencies for the controller
import net.engineeringdigest.journalApp.Entity.JournalEntry;
import net.engineeringdigest.journalApp.service.Journalentryservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

// Marks this class as a REST Controller that handles web requests
@RestController
@RequestMapping("/journal") // Base URL for all endpoints in this controller
public class JournalEntryControllerV2 {

    // Injecting the service layer to interact with database operations
    @Autowired
    private Journalentryservice journalentryservice;

    // GET request to fetch all journal entries
    @GetMapping
    public ResponseEntity<List<JournalEntry>> getAll() {
        // Fetch all entries from the database
        List<JournalEntry> entries = journalentryservice.Getall();

        // Return 200 OK with the list of entries
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(entries);
    }

    // POST request to create a new journal entry
    @PostMapping
    public ResponseEntity<String> createEntry(@RequestBody JournalEntry myEntry) {
        try {
            // Automatically set the current date and time before saving
            myEntry.setDate(LocalDateTime.now());

            // Save the entry in the database
            journalentryservice.saveEntry(myEntry);

            // Return HTTP 201 (Created) if successful
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body("Entry created successfully.");
        } catch (Exception e) {
            // Return HTTP 500 (Internal Server Error) if something goes wrong
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to create entry: " + e.getMessage());
        }
    }

    // GET request to fetch a specific journal entry by its ID
    @GetMapping("id/{myID}")
    public ResponseEntity<?> getById(@PathVariable("myID") String myID) {
        try {
            // Find entry by ID
            var entry = journalentryservice.findbyId(myID).orElse(null);

            // If entry exists, return it
            if (entry != null) {
                return ResponseEntity
                        .status(HttpStatus.OK)
                        .body(entry);
            } else {
                // Entry not found
                return ResponseEntity
                        .status(HttpStatus.NOT_FOUND)
                        .body("Entry not found with ID: " + myID);
            }
        } catch (Exception e) {
            // Handle unexpected errors
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error fetching entry: " + e.getMessage());
        }
    }

    // DELETE request to remove a journal entry by its ID
    @DeleteMapping("id/{myID}")
    public ResponseEntity<String> removeById(@PathVariable("myID") String myID) {
        try {
            boolean isDeleted = journalentryservice.removeById(myID); // Assume this returns boolean

            if (isDeleted) {
                // Successfully deleted
                return ResponseEntity
                        .status(HttpStatus.OK)
                        .body("Entry deleted successfully.");
            } else {
                // Entry not found or deletion failed
                return ResponseEntity
                        .status(HttpStatus.NOT_FOUND)
                        .body("Entry not found or could not be deleted.");
            }
        } catch (Exception e) {
            // Handle errors gracefully
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error deleting entry: " + e.getMessage());
        }
    }

    // PUT request to update a journal entry by its ID
    @PutMapping("/id/{id}")
    public ResponseEntity<?> putByID(@PathVariable("id") String id, @RequestBody JournalEntry newEntry) {
        // Find the existing journal entry by ID
        var old = journalentryservice.findbyId(id).orElse(null);

        // If the entry exists, update the details
        if (old != null) {
            // Update the title if provided and not empty
            if (newEntry.getTitle() != null && !newEntry.getTitle().isEmpty()) {
                old.setTitle(newEntry.getTitle());
            }

            // Update the content if provided and not empty
            if (newEntry.getContent() != null && !newEntry.getContent().isEmpty()) {
                old.setContent(newEntry.getContent());
            }

            // Save the updated entry in the database
            journalentryservice.saveEntry(old);

            // Return 200 OK with the updated entry
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(old);
        }


        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body("Entry not found with ID: " + id);  // this returns response entity
    }
}
