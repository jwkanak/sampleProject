package roster.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import roster.bean.BaseBean;
import roster.bean.Player;
import roster.bean.Team;
import roster.dao.RosterDao;

/**
 * Class to handle the "Business" logic before accessing the DAO For this quick
 * snippet it works as essentially a barrier between the controller and the data
 * layer
 * 
 * @author Kanak
 *
 */
@Service(value = "RosterService")
public class RosterService {

	@Autowired
	RosterDao rosterDao;

	/**
	 * calls rosterDAO to get team Roster
	 * 
	 * @param teamId
	 * @return{Team}
	 */
	public Team getRoster(String teamId) {
		return rosterDao.getTeamRoster(teamId);
	}

	/**
	 * calls rosterDAo to get specific player
	 * 
	 * @param teamId
	 * @param playerId
	 * @return{Player}
	 */
	public Player getPlayer(String teamId, String playerId) {
		return rosterDao.getPlayer(playerId, playerId);
	}

	/**
	 * calls rosterDAO to UPDATE a current player in system
	 * 
	 * @param teamId
	 * @param updatePlayer
	 * @return {Player}
	 */
	public Player updatePlayer(String teamId, Player updatePlayer) {
		return rosterDao.updatePlayerInfo(teamId, updatePlayer);

	}

	/**
	 * calls rosterDAO to INSERT player onto roster
	 * 
	 * @param teamId
	 * @param updatePlayer
	 * @return {Player}
	 */
	public Player insertPlayer(String teamId, Player updatePlayer) {
		return rosterDao.insertPlayer(teamId, updatePlayer);

	}

	/**
	 * calls rosterDAO to DELETE player from roster
	 * 
	 * @param teamId
	 * @param playerId
	 * @return{BaseBean}
	 */
	public BaseBean deletePlayer(String teamId, String playerId) {
		return rosterDao.deletePlayer(teamId, playerId);
	}

}
