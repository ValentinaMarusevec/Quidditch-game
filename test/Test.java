package test;
import static org.junit.jupiter.api.Assertions.assertTrue;
import models.Team;

public class Test {
    
    @org.junit.jupiter.api.Test
    public void hasNullTest(){
        String[] chasers = new String[] {null, "Ginny", "Katie"};
        assertTrue(Team.hasNull(chasers));
    }

    @org.junit.jupiter.api.Test
    public void hasBlankTest(){
        String[] chasers = {"    ", "Ginny", "Katie"}; 
        assertTrue(Team.hasBlank(chasers));
    }

}
