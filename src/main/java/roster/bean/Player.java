package roster.bean;


/**
 * Basic information used for a player.  SerialVersion is needed for writing data to a txt file
 * @author Kanak
 *
 */
public class Player extends BaseBean {

	
	private static final long serialVersionUID = 1L;
	private String firstName;
	private String lastName;
	private String position;
	private String number;
	private String teamId;
	private String playerId;
	
	public Player() {
		
	}
	
	public Player(String firstName, String lastName, String position, String number, String teamId, String playerId) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.position = position;
		this.number = number;
		this.teamId = teamId;
		this.playerId = playerId;
	}
	

	public String getPlayerId() {
		return playerId;
	}

	public void setPlayerId(String playerId) {
		this.playerId = playerId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getTeamId() {
		return teamId;
	}

	public void setTeamId(String teamId) {
		this.teamId = teamId;
	}

}
