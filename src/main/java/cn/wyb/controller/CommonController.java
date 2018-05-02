package cn.wyb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Create Time: 2018年04月26日 13:09
 * C@author wyb
 **/
@Controller
public class CommonController {

    @RequestMapping("/")
    public String init(Model m){
        m.addAttribute("username","张三");
        return "index";
    }
}
