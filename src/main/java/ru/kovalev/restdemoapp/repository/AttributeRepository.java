package ru.kovalev.restdemoapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.kovalev.restdemoapp.entity.Attribute;

import java.util.Optional;

@Repository
public interface AttributeRepository extends JpaRepository<Attribute, Long> {

  @Query("select a from Attribute a  where a.name=:name")
  Optional<Attribute> findByName(String name);



}
