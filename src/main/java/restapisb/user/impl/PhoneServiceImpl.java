/*
 * Copyright (C) Intraway Corporation - All Rights Reserved (2020)
 *
 * Unauthorized copying of this file, via any medium is strictly prohibited
 *
 * Proprietary and confidential
 *
 * This file is subject to the terms and conditions defined in file LICENSE.txt, which is part of this source code
 * package.
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
