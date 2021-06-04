package com.list.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.list.entity.SubscriptionMaster;
@Repository
public interface SubscriptionRepository extends JpaRepository<SubscriptionMaster,String>{
	public SubscriptionMaster findByEmail(String email);
}
