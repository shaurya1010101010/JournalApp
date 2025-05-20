package net.engineeringdigest.journalApp.Controller;

// Importing necessary dependencies for the controller
import ExceptionHandlers.ObjectNotFoundException;
import net.engineeringdigest.journalApp.Entity.JournalEntry;
import net.engineeringdigest.journalApp.service.JournalEntryService;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.time.LocalDateTime;
import java.util.List;

// Marks this class as a REST Controller that handles web requests //test
@RestController
@RequestMapping("/journal") // Base URL for all endpoints in this controller
public class JournalEntryControllerV2 {

    // Injecting the service layer to interact with database operations
    @Autowired
    private JournalEntryService journalEntryService;

    // GET request to fetch all journal entries
    @GetMapping
    public ResponseEntity<List<JournalEntry>> getAll() {
        List<JournalEntry> entries = journalEntryService.getAll();
        return ResponseEntity.ok(entries);
    }

    // POST request to create a new journal entry
    @PostMapping
    public ResponseEntity<String> createEntry(@RequestBody JournalEntry myEntry) {
        try {
            if (Strings.isBlank(myEntry.getTitle())) {
                return ResponseEntity.badRequest().body("Title is empty");
            }
            // Automatically set the current date and time before saving
            myEntry.setDate(LocalDateTime.now());
            journalEntryService.saveEntry(myEntry);
            return ResponseEntity.status(HttpStatus.CREATED).body("Entry created successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to create entry: " + e.getMessage());
        }
    }

    // GET request to fetch a specific journal entry by its ID
    @GetMapping("/id/{myID}")
    public ResponseEntity<?> getById(@PathVariable("myID") String myID) throws ObjectNotFoundException {

        var entry = journalEntryService.findById(myID).orElse(null);

        return ResponseEntity.ok(entry);

    }

    // DELETE request to remove a journal entry by its ID
    @DeleteMapping("/id/{myID}")
    public ResponseEntity<String> removeById(@PathVariable("myID") String myID) {
        try {
            boolean isDeleted = journalEntryService.removeById(myID);
            if (isDeleted) {
                return ResponseEntity.ok("Entry deleted successfully.");
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Entry not found or could not be deleted.");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error deleting entry: " + e.getMessage());
        }
    }

    // PUT request to update a journal entry by its ID
    @PutMapping("/id/{id}")
    public ResponseEntity<?> putByID(@PathVariable("id") String id, @RequestBody JournalEntry newEntry) {
        var old = journalEntryService.findById(id).orElse(null);
        if (old != null) {
            if (newEntry.getTitle() != null && !newEntry.getTitle().isEmpty()) {
                old.setTitle(newEntry.getTitle());
            }
            if (newEntry.getContent() != null && !newEntry.getContent().isEmpty()) {
                old.setContent(newEntry.getContent());
            }
            journalEntryService.saveEntry(old);
            return ResponseEntity.ok(old);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Entry not found with ID: " + id);
    }

    // GET request to search journal entries by title
    @GetMapping("/search")
    public ResponseEntity<List<JournalEntry>> searchEntries(@RequestParam String title) {
        List<JournalEntry> entries = journalEntryService.searchByTitle(title);
        return ResponseEntity.ok(entries);
    }

    @Configuration
    public class WebConfig implements WebMvcConfigurer {
        @Value("${project.cors.allowed-origins}")
        private String[] allowedOrigins;

        @Value("${project.cors.allowed-methods}")
        private String[] allowedMethods;

        @Override
        public void configurePathMatch(PathMatchConfigurer configurer) {
            configurer.setUseTrailingSlashMatch(false);

        }

        @Override
        public void addCorsMappings(CorsRegistry registry) {
            registry.addMapping("/**").allowedOrigins(allowedOrigins).allowedMethods(allowedMethods);
        }
    }
}
