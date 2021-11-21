package top.wemc.bakapi.test;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import top.wemc.bakaapi.utils.JsonUtils;

import java.io.IOException;

/**
 * @author WYH2004
 * @date 2021/11/20
 */
public class Main {
    public static void main(String[] args) throws IOException {
        JsonElement jsonElement =  JsonUtils.getJsonURL("https://qmsg.zendee.cn/send/2d8b6b5c84fece1a9508da1c0cb2275e");
//        System.out.println(jsonElement);
        JsonObject jsonObject = jsonElement.getAsJsonObject();
        System.out.println(jsonObject.get("success"));
        System.out.println(jsonObject.get("reason"));
        jsonElement.getAsBoolean();
        System.out.println("done");
    }
}
