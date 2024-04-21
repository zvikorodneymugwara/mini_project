package app.network;

import java.net.Socket;
import java.util.ArrayList;

public class AdminHandler extends UserHandler {

    public AdminHandler(Socket clientSocket) {
        super(clientSocket);
    }

    public void sendResponse() {

    }

    public ArrayList<String> recieveRequests() {
        return null;
    }

    public AdminResponse processResponse() {
        return null;
    }

    @Override
    public void run() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'run'");
    }
}
