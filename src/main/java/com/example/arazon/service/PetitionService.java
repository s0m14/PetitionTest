package com.example.arazon.service;

import com.example.arazon.repository.PetitionRepository;
import com.example.arazon.user.Petition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PetitionService {
    @Autowired
    private PetitionRepository petitionRepository;

    public List<Petition> getAllPetitions(){
        return petitionRepository.findAll();
    }

    public Petition getPetitionById(int id){
        return petitionRepository.findById(id).orElseThrow(()->new RuntimeException("Petition is not found"));
    }

    public void addVote(Petition petition){
        petition.setVoteQuantity(petition.getVoteQuantity()+1);
        petitionRepository.save(petition);
    }

    public void Devote(Petition petition){
        petition.setVoteQuantity(petition.getVoteQuantity()-1);
        petitionRepository.save(petition);
    }
}
