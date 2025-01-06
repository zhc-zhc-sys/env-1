package com.ruoyi.web.controller.business;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.system.domain.PtEvaluateInfo;
import com.ruoyi.system.service.IPtEvaluateInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/evaluate")
public class PtEvaluateInfoController extends BaseController {

    @Autowired
    private IPtEvaluateInfoService evaluateInfoService;


    /**
     * 获取评估列表
     */
    @GetMapping("/list")
    public AjaxResult list(PtEvaluateInfo info)
    {
        LambdaQueryWrapper<PtEvaluateInfo> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(PtEvaluateInfo::getDelFlag, 0);
        if (StringUtils.isNotEmpty(info.getEvaluateName())){
            queryWrapper.like(PtEvaluateInfo::getEvaluateName, info.getEvaluateName());
        }
        List<PtEvaluateInfo> list = evaluateInfoService.list(queryWrapper);
        return success(list);
    }

    /**
     * 获取评估列表
     */
    @GetMapping("/page")
    public TableDataInfo page(PtEvaluateInfo info)
    {
        startPage();
        LambdaQueryWrapper<PtEvaluateInfo> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(PtEvaluateInfo::getDelFlag, 0);
        if (StringUtils.isNotEmpty(info.getEvaluateName())){
            queryWrapper.like(PtEvaluateInfo::getEvaluateName, info.getEvaluateName());
        }
        queryWrapper.eq(PtEvaluateInfo::getCreateBy, getUserId());
        List<PtEvaluateInfo> list = evaluateInfoService.list(queryWrapper);
        return getDataTable(list);
    }


    /**
     * 新增
     */
//    @Log(title = "新增", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    public AjaxResult add(@Validated @RequestBody PtEvaluateInfo info)
    {
        info.setCreateBy(getUserId().toString());
        return toAjax(evaluateInfoService.save(info));
    }

    /**
     * 修改
     */
//    @Log(title = "修改", businessType = BusinessType.UPDATE)
    @PutMapping("/update")
    public AjaxResult edit(@Validated @RequestBody PtEvaluateInfo info)
    {
        if (info.getId() == null) {
            return error("请选择要修改的数据");
        }
        info.setCreateBy(getUserId().toString());
        return toAjax(evaluateInfoService.updateById(info));
    }

    /**
     * 删除
     */
    @DeleteMapping("/del/{id}")
    public AjaxResult remove(@PathVariable Long id)
    {
        if (evaluateInfoService.getById(id) == null)
        {
            return warn("要删除的数据不存在");
        }
        PtEvaluateInfo info = new PtEvaluateInfo();
        info.setDelFlag("1");
        info.setId(id);
        return toAjax(evaluateInfoService.updateById(info));
    }


    /**
     * 删除
     */
    @PostMapping("/copy/{id}")
    public AjaxResult copy(@PathVariable Long id)
    {
        PtEvaluateInfo byId = evaluateInfoService.getById(id);
        if (byId == null)
        {
            return warn("要复制的数据不存在");
        }
        byId.setId(null);
        byId.setCreateTime(new Date());
        byId.setUpdateTime(new Date());
        return toAjax(evaluateInfoService.save(byId));
    }
}
