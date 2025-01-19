package com.example.arazon.repository;

import com.example.arazon.user.Petition;
import com.example.arazon.user.User;
import com.example.arazon.user.Vote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VoteRepository extends JpaRepository<Vote, Integer> {
    Optional<Vote> findByUserAndPetition(User user, Petition petition);
    void deleteById(int id);
}