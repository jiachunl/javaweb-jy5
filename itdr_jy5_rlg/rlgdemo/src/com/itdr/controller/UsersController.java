package com.itdr.controller;

import com.itdr.common.ResponseCode;
import com.itdr.pojo.Users;
import com.itdr.service.UserService;
import com.itdr.utils.PathUTil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("/manage/user/*")/*list.do*/
public class UsersController extends HttpServlet {
    private UserService uc = new UserService();
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
                //rs = listDo(request);
                listDo(request,response);
                break;
            case "login":
                //rs = loginDo(request);
                loginDo(request, response);
                break;
            case "disableuser":
                rs = disableuserDo(request);
                break;
            case "yz":
                 yzDo(request,response);
                break;
        }

        //获取参数
        String pageSize = request.getParameter("pageSize");
        String pageNum = request.getParameter("pageNum");

        //返回响应数据
        //response.getWriter().write(rs.toString());

    }
    //获取所有用户列表的请求
    private void listDo(HttpServletRequest request,HttpServletResponse response) throws ServletException{

        ResponseCode rs = new ResponseCode();
       /* HttpSession session = request.getSession();
        Users user = (Users) session.getAttribute("user");
        if(user == null){
            rs.setStatus(3);
            rs.setMsg("请登录后操作！");
            //return rs;
        }
        if(user.getType() != 1){
            rs.setStatus(3);
            rs.setMsg("没有操作权限！");
            //return rs;
        }*/

        //获取参数
        String pageSize = request.getParameter("pageSize");
        String pageNum = request.getParameter("pageNum");
        rs = uc.selectAll(pageSize,pageNum);

        request.setAttribute("uli",rs);
        try {
            request.getRequestDispatcher("/WEB-INF/userlist.jsp").forward(request,response);
        } catch (IOException e) {
            e.printStackTrace();
        }
        //调用业务层处理业务
        //return rs;
    }

    //用户登录的请求
    private void loginDo(HttpServletRequest request,HttpServletResponse response) {
        //获取参数
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        ResponseCode rs = uc.selectOne(username,password);

        //获取session对象
        HttpSession session = request.getSession();
        session.setAttribute("user",rs.getData());

        //调用业务层处理业务
        //return rs;
        //response.sendRedirect("/home.jsp");
        try {
            request.getRequestDispatcher("/WEB-INF/home.jsp").forward(request,response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    //禁用用户的请求
    private ResponseCode disableuserDo(HttpServletRequest request){
        //获取参数
        String uid = request.getParameter("uid");

        ResponseCode rs = uc.selectOne(uid);

        //调用业务层处理业务
        return rs;
    }

    //验证用户名是否存在
    private void yzDo(HttpServletRequest request,HttpServletResponse response){
        //获取参数
        String uname = request.getParameter("un");

        Boolean bol = uc.yz(uname);

        //调用业务层处理业务
        try {
            response.getWriter().write(bol+"");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
