package org.ramekin.model;

public class PlantList {

    private Back back;

    private class Back {
        private class TotalData {
            private String CO2Sum;
            private String currentPowerSum;
            private boolean isHaveStorage;
            private String todayEnergySum;
            private String eTotalMoneyText;
            private String totalEnergySum;
        }

        private class Data {
            private boolean isHaveStorage;
            private String currentPower;
            private String totalEnergy;
            private String plantMoneyText;
            private String plantId;
            private String todayEnergy;
            private String plantName;
        }

        private TotalData totalData;
        private Data[] data;
        private boolean success;
    }

    public String getCO2Sum() {
        return back.totalData.CO2Sum;
    }

    public String getCurrentPowerSum() {
        return back.totalData.currentPowerSum;
    }

    public boolean isHaveStorage() {
        return back.totalData.isHaveStorage;
    }

    public String getTodayEnergySum() {
        return back.totalData.todayEnergySum;
    }

    public String geteTotalMoneyText() {
        return back.totalData.eTotalMoneyText;
    }

    public String getTotalEnergySum() {
        return back.totalData.totalEnergySum;
    }

    public boolean isHaveStorage(final int index) {
        return back.data[index].isHaveStorage;
    }

    public String getCurrentPower(final int index) {
        return back.data[index].currentPower;
    }

    public String getTotalEnergy(final int index) {
        return back.data[index].totalEnergy;
    }

    public String getPlantMoneyText(final int index) {
        return back.data[index].plantMoneyText;
    }

    public String getPlantId(final int index) {
        return back.data[index].plantId;
    }

    public String getTodayEnergy(final int index) {
        return back.data[index].todayEnergy;
    }

    public String getPlantName(final int index) {
        return back.data[index].plantName;
    }

    public int getPlantCount() {
        return back.data.length;
    }

    public boolean isSuccess() {
        return back.success;
    }

    @Override
    public String toString() {
        return "You have " + getPlantCount() + " plant(s) installed";
    }
}
