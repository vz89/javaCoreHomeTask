package com.vz89.hometask.controller;

import com.google.gson.Gson;
import com.vz89.hometask.model.Developer;
import com.vz89.hometask.service.DeveloperService;
import com.vz89.hometask.service.DeveloperServiceImpl;
import com.vz89.hometask.utils.RestUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class DeveloperServlet extends HttpServlet {
    private DeveloperService developerService = new DeveloperServiceImpl();
    private static final String RESPONSE_TYPE = "developers";


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pathInfo = request.getRequestURI();
        RestUtils restUtils = new RestUtils(pathInfo, RESPONSE_TYPE);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        switch (restUtils.getGetRequestType()) {
            case NO_PARAMS:
                response.getWriter().print(new Gson().toJson(developerService.findAll()));
                response.setStatus(200);
                break;
            case WITH_PARAMS:
                response.getWriter().print(new Gson().toJson(developerService.getById(restUtils.getId())));
                response.setStatus(200);
                break;
            default:
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String collect = request.getReader().lines().collect(Collectors.joining());
        Developer developer = new Gson().fromJson(collect, Developer.class);
        List<String> collect1 = developer.getSkills().stream().map(skill -> skill.getId().toString()).collect(Collectors.toList());
        String skillString = String.join(",", collect1);
        developerService.save(developer.getFirstName(), developer.getLastName(), developer.getAccount().getId(), skillString);
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pathInfo = request.getRequestURI();
        RestUtils restUtils = new RestUtils(pathInfo, RESPONSE_TYPE);

        String collect = request.getReader().lines().collect(Collectors.joining());
        Developer developer = new Gson().fromJson(collect, Developer.class);
        developerService.update(restUtils.getId(), developer.getFirstName(), developer.getLastName());
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pathInfo = request.getRequestURI();
        RestUtils restUtils = new RestUtils(pathInfo, RESPONSE_TYPE);
        developerService.delete(restUtils.getId());
    }
}
