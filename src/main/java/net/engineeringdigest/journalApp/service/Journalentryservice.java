package net.engineeringdigest.journalApp.service;

// Imports necessary classes for MongoDB interactions, Entity handling, and Spring Boot annotations
import net.engineeringdigest.journalApp.Entity.JournalEntry;
import net.engineeringdigest.journalApp.Repository.JournalEntryRepo;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

// Marks this class as a Spring component (Bean), allowing dependency injection
@Service
public class Journalentryservice {

    // Injects the repository for data interaction with MongoDB
    @Autowired
    private JournalEntryRepo Journalentryrepository;

    // Saves a journal entry to the database
    public void saveEntry(JournalEntry journalentry) {
        Journalentryrepository.save(journalentry);
    }

    // Retrieves all journal entries from the database
    public List<JournalEntry> Getall() {
        return Journalentryrepository.findAll();
    }

    // Finds a journal entry by ID and returns an Optional object to handle null values safely
    public Optional<JournalEntry> findbyId(String id) {
        try {
            return Journalentryrepository.findById(new ObjectId(id));
        } catch (IllegalArgumentException e) {
            // Handles invalid ObjectId format errors to avoid runtime exceptions
            return Optional.empty();
        }
    }

    // Deletes a journal entry by ObjectId (returns null since deletion doesn't require return data)
    public JournalEntry DeletebyID(ObjectId id) {
        Journalentryrepository.deleteById(id);
        return null; // Can be improved by returning a status or confirmation message
    }

    // Dummy method for removal — needs proper logic for meaningful functionality
    public boolean removeById(String myID) {
        try {
            ObjectId objectId = new ObjectId(myID);  // Ensures valid ID format
            Journalentryrepository.deleteById(objectId);
            return true; // Returns success if no exceptions occur
        } catch (IllegalArgumentException e) {
            return false; // Handles invalid ObjectId format gracefully
        }
    }
}