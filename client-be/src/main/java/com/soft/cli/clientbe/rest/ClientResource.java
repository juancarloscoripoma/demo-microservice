package com.soft.cli.clientbe.rest;

import com.soft.cli.clientbe.entity.Client;
import com.soft.cli.clientbe.service.ClientService;
import com.soft.cli.clientbe.service.dto.ClientDTO;
import com.soft.cli.clientbe.web.rest.util.HeaderUtil;
import com.soft.cli.clientbe.web.rest.util.PaginationUtil;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class ClientResource {

    private final Logger log = LoggerFactory.getLogger(ClientResource.class);
    private static final String ENTITY_NAME = "client";

    private final ClientService clientService;

    public ClientResource(ClientService clientService) {
        this.clientService = clientService;
    }

    @PostMapping("/clients")
    public ResponseEntity<Client> createClient(@Valid @RequestBody ClientDTO clientDTO) throws URISyntaxException {
        log.debug("REST request to save Client : {}", clientDTO);
        if (clientDTO.getId() != 0) { //null
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new client cannot already have an ID")).body(null);
        }
        Client result = clientService.save(clientDTO);
        return ResponseEntity.created(new URI("/api/clients/" + result.getId()))
                .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
                .body(result);
    }

    //@GetMapping("/clients")
    @ApiOperation(value = "Getting all clients")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "orderBy", dataType = "string", paramType = "query", value = "Order by the column.",defaultValue = "id"),
            @ApiImplicitParam(name = "page", dataType = "integer", paramType = "query", value = "Results page you want to retrieve (0..N)",defaultValue = "0"),
            @ApiImplicitParam(name = "size", dataType = "integer", paramType = "query", value = "Number of records per page.",defaultValue = "50"),
            @ApiImplicitParam(name = "sort", allowMultiple = true, dataType = "string", paramType = "query", value = "Sorting criteria in the format: property(,asc|desc). "+"Default sort order is ascending. "+"Multiple sort criteria are supported.")
    })
    @RequestMapping(value = "/clients", params = { "orderBy", "sort", "page", "size" }, method = RequestMethod.GET)
    public ResponseEntity<List<Client>> getAllClients(@RequestParam("orderBy") String orderBy,
                                                      @RequestParam("sort") String sort,
                                                      @RequestParam("page") int page,
                                                      @RequestParam("size") int size) {
        log.debug("REST request to get a page of Clients");
        Page<Client> clients = clientService.findAll(orderBy, sort, page, size);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(clients, "/api/clients");
        return new ResponseEntity<>(clients.getContent(), headers, HttpStatus.OK);
    }

    @GetMapping("/client/{id}")
    public ResponseEntity<Client> getClient(@PathVariable Long id) {
        log.debug("REST request to get Client : {}", id);
        Client client = clientService.findOne(id);
        return Optional.ofNullable(client)
                .map(result -> new ResponseEntity<>(
                        result,
                        HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/client/{id}")
    public ResponseEntity<Void> deleteClient(@PathVariable Long id) {
        log.debug("REST request to delete Client : {}", id);
        clientService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

    @PutMapping("/clients")
    public ResponseEntity<Client> updateClient(@Valid @RequestBody ClientDTO clientDTO) throws URISyntaxException {
        log.debug("REST request to update Client : {}", clientDTO);
        if (clientDTO.getId() == 0) {//null
            return createClient(clientDTO);
        }
        Client result = clientService.updateClient(clientDTO);
        return ResponseEntity.ok()
                .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, clientDTO.getId().toString()))
                .body(result);
    }


}
