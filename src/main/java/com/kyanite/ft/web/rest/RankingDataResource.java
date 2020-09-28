package com.kyanite.ft.web.rest;

import com.kyanite.ft.domain.RankingData;
import com.kyanite.ft.service.RankingDataService;
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
 * REST controller for managing {@link com.kyanite.ft.domain.RankingData}.
 */
@RestController
@RequestMapping("/api")
public class RankingDataResource {

    private final Logger log = LoggerFactory.getLogger(RankingDataResource.class);

    private static final String ENTITY_NAME = "rankingData";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final RankingDataService rankingDataService;

    public RankingDataResource(RankingDataService rankingDataService) {
        this.rankingDataService = rankingDataService;
    }

    /**
     * {@code POST  /ranking-data} : Create a new rankingData.
     *
     * @param rankingData the rankingData to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new rankingData, or with status {@code 400 (Bad Request)} if the rankingData has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/ranking-data")
    public ResponseEntity<RankingData> createRankingData(@RequestBody RankingData rankingData) throws URISyntaxException {
        log.debug("REST request to save RankingData : {}", rankingData);
        if (rankingData.getId() != null) {
            throw new BadRequestAlertException("A new rankingData cannot already have an ID", ENTITY_NAME, "idexists");
        }
        RankingData result = rankingDataService.save(rankingData);
        return ResponseEntity.created(new URI("/api/ranking-data/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /ranking-data} : Updates an existing rankingData.
     *
     * @param rankingData the rankingData to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated rankingData,
     * or with status {@code 400 (Bad Request)} if the rankingData is not valid,
     * or with status {@code 500 (Internal Server Error)} if the rankingData couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/ranking-data")
    public ResponseEntity<RankingData> updateRankingData(@RequestBody RankingData rankingData) throws URISyntaxException {
        log.debug("REST request to update RankingData : {}", rankingData);
        if (rankingData.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        RankingData result = rankingDataService.save(rankingData);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, rankingData.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /ranking-data} : get all the rankingData.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of rankingData in body.
     */
    @GetMapping("/ranking-data")
    public ResponseEntity<List<RankingData>> getAllRankingData(Pageable pageable) {
        log.debug("REST request to get a page of RankingData");
        Page<RankingData> page = rankingDataService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /ranking-data/:id} : get the "id" rankingData.
     *
     * @param id the id of the rankingData to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the rankingData, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/ranking-data/{id}")
    public ResponseEntity<RankingData> getRankingData(@PathVariable Long id) {
        log.debug("REST request to get RankingData : {}", id);
        Optional<RankingData> rankingData = rankingDataService.findOne(id);
        return ResponseUtil.wrapOrNotFound(rankingData);
    }

    /**
     * {@code DELETE  /ranking-data/:id} : delete the "id" rankingData.
     *
     * @param id the id of the rankingData to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/ranking-data/{id}")
    public ResponseEntity<Void> deleteRankingData(@PathVariable Long id) {
        log.debug("REST request to delete RankingData : {}", id);
        rankingDataService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
