package com.kyanite.ft.web.rest;

import com.kyanite.ft.domain.DdBookPerson;
import com.kyanite.ft.service.DdBookPersonService;
import com.kyanite.ft.web.rest.errors.BadRequestAlertException;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link com.kyanite.ft.domain.DdBookPerson}.
 */
@RestController
@RequestMapping("/api")
public class DdBookPersonResource {

    private final Logger log = LoggerFactory.getLogger(DdBookPersonResource.class);

    private static final String ENTITY_NAME = "ddBookPerson";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final DdBookPersonService ddBookPersonService;

    public DdBookPersonResource(DdBookPersonService ddBookPersonService) {
        this.ddBookPersonService = ddBookPersonService;
    }

    /**
     * {@code POST  /dd-book-people} : Create a new ddBookPerson.
     *
     * @param ddBookPerson the ddBookPerson to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new ddBookPerson, or with status {@code 400 (Bad Request)} if the ddBookPerson has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/dd-book-people")
    public ResponseEntity<DdBookPerson> createDdBookPerson(@RequestBody DdBookPerson ddBookPerson) throws URISyntaxException {
        log.debug("REST request to save DdBookPerson : {}", ddBookPerson);
        if (ddBookPerson.getId() != null) {
            throw new BadRequestAlertException("A new ddBookPerson cannot already have an ID", ENTITY_NAME, "idexists");
        }
        DdBookPerson result = ddBookPersonService.save(ddBookPerson);
        return ResponseEntity.created(new URI("/api/dd-book-people/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /dd-book-people} : Updates an existing ddBookPerson.
     *
     * @param ddBookPerson the ddBookPerson to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated ddBookPerson,
     * or with status {@code 400 (Bad Request)} if the ddBookPerson is not valid,
     * or with status {@code 500 (Internal Server Error)} if the ddBookPerson couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/dd-book-people")
    public ResponseEntity<DdBookPerson> updateDdBookPerson(@RequestBody DdBookPerson ddBookPerson) throws URISyntaxException {
        log.debug("REST request to update DdBookPerson : {}", ddBookPerson);
        if (ddBookPerson.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        DdBookPerson result = ddBookPersonService.save(ddBookPerson);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, ddBookPerson.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /dd-book-people} : get all the ddBookPeople.
     *
     * @param pageable the pagination information.
     * @param eagerload flag to eager load entities from relationships (This is applicable for many-to-many).
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of ddBookPeople in body.
     */
    @GetMapping("/dd-book-people")
    public ResponseEntity<List<DdBookPerson>> getAllDdBookPeople(Pageable pageable, @RequestParam(required = false, defaultValue = "false") boolean eagerload) {
        log.debug("REST request to get a page of DdBookPeople");
        Page<DdBookPerson> page;
        if (eagerload) {
            page = ddBookPersonService.findAllWithEagerRelationships(pageable);
        } else {
            page = ddBookPersonService.findAll(pageable);
        }
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /dd-book-people/:id} : get the "id" ddBookPerson.
     *
     * @param id the id of the ddBookPerson to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the ddBookPerson, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/dd-book-people/{id}")
    public ResponseEntity<DdBookPerson> getDdBookPerson(@PathVariable Long id) {
        log.debug("REST request to get DdBookPerson : {}", id);
        Optional<DdBookPerson> ddBookPerson = ddBookPersonService.findOne(id);
        return ResponseUtil.wrapOrNotFound(ddBookPerson);
    }

    /**
     * {@code DELETE  /dd-book-people/:id} : delete the "id" ddBookPerson.
     *
     * @param id the id of the ddBookPerson to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/dd-book-people/{id}")
    public ResponseEntity<Void> deleteDdBookPerson(@PathVariable Long id) {
        log.debug("REST request to delete DdBookPerson : {}", id);
        ddBookPersonService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
