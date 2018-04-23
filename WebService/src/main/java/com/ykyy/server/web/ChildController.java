package com.ykyy.server.web;

import com.ykyy.server.bean.ChildBean;
import com.ykyy.server.bean.JsonResult;
import com.ykyy.server.service.ChildService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/child")
@Api(value = "/child", description = "儿童")
@CacheConfig(cacheNames="Cache")
@Transactional(propagation = Propagation.REQUIRED,readOnly=false,rollbackFor = Exception.class)
public class ChildController
{
    @Autowired
    ChildService childService;

    /**
     * @Author:owen
     * @Description:添加儿童
     * @Date:Create in 20:38 2018/4/23
     * @Modified By:
     */
    @PostMapping("/add")
    ResponseEntity<JsonResult> add(@RequestBody @ApiParam(name = "childBean") ChildBean childBean)
    {

        return null;
    }


    /**
     * @Author:owen
     * @Description:查询userid的儿童个数
     * @Date:Create in 20:39 2018/4/23
     * @Modified By:
     */
    @GetMapping("/getChildCountByUserId/{users_id}")
    ResponseEntity<JsonResult> getChildCountByUserId(@PathVariable(value = "users_id") @ApiParam(name = "用户id") int users_id)
    {


        return null;
    }

    /**
     * @Author:owen
     * @Description:查询userid的所有儿童
     * @Date:Create in 20:44 2018/4/23
     * @Modified By:
     */
    @GetMapping("/getChildByUserId/{users_id}")
    ResponseEntity<JsonResult> getChildByUserId(@PathVariable(value = "users_id") @ApiParam(name = "用户id") int users_id)
    {
        return null;
    }


    /**
     * @Author:owen
     * @Description:查询产品报名人数
     * @Date:Create in 20:39 2018/4/23
     * @Modified By:
     */
    @GetMapping("/getChildCountByProductId/{product_id}")
    ResponseEntity<JsonResult> getChildCountByProductId(@PathVariable(value = "product_id") @ApiParam(name = "产品I") int product_id)
    {
        return null;
    }

    /**
     * @Author:owen
     * @Description:查询产品报名的所有孩子
     * @Date:Create in 20:40 2018/4/23
     * @Modified By:
     */
    @GetMapping("/getChildByProductId/{product_id}")
    ResponseEntity<JsonResult> getChildByProductId(@PathVariable(value = "product_id") @ApiParam(name = "产品I") int product_id)
    {
        return null;
    }

    /**
     * @Author:owen
     * @Description:查询该孩子报的所有产品
     * @Date:Create in 20:41 2018/4/23
     * @Modified By:
     */
    @GetMapping("/getProductByChildId/{child_id}")
    ResponseEntity<JsonResult> getProductByChildId(@PathVariable(value = "child_id") @ApiParam(name = "儿童I")int child_id)
    {
        return null;
    }

    /**
     * @Author:owen
     * @Description:更新儿童
     * @Date:Create in 20:46 2018/4/23
     * @Modified By:
     */
    @PostMapping("/updateChild")
    ResponseEntity<JsonResult> UpdateChild(@RequestBody @ApiParam(name = "childBean") ChildBean childBean)
    {
        return null;
    }

    /**
     * @Author:owen
     * @Description:删除儿童
     * @Date:Create in 20:47 2018/4/23
     * @Modified By:
     */
    @GetMapping("/deleteChild/{users_id}/{child_id}")
    ResponseEntity<JsonResult> deleteChild(@PathVariable(value = "users_id") @ApiParam(name = "用户id") int users_id, @PathVariable(value = "child_id") @ApiParam(name = "儿童I") int child_id)
    {
        return null;
    }
}
