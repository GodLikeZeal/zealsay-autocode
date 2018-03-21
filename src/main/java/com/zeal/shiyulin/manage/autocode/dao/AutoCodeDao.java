package com.zeal.shiyulin.manage.autocode.dao;

import com.zeal.shiyulin.common.persistence.annotation.MyBatisDao;
import com.zeal.shiyulin.manage.autocode.entity.XmlTable;
import com.zeal.shiyulin.manage.autocode.entity.XmlTableColumn;


import java.util.List;
import java.util.Map;

/**
 * Created by zeal on 2016/1/22.
 */
@MyBatisDao
public interface AutoCodeDao {

    List<XmlTable> getAllTable(String dataBaseName);

    List<XmlTableColumn> getAllColumns(Map map);

    XmlTable  getTableClass(Map map);

    List<XmlTable> getAllTableClass();

    List<XmlTableColumn> getColumnClass(XmlTableColumn table);

    int insertTable(XmlTable table);

    int insertColumn(XmlTableColumn column);

    void deleteTable(XmlTable table);

    void deleteColumn(XmlTableColumn column);

    void deleteColumnByName(String name);

    /**
     * 获取所有以配置的表信息
     * @return
     */
    List<XmlTable> getAllGenTable();

    /**
     * 分页查询
     * @param table
     * @return
     */
    List<XmlTable> findByClassPaginate(XmlTable table);
    long findByClassPaginateCount(XmlTable table);
    int getNumById(int id);
    int getNumByName(String name);
    int updateTable(XmlTable table);
    int updateColumn(XmlTableColumn column);

    List<String >getDatabaseTableName();
}
