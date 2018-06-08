package com.ict.human.bbs.security;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.servlet.support.RequestDataValueProcessor;

public class CSRFRequestDataValueProcessor implements RequestDataValueProcessor {

	@Override
	public String processAction(HttpServletRequest request, String action, String httpMethod) {
		System.out.println(action + " ---- 1"); // /human/write.bbs  --  폼에 있는 action을 받아옴
		System.out.println(httpMethod + " ---- 2"); // post  --  form에 있는 메소드 방식을 받아옴
		return action;
	}

	@Override
	public String processFormFieldValue(HttpServletRequest request, String name, String value, String type) {
		
		return value;
	}

	@Override
	public Map<String, String> getExtraHiddenFields(HttpServletRequest request) {
		HashMap<String, String> hiddenFields = new HashMap<>();
		hiddenFields.put("CSRF_TOKEN", CSRFManager.getSessionToken(request.getSession()));
		return hiddenFields;
	}

	@Override
	public String processUrl(HttpServletRequest request, String url) {
		System.out.println(url + " ---- 6 "); // /human/list.bbs?pageNum=1   -- 컨트롤러가 동작 후 돌아갈 뷰 
		return url;
	}

}
