package com.ecommerce.microcommerce.spring;

import java.time.Instant;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.core.annotation.AnnotatedElementUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.ecommerce.microcommerce.web.exception.CustomException;

@ControllerAdvice
public class CustomRestExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler({ CustomException.class })
	public ResponseEntity<Object> handleAll(Exception ex, WebRequest request) {
		ResponseStatus status = AnnotatedElementUtils.findMergedAnnotation(ex.getClass(), ResponseStatus.class);
		ApiError apiError = new ApiError(Instant.now().toString(), status.value().value(), status.value().getReasonPhrase(), ex.getLocalizedMessage(),
				((ServletWebRequest)request).getRequest().getRequestURI());
		return new ResponseEntity<Object>(apiError, new HttpHeaders(), status.value());
	}

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", Instant.now().toString());
        body.put("status", status.value());

        BindingResult result = ex.getBindingResult();
		if (result == null) {
			body.put("message", ex.getMessage());
			return new ResponseEntity<>(body, headers, status);
		}
		if (result.hasErrors()) {
			body.put("errors", result.getAllErrors());
			body.put("message", "Validation failed for object='" + result.getObjectName()
					+ "'. Error count: " + result.getErrorCount());
		}
		else {
			body.put("message", "No errors");
		}
		
		body.put("path", ((ServletWebRequest)request).getRequest().getRequestURI());
        
        return new ResponseEntity<>(body, headers, status);
	}
}
