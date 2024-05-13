package app.network;

import java.io.Serializable;

/**
 * The response of the admin to the user's request that will be sent over the
 * network
 */
public class AdminResponse implements Serializable {
    private boolean responseStatus;
    private String response;
    private String docId;

    // constructors

    public AdminResponse() {
    }

    public AdminResponse(boolean status, String response) {
        this.responseStatus = status;
        this.response = response;
    }

    // getters and setters

    public String getDocId() {
        return docId;
    }

    public void setDocId(String docId) {
        this.docId = docId;
    }

    public String getResponse() {
        return response;
    }

    public boolean getResponseStatus() {
        return responseStatus;
    }
}
