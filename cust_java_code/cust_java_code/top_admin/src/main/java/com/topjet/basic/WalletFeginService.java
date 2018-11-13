package com.topjet.basic;

import com.topjet.basic.fallback.WalletFeignFallBack;
import com.topjet.cloud.manage.constant.ManageServiceConstant;
import com.topjet.cloud.manage.service.basic.bean.*;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @Author: zhaocj
 * @Description: 钱包服务
 * @Date: 2017-09-28 13:59
 */

@FeignClient(name = ManageServiceConstant.BASIC_SERVICE_NAME,fallback = WalletFeignFallBack.class)
public interface WalletFeginService {
    /**
     * 生成钱包账号
     * @param
     * @return
     */
    @PostMapping("/feign-wallet/createwallet")
    CreateWalletVRU createWallet(@RequestBody CreateWalletRTS createWalletRTS);

    /**
     * 退款  (账单号,退款原因)
     * @param
     * @return
     */
    @PostMapping("/feign-wallet/refund")
    RefundVRU refund(@RequestBody RefundRTS refundRTS);

    /**
     * 查询钱包余额
     * @param
     * @return
     */
    @PostMapping("/feign-wallet/getbalance")
    GetBalanceVRU getBalance(@RequestBody GetBalanceRTS getBalanceRTS);
}
