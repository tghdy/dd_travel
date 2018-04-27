package com.dd.servlet;

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
