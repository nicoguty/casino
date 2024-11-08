package com.example.application.views.gestioncontador;

import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.router.Menu;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import jakarta.annotation.security.PermitAll;

@PageTitle("Gestion Contador")
@Route("")
@Menu(order = 0, icon = "line-awesome/svg/home-solid.svg")
@PermitAll
public class GestionContadorView extends VerticalLayout {

    public GestionContadorView() {
        setWidth("100%");
        getStyle().set("flex-grow", "1");

        // Campo numérico
        NumberField numeroField = new NumberField("Número");

        // Campos de fecha
        DatePicker fechaInicio = new DatePicker("Fecha de inicio");
        DatePicker fechaFin = new DatePicker("Fecha de fin");

        // Botón de resetear
        Button resetButton = new Button("Resetear", event -> numeroField.clear());

        // Botón de guardar
        Button saveButton = new Button("Guardar", event -> {
            // Aquí puedes agregar la lógica para guardar la información
            System.out.println("Número: " + numeroField.getValue());
            System.out.println("Fecha de inicio: " + fechaInicio.getValue());
            System.out.println("Fecha de fin: " + fechaFin.getValue());
        });

        // Añadir componentes al layout
        add(numeroField, fechaInicio, fechaFin, resetButton, saveButton);
    }
}
