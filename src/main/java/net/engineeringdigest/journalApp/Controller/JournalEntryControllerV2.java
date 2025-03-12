package net.engineeringdigest.journalApp.Controller;

import net.engineeringdigest.journalApp.Entity.JournalEntry;
import net.engineeringdigest.journalApp.service.Journalentryservice;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/journal")
public class JournalEntryControllerV2 {
    @Autowired
    private Journalentryservice journalentryservice; // Fixed class name
    private ObjectId myID;


    @GetMapping
    public List<JournalEntry> getAll() {
        return journalentryservice.Getall();


    }
    @PostMapping
    public boolean createEntry(@RequestBody JournalEntry myEntry) {
        myEntry.setDate(LocalDateTime.now());
        journalentryservice.saveEntry(myEntry);

        return true;
    }

    @GetMapping("id/{myID}")  // More conventional
    public JournalEntry getById(@PathVariable ObjectId myId) {
         return journalentryservice.findbyId(myID).orElse(null);
    }

    @DeleteMapping("id/{myID}")  // More conventional
    public  JournalEntry void removeById(@PathVariable  ObjectId id) {
         journalentryservice.DeletebyID(myID);

    }

    @PutMapping
    public JournalEntry putbyID(@PathVariable ObjectId id, @RequestBody JournalEntry myEntry){
        return null;

    }
}
