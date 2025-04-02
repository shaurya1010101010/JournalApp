package net.engineeringdigest.journalApp.Entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;  // Import for MongoDB ObjectId
import org.springframework.boot.context.properties.bind.DefaultValue;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.LocalDateTime;

// Maps this class to the MongoDB collection "Journal_entries"
@Document(collection = "Journal_entries")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class JournalEntry {

    // MongoDB's primary key field



    // Entry fields for the journal
    private String names;   // Author's name
    private String content;
    // Content of the journal entry
    private LocalDateTime date ; // Date when the entry was created or modified
    private String title;
}