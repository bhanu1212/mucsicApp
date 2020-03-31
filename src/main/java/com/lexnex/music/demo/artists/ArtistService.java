package com.lexnex.music.demo.artists;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

@Component
public class ArtistService {
	
	@Autowired
	private ArtistsRepository artistsRepository;

	public Page<Artist> getByArtistLastName(String filter, Integer pageNo, Integer pageSize) {
        
        Pageable page = PageRequest.of(pageNo, pageSize,Sort.by("artistFirstName").ascending());
        return artistsRepository.findByArtistLastName(filter, page);
    }

	

    

}
