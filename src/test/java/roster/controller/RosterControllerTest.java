package roster.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.IOException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import roster.bean.Player;
import roster.bean.Team;
import roster.service.RosterService;

@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@WebMvcTest(RosterController.class)
public class RosterControllerTest {

	@MockBean
	private RosterService rosterService;

	@Autowired
	private MockMvc mockMvc;

	Team mockTeam = new Team("Bears", "2", "Da");
	Player mockPlayer = new Player("Fridge", "Perry", "DT", "72", "2", "1");

	/**
	 * returns correct team
	 * 
	 * @throws Exception
	 */
	@Test
	public void validTeamReturnsRoster() throws Exception {
		when(rosterService.getRoster(Mockito.anyString())).thenReturn(mockTeam);

		this.mockMvc.perform(get("/team/{teamId}/roster", 5)).andDo(print()).andExpect(status().isOk())
				.andExpect(jsonPath("$.teamName").value("Bears"));

	}

	/**
	 * call successful but team not on system
	 * 
	 * @throws Exception
	 */
	@Test
	public void validTeamDoesNotReturnsRoster() throws Exception {
		this.mockMvc.perform(get("/team/{teamId}/roster", 4)).andDo(print()).andExpect(status().isOk())
				.andExpect(jsonPath("$.teamName").doesNotExist());
		;

	}

	/**
	 * returns valid player
	 * 
	 * @throws Exception
	 */
	@Test
	public void validPlayerReturns() throws Exception {
		when(rosterService.getPlayer(Mockito.anyString(), Mockito.anyString())).thenReturn(mockPlayer);

		this.mockMvc.perform(get("/team/{teamId}/player/{playerId}", 4, 5)).andDo(print()).andExpect(status().isOk())
				.andExpect(jsonPath("$.firstName").value("Fridge"));

	}

	/**
	 * test that a call is successfully but player is not in system
	 * 
	 * @throws Exception
	 */
	@Test
	public void validDoesNotReturn() throws Exception {

		this.mockMvc.perform(get("/team/{teamId}/player/{playerId}", 4, 5)).andDo(print()).andExpect(status().isOk())
				.andExpect(jsonPath("$.firstName").doesNotExist());

	}

	/**
	 * returns valid player
	 * 
	 * @throws Exception
	 */
	@Test
	public void updateExistingPlayer() throws Exception {
		when(rosterService.updatePlayer(Mockito.anyString(), mockPlayer)).thenReturn(mockPlayer);

		this.mockMvc.perform(put("/team/{teamId}/player", 4).contentType(MediaType.APPLICATION_JSON_UTF8)
				.content(convertObjectToJsonBytes(mockPlayer))).andDo(print()).andExpect(status().isOk());

	}


	/**
	 * helper method to attach json body to call
	 * 
	 * @param object
	 * @return
	 * @throws IOException
	 */
	public static byte[] convertObjectToJsonBytes(Object object) throws IOException {
		ObjectMapper mapper = new ObjectMapper();
		return mapper.writeValueAsBytes(object);
	}

}
