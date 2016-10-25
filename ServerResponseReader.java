import java.util.ArrayList;

/*
*
 */
public class ServerResponseReader {

    public ServerResponseReader(){}

    /*
    * Returns status of the command from server's
    * response message
     */
    public String getCommandStatus(String message){
        return message.substring(message.indexOf('-', 10) + 2);
    }

    /*
    * Returns command title from server's response message
     */
    public String getCommand(String message){
        return message.substring(message.indexOf('-') + 2, message.indexOf('-', 10));
    }

    /*
    * Returns an array of Strings which consists of the first
    * String being the word and the second String the correct
    * definition
     */
    public String [] getGameWord(String message){
        message = message.substring(message.indexOf('-'));
        String [] gameWord = new String[2];
        gameWord[0] = message.substring(message.indexOf('-') + 2, message.indexOf('-', 3));
        gameWord[1] = message.substring(message.indexOf('-', 3) + 2);
        return gameWord;
    }
    /*
    * Returns an array of Strings consisting of all the definition
    * options players can choose from for a round of the game
     */
    public String [] getRoundOptions(String message){
        ArrayList<String> options = new ArrayList<>();
        message = message.substring(message.indexOf('S') + 1) + "--";
        for(int i = 0; i < message.length(); i++){
            if(i == message.length() - 3)
                break;
            if(message.charAt(i) == '-'){
                options.add(message.substring(i + 2, message.indexOf('-', i + 2)));
                i += 2;
            }
        }
        return options.toArray(new String[options.size()]);
    }

    /*
    * Returns a String array consisting of the results from the previous
    * round of the game in the following format:
    *   - (0) Player name
    *   - (1) Message for player
    *   - (2) Cumulative score for player
    *   - (3) Number of players fooled by player
    *   - (4) Number of times the player was fooled by other players
    * for all players of the game
     */
    public String [] getRoundResults(String message) {
        ArrayList<String> results = new ArrayList<>();
        message = message.substring(message.indexOf('T') + 1) + "--";
        for(int i = 0; i < message.length(); i++){
            if(i == message.length() - 2)
                break;
            if(message.charAt(i) == '-'){
                results.add(message.substring(i + 2, message.indexOf('-', i + 2)));
                i += 2;
            }
        }
        return results.toArray(new String[results.size()]);
    }

    /*
    * Returns the session cookie token received from the server
    * in a successful login response
     */
    public String getSessionCookie(String message){
        String sessionCookie = "";
        for(int i = message.length() - 1; i >= 0; i--){
            if(message.charAt(i) == '-')
                return sessionCookie;
            else
                sessionCookie = message.charAt(i) + sessionCookie;
        }
        return sessionCookie;
    }

    /*
    * Returns the game token received from the server in a
    * successful creation of a new game response
     */
    public String getGameToken(String message){
        String gameToken = "";
        for(int i = message.length() - 1; i >= 0; i--){
            if(message.charAt(i) == '-')
                return gameToken;
            else
                gameToken = message.charAt(i) + gameToken;
        }
        return gameToken;
    }

    /*
    * Returns a String array which consists of a first String being
    * the new player's name and the second the player's cumulative
    * score
     */
    public String [] getNewParticipantInfo(String message){
        message = message.substring(message.indexOf('-'));
        String [] info = new String[2];
        info[0] = message.substring(message.indexOf('-') + 2, message.indexOf('-', 3));
        info[1] = message.substring(message.indexOf('-', 3) + 2);
        return info;
    }

    public static void main(String [] args){
        ServerResponseReader r = new ServerResponseReader();
        String msg = "ROUNDRESULT--Bob--You were fooled by Alice.--0--0--1--Alice--You got it right!. You " +
                "fooled Bob.--15--1--0";
                //"NEWPARTICIPANT--Alice--0";
                //"RESPONSE--STARTNEWGAME-SUCCESS--ypw";
                //"NEWGAMEWORD--A group of zebras--a dazzle";
                //"ROUNDOPTIONS--A zippy do--A zig zag--A dazzle";
        String [] o = r.getRoundResults(msg);//r.getRoundOptions(msg);
        for(int i = 0; i < o.length; i++)
            System.out.println(o[i]);
    }
}
