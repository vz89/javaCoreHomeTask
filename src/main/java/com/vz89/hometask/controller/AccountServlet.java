package com.vz89.hometask.controller;

import com.google.gson.Gson;
import com.vz89.hometask.model.Account;
import com.vz89.hometask.service.AccountService;
import com.vz89.hometask.service.AccountServiceImpl;
import com.vz89.hometask.utils.RestUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.stream.Collectors;

public class AccountServlet extends HttpServlet {

    private static final String RESPONSE_TYPE = "accounts";
    private AccountService accountService = new AccountServiceImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pathInfo = request.getRequestURI();
        RestUtils restUtils = new RestUtils(pathInfo, RESPONSE_TYPE);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        switch (restUtils.getGetRequestType()) {
            case NO_PARAMS:
                response.getWriter().print(new Gson().toJson(accountService.findAll()));
                response.setStatus(200);
                break;
            case WITH_PARAMS:
                response.getWriter().print(new Gson().toJson(accountService.getById(restUtils.getId())));
                response.setStatus(200);
                break;
            default:
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String collect = request.getReader().lines().collect(Collectors.joining());
        Account account = new Gson().fromJson(collect, Account.class);
        accountService.save(account.getName());
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pathInfo = request.getRequestURI();
        RestUtils restUtils = new RestUtils(pathInfo, RESPONSE_TYPE);

        String collect = request.getReader().lines().collect(Collectors.joining());
        Account account = new Gson().fromJson(collect, Account.class);
        accountService.update(restUtils.getId(), account.getAccountStatus());
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pathInfo = request.getRequestURI();
        RestUtils restUtils = new RestUtils(pathInfo, RESPONSE_TYPE);
        accountService.delete(restUtils.getId());
    }
}
