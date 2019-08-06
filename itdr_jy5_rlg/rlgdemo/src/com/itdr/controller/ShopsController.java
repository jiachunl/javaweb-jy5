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

@WebServlet(name = "ShopsController",value = "/manage/shops/*")
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
        switch (path){
            case "list":
                rs = getAll(request);
                break;
        }

        //获取参数
        String pageSize = request.getParameter("pageSize");
        String pageNum = request.getParameter("pageNum");

        //返回响应数据
        response.getWriter().write(rs.toString());
    }

    private ShopsService sss = new ShopsService();
    //查询所有商品
    private ResponseCode getAll(HttpServletRequest request){
        //获取参数

        //调用业务层
        ResponseCode rs = sss.getAll();
        return rs;
    }
}
