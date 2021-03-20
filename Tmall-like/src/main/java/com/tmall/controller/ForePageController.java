package com.tmall.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;

@Controller
public class ForePageController {
    @RequestMapping(value="/",method = RequestMethod.HEAD)
    public String index(){
        return "redirect:home";
    }
    @RequestMapping(value="/",method = RequestMethod.GET)
    public String indexGet(){
        return "redirect:home";
    }
    @RequestMapping(value="/home",method = RequestMethod.GET)
    public String home(){
        return "fore/home";
    }
    @RequestMapping(value="/register",method = RequestMethod.GET)
    public String register(){
        return "fore/register";
    }
    @RequestMapping(value="/alipay",method = RequestMethod.GET)
    public String alipay(){
        return "fore/alipay";
    }
    @RequestMapping(value="/bought",method = RequestMethod.GET)
    public String bought(){
        return "fore/bought";
    }
    @RequestMapping(value="/buy",method = RequestMethod.GET)
    public String buy(){
        return "fore/buy";
    }
    @RequestMapping(value="/cart",method = RequestMethod.GET)
    public String cart(){
        return "fore/cart";
    }
    @RequestMapping(value="/category",method = RequestMethod.GET)
    public String category(){
        return "fore/category";
    }
    @RequestMapping(value="/confirmPay",method = RequestMethod.GET)
    public String confirmPay(){
        return "fore/confirmPay";
    }
    @RequestMapping(value="/login",method = RequestMethod.GET)
    public String login(){
        return "fore/login";
    }
    @RequestMapping(value="/orderConfirmed",method = RequestMethod.GET)
    public String orderConfirmed(){
        return "fore/orderConfirmed";
    }
    @RequestMapping(value="/payed",method = RequestMethod.GET)
    public String payed(){
        return "fore/payed";
    }
    @RequestMapping(value="/product",method = RequestMethod.GET)
    public String product(){
        return "fore/product";
    }
    @RequestMapping(value="/registerSuccess",method = RequestMethod.GET)
    public String registerSuccess(){
        return "fore/registerSuccess";
    }
    @RequestMapping(value="/review",method = RequestMethod.GET)
    public String review(){
        return "fore/review";
    }
    @RequestMapping(value="/orderRefund",method = RequestMethod.GET)
    public String orderRefund(){return "fore/orderRefund"; }
    @RequestMapping(value="/search",method = RequestMethod.GET)
    public String searchResult(){
        return "fore/search";
    }
    @RequestMapping(value = "/forelogout",method = RequestMethod.GET)
    public String logout(HttpSession session) {
        Subject subject = SecurityUtils.getSubject();
        if(subject.isAuthenticated())
            subject.logout();
        return "redirect:home";
    }
}