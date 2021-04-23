package com.capgemini.service.implementation;

import java.sql.SQLException;
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
		Optional<NeedyPeople> np = needyPeople.findById(person.getNp_id());
		if(np.isPresent()) {
			throw new DuplicateNeedyPeopleException(person.getNp_id());
		}
		try {
			NeedyPeople n=np.get();
			needyPeople.addAddress(n.getAddress().getAdd_Id(), n.getAddress().getCity(),n.getAddress().getState(), n.getAddress().getPin(), n.getAddress().getLandmark());
			needyPeople.createNeedyPerson(n.getNp_id(), n.getNp_name(), n.getPhone(), n.getFamily_income(), n.getAddress().getAdd_Id());
			return true;
		}
		catch(SQLException e)
		{
			System.out.println(e.getMessage());
		}
		return false;
	}

	@Transactional
	@Override
	public boolean login(NeedyPeople person) throws NoSuchNeedyPeopleException {
		Optional<NeedyPeople> np = needyPeople.findById(person.getNp_id());
		if(np.isEmpty()) {
			throw new NoSuchNeedyPeopleException(person.getNp_id());
		}
		return false;/*needyPeople.readLoginData(person);*/
	}


}
