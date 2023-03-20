package models;

import java.util.HashMap;

public class Game {

    private HashMap<Team,Integer> scoreboard = new HashMap<>();
    private static int gameCount = 0;
    private static final int QUAFFLE_POINTS = 10;
    private static final int SNITCH_POINTS = 150;

    public Game(Team home, Team away) {
        scoreboard =  new HashMap<>();
        scoreboard.put(new Team(home), 0);
        scoreboard.put(new Team(away), 0);
        gameCount++;
    }

    public static int getGameCount() {
        return gameCount;
    }

    public int getScore(Team team){
        return scoreboard.get(team);
    }

    public HashMap<Team,Integer> setScore(Team team, Integer score){
        if(team == null){
            throw new IllegalArgumentException("Cannot add null to the scoreboard.");
        }
        scoreboard.put(team,score);
        return scoreboard;
    }

    public Team getTeam(String name){
        return this.scoreboard.entrySet()
        .stream()
        .filter((entry) -> entry.getKey().getHouse().equals(name))
        .findFirst()
        .orElse(null)
        .getKey();
    }

    public String getPlaceholder(String play){
        return play.substring(play.indexOf("<")+1, play.indexOf(">")); 
    }

    public String replacePlaceholder(String play, String placeholder, String value){
        return play.replace("<" + placeholder + ">", value)
                    .replace("<", "")
                    .replace(">", "");
    }

    public static int getQuafflePoints() {
        return QUAFFLE_POINTS;
    }

    public void quaffleScore(Team team){
        this.setScore(team, this.getScore(team)+ QUAFFLE_POINTS);
    }
    
    public void catchSnitch(Team team){
        this.setScore(team, this.getScore(team) + SNITCH_POINTS);
    }

    public Team getRandomTeam(){
        Object[] teams = this.scoreboard.keySet().toArray();
        return (Team)teams[random(teams.length)];
    }

    public int random(int range){
        return (int) (Math.random() * range);
    }

    public String simulate(String play){
        
        String simulate = "";
        String placeholder = getPlaceholder(play);
        Team team = getRandomTeam();
        String[] randomChaser = team.getChasers();
        if(placeholder.equals(Team.getPositionChaser())){
            quaffleScore(team);
            simulate = replacePlaceholder(play, placeholder, randomChaser[random(randomChaser.length)]);
        }else if(placeholder.equals(Team.getPositionSeeker())){
            catchSnitch(team);
            simulate = replacePlaceholder(play, placeholder, randomChaser[random(randomChaser.length)]);
        }else if(placeholder.equals(Team.getPositionKeeper())){
            simulate = replacePlaceholder(play, placeholder, randomChaser[random(randomChaser.length)]);
        }

        return simulate;
        
    }
   
}
