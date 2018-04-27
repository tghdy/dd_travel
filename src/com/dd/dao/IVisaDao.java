package com.dd.dao;

import java.util.Map;

public interface IVisaDao {
    Map<String,Object> selectVisaInf(int visaId) throws Exception;
}
