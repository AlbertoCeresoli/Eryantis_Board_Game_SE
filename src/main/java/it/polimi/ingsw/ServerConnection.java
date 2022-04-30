package it.polimi.ingsw;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class ServerConnection implements Runnable{
    private Socket server;
    private BufferedReader in;

    public ServerConnection(Socket s) throws IOException {
        this.server = s;
        in = new BufferedReader(new InputStreamReader(server.getInputStream()));
    }

    @Override
    public void run() {
        try {
            while (true) {
                String serverResponse = in.readLine();
                if (serverResponse == null) break;
                if (serverResponse.startsWith("Server")){
                    System.out.println(serverResponse);
                    System.out.print("> ");
                } else{
                System.out.println("Someone said: " + serverResponse);
                    System.out.print("> ");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }


}
