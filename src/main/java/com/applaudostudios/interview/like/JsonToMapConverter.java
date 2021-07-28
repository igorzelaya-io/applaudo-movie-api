package com.applaudostudios.interview.like;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Converter
public class JsonToMapConverter implements AttributeConverter<String, Map<String, Object>>{

	private static final Logger LOGGER = LoggerFactory.getLogger(JsonToMapConverter.class);
	
	@Override
	@SuppressWarnings("unchecked")
	public Map<String, Object> convertToDatabaseColumn(String attribute){
		try {
			ObjectMapper mapper = new ObjectMapper();
			return mapper.readValue(attribute, HashMap.class);
		}
		catch(NullPointerException | JsonProcessingException e) {
			LOGGER.error("Convertion failed while trying to conert string to map");
			return new HashMap<>();
		}
	}
	
	@Override
	public String convertToEntityAttribute(Map<String, Object> dbData) {
		try {
			ObjectMapper mapper = new ObjectMapper();
			return mapper.writeValueAsString(dbData);
		}
		catch(JsonProcessingException e) {
			LOGGER.error("Could not convert map to json string.");
			return null;
		}
	}
	
}
