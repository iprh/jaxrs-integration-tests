package com.redhat.validation.resource;

import org.jboss.resteasy.api.validation.Validation;

import javax.validation.ValidationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;

/**
 * 
 * @author Nicolas NESMON
 *
 */
public abstract class ValidationExceptionMapper<T extends ValidationException> implements ExceptionMapper<T> {

	@Override
	public Response toResponse(T validationException) {
		ResponseBuilder builder = Response.status(Status.INTERNAL_SERVER_ERROR)
				.entity(getClass().getName() + ":" + validationException.getMessage());
		builder.type(MediaType.TEXT_PLAIN);
		builder.header(Validation.VALIDATION_HEADER, "true");
		return builder.build();
	}

}