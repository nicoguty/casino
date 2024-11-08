package com.example.application.views.gestioncontador;

import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Menu;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import jakarta.annotation.security.PermitAll;

@PageTitle("Gestion Contador")
@Route("")
@Menu(order = 0, icon = "line-awesome/svg/home-solid.svg")
@PermitAll
public class GestionContadorView extends Composite<VerticalLayout> {

    public GestionContadorView() {
        getContent().setWidth("100%");
        getContent().getStyle().set("flex-grow", "1");
    }
}
