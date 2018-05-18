package com.qa.service;

public interface ITicketsService {
	String addShowing(String showing);
	String removeShowing(long id);
	String getAllShowings(Long movieID);
	String buyTicket(long id, String ticket);
}
