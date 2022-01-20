package com.epam.apigatewayui.model;


import com.epam.apigatewayui.types.MenuItemDescription;
import com.epam.apigatewayui.types.MenuRole;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Menu {
    private int id;
    private String command;
    private MenuItemDescription menuItemDescription;
    private MenuRole role;
}
