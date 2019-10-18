package net.javaguides.springmvc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.javaguides.springmvc.entity.Customer;
import net.javaguides.springmvc.exception.ResourceNotFoundException;
import net.javaguides.springmvc.repository.CustomerRepository;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustomerRepository customerRepository;
	
	@Override
	@Transactional
	public List<Customer> getCustomers() {
		return customerRepository.findAll();
	}

	@Override
	@Transactional
	public Customer saveCustomer(Customer theCustomer) {
		return customerRepository.save(theCustomer);
	}

	@Override
	@Transactional
	public Customer getCustomer(int id) throws ResourceNotFoundException {
		return customerRepository.findById(id).orElseThrow(
				() -> new ResourceNotFoundException(
						String.format("Customer with ID %s not exists", id))
		);
	}

	@Override
	@Transactional
	public void updateCustomer(Customer customer) throws ResourceNotFoundException {
		if (customerRepository.existsById(customer.getId())){
			customerRepository.save(customer);
		} else {
			throw new ResourceNotFoundException(
					String.format("Customer with ID %s not exists", customer.getId())
			);
		}
	}

	@Override
	@Transactional
	public void deleteCustomer(int theId) throws ResourceNotFoundException{
		customerRepository.findById(theId).orElseThrow(
				() -> new ResourceNotFoundException(
						String.format("Customer with ID %s not exists", theId))
		);
		customerRepository.deleteById(theId);
	}
}





