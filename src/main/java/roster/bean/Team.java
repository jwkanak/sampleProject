package roster.bean;

import java.util.HashMap;

/**
 * Basic info for Team. SerialVersion is needed for writing data to a txt file
 * @author Kanak
 *
 */
public class Team extends BaseBean {

	private static final long serialVersionUID = 1L;
	private String teamName;
	private String teamId;
	private String teamLocation;
	private HashMap<String, Player> roster;

	public Team() {

	}

	public Team(String teamName, String teamId, String teamLocation) {
		super();
		this.teamName = teamName;
		this.teamId = teamId;
		this.teamLocation = teamLocation;
	}

	/**
	 * return player
	 * 
	 * @param playerId
	 * @return {Player}
	 */
	public Player getPlayer(String playerId) {
		return roster.get(playerId);
	}

	/**
	 * insert player
	 * 
	 * @param newPlayer
	 */
	public void insertPlayer(Player newPlayer) {
		roster.put(newPlayer.getPlayerId(), newPlayer);
	}

	/**
	 * delete player from roster
	 * 
	 * @param playerId
	 * @return{boolean}
	 */
	public boolean deletePlayer(String playerId) {
		Player player = this.getPlayer(playerId);

		return roster.remove(player.getPlayerId(), player);
	}

	public String getTeamName() {
		return teamName;
	}

	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}

	public String getTeamId() {
		return teamId;
	}

	public void setTeamId(String teamId) {
		this.teamId = teamId;
	}

	public String getTeamLocation() {
		return teamLocation;
	}

	public void setTeamLocation(String teamLocation) {
		this.teamLocation = teamLocation;
	}

	public HashMap<String, Player> getRoster() {
		return roster;
	}

	public void setRoster(HashMap<String, Player> roster) {
		this.roster = roster;
	}

}
