package com.mytask.service;

import com.mytask.domain.order.Tax;
import com.mytask.exeption.TaxNotExistRuntimeException;
import com.mytask.exeption.UncorrectedIdRuntimeException;
import com.mytask.repository.TaxRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class TaxServiceImpl implements TaxService {
    private TaxRepository taxRepository;

    @Autowired
    public TaxServiceImpl(TaxRepository taxRepository) {
        this.taxRepository = taxRepository;
    }

    @Override
    public Tax save(Tax tax) {
        if (tax == null) {
            throw new TaxNotExistRuntimeException(" Tax is not exist");
        }
        return taxRepository.save(tax);
    }

    @Override
    public Tax findById(Long id) {
        if (id < 0) {
            throw new UncorrectedIdRuntimeException("Id Tax must be positive");

        }
        Optional<Tax> taxFindById = taxRepository.findById(id);
        if (taxFindById.isPresent()) {
            return taxFindById.get();
        }
        throw new UncorrectedIdRuntimeException("Id Tax must be correct");
    }

    @Override
    public void update(Tax tax) {
        if (tax == null) {
            throw new TaxNotExistRuntimeException("Tax is not exist");
        }
        taxRepository.update(tax);
    }

    @Override
    public ArrayList<Tax> findAll() {
        return taxRepository.findAll();
    }

    @Override
    public Tax deleteById(Long id) {
        if (id < 0) {
            throw new UncorrectedIdRuntimeException("Id Tax must be positive");
        }
        Optional<Tax> taxDeleteById = taxRepository.deleteById(id);
        if (taxDeleteById.isPresent()) {
            return taxDeleteById.get();
        }
        throw new UncorrectedIdRuntimeException("Id Tax must be correct");
    }
}
