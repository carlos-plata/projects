package com.example.demo;

import java.lang.reflect.Field;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestUtils {

	private static final Logger log = LoggerFactory.getLogger(TestUtils.class);

	public static void injectObjects(Object target, String fieldName, Object toInject) {
		boolean wasPrivate = false;

		Field field;
		try {
			field = target.getClass().getDeclaredField(fieldName);
			if (!field.isAccessible()) {
				field.setAccessible(true);
				wasPrivate = true;
			}
			field.set(target, toInject);
			if (wasPrivate) {
				field.setAccessible(false);
			}
		} catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException e) {
			log.error(e.getMessage());
		}

	}

}
