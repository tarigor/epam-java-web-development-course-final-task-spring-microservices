package com.epam.apigatewayui.service;


import com.epam.apigatewayui.entity.Menu;
import com.epam.apigatewayui.types.MenuRole;

import java.io.IOException;
import java.util.ArrayList;

public interface ISiteMenuService {

    ArrayList<Menu> getMenuListCollectedByRoleSortedByID(MenuRole... menuRole);

    Menu getMenu(String menuItem) throws IOException;
}
