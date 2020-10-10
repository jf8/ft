package com.kyanite.ft.web.rest;

import com.kyanite.ft.domain.DdBookDept;
import com.kyanite.ft.service.DdBookDeptService;
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
 * REST controller for managing {@link com.kyanite.ft.domain.DdBookDept}.
 */
@RestController
@RequestMapping("/api")
public class DdBookDeptResource {

    private final Logger log = LoggerFactory.getLogger(DdBookDeptResource.class);

    private static final String ENTITY_NAME = "ddBookDept";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final DdBookDeptService ddBookDeptService;

    public DdBookDeptResource(DdBookDeptService ddBookDeptService) {
        this.ddBookDeptService = ddBookDeptService;
    }

    /**
     * {@code POST  /dd-book-depts} : Create a new ddBookDept.
     *
     * @param ddBookDept the ddBookDept to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new ddBookDept, or with status {@code 400 (Bad Request)} if the ddBookDept has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/dd-book-depts")
    public ResponseEntity<DdBookDept> createDdBookDept(@RequestBody DdBookDept ddBookDept) throws URISyntaxException {
        log.debug("REST request to save DdBookDept : {}", ddBookDept);
        if (ddBookDept.getId() != null) {
            throw new BadRequestAlertException("A new ddBookDept cannot already have an ID", ENTITY_NAME, "idexists");
        }
        DdBookDept result = ddBookDeptService.save(ddBookDept);
        return ResponseEntity.created(new URI("/api/dd-book-depts/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /dd-book-depts} : Updates an existing ddBookDept.
     *
     * @param ddBookDept the ddBookDept to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated ddBookDept,
     * or with status {@code 400 (Bad Request)} if the ddBookDept is not valid,
     * or with status {@code 500 (Internal Server Error)} if the ddBookDept couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/dd-book-depts")
    public ResponseEntity<DdBookDept> updateDdBookDept(@RequestBody DdBookDept ddBookDept) throws URISyntaxException {
        log.debug("REST request to update DdBookDept : {}", ddBookDept);
        if (ddBookDept.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        DdBookDept result = ddBookDeptService.save(ddBookDept);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, ddBookDept.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /dd-book-depts} : get all the ddBookDepts.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of ddBookDepts in body.
     */
    @GetMapping("/dd-book-depts")
    public ResponseEntity<List<DdBookDept>> getAllDdBookDepts(Pageable pageable) {
        log.debug("REST request to get a page of DdBookDepts");
        Page<DdBookDept> page = ddBookDeptService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /dd-book-depts/:id} : get the "id" ddBookDept.
     *
     * @param id the id of the ddBookDept to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the ddBookDept, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/dd-book-depts/{id}")
    public ResponseEntity<DdBookDept> getDdBookDept(@PathVariable Long id) {
        log.debug("REST request to get DdBookDept : {}", id);
        Optional<DdBookDept> ddBookDept = ddBookDeptService.findOne(id);
        return ResponseUtil.wrapOrNotFound(ddBookDept);
    }

    /**
     * {@code DELETE  /dd-book-depts/:id} : delete the "id" ddBookDept.
     *
     * @param id the id of the ddBookDept to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/dd-book-depts/{id}")
    public ResponseEntity<Void> deleteDdBookDept(@PathVariable Long id) {
        log.debug("REST request to delete DdBookDept : {}", id);
        ddBookDeptService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
