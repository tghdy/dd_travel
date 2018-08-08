package com.dd.dao;

import com.dd.entity.TravelVisa;

import java.util.List;
import java.util.Map;

public interface IVisaDao {
    //取页面信息
    Map<String,Object> selectVisaInf(int visaId) throws Exception;

    //搜索
    List<Map<String, Object>> selectVisa(String inf) throws Exception;

    //取全部信息
    List<Map<String, Object>> selectAll() throws Exception;

    int updateVisa(TravelVisa tv) throws Exception;

    int insertVisa(TravelVisa tv) throws Exception;

    int delete(int len, String[] strArray) throws Exception;
}
