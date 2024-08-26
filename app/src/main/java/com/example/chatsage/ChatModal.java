package com.example.chatsage;

//another modal class which holds the data tht has to be displayed in recycler view
public class ChatModal {
    private String message; //display the data
    private String sender; //tells whether its a user or a bot
    //there will be two different view one for user another for bot and two layout file will be created for user and bot
    //to handle date inside recycler view an adapter class is created a
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public ChatModal(String message, String sender) {
        this.message = message;
        this.sender = sender;
    }
}
