import com.rz.gms.ErrorInfo;
import com.rz.gms.ResultCallback;
import com.rz.gms.bean.LoginParams;
import com.rz.gms.client.GMSClient;
import com.rz.gms.client.GMSClientListener;
import com.rz.gms.connect.bean.EnvConfig;
import com.rz.gms.connect.bean.GMSMessage;
import com.rz.gms.connect.bean.Options;
import com.rz.gms.user.GMSAttributeWithState;
import com.rz.gms.utils.EmptyUtils;
import com.rz.gms.utils.EncryptUtils;

import java.util.List;
import java.util.Map;

public class Test {
    public static void main(String[] args) {
        GMSClient instance = GMSClient.createInstance("7zVD31N8WqmP0xyE", new Options(new EnvConfig("https://api-dev.rzrtc.com")), new GMSClientListener() {
            @Override
            public void onConnectionStateChanged(int i, int i1) {
                System.out.println("onConnectionStateChanged  "+ i + "====="+i1);

            }

            @Override
            public void onMessageReceived(GMSMessage gmsMessage, String s) {

            }

            @Override
            public void onPeersOnlineStatusChanged(Map<String, Integer> map) {

            }

            @Override
            public void onTokenExpired() {

            }

            @Override
            public void onPeersUserAttributesChanged(Map<String, ? extends List<GMSAttributeWithState>> map) {

            }
        });

        long timestamp = System.currentTimeMillis();
        String token = createToke("7zVD31N8WqmP0xyE", "1113", timestamp, "e6cd8cec107a4b5abee9cfae52f15457");

        instance.login(new LoginParams(token, "1113", timestamp), new ResultCallback<Void>() {
            @Override
            public void onSuccess( Void unused) {
                System.out.println("login success");
            }

            @Override
            public void onFailure( ErrorInfo errorInfo) {

            }
        });
    }

    public static String createToke(String appId, String userId, long timestamp, String appkey) {
        if (EmptyUtils.isEmpty(appId)) {
            return null;
        }
        if (EmptyUtils.isEmpty(userId)) {
            return null;
        }
        if (EmptyUtils.isEmpty(appkey)) {
            return null;
        }
        String params = new StringBuffer("appId=").append(appId).append("&timestamp=").append(timestamp).append("&userId=").
                append(userId).append(appkey).toString();
        return EncryptUtils.MD5(params);
    }
}
