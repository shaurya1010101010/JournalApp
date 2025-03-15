package net.engineeringdigest.journalApp.Controller;

// Importing necessary dependencies for the controller
import net.engineeringdigest.journalApp.Entity.JournalEntry;
import net.engineeringdigest.journalApp.service.Journalentryservice;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
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
    public List<JournalEntry> getAll() {
        return journalentryservice.Getall();
    }

    // POST request to create a new journal entry
    @PostMapping
    public boolean createEntry(@RequestBody JournalEntry myEntry) {
        myEntry.setDate(LocalDateTime.now()); // Automatically set the current date and time
        journalentryservice.saveEntry(myEntry); // Save the entry in the database
        return true; // Return true to indicate success
    }

    // GET request to fetch a specific journal entry by its ID
    @GetMapping("id/{myID}")
    public JournalEntry getById(@PathVariable("myID") String myID) {
        return journalentryservice.findbyId(myID).orElse(null); // Return the entry if found, otherwise null
    }

    // DELETE request to remove a journal entry by its ID
    @DeleteMapping("id/{myID}")
    public boolean removeById(@PathVariable("myID") String myID) {
        return journalentryservice.removeById(myID); // Return true if deletion is successful
    }

    // PUT request to update a journal entry by its ID
    @PutMapping("/id/{id}")
    public JournalEntry putByID(@PathVariable("id") String id, @RequestBody JournalEntry newEntry) {
        // Find the existing journal entry by ID
        JournalEntry old = journalentryservice.findbyId(id).orElse(null);

        // If the entry exists, update the details
        if (old != null) {
            // Update the title if provided and not empty
            if (newEntry.getTitle() != null && !newEntry.getTitle().equals("")) {
                old.setTitle(newEntry.getTitle());
            }

            // Update the content if provided and not empty
            if (newEntry.getContent() != null && !newEntry.getContent().equals("")) {
                old.setContent(newEntry.getContent());
            }

            // Save the updated entry in the database
            journalentryservice.saveEntry(old);
        }

        return old; // Return the updated entry (or null if no entry was found)

    }
}