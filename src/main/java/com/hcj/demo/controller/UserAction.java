package com.hcj.demo.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.hcj.demo.model.User;
import com.hcj.demo.service.UserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

/**
 * Created by superadmin on 2017/2/13.
 *  @responseBody将返回值转化为json格式响应到客户端
 *  @requestBody将请求数据转化为json对象
 */
@Controller
@RequestMapping("/user")
public class UserAction {
    private static final Logger logger = Logger.getLogger(UserAction.class);
    @Autowired
    private UserService testService;

    @RequestMapping(value="/getIndexPage")
    public String getIndexPage(HttpServletRequest request) {

        List<User> list = testService.selectList();
        logger.info("查询所有 " + JSON.toJSONStringWithDateFormat(list, "yyyy-MM-dd HH:mm:ss"));
        String json = JSON.toJSONStringWithDateFormat(list, "yyyy-MM-dd HH:mm:ss");
        JSONArray ja = JSON.parseArray(json);
        request.setAttribute("userlist", ja);
        return "index";
    }

    @RequestMapping(value="/listUser")
    public @ResponseBody JSONObject listUser() {

        List<User> list = testService.selectList();
        //return JSON.toJSONStringWithDateFormat(list,"YYYY-dd-MM HH:mm:ss");
        JSONObject jo = new JSONObject();
        jo.put("data", JSONArray.toJSON(list));
        return jo;
    }

    @RequestMapping(value="/getUserById")
    public @ResponseBody JSONObject getUserById(String id) {
        JSONObject jo = new JSONObject();
        if(id == null || "".equals(id)){
            jo.put("code",0);
            jo.put("msg","参数错误！");
            jo.put("data",null);
            return jo;
        }
        logger.info("发过来的参数id="+JSONObject.toJSONStringWithDateFormat(id,"YYYY-mm-DD HH:mm:ss"));
        User result  = testService.selectById(Integer.parseInt(id));
        jo.put("code",1);
        jo.put("msg","成功！");
        jo.put("data", result);
        return jo;
    }

    @RequestMapping(value="/addUser")
    public @ResponseBody JSONObject addUser(User user) {
        JSONObject jo = new JSONObject();
        if(user.getId() != null){
            jo.put("code",0);
            jo.put("msg","参数错误！");
            jo.put("data",null);
            return jo;
        }
        user.setCreateTime(new Date());
        Integer result  = testService.insert(user);
        jo.put("code",1);
        jo.put("msg","成功！");
        jo.put("data", result);
        return jo;
    }

    @RequestMapping(value="/addOrUpdateUser")
    public @ResponseBody JSONObject addOrUpdateUser(User user) {
        JSONObject jo = new JSONObject();
        logger.info("update="+JSONObject.toJSONStringWithDateFormat(user, "YYYY-mm-DD HH:mm:ss")+"user.getId()="+user.getId());
        //正常的做法是对所有字段进行逻辑判断
        if(user.getId() != null && !"".equals(user.getId())){
            Integer result  = testService.update(user);
            jo.put("code",1);
            jo.put("msg","更新成功！");
            jo.put("data",result);
            return jo;
        }
        user.setCreateTime(new Date());
        Integer result  = testService.insert(user);
        jo.put("code",1);
        jo.put("msg","添加成功！");
        jo.put("data", result);
        return jo;
    }

    @RequestMapping(value="/updateUser")
    public @ResponseBody JSONObject updateUser(User user) {
        JSONObject jo = new JSONObject();
        if(user.getId() == null ){
            jo.put("code",0);
            jo.put("msg","参数错误！");
            jo.put("data",null);
            return jo;
        }
        logger.info("update=" + JSONObject.toJSONStringWithDateFormat(user, "YYYY-mm-DD HH:mm:ss"));
        Integer result  = testService.update(user);
        jo.put("code",1);
        jo.put("msg","成功！");
        jo.put("data", result);
        return jo;
    }

    @RequestMapping(value="/deleteUser")
    public @ResponseBody JSONObject deleteUser(String  id) {
        JSONObject jo = new JSONObject();
        if(id == null || "".equals(id)){
            jo.put("code",0);
            jo.put("msg","参数错误！");
            jo.put("data",null);
            return jo;
        }
        logger.info("发过来的参数id="+JSONObject.toJSONStringWithDateFormat(id,"YYYY-mm-DD HH:mm:ss"));
        Integer result  = testService.delete(Integer.parseInt(id));
        jo.put("code",1);
        jo.put("msg","删除成功！");
        jo.put("data", result);
        return jo;
    }

    @RequestMapping(value="/deleteUserById")
    public String deleteUserById(String  id) {
        JSONObject jo = new JSONObject();
        if(id == null || "".equals(id)){
            return null;
        }
        Integer result  = testService.delete(Integer.parseInt(id));
        return "redirect:/user/getIndexPage.do";
    }


}
