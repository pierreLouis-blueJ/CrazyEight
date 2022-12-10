package game;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Random;

import fr.pantheonsorbonne.miage.Facade;
import fr.pantheonsorbonne.miage.PlayerFacade;
import fr.pantheonsorbonne.miage.model.Game;
import fr.pantheonsorbonne.miage.model.GameCommand;

public class CrazyEightNetworkPlayer{

    private static final String playerId = "Player-" + new Random().nextInt();
    protected static LinkedList<Card> hand = new LinkedList<>();

    static final PlayerFacade playerFacade = Facade.getFacade();
    static Game crazyEight;

    public static void main(String[] args) {

        playerFacade.waitReady();
        playerFacade.createNewPlayer(playerId);
        crazyEight = playerFacade.autoJoinGame("CRAZYEIGHT");
        while (true) {

            GameCommand command = playerFacade.receiveGameCommand(crazyEight);
            switch (command.name()) {
                case "cardsForYou":
                    handleCardsForYou(command);
                    break;
                case "playACard":
                    System.out.println("I have " + hand.stream().map(Card::toFancyString).collect(Collectors.joining(" ")));
                    handlePlayACard(command);
                    break;
                case "gameOver":
                    handleGameOverCommand(command);
                    break;

            }
        }
    }

    protected static void handleCardsForYou(GameCommand command){
        for (Card card : Card.stringToCards(command.body())) {
            hand.offerLast(card);
        }
    }

    private static void handlePlayACard(GameCommand command) {
        if (command.params().get("playerId").equals(playerId)) {
            if (!hand.isEmpty()) {
                playerFacade.sendGameCommandToAll(war, new GameCommand("card", hand.pollFirst().toString()));
            } else {
                playerFacade.sendGameCommandToAll(war, new GameCommand("outOfCard", playerId));
            }
        }
    }


}
