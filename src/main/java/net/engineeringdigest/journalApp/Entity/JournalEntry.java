package net.engineeringdigest.journalApp.Entity;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.Date;

@Document(collection = "Journal_Entries")
public class JournalEntry {

    @Id
    private ObjectId id;
    private String names;
    private String content;
    private LocalDateTime date;



    public JournalEntry() {} // No-arg constructor

    public ObjectId getId() {
        return id;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }




    public String getNames() {

        return names;
    }

    public void setNames(String names) {

        this.names = names;
    }

    public String getContent() {

        return content;
    }

    public void setContent(String content) {

        this.content = content;
    }
}
