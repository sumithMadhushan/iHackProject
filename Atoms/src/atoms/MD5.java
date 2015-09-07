
package atoms;

import java.security.MessageDigest;

public class MD5 {
    public static  String md5(String s){
        try {
            MessageDigest digs=MessageDigest.getInstance("MD5");
            digs.update(s.getBytes("UTF8"));
            String str=new String(digs.digest());
            return str;
        } catch (Exception ex) {
            return "";
        } 
    } 
}
