package com.connecsen.jereserve.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.connecsen.jereserve.domaine.Code;

public interface CodeRepository extends JpaRepository<Code,Long>{
public Code findByPhoneAndCodeAndStatus(long phone,long code,boolean status);
}
