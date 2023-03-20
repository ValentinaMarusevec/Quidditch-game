
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

import models.Game;
import models.Team;

public class Main {

    public static String TEAMS_FILE = "teams.txt";
    public static String PLAYS_FILE = "plays.txt";
    public static Game game;
    public static void main(String[] args){

        String[][] data = new String[TEAMS_FILE.length()][];

        try {
            data = getData();
            game = new Game(
                new Team(data[0][0], data[0][1], data[0][2], new String[] {data[0][3], data[0][4], data[0][5]}),
                new Team(data[1][0], data[1][1], data[1][2], new String[] {data[1][3], data[1][4], data[1][5]})
              );
    
              startGame();
              printResult();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        
    }

    public static String[][] getData() throws FileNotFoundException{
        List<List<String>> data = new ArrayList<>();
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(TEAMS_FILE));

            String line;

            while ((line = bufferedReader.readLine()) != null) {
                List<String> row = Arrays.asList(line.split(","));
                data.add(row);
            }

            bufferedReader.close();
        } catch (IOException e) {
            System.out.println("Cannot read the file.");
            e.printStackTrace();
        }
        
        String[][] array = new String[data.size()][];
        for(int i = 0; i < data.size(); i++){
            List<String> row = data.get(i);
            array[i] = row.toArray(new String[row.size()]);
        }
        return array;
    }

    public static String startGame(){
        String result = "";
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(PLAYS_FILE));

            String line;

            while ((line = bufferedReader.readLine()) != null) {
                result = game.simulate(line);
                System.out.println("\n" + result + "\n");
                wait(3);
            }

            bufferedReader.close();
        } catch (IOException e) {
            System.out.println("Cannot read the file.");
            e.printStackTrace();
        }
        return result;
    }

    public static void printResult(){
        Team gryffindor = game.getTeam("GRYFFINDOR");
        Team slytherin = game.getTeam("SLYTHERIN");
        System.out.println("\nGRYFFINDOR: " + game.getScore(gryffindor) + " SLYTHERIN: " + game.getScore(slytherin));

        if(game.getScore(gryffindor) > game.getScore(slytherin)){
            System.out.println("\nGryffindor wins!");
        }else{
            System.out.println("\nSlytherin wins!");
        }
    }

    public static void wait(int sec){
       try {
        TimeUnit.SECONDS.sleep(sec);
    } catch (InterruptedException e) {
        e.printStackTrace();
    }
    }

}
