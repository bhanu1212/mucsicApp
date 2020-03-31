package com.lexnex.music.demo.albums;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import com.lexnex.music.demo.artists.Artist;
import com.lexnex.music.demo.artists.ArtistsRepository;

@Component
public class AlbumService {

	@Autowired
	private AlbumRepository albumRepository;

	@Autowired
	private ArtistsRepository artistsRepository;

	public List<Album> getAlbumByArtistId(long artistId, String key, String orderBy, String genre) {

		if (genre.isEmpty()) {
			if (orderBy.equals("asc")) {
				return albumRepository.findByArtistId(artistId, Sort.by(key).ascending());
			}
			if (orderBy.equals("desc")) {
				return albumRepository.findByArtistId(artistId, Sort.by(key).descending());
			}

		}
		return albumRepository.findByGenre(genre, Sort.by("title"));

	}

	public Album addAlbum(long artistId, Album album) {

		Artist artist = artistsRepository.getOne(artistId);

		return albumRepository
				.save(new Album(album.getId(), artist, album.getTitle(), album.getYearReleased(), album.getGenre()));

	}

	

}
