package com.advantech.recruit.utils;

public class PositionUtils {

    public static String switchPosition(String code){
        switch (code){
            case "0":
                return "前端开发";
            case "1":
                return "java开发";
            case "2":
                return "云平台开发工程师";
            case "3":
                return "软件测试";
            case "4":
               return "软件产品经理";
            case "5":
                return "云平台应用工程师";
            default:
                return  "java开发";
        }
    }


}
