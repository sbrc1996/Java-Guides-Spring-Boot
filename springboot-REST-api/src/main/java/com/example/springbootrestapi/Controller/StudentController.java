package com.example.springbootrestapi.Controller;

import com.example.springbootrestapi.Bean.Student;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController                         //Internally contains Controller Class and Response Body.
@RequestMapping("students")         //Define a BASE URL for the API Endpoints.

//Always use ResponseEntity to send the response back to the client with the correct HTTP status code.

public class StudentController {

    //Returns a JSON response of a single student.
    @GetMapping("/student")
    public ResponseEntity<Student> getStudent(){
        Student student = new Student(1,"Subham","BRC");

//        return student;
//        return new ResponseEntity<>(student,HttpStatus.OK);

        return ResponseEntity.ok().
                header("custom-header","yo yo whats-up?").
                body(student);
    }

    @GetMapping  //Returns a list of JSON response of a multiple students.
    public List<Student> getStudents(){
        List<Student> lst = new ArrayList<>();
        lst.add(new Student(1,"skdjfgd","jdghfdf"));
        lst.add(new Student(1,"fcvfhjcg","';;[p["));
        lst.add(new Student(1,"riuereytr","hjpokipohj"));
        lst.add(new Student(1,"12343254243","fgpojohgiu"));
        return lst;
    }

    @GetMapping("/{id}/{first-name}/{last-name}")       //Takes some parameters from the URL and creates
                // a new resources and send it back as  JSON response
    public Student studentPathVariable(@PathVariable("id") int studentId,
                                       @PathVariable("first-name") String firstname,
                                       @PathVariable("last-name") String lastname){
        return new Student(studentId,firstname,lastname);
    }

    //Controller to get the RequestParameters from the URL:
    //URL: http://localhost:8080/query?id=3&firstName=Subham&lastName=sdhfgdsuyftuyds
    @GetMapping("/query")
    public Student studentRequestparameter(@RequestParam int id, @RequestParam String firstName,
                                            @RequestParam String lastName){
        return new Student(id,firstName,lastName);
    }

    //POST Request and RequestBODY to create a new resource.
    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public Student createStudent(@RequestBody Student student){
        System.out.println(student.getId());
        System.out.println(student.getFirstname());
        System.out.println(student.getLastname());

        return student;
    }

    //PUT Request and RequestBody to update an existing resource.
    //http://localhost:8080/student/{{id}}/update
    @PutMapping("{id}/update")
    @ResponseStatus(HttpStatus.OK)
    public Student updateStudent(@RequestBody Student student, @PathVariable("id") int studentId){
        System.out.println(student.getFirstname());
        System.out.println(student.getLastname());

        return student;
    }

    //DELETE Request and RequestBody to REMOVE an existing resource.
    //http://localhost:8080/student/{{id}}/update
    @DeleteMapping("{id}/delete")
    @ResponseStatus(HttpStatus.OK)
    public String deleteStudent(@PathVariable("id") int studentId){
        String message = "YAY id "+ studentId + " has been deleted..";
        return message;
    }
}
