package org.ramekin.model;

import java.util.Map;
import java.util.Set;

public class MixDetail {

    public class Obj {
        public class ChartData {
            private String pacToGrid;
            private String ppv;
            private String sysOut;
            private String pdischarge;
            private String pacToUser;

            public String getPacToGrid() {
                return pacToGrid;
            }

            public String getPpv() {
                return ppv;
            }

            public String getSysOut() {
                return sysOut;
            }

            public String getPdischarge() {
                return pdischarge;
            }

            public String getPacToUser() {
                return pacToUser;
            }
        }

        private String eChargeToday;
        private String echargeToat;
        private String photovoltaic;
        private String eCharge;
        private String eAcCharge;
        private String unit2;
        private String elocalLoad;
        private String etouser;
        private Map<String, ChartData> chartData;
        private String ratio2;
        private String unit;
        private String ratio1;
        private String echarge1;
        private String eChargeToday2;
        private String ratio4;
        private String ratio3;
        private String ratio6;
        private String eChargeToday1;
        private String ratio5;
    }

    private float deviceType;
    private String msg;
    private float result;
    private float dtc;
    private float haveMeter;
    private Obj obj;
    private float normalPower;
    private String model;


    public float getDeviceType() {
        return deviceType;
    }

    public String getMsg() {
        return msg;
    }

    public float getResult() {
        return result;
    }

    public float getDtc() {
        return dtc;
    }

    public float getHaveMeter() {
        return haveMeter;
    }

    public Obj getObj() {
        return obj;
    }

    public float getNormalPower() {
        return normalPower;
    }

    public String getModel() {
        return model;
    }

    public String getEChargeToday() {
        return obj.eChargeToday;
    }

    public String getEchargeToat() {
        return obj.echargeToat;
    }

    public String getPhotovoltaic() {
        return obj.photovoltaic;
    }

    public String getECharge() {
        return obj.eCharge;
    }

    public String getEAcCharge() {
        return obj.eAcCharge;
    }

    public String getUnit2() {
        return obj.unit2;
    }

    public String getElocalLoad() {
        return obj.elocalLoad;
    }

    public String getEtouser() {
        return obj.etouser;
    }

    public Map<String, Obj.ChartData> getChartData() {
        return obj.chartData;
    }

    public Set<String> getChartDataKeys() {
        return obj.chartData.keySet();
    }

    public String getRatio2() {
        return obj.ratio2;
    }

    public String getUnit() {
        return obj.unit;
    }

    public String getRatio1() {
        return obj.ratio1;
    }

    public String getEcharge1() {
        return obj.echarge1;
    }

    public String getEChargeToday2() {
        return obj.eChargeToday2;
    }

    public String getRatio4() {
        return obj.ratio4;
    }

    public String getRatio3() {
        return obj.ratio3;
    }

    public String getRatio6() {
        return obj.ratio6;
    }

    public String getEChargeToday1() {
        return obj.eChargeToday1;
    }

    public String getRatio5() {
        return obj.ratio5;
    }

    @Override
    public String toString() {
        return "There are " + getChartDataKeys().size() + " data entries for the given date";
    }
}
