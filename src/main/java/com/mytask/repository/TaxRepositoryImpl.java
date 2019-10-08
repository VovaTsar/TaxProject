package com.mytask.repository;

import com.mytask.domain.order.Tax;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Repository
public class TaxRepositoryImpl implements TaxRepository {

    private Map<Long, Tax> idToTaxes = new HashMap<>();
    private static Long counter = 0L;


    @Override
    public Tax save(Tax tax) {

        return idToTaxes.put(++counter, tax);
    }


    @Override
    public Optional<Tax> findById(Long id) {

        return Optional.ofNullable(idToTaxes.get(id));
    }

    @Override
    public ArrayList<Tax> findAll() {
        return new ArrayList<>(idToTaxes.values());
    }


    @Override
    public void update(Tax tax) {
        idToTaxes.replace(tax.getId(), tax);
    }

    @Override
    public Optional<Tax> deleteById(Long id) {

        return Optional.ofNullable(idToTaxes.remove(id));
    }
}
