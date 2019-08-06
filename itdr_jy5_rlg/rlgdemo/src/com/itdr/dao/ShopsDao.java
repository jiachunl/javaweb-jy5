package com.itdr.dao;

import com.itdr.pojo.Shops;
import com.itdr.utils.PoolUTil;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.List;

public class ShopsDao {

    public List<Shops> selectAll() {
        QueryRunner qr = new QueryRunner(PoolUTil.getCom());
        String sql = "select * from shops";
        List<Shops> li = null;
        try {
            li = qr.query(sql,new BeanListHandler<Shops>(Shops.class));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return li;
    }
}
