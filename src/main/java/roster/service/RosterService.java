package roster.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

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
@Repository(value = "RosterService")
public class RosterService {

	@Autowired
	RosterDao rosterDao;

	/**
	 * calls rosterDAO to get team Roster
	 * 
	 * @param teamId
	 * @return{int}
	 */
	public Team getRoster(String teamId) {
		return rosterDao.getTeamRoster(teamId);
	}

	/**
	 * calls rosterDAo to get specific player
	 * 
	 * @param teamId
	 * @param playerId
	 * @return{int}
	 */
	public Player getPlayer(String teamId, String playerId) {
		return rosterDao.getPlayer(playerId, playerId);
	}

	/**
	 * calls rosterDAO to UPDATE a current player in system
	 * 
	 * @param teamId
	 * @param updatePlayer
	 * @return
	 */
	public int updatePlayer(String teamId, Player updatePlayer) {
		int result = rosterDao.updatePlayerInfo(teamId, updatePlayer);

		return result;
	}

	/**
	 * calls rosterDAO to INSERT player onto roster
	 * 
	 * @param teamId
	 * @param updatePlayer
	 * @return {int}
	 */
	public int insertPlayer(String teamId, Player updatePlayer) {
		int result = rosterDao.insertPlayer(teamId, updatePlayer);

		return result;
	}

	/**
	 * calls rosterDAO to DELETE player from roster
	 * 
	 * @param teamId
	 * @param playerId
	 * @return{int}
	 */
	public int deletePlayer(String teamId, String playerId) {
		int result = rosterDao.deletePlayer(teamId, playerId);

		return result;
	}

}
