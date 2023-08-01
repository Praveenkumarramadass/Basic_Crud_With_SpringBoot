package com.example.project1.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.project1.model.Tamil;
import com.example.project1.repository.TamilRepository;

@RestController
@RequestMapping("/api")

public class TamilController {
    
    @Autowired
    TamilRepository tamilRepository;

    @GetMapping("/show_all")
    public List<Tamil> getalltamilans(){
        return(List<Tamil>) tamilRepository.findAll();
    }


    @PostMapping("/insert")
    public ResponseEntity<Tamil> createTamil(@RequestBody Tamil tamil){
        Tamil tamilrep = tamilRepository.save(new Tamil(tamil.getFirstname(),tamil.getLastname(),tamil.getPassword()));
        return new ResponseEntity<Tamil>(tamilrep,HttpStatus.OK);
    }

    @DeleteMapping("/delete_all")
    public ResponseEntity<HttpStatus> deleteAllTamilans(){
        tamilRepository.deleteAll();
        return new ResponseEntity<> (HttpStatus.NOT_FOUND);
    } 


    @PutMapping("/insert/{id}")
    public ResponseEntity<Tamil> updateTutorial(@PathVariable("id") Long id,@RequestBody Tamil tamil){
        Optional <Tamil> tamilrepos = tamilRepository.findById(id);
        if(tamilrepos.isPresent()){
            Tamil tr = tamilrepos.get();
            tr.setFirstname(tamil.getFirstname());
            tr.setLastname(tamil.getLastname());
            tr.setPassword(tamil.getPassword());

            return new ResponseEntity<Tamil>(tamilRepository.save(tr),HttpStatus.OK);
        }
        else{
            return new ResponseEntity<> (HttpStatus.NOT_FOUND);
        }
    }

}
