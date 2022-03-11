import cn.ts.gms.ErrorInfo;
import cn.ts.gms.ResultCallback;
import cn.ts.gms.bean.LoginParams;
import cn.ts.gms.channel.GMSChannel;
import cn.ts.gms.channel.GMSChannelListener;
import cn.ts.gms.channel.MemberLeftReason;
import cn.ts.gms.channel.bean.GMSChannelAttribute;
import cn.ts.gms.channel.bean.GMSChannelMember;
import cn.ts.gms.client.GMSClient;
import cn.ts.gms.client.GMSClientListener;
import cn.ts.gms.connect.bean.EnvConfig;
import cn.ts.gms.connect.bean.GMSMessage;
import cn.ts.gms.connect.bean.Options;
import cn.ts.gms.user.GMSAttributeWithState;
import cn.ts.gms.utils.EmptyUtils;
import cn.ts.gms.utils.EncryptUtils;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Test {

    public static String TEST_APPID = "7zVD31N8WqmP0xyE";
    public static String TEST_APPKEY = "e6cd8cec107a4b5abee9cfae52f15457";

    public static void main(String[] args) {
        loginWithUserId("1111");
//        loginWithUserId("1112");
//        loginWithUserId("1113");
//        loginWithUserId("1114");
//        loginWithUserId("1115");
//        loginWithUserId("1116");
//        loginWithUserId("1117");
//        loginWithUserId("1118");
//        loginWithUserId("1119");



//        GMSClient instance = GMSClient.createInstance("7zVD31N8WqmP0xyE", new Options(new EnvConfig("https://api-dev.rzrtc.com")), new GMSClientListener() {
//            @Override
//            public void onConnectionStateChanged(int i, int i1) {
//                System.out.println("onConnectionStateChanged  " + i + "=====" + i1);
//
//            }
//
//            @Override
//            public void onMessageReceived(GMSMessage gmsMessage, String s) {
//
//            }
//
//            @Override
//            public void onPeersOnlineStatusChanged(Map<String, Integer> map) {
//
//
//            }
//
//            @Override
//            public void onTokenExpired() {
//
//            }
//
//            @Override
//            public void onPeersUserAttributesChanged(Map<String, ? extends List<GMSAttributeWithState>> map) {
//
//            }
//        });
//
//        long timestamp = System.currentTimeMillis();
//        String token = createToke("7zVD31N8WqmP0xyE", "1113", timestamp, "e6cd8cec107a4b5abee9cfae52f15457");
//
//        instance.login(new LoginParams(token, "1113", timestamp), new ResultCallback<Void>() {
//            @Override
//            public void onSuccess(Void unused) {
//                System.out.println("login success");
//            }
//
//            @Override
//            public void onFailure(ErrorInfo errorInfo) {
//
//            }
//        });
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
        String params = "appId=" + appId + "&timestamp=" + timestamp + "&userId=" +
                userId + appkey;
        return EncryptUtils.MD5(params);
    }

    private static void loginWithUserId(String userId) {
        long timestamp = System.currentTimeMillis();
        String token = createToke(TEST_APPID, userId, timestamp, TEST_APPKEY);
        GMSClient instance = createClientInstance2(userId);
        assert token != null;
        instance.login(
                new LoginParams(token, userId, timestamp), new ResultCallback<Void>() {
                    @Override
                    public void onSuccess(@Nullable Void unused) {
                        ArrayList channel = new ArrayList<>();
                        channel.add("9999");
                        GMSChannel channel1 = instance.createChannel("9999", new GMSChannelListener() {
                            @Override
                            public void onAttributesUpdated(@NotNull List<GMSChannelAttribute> list) {

                            }


                            @Override
                            public void onMemberJoined(@NotNull GMSChannelMember gmsChannelMember) {

                            }

                            @Override
                            public void onMemberLeft(@NotNull GMSChannelMember gmsChannelMember, @NotNull MemberLeftReason memberLeftReason) {

                            }

                            @Override
                            public void onMessageReceived(@NotNull GMSMessage gmsMessage, @NotNull GMSChannelMember gmsChannelMember) {

                            }


                            @Override
                            public void onMemberCountUpdated(int i) {

                            }
                        });


                        channel1.join(new ResultCallback<Void>() {
                            @Override
                            public void onSuccess(@Nullable Void unused) {


                                    channel1.leave(new ResultCallback<Void>() {
                                        @Override
                                        public void onSuccess(@Nullable Void unused) {
                                            instance.getChannelMemberCount(channel, new ResultCallback<Map<String, Integer>>() {
                                                @Override
                                                public void onSuccess(@Nullable Map<String, Integer> stringIntegerMap) {
                                                    System.out.println(stringIntegerMap);

                                                }

                                                @Override
                                                public void onFailure(@NotNull ErrorInfo errorInfo) {
                                                    System.out.println(errorInfo);
                                                }
                                            });
                                        }

                                        @Override
                                        public void onFailure(@NotNull ErrorInfo errorInfo) {

                                        }
                                    });

                            }

                            @Override
                            public void onFailure(@NotNull ErrorInfo errorInfo) {

                            }
                        });

                    }

                    @Override
                    public void onFailure(@NotNull ErrorInfo errorInfo) {

                    }
                }
        );
    }


    private static GMSClient createClientInstance2(String userId) {
        return GMSClient.createInstance(
//            applicationContext,
                TEST_APPID,
                new Options(new EnvConfig("https://api-dev.rzrtc.com","https://www.baidu.com")),
                new GMSClientListener() {
                    @Override
                    public void onConnectionStateChanged(int i, int i1) {
                        System.out.println(userId + " code " + i + "reason " + i1);
                    }

                    @Override
                    public void onMessageReceived(@Nullable GMSMessage gmsMessage, @NotNull String s) {

                    }

                    @Override
                    public void onPeersOnlineStatusChanged(@NotNull Map<String, Integer> map) {

                    }

                    @Override
                    public void onTokenExpired() {

                    }

                    @Override
                    public void onPeersUserAttributesChanged(@NotNull Map<String, ? extends List<GMSAttributeWithState>> map) {

                    }
                });

    }

}
