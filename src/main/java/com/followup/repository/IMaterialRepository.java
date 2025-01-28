package com.followup.repository;

import com.followup.entity.Customer;
import com.followup.entity.Material;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IMaterialRepository extends JpaRepository<Material, Long> {
}
