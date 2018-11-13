package com.topjet.basic;

import com.topjet.basic.fallback.UserFeignFallBack;
import com.topjet.cloud.manage.constant.ManageServiceConstant;
import com.topjet.cloud.manage.custservice.truck.bean.*;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author: zhaocj
 * @Description:
 * @Date: 2017-09-25 9:41
 */

@FeignClient(name = ManageServiceConstant.TRUCK_SERVICE_NAME ,fallback = UserFeignFallBack.class)
public interface TruckFeignService {

    @PostMapping({"/cust-truck/updateturck"})
    UpdateTurckVRU updateTruck(@RequestBody UpdateTurckRTS turckRTS);

    @PostMapping({"/cust-truck/updatedrivertruckinfo"})
    UpdateDriverTruckInfoVRU updateDriverTruckInfo(@RequestBody UpdateDriverTruckInfoRTS updateDriverTruckInfoRTS);

    @PostMapping({"/cust-truck/updateownertruckinfo"})
    UpdateOwnerTruckInfoVRU updateOwnerTruckInfo(@RequestBody UpdateOwnerTruckInfoRTS updateOwnerTruckInfoRTS);

    @PostMapping("/cust-truck/updatedriverbussinessInfo")
    UpdateDriverBussinessInfoVRU updateDriverBussinessInfo(@RequestBody UpdateDriverBussinessInfoRTS updateDriverBussinessInfoRTS);

}
