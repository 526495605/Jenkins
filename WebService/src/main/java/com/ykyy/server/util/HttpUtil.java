package com.ykyy.server.util;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.List;
import java.util.Map;

public class HttpUtil {
	//public static CloseableHttpClient httpclient = HttpClients.createDefault();
	public static CloseableHttpClient httpclient = new DefaultHttpClient();

	public Map<String,String> headers;

	public Map<String, String> getHeaders() {
		return headers;
	}

	public void setHeaders(Map<String, String> headers) {
		this.headers = headers;
	}

	public static String rawGet(String url) {
        Logging.info("http get url=" + url);
        HttpGet httpGet = new HttpGet(url);
        CloseableHttpResponse response = null;
        try {
            response = httpclient.execute(httpGet);
            Logging.info(response.getStatusLine());
            HttpEntity entity = response.getEntity();
            if (response.getStatusLine().getStatusCode() != 200) {
                return null;
            }
            String result = EntityUtils.toString(entity);
            Logging.info("http response = " + result);
            EntityUtils.consume(entity);
            return result;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                response.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

	public static String rawGet(String url, Map<String,String> headers) {
		Logging.info("http get url=" + url);
		int retcode = -1;
		String result = null;
		HttpGet httpGet = new HttpGet(url);
		for( String key : headers.keySet() ) {
			httpGet.setHeader(key, headers.get(key));
		}
		CloseableHttpResponse response = null;
		try {
			response = httpclient.execute(httpGet);
			//Logging.info(response.getStatusLine());
			HttpEntity entity = response.getEntity();
			retcode = response.getStatusLine().getStatusCode();
			result = EntityUtils.toString(entity);
			//Logging.info("http response = " + result);
			EntityUtils.consume(entity);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			Logging.info("http get url = " + url + ", retcode = " + retcode + ", result = [" + result + "]");
			try {
				response.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return result;
	}

    public static String rawPost(String url, List<NameValuePair> nvps) {
        HttpPost httpPost = new HttpPost(url);
        CloseableHttpResponse response = null;
        try {
            httpPost.setEntity(new UrlEncodedFormEntity(nvps));
            response = httpclient.execute(httpPost);
            HttpEntity entity = response.getEntity();
            if (response.getStatusLine().getStatusCode() != 200) {
                return null;
            }
            String result = EntityUtils.toString(entity);
            Logging.info("http response = " + result);
            EntityUtils.consume(entity);
            return result;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                response.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return null;
    }

	public static boolean get(String url) {
		Logging.info("httpget url=" + url);
		HttpGet httpGet = new HttpGet(url);
		CloseableHttpResponse response = null;
		// The underlying HTTP connection is still held by the response object
		// to allow the response content to be streamed directly from the network socket.
		// In order to ensure correct deallocation of system resources
		// the user MUST call CloseableHttpResponse#close() from a finally clause.
		// Please note that if response content is not fully consumed the underlying
		// connection cannot be safely re-used and will be shut down and discarded
		// by the connection manager. 
		try {
			response = httpclient.execute(httpGet);
		    Logging.info(response.getStatusLine());
		    HttpEntity entity = response.getEntity();
		    // do something useful with the response body
		    // and ensure it is fully consumed
		    if (response.getStatusLine().getStatusCode() != 200) {
		    	return false;
		    }
		    String result = EntityUtils.toString(entity);
		    Logging.info(result);
		    String[] strs = result.split(",");
		    if (strs.length > 0) {
		    	int resCode = Integer.parseInt(strs[0]);
		    	if (resCode == 0) return true;
		    }
		    EntityUtils.consume(entity);
		    
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
		    try {
		    	response.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return false;
	}
	
	public static boolean post(String url, List<NameValuePair> nvps) {
		//HttpPost httpPost = new HttpPost("http://targethost/login");
		HttpPost httpPost = new HttpPost(url);
		/*
		List <NameValuePair> nvps = new ArrayList <NameValuePair>();
		nvps.add(new BasicNameValuePair("username", "vip"));
		nvps.add(new BasicNameValuePair("password", "secret"));
		*/
		CloseableHttpResponse response = null;

		try {
			httpPost.setEntity(new UrlEncodedFormEntity(nvps,"UTF-8"));

			response = httpclient.execute(httpPost);
		    System.out.println(response.getStatusLine());
		    HttpEntity entity = response.getEntity();
		    // do something useful with the response body
		    // and ensure it is fully consumed
		    EntityUtils.consume(entity);
		    if (response.getStatusLine().getStatusCode() == 200) {
		    	return true;
		    }
		    
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				response.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return false;
	}

	public static String doPost(String url, String body) {
		Logging.info("url = [" + url + "]; body = [" + body + "]");
		HttpPost httpPost = new HttpPost(url);
		httpPost.addHeader("Content-type","application/json; charset=utf-8");
		httpPost.setHeader("Accept", "application/json");
		httpPost.setEntity(new StringEntity(body, Charset.forName("UTF-8")));
		CloseableHttpResponse response = null;
		try {
			System.out.println("httpclient = " + httpclient);
			System.out.println("httpPost = " + httpPost);
			response = httpclient.execute(httpPost);
			HttpEntity entity = response.getEntity();
			String result = null;
			if (response.getStatusLine().getStatusCode() != 200) {
				result = EntityUtils.toString(entity);
				Logging.info("http code = " + response.getStatusLine().getStatusCode() + " ; http response = " + result);
				return null;
			}
			result = EntityUtils.toString(entity);
			Logging.info("http response = " + result);
			EntityUtils.consume(entity);
			return result;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}
