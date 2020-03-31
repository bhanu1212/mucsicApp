package com.lexnex.music.demo.artists;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * 
 * @author BHANU PRATAP SINGH
 *
 */

@Repository
public interface ArtistsRepository extends JpaRepository<Artist, Long> {

	Page<Artist> findByArtistLastName(String artistLastName, Pageable page);

	

}
