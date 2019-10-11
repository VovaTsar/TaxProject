package com.mytask.service.impl;


import com.mytask.domain.order.Tax;
import com.mytask.domain.order.impl.AdditionalWork;
import com.mytask.domain.order.impl.Currency;
import com.mytask.domain.order.impl.MainWork;
import com.mytask.exeption.TaxNotExistRuntimeException;
import com.mytask.exeption.UncorrectedIdRuntimeException;
import com.mytask.repository.TaxRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;


import java.util.ArrayList;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TaxServiceImplTest {

    @Mock
    private TaxRepository taxRepository;

    @InjectMocks
    private TaxServiceImpl taxService;

    private Tax tax;

    @Before
    public void setUp() {
        tax = new MainWork(4000, Currency.PRIVILEGE, "TS");
    }

    @Test
    public void shouldReturnSavedTax() {
        when(taxRepository.save(tax)).thenReturn(tax);

        Tax taxActual = taxService.save(tax);
        assertThat(tax, is(taxActual));
    }

    @Test
    public void shouldReturnTaxById() {
        when(taxRepository.findById(1L)).thenReturn(Optional.ofNullable(tax));

        Tax taxActual = taxService.findById(1L);
        assertThat(tax, is(taxActual));
    }

    @Test
    public void shouldReturnAllTaxes() {
        Tax tax = new AdditionalWork(300, Currency.PRIVILEGE, "TS");
        ArrayList<Tax> taxes = new ArrayList<>();
        taxes.add(this.tax);
        taxes.add(tax);

        when(taxRepository.findAll()).thenReturn(taxes);

        ArrayList<Tax> taxActual = taxService.findAll();
        assertThat(taxes, is(taxActual));
    }


    @Test
    public void shouldReturnDeletedTaxById() {
        when(taxRepository.deleteById(1L)).thenReturn(Optional.ofNullable(tax));

        Tax taxActual = taxService.deleteById(1L);
        assertThat(tax, is(taxActual));
    }

    @Test(expected = TaxNotExistRuntimeException.class)
    public void shouldReturnTaxNotExistRuntimeExceptionInSave() {
        taxService.save(null);
    }

    @Test(expected = UncorrectedIdRuntimeException.class)
    public void shouldReturnUncorrectedIdRuntimeExceptionInFindById() {
        taxService.findById(-1L);
    }

    @Test(expected = TaxNotExistRuntimeException.class)
    public void shouldReturnVegetableNotExistRuntimeExceptionInUpdate() {
        taxService.update(null);
    }

    @Test(expected = UncorrectedIdRuntimeException.class)
    public void shouldReturnUncorrectedIdRuntimeExceptionInDeleteById() {
        taxService.deleteById(-1L);
    }

}