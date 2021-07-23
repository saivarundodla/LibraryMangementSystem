package com.unt.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.unt.model.User;

@Repository
public interface UserRepository extends CrudRepository<User, Integer>   {

	@Query(value="select * from user u where u.login_id = ?1 and u.password = ?2", nativeQuery = true)
	public User checkCredentials(String loginId, String password);

	@Query(value="select u.user_id,u.name,u.email,u.contact,u.type, count(*) as no_of_books_taken" +
			", sum(case when lower(request_approval) = 1 then 1 else 0 end) as no_of_books_approved " +
			", sum(case when lower(request_approval)='n' or request_approval = 0 then 1 else 0 end) as no_of_books_pending " +
			", sum(case when returned_date is not null then 1 else 0 end) as no_of_books_returned " +
			", sum(case when returned_date is null then 1 else 0 end) as no_of_non_returned_books " +
			" from user u join checkin c on u.user_id=c.user_id and c.user_id = ?1" +
			" group by u.user_id,u.name,u.email,u.contact,u.type", nativeQuery = true)
	public String getUserDetails(int userId);

	@Query(value="select u.* from user u where u.user_id=?1" , nativeQuery = true)
	public User getUser(int userId);

	@Query(value="select u.user_id, name, email, contact, type, SUM((CASE WHEN request_approval=0 THEN 1 end)) as not_approved\n" +
			", SUM(CASE WHEN request_approval!=0 THEN 1 ELSE 0 end) as approved from user u join checkin c on 1=1 and u.user_id = ?1\n" +
			"group by u.user_id, name, email, contact, type;", nativeQuery = true)
	public String getLibUserDetails(int userId);
}
