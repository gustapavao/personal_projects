package br.com.pavao.personal_projects.repositories;



import br.com.pavao.personal_projects.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {

}
//import br.com.pavao.personal_projects.model.User;
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.stereotype.Repository;
//
//@Repository
//public interface UserRepository extends JpaRepository<User, Long> {
//    @Query("select e from User e where e.email = :email")
//    public User findByEmail(String email);
//
//    @Query("select l from User l where l.email = :email and l.password = :senha")
//    public User login(String user, String password);
//}
