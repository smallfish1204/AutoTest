package com.course.utils;

import com.course.model.InterfaceName;

import java.util.ResourceBundle;

public class ConfigFile {

    private static ResourceBundle bundle = ResourceBundle.getBundle("application");

    public static String getUrl(InterfaceName interfaceName) {
        String address = bundle.getString("test.url");
        String uri = null;
        String testUrl;
        if (interfaceName == InterfaceName.ADDUSER) {
            uri = bundle.getString("addUserInfo.uri");
        }
        if (interfaceName == InterfaceName.GETUSERINFO) {
            uri = bundle.getString("getUserInfo.uri");
        }
        if (interfaceName == InterfaceName.GETUSERLIST) {
            uri = bundle.getString("getUserList.uri");
        }
        if (interfaceName == InterfaceName.LOGIN) {
            uri = bundle.getString("login.uri");
        }
        if (interfaceName == InterfaceName.UPDATEUSERINFO) {
            uri = bundle.getString("updateUserInfo.uri");
        }
        testUrl = address + uri;
        return testUrl;
    }
}
