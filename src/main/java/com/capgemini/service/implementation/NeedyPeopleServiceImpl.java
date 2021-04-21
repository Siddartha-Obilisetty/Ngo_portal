package com.capgemini.service.implementation;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.capgemini.dao.NeedyPeopleDao;
import com.capgemini.exception.DuplicateNeedyPeopleException;
import com.capgemini.exception.NoSuchNeedyPeopleException;
import com.capgemini.model.NeedyPeople;
import com.capgemini.service.NeedyPeopleService;

@Service
public class NeedyPeopleServiceImpl implements NeedyPeopleService
{
	@Autowired
	NeedyPeopleDao needyPeople;

	//not finished
	@Transactional
	@Override
	public boolean requestForHelp(NeedyPeople person) {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Transactional
	@Override
	public boolean registerNeedyPerson(NeedyPeople person) throws DuplicateNeedyPeopleException {
		Optional<NeedyPeople> np = needyPeople.findById(person.getNeedyPersonid());
		if(np.isPresent()) {
			throw new DuplicateNeedyPeopleException(person.getNeedyPersonid());
		}
		return needyPeople.createNeedyPerson(person);
	}

	@Transactional
	@Override
	public boolean login(NeedyPeople person) throws NoSuchNeedyPeopleException {
		Optional<NeedyPeople> np = needyPeople.findById(person.getNeedyPersonid());
		if(np.isPresent()) {
			throw new NoSuchNeedyPeopleException(person.getNeedyPersonid());
		}
		return needyPeople.readLoginData(person);
	}


}
