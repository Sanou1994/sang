package com.connecsen.jereserve.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.connecsen.jereserve.domaine.Partenaire;

public interface PartenaireRepository extends JpaRepository<Partenaire,Long>{
public Partenaire findByPhone(long phone);
public List<Partenaire> findByStatus(boolean status);
public List<Partenaire> findByStatusAndLocked(boolean status,boolean locked);


}
