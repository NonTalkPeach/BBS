package auth.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.Verification;

import java.util.Date;

public class JWTTokenUtil {
    /**
     * 单次登录有效时间为1个小时。
     */
    private static final long EXPIRE_TIME = 3600000;
    /**
     * 系统密钥
     */
    private static final String SECRET = "NonSpeakPeach";

    public static String getToken(String username, String userCode){
        /** 密钥 用户code + 系统密钥 */
        String secretKey = SECRET + userCode;

        /** 过期时间 */
        Date expireDate = new Date(System.currentTimeMillis() + EXPIRE_TIME);

        String token = JWT.create()
                /** 存放内容 */
                .withClaim("username", username)
                .withExpiresAt(expireDate)
                .sign(Algorithm.HMAC512(secretKey));
                return token;
    }

    public static boolean verifyToken(String token, String userCode){
        String secretKey = SECRET + userCode;
        Verification verification = JWT.require(Algorithm.HMAC512(secretKey));
        try{
            verification.build().verify(token);
            return true;
        }catch (Exception e){
            return false;
        }
    }
}
