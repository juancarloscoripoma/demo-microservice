package com.soft.cli.clientbe.service;

import com.soft.cli.clientbe.entity.Client;
import com.soft.cli.clientbe.service.dto.ClientDTO;
import org.springframework.data.domain.Page;

public interface ClientService {

    Client save(ClientDTO clientDTO);

    Client updateClient(ClientDTO clientDTO);

    Page<Client> findAll(String orderBy, String sort, int page, int size);

    Client findOne(Long id);

    void delete(Long id);
}
