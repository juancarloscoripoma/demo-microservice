package com.soft.cli.oauthservice.core.repository;

import com.soft.cli.oauthservice.core.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<Account,String> {
    @Query("SELECT distinct a FROM Account a left join fetch a.authorities WHERE a.userName = :userName")
    Account findByUsername(@Param("userName") String userName);

    Account findOneById(Integer id);
}
