package com.example.application.views.gestioncontador;

import com.example.application.data.sistema.Estado;
import com.example.application.data.sistema.Promocion;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.confirmdialog.ConfirmDialog;
import com.vaadin.flow.component.datetimepicker.DateTimePicker;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.router.Menu;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import jakarta.annotation.security.PermitAll;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@PageTitle("Gestion de Promociones")
@Route("")
@Menu(order = 0, icon = "line-awesome/svg/home-solid.svg")
@PermitAll
public class GestionContadorView extends VerticalLayout {

    Grid<Promocion> gridPromocionesActivas = new Grid<Promocion>(Promocion.class,false);

    public GestionContadorView() {
        setSizeFull();
        getStyle().set("flex-grow", "1");

        HorizontalLayout bloquePrincipal= new HorizontalLayout();
        bloquePrincipal.setAlignItems(Alignment.END);

        VerticalLayout columna1 = new VerticalLayout();
        VerticalLayout columna2 = new VerticalLayout();

        columna1.setJustifyContentMode(JustifyContentMode.CENTER);
        columna2.setJustifyContentMode(JustifyContentMode.END);
        columna1.getStyle().set("border", "1px solid grey");

        H2 titulo = new H2("Crear Promoción");
        H2 titulo2 = new H2("Lista de Promociones");


        // Campos numérico
        HorizontalLayout layoutNumeros = new HorizontalLayout();
        NumberField montoInicial = new NumberField("Monto Inicial");
        montoInicial.setPrefixComponent(VaadinIcon.DOLLAR.create());
        NumberField factorConversion = new NumberField("Factor de Conversion");
        Button botonCrear = new Button("Nueva Promo", event -> {});
        botonCrear.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        layoutNumeros.setWidth("100%");
        layoutNumeros.setAlignItems(Alignment.END);
        layoutNumeros.setJustifyContentMode(JustifyContentMode.BETWEEN);
        Div contenedor = new Div();
        contenedor.add(montoInicial,factorConversion);
        layoutNumeros.add(contenedor,botonCrear);

        // Campos de fecha
        HorizontalLayout layoutFechas = new HorizontalLayout();
        DateTimePicker fechaInicio = new DateTimePicker("Fecha de inicio");
        DateTimePicker fechaFin = new DateTimePicker("Fecha de fin");
        layoutFechas.add(fechaInicio,fechaFin);


        columna1.add(layoutFechas,layoutNumeros);


        configurarGrid();
        actualizarListaPromociones();


        bloquePrincipal.add(columna1,columna2);
        add(titulo,bloquePrincipal,titulo2, gridPromocionesActivas);
    }

    private void configurarGrid() {

        gridPromocionesActivas.addColumn(Promocion::getFechaInicio).setHeader("Fecha Inicio");
        gridPromocionesActivas.addColumn(Promocion::getFechaFin).setHeader("Fecha Fin");
        gridPromocionesActivas.addColumn(Promocion::getMontoInicial).setHeader("Monto Inicial");
        gridPromocionesActivas.addColumn(Promocion::getMontoFinal).setHeader("Monto Final");
        gridPromocionesActivas.addColumn(Promocion::getEstado).setHeader("Estado");

        gridPromocionesActivas.addComponentColumn(Promocion -> {
            HorizontalLayout botones = new HorizontalLayout();
            Button botonIniciar = new Button("Iniciar");
            Button botonDetener = new Button("Detener");
            Button botonImprimir = new Button("Detalle");
            botonIniciar.addClickListener(e -> {
                Notification notification = Notification.show("Promocion Iniciada!");
                notification.addThemeVariants(NotificationVariant.LUMO_SUCCESS);
            });
            botonDetener.addClickListener(e -> {
                mostrarConfirmacion();
            });
            botonImprimir.addClickListener(e -> {

            });
            botones.add(botonIniciar,botonDetener,botonImprimir);
            return botones;
        }).setHeader("Acciones").setAutoWidth(true);

        gridPromocionesActivas.setSizeFull();

    }

    private void mostrarConfirmacion (){

        ConfirmDialog dialog = new ConfirmDialog();
        dialog.setHeader("¿Seguro desea detener la promocion?");
        dialog.setText("Una vez detenida no es posible reanudarla.");

        dialog.setCancelable(true);
        dialog.addCancelListener(event -> {});

        dialog.setConfirmText("Detener");
        dialog.addConfirmListener(event -> {
            Notification notification = Notification.show("Promocion Detenida!");
            notification.addThemeVariants(NotificationVariant.LUMO_ERROR);
        });

        dialog.open();
    }

    private void actualizarListaPromociones (){
        List<Promocion> listaFicticia = new ArrayList<>();
        listaFicticia.add(new Promocion(LocalDateTime.now(),LocalDateTime.now(),300000d,new ArrayList<>(),0d, Estado.ENCURSO));
        listaFicticia.add(new Promocion(LocalDateTime.now(),LocalDateTime.now(),600000d,new ArrayList<>(),0d,Estado.ENCURSO));
        listaFicticia.add(new Promocion(LocalDateTime.now(),LocalDateTime.now(),500000d,new ArrayList<>(),0d,Estado.DETENIDA));
        gridPromocionesActivas.setItems(listaFicticia);
    }
}
