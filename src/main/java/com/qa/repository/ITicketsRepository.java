package com.qa.repository;
public interface ITicketsRepository {
	String addShowing(String showing);
	String removeShowing(long id);
	String getAllShowings(Long movieID);
	String buyTicket(long id, String ticket);
}
