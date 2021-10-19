/*

 */
package restapisb.user.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import restapisb.user.PhoneRepository;
import restapisb.user.PhoneService;
import restapisb.user.domain.Phone;

@Service
public class PhoneServiceImpl implements PhoneService {

  @Autowired
  private PhoneRepository ur;

  @Override
  public Phone createPhone(Phone phone) throws Exception {
    Phone u;
    try {
      u = ur.save(phone);
    } catch (Exception e) {
      throw e;
    }
    return u;
  }


}
