package br.com.neomind.rest;

import java.io.IOException;
import java.io.StringWriter;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import br.com.neomind.exception.GlobalException;

public class UtilRest {

	public ObjectMapper getObjectMapper() throws GlobalException {

		try {
			ObjectMapper mapper = new ObjectMapper();
			return mapper;

		} catch (Throwable e) {

			e.printStackTrace();
			return null;
		}

	}

	public String buildResponse(Object objeto) throws GlobalException,
			JsonGenerationException, JsonMappingException, IOException {

		StringWriter fw = new StringWriter();
		ObjectMapper mapper = new ObjectMapper();
		try {
			mapper.writeValue(fw, objeto);
			return fw.toString();

		} catch (Exception e) {
		}
		ResponseBuilder rb = Response.serverError();
		return rb.toString();

	}

}
