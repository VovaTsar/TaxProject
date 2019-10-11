package com.mytask.service.impl;

import com.mytask.domain.customer.Address;
import com.mytask.domain.customer.Customer;
import com.mytask.domain.customer.Role;
import com.mytask.exeption.UncorrectedIdRuntimeException;
import com.mytask.repository.CustomerRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class AdminServiceImplTest {

    private Customer vova;

    @Mock
    private CustomerRepository customerRepository;


    @InjectMocks
    private AdminServiceImpl adminService;

    @Before
    public void setUp() {
        vova = Customer.builder()
                .withName("Vova")
                .withSurname("Tsaruk")
                .withBirthday(LocalDate.of(1999, 6, 11))
                .withAddress(new Address("Uman", "South", 13))
                .withPhoneNumber("380911111111")
                .withEmail("1@gmail.com")
                .withPassword("1")
                .withRole(Role.ADMIN)
                .build();
    }

    @Test
    public void findAll() {
        Customer vasyl = Customer.builder()
                .withName("VasyL")
                .withSurname("Zaichenk")
                .withBirthday(LocalDate.of(1999, 1, 13))
                .withAddress(new Address("Uman", "South", 13))
                .withPhoneNumber("380911111111")
                .withEmail("1@gmail.com")
                .withPassword("1")
                .withRole(Role.ADMIN)
                .build();

        ArrayList<Customer> customers = new ArrayList<>();
        customers.add(vova);
        customers.add(vasyl);

        when(customerRepository.findAll()).thenReturn(customers);
        assertThat(customers, is(adminService.findAll()));
    }

    @Test
    public void shouldReturnDeleteCustomer() {
        when(customerRepository.deleteById(vova.getId())).thenReturn(Optional.ofNullable(vova));

        Customer studentActual = adminService.deleteById(vova.getId());
        assertThat(vova, is(studentActual));
    }

    @Test(expected = UncorrectedIdRuntimeException.class)
    public void shouldReturnUncorrectedIdRuntimeExceptionInFindById() {
        adminService.findById(-1L);
    }

}