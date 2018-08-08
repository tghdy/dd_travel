package com.dd.service;

import com.dd.entity.TravelVisa;

import java.util.List;
import java.util.Map;

public interface IVisaService {

    Map<String, Object> selectVisaAllInf(int visaId) throws Exception;

    List<Map<String, Object>> selectVisa(String inf) throws Exception;

    List<Map<String, Object>> selectAll() throws Exception;

    int updateVisa(TravelVisa tv) throws Exception;

    int insertVisa(TravelVisa tv) throws Exception;

    int delete(int len, String[] strArray) throws Exception;
}
