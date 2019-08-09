package com.example.demo.controller;

import com.alibaba.fastjson.JSON;
import com.example.demo.common.CodeMenu;
import com.example.demo.common.ResponseVo;
import com.example.demo.common.StringUtil;
import com.example.demo.config.SysLogger;
import com.example.demo.database.mysql.IUserDao;
import com.example.demo.entity.User;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * @author hao
 * @date 2019-08-06 15:21
 * description
 */
@RestController
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    private IUserDao iUserDao;

    /**
     * 跳转到主页
     * @return 视图
     */
    @ApiOperation(value = "获取用户列表页面", notes = "获取用户列表页面")
    @RequestMapping(value = "index", method = RequestMethod.GET)
    @SysLogger("index")
    public ModelAndView index() {
        return new ModelAndView("/index");
    }

    /**
     * 跳转到新增页
     * @return 视图
     */
    @ApiOperation(value = "获取用户详情页面", notes = "获取用户详情页面")
    @RequestMapping(value = "add", method = RequestMethod.GET)
    @SysLogger("add")
    public ModelAndView add(String id, String edit) {
        ModelMap modelMap = new ModelMap();
        modelMap.addAttribute("id", id);
        modelMap.addAttribute("edit", edit);//edit，0：不可编辑、1：可编辑
        if (StringUtil.isNoBlank(id)) {
            modelMap.addAttribute("user", iUserDao.getOne(id));
        }
        return new ModelAndView("/add", modelMap);
    }

    /**
     * 获取列表
     * @return
     */
    @SysLogger("getUserList")
    @ApiOperation(value = "获取用户列表", notes = "获取用户列表")
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String getUserList() {
        List<User> list = iUserDao.findAll();
        ResponseVo responseVo = new ResponseVo(CodeMenu.SUCCESS.getCode(), "查找成功！", list);
        if (list != null && list.size() > 0) {
            responseVo.setCount(String.valueOf(list.size()));
        }
        return JSON.toJSONString(responseVo);
    }

    /**
     * 新增
     * @param user
     * @return
     */
    @SysLogger("postUser")
    @ApiOperation(value="创建用户", notes="根据User对象创建用户")
    @ApiImplicitParam(name = "user", value = "用户详细实体user", required = true, dataType = "User")
    @RequestMapping(value = "/", method = RequestMethod.POST)
    public String postUser(@ModelAttribute User user) {
        User user1 = iUserDao.save(user);
        ResponseVo responseVo = new ResponseVo(CodeMenu.SUCCESS.getCode(), "新增成功！", user1);
        return JSON.toJSONString(responseVo);
    }

    /**
     * 查找
     * @param id
     * @return
     */
    @SysLogger("getUser")
    @ApiOperation(value="获取用户详细信息", notes="根据url的id来获取用户详细信息")
    @ApiImplicitParam(name = "id", value = "用户ID", required = true, dataType = "String")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String getUser(@PathVariable String id) {
        User user = iUserDao.getOne(id);
        ResponseVo responseVo = new ResponseVo(CodeMenu.SUCCESS.getCode(), "查找成功！", user);
        return JSON.toJSONString(responseVo);
    }

    /**
     * 更新
     * @param id
     * @param user
     * @return
     */
    @SysLogger("putUser")
    @ApiOperation(value="更新用户详细信息", notes="根据url的id来指定更新对象，并根据传过来的user信息来更新用户详细信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "用户ID", required = true, dataType = "String"),
            @ApiImplicitParam(name = "user", value = "用户详细实体user", required = true, dataType = "User")
    })
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public String putUser(@PathVariable Long id, @ModelAttribute User user) {
        User user1 = iUserDao.save(user);
        ResponseVo responseVo = new ResponseVo(CodeMenu.SUCCESS.getCode(), "更新成功！", user1);
        return JSON.toJSONString(responseVo);
    }

    @ApiOperation(value="删除用户", notes="根据url的id来指定删除对象")
    @ApiImplicitParam(name = "id", value = "用户ID", required = true, dataType = "String")
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public String deleteUser(@PathVariable String id) {
        iUserDao.deleteById(id);
        ResponseVo responseVo = new ResponseVo(CodeMenu.SUCCESS.getCode(), "删除成功！", id);
        return JSON.toJSONString(responseVo);
    }

}
