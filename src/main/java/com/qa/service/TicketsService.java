package com.qa.service;

import javax.inject.Inject;

import com.qa.repository.ITicketsRepository;

public class TicketsService implements ITicketsService {
	@Inject
    private ITicketsRepository ticketsRepository;

    public String addShowing(String showing) {
        return ticketsRepository.addShowing(showing);
    }

    public String removeShowing(long id) {
        return ticketsRepository.removeShowing(id);
    }

    public String getAllShowings(Long movieID) {
        return ticketsRepository.getAllShowings(movieID);
    }

    public String buyTicket(long id, String ticket) {
        return ticketsRepository.buyTicket(id, ticket);
    }

}
