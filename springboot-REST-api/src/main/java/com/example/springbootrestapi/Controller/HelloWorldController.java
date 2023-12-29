package com.example.springbootrestapi.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController     //contains ResponseBody and Controller Annotation here. ResponseBody is responsible for the
                    // sending a JSON response back to the client..
public class HelloWorldController {
    //HTTP Get Request

    @GetMapping("/hello-world")
    public String helloWorld(){
        return "Hello World!!";
    }
}
