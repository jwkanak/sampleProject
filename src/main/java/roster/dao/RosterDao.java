package roster.dao;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

import roster.bean.BaseBean;
import roster.bean.Player;
import roster.bean.Team;

/**
 * Class designed to handle interaction with the data layer
 * @author Kanak
 *TODO:  Add logging and errors methods
 */
@Repository(value = "RosterDao")
public class RosterDao {

	/**
	 * Map of teams
	 */
	HashMap<String, Team> teams;
	
	private static final String SUCCESS_RESULT = "success";
	private static final String FAILURE_RESULT = "failure";

	/**
	 * returns team
	 * @param teamId
	 * @return{Team}
	 */
	public Team getTeamRoster(String teamId) {
		Team team = getTeamMap().get(teamId);
		team.setStatusMessage(SUCCESS_RESULT);
		return team;
	}

	/**
	 * 
	 * @param teamId
	 * @param playerId
	 * @return{Player}
	 */
	public Player getPlayer(String teamId, String playerId) {
		Player player = this.getTeamMap().get(teamId).getPlayer(playerId);
		player.setStatusMessage(SUCCESS_RESULT);
		return player;
	}

	/**
	 * update current player on roster
	 * @param teamId
	 * @param updatePlayer
	 * @return{Player}
	 */
	public Player updatePlayerInfo(String teamId, Player updatePlayer) {
		String result = FAILURE_RESULT;
		Team team = this.getTeamMap().get(teamId);
		Player originalPlayer = team.getPlayer(updatePlayer.getPlayerId());

		if (originalPlayer != null) {
			team.insertPlayer(updatePlayer);
			updateFile(team);
			result = SUCCESS_RESULT;
		}
		
		updatePlayer.setStatusMessage(result);
		return updatePlayer;
	}
	
	/**
	 * Only insert player if player does not already exist within roster.
	 * @param teamId
	 * @param newPlayer
	 * @return{Player}
	 */
	public Player insertPlayer(String teamId, Player newPlayer) {
		String result = FAILURE_RESULT;
		Team team = this.getTeamMap().get(teamId);
		boolean playerExists = isPlayerOnRoster(team, newPlayer.getPlayerId());
		
		if(!playerExists) {
			team.insertPlayer(newPlayer);
			updateFile(team);
			result = SUCCESS_RESULT;
		}
		newPlayer.setStatusMessage(result);
		return newPlayer;
	}
	
	/**
	 * if player exists, delete
	 * @param teamId
	 * @param playerId
	 * @return{BaseBean}
	 */
	public BaseBean deletePlayer(String teamId, String playerId) {
		String result = FAILURE_RESULT;
		BaseBean resultBean = new BaseBean();
		Team team = this.getTeamMap().get(teamId);
		boolean playerExists = isPlayerOnRoster(team, playerId);
		
		if(playerExists) {
			//only attempt to remove if player exists
			if(team.deletePlayer(playerId)) {
				result = SUCCESS_RESULT;
				updateFile(team);
			}
			
		} 
		resultBean.setStatusMessage(result);
		return resultBean;
	}
	
	/**
	 * returns boolean based on if player exists on roster
	 * @param team
	 * @param playerId
	 * @return{boolean}
	 */
	private boolean isPlayerOnRoster(Team team, String playerId) {
		boolean playerExists = false;
		HashMap<String, Player> map = team.getRoster();
		
		for(Map.Entry<String, Player> entry: map.entrySet() ) {
			if(entry.getValue().getPlayerId().equals(playerId)) {
				playerExists = true;
				break;
			}
		}
		
		return playerExists;
	}

	/**
	 * If map not initialized then do so
	 * @return hashmap
	 */
	private HashMap<String, Team> getTeamMap() {
		if (null == teams) {
			loadTeamHashMap();
		}

		return teams;
	}

	/**
	 * load the local team hashmap
	 */
	private void loadTeamHashMap() {
		Team chiefs = new Team("Chiefs", "1", "Kansas City");
		Team daBears = new Team("Bears", "2", "Da");

		teams = new HashMap<String, Team>();

		chiefs.setRoster(readRosterFromFile("chiefs.txt"));
		daBears.setRoster(readRosterFromFile("bears.txt"));

		teams.put(chiefs.getTeamId(), chiefs);
		teams.put(daBears.getTeamId(), daBears);
	}

	/**
	 * update roster team file
	 * @param team
	 */
	private void updateFile(Team team) {
		FileOutputStream fos = null;
		ObjectOutputStream oos = null;
		String fileName = team.getTeamId() == "1" ? "chiefs.txt" : "bears.txt";

		try {
			fos = new FileOutputStream("src/main/resources/roster/" + fileName);
			oos = new ObjectOutputStream(fos);

			oos.writeObject(team.getRoster());


		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (fos != null) {
				try {
					fos.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (oos != null) {
				try {
					oos.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * substitute for db connection
	 * reads roster from file and returns hash map
	 * @param fileName
	 * @return
	 */
	private HashMap<String, Player> readRosterFromFile(String fileName) {
		FileInputStream fin = null;
		ObjectInputStream ois = null;
		HashMap<String, Player> roster = null;

		try {
			fin = new FileInputStream(new File("src/main/resources/roster/" + fileName));
			ois = new ObjectInputStream(fin);
			roster = (HashMap<String, Player>) ois.readObject();

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return roster;
	}

}
