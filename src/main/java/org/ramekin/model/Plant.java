package org.ramekin.model;

public class Plant {

    private class Device {
        private String deviceType;
        private String pCharge;
        private String eChargeToday;
        private boolean lost;
        private String datalogSn;
        private String location;
        private String deviceSn;
        private String deviceAilas;
        private float deviceStatus;
        private String energy;
        private String capacity;
    }
    private float isHaveOptimizer;
    private String totalEnergy;
    private float nominal_Power;
    private String totalMoneyText;
    private float optimizerType;
    private String storageTodayPpv;
    private String storagePuser;
    private String Co2Reduction;
    private String useEnergy;
    private float nominalPower;
    private String isHavePumper;
    private String ammeterType;
    private String invTodayPpv;
    private String plantMoneyText;
    private Device[] deviceList;
    private String todayEnergy;
    private String todayDischarge;
    private String storagePgrid;

    public float getIsHaveOptimizer() {
        return isHaveOptimizer;
    }

    public String getTotalEnergy() {
        return totalEnergy;
    }

    public float getNominal_Power() {
        return nominal_Power;
    }

    public String getTotalMoneyText() {
        return totalMoneyText;
    }

    public float getOptimizerType() {
        return optimizerType;
    }

    public String getStorageTodayPpv() {
        return storageTodayPpv;
    }

    public String getStoragePuser() {
        return storagePuser;
    }

    public String getCo2Reduction() {
        return Co2Reduction;
    }

    public String getUseEnergy() {
        return useEnergy;
    }

    public float getNominalPower() {
        return nominalPower;
    }

    public String getIsHavePumper() {
        return isHavePumper;
    }

    public String getAmmeterType() {
        return ammeterType;
    }

    public String getInvTodayPpv() {
        return invTodayPpv;
    }

    public String getPlantMoneyText() {
        return plantMoneyText;
    }

    public String getTodayEnergy() {
        return todayEnergy;
    }

    public String getTodayDischarge() {
        return todayDischarge;
    }

    public String getStoragePgrid() {
        return storagePgrid;
    }

    public String getDeviceType(final int index) {
        return deviceList[index].deviceType;
    }

    public String getPCharge(final int index) {
        return deviceList[index].pCharge;
    }

    public String getEChargeToday(final int index) {
        return deviceList[index].eChargeToday;
    }

    public boolean getLost(final int index) {
        return deviceList[index].lost;
    }

    public String getDatalogSn(final int index) {
        return deviceList[index].datalogSn;
    }

    public String getLocation(final int index) {
        return deviceList[index].location;
    }

    public String getDeviceSn(final int index) {
        return deviceList[index].deviceSn;
    }

    public String getDeviceAilas(final int index) {
        return deviceList[index].deviceAilas;
    }

    public float getDeviceStatus(final int index) {
        return deviceList[index].deviceStatus;
    }

    public String getEnergy(final int index) {
        return deviceList[index].energy;
    }

    public String getCapacity(final int index) {
        return deviceList[index].capacity;
    }

    public int getDeviceCount() {
        return deviceList.length;
    }

    @Override
    public String toString() {
        return "You have " + getDeviceCount() + " devices(s) in this plant, your first device is of type " + getDeviceType(0);
    }
}
