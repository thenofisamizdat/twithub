package com.twithub.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.twithub.domain.Search;

import com.twithub.repository.SearchRepository;
import com.twithub.repository.UserRepository;
import com.twithub.web.rest.util.HeaderUtil;
import com.twithub.web.rest.util.TwitHubResult;
import com.twithub.web.rest.util.TwitHubWrapper;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing Search.
 */
@RestController
@RequestMapping("/api")
public class SearchResource {

    private final Logger log = LoggerFactory.getLogger(SearchResource.class);

    private static final String ENTITY_NAME = "search";

    private final SearchRepository searchRepository;

    private final UserRepository userRepository;

    public SearchResource(UserRepository userRepository, SearchRepository searchRepository) {
        this.searchRepository = searchRepository;
        this.userRepository = userRepository;
    }

    /**
     * POST  /searches : Create a new search.
     *
     * @param search the search to create
     * @return the ResponseEntity with status 201 (Created) and with body the new search, or with status 400 (Bad Request) if the search has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/searches")
    @Timed
    public ResponseEntity<Search> createSearch(@RequestBody Search search) throws URISyntaxException {
        log.debug("REST request to save Search : {}", search);
        if (search.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new search cannot already have an ID")).body(null);
        }
        Search result = searchRepository.save(search);
        return ResponseEntity.created(new URI("/api/searches/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /searches : Updates an existing search.
     *
     * @param search the search to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated search,
     * or with status 400 (Bad Request) if the search is not valid,
     * or with status 500 (Internal Server Error) if the search couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/searches")
    @Timed
    public ResponseEntity<Search> updateSearch(@RequestBody Search search) throws URISyntaxException {
        log.debug("REST request to update Search : {}", search);
        if (search.getId() == null) {
            return createSearch(search);
        }
        Search result = searchRepository.save(search);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, search.getId().toString()))
            .body(result);
    }

    /**
     * GET  /searches : get github projects and related tweets for query.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of searches in body
     */
    @GetMapping("/searches")
    @Timed
    public List<TwitHubResult> getSearches(@RequestParam String query) throws IOException {
        log.debug("REST request to search github and twitter : {}", query);

       //  If user Logged in - Save search
        if (SecurityContextHolder.getContext().getAuthentication().getAuthorities().toString().contains("ROLE_USER")){
            Search search = new Search();
            String username = SecurityContextHolder.getContext().getAuthentication().getName();
            Long uid = userRepository.findOneWithAuthoritiesByLogin(username).get().getId();
            search.setUserId(uid);
            search.setQuery(query);
            search.setTimestamp(System.currentTimeMillis() / 1000L);

            searchRepository.save(search);
        }

        TwitHubWrapper twitHubWrapper = new TwitHubWrapper();
        List<TwitHubResult> result = twitHubWrapper.constructResult(query);

        return result;
    }
    /**
     * GET  /searches : get all the searches.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of searches in body
     */
    @GetMapping("/userSearches")
    @Timed
    public List<Search> getAllSearches() {
        log.debug("REST request to get all Searches");
        return searchRepository.findAll();
    }

    /**
     * GET  /searches/:id : get the "id" search.
     *
     * @param id the id of the search to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the search, or with status 404 (Not Found)
     */
    @GetMapping("/searches/{id}")
    @Timed
    public ResponseEntity<Search> getSearch(@PathVariable Long id) {
        log.debug("REST request to get Search : {}", id);
        Search search = searchRepository.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(search));
    }

    /**
     * DELETE  /searches/:id : delete the "id" search.
     *
     * @param id the id of the search to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/searches/{id}")
    @Timed
    public ResponseEntity<Void> deleteSearch(@PathVariable Long id) {
        log.debug("REST request to delete Search : {}", id);
        searchRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }


}
