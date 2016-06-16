package com.facilitydoor.app.facilitydoor.Models;

import java.util.List;

/**
 * Created by root on 10/6/16.
 */
public class TrendCategories {
    String id;
    String heading;
    List<TrendServices> services;
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getHeading() {
        return heading;
    }

    public void setHeading(String heading) {
        this.heading = heading;
    }

    public List<TrendServices> getServices() {
        return services;
    }

    public void setServices(List<TrendServices> services) {
        this.services = services;
    }


}
