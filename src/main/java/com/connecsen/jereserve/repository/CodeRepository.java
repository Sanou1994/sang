package com.connecsen.jereserve.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.connecsen.jereserve.domaine.Code;

public interface CodeRepository extends JpaRepository<Code,Long>{
public Code findByCode(long code);
public List<Code> findByCodeAndStatus(long code,boolean status);


}
