package com.applaudostudios.interview.sale;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for Sale entity CRUD Operations.
 * @author igorzelaya
 *
 */
@Repository
public interface SaleRepository extends CrudRepository<Sale, String>{

	
}
