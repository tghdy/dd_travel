package com.dd.dao;

import java.util.List;
import java.util.Map;

public interface IVisaDao {
    Map<String,Object> selectVisaInf(int visaId) throws Exception;

    List<Map<String, Object>> selectVisa(String inf) throws Exception;
}
