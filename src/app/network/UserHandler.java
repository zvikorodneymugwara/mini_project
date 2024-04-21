package app.network;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;

public abstract class UserHandler  implements Runnable  {
    protected Socket clientSocket;
    protected BufferedReader in;
    protected PrintWriter out;
    protected ArrayList<AdminResponse> adminResponses;
    protected ArrayList<UserRequest> userRequests;

    public UserHandler(){

    }

    public UserHandler(Socket socket){
        this.clientSocket = socket;
        adminResponses = new ArrayList<>();
        userRequests = new ArrayList<>();
    }
}
