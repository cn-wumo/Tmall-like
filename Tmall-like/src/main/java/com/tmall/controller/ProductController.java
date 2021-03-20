package com.tmall.controller;

import com.tmall.pojo.Product;
import com.tmall.service.CategoryService;
import com.tmall.service.ProductImageService;
import com.tmall.service.ProductService;
import com.tmall.util.Page4Navigator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@RestController
public class ProductController {
    @Autowired
    ProductService productService;
    @Autowired
    CategoryService categoryService;
    @Autowired
    ProductImageService productImageService;

    @RequestMapping(value = "/categories/{cid}/products",method = RequestMethod.GET)
    public Page4Navigator<Product> list(@PathVariable("cid") int cid,
                                        @RequestParam(value = "start", defaultValue = "0") int start,
                                        @RequestParam(value = "size", defaultValue = "5") int size) throws Exception {
        start = start<0?0:start;
        Page4Navigator<Product> page =productService.list(cid, start, size,5 );
        productImageService.setFirstProdutImages(page.getContent());
        return page;
    }

    @RequestMapping(value ="/products/{id}",method = RequestMethod.GET)
    public Product get(@PathVariable("id") int id) throws Exception {
        Product bean=productService.get(id);
        return bean;
    }

    @RequestMapping(value ="/products",method = RequestMethod.POST)
    public Object add(@RequestBody Product bean) throws Exception {
        bean.setCreateDate(new Date());
        productService.add(bean);
        return bean;
    }

    @RequestMapping(value ="/products/{id}",method = RequestMethod.DELETE)
    public String delete(@PathVariable("id") int id, HttpServletRequest request)  throws Exception {
        productService.delete(id);
        return null;
    }

    @RequestMapping(value ="/products",method = RequestMethod.PUT)
    public Object update(@RequestBody Product bean) throws Exception {
        productService.update(bean);
        return bean;
    }
}