package com.ohgiraffers.dosirak.admin.suitBox.model.service;

import com.ohgiraffers.dosirak.admin.suitBox.model.dao.AdminSuitBoxDAO;
import com.ohgiraffers.dosirak.admin.suitBox.model.dto.SuitBoxMenuDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class AdminSuitBoxService {
    private final AdminSuitBoxDAO mapper;
    public AdminSuitBoxService(AdminSuitBoxDAO mapper) {
        this.mapper = mapper;
    }

    public List<SuitBoxMenuDTO> getSuitBoxMenu() {
        return mapper.getSuitBoxMenu();
    }

    public int registMenu(SuitBoxMenuDTO menu) {
        return mapper.registMenu(menu);
    }

    public SuitBoxMenuDTO menuFindByMenuCode(int menuCode) {
        return mapper.menuFindByMenuCode(menuCode);
    }

    public int modifyMenu(SuitBoxMenuDTO menu, int menuCode) {
        return mapper.modifyMenu(menu,menuCode);
    }

    public int deleteMenu(int menuCode) {
        return mapper.deleteMenu(menuCode);
    }

    public List<SuitBoxMenuDTO> searchMenuByCondition(Map<String ,String > condition) {
        return mapper.searchMenuByCondition(condition);
    }
}
