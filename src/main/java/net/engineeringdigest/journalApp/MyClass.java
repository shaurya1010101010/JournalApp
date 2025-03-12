package net.engineeringdigest.journalApp;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyClass {


    @GetMapping("API")
    public String Hello(){
        return "Hello";
    }

    @PostMapping
    public void createEntry(){

    }


}
