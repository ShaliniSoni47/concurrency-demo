package com.shalini.concurrent.utils;

import java.util.UUID;

import org.slf4j.MDC;
import org.springframework.util.StringUtils;

import com.shalini.concurrent.constants.ApplicationConstants;

public class CommonUtil {
	 private CommonUtil() {
	    }

	    public static void setRequestContext() {
	        String requestId = MDC.get(ApplicationConstants.REQUEST_ID);
	        if (StringUtils.isEmpty(requestId)) {
	            requestId = UUID.randomUUID().toString();
	        }
	        MDC.put(ApplicationConstants.REQUEST_ID, requestId);
	    }

	    public static void clearRequestContext() {
	        MDC.clear();
	    }
}
