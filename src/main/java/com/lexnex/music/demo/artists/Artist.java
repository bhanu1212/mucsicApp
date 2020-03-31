package com.lexnex.music.demo.artists;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.lexnex.music.demo.albums.Album;

/**
 * 
 * @author BHANU PRATAP SINGH
 *
 */
@Entity
@Table(name = "artist")
public class Artist {

	@Id
	//@JsonIgnore
	@Column(name = "id")
	private long id;

	private String artistFirstName;

	private String artistLastName;
	
	@JsonIgnore
	@OneToMany( mappedBy = "artist", orphanRemoval = true, fetch = FetchType.LAZY)
	private Set<Album> albums = new HashSet<Album>(0);

	public Artist() {

	}

	public Artist(long id, String artistFirstName, String artistLastName, Set<Album> albums) {
		super();
		this.id = id;
		this.artistFirstName = artistFirstName;
		this.artistLastName = artistLastName;
		this.albums = albums;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getArtistFirstName() {
		return artistFirstName;
	}

	public void setArtistFirstName(String artistFirstName) {
		this.artistFirstName = artistFirstName;
	}

	public String getArtistLastName() {
		return artistLastName;
	}

	public void setArtistLastName(String artistLastName) {
		this.artistLastName = artistLastName;
	}

	public Set<Album> getAlbums() {
		return albums;
	}

	public void setAlbums(Set<Album> albums) {
		this.albums = albums;
	}

	

}
