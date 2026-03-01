package com.example.BookShopApp;
import org.springframework.data.jpa.repository.JpaRepository;
public interface LoginRepository extends JpaRepository<Login,String>
{
	Login findByUsernameAndPassword(String username, String password);
}

