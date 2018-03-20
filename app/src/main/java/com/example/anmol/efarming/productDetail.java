package com.example.anmol.efarming;

/**
 * Created by MiLaN on 17-03-2018.
 */

public class productDetail {
    public String farmername;
    public String cropgroup;
    public String cropname;
    public String quantity;
    public String imageurl;
    public String description;
    public String price ;

    public productDetail(String farmername,String cropname , String cropgroup,String description ,String quantity,String price,String imageurl)
    {
        this.cropgroup = cropgroup;
        this.cropname = cropname;
        this.farmername = farmername;
        this.imageurl = imageurl;
        this.quantity = quantity;
        this.description = description;
        this.price = price;
    }
    productDetail()
    {

    }
    public String getFarmername() {
        return farmername;
    }

    public void setFarmername(String farmername) {
        this.farmername = farmername;
    }

    public String getCropgroup() {
        return cropgroup;
    }

    public void setCropgroup(String cropgroup) {
        this.cropgroup = cropgroup;
    }

    public String getCropname() {
        return cropname;
    }

    public void setCropname(String cropname) {
        this.cropname = cropname;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getImageurl() {
        return imageurl;
    }

    public void setImageurl(String imageurl) {
        this.imageurl = imageurl;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

}
