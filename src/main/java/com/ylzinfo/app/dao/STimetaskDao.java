package com.ylzinfo.app.dao;

import com.ylzinfo.app.model.STimetask;
import org.apache.ibatis.annotations.Param;

import java.util.ArrayList;

public interface STimetaskDao {

    boolean addTimeTaskTA(@Param("task") STimetask sTietask);
    ArrayList<STimetask> getTimeTaskListTA();
    ArrayList<STimetask> getExecutableTaskTA();
    void deleteTaskByNameTA(@Param("jobName") String jobName,@Param("groupName") String groupName);
    void updateByNameTA(@Param("task") STimetask sTimetask);
}
