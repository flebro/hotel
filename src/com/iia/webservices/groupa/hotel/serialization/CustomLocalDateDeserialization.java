package com.iia.webservices.groupa.hotel.serialization;

import java.io.IOException;
import java.time.LocalDate;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.iia.webservices.groupa.hotel.utils.LocalDateUtil;

public class CustomLocalDateDeserialization extends StdDeserializer<LocalDate> {

	private static final long serialVersionUID = 2195502536872458137L;

	protected CustomLocalDateDeserialization() {
		super(LocalDate.class);
	}

	@Override
	public LocalDate deserialize(JsonParser arg0, DeserializationContext arg1)
			throws IOException, JsonProcessingException {
		return LocalDateUtil.parse(arg0.readValueAs(String.class));
	}

}
