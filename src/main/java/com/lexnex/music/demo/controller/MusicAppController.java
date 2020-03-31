package com.lexnex.music.demo.controller;

import java.net.URI;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.lexnex.music.demo.albums.AlbumNotFoundException;
import com.lexnex.music.demo.albums.Album;
import com.lexnex.music.demo.albums.AlbumRepository;
import com.lexnex.music.demo.albums.AlbumService;
import com.lexnex.music.demo.artists.Artist;
import com.lexnex.music.demo.artists.ArtistNotFoundException;
import com.lexnex.music.demo.artists.ArtistService;
import com.lexnex.music.demo.artists.ArtistsRepository;

/**
 * 
 * @author BHANU PRATAP SINGH
 *
 */
@RestController
public class MusicAppController {

	@Autowired
	private ArtistsRepository artistsRepository;

	@Autowired
	private AlbumRepository albumRespository;

	@Autowired
	private ArtistService artistService;

	@Autowired
	private AlbumService albumService;

	/**
	 * Fetch all artists
	 * 
	 * Sort on column artistFirstName and artistLastName order by ascending and
	 * descending Displays results per page default page size - 5 default page start
	 * - first page
	 * 
	 * @param key      - sort on firstName or lastName
	 * @param orderBy  - asc or desc
	 * @param pageNo
	 * @param pageSize
	 * @return list all artists
	 */
	@GetMapping(path = "/artists")
	public Page<Artist> getAllArtistsData(
			@RequestParam(value = "SortBy - artistFirstName or artistLastName", defaultValue = "artistFirstName") String key,
			@RequestParam(value = "orderBy - asc or desc", defaultValue = "asc") String orderBy,
			@RequestParam(defaultValue = "0") Integer pageNo, @RequestParam(defaultValue = "5") Integer pageSize,
			@RequestParam(defaultValue = "") String filter) {

		Pageable pagingAsc = PageRequest.of(pageNo, pageSize, Sort.by(key).ascending());
		Pageable pagingDesc = PageRequest.of(pageNo, pageSize, Sort.by(key).descending());

		if (filter.isEmpty()) {
			if (orderBy.equals("asc")) {
				return artistsRepository.findAll(pagingAsc);
			}
			if (orderBy.equals("desc")) {
				return artistsRepository.findAll(pagingDesc);
			}

		}
		return artistService.getByArtistLastName(filter, pageNo, pageSize);
	}

	/**
	 * Add new artist
	 * 
	 * @param artist
	 * @return uri of the newly created artist
	 */
	@PostMapping(path = "/artists")
	public ResponseEntity<Object> addArtist(@RequestBody Artist artist) {
		Artist SavedArtist = artistsRepository.save(artist);

		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{artistId}")
				.buildAndExpand(SavedArtist.getId()).toUri();

		return ResponseEntity.created(uri).build();
	}

	/**
	 * Update an existing artist
	 * 
	 * @param artist
	 * @param artistId
	 * @return
	 */
	@PutMapping(path = "/artists/{artistId}")
	public ResponseEntity<Object> updateArtist(@RequestBody Artist artist, @PathVariable long artistId) {

		if (!(artistsRepository.findById(artistId).isPresent())) {
			throw new ArtistNotFoundException("artistId: " + artistId);

		}
		artist.setId(artistId);
		artistsRepository.save(artist);
		return ResponseEntity.noContent().build();

	}

	/**
	 * GET /artists/{artistId}/albums` lists all albums by the given artist.
	 */
	@GetMapping(path = "/artists/{artistId}/albums")
	public List<Album> getAllAlbumsData(@PathVariable long artistId,
			@RequestParam(value = "SortBy - yearReleased or title", defaultValue = "yearReleased") String key,
			@RequestParam(value = "orderBy - asc or desc", defaultValue = "asc") String orderBy,
			@RequestParam(defaultValue = "") String genre) {

		return albumService.getAlbumByArtistId(artistId, key, orderBy, genre);

	}

	/**
	 * `POST /artists/{artistId}/albums` to add a new album to an existing artist
	 */
	@PostMapping(path = "/artists/{artistId}/albums")
	public Album addAlbumToArtist(@PathVariable long artistId, @RequestBody Album album) {

		return albumService.addAlbum(artistId, album);
	}
	
	/**
	 * `PUT /artists/{artistId}/albums/{albumId}` to update an existing album
	 */
	@PutMapping(path = "/artists/{artistId}/albums/{albumId}")
	public ResponseEntity<Object> updateAlbum(@PathVariable long artistId,@PathVariable long albumId, @RequestBody Album album) {

		
		
		if (!(albumRespository.findById(albumId).isPresent()) || !(artistsRepository.findById(artistId).isPresent())) {
			throw new AlbumNotFoundException("albumId: " + albumId);

		}
		Album existingAlbum = albumRespository.getOne(albumId);
		Artist existingArtist = artistsRepository.getOne(artistId);
		album.setId(albumId);
		album.setArtist(existingArtist);
		albumRespository.save(album);
		return ResponseEntity.noContent().build();
		
		
		

	}
	
}
