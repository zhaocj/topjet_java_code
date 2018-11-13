package com.topjet.basic.fallback;

import com.topjet.basic.WalletFeginService;
import com.topjet.cloud.manage.service.basic.bean.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @Author: zhaocj
 * @Description:
 * @Date: 2017-09-28 14:00
 */

public class WalletFeignFallBack implements WalletFeginService{

    private static Logger log = LoggerFactory.getLogger(WalletFeignFallBack.class);


    @Override
    public CreateWalletVRU createWallet(CreateWalletRTS createWalletRTS) {
        log.error("WalletFeignFallBack ERROR: createWallet " + createWalletRTS );
        return null;
    }
    @Override
    public RefundVRU refund(RefundRTS refundRTS) {
        log.error("WalletFeignFallBack ERROR: refund" + refundRTS );
        return null;
    }

    @Override
    public GetBalanceVRU getBalance(GetBalanceRTS getBalanceRTS) {
        log.error("WalletFeignFallBack ERROR: getbalance" + getBalanceRTS );
        return null;
    }
}
