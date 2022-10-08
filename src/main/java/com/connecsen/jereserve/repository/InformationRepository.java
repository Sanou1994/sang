package com.connecsen.jereserve.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.connecsen.jereserve.domaine.Information;

public interface InformationRepository extends JpaRepository<Information,Long>{
    public List<Information> findBystatusOfInformation(boolean status);

}
