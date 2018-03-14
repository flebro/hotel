package com.iia.webservices.groupa.hotel.serialization;

import java.io.IOException;
import java.time.LocalDate;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.iia.webservices.groupa.hotel.utils.LocalDateUtil;

public class CustomLocalDateSerializer extends StdSerializer<LocalDate> {
	
	private static final long serialVersionUID = -6696321419784631609L;

	public CustomLocalDateSerializer() {
		super(LocalDate.class);
	}

	@Override
	public void serialize(LocalDate date, JsonGenerator gen, SerializerProvider arg2) throws IOException {
		gen.writeString(LocalDateUtil.format(date));
	}

}
