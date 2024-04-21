package app.network;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import app.objects.submissions.SubmissionDocument;

public abstract class UserHandler implements Runnable {
    protected Socket clientSocket;
    protected BufferedReader in;
    protected PrintWriter out;
    protected ArrayList<AdminResponse> adminResponses;
    protected ArrayList<SubmissionDocument> userRequests;
    protected ObjectOutputStream objOut;
    protected ObjectInputStream objIn;
    protected PrintWriter pw;
    protected BufferedReader br;
    protected DataOutputStream dos;

    public UserHandler() {
    }

    public UserHandler(Socket socket) {
        this.clientSocket = socket;
        adminResponses = new ArrayList<>();
        userRequests = new ArrayList<>();

        try {
            objOut = new ObjectOutputStream(this.clientSocket.getOutputStream());
            objIn = new ObjectInputStream(this.clientSocket.getInputStream());
            out = new PrintWriter(this.clientSocket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(this.clientSocket.getInputStream()));
            pw = new PrintWriter(clientSocket.getOutputStream(), true);
            br = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            dos = new DataOutputStream(clientSocket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void closeAllConnections() {
        try {
            objOut.close();
            objIn.close();
            out.close();
            in.close();
            pw.close();
            br.close();
            dos.close();
            clientSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
