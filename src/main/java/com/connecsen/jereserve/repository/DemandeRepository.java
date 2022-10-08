package com.connecsen.jereserve.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.connecsen.jereserve.domaine.Demande;

public interface DemandeRepository extends JpaRepository<Demande,Long>{
    public List<Demande> findByStatus(boolean status);
    public List<Demande> findByStatusAndLockedAndMessageSendStateAndActivedJob(boolean status,boolean locked,boolean messageSendState,boolean activiedJob);

}
