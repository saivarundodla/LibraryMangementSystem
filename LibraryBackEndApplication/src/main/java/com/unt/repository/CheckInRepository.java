package com.unt.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.unt.model.*;

@Repository
public interface CheckInRepository extends CrudRepository<CheckIn, Integer>  {

    @Query(value="select * from checkin where request_approval=0 and returned_date is null", nativeQuery = true)
    public Iterable<CheckIn> getRequestApproval();

    @Query(value="select * from checkin where user_id=?1", nativeQuery = true)
    public Iterable<CheckIn> getAllUser(Integer userId);

    @Query(value="select * from checkin where check_in_id=?1", nativeQuery = true)
    public CheckIn getCheckInId(Integer checkId);

    @Query(value="select * from checkin where book_id=?1", nativeQuery = true)
    public CheckIn getCheckInWithBookId(Integer bookId);
}
