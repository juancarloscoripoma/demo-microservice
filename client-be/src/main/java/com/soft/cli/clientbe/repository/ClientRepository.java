package com.soft.cli.clientbe.repository;

import com.soft.cli.clientbe.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client,Long> {
}
