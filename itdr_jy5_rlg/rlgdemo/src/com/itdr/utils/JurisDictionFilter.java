package com.itdr.utils;

import com.itdr.common.ResponseCode;
import com.itdr.pojo.Users;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(filterName = "JurisDictionFilter",value =  "/manage/*")
public class JurisDictionFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {

        //处理乱码
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=UTF-8");
        ResponseCode rs = new ResponseCode();
        HttpServletRequest request = (HttpServletRequest)req;
        //获取路径
        String pathInfo = request.getPathInfo();
        //判断是否是登录，是登录直接放行
        if(pathInfo.equals("/login.do")){
            chain.doFilter(req, resp);
            return;
        }
        HttpSession session = request.getSession();
        Users user = (Users) session.getAttribute("user");
        if(user == null){
            rs.setStatus(3);
            rs.setMsg("请登录后操作");
            resp.getWriter().write(rs.toString());
            return;
        }
        if(user.getType() != 1){
            rs.setStatus(3);
            rs.setMsg("没有操作权限");
            resp.getWriter().write(rs.toString());
            return;
        }
        chain.doFilter(req,resp);
        return;

    }

    public void init(FilterConfig config) throws ServletException {

    }

}
