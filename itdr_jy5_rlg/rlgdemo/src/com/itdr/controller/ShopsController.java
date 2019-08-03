package com.itdr.controller;

import com.itdr.common.ResponseCode;
import com.itdr.pojo.Shops;
import com.itdr.pojo.Users;
import com.itdr.service.ShopsService;
import com.itdr.service.UserService;
import com.itdr.utils.PathUTil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "ShopsController")
public class ShopsController extends HttpServlet {
    private ShopsService ss = new ShopsService();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //怎么获取请求路径信息
        String pathInfo = request.getPathInfo();
        String path = PathUTil.getPath(pathInfo);
        ResponseCode rs = null;

        //判断一下是什么样的请求
       /* switch (path){
            case "list":
                rs = listDo(request);
                break;
            case "login":
                rs = loginDo(request);
                break;
            case "disableuser":
                rs = disableuserDo(request);
                break;
        }*/

        //获取参数
        String pageSize = request.getParameter("pageSize");
        String pageNum = request.getParameter("pageNum");

        //返回响应数据
        response.getWriter().write(rs.toString());
    }
    //获取所有用户列表的请求
    private ResponseCode listDo(HttpServletRequest request){

        ResponseCode rs = new ResponseCode();
        HttpSession session = request.getSession();
        Shops sname = (Shops) session.getAttribute("sname");
        if(sname == null){
            rs.setStatus(3);
            rs.setMsg("请登录后操作！");
            return rs;
        }


        //获取参数
        String pageSize = request.getParameter("pageSize");
        String pageNum = request.getParameter("pageNum");
        rs = ss.selectAll(pageSize,pageNum);

        //调用业务层处理业务
        return rs;
    }

    //用户登录的请求
    private ResponseCode loginDo(HttpServletRequest request){
        //获取参数
        String password = request.getParameter("sname");

        ResponseCode rs = ss.selectOne(sname);

        //获取session对象
        HttpSession session = request.getSession();
        session.setAttribute("sname",rs.getData());

        //调用业务层处理业务
        return rs;
    }
    //禁用用户的请求
    private ResponseCode disableuserDo(HttpServletRequest request){
        //获取参数
        String uid = request.getParameter("sid");

        ResponseCode rs = ss.selectOne(uid);

        //调用业务层处理业务
        return rs;
    }
}
