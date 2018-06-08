package com.ict.human.bbs.security;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.support.RequestDataValueProcessor;

@Component
public class CSRFRequestDataValueProcessor implements RequestDataValueProcessor {

	@Override
	public String processAction(HttpServletRequest request, String action, String httpMethod) {
		
		return null;
	}

	@Override
	public String processFormFieldValue(HttpServletRequest request, String name, String value, String type) {
		
		return null;
	}

	@Override
	public Map<String, String> getExtraHiddenFields(HttpServletRequest request) {
		HashMap<String, String> hiddenFields = new HashMap<>();
		hiddenFields.put("CSRF_TOKEN", CSRFManager.getSessionToken(request.getSession()));
		return hiddenFields;
	}

	@Override
	public String processUrl(HttpServletRequest request, String url) {
		
		return null;
	}

}
