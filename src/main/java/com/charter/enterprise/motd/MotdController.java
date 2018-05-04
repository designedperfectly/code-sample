package com.charter.enterprise.motd;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.charter.enterprise.motd.model.Message;

@RestController
public class MotdController {

    @Autowired
    private Message messageOfTheDay;

    private ArrayList<Message> messageHistory;

    private final String putSuccessMessage = "Message updated successfully.";

    /**
     * @return The current message of the day.
     */
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String getDefault() {
    	initMotdHistory();
        return messageOfTheDay.getValue();
    }

    @RequestMapping(value = "/motd", method = RequestMethod.GET)
    public String getMotd() {
    	initMotdHistory();
        return messageOfTheDay.getValue();
    }

    @RequestMapping(value = "/browserHistory", method = RequestMethod.GET)
    public String getHistoryForwardInBrowswer() {
    	initMotdHistory();
    	StringBuilder history = new StringBuilder();
    	history.append("<ol>");
    	for (int i = 0; i < messageHistory.size(); i++) {
    		history.append("<li>" + messageHistory.get(i).getValue() + "</li>");
    	}
    	history.append("</ol>");
        return history.toString();
    }

    @RequestMapping(value = "/browserHistoryReverse", method = RequestMethod.GET)
    public String getHistoryReverseInBrowswer() {
    	initMotdHistory();
    	StringBuilder history = new StringBuilder();
    	history.append("<ol reversed>");
    	for (int i = messageHistory.size() - 1; i >= 0; i--) {
    		history.append("<li>" + messageHistory.get(i).getValue() + "</li>");
    	}
    	history.append("</ol>");
        return history.toString();
    }

    @RequestMapping(value = "/history", method = RequestMethod.GET)
    public String getHistoryForward() {
    	initMotdHistory();
    	StringBuilder history = new StringBuilder();
    	for (int i = 0; i < messageHistory.size(); i++) {
    		if (i > 0) {
    			history.append("\n");
    		}
    		history.append(messageHistory.get(i).getValue());
    	}
        return history.toString();
    }

    public void initMotdHistory() {
    	if (null == messageHistory) {
    		messageHistory = new ArrayList<>();
    		messageHistory.add(new Message(messageOfTheDay.getValue()));
    	}
    }

    /**
     * PUT /motd - updates message of the day
     *
     *  @param newMessage: JSON body of the message
     *     format: {
     *          value (String): the value of the new message
     *     }
     */
    @RequestMapping(value = "/motd", method = RequestMethod.PUT, consumes = "application/json")
    public ResponseEntity<?> setMotd(@RequestBody Message newMessage)
    {
    	final String motd = newMessage.getValue();
        messageOfTheDay.setValue(motd);
		messageHistory.add(new Message(motd));
    	ResponseEntity<String> response = new ResponseEntity<String>(putSuccessMessage, HttpStatus.OK);
        return response;
    }

}
