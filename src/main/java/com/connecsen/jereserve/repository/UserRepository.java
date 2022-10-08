package com.connecsen.jereserve.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.connecsen.jereserve.domaine.Utilisateur;

public interface UserRepository extends JpaRepository<Utilisateur,Long>{
    public Utilisateur findByPhone(long  phone);
    public Utilisateur findByUsername(String username);
    public Utilisateur findByEmailAndPhone(String email,long phone);
    public Utilisateur findByUsernameAndEmail(String username,String email);
    public Utilisateur findByUsernameAndPassword(String username,String password);
    public List<Utilisateur> findByStatus(boolean status);
    public List<Utilisateur> findByStatusAndLocked(boolean status,boolean locked);
}
 