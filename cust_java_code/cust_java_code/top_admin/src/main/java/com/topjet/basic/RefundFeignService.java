package com.topjet.basic;

import com.topjet.basic.fallback.RefundFeignFallBack;
import com.topjet.cloud.manage.constant.ManageServiceConstant;
import com.topjet.cloud.manage.custservice.order.bean.UpdateRefundCsInfoRTS;
import com.topjet.cloud.manage.custservice.order.bean.UpdateRefundCsInfoVRU;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * Created by liyj on 2017/10/22.
 */
@FeignClient(name = ManageServiceConstant.ORDER_SERVICE_NAME ,fallback = RefundFeignFallBack.class)
public interface RefundFeignService {

    /**
     * 修改退款——客服信息
     * @param request
     * @return
     */
    @PostMapping("/cust-order/updateRefundCsInfo")
    UpdateRefundCsInfoVRU UpdateRefundCsInfo(UpdateRefundCsInfoRTS request);

}
