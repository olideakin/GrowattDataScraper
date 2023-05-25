package org.ramekin.model;

public class Login {

    private Back back;

    private class Back {
        private class Data {
            private String plantId;
            private String plantName;
        }

        private class User {
            private String agentCode;
            private String phoneNum;
            private String cpowerToken;
            private int type;
            private int vipPoints;
            private boolean approved;
            private String password;
            private int rightlevel;
            private int isAgent;
            private String userIconPath;
            private String serverUrl;
            private String[] inverterList;
            private int id;
            private String lat;
            private String area;
            private String lng;
            private int kind;
            private String nickName;
            private int parentUserId;
            private int timeZone;
            private String accountName;
            private String counrty;
            private String [] inverterGroup;
            private String customerCode;
            private String registerType;
            private boolean enabled;
            private String lastLoginIp;
            private String userLanguage;
            private int isBigCustomer;
            private String uid;
            private int userDeviceType;
            private String installerEnable;
            private String appType;
            private String company;
            private int isValiPhone;
            private String appAlias;
            private String email;
            private String createDate; // Format "YYYY-MM-DD HH:MM:SS"
            private String activeName;
            private int roleId;
            private boolean smsNotice;
            private  String wxOpenid;
            private String noticeType;
            private int isPhoneNumReg;
            private int isValiEmail;
            private String token;
            private String lastLoginTime; // Format "YYYY-MM-DD HH:MM:SS"
            private boolean mailNotice;
            private String accountNameOss;
            private String[] dataAcqList;
            private String distributorEnable;
            private int codeIndex;
        }

        String msg;
        Data[] data;
        int deviceCount;
        int isOpenDeviceList;
        int quality;
        boolean isCheckUserAuth;
        boolean isEicUserAddSmartDevice;
        int isOpenDeviceParams;
        boolean isViewDeviceInfo;
        boolean success;
        int service;
        User user;
        int isOpenSmartFamily;
        int app_code;
    }

    public String getMsg() {
        return back.msg;
    }

    public String getPlantId() {
        return back.data[0].plantId;
    }

    public String getPlantName() {
        return back.data[0].plantName;
    }

    public int getDeviceCount() {
        return back.deviceCount;
    }

    public int getIsOpenDeviceList() {
        return back.isOpenDeviceList;
    }

    public int getQuality() {
        return back.quality;
    }

    public boolean isCheckUserAuth() {
        return back.isCheckUserAuth;
    }

    public boolean isEicUserAddSmartDevice() {
        return back.isEicUserAddSmartDevice;
    }

    public int getIsOpenDeviceParams() {
        return back.isOpenDeviceParams;
    }

    public boolean isViewDeviceInfo() {
        return back.isViewDeviceInfo;
    }

    public boolean isSuccess() {
        return back.success;
    }

    public int getService() {
        return back.service;
    }

    public String getAgentCode() {
        return back.user.agentCode;
    }

    public String getPhoneNum() {
        return back.user.phoneNum;
    }

    public String getCpowerToken() {
        return back.user.cpowerToken;
    }

    public int getType() {
        return back.user.type;
    }

    public int getVipPoints() {
        return back.user.vipPoints;
    }

    public boolean isApproved() {
        return back.user.approved;
    }

    public String getPassword() {
        return back.user.password;
    }

    public int getRightlevel() {
        return back.user.rightlevel;
    }

    public int getIsAgent() {
        return back.user.isAgent;
    }

    public String getUserIconPath() {
        return back.user.userIconPath;
    }

    public String getServerUrl() {
        return back.user.serverUrl;
    }

    public String[] getInverterList() {
        return back.user.inverterList;
    }

    public int getId() {
        return back.user.id;
    }

    public String getLat() {
        return back.user.lat;
    }

    public String getArea() {
        return back.user.area;
    }

    public String getLng() {
        return back.user.lng;
    }

    public int getKind() {
        return back.user.kind;
    }

    public String getNickName() {
        return back.user.nickName;
    }

    public int getParentUserId() {
        return back.user.parentUserId;
    }

    public int getTimeZone() {
        return back.user.timeZone;
    }

    public String getAccountName() {
        return back.user.accountName;
    }

    public String getCountry() {
        return back.user.counrty;
    }

    public String[] getInverterGroup() {
        return back.user.inverterGroup;
    }

    public String getCustomerCode() {
        return back.user.customerCode;
    }

    public String getRegisterType() {
        return back.user.registerType;
    }

    public boolean isEnabled() {
        return back.user.enabled;
    }

    public String getLastLoginIp() {
        return back.user.lastLoginIp;
    }

    public String getUserLanguage() {
        return back.user.userLanguage;
    }

    public int getIsBigCustomer() {
        return back.user.isBigCustomer;
    }

    public String getUid() {
        return back.user.uid;
    }

    public int getUserDeviceType() {
        return back.user.userDeviceType;
    }

    public String getInstallerEnable() {
        return back.user.installerEnable;
    }

    public String getAppType() {
        return back.user.appType;
    }

    public String getCompany() {
        return back.user.company;
    }

    public int getIsValiPhone() {
        return back.user.isValiPhone;
    }

    public String getAppAlias() {
        return back.user.appAlias;
    }

    public String getEmail() {
        return back.user.email;
    }

    public String getCreateDate() {
        return back.user.createDate;
    }

    public String getActiveName() {
        return back.user.activeName;
    }

    public int getRoleId() {
        return back.user.roleId;
    }

    public boolean isSmsNotice() {
        return back.user.smsNotice;
    }

    public String getWxOpenid() {
        return back.user.wxOpenid;
    }

    public String getNoticeType() {
        return back.user.noticeType;
    }

    public int getIsPhoneNumReg() {
        return back.user.isPhoneNumReg;
    }

    public int getIsValiEmail() {
        return back.user.isValiEmail;
    }

    public String getToken() {
        return back.user.token;
    }

    public String getLastLoginTime() {
        return back.user.lastLoginTime;
    }

    public boolean isMailNotice() {
        return back.user.mailNotice;
    }

    public String getAccountNameOss() {
        return back.user.accountNameOss;
    }

    public String[] getDataAcqList() {
        return back.user.dataAcqList;
    }

    public String getDistributorEnable() {
        return back.user.distributorEnable;
    }

    public int getCodeIndex() {
        return back.user.codeIndex;
    }

    public int getIsOpenSmartFamily() {
        return back.isOpenSmartFamily;
    }

    public int getApp_code() {
        return back.app_code;
    }
}
