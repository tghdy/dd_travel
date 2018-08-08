package com.dd.servlet;

import com.dd.entity.TravelVisa;
import com.dd.service.IVisaService;
import com.dd.serviceImpl.VisaServiceImpl;
import com.dd.util.JsonData;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

@WebServlet("/visa")
public class VisaServlet extends HttpServlet {

    private IVisaService visaService = VisaServiceImpl.getInstance();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        String method = request.getParameter("method");
        if ("visaAllInf".equals(method)) {
            visaAllInf(request, response);
        }
        if ("getAllInf".equals(method)) {
            getAllInf(request, response);
        }
        if ("selectAll".equals(method)) {
            selectAll(request, response);
        }
        if ("updateVisa".equals(method)) {
            updateVisa(request, response);
        }
        if ("insertVisa".equals(method)) {
            insertVisa(request, response);
        }
        if ("delete".equals(method)) {
            delete(request, response);
        }
    }

    private void delete(HttpServletRequest request, HttpServletResponse response) {
        String[] strArray= request.getParameterValues("list[]");
        int len=strArray.length;
        JsonData jsonData = null;
        jsonData = new JsonData(JsonData.SUCCESS,"成功");
        System.out.print("aaaaaaaaa");
        try{
            int flag = visaService.delete(len,strArray);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            output(response, jsonData);
        }

    }
    private void insertVisa(HttpServletRequest request, HttpServletResponse response) {
        System.out.print("ass");
        float price=0;
        if(request.getParameter("price")==""){

        }else{
            price=Float.parseFloat(request.getParameter("price"));
        }
        TravelVisa tv = new TravelVisa(request.getParameter("deal_place"),
                request.getParameter("validity_period"),
                request.getParameter("most_stay"),
                request.getParameter("deal_time"),
                request.getParameter("interview"),
                request.getParameter("entry"),
                request.getParameter("custom_range"),
                request.getParameter("custom_inf1"),
                request.getParameter("custom_inf2"),
                request.getParameter("custom_inf3"),
                request.getParameter("custom_inf4"),
                request.getParameter("custom_inf5"),
                price,
                request.getParameter("title"),
                request.getParameter("matters"),
                request.getParameter("description_fees"),
                request.getParameter("warning"),
                request.getParameter("picture"));
        System.out.print("dss");
        JsonData jsonData = null;
        jsonData = new JsonData(JsonData.SUCCESS,"成功");
        System.out.print(tv.getVisa_title());
        try{
            int flag = visaService.insertVisa(tv);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            output(response, jsonData);

        }
    }


    private void updateVisa(HttpServletRequest request, HttpServletResponse response) {
        TravelVisa tv = new TravelVisa(Integer.valueOf(request.getParameter("id")),
                request.getParameter("deal_place"),
                request.getParameter("validity_period"),
                request.getParameter("most_stay"),
                request.getParameter("deal_time"),
                request.getParameter("interview"),
                request.getParameter("entry"),
                request.getParameter("custom_range"),
                Float.parseFloat(request.getParameter("price")),
                request.getParameter("title"),
                request.getParameter("matters"),
                request.getParameter("description_fees"),
                request.getParameter("warning"),
                request.getParameter("picture"),
                request.getParameter("custom_inf1"),
                request.getParameter("custom_inf2"),
                request.getParameter("custom_inf3"),
                request.getParameter("custom_inf4"),
                request.getParameter("custom_inf5"));
        JsonData jsonData = null;
        jsonData = new JsonData(JsonData.SUCCESS,"成功");
        try{
            int flag = visaService.updateVisa(tv);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            output(response, jsonData);

        }

    }


    private void selectAll(HttpServletRequest request, HttpServletResponse response) {
        JsonData jsonData = null;
        try {
            List<Map<String, Object>> list = visaService.selectAll();

            if (list != null) {
                jsonData = new JsonData(JsonData.SUCCESS, "获取成功", list);
            } else {
                jsonData = new JsonData(JsonData.FAILED, "无结果");
            }
        } catch (Exception e) {
            e.printStackTrace();
            jsonData = new JsonData(JsonData.FAILED, "异常");
        } finally {
            output(response, jsonData);
        }
    }

    private void getAllInf(HttpServletRequest request, HttpServletResponse response) {
        JsonData jsonData = null;
        try {
            String inf = request.getParameter("inf");
            List<Map<String, Object>> list = visaService.selectVisa(inf);

            if (list != null) {
                jsonData = new JsonData(JsonData.SUCCESS, "获取成功", list);
            } else {
                jsonData = new JsonData(JsonData.FAILED, "无结果");
            }
        } catch (Exception e) {
            e.printStackTrace();
            jsonData = new JsonData(JsonData.FAILED, "异常");
        } finally {
            output(response, jsonData);
        }
    }

    private void visaAllInf(HttpServletRequest request, HttpServletResponse response) {
        JsonData jsonData = null;
        try {
            Integer id = Integer.valueOf(request.getParameter("id"));
            Map<String, Object> map = visaService.selectVisaAllInf(id);
            
            if (map != null) {
                jsonData = new JsonData(JsonData.SUCCESS, "获取成功", map);
            } else {
                jsonData = new JsonData(JsonData.FAILED, "无结果");
            }
        } catch (Exception e) {
            e.printStackTrace();
            jsonData = new JsonData(JsonData.FAILED, "异常");
        } finally {
            output(response, jsonData);
        }
    }

    private void output(HttpServletResponse response, JsonData jsonData) {
        try {
            PrintWriter out = response.getWriter();
            out.println(jsonData.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
