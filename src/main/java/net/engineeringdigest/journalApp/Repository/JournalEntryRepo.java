package net.engineeringdigest.journalApp.Repository;

import net.engineeringdigest.journalApp.Entity.JournalEntry;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface JournalEntryRepo extends MongoRepository<JournalEntry, ObjectId>{
    List<JournalEntry> findbyTitleContainingIgnoreCase(String title);
}
