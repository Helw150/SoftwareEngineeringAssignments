package api;

/*
 * A card dealer, the maestro of the table. Implementations should
 * have a single constructor that takes an integer representing the
 * number of decks to be used.
 */
public interface Dealer {
    /*
     * Deal a single card to a player, and collect all cards from a
     * player, respectively. In both cases, card instances should be
     * preserved: a card should be explicity removed from one location
     * (a deck or a hand) and added to the other.
     */
    public void dealCard(Player player);
    public void collectCards(Player player);

    /*
     * Get the hand of the dealer.
     */
    public Hand getHand();

    /*
     * Shuffle the cards.
     */
    public void shuffle();
}
