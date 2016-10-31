package Psych;

/**
 * Created by Chris Nitta on 10/31/2016.
 */
public class FoilMakerModel {
    final private int MAX_PLAYER_SIZE = 3;
    private String username;
    private String password;
    private String[] players;
    private int playersWaiting = 0;
    private String gameToken;
    private String userToken;
    private String[] options = {""};

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String[] getPlayers() {
        return players;
    }

    public void addPlayer(String players) {
        for (int i = 0; i < 4; i++) {
            if (this.players[i].isEmpty()) {
                this.players[i] = players;
            }
        }
    }

    public int getPlayersWaiting() {
        return playersWaiting;
    }

    public void incPlayersWaiting() {
        this.playersWaiting++;
    }

    public String getGameToken() {
        return gameToken;
    }

    public void setGameToken(String gameToken) {
        this.gameToken = gameToken;
    }

    public String getUserToken() {
        return userToken;
    }

    public void setUserToken(String userToken) {
        this.userToken = userToken;
    }

    public int getMAX_PLAYER_SIZE() {
        return MAX_PLAYER_SIZE;
    }

    public void addOptions(String options) {
        for (int i = 0; i < 4; i++) {
            if (this.options[i].isEmpty()) {
                this.options[i] = options;
            }
        }
    }

    public String[] getOptions() {
        return options;
    }
}
