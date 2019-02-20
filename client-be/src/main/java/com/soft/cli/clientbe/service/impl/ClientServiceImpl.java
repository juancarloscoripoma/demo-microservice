package com.soft.cli.clientbe.service.impl;

import com.soft.cli.clientbe.entity.Client;
import com.soft.cli.clientbe.repository.ClientRepository;
import com.soft.cli.clientbe.service.ClientService;
import com.soft.cli.clientbe.service.dto.ClientDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ClientServiceImpl implements ClientService {

    private final Logger log = LoggerFactory.getLogger(ClientServiceImpl.class);

    private final ClientRepository clientRepository;

    private Sort.Direction sort = Sort.Direction.ASC;

    public ClientServiceImpl(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Override
    public Client save(ClientDTO clientDTO) {
        Client client = new Client();
        client.setNit(clientDTO.getNit());
        client.setReasonsocial(clientDTO.getReasonsocial());

        clientRepository.save(client);
        return client;
    }

    @Override
    public Client updateClient(ClientDTO clientDTO) {
        log.debug("Request to Update By client {}", clientDTO);
        Client client = clientRepository.findOne(clientDTO.getId());
        client.setNit(clientDTO.getNit());
        client.setReasonsocial(clientDTO.getReasonsocial());

        clientRepository.save(client);
        return client;
    }

    @Override
    public Page<Client> findAll(String orderBy, String direction, int page, int size) {
        if(direction.equalsIgnoreCase("desc")){
            sort = Sort.Direction.DESC;
        }
        Pageable pageable = new PageRequest(page, size, sort, orderBy);
        return clientRepository.findAll(pageable);
    }

    @Override
    public Client findOne(Long id) {
        return clientRepository.findOne(id);
    }

    @Override
    public void delete(Long id) {
        clientRepository.delete(id);
    }
}
