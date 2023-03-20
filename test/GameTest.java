package test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

import models.Game;
import models.Team;

public class GameTest {
  
    @Test
    public void getPlaceholderTest(){
        Game game = new Game(
            new Team("GRYFFINDOR", "Oliver", "Harry", 
            new String[] {"Angelina", "Ginny", "Katie"}), 
            new Team("SLYTHERIN", "Vincent",  "Draco", 
            new String[] {"Bridget", "Harper", "Malcolm"})
        );
        assertEquals("chaser", game.getPlaceholder("<chaser> gets the next pass"));
    }

    @Test
    public void replacePlaceholder(){
        Game game = new Game(
            new Team("GRYFFINDOR", "Oliver", "Harry", 
            new String[] {"Angelina", "Ginny", "Katie"}), 
            new Team("SLYTHERIN", "Vincent",  "Draco", 
            new String[] {"Bridget", "Harper", "Malcolm"})
        );
        assertEquals("Katie gets the next pass",game.replacePlaceholder("<chaser> gets the next pass", "chaser", "Katie"));
    }

    @Test
    public void quaffleScoreTest(){
        Game game = new Game(
            new Team("GRYFFINDOR", "Oliver", "Harry", 
            new String[] {"Angelina", "Ginny", "Katie"}), 
            new Team("SLYTHERIN", "Vincent",  "Draco", 
            new String[] {"Bridget", "Harper", "Malcolm"})
        );

        Team team = game.getTeam("GRYFFINDOR");

        game.quaffleScore(team);
        game.quaffleScore(team);

        assertEquals(20, game.getScore(team));
    }

    @Test
    public void catchSnitchTest(){
        Game game = new Game(
            new Team("GRYFFINDOR", "Oliver", "Harry", 
            new String[] {"Angelina", "Ginny", "Katie"}), 
            new Team("SLYTHERIN", "Vincent",  "Draco", 
            new String[] {"Bridget", "Harper", "Malcolm"})
        );

        Team team = game.getTeam("SLYTHERIN");

        game.catchSnitch(team);

        assertEquals(150, game.getScore(team));

    }
}
