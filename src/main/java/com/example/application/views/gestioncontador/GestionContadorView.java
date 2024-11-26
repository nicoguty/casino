package com.example.application.views.gestioncontador;

import com.example.application.data.sistema.Estado;
import com.example.application.data.sistema.OrdenPromocion;
import com.example.application.data.sistema.Promocion;
import com.example.application.services.LogsService;
import com.example.application.services.PromocionService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.confirmdialog.ConfirmDialog;
import com.vaadin.flow.component.datetimepicker.DateTimePicker;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.binder.ValidationException;
import com.vaadin.flow.data.validator.DateTimeRangeValidator;
import com.vaadin.flow.data.validator.DoubleRangeValidator;
import com.vaadin.flow.router.Menu;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import jakarta.annotation.security.PermitAll;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@PageTitle("Gestion de Promociones")
@Route("")
@Menu(order = 0, icon = "line-awesome/svg/home-solid.svg")
@PermitAll
public class GestionContadorView extends VerticalLayout {

    private Grid<Promocion> gridPromocionesActivas = new Grid<Promocion>(Promocion.class,false);
    private List<Promocion> listaPromociones = new ArrayList<>();
    private Double puntosNumero =0d;
    private Double montoNumero =0d;
    private Paragraph resultado = new Paragraph("Complete los campos para determinar el factor calculado.");
    private Binder<Promocion> binder = new Binder<>(Promocion.class);


    private LogsService logsService;
    private PromocionService promocionService;

    public GestionContadorView(LogsService logsService, PromocionService promocionService) {
        this.logsService = logsService;
        this.promocionService = promocionService;

        setSizeFull();
        getStyle().set("flex-grow", "1");

        crearInterfazPrincipal();
        configurarGrid();
        actualizarListaPromociones();

    }
    private void crearInterfazPrincipal() {
        HorizontalLayout bloquePrincipal = new HorizontalLayout();
        bloquePrincipal.setAlignItems(Alignment.END);

        VerticalLayout columna1 = new VerticalLayout();
        VerticalLayout columna2 = new VerticalLayout();

        columna1.setJustifyContentMode(JustifyContentMode.CENTER);
        columna2.setJustifyContentMode(JustifyContentMode.END);
        columna1.getStyle().set("border", "1px solid grey");

        H2 titulo = new H2("Crear Promoción");
        H2 titulo2 = new H2("Lista de Promociones");

        DateTimePicker fechaInicio = new DateTimePicker("Fecha de inicio");
        DateTimePicker fechaFin = new DateTimePicker("Fecha de finalización");
        NumberField factorConversion = new NumberField("Factor de Conversion");

        // Campos numéricos
        HorizontalLayout layoutNumeros = new HorizontalLayout();

        NumberField montoInicial = new NumberField("Monto Inicial");
        montoInicial.setPrefixComponent(VaadinIcon.DOLLAR.create());

        Button botonCrear = new Button("Nueva Promo");
        botonCrear.setEnabled(false); // Deshabilitado por defecto
        botonCrear.addThemeVariants(ButtonVariant.LUMO_PRIMARY);

        layoutNumeros.setWidth("100%");
        layoutNumeros.setAlignItems(Alignment.END);
        layoutNumeros.setJustifyContentMode(JustifyContentMode.BETWEEN);

        layoutNumeros.add(montoInicial, botonCrear);

        // Enlazar campos con el Binder
        binder.forField(fechaInicio)
                .withValidator(new DateTimeRangeValidator("La fecha de inicio es requerida", LocalDateTime.now(), LocalDateTime.now().plusMonths(2)))
                .asRequired()
                .bind(Promocion::getFechaInicio, Promocion::setFechaInicio);

        binder.forField(fechaFin)
                .withValidator(new DateTimeRangeValidator("La fecha de fin es requerida", LocalDateTime.now(), LocalDateTime.now().plusMonths(2)))
                .asRequired()
                .bind(Promocion::getFechaFin, Promocion::setFechaFin);

        binder.forField(montoInicial)
                .withValidator(new DoubleRangeValidator("El monto inicial debe ser mayor a 10 y menor a 1.000.000", 10D, 10000000D))
                .asRequired()
                .bind(Promocion::getMontoInicial, Promocion::setMontoInicial);

        binder.forField(factorConversion)
                .withValidator(new DoubleRangeValidator("El factor de conversion debe ser mayor a 0 y menor a 100", 0.01D, 50D))
                .asRequired()
                .bind(Promocion::getFactorConversion,Promocion::setFactorConversion);



        // Escuchar cambios en los campos para habilitar/deshabilitar el botón
        binder.addStatusChangeListener(event -> botonCrear.setEnabled(!event.hasValidationErrors()));

        // Configuración del botón "Nueva Promo"
        botonCrear.addClickListener(event -> {
            Promocion promocion = new Promocion();
            try {
                binder.writeBean(promocion); // Transferir datos desde el formulario al objeto
                agregarPromocion(promocion.getFechaInicio(), promocion.getFechaFin(), promocion.getMontoInicial());
            } catch (ValidationException e) {
                Notification.show("Error en el formulario: " + e.getMessage());
            }
        });

        // Agregar elementos al diseño
        HorizontalLayout layoutFechas = new HorizontalLayout(fechaInicio, fechaFin);
        HorizontalLayout layoutCampos = new HorizontalLayout(montoInicial, factorConversion);
        columna1.add(layoutFechas,layoutCampos, layoutNumeros);
        bloquePrincipal.add(columna1, columna2);
        add(titulo, bloquePrincipal, titulo2, gridPromocionesActivas);
    }



    private void configurarGrid() {

        gridPromocionesActivas.addColumn(Promocion::getFechaInicio).setHeader("Fecha Inicio");
        gridPromocionesActivas.addColumn(Promocion::getFechaFin).setHeader("Fecha Fin");
        gridPromocionesActivas.addColumn(Promocion::getMontoInicial).setHeader("Monto Inicial");
        gridPromocionesActivas.addColumn(Promocion::getMontoFinal).setHeader("Monto Final");
        gridPromocionesActivas.addColumn(Promocion::getEstado).setHeader("Estado");
        gridPromocionesActivas.addComponentColumn(promocion -> {
            Select<OrdenPromocion> ordenPromocionSelect = new Select<>();
            ordenPromocionSelect.setItems(OrdenPromocion.values());
            return ordenPromocionSelect;
        }).setHeader("Tipo Premio");

        gridPromocionesActivas.addComponentColumn(Promocion -> {
            HorizontalLayout botones = new HorizontalLayout();
            Button botonIniciar = new Button("Iniciar");
            Button botonPausar = new Button("Pausar");
            botonPausar.setEnabled(false);
            Button botonDetener = new Button("Detener");
            botonDetener.setEnabled(false);
            botonDetener.addThemeVariants(ButtonVariant.LUMO_ERROR, ButtonVariant.LUMO_PRIMARY);
            Button botonCambioFactor = new Button("Editar");
            Button botonPremiar = new Button("Sacar Premio");
            botonIniciar.addClickListener(e -> {
                Notification notification = Notification.show("Promocion Iniciada!");
                notification.addThemeVariants(NotificationVariant.LUMO_SUCCESS);
                botonIniciar.setEnabled(false);
                botonPausar.setEnabled(true);
                botonDetener.setEnabled(true);

            });
            botonDetener.addClickListener(e -> {
                mostrarConfirmacion();
            });
            botonCambioFactor.addClickListener(e -> {
                mostrarMenuEditar();
            });
            botones.add(botonIniciar,botonPausar,botonCambioFactor,botonPremiar,botonDetener);
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

        Button cancelButton = new Button("Detener");
        cancelButton.addThemeVariants(ButtonVariant.LUMO_ERROR);
        dialog.setConfirmButton(cancelButton);
        dialog.addConfirmListener(event -> {
            Notification notification = Notification.show("Promocion Detenida!");
            notification.addThemeVariants(NotificationVariant.LUMO_ERROR);
        });

        dialog.open();
    }

    private void mostrarMenuEditar (){

        Dialog dialog = new Dialog();
        dialog.setHeaderTitle("Editar Factor de conversion.");

        VerticalLayout dialogLayout = new VerticalLayout();
        Paragraph titulo1 = new Paragraph("Factor:");
        TextField factor = new TextField();
        factor.setPrefixComponent(new Paragraph("%"));

        Paragraph titulo2 = new Paragraph("Monto:");
        TextField monto = new TextField();
        monto.setPrefixComponent(new Paragraph("$"));
        Paragraph titulo3 = new Paragraph("Puntos:");
        TextField puntos = new TextField();
        puntos.setPrefixComponent(new Paragraph("-"));

        dialogLayout.add(titulo1,factor,titulo2,monto,titulo3,puntos);
        dialog.add(dialogLayout);

        Button saveButton = new Button("Guardar",e -> {});
        saveButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        Button cancelButton = new Button("Cancel", e -> dialog.close());
        dialog.getFooter().add(cancelButton);
        dialog.getFooter().add(saveButton);

        dialog.open();
    }

    private void actualizarListaPromociones (){

        Page<Promocion> resultado = promocionService.list(Pageable.ofSize(10));
        listaPromociones = resultado.stream().toList();
        gridPromocionesActivas.setItems(listaPromociones);
    }

    private void agregarPromocion(LocalDateTime inicio, LocalDateTime fin, Double monto){
        Promocion promoNueva = new Promocion(inicio, fin, monto, 0d, Estado.NO_INICIADA);
        promocionService.update(promoNueva);
        actualizarListaPromociones();
    }
}
