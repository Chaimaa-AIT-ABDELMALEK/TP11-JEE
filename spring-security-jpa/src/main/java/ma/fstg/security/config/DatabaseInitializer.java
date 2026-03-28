package ma.fstg.security.config;

import ma.fstg.security.entities.Role;
import ma.fstg.security.entities.User;
import ma.fstg.security.repositories.RoleRepository;
import ma.fstg.security.repositories.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DatabaseInitializer {

    @Bean
    CommandLineRunner init(RoleRepository roleRepo,
                           UserRepository userRepo,
                           BCryptPasswordEncoder encoder) {

        return args -> {
            if (userRepo.count() != 0) {
                return;
            }

            Role roleAdmin = new Role();
            roleAdmin.setId(null);
            roleAdmin.setName("ROLE_ADMIN");

            Role roleUser = new Role();
            roleUser.setId(null);
            roleUser.setName("ROLE_USER");

            roleAdmin = roleRepo.save(roleAdmin);
            roleUser = roleRepo.save(roleUser);

            User adminAccount = new User();
            adminAccount.setId(null);
            adminAccount.setUsername("admin");
            adminAccount.setPassword(encoder.encode("1234"));
            adminAccount.setActive(true);
            adminAccount.setRoles(new ArrayList<>(List.of(roleAdmin, roleUser)));

            User normalAccount = new User();
            normalAccount.setId(null);
            normalAccount.setUsername("user");
            normalAccount.setPassword(encoder.encode("1111"));
            normalAccount.setActive(true);
            normalAccount.setRoles(new ArrayList<>(List.of(roleUser)));

            userRepo.save(adminAccount);
            userRepo.save(normalAccount);
        };
    }
}