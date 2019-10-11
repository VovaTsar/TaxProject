package com.mytask.service.impl;


import com.mytask.domain.customer.Address;
import com.mytask.domain.customer.Customer;
import com.mytask.domain.customer.Role;
import com.mytask.domain.order.Tax;
import com.mytask.domain.order.impl.AdditionalWork;
import com.mytask.domain.order.impl.Currency;
import com.mytask.domain.order.impl.MainWork;
import com.mytask.exeption.CustomerNotExistRuntimeException;
import com.mytask.exeption.UncorrectedIdRuntimeException;
import com.mytask.repository.CustomerRepository;
import com.mytask.service.TaxService;
import com.mytask.util.encoder.PasswordEncoder;
import com.mytask.util.validator.UserValidator;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;

import static java.util.Optional.ofNullable;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceImplTest {

    private Customer vova;

    @Mock
    private CustomerRepository customerRepository;

    @Mock
    private UserValidator userValidator;

    @Mock
    private TaxService taxService;

    @InjectMocks
    private UserServiceImpl userService;

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

    @After
    public void resetMock() {
        reset(customerRepository);
    }

    @Test
    public void shouldReturnLoginCustomer() {
        Customer studentExpected = (Customer) vova.clone(PasswordEncoder.generateSecurePassword(vova.getPassword()));

        when(customerRepository.findByEmail(any(String.class))).thenReturn(ofNullable(studentExpected));

        Customer studentActual = userService.login(vova.getEmail(), vova.getPassword());
        assertThat(true, is(PasswordEncoder.verifyCustomerPassword(vova.getPassword(), studentActual.getPassword())));
    }

    @Test
    public void shouldReturnRegisterCustomer() {

        Customer studentExpected = (Customer) vova.clone(PasswordEncoder.generateSecurePassword(vova.getPassword()));

        when(customerRepository.save(any(Customer.class))).thenReturn(studentExpected);
        when(userValidator.validate(any(Customer.class))).thenReturn(true);

        Customer studentActual = userService.register(vova);
        assertThat(true, is(PasswordEncoder.verifyCustomerPassword(vova.getPassword(), studentActual.getPassword())));
    }

    @Test
    public void shouldReturnCustomerById() {
        when(customerRepository.findById(1L)).thenReturn(Optional.ofNullable(vova));

        Customer studentActual = userService.findById(1L);
        assertThat(vova, is(studentActual));
    }

    @Test
    public void shouldReturnAllTaxes() {
        MainWork mainWork = new MainWork(1000, Currency.PRIVILEGE, "TS");
        AdditionalWork additionalWork = new AdditionalWork(1000, Currency.PRIVILEGE, "TS");

        when(userValidator.validate(any(Customer.class))).thenReturn(true);
        when(taxService.findById(mainWork.getId())).thenReturn(mainWork);
        when(taxService.findById(additionalWork.getId())).thenReturn(additionalWork);

        userService.addTax(vova, mainWork.getId());
        userService.addTax(vova, additionalWork.getId());

        ArrayList<Tax> expectedVegetables = new ArrayList<>();
        expectedVegetables.add(mainWork);
        expectedVegetables.add(additionalWork);

        assertThat(expectedVegetables, is(userService.findAllTaxes(vova)));
    }

    @Test
    public void shouldReturnAddingTaxes() {
        MainWork mainWork = new MainWork(1000, Currency.PRIVILEGE, "TS");
        AdditionalWork additionalWork = new AdditionalWork(1000, Currency.PRIVILEGE, "TS");

        when(userValidator.validate(any(Customer.class))).thenReturn(true);
        when(taxService.findById(mainWork.getId())).thenReturn(mainWork);
        when(taxService.findById(additionalWork.getId())).thenReturn(additionalWork);

        userService.addTax(vova, mainWork.getId());
        userService.addTax(vova, additionalWork.getId());

        ArrayList<Tax> expectedVegetables = new ArrayList<>();
        expectedVegetables.add(mainWork);
        expectedVegetables.add(additionalWork);

        assertThat(expectedVegetables, is(userService.findAllTaxes(vova)));
    }

    @Test
    public void shouldReturnDeletingTaxes() {
        MainWork mainWork = new MainWork(1000, Currency.PRIVILEGE, "TS");
        AdditionalWork additionalWork = new AdditionalWork(1000, Currency.PRIVILEGE, "TS");

        when(userValidator.validate(any(Customer.class))).thenReturn(true);
        when(taxService.findById(mainWork.getId())).thenReturn(mainWork);
        when(taxService.findById(additionalWork.getId())).thenReturn(additionalWork);

        userService.addTax(vova, mainWork.getId());
        userService.addTax(vova, additionalWork.getId());
        userService.deleteTax(vova, additionalWork.getId());

        ArrayList<Tax> expectedVegetables = new ArrayList<>();
        expectedVegetables.add(mainWork);

        assertThat(expectedVegetables, is(userService.findAllTaxes(vova)));
    }

    @Test
    public void shouldReturnSortingTax() {
        MainWork mainWork = new MainWork(13000, Currency.PRIVILEGE, "TS");
        AdditionalWork additionalWork = new AdditionalWork(12000, Currency.PRIVILEGE, "TS");

        when(userValidator.validate(any(Customer.class))).thenReturn(true);
        when(taxService.findById(mainWork.getId())).thenReturn(mainWork);
        when(taxService.findById(additionalWork.getId())).thenReturn(additionalWork);

        userService.addTax(vova, mainWork.getId());
        userService.addTax(vova, additionalWork.getId());

        ArrayList<Tax> expectedVegetables = new ArrayList<>();
        expectedVegetables.add(additionalWork);
        expectedVegetables.add(mainWork);

        assertThat(expectedVegetables, is(userService.sortTax(vova)));
    }


    @Test
    public void shouldReturnSummaryOfTaxes() {
        MainWork mainWork = new MainWork(12000, Currency.PRIVILEGE, "TS");
        AdditionalWork additionalWork = new AdditionalWork(1000, Currency.PRIVILEGE, "TS");

        when(userValidator.validate(any(Customer.class))).thenReturn(true);
        when(taxService.findById(mainWork.getId())).thenReturn(mainWork);
        when(taxService.findById(additionalWork.getId())).thenReturn(additionalWork);

        userService.addTax(vova, mainWork.getId());
        userService.addTax(vova, additionalWork.getId());

        assertThat(13000, is(userService.sumOfTaxes(vova)));
    }

    @Test(expected = CustomerNotExistRuntimeException.class)
    public void shouldReturnCustomerNotExistRuntimeExceptionInRegister() {
        userService.register(null);
    }

    @Test(expected = UncorrectedIdRuntimeException.class)
    public void shouldReturnUncorrectedIdRuntimeExceptionInFindById() {
        userService.findById(-1L);
    }

    @Test(expected = CustomerNotExistRuntimeException.class)
    public void shouldReturnCustomerNotExistRuntimeExceptionInUpdate() {
        userService.update(null);
    }

    @Test(expected = CustomerNotExistRuntimeException.class)
    public void shouldReturnCustomerNotExistRuntimeExceptionInFindVegetable() {
        userService.findAllTaxes(null);
    }

    @Test(expected = CustomerNotExistRuntimeException.class)
    public void shouldReturnCustomerNotExistRuntimeExceptionInAddVegetable() {
        userService.addTax(null, 1L);
    }

    @Test(expected = CustomerNotExistRuntimeException.class)
    public void shouldReturnCustomerNotExistRuntimeExceptionInDeleteVegetable() {
        userService.deleteTax(null, 1L);
    }

    @Test(expected = CustomerNotExistRuntimeException.class)
    public void shouldReturnCustomerNotExistRuntimeExceptionInSortVegetable() {
        userService.sortTax(null);
    }


    @Test(expected = CustomerNotExistRuntimeException.class)
    public void shouldReturnCustomerNotExistRuntimeExceptionInSummaryVegetable() {
        userService.sumOfTaxes(null);
    }
}
