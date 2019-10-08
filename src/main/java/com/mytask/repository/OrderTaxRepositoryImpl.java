package com.mytask.repository;

import com.mytask.domain.order.Taxes;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Repository
public class OrderTaxRepositoryImpl implements OrderTaxRepository {

    private Map<Long, Taxes> idToTaxes = new HashMap<>();
    private static Long counter = 0L;


    @Override
    public Taxes save(Taxes taxes) {

        return idToTaxes.put(++counter, taxes);
    }


    @Override
    public Optional<Taxes> findById(Long id) {

        return Optional.ofNullable(idToTaxes.get(id));
    }

    @Override
    public ArrayList<Taxes> findAll() {
        return new ArrayList<>(idToTaxes.values());
    }


    @Override
    public void update(Taxes taxes) {
        idToTaxes.replace(taxes.getId(), taxes);
    }

    @Override
    public Optional<Taxes> deleteById(Long id) {

        return Optional.ofNullable(idToTaxes.remove(id));
    }
}
