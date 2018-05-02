package com.dd.service;

import java.util.List;
import java.util.Map;

public interface IVisaService {

    Map<String, Object> selectVisaAllInf(int visaId) throws Exception;

    List<Map<String, Object>> selectVisa(String inf) throws Exception;
}
