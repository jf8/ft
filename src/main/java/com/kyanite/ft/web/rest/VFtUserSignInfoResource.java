package com.kyanite.ft.web.rest;

import com.kyanite.ft.domain.VFtUserSignInfo;
import com.kyanite.ft.service.VFtUserSignInfoService;
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
 * REST controller for managing {@link com.kyanite.ft.domain.VFtUserSignInfo}.
 */
@RestController
@RequestMapping("/api")
public class VFtUserSignInfoResource {

    private final Logger log = LoggerFactory.getLogger(VFtUserSignInfoResource.class);

    private static final String ENTITY_NAME = "vFtUserSignInfo";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final VFtUserSignInfoService vFtUserSignInfoService;

    public VFtUserSignInfoResource(VFtUserSignInfoService vFtUserSignInfoService) {
        this.vFtUserSignInfoService = vFtUserSignInfoService;
    }

    /**
     * {@code POST  /v-ft-user-sign-infos} : Create a new vFtUserSignInfo.
     *
     * @param vFtUserSignInfo the vFtUserSignInfo to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new vFtUserSignInfo, or with status {@code 400 (Bad Request)} if the vFtUserSignInfo has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/v-ft-user-sign-infos")
    public ResponseEntity<VFtUserSignInfo> createVFtUserSignInfo(@RequestBody VFtUserSignInfo vFtUserSignInfo) throws URISyntaxException {
        log.debug("REST request to save VFtUserSignInfo : {}", vFtUserSignInfo);
        if (vFtUserSignInfo.getId() != null) {
            throw new BadRequestAlertException("A new vFtUserSignInfo cannot already have an ID", ENTITY_NAME, "idexists");
        }
        VFtUserSignInfo result = vFtUserSignInfoService.save(vFtUserSignInfo);
        return ResponseEntity.created(new URI("/api/v-ft-user-sign-infos/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /v-ft-user-sign-infos} : Updates an existing vFtUserSignInfo.
     *
     * @param vFtUserSignInfo the vFtUserSignInfo to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated vFtUserSignInfo,
     * or with status {@code 400 (Bad Request)} if the vFtUserSignInfo is not valid,
     * or with status {@code 500 (Internal Server Error)} if the vFtUserSignInfo couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/v-ft-user-sign-infos")
    public ResponseEntity<VFtUserSignInfo> updateVFtUserSignInfo(@RequestBody VFtUserSignInfo vFtUserSignInfo) throws URISyntaxException {
        log.debug("REST request to update VFtUserSignInfo : {}", vFtUserSignInfo);
        if (vFtUserSignInfo.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        VFtUserSignInfo result = vFtUserSignInfoService.save(vFtUserSignInfo);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, vFtUserSignInfo.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /v-ft-user-sign-infos} : get all the vFtUserSignInfos.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of vFtUserSignInfos in body.
     */
    @GetMapping("/v-ft-user-sign-infos")
    public ResponseEntity<List<VFtUserSignInfo>> getAllVFtUserSignInfos(Pageable pageable) {
        log.debug("REST request to get a page of VFtUserSignInfos");
        Page<VFtUserSignInfo> page = vFtUserSignInfoService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /v-ft-user-sign-infos/:id} : get the "id" vFtUserSignInfo.
     *
     * @param id the id of the vFtUserSignInfo to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the vFtUserSignInfo, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/v-ft-user-sign-infos/{id}")
    public ResponseEntity<VFtUserSignInfo> getVFtUserSignInfo(@PathVariable Long id) {
        log.debug("REST request to get VFtUserSignInfo : {}", id);
        Optional<VFtUserSignInfo> vFtUserSignInfo = vFtUserSignInfoService.findOne(id);
        return ResponseUtil.wrapOrNotFound(vFtUserSignInfo);
    }

    /**
     * {@code DELETE  /v-ft-user-sign-infos/:id} : delete the "id" vFtUserSignInfo.
     *
     * @param id the id of the vFtUserSignInfo to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/v-ft-user-sign-infos/{id}")
    public ResponseEntity<Void> deleteVFtUserSignInfo(@PathVariable Long id) {
        log.debug("REST request to delete VFtUserSignInfo : {}", id);
        vFtUserSignInfoService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
