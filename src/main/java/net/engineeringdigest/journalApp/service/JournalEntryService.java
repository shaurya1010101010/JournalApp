package net.engineeringdigest.journalApp.service;

// Imports necessary classes for MongoDB interactions, Entity handling, and Spring Boot annotations
import net.engineeringdigest.journalApp.Entity.JournalEntry;
import net.engineeringdigest.journalApp.Repository.JournalEntryRepo;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

// Marks this class as a Spring service (Bean), allowing dependency injection
@Service
public class JournalEntryService {

    // Injects the repository for data interaction with MongoDB
    @Autowired
    private JournalEntryRepo journalEntryRepository;

    // Saves a journal entry to the database
    public void saveEntry(JournalEntry journalEntry) {
        journalEntryRepository.save(journalEntry);
    }

    // Retrieves all journal entries from the database
    public List<JournalEntry> getAll() {
        return journalEntryRepository.findAll();
    }

    // Finds a journal entry by ID and returns an Optional object to handle null values safely
    public Optional<JournalEntry> findById(String id) {
        try {
            return journalEntryRepository.findById(new ObjectId(id));
        } catch (IllegalArgumentException e) {
            // Handles invalid ObjectId format errors to avoid runtime exceptions
            return Optional.empty();
        }
    }

    // Deletes a journal entry by ObjectId
    public void deleteById(ObjectId id) {
        journalEntryRepository.deleteById(id);
    }

    // Removes an entry by String ID, converting it to ObjectId
    public boolean removeById(String myID) {
        try {
            ObjectId objectId = new ObjectId(myID); // Ensures valid ID format
            journalEntryRepository.deleteById(objectId);
            return true; // Returns success if no exceptions occur
        } catch (IllegalArgumentException e) {
            return false; // Handles invalid ObjectId format gracefully
        }
    }

    public List<JournalEntry> searchByTitle(String title) {
        return journalEntryRepository.findByTitle(title);
    }
}
