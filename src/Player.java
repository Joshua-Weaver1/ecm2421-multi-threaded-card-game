import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

/**
 * PLayer Class.
 * 
 * @author Kevin Liu & Joshua Weaver
 */
public class Player implements Runnable{

    private final int playerId;
    private int numberOfcards;
    private final Card[] playerCards;
    private final String location;

    @Override
    public void run() {
        System.out.println("Thread of player " + this.playerId + " has started.");
    }

    public Player(int playerId) {
        this.playerId = playerId;
        this.playerCards = new Card[5];
        this.numberOfcards = 0;
        this.location = "Logs" + File.separator + "player" + this.playerId + "_output.txt";
        try {
            File playerOutFile = new File(this.location);
            playerOutFile.getParentFile().mkdirs();
            if (!playerOutFile.createNewFile()) {
                BufferedWriter bwriter = new BufferedWriter(new FileWriter(location));
                bwriter.write("Player " + this.playerId + " created.");
                bwriter.close();
            }
        } catch (IOException e) {
            System.out.printf("An output file for player"+ playerId + " has not been created.");
        }
    }

    public int getPlayerId(){
        return this.playerId;
    }

    public void addCardToDeck(Card card) {
        this.playerCards[numberOfcards++] = card;
        if (numberOfcards == 4) {
            playerOutput("Player " + this.playerId + " is starting with " + this.playerCards[0] + " " 
            + this.playerCards[1] + " " + this.playerCards[2] + " " + this.playerCards[3]);
        }
    }

    public void playerOutput(String string) {
        try {
            BufferedWriter bWriter = new BufferedWriter(new FileWriter(location, true));
            bWriter.newLine();
            bWriter.write(string);
            bWriter.close();
        } catch (IOException e) {
            System.out.println("Error:" + e);
        }
    }

    public boolean startGameCheck() {
        int cardPos = 0;
        Card card1 = this.playerCards[0];
        for(Card temp : this.playerCards) {
            if(cardPos++ == 4) break;
            if(temp.getCardNumber() != card1.getCardNumber()) {
                return false;
            }
        }
        System.out.println("Player " + playerId + " is the winner!");
        return true;
    }

    public Card playerDrawCard(Card drawCardFromLeft, int deckNum, int pickUpDeckNum) {
        boolean pref = true;
        Random rand = new Random();
        Card current;
        short count;

        do {
            count = (short) rand.nextInt(4);
            current = this.playerCards[count];
            if (current.getCardNumber() != getPlayerId()) pref= false;
        } while (pref);

        deckNum++;
        pickUpDeckNum++;
        this.playerCards[count] = current;

        return current;

    }
}
