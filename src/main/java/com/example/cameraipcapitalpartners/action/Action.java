package com.example.cameraipcapitalpartners.action;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Action {
	String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException;
	String list(HttpServletRequest request, HttpServletResponse response) throws ServletException;
	String load(HttpServletRequest request, HttpServletResponse response) throws ServletException;
	String save(HttpServletRequest request, HttpServletResponse response) throws ServletException;
	String delete(HttpServletRequest request, HttpServletResponse response) throws ServletException;
}
