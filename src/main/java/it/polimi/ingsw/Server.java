package it.polimi.ingsw;

import it.polimi.ingsw.Constants.Constants;
import it.polimi.ingsw.Messages.EasyMessage;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {
    static ServerSocket serverSocket;
    private static final ArrayList<ClientHandler> clients = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        //server creation
        serverSocket = new ServerSocket(1234);
        System.out.println("[SERVER] Waiting for client connection...");

        while (true) {
            Socket client = serverSocket.accept();
            acceptClient(client);
        }
    }

    /**
     * The method is used to establish connection with clients. It creates a thread that will infinitely run in order to
     * accept client connection. For each of them, playerAcceptance method is called, that depending on the number of
     * clients already connected, will behave in a different way
     */
    public static void acceptClient(Socket client) {
        Runnable acceptClient = () -> {
            try {
                manageNewPlayer(client);
            } catch (IOException | InterruptedException | ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        };

        new Thread(acceptClient).start();
    }

    /**
     * The method is used to establish connection with a single client. After it, nickname selection has to be made.
     *
     * <p>When nickname is chosen, the new player could be added to the list of ClientHandler that will be run later.</p>
     *
     * <p>When the first connection is established, game rules are chosen; when the last client connects to the server,
     * createGame() method is launched.</p>
     *
     * <p>Checks on clients.size are done in a synchronized block of the code</p>
     */
    public static void manageNewPlayer(Socket client) throws IOException, InterruptedException, ClassNotFoundException {
        System.out.println("[SERVER] Client connected");

        ClientHandler player = new ClientHandler(client);
        player.selectNickName();
        while (!checkNickname(player.getNickName())) {
            player.sendMessage(new EasyMessage("Nickname you choose is not available, please insert a new one: "));
            player.selectNickName();
        }

        synchronized (clients) {
            if (clients.size() == 0) {
                clients.add(player);
                player.sendMessage(new EasyMessage("You are the first player, please select game mode"));
                player.gameRulesSelection();
            } else if (clients.size() < Constants.getNumPlayers()) {
                clients.add(player);
                if (clients.size() == Constants.getNumPlayers()) {
                    player.sendMessage(new EasyMessage("Correctly connected to the game, you were the last client needed, game will start now..."));
                    createGame();
                } else {
                    player.sendMessage(new EasyMessage("Correctly connected to the game, wait for other clients"));
                }
            } else if (clients.size() == Constants.getNumPlayers() - 1) {
                player.sendMessage(new EasyMessage("All requested clients are already connected, please try again later"));
                player.getClient().close();
            }
        }
    }

    /**
     * The method controls if nickname chosen by a player that is connecting is already used or not
     *
     * @param nickname that has just been chosen
     * @return true or false depending on the availability of chosen nickname
     */
    public static boolean checkNickname(String nickname) {
        boolean check = true;

        for (ClientHandler client : clients) {
            if (client.getNickName().equals(nickname)) {
                check = false;
                break;
            }
        }

        return check;
    }

    /**
     * The method create a new instance of GameHandler and runs it on a dedicated thread. After it, all clients collected
     * in the list are run.
     */
    private static void createGame() {
        new Thread(new GameHandler(clients)).start();

        ExecutorService pool = Executors.newFixedThreadPool(Constants.getNumPlayers());
        for (ClientHandler client : clients)
            pool.execute(client);
    }
}
