package net.engineeringdigest.journalApp.Entity;

import org.bson.types.ObjectId;  // Import for MongoDB ObjectId
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.LocalDateTime;

// Maps this class to the MongoDB collection "Journal_entries"
@Document(collection = "Journal_entries")
public class JournalEntry {

    // MongoDB's primary key field
    @Id
    private ObjectId id;

    // Entry fields for the journal
    private String names;   // Author's name
    private String content; // Content of the journal entry
    private LocalDateTime date; // Date when the entry was created or modified

    // No-argument constructor (required for MongoDB to create instances)
    public JournalEntry() {}

    // Getter for ObjectId (primary key)
    public ObjectId getId() {
        return id;
    }

    // Setter for ObjectId
    public void setId(ObjectId id) {
        this.id = id;
    }

    // Getter for date
    public LocalDateTime getDate() {
        return date;
    }

    // Setter for date
    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    // Title of the journal entry
    public String title;

    // Getter for title
    public String getTitle() {
        return title;
    }

    // Setter for title
    public void setTitle(String title) {
        this.title = title;
    }

    // Getter for names (author)
    public String getNames() {
        return names;
    }

    // Setter for names (author)
    public void setNames(String names) {
        this.names = names;
    }

    // Getter for content
    public String getContent() {
        return content;
    }

    // Setter for content
    public void setContent(String content) {
        this.content = content;

        // this is entry class
    }
}