package com.gientech.practice.component;

import lombok.extern.slf4j.Slf4j;
import okhttp3.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

@Slf4j
@Component
public class MyOkHttpClient {
	@Autowired
	private OkHttpClient okHttpClient;

	/**
	 * get 请求
	 *
	 * @param url 请求url地址
	 * @return string
	 */
	public String doGet(String url) {
		Request request = new Request.Builder().url(url).build();
		return execute(request);
	}

	/**
	 * post 请求
	 *
	 * @param url    请求url地址
	 * @param params 请求参数 map
	 * @return string
	 */
	public String doPost(String url, Map<String, String> params) {
		FormBody.Builder builder = new FormBody.Builder();
		if (params != null && params.keySet().size() > 0) {
			for (String key : params.keySet()) {
				builder.add(key, params.get(key));
			}
		}
		Request request = new Request.Builder().url(url).post(builder.build()).build();
		return execute(request);
	}

	private String execute(Request request) {
		Response response = null;
		try {
			response = okHttpClient.newCall(request).execute();
			if (response.isSuccessful()) {
				return response.body().string();
			}
		} catch (Exception e) {
			log.error(e.getMessage());
		} finally {
			if (response != null) {
				response.close();
			}
		}
		return "";
	}
}
