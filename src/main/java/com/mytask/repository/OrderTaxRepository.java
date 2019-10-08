package com.mytask.repository;

import com.mytask.domain.order.Report;
import com.mytask.domain.order.Taxes;

public interface OrderTaxRepository extends CrudRepository<Taxes,Long> {
}
