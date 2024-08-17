package com.example.homework_spring_data_jpa.service.serviceImpl;

import com.example.homework_spring_data_jpa.dto.request.CustomerRequest;
import com.example.homework_spring_data_jpa.dto.response.CustomerResponse;
import com.example.homework_spring_data_jpa.entity.Customer;
import com.example.homework_spring_data_jpa.entity.Email;
import com.example.homework_spring_data_jpa.repository.CustomerRepository;
import com.example.homework_spring_data_jpa.repository.EmailRepository;
import com.example.homework_spring_data_jpa.service.CustomerService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final EmailRepository emailRepository;
    @Override
    public CustomerResponse addCustomer(CustomerRequest customerRequest) {
        return customerRepository.save(customerRequest.toEntity()).toResponse();
    }

    @Override
    public List<CustomerResponse> getAllCustomer(Sort.Direction orderBy, String sortBy, Integer page, Integer size) {
        Sort sort = Sort.by(orderBy, sortBy);
        Pageable pageable = PageRequest.of(page, size, sort);
        Page<Customer> customers = customerRepository.findAll(pageable);

        return customers.getContent().stream().map(Customer::toResponse).toList();
    }

    @Override
    public CustomerResponse getCustomerById(Integer id) {
        return customerRepository.findById(id).orElseThrow().toResponse();
    }

    @Override
    public CustomerResponse updateCustomer(Integer id, CustomerRequest customerRequest) {

        Customer editCustomer = customerRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Customer not found"));

        editCustomer.setCustomerName(customerRequest.getCustomerName());
        editCustomer.setAddress(customerRequest.getAddress());
        editCustomer.setPhoneNumber(customerRequest.getPhoneNumber());

        if (editCustomer.getEmail() != null) {
            editCustomer.getEmail().setEmail(customerRequest.getEmail());
            emailRepository.save(editCustomer.getEmail());
        } else {
            Email newEmail = new Email(null, customerRequest.getEmail(), editCustomer);
            editCustomer.setEmail(newEmail);
        }

        Customer updatedCustomer = customerRepository.save(editCustomer);

        return updatedCustomer.toResponse();
    }

    @Override
    public void deleteCustomer(Integer id) {
        customerRepository.deleteById(id);
    }
}
