package com.facilitydoor.app.facilitydoor.Models;

import java.util.List;

/**
 * Created by Shubham Chaudhary on 6/10/2016.
 */
public class ServiceExpand {
    String service__id;
    String service_name;
    // String Image;
    List<ServiceCategoryExpand> serviceCategoryExpands;


    public String getService__id() {
        return service__id;
    }

    public void setService__id(String service__id) {
        this.service__id = service__id;
    }

    public String getService_name() {
        return service_name;
    }

    public void setService_name(String service_name) {
        this.service_name = service_name;
    }

    public List<ServiceCategoryExpand> getServiceCategoryExpands() {
        return serviceCategoryExpands;
    }

    public void setServiceCategoryExpands(List<ServiceCategoryExpand> serviceCategoryExpands) {
        this.serviceCategoryExpands = serviceCategoryExpands;
    }
}
