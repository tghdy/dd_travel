package com.dd.serviceImpl;

import com.dd.dao.IVisaDao;
import com.dd.daoImpl.VisaDaoImpl;
import com.dd.entity.TravelVisa;
import com.dd.service.IVisaService;

import java.util.List;
import java.util.Map;

public class VisaServiceImpl implements IVisaService {
    private static VisaServiceImpl visaService = new VisaServiceImpl();

    public static VisaServiceImpl getInstance() {
        return visaService;
    }

    private IVisaDao visaDao = VisaDaoImpl.getInstance();

    @Override
    public Map<String, Object> selectVisaAllInf(int visaId) throws Exception {
        return visaDao.selectVisaInf(visaId);
    }

    @Override
    public List<Map<String, Object>> selectVisa(String inf) throws Exception {
        return visaDao.selectVisa(inf);
    }

    @Override
    public List<Map<String, Object>> selectAll() throws Exception {
        return visaDao.selectAll();
    }

    @Override
    public int updateVisa(TravelVisa tv) throws Exception {
        return visaDao.updateVisa(tv);
    }

    @Override
    public int insertVisa(TravelVisa tv) throws Exception {
        return visaDao.insertVisa(tv);
    }

    @Override
    public int delete(int len, String[] strArray) throws Exception {
        return visaDao.delete(len,strArray);
    }


}
