package com.example.arazon.controller;

import com.example.arazon.service.PetitionService;
import com.example.arazon.service.VoteService;
import com.example.arazon.user.Petition;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/home")
@RequiredArgsConstructor
public class MainController {

    @Autowired
    PetitionService petitionService;
    @Autowired
    VoteService voteService;


    @GetMapping("/hello")
    public String getHello(){
        return "Hello!";
    }

    @GetMapping("/petitions")
    public List<Petition> getPetitions(){
        return petitionService.getAllPetitions();
    }

    @GetMapping("/vote/{petition_id}")
    public String vote(@PathVariable int petition_id){
        voteService.vote(petition_id);
        return "You voted " + petition_id;
    }

    @GetMapping("/devote/{petition_id}")
    public String devote(@PathVariable int petition_id){
        voteService.devote(petition_id);
        return "You devoted " + petition_id;
    }

}