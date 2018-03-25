package com.zeal.shiyulin.manage.autocode.service;

import com.zeal.shiyulin.common.mapper.JaxbMapper;
import com.zeal.shiyulin.common.utils.*;
import com.zeal.shiyulin.manage.Utils.ConvertUtils;
import com.zeal.shiyulin.manage.Utils.MapUtils;
import com.zeal.shiyulin.manage.autocode.entity.*;
import com.zeal.shiyulin.paginate.PaginateDataResponse;
import com.zeal.shiyulin.utils.ConstUtils;
import com.google.common.collect.Lists;
import com.zeal.shiyulin.manage.autocode.dao.AutoCodeDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zeal on 2016/1/22.
 */
@Service
public class AutoCodeService {
    @Autowired
    AutoCodeDao autoCodeDao;

    /**
     * 获取所有已配置的表信息
     * @return
     */
    public List<XmlTable> getAllGenTable(){
        return autoCodeDao.getAllGenTable();
    }

    /**
     * 分页查询所有已配置的信息
     * @param table
     * @return
     */
    public PaginateDataResponse getAllGenTablePaginList(XmlTable table){
        List<XmlTable> entityList = autoCodeDao.findByClassPaginate(table);
        PaginateDataResponse response = new PaginateDataResponse();
        response.setStart(table.getStart());
        response.setSize(table.getSize());
        response.setSort(table.getSort());
        response.setOrder(table.getOrder());
        response.setTotal(autoCodeDao.findByClassPaginateCount(table));
        response.setData(entityList);
        return response;
    }

    public List<XmlTable> GetAllTable(String dataBaseName) {
        return autoCodeDao.getAllTable(dataBaseName);
    }

    public List<XmlTableColumn> GetAllColumn(Map map) {
        //CreateTemplete(map);
        List<XmlTableColumn>xmlTableColumnList = autoCodeDao.getAllColumns(map);
        //遍历处理，将字段下划线处拼接成小驼峰法
        if (xmlTableColumnList!=null && !xmlTableColumnList.isEmpty()){
            for (XmlTableColumn xmlTableColumn:xmlTableColumnList){
                xmlTableColumn.setJavaField(ConvertUtils.underlineToCamel2(xmlTableColumn.getJavaField()));
            }
        }
        return autoCodeDao.getAllColumns(map);
    }

    public void saveTableAndColumn(XmlTable table){

        /**
         * 更新之前先删除信息
         */
        if(!StringUtils.isNull(table.getName())){
            autoCodeDao.deleteColumnByName(table.getName());
            autoCodeDao.deleteTable(table);
        }



        //插入表信息
        int c=autoCodeDao.getNumByName(table.getName());
        if(c>0){
            autoCodeDao.updateTable(table);
        }else{
            autoCodeDao.insertTable(table);
        }

        List<XmlTableColumn> xmlTableColumns=table.getColumnList();
        int e=autoCodeDao.getNumById(Integer.parseInt(table.getId()));
        if(e>0){
            for(XmlTableColumn tableColumn : xmlTableColumns){
                tableColumn.setGenTable(table);
                autoCodeDao.updateColumn(tableColumn);
            }
        }else {
            for(XmlTableColumn tableColumn : xmlTableColumns){
                tableColumn.setGenTable(table);
                autoCodeDao.insertColumn(tableColumn);
            }
        }

    }
    public void BacthCreateTemplete(){
        List<XmlTable> xmlTableList=autoCodeDao.getAllTableClass();
        for(XmlTable table :xmlTableList){
            Map map=new HashMap();
            map.put("name",table.getName());
            map.put("moduleName",table.getComments());
            map.put("className",table.getClassName());
            CreateTemplete(map);
        }

    }

    /**
     * 单个模版保存
     * @param allRequestParam
     */
    public void CreateTempleteSigle(Map<String,String> allRequestParam){
        XmlTable xmlTable =autoCodeDao.getTableClass(allRequestParam);
        Map map=new HashMap();
        map.put("name",xmlTable.getName());
        map.put("moduleName",xmlTable.getComments());
        map.put("className",xmlTable.getClassName());
        CreateTemplete(map);
    }

    /**
     * 核心生成文件方法
     * nimq
     * @param map
     */
    public  void CreateTemplete(Map map){
        StringBuilder result = new StringBuilder();
        XmlConfig config = getConfig();
        List<XmlTemplate> templateList = getTemplateList(config, "ALL", false);
        Map<String, Object> model = new HashMap<String, Object>();
        XmlTable table=autoCodeDao.getTableClass(map);
        table.setColumnList(autoCodeDao.getColumnClass(new XmlTableColumn(new XmlTable(table.getId()))));

        String modulesName= MapUtils.FilterMapValue(map,"moduleName");
        String projectName= MapUtils.FilterMapValue(map,"projectName");
        String className=MapUtils.FilterMapValue(map,"className");
        String createBy=MapUtils.FilterMapValue(map,"createBy");

        model.put("packageName", "com.zeal.zealsay."+projectName+".modules");//工程包路径
        if(PropertiesUtils.getM_jdbcUserName().toUpperCase().equals("SDE")){
            model.put("packageName", ConstUtils.surveyPackageName);//工程包路径
        }
        model.put("packageUtilsName",ConstUtils.packageUtilsName); //常用类文件夹路径
        model.put("packagePaginateName",ConstUtils.packagePaginateName); //分页处理文件夹路径
        model.put("packageExceptionName",ConstUtils.packageExceptionName);//异常处理文件夹路径
        model.put("packageCommonName",ConstUtils.packageCommonName);//通用类路径
        model.put("packageConstantsName",ConstUtils.packageConstantsName);//常量类路径

        if(PropertiesUtils.getM_jdbcUserName().toUpperCase().equals("SDE")){
            model.put("mappingPackageName","surveymappings");//通用类路径
            model.put("baseDao","MyBatisSurveyDao");//通用类路径
        }
        else{
            model.put("mappingPackageName","mappings");//通用类路径
            model.put("baseDao","MyBatisDao");//通用类路径
        }

        model.put("lastPackageName", modulesName);
        model.put("moduleName", modulesName);
        model.put("className", className);
        model.put("ClassName", StringUtils.capitalize(className));

        model.put("functionName", table.getName());
        model.put("functionNameSimple",table.getName());
        model.put("functionAuthor", createBy);
        model.put("functionVersion", DateUtils.getDate("yyyy-MM-dd HH:mm:ss"));

        model.put("urlPrefix", ".....");
        model.put("viewPrefix",
                model.get("urlPrefix"));
        model.put("permissionPrefix", "......");

        model.put("dbType","Mysql");
        model.put("table",table);
        model.put("currentYear","2018");

        for (XmlTemplate tpl : templateList){
            result.append(generateToFile(tpl, model, true, "E:\\\\AutoCode\\\\CustomService"));
        }
    }

    /**
     * 获取模版列表，转换成XmlTemplate对象
     * @param config
     * @param category
     * @param isChildTable
     * @return
     */
    public static List<XmlTemplate> getTemplateList(XmlConfig config, String category, boolean isChildTable){
        List<XmlTemplate> templateList = Lists.newArrayList();
        if (config !=null && config.getCategoryList() != null && category !=  null){
            for (XmlCategory e : config.getCategoryList()){
                if (category.equals(e.getValue())){
                    List<String> list = null;
                    if (!isChildTable){
                        list = e.getTemplate();
                    }else{
                        list = e.getChildTableTemplate();
                    }
                    if (list != null){
                        for (String s : list){
                            if (StringUtils.startsWith(s, XmlCategory.CATEGORY_REF)){
                                templateList.addAll(getTemplateList(config, StringUtils.replace(s, XmlCategory.CATEGORY_REF, ""), false));
                            }else{
                                XmlTemplate template = fileToObject(s, XmlTemplate.class);
                                if (template != null){
                                    templateList.add(template);
                                }
                            }
                        }
                    }
                    break;
                }
            }
        }
        return templateList;
    }

    /**
     * 把XMl对象转换程实体类
     * @param fileName
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> T fileToObject(String fileName, Class<?> clazz){
        try {
            String pathName = "/template/" + fileName;
//			logger.debug("File to object: {}", pathName);
            Resource resource = new ClassPathResource(pathName);
            InputStream is = resource.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(is, "UTF-8"));
            StringBuilder sb = new StringBuilder();
            while (true) {
                String line = br.readLine();
                if (line == null){
                    break;
                }
                sb.append(line).append("\r\n");
            }
            if (is != null) {
                is.close();
            }
            if (br != null) {
                br.close();
            }
            return (T) JaxbMapper.fromXml(sb.toString(), clazz);
        } catch (IOException e) {

        }
        return null;
    }

    /**
     * 获取代码生成配置对象
     * @return
     */
    public static XmlConfig getConfig(){
        return fileToObject("config.xml", XmlConfig.class);
    }

    public static String generateToFile(XmlTemplate tpl, Map<String, Object> model, boolean isReplaceFile, String proPath){
        try{
            //String proPath = StringUtils.getProjectPath();
            String tplPath = tpl.getFilePath();
            String fileName = proPath + File.separator
                    + StringUtils.replaceEach(FreeMarkers.renderString(tplPath + "/", model),
                    new String[]{"//", "/", "."}, new String[]{File.separator, File.separator, File.separator})
                    + FreeMarkers.renderString(tpl.getFileName(), model);

            // 获取生成文件内容
            String content = FreeMarkers.renderString(StringUtils.trimToEmpty(tpl.getContent()), model);
            //logger.debug(" content === \r\n" + content);

            // 如果选择替换文件，则删除原文件
            if (isReplaceFile){
                FileUtils.deleteFile(fileName);
            }

            // 创建并写入文件
            if (FileUtils.createFile(fileName)){
                FileUtils.writeToFile(fileName, content, true);
                return "生成成功："+fileName+"<br/>";
            }else{
                return "文件已存在："+fileName+"<br/>";
            }
        }catch(Exception e){
           throw new RuntimeException(e);
        }
    }

     public List<String >getDatabaseTableName(){
        return  autoCodeDao.getDatabaseTableName();
    }


}
