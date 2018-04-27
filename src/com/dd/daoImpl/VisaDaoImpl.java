package com.dd.daoImpl;

import com.dd.dao.IVisaDao;
import com.dd.util.JdbcUtils_DBCP;

import java.util.Map;

public class VisaDaoImpl implements IVisaDao {
    private static VisaDaoImpl visaDao = new VisaDaoImpl();

    public static VisaDaoImpl getInstance() {
        return visaDao;
    }

    @Override
    public Map<String, Object> selectVisaInf(int visaId) throws Exception {
        String sql="SELECT * FROM travel_visa where id = ?";
        return JdbcUtils_DBCP.selectMap(sql, new Object[]{visaId});
    }
}