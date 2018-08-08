package com.dd.daoImpl;

import com.dd.dao.IVisaDao;
import com.dd.entity.TravelVisa;
import com.dd.util.JdbcUtils_DBCP;

import java.util.List;
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

    @Override
    public List<Map<String, Object>> selectVisa(String inf) throws Exception {
        String sql="SELECT * FROM travel_visa where visa_title LIKE ?";
        return JdbcUtils_DBCP.selectMapList(sql, new Object[]{'%'+inf+'%'});
    }

    @Override
    public List<Map<String, Object>> selectAll() throws Exception {
        String sql="SELECT * FROM travel_visa";
        return JdbcUtils_DBCP.selectMapList(sql,null);
    }

    @Override
    public int updateVisa(TravelVisa tv) throws Exception {
        String sql="UPDATE travel_visa SET deal_place = '"+tv.getDeal_place()+"',validity_period = '"+
                tv.getValidity_period()+"',most_stay = '"+tv.getMost_stay()+"',deal_time = '"+
                tv.getDeal_time()+"',interview = '"+tv.getInterview()+"',entry = '"+
                tv.getEntry()+"',custom_range = '"+tv.getCustom_range()+"',price = '"+
                tv.getPrice()+"',visa_title = '"+tv.getVisa_title()+"',matters = '"+
                tv.getMatters()+"',description_fees = '"+tv.getDescription_fees()+"',warning = '"+
                tv.getWarning()+"',visa_pic = '"+tv.getVisa_pic()+"',custom_inf1 = '"+
                tv.getCustom_inf1()+"',custom_inf2 = '"+tv.getCustom_inf2()+"',custom_inf3 = '"
                +tv.getCustom_inf3()+"',custom_inf4 = '"+tv.getCustom_inf4()+"',custom_inf5 = '"
                +tv.getCustom_inf5()+"' WHERE id = ?";
        return JdbcUtils_DBCP.update(sql,new Object[]{tv.getId()});
    }

    @Override
    public int insertVisa(TravelVisa tv) throws Exception {
        String sql="INSERT INTO travel_visa (deal_place,validity_period,most_stay,deal_time," +
                "interview,entry,custom_range,price,visa_title,matters,description_fees,warning," +
                "visa_pic,custom_inf1,custom_inf2,custom_inf3,custom_inf4,custom_inf5) VALUES('"+
                tv.getDeal_place()+"','"+
                tv.getValidity_period()+"','"+tv.getMost_stay()+"','"+
                tv.getDeal_time()+"','"+tv.getInterview()+"','"+
                tv.getEntry()+"','"+tv.getCustom_range()+"','"+
                tv.getPrice()+"','"+tv.getVisa_title()+"','"+
                tv.getMatters()+"','"+tv.getDescription_fees()+"','"+
                tv.getWarning()+"','"+tv.getVisa_pic()+"','"+
                tv.getCustom_inf1()+"','"+tv.getCustom_inf2()+"','"
                +tv.getCustom_inf3()+"','"+tv.getCustom_inf4()+"','"
                +tv.getCustom_inf5()+"')";
        return JdbcUtils_DBCP.update(sql,null);
    }

    @Override
    public int delete(int len, String[] strArray) throws Exception {
        StringBuilder sql = new StringBuilder();
        sql.append("DELETE FROM travel_visa WHERE id IN (");
        for(int i=0;i<len-1;i++){
            sql.append(strArray[i]).append(",");
        }
        sql.append(strArray[len-1]).append(")");
        return JdbcUtils_DBCP.update(sql.toString(), null);
    }

}
