package com.vz89.hometask.controller;

import com.google.gson.Gson;
import com.vz89.hometask.service.SkillService;
import com.vz89.hometask.service.SkillServiceImpl;
import com.vz89.hometask.utils.RestUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SkillServlet extends HttpServlet {
    private SkillService skillService = new SkillServiceImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pathInfo = request.getRequestURI();
        RestUtils restUtils = new RestUtils(pathInfo,"skills");
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        switch (restUtils.getGetRequestType()){
            case FIND_ALL:
                response.getWriter().print(new Gson().toJson(skillService.findAll()));
                break;
            case GET_BY_ID:
                response.getWriter().print(new Gson().toJson(skillService.getById(restUtils.getId())));
                break;
            default:
        }
    }
}
