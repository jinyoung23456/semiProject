package com.ohgiraffers.dosirak.admin.suitBox.model.dao;

import com.ohgiraffers.dosirak.admin.suitBox.model.dto.SuitBoxMenuDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface AdminSuitBoxDAO {
    List<SuitBoxMenuDTO> getSuitBoxMenu();

    int registMenu(SuitBoxMenuDTO menu);

    SuitBoxMenuDTO menuFindByMenuCode(int menuCode);

    int modifyMenu(SuitBoxMenuDTO menu, int menuCode);

    int deleteMenu(int menuCode);

    List<SuitBoxMenuDTO> searchMenuByCondition(Map<String ,String > condition);
}
