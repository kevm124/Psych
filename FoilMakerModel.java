public class FoilMakerModel {
    ServerResponseReader r = new ServerResponseReader();
    Server s = new Server();
    final private int MAX_PLAYER_SIZE = 3;
    private String username;
    private String password;
    private String[] players;
    private int playersWaiting = 0;
    private String gameToken;
    private String userToken;
    private String[] options = {""};
    private String startGameMessage;
    private boolean leaderStartedGame = false;
    private String score;
    private String playersFooled;
    private String timesFooled;

    public void setPlayersFooled(String playersFooled) {
        this.playersFooled = playersFooled;
    }

    public String getPlayersFooled() {
        return playersFooled;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getScore() {
        return score;
    }

    public void setTimesFooled(String timesFooled) {
        this.timesFooled = timesFooled;
    }

    public String getTimesFooled() {
        return timesFooled;
    }

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

    public void addPlayer(String player) {
        for (int i = 0; i < 4; i++) {
            if (this.players[i].isEmpty()) {
                this.players[i] = player;
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

    public String getStartGameMessage() {
        return startGameMessage;
    }

    public void setStartGameMessage(String startGameMessage) {
        this.startGameMessage = startGameMessage;
    }
    public boolean getLeaderStartedGame() {
        return leaderStartedGame;
    }
    public void setLeaderStartedGame(boolean leaderStartedGame) {
        this.leaderStartedGame = leaderStartedGame;
    }
    public String getDefinition() {
        String gameWord = s.getGameWord();
        if (gameWord.substring(0,11).equals("NEWGAMEWORD")) {
            String defintion = r.getDefinition(r.getGameWord(gameWord));
            return defintion;
        }
        else
            return null;
    }

}
