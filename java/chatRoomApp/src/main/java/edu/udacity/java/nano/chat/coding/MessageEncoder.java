package edu.udacity.java.nano.chat.coding;

import javax.json.Json;
import javax.json.JsonObject;
import javax.websocket.EncodeException;
import javax.websocket.Encoder;
import javax.websocket.EndpointConfig;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import edu.udacity.java.nano.chat.model.Message;

public class MessageEncoder implements Encoder.Text<Message> {
	private static final Logger logger = LoggerFactory.getLogger(MessageEncoder.class);

	@Override
	public String encode(Message message) throws EncodeException {

		return message.getJson().toString();

	}

	@Override
	public void init(EndpointConfig ec) {
		logger.info("init.");
	}

	@Override
	public void destroy() {
		logger.info("destroy.");
	}

}