package com.lexnex.music.demo.albums;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlbumRepository extends JpaRepository<Album, Long>{

	List<Album> findByArtistId(long artistId, Sort sort);

	List<Album> findByGenre(String genre, Sort sort);

	

	
	
	

}
