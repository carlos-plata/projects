package edu.udacity.java.nano.chat.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.json.Json;
import javax.websocket.EncodeException;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import edu.udacity.java.nano.chat.coding.MessageDecoder;
import edu.udacity.java.nano.chat.coding.MessageEncoder;
import edu.udacity.java.nano.chat.model.Message;

/**
 * WebSocket Server
 *
 * @see ServerEndpoint WebSocket Client
 * @see Session WebSocket Session
 */

@Component
@ServerEndpoint(value = "/chat/{username}", decoders = MessageDecoder.class, encoders = MessageEncoder.class)
public class WebSocketChatServer {
	private static final Logger logger = LoggerFactory.getLogger(WebSocketChatServer.class);

	/**
	 * All chat sessions.
	 */
	private static Map<String, Session> onlineSessions = new ConcurrentHashMap<>();
	/*
	 * All online users
	 */
	private static HashMap<String, String> users = new HashMap<>();

	private static void sendMessageToAll(Message message) {
		logger.info("Message for all: " + message.toJSONString());
		for (Map.Entry<String, Session> entry : onlineSessions.entrySet()) {
			try {
				((Session) entry.getValue()).getBasicRemote().sendObject(message);
			} catch (IOException | EncodeException e) {
				logger.error(e.getMessage());
			}
		}

	}

	/**
	 * Open connection, 1) add session, 2) add user.
	 */
	@OnOpen
	public void onOpen(Session session, @PathParam("username") String username) {
		logger.info("onOpen: " + username);
		onlineSessions.put(session.getId(), session);
		users.put(session.getId(), username);
		Message message = new Message(
				Json.createObjectBuilder().add("user", username).add("content", " has joined the conversation!")
						.add("onlineUsers", Integer.toString(users.size())).build());
		sendMessageToAll(message);
	}

	/**
	 * Send message, 1) get username and session, 2) send message to all.
	 */
	@OnMessage
	public void onMessage(Session session, Message message) {
		logger.info("onMessage: " + message.toJSONString());
		message.setUser(users.get(session.getId()));
		sendMessageToAll(message);
	}

	/**
	 * Close connection, 1) remove session, 2) update user.
	 */
	@OnClose
	public void onClose(Session session) {
		String user = (String) users.get(session.getId());
		logger.info("onClose: " + user);
		onlineSessions.remove(session.getId());
		users.remove(session.getId());
		Message message = new Message(
				Json.createObjectBuilder().add("user", user).add("content", " has left the conversation!")
						.add("onlineUsers", Integer.toString(users.size())).build());
		sendMessageToAll(message);
	}

	/**
	 * Print exception.
	 */
	@OnError
	public void onError(Session session, Throwable error) {
		error.printStackTrace();
	}

}
