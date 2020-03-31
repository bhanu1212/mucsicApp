package com.lexnex.music.demo.controller;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.lexnex.music.demo.albums.AlbumRepository;
import com.lexnex.music.demo.albums.AlbumService;
import com.lexnex.music.demo.artists.ArtistService;
import com.lexnex.music.demo.artists.ArtistsRepository;

@RunWith(SpringJUnit4ClassRunner.class)
public class MusicAppControllerTest {

	@InjectMocks
	private MusicAppController musicAppController;

	@Mock
	private ArtistsRepository artistsRepository;

	@MockBean
	private AlbumRepository albumRespository;


	@MockBean
	private AlbumService albumService;


	private MockMvc mockMvc;

	@Before
	public void setUp() throws Exception {
		
		
	}

	
}
