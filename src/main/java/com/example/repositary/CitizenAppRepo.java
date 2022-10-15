package com.example.repositary;

import java.io.Serializable;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.entity.CitizenAppEntity;

public interface CitizenAppRepo extends JpaRepository<CitizenAppEntity, Serializable> {

}
