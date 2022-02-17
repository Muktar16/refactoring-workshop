package workshop;

public class Player {
    private String playerName;
    private int place;
    private int purse;
    private boolean inPenaltyBox;

    public Player(String name) {
        playerName = name;
        place = 0;
        purse = 0;
        inPenaltyBox = false;
    }

    public String getPlayerName() {
        return playerName;
    }

    public int getPlace() {
        return place;
    }

    public int getPurse() {
        return purse;
    }

    public void setInPenaltyBox(boolean inPenaltyBox) {
        this.inPenaltyBox = inPenaltyBox;
    }

    public boolean isInPenaltyBox() {
        return inPenaltyBox;
    }


    public void addRoll(int roll){
        place = place+roll;
   }

   public void reducePlace(){
        place = place - 12;
   }

   public void increasePurse(){
        purse++;
   }

}
