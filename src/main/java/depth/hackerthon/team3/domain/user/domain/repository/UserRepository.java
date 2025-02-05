package depth.hackerthon.team3.domain.user.domain.repository;

import depth.hackerthon.team3.domain.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByDeviceId(String deviceId);
}
