/*

 */
package restapisb.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import restapisb.user.domain.Phone;

@Repository
public interface PhoneRepository extends JpaRepository<Phone, Long> {

}
