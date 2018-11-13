package com.topjet.manage.domain.bean;


import com.topjet.manage.domain.model.BaseModel;

/**
 * Created by liyj on 2016/12/15.
 */
public class TruckLengthQueryBean extends BaseModel {

    private String displayName;

    private String valid;

    private String truckLength;

    public String getTruckLength() {
        return truckLength;
    }

    public void setTruckLength(String truckLength) {
        this.truckLength = truckLength;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getValid() {
        return valid;
    }

    public void setValid(String valid) {
        this.valid = valid;
    }
}
