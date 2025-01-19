package com.example.arazon.service;

import com.example.arazon.repository.VoteRepository;
import com.example.arazon.user.Vote;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Optional;

@Service
public class VoteService {
    @Autowired
    VoteRepository voteRepository;
    @Autowired
    UserService userService;
    @Autowired
    PetitionService petitionService;


    public void vote(int petition_id){
        if(voteRepository.findByUserAndPetition(userService.getCurrentUser(),petitionService.getPetitionById(petition_id)).isPresent()){
            throw new RuntimeException("You can't vote more than one time");
        }else{
            voteRepository.save(Vote.builder().
                    petition(petitionService.getPetitionById(petition_id)).
                    user(userService.getCurrentUser()).
                    dateVoted(new Timestamp(new Date().getTime())).
                    build());
            petitionService.addVote(petitionService.getPetitionById(petition_id));
        }
    }

    public void devote(int petition_id){
        if(voteRepository.findByUserAndPetition(userService.getCurrentUser(),petitionService.getPetitionById(petition_id)).isPresent()){
            Optional<Vote> vote = voteRepository.findByUserAndPetition(userService.getCurrentUser(),petitionService.getPetitionById(petition_id));
            voteRepository.deleteById(vote.get().getId());
            petitionService.Devote(petitionService.getPetitionById(petition_id));
        }else{
            throw new RuntimeException("You can't devote to a non-existed petition");
        }
    }
}
