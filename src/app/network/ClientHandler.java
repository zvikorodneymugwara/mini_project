package app.network;

import java.net.Socket;
import java.util.ArrayList;

public class ClientHandler extends UserHandler {

    public ClientHandler(Socket clientSocket) {
        super(clientSocket);
    }

    @Override
    public void run() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'run'");
    }

    public void sendRequest() {

    }

    public ArrayList<String> recieveResponses() {
        return null;
    }

}
