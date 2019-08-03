package com.itdr.dao;

import com.itdr.pojo.Shops;
import com.itdr.utils.PoolUTil;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.List;

public class ShopsDao {
    //查找所有用户
    public List<Shops> selectAll(String pageSize, String pageNum) {
        /* ComboPooledDataSource co = PoolUTil.getCom();*/
        QueryRunner qr = new QueryRunner(PoolUTil.getCom());
        String sql = "select * from users";
        List<Shops> li = null;
        try {
            li = qr.query(sql, new BeanListHandler<>(Shops.class));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return li;
    }

    //根据用户名和密码查找一个用户
    public Shops selectOne(String username, String password) {
        QueryRunner qr = new QueryRunner(PoolUTil.getCom());
        String sql = "select * from users where sname = ?";
        Shops s = null;
        try {
            s = qr.query(sql,new BeanHandler<Shops>(Shops.class),sname);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return s;
    }

    //根据id查找一个用户
    public Shops selectOne(Integer sid) {
        QueryRunner qr = new QueryRunner(PoolUTil.getCom());
        String sql = "select * from users where sid = ?";
        Shops s = null;
        try {
            s = qr.query(sql, new BeanHandler<Shops>(Shops.class),sid);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return s;
    }

    //根据id禁用一个用户
    public int updateByUid(Integer uid) {
        QueryRunner qr = new QueryRunner(PoolUTil.getCom());
        String sql = "update users set stats = 1 where id = ?";
        int row = 0;
        try {
            row = qr.update(sql,sid);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return row;
    }
}
