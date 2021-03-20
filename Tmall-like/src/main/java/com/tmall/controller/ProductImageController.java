package com.tmall.controller;

import com.tmall.pojo.Product;
import com.tmall.pojo.ProductImage;
import com.tmall.service.CategoryService;
import com.tmall.service.ProductImageService;
import com.tmall.service.ProductService;
import com.tmall.util.ImageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
public class ProductImageController {
    @Autowired
    ProductService productService;
    @Autowired
    ProductImageService productImageService;
    @Autowired
    CategoryService categoryService;

    @RequestMapping(value = "/products/{pid}/productImages",method = RequestMethod.GET)
    public List<ProductImage> list(@RequestParam("type") String type, @PathVariable("pid") int pid) throws Exception {
        Product product = productService.get(pid);

        if(ProductImageService.type_single.equals(type)) {
            List<ProductImage> singles =  productImageService.listSingleProductImages(product);
            return singles;
        }
        else if(ProductImageService.type_detail.equals(type)) {
            List<ProductImage> details =  productImageService.listDetailProductImages(product);
            return details;
        }
        else {
            return new ArrayList<>();
        }
    }

    @RequestMapping(value = "/productImages",method = RequestMethod.POST)
    public Object add(@RequestParam("pid") int pid, @RequestParam("type") String type, MultipartFile image, HttpServletRequest request) throws Exception {
        ProductImage bean = new ProductImage();
        Product product = productService.get(pid);
        bean.setProduct(product);
        bean.setType(type);

        productImageService.add(bean);
        String path = ResourceUtils.getURL("classpath:").getPath()+"static/img/";
        String realPath = path.replace('/', '\\').substring(1,path.length());
        if(ProductImageService.type_single.equals(bean.getType())){
            realPath +="productSingle";
        }
        else{
            realPath +="productDetail";
        }
        File imageFolder= new File(realPath);
        File file = new File(imageFolder,bean.getId()+".jpg");
        String fileName = file.getName();
        if(!file.getParentFile().exists())
            file.getParentFile().mkdirs();
        try {
            image.transferTo(file);
            BufferedImage img = ImageUtil.change2jpg(file);
            ImageIO.write(img, "jpg", file);
        } catch (IOException e) {
            e.printStackTrace();
        }

        if(ProductImageService.type_single.equals(bean.getType())){
            realPath = path.replace('/', '\\').substring(1,path.length());
            String imageFolder_small= realPath+"productSingle_small";
            String imageFolder_middle= realPath+"productSingle_middle";
            File f_small = new File(imageFolder_small, fileName);
            File f_middle = new File(imageFolder_middle, fileName);
            f_small.getParentFile().mkdirs();
            f_middle.getParentFile().mkdirs();
            ImageUtil.resizeImage(file, 56, 56, f_small);
            ImageUtil.resizeImage(file, 217, 190, f_middle);
        }

        return bean;
    }

    @RequestMapping(value = "/productImages/{id}",method = RequestMethod.DELETE)
    public String delete(@PathVariable("id") int id, HttpServletRequest request)  throws Exception {
        ProductImage bean = productImageService.get(id);
        productImageService.delete(id);

        String path = ResourceUtils.getURL("classpath:").getPath()+"static/img/";
        String realPath = path.replace('/', '\\').substring(1,path.length());
        if(ProductImageService.type_single.equals(bean.getType()))
            realPath +="productSingle";
        else
            realPath +="productDetail";

        File  imageFolder= new File(realPath);
        File file = new File(imageFolder,bean.getId()+".jpg");
        String fileName = file.getName();
        file.delete();
        if(ProductImageService.type_single.equals(bean.getType())){
            realPath = path.replace('/', '\\').substring(1,path.length());
            String imageFolder_small= realPath+"productSingle_small";
            String imageFolder_middle= realPath+"productSingle_middle";
            File f_small = new File(imageFolder_small, fileName);
            File f_middle = new File(imageFolder_middle, fileName);
            f_small.delete();
            f_middle.delete();
        }
        return null;
    }
}