package com.lexnex.music.demo.albums;



import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.lexnex.music.demo.artists.Artist;



/**
 * 
 * @author BHANU PRATAP SINGH
 *
 */
//title, year of release, genres (list of tags)

@Entity
@Table(name = "album")
public class Album  {

	@Id
	@Column(name = "id")
	private Long id;
    
	
	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY,optional=false)
	@JoinColumn(name = "artist_id", nullable =false)
	private Artist artist;
	
	private String title;
	private Integer yearReleased;
	private String genre;
	
	public Album() {
		
	}

	public Album(Long id, Artist artist, String title, Integer yearReleased, String genre) {
		super();
		this.id = id;
		this.artist = artist;
		this.title = title;
		this.yearReleased = yearReleased;
		this.genre = genre;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Artist getArtist() {
		return artist;
	}

	public void setArtist(Artist artist) {
		this.artist = artist;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Integer getYearReleased() {
		return yearReleased;
	}

	public void setYearReleased(Integer yearReleased) {
		this.yearReleased = yearReleased;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}
	
	
}
