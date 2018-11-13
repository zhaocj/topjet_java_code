package com.topjet.basic.fallback;

import com.topjet.basic.TruckFeignService;
import com.topjet.cloud.manage.custservice.truck.bean.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @Author: zhaocj
 * @Description:
 * @Date: 2017-09-25 9:46
 */

public class TruckFeignFallBack implements TruckFeignService{

    private static Logger log = LoggerFactory.getLogger(TruckFeignService.class);

    @Override
    public UpdateTurckVRU updateTruck(UpdateTurckRTS rts) {
        log.error("TruckFeignFallBack ERROR: updateUserStatusById" + rts );
        log.error("请求车辆服务错误 ERROR: updateUserStatusById" + rts );
        return null;
    }

    @Override
    public UpdateDriverTruckInfoVRU updateDriverTruckInfo(UpdateDriverTruckInfoRTS rts) {
        log.error("TruckFeignFallBack ERROR: updateDriverTruckInfo" + rts );
        log.error("请求车辆服务错误 ERROR: updateDriverTruckInfo" + rts );
        return null;
    }

    @Override
    public UpdateOwnerTruckInfoVRU updateOwnerTruckInfo(UpdateOwnerTruckInfoRTS updateOwnerTruckInfoRTS) {
        log.error("请求车辆服务错误 ERROR: updateOwnerTruckInfo" + updateOwnerTruckInfoRTS );
        return null;
    }

    @Override
    public UpdateDriverBussinessInfoVRU updateDriverBussinessInfo(UpdateDriverBussinessInfoRTS updateDriverBussinessInfoRTS) {
        log.error("请求经营路线服务错误 ERROR: updateDriverBussinessInfo" + updateDriverBussinessInfoRTS );
        return null;
    }
}
