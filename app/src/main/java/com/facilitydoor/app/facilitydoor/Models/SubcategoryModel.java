package com.facilitydoor.app.facilitydoor.Models;

import java.util.List;

/**
 * Created by root on 11/6/16.
 */
public class SubcategoryModel {

    String serviceId;
    String categoryId;
    List<ServicesSubcategory> servicesSubcategories;
    String categoryname;

    public String getCategoryname() {
        return categoryname;
    }

    public void setCategoryname(String categoryname) {
        this.categoryname = categoryname;
    }

    public String getServiceId() {
        return serviceId;
    }

    public void setServiceId(String serviceId) {
        this.serviceId = serviceId;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public List<ServicesSubcategory> getServicesSubcategories() {
        return servicesSubcategories;
    }

    public void setServicesSubcategories(List<ServicesSubcategory> servicesSubcategories) {
        this.servicesSubcategories = servicesSubcategories;
    }
}
