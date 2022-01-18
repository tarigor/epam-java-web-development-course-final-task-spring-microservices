package com.epam.apigatewayui.service.impl;

import com.epam.apigatewayui.entity.Menu;
import com.epam.apigatewayui.service.ISiteMenuService;
import com.epam.apigatewayui.types.MenuItemDescription;
import com.epam.apigatewayui.types.MenuRole;
import com.epam.apigatewayui.utility.JsonFileHandler;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Provides the functionality of getting a specific menu item based on factory pattern.
 */
@Component
public class SiteMenuServiceImpl implements ISiteMenuService {

//    public static final String JSON = "./src/main/resources/factory/menu.json";
    public static final String JSON = "factory/menu.json";
    public static final String ID = "id";
    public static final String COMMAND = "command";
    public static final String MENU_ITEM_DESCRIPTION = "menuItemDescription";
    public static final String ROLE = "role";
    private final JsonFileHandler jsonFileHandler;
    HashMap<String, Menu> menuList;

    public SiteMenuServiceImpl(@Value(value = JSON) JsonFileHandler jsonFileHandler) {
        this.jsonFileHandler = jsonFileHandler;
    }

    @PostConstruct
    public void init() throws IOException {

        menuList = new HashMap<>();
        for (MenuItemDescription menuItem : MenuItemDescription.values()) {
            menuList.put(menuItem.name(), getMenu(menuItem.name()));
        }
    }

    @Override
    public ArrayList<Menu> getMenuListCollectedByRoleSortedByID(MenuRole... menuRole) {

        ArrayList<Menu> sortedMenuListByRole = new ArrayList<>();
        for (MenuRole singleMenuRole : menuRole) {
            for (Map.Entry<String, Menu> entry : this.menuList.entrySet()) {
                if (entry.getValue().getRole().equals(singleMenuRole)) {
                    sortedMenuListByRole.add(entry.getValue());
                }
            }
        }
        return sortedMenuListByRole.stream()
                .sorted(Comparator.comparing(o -> ((Integer) o.getId())))
                .collect(Collectors.toCollection(ArrayList::new));
    }

    @Override
    public Menu getMenu(String menuItem) throws IOException {
        int id = Integer.parseInt(((LinkedHashMap) (jsonFileHandler.getMapFromJson().get(menuItem))).get(SiteMenuServiceImpl.ID).toString());
        String pageCommandName = ((LinkedHashMap) jsonFileHandler.getMapFromJson().get(menuItem)).get(SiteMenuServiceImpl.COMMAND).toString();
        MenuItemDescription menuItemDescription = MenuItemDescription.valueOf(((LinkedHashMap) jsonFileHandler.getMapFromJson().get(menuItem)).get(SiteMenuServiceImpl.MENU_ITEM_DESCRIPTION).toString());
        MenuRole menuRole = MenuRole.valueOf(((LinkedHashMap) jsonFileHandler.getMapFromJson().get(menuItem)).get(SiteMenuServiceImpl.ROLE).toString());
        return new Menu(id, pageCommandName, menuItemDescription, menuRole);
    }

}
