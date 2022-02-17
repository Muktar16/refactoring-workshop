package workshop;

import java.util.ArrayList;
import java.util.LinkedList;


public class TriviaGame {
    //ArrayList players = new ArrayList();
    //int[] places = new int[6];
    //int[] purses = new int[6];
    //boolean[] inPenaltyBox = new boolean[6];
    ArrayList<Player> players = new ArrayList<>();
    Questions questions = new Questions();

    //LinkedList popQuestions = new LinkedList();
    //LinkedList scienceQuestions = new LinkedList();
    //LinkedList sportsQuestions = new LinkedList();
    //LinkedList rockQuestions = new LinkedList();

    int currentPlayer = 0;
    boolean isGettingOutOfPenaltyBox;

//    public TriviaGame() {
//        for (int i = 0; i < 50; i++) {
//            popQuestions.addLast("Pop Question " + i);
//            scienceQuestions.addLast(("Science Question " + i));
//            sportsQuestions.addLast(("Sports Question " + i));
//            rockQuestions.addLast(("Rock Question " + i));
//        }
//    }

    //public String createRockQuestion(int index) {
        //return "Rock Question " + index;
    //}

    public boolean isPlayable() {
        return (howManyPlayers() >= 2);
    }

    public boolean add(String playerName) {

        Player newPlayer = new Player(playerName);
        players.add(newPlayer);
        //players.add(playerName);
        //places[howManyPlayers()] = 0;
        //purses[howManyPlayers()] = 0;
        //inPenaltyBox[howManyPlayers()] = false;

        announce(newPlayer.getPlayerName() + " was added");
        announce("They are player number " + players.size());
        return true;
    }

    public int howManyPlayers() {
        return players.size();
    }

    public void roll(int roll) {
        announce(players.get(currentPlayer).getPlayerName() + " is the current player");
        announce("They have rolled a " + roll);

        if (players.get(currentPlayer).isInPenaltyBox()) {
            if (roll % 2 != 0) {
                isGettingOutOfPenaltyBox = true;
                announce(players.get(currentPlayer).getPlayerName() + " is getting out of the penalty box");
                players.get(currentPlayer).addRoll(roll);
                //places[currentPlayer] = places[currentPlayer] + roll;
                //if (places[currentPlayer] > 11) places[currentPlayer] = places[currentPlayer] - 12;
                if (players.get(currentPlayer).getPlace() > 11) players.get(currentPlayer).reducePlace();

                announce(players.get(currentPlayer).getPlayerName()
                        + "'s new location is "
                        + players.get(currentPlayer).getPlace());
                announce("The category is " + currentCategory());
                askQuestion();
            } else {
                announce(players.get(currentPlayer).getPlayerName() + " is not getting out of the penalty box");
                isGettingOutOfPenaltyBox = false;
            }

        } else {

            //places[currentPlayer] = places[currentPlayer] + roll;
            players.get(currentPlayer).addRoll(roll);
            //if (places[currentPlayer] > 11) places[currentPlayer] = places[currentPlayer] - 12;
            if (players.get(currentPlayer).getPlace() > 11) players.get(currentPlayer).reducePlace();


            announce(players.get(currentPlayer).getPlayerName()
                    + "'s new location is "
                    + players.get(currentPlayer).getPlace());
            announce("The category is " + currentCategory());
            askQuestion();
        }

    }

    private void askQuestion() {
        if (currentCategory() == "Pop")
            announce(questions.popQuestions.removeFirst());
        if (currentCategory() == "Science")
            announce(questions.scienceQuestions.removeFirst());
        if (currentCategory() == "Sports")
            announce(questions.sportsQuestions.removeFirst());
        if (currentCategory() == "Rock")
            announce(questions.rockQuestions.removeFirst());
    }


    private String currentCategory() {
        if (players.get(currentPlayer).getPlace() == 0) return "Pop";
        if (players.get(currentPlayer).getPlace() == 4) return "Pop";
        if (players.get(currentPlayer).getPlace() == 8) return "Pop";
        if (players.get(currentPlayer).getPlace() == 1) return "Science";
        if (players.get(currentPlayer).getPlace() == 5) return "Science";
        if (players.get(currentPlayer).getPlace() == 9) return "Science";
        if (players.get(currentPlayer).getPlace() == 2) return "Sports";
        if (players.get(currentPlayer).getPlace() == 6) return "Sports";
        if (players.get(currentPlayer).getPlace() == 10) return "Sports";
        return "Rock";
    }

    public boolean wasCorrectlyAnswered() {
        if (players.get(currentPlayer).isInPenaltyBox()) {
            if (isGettingOutOfPenaltyBox) {
                announce("Answer was correct!!!!");
                //purses[currentPlayer]++;
                players.get(currentPlayer).increasePurse();
                announce(players.get(currentPlayer).getPlayerName()
                        + " now has "
                        + players.get(currentPlayer).getPurse()
                        + " Gold Coins.");

                boolean winner = didPlayerWin();
                currentPlayer++;
                if (currentPlayer == players.size()) currentPlayer = 0;

                return winner;
            } else {
                currentPlayer++;
                if (currentPlayer == players.size()) currentPlayer = 0;
                return true;
            }


        } else {

            announce("Answer was correct!!!!");
            //purses[currentPlayer]++;
            players.get(currentPlayer).increasePurse();
            announce(players.get(currentPlayer).getPlayerName()
                    + " now has "
                    + players.get(currentPlayer).getPurse()
                    + " Gold Coins.");

            boolean winner = didPlayerWin();
            currentPlayer++;
            if (currentPlayer == players.size()) currentPlayer = 0;

            return winner;
        }
    }

    public boolean wrongAnswer() {
        announce("Question was incorrectly answered");
        announce(players.get(currentPlayer).getPlayerName() + " was sent to the penalty box");
        //inPenaltyBox[currentPlayer] = true;
        players.get(currentPlayer).setInPenaltyBox(true);

        currentPlayer++;
        if (currentPlayer == players.size()) currentPlayer = 0;
        return true;
    }

    private boolean didPlayerWin() {
        return !(players.get(currentPlayer).getPurse() == 6);
    }

    protected void announce(Object message) {
        System.out.println(message);
    }
}