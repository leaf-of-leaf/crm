package com.briup.crm.bean;

/**
 * @author kj
 * @Date 2020/4/3 10:36
 * @Version 1.0
 */
public class CustomerConstitute {

    private String name;
    private Float y;
    private String drilldown;

    public CustomerConstitute(String name, Float y, String drilldown) {
        this.name = name;
        this.y = y;
        this.drilldown = drilldown;
    }

    public CustomerConstitute() {
    }

    @Override
    public String toString() {
        return "CustomerConstitute{" +
                "name='" + name + '\'' +
                ", y=" + y +
                ", drilldown='" + drilldown + '\'' +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Float getY() {
        return y;
    }

    public void setY(Float y) {
        this.y = y;
    }

    public String getDrilldown() {
        return drilldown;
    }

    public void setDrilldown(String drilldown) {
        this.drilldown = drilldown;
    }
}
