package com.topjet.manage.service.impl;

import com.topjet.manage.service.ChangeMobileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service("changeMobileService")
public class ChangeMobileServiceImpl implements ChangeMobileService {

    private static final Logger log = LoggerFactory.getLogger(ChangeMobileServiceImpl.class);


    @Autowired
    @Qualifier("payJdbcTemplate")
    protected JdbcTemplate  payJdbcTemplate;




    public void changeMobile(String olderMobile,String newMobile){
        log.info("changeMobile: "+olderMobile+" -> "+newMobile);
        //钱包
        payJdbcTemplate.execute("call TopJetPay.pro_wal_updateMobile('"+olderMobile+"','"+newMobile+"',@a)");
    }

}
