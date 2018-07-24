package com.crm.core.pem.service;

import com.crm.core.pem.dao.PemDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class PemServiceImpl implements PemService{

    @Autowired
    private PemDao pemDao;

    @Override
    public String getPublicKey(){
        return pemDao.getPublicKey();
    }
}
