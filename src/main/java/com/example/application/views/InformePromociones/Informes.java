package com.example.application.views.InformePromociones;

import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Menu;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import jakarta.annotation.security.PermitAll;

@PageTitle("Informes")
@Route("informes")
@Menu(order = 1, icon = "line-awesome/svg/clipboard.svg")
@PermitAll
public class Informes extends VerticalLayout {

    public Informes(){
        setSizeFull();
        getStyle().set("flex-grow", "1");
    }

}
