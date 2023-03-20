package models;
import java.util.Arrays;
import java.util.Objects;

public class Team{

    private static final String POSITION_CHASER = "chaser";
    private static final String POSITION_SEEKER = "seeker";
    private static final String POSITION_KEEPER = "keeper";

    private String house;
    private String keeper;
    private String seeker;
    private String[] chasers = new String[3];

    public Team(String house, String keeper, String seeker, String[] chasers) {
        if(house.equals(null) || keeper.equals(null) || seeker.equals(null)){
            throw new IllegalArgumentException("Fields cannot be null.");
        }
        if(house.isBlank() || keeper.isBlank() || seeker.isBlank()){
            throw new IllegalArgumentException("Fields cannot be blank.");
        }
        if(chasers.length!=3){
            throw new IllegalArgumentException("There must be 3 chasers!");
        }
        if(hasNull(chasers)){
            throw new IllegalArgumentException("Chasers cannot be null!");
        }
        if(hasBlank(chasers)){
            throw new IllegalArgumentException("Fields in chasers cannot be blank!");
        }
        this.house = house;
        this.keeper = keeper;
        this.seeker = seeker;
        this.chasers = Arrays.copyOf(chasers,chasers.length);
    }

    public Team(Team source){
        this.house = source.house;
        this.keeper = source.keeper;
        this.seeker = source.seeker;
        this.chasers = Arrays.copyOf(source.chasers,source.chasers.length);
    }

    public String getHouse() {
        return house;
    }
    public String getKeeper() {
        return keeper;
    }
    public String getSeeker() {
        return seeker;
    }
    public String[] getChasers() {
        return Arrays.copyOf(this.chasers, this.chasers.length);
    }

    public void setHouse(String house) {
        checkParam(house);
        this.house = house;
    }
    public void setKeeper(String keeper) {
        checkParam(keeper);
        this.keeper = keeper;
    }
    public void setSeeker(String seeker) {
        checkParam(seeker);
        this.seeker = seeker;
    }
    public void setChasers(String[] chasers) {
        this.chasers = Arrays.copyOf(chasers, chasers.length);
    }
    
     public static String getPositionChaser() {
         return POSITION_CHASER;
     }

     public static String getPositionSeeker() {
         return POSITION_SEEKER;
     }

     public static String getPositionKeeper() {
         return POSITION_KEEPER;
     }

     @Override
     public String toString() {
        return "House: " + this.house + "\n" +
        "Keeper: " + this.keeper + "\n" +         
        "Seeker: "  + this.seeker + "\n" +         
        "Chasers: " + Arrays.toString(this.chasers) + "\n"; 
     }

    public static boolean hasNull(String[] chasers){
       return Arrays.stream(chasers)
       .anyMatch(chaser -> chaser == null);
       
    }
    public static boolean hasBlank(String[] chasers){
        return Arrays.stream(chasers)
        .anyMatch(chaser -> chaser.isBlank());
    }
    public void checkParam(String param) {
        if (param == null || param.isBlank()) {
            throw new IllegalArgumentException(param + " cannot be null or blank");
        }
    }

    public boolean equals(Object obj){
        
        if(obj == null){
            return false;
        }

        if(!(obj instanceof Team)){
            return false;
        }

        Team team = (Team)obj;
        return this.house.equals(team.house) &&
        this.keeper.equals(team.keeper) &&
        this.seeker.equals(team.seeker) &&
        Arrays.toString(this.chasers).equals(Arrays.toString(team.chasers));
    }

    public int hashCode(){
        return Objects.hash(house, keeper, seeker, Arrays.toString(chasers));
    }





}
