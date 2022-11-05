/**
 * Card Deck class.
 * 
 * @author Kevin Liu & Joshua Weaver
 * @version 1.0
 */
public class CardDeck {
    private int numberOfCards;
    private int deckId;
    private Card[] deckCards;

    CardDeck(int deckId) {
        this.numberOfCards = 0;
        this.deckId = deckId;
        this.deckCards = new Card[5];
    }

    public void addToDeck(Card card) {
        this.deckCards[numberOfCards++] = card;
    }

    public void drawCardFromDeck(Card number) {
        
    }
}
