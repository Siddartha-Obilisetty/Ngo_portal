package com.capgemini.service.implementation;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.capgemini.dao.EmployeeDao;
import com.capgemini.dao.NeedyPeopleDao;
import com.capgemini.exception.DuplicateNeedyPeopleException;
import com.capgemini.exception.NoSuchNeedyPeopleException;
import com.capgemini.exception.WrongPasswordException;
import com.capgemini.model.DonationDistribution;
import com.capgemini.model.DonationDistributionStatus;
import com.capgemini.model.DonationItem;
import com.capgemini.model.DonationType;
import com.capgemini.model.Employee;
import com.capgemini.model.NeedyPeople;
import com.capgemini.model.RequestStatus;
import com.capgemini.service.EmployeeService;
import com.capgemini.service.NeedyPeopleService;

@Service
public class NeedyPeopleServiceImpl implements NeedyPeopleService
{
	@Autowired
	NeedyPeopleDao needyPeople;
	@Autowired
	EmployeeDao employee;

	public void setNeedyPeople(NeedyPeopleDao needyPeople) {
		this.needyPeople = needyPeople;
	}

	@Transactional
	@Override
	public boolean requestForHelp(int np_id) {
		DonationDistribution dd = new DonationDistribution();
		DonationItem di = new DonationItem();
		RequestStatus rs = new RequestStatus(needyPeople.findById(np_id).get());
		dd.setNeedyPeople(needyPeople.findById(np_id).get());
		dd.setAmt_distributed(500);
		dd.setStatus(DonationDistributionStatus.PENDING);
		
		di.setDonationType(DonationType.MONEY);
		di.setItem_desc("Money");
		dd.setDonationItem(di);
		dd.setEmployee(employee.findById(102).get());
		
		needyPeople.addDonationItem(di.getItem_id(),"Money", DonationType.MONEY);
		needyPeople.addDonationDistribution(dd.getDistributionid(),dd.getAmt_distributed(),dd.getStatus(),dd.getDonationItem().getItem_id(),dd.getNeedyPeople().getNp_id(),dd.getEmployee().getEmpid());
		needyPeople.requestForHelp(rs.getId(), np_id);
		return true;
	}

	@Transactional(readOnly = true)
	@Override
	public String login(String username, String password) throws NoSuchNeedyPeopleException, WrongPasswordException {
		/*NeedyPeople n = needyPeople.getByUsername(username);
		System.out.println(n.getNp_id());
		if(n!=null) {
			if(n.getPassword()==needyPeople.readLoginData(username))
				return true;
			else
				throw new WrongPasswordException();
		}
		else {
			throw new NoSuchNeedyPeopleException(username);
		}*/
		return needyPeople.readLoginData(username);
		
	}
	
	
	@Transactional
	@Override
	public boolean registerNeedyPerson(NeedyPeople person) throws DuplicateNeedyPeopleException {
		Optional<NeedyPeople> np = needyPeople.findById(person.getNp_id());
		if(np.isPresent()) {
			throw new DuplicateNeedyPeopleException(person.getNp_id());
		}
		try {
			needyPeople.addAddress(person.getAddress().getAdd_Id(), person.getAddress().getCity(),person.getAddress().getState(), person.getAddress().getPin(), person.getAddress().getLandmark());
			needyPeople.createNeedyPerson(person.getNp_id(), person.getNp_name(), person.getPhone(), person.getFamily_income(),person.getUsername(),person.getPassword(),person.getDonationType(), person.getAddress().getAdd_Id());
			return true;
		}
		catch(SQLException e)
		{
			System.out.println(e.getMessage());
		}
		return false;
	}

	@Transactional(readOnly = true)
	@Override
	public Optional<NeedyPeople> getByUsername(String username) {
		return needyPeople.findByNp_name(username);
	}

}
