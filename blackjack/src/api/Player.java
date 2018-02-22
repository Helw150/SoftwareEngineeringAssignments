package api;

/*
 * A card player. Implementations should have at least two
 * constructors, neither of which are default:
 *
 *   1. Takes a single string that is the players name.
 *   2. Takes a string and an integer, representing the players name
 *      and the initial monetary balance.
 */
public interface Player extends Comparable<Player> {
    /*
     * Receive a card. The number of cards in this players hand should
     * increase after this method is called.
     */
    public void receive(Card card);

    /*
     * Return the current hand to the caller
     */
    public Hand getHand();

    /*
     * Remove and return the cards in this players hand. The number of
     * cards in the hand should be zero after this method is run.
     */
    public Hand relinquishCards();

    /*
     * Place a wager. This should actually deduct this amount from the
     * players wallet. As such it should never be more than the player
     * actually has.
     */
    public int placeWager();

    /*
     * Give a player money; generally run upon winning a round.
     */
    public void payOut(int money);

    /*
     * Return the amount of money currently available to the
     * player. This value returned should never be negative.
     */
    public int getMoney();

    /*
     * Return the name of the player
     */
    public String getName();

    /*
     * Whether the player would like to request a card: true indicates
     * that they would (equivalent to a 'hit' in Black Jack); false
     * indicates they would not.
     */
    public boolean requestCard();

    /*
     * The number of cards currently in the hand.
     */
    public int numberOfCards();
}
