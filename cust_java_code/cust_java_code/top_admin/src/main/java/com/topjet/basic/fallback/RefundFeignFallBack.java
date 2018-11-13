package com.topjet.basic.fallback;

import com.topjet.basic.RefundFeignService;
import com.topjet.cloud.manage.custservice.order.bean.UpdateRefundCsInfoRTS;
import com.topjet.cloud.manage.custservice.order.bean.UpdateRefundCsInfoVRU;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by liyj on 2017/10/24.
 */
public class RefundFeignFallBack implements RefundFeignService{

    private static Logger log = LoggerFactory.getLogger(RefundFeignFallBack.class);

    @Override
    public UpdateRefundCsInfoVRU UpdateRefundCsInfo(UpdateRefundCsInfoRTS rts) {
        log.error("RefundFeignFallBack ERROR: UpdateRefundCsInfo" + rts );
        log.error("请求退款服务发生错误 ERROR: UpdateRefundCsInfo" + rts );
        return null;
    }
}
