package br.com.pavao.personal_projects.service;

import br.com.pavao.personal_projects.exception.CriptoExistsException;
import br.com.pavao.personal_projects.exception.EmailExistsException;
import br.com.pavao.personal_projects.exception.ServiceException;
import br.com.pavao.personal_projects.model.User;
import br.com.pavao.personal_projects.repositories.UserRepository;
import br.com.pavao.personal_projects.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;
import java.util.List;

@Service
public class ServiceUser {

        private UserRepository userRepository;

        @Autowired
        public ServiceUser(UserRepository userRepository) {this.userRepository = userRepository;}

        public List<User> findAll() {
            return userRepository.findAll();
        }

        public void saveUser(User user) throws Exception {

            try {

                if(userRepository.findByEmail(user.getEmail()) !=null) {
                    throw new EmailExistsException("Este email já esta cadastrado: " + user.getEmail());
                }

                user.setPassword(Util.md5(user.getPassword()));

            } catch (NoSuchAlgorithmException e) {
                throw new CriptoExistsException("Error na criptografia da senha");
            }
            userRepository.save(user);
        }

        public User loginUser(String user, String password) throws ServiceException {

            return userRepository.login(user, password);
        }
}

