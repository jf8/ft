package com.kyanite.ft.web.rest;

import com.kyanite.ft.domain.LiveSharing;
import com.kyanite.ft.service.LiveSharingService;
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
 * REST controller for managing {@link com.kyanite.ft.domain.LiveSharing}.
 */
@RestController
@RequestMapping("/api")
public class LiveSharingResource {

    private final Logger log = LoggerFactory.getLogger(LiveSharingResource.class);

    private static final String ENTITY_NAME = "liveSharing";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final LiveSharingService liveSharingService;

    public LiveSharingResource(LiveSharingService liveSharingService) {
        this.liveSharingService = liveSharingService;
    }

    /**
     * {@code POST  /live-sharings} : Create a new liveSharing.
     *
     * @param liveSharing the liveSharing to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new liveSharing, or with status {@code 400 (Bad Request)} if the liveSharing has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/live-sharings")
    public ResponseEntity<LiveSharing> createLiveSharing(@RequestBody LiveSharing liveSharing) throws URISyntaxException {
        log.debug("REST request to save LiveSharing : {}", liveSharing);
        if (liveSharing.getId() != null) {
            throw new BadRequestAlertException("A new liveSharing cannot already have an ID", ENTITY_NAME, "idexists");
        }
        LiveSharing result = liveSharingService.save(liveSharing);
        return ResponseEntity.created(new URI("/api/live-sharings/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /live-sharings} : Updates an existing liveSharing.
     *
     * @param liveSharing the liveSharing to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated liveSharing,
     * or with status {@code 400 (Bad Request)} if the liveSharing is not valid,
     * or with status {@code 500 (Internal Server Error)} if the liveSharing couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/live-sharings")
    public ResponseEntity<LiveSharing> updateLiveSharing(@RequestBody LiveSharing liveSharing) throws URISyntaxException {
        log.debug("REST request to update LiveSharing : {}", liveSharing);
        if (liveSharing.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        LiveSharing result = liveSharingService.save(liveSharing);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, liveSharing.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /live-sharings} : get all the liveSharings.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of liveSharings in body.
     */
    @GetMapping("/live-sharings")
    public ResponseEntity<List<LiveSharing>> getAllLiveSharings(Pageable pageable) {
        log.debug("REST request to get a page of LiveSharings");
        Page<LiveSharing> page = liveSharingService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /live-sharings/:id} : get the "id" liveSharing.
     *
     * @param id the id of the liveSharing to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the liveSharing, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/live-sharings/{id}")
    public ResponseEntity<LiveSharing> getLiveSharing(@PathVariable Long id) {
        log.debug("REST request to get LiveSharing : {}", id);
        Optional<LiveSharing> liveSharing = liveSharingService.findOne(id);
        return ResponseUtil.wrapOrNotFound(liveSharing);
    }

    /**
     * {@code DELETE  /live-sharings/:id} : delete the "id" liveSharing.
     *
     * @param id the id of the liveSharing to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/live-sharings/{id}")
    public ResponseEntity<Void> deleteLiveSharing(@PathVariable Long id) {
        log.debug("REST request to delete LiveSharing : {}", id);
        liveSharingService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
