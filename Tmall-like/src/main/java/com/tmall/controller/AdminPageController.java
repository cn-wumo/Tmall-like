package com.tmall.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class AdminPageController {
    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public String admin() {
        return "redirect:admin_category_list";
    }

    @RequestMapping(value = "/admin_category_list", method = RequestMethod.GET)
    public String listCategory() {
        return "admin/listCategory";
    }

    @RequestMapping(value = "/admin_category_edit", method = RequestMethod.GET)
    public String editCategory(){
        return "admin/editCategory";
    }

    @RequestMapping(value="/admin_order_list",method = RequestMethod.GET)
    public String listOrder(){
        return "admin/listOrder";

    }

    @RequestMapping(value="/admin_product_list",method = RequestMethod.GET)
    public String listProduct(){
        return "admin/listProduct";

    }

    @RequestMapping(value="/admin_product_edit",method = RequestMethod.GET)
    public String editProduct(){
        return "admin/editProduct";

    }
    @RequestMapping(value="/admin_productImage_list",method = RequestMethod.GET)
    public String listProductImage(){
        return "admin/listProductImage";

    }

    @RequestMapping(value="/admin_property_list",method = RequestMethod.GET)
    public String listProperty(){
        return "admin/listProperty";

    }

    @RequestMapping(value="/admin_property_edit",method = RequestMethod.GET)
    public String editProperty(){
        return "admin/editProperty";

    }

    @RequestMapping(value="/admin_propertyValue_edit",method = RequestMethod.GET)
    public String editPropertyValue(){
        return "admin/editPropertyValue";

    }

    @RequestMapping(value="/admin_user_list",method = RequestMethod.GET)
    public String listUser(){
        return "admin/listUser";

    }

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public String Test() {
        return "admin/test";
    }
}
