package net.engineeringdigest.journalApp.service;

import net.engineeringdigest.journalApp.Entity.JournalEntry;
import net.engineeringdigest.journalApp.Repository.JournalEntryRepo;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;


@Component
public class Journalentryservice {
    @Autowired
    private JournalEntryRepo Journalentryrepository;


    public void saveEntry(JournalEntry journalentry) {
        Journalentryrepository.save(journalentry);
    }

    public List<JournalEntry> Getall(){
        return Journalentryrepository.findAll();

    }

    public Optional<JournalEntry> findbyId(ObjectId id){
        return Journalentryrepository.findById(id);
    }

    public JournalEntry DeletebyID(ObjectId id){
        Journalentryrepository.deleteById(id);
        return null;
    }
}
