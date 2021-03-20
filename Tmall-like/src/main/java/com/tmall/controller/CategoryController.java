package com.tmall.controller;

import com.tmall.pojo.Category;
import com.tmall.service.CategoryService;
import com.tmall.util.ImageUtil;
import com.tmall.util.Page4Navigator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

@RestController
public class CategoryController {
    @Autowired
    CategoryService categoryService;

    @RequestMapping(value = "/categories",method = RequestMethod.GET)
    public Page4Navigator<Category> list(@RequestParam(value = "start", defaultValue = "0") int start, @RequestParam(value = "size", defaultValue = "5") int size) throws Exception {
        start = start<0?0:start;
        Page4Navigator<Category> page =categoryService.list(start, size, 5);  //5表示导航分页最多有5个，如[1,2,3,4,5]
        return page;
    }

    @RequestMapping(value = "/categories",method = RequestMethod.POST)
    public Object add(Category bean, MultipartFile image, HttpServletRequest request) throws Exception {
        categoryService.add(bean);
        saveOrUpdateImageFile(bean, image, request);
        return bean;
    }
    public void saveOrUpdateImageFile(Category bean, MultipartFile image, HttpServletRequest request)
            throws IOException {
        String path = ResourceUtils.getURL("classpath:").getPath()+"static/img/category";
        String realPath = path.replace('/', '\\').substring(1,path.length());
        File imageFolder= new File(realPath);
        File file = new File(imageFolder,bean.getId()+".jpg");
        if(!file.getParentFile().exists())
            file.getParentFile().mkdirs();
        image.transferTo(file);
        BufferedImage img = ImageUtil.change2jpg(file);
        ImageIO.write(img, "jpg", file);
    }

    @RequestMapping(value = "/categories/{id}",method = RequestMethod.DELETE)
    public String delete(@PathVariable("id") int id, HttpServletRequest request)  throws Exception {
        categoryService.delete(id);
        String path = ResourceUtils.getURL("classpath:").getPath()+"static/img/category";
        String realPath = path.replace('/', '\\').substring(1,path.length());
        File  imageFolder= new File(realPath);
        File file = new File(imageFolder,id+".jpg");
        file.delete();
        return null;
    }

    @RequestMapping(value = "/categories/{id}",method = RequestMethod.GET)
    public Category get(@PathVariable("id") int id) throws Exception {
        Category bean = categoryService.get(id);
        return bean;
    }

    @RequestMapping(value = "/categories/{id}",method = RequestMethod.PUT)
    public Object update(Category bean, MultipartFile image,HttpServletRequest request) throws Exception {
        String name = request.getParameter("name");
        bean.setName(name);
        categoryService.update(bean);
        if(image!=null) {
            saveOrUpdateImageFile(bean, image, request);
        }
        return bean;
    }
}
