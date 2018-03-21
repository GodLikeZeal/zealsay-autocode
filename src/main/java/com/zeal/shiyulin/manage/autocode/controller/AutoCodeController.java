package com.zeal.shiyulin.manage.autocode.controller;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.zeal.shiyulin.common.web.BaseController;
import com.zeal.shiyulin.manage.autocode.entity.XmlTable;
import com.zeal.shiyulin.manage.autocode.entity.XmlTableColumn;
import com.zeal.shiyulin.manage.autocode.service.AutoCodeService;
import com.zeal.shiyulin.paginate.PaginateDataResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by zeal on 2016/1/22.
 */
@RestController
public class AutoCodeController extends BaseController {

    @Autowired
    AutoCodeService autoCodeService;

    // @RequestMapping(value="/test/autocode",method= RequestMethod.GET,produces = "text/javascript;charset=UTF-8")
    @RequestMapping(value = "/test/autocode", method = RequestMethod.GET, produces = "application/json")
    public String GetAllCount(HttpServletRequest resquest, HttpServletResponse response) {
        //       /*// Long t=autoCodeService.GetAllCount()
        return "";
    }

    @RequestMapping(value = "autocode/getAllTable", method = RequestMethod.GET, produces = "application/json")
    @JsonInclude(JsonInclude.Include.ALWAYS)
    public List<XmlTable> getAllTable(HttpServletRequest resquest, HttpServletResponse response) {
        List<XmlTable> tables = autoCodeService.GetAllTable(resquest.getParameter("dataBaseName"));
        return tables;
    }

    /**
     * 获取所有已配置的表信息
     * 2017-01-16 nimq
     * @param resquest
     * @param response
     * @return
     */
    @RequestMapping(value = "autocode/getAllGenTable", method = RequestMethod.GET, produces = "application/json")
    public PaginateDataResponse getAllGenTable(@RequestParam Map<String,String> allRequestParam,
                                         HttpServletRequest resquest, HttpServletResponse response) {

        XmlTable table=new XmlTable();
        if(allRequestParam.containsKey("size")){
            table.setSize(Integer.valueOf(allRequestParam.get("size")));
        }
        if(allRequestParam.containsKey("start")){
            table.setStart(Integer.valueOf(allRequestParam.get("start")));
        }
        if(allRequestParam.containsKey("order")){
            table.setOrder(String.valueOf(allRequestParam.get("order")));
        }
        if(allRequestParam.containsKey("sort")){
            table.setSort(String.valueOf(allRequestParam.get("sort")));
        }

        PaginateDataResponse tables = autoCodeService.getAllGenTablePaginList(table);
        return tables;
    }

    @RequestMapping(value="autocode/getAllColumn",method = RequestMethod.GET,produces = "application/json")
    public List<XmlTableColumn> getAllColumn(@RequestParam Map<String,String> allRequestParam, HttpServletRequest resquest, HttpServletResponse response){
        return  autoCodeService.GetAllColumn(allRequestParam);
    }

    @RequestMapping(value="autocode/save" , method = RequestMethod.POST,produces = "application/json")
    public void saveTable(@RequestBody XmlTable table,HttpServletResponse response,HttpServletRequest request){
        autoCodeService.saveTableAndColumn(table);
    }

    @RequestMapping(value="autocode/saveClass",method = RequestMethod.GET,produces = "applicaiotn/json")
    public void saveTable(@RequestParam Map<String,String> map,HttpServletRequest request,HttpServletResponse response){
        autoCodeService.CreateTemplete(map);
    }

    @RequestMapping(value="autocode/saveBatchClass",method = RequestMethod.GET,produces = "applicaiotn/json")
    public void saveBatchTable(HttpServletRequest request,HttpServletResponse response){
        autoCodeService.BacthCreateTemplete();
    }

    /**
     * 保存指定的配置信息
     * @param request
     * @param response
     */
    @RequestMapping(value="autocode/saveBatchClassById",method = RequestMethod.GET,produces = "applicaiotn/json")
    public void saveBatchTableById(
            @RequestParam Map<String,String> allRequestParam,
            HttpServletRequest request,HttpServletResponse response){
        autoCodeService.CreateTempleteSigle(allRequestParam);
    }

    /**
     * 获取数据库名称
     * @param request
     * @param response
     */
    @RequestMapping(value="autocode/getDatabaseTableName",method = RequestMethod.GET)
    public List<String> getDatabaseTableName(
            @RequestParam Map< String,String> allRequestParam,
            HttpServletRequest request,HttpServletResponse response){
        List<String>list=autoCodeService.getDatabaseTableName();
        return list;
    }

}
