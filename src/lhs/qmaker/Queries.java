/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lhs.qmaker;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.apache.commons.codec.binary.Base64;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

/**
 *
 * @author jordan
 */
public class Queries {
    private static final String PHPURL = "http://qmaker.zxq.net/encodedQuery.php";
    public static JsonObject sendQuery(String query, boolean returns) {
        try {
            HttpClient httpclient = new DefaultHttpClient();
            String encoded = new String(Base64.encodeBase64(query.getBytes()));
            System.out.println(query);
            System.out.println(encoded);
            System.out.println(new String(Base64.decodeBase64(encoded)));
            HttpGet httpget = new HttpGet(PHPURL+"?query="+encoded);
            String st = EntityUtils.toString(httpclient.execute(httpget).getEntity());
            //System.out.println(st);
            if (returns) {
                JsonObject o = new JsonParser().parse(st).getAsJsonObject();
                //System.out.println(st);
                return o;
            } else {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
