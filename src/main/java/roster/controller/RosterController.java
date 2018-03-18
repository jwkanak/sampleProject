package roster.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import roster.bean.Player;
import roster.bean.Team;
import roster.service.RosterService;

/**
 * main controller for service
 * @author Kanak
 *
 */
@RestController
public class RosterController {

	@Autowired
	RosterService rosterService;

	private static final String SUCCESS_RESULT = "success";
	private static final String FAILURE_RESULT = "failure";

	/**
	 * returns complete roster for team
	 * 
	 * @param teamId
	 * @return {Team}
	 */
	@RequestMapping(value = "/team/{teamId}/roster", method = RequestMethod.GET)
	public Team getTeamRoster(@PathVariable String teamId) {

		return rosterService.getRoster(teamId);
	}

	/**
	 * 
	 * @param teamId
	 * @param playerId
	 * @return{Player}
	 */
	@RequestMapping(value = "/team/{teamId}/player/{playerId}", method = RequestMethod.GET)
	public Player getPlayer(@PathVariable String teamId, @PathVariable String playerId) throws ClassNotFoundException {

		return rosterService.getPlayer(teamId, playerId);
	}

	/**
	 * Updates player if valid player in system
	 * @param teamId
	 * @param player
	 * @return {String result}
	 */
	@RequestMapping(value = "/team/{teamId}/player", method = RequestMethod.PUT)
	public String updatePlayer(@PathVariable String teamId, @RequestBody Player player) {

		int result = rosterService.updatePlayer(teamId, player);

		return result == 1 ? SUCCESS_RESULT : FAILURE_RESULT;
	}

	/**
	 * Adds new player to roster if does not already exist
	 * @param teamId
	 * @param newPlayer
	 * @return{String result}
	 */
	@RequestMapping(value = "/team/{teamId}/player", method = RequestMethod.POST)
	public String insertPlayer(@PathVariable String teamId, @RequestBody Player newPlayer) {

		int result = rosterService.insertPlayer(teamId, newPlayer);

		return result == 1 ? SUCCESS_RESULT : FAILURE_RESULT;
	}

	/**
	 * Deletes player from roster if he exists
	 * @param teamId
	 * @param playerId
	 * @return{String result}
	 */
	@RequestMapping(value = "/team/{teamId}/player/{playerId}", method = RequestMethod.DELETE)
	public String removePlayer(@PathVariable String teamId, @PathVariable String playerId) {

		int result = rosterService.deletePlayer(teamId, playerId);

		return result == 1 ? SUCCESS_RESULT : FAILURE_RESULT;
	}

}
