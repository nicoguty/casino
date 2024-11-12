package com.example.application.views.InformePromociones;

import com.example.application.data.sistema.Logs;
import com.example.application.data.sistema.TipoMensaje;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Menu;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import jakarta.annotation.security.PermitAll;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@PageTitle("Informes")
@Route("informes")
@Menu(order = 1, icon = "line-awesome/svg/clipboard.svg")
@PermitAll
public class Informes extends VerticalLayout {

    private Grid<Logs> gridLogs = new Grid<>(Logs.class);

    public Informes(){
        setSizeFull();
        getStyle().set("flex-grow", "1");

        actualizarGrid();

        H1 titulo = new H1("Ultimos movimientos del sistema");
        add(titulo,gridLogs);

    }

    private void actualizarGrid() {
        List<Logs> listaLogs = new ArrayList<>();
        listaLogs.add(new Logs(LocalDateTime.now(),"En este momento se hizo algo", TipoMensaje.REGISTRO));
        listaLogs.add(new Logs(LocalDateTime.now(),"En este momento se hizo otra cosa", TipoMensaje.FIN));

        gridLogs.setItems(listaLogs);
    }

}
