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
package restapisb.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import restapisb.user.domain.Phone;

@Repository
public interface PhoneRepository extends JpaRepository<Phone, Long> {

}
