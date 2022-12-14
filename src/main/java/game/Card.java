package game;

public class Card {
    private static final String EIGHT = "EIGHT";
    private static final String[] VALUES = { "ACE", "KING", "QUEEN", "JACK", "TEN", "NINE", EIGHT, "SEVEN", "SIX",
            "FIVE", "FOUR", "THREE", "TWO" }; 
    private static final String[] COLORS = { "HEARTS", "DIAMONDS", "CLUBS", "SPADES" }; 

    private String value;
    private String color; 

    protected Card(String value, String color) { // constructeur qui récupère les informations données par les
                                                 // paramètres
        this.value = value;
        this.color = color;
    }

    public static void cardPrinter(Card card) {
        System.out.println(card.getValue() + " OF " + card.getColor());
    }

    protected String getValue() {
        return this.value;
    }

    protected String getColor() {
        return this.color;
    }

    @Override
    public boolean equals(Object card) {
        if(card instanceof Card){
            return this.haveSameColor((Card) card) && this.haveSameValue((Card) card);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    protected boolean haveSameColor(Card card) {
        return this.getColor().equals(card.getColor());
    }

    protected boolean haveSameValue(Card card) {
        return this.getValue().equals(card.getValue());
    }

    protected static String[] getAllCardsValues() {
        return VALUES;
    }

    protected static String[] getAllCardsColors() {
        return COLORS;
    }

    protected static String getMostPowerfullValue() {
        return EIGHT;
    }
}