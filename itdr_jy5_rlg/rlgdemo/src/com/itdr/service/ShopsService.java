package com.itdr.service;

import com.itdr.common.Const;
import com.itdr.common.ResponseCode;
import com.itdr.dao.ShopsDao;
import com.itdr.dao.UserDao;
import com.itdr.pojo.Shops;
import com.itdr.pojo.Users;

import java.util.List;
import java.util.Objects;

public class ShopsService {
    private ShopsDao sd = new ShopsDao();

    public ResponseCode getAll() {
        ResponseCode rs = null;
        List<Shops> li = sd.selectAll();
        rs = ResponseCode.successRS(li);
        return rs;
    }
}
