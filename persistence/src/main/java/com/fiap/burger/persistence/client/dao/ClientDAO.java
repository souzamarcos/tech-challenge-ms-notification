package com.fiap.burger.persistence.client.dao;

import com.fiap.burger.persistence.client.model.ClientJPA;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientDAO extends JpaRepository<ClientJPA, Long>  {
}
