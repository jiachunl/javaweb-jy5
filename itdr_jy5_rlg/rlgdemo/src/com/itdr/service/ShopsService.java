package com.itdr.service;

import com.itdr.common.Const;
import com.itdr.common.ResponseCode;
import com.itdr.dao.ShopsDao;
import com.itdr.dao.UserDao;
import com.itdr.pojo.Shops;
import com.itdr.pojo.Users;

import java.util.List;

public class ShopsService {
    private ShopsDao sd = new ShopsDao();
    public ResponseCode selectAll(String pageSize, String pageNum) {
        if(pageSize == null || pageSize.equals("")){
            pageSize = "10";
        }

        if(pageNum == null || pageNum.equals("")){
            pageNum = "1";
        }

        List<Shops> li = sd.selectAll(pageSize,pageNum);
        //如果集合元素是空呢？
        ResponseCode rs = new ResponseCode();
        rs.setStatus(0);
        rs.setData(li);
        return rs;
    }

    //用户登录
    public ResponseCode selectOne(String sname) {
        ResponseCode rs = new ResponseCode();
        if(sname == null || sname.equals("")){
            rs.setStatus(1);
            rs.setMsg("账号活密码错误");
            return rs;
        }
        //查找是否有这样一个用户
        Shops s = sd.selectOne(sname);

        //如果用户不存在
        if(s == null){
            rs.setStatus(1);
            rs.setMsg("账号活密码错误");
            return rs;
        }



        rs.setStatus(0);
        rs.setData(s);
        return rs;
    }

    //用户禁用
    public ResponseCode selectOne(String sid) {
        ResponseCode rs = new ResponseCode();
        if(sid == null || sid.equals("") ){
            rs.setStatus(Const.USER_PARAMETER_CODE);
            rs.setMsg(Const.USER_PARAMETER_MSG);
            return rs;
        }
        //字符串转数值
        Integer sid = null;
        try{
            sid = Integer.parseInt(sid);
        }catch(Exception e){
            rs.setStatus(105);
            rs.setMsg("输入非法参数");
            return rs;
        }

        //查找是否有这样一个用户
        Shops s = sd.selectOne(sid);

        //如果用户不存在
        if(s == null){
            rs.setStatus(Const.USER_NO_CODE);
            rs.setMsg(Const.USER_NO_MSG);
            return rs;
        }

        //禁用用户
        int row = sd.updateByUid(sid);
        if(row <= 0){
            rs.setStatus(106);
            rs.setMsg("用户禁用更新失败");
            return rs;
        }
        rs.setStatus(0);
        rs.setData(row);
        return rs;
    }
}
