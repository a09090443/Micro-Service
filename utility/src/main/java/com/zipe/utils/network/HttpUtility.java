package com.zipe.utils.network;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * This class encapsulates methods for requesting a server via HTTP GET/POST and
 * provides methods for parsing response from the server.
 *
 * @author www.codejava.net
 *
 */
public class HttpUtility {

	/**
	 * Represents an HTTP connection
	 */
	private static HttpURLConnection httpConn;

	/**
	 * Makes an HTTP request using GET method to the specified URL.
	 *
	 * @param requestURL the URL of the remote server
	 * @return An HttpURLConnection object
	 * @throws IOException thrown if any I/O error occurred
	 */
	public static HttpURLConnection sendGetRequest(String requestURL) throws IOException {
		URL url = new URL(requestURL);
		httpConn = (HttpURLConnection) url.openConnection();
		httpConn.setUseCaches(false);

		httpConn.setDoInput(true); // true if we want to read server's response
		httpConn.setDoOutput(false); // false indicates this is a GET request

		return httpConn;
	}

	/**
	 * Makes an HTTP request using POST method to the specified URL.
	 *
	 * @param requestURL the URL of the remote server
	 * @param params A map containing POST data in form of key-value pairs
	 * @return An HttpURLConnection object
	 * @throws IOException thrown if any I/O error occurred
	 */
	public static HttpURLConnection sendPostRequest(String requestURL, Map<String, String> params) throws IOException {

		URL url = new URL(requestURL);
		httpConn = (HttpURLConnection) url.openConnection();
		httpConn.setRequestMethod("POST");
		httpConn.setUseCaches(false);

		httpConn.setDoInput(true); // true indicates the server returns response

		setRequestParams(params);
		return httpConn;
	}

	/**
	 * Makes an HTTP request using DELTE method to the specified URL.
	 *
	 * @param requestURL the URL of the remote server
	 * @param params A map containing POST data in form of key-value pairs
	 * @return An HttpURLConnection object
	 * @throws IOException thrown if any I/O error occurred
	 */
	public static HttpURLConnection sendDeleteRequest(String requestURL, Map<String, String> params) throws IOException {

		URL url = new URL(requestURL);
		httpConn = (HttpURLConnection) url.openConnection();
		httpConn.setRequestMethod("DELETE");
		httpConn.setUseCaches(false);

		httpConn.setDoInput(true); // true indicates the server returns response
		httpConn.setRequestProperty(
				"Content-Type", "application/x-www-form-urlencoded");

		setRequestParams(params);

		return httpConn;
	}

	/**
	 * Makes an HTTP request using PUT method to the specified URL.
	 *
	 * @param requestURL the URL of the remote server
	 * @param params A map containing POST data in form of key-value pairs
	 * @return An HttpURLConnection object
	 * @throws IOException thrown if any I/O error occurred
	 */
	public static HttpURLConnection sendPutRequest(String requestURL, Map<String, String> params) throws IOException {

		URL url = new URL(requestURL);
		httpConn = (HttpURLConnection) url.openConnection();
		httpConn.setRequestMethod("PUT");
		httpConn.setUseCaches(false);

		httpConn.setDoInput(true); // true if we want to read server's response
		httpConn.setDoInput(true); // true indicates the server returns response
		httpConn.setRequestProperty(
				"Content-Type", "application/x-www-form-urlencoded");

		setRequestParams(params);

		return httpConn;
	}

	/**
	 * Returns only one line from the server's response. This method should be used
	 * if the server returns only a single line of String.
	 *
	 * @return a String of the server's response
	 * @throws IOException thrown if any I/O error occurred
	 */
	public static String readSingleLineRespone() throws IOException {
		InputStream inputStream = null;
		if (httpConn != null) {
			inputStream = httpConn.getInputStream();
		} else {
			throw new IOException("Connection is not established.");
		}
		BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));

		String response = reader.readLine();
		reader.close();

		
		return response;
	}

	/**
	 * Returns an array of lines from the server's response. This method should be
	 * used if the server returns multiple lines of String.
	 *
	 * @return an array of Strings of the server's response
	 * @throws IOException thrown if any I/O error occurred
	 */
	public static String[] readMultipleLinesRespone() throws IOException {
		InputStream inputStream = null;
		if (httpConn != null) {
			inputStream = httpConn.getInputStream();
		} else {
			throw new IOException("Connection is not established.");
		}

		BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
		List<String> response = new ArrayList<String>();

		String line = "";
		while ((line = reader.readLine()) != null) {
			response.add(line);
		}
		reader.close();

		return (String[]) response.toArray(new String[0]);
	}

	/**
	 * Closes the connection if opened
	 */
	public static void disconnect() {
		if (httpConn != null) {
			httpConn.disconnect();
		}
	}

	private static void setRequestParams(Map<String, String> params) throws IOException {
		StringBuffer requestParams = new StringBuffer();

		// creates the params string, encode them using URLEncoder
		Iterator<String> paramIterator = params.keySet().iterator();
		while (paramIterator.hasNext()) {
			String key = paramIterator.next();
			String value = params.get(key);
			requestParams.append(URLEncoder.encode(key, "UTF-8"));
			requestParams.append("=").append(URLEncoder.encode(value, "UTF-8"));
			requestParams.append("&");
		}
		// sends DELETE data
		OutputStreamWriter writer = new OutputStreamWriter(httpConn.getOutputStream());
		writer.write(requestParams.toString());
		writer.flush();
	}
}
