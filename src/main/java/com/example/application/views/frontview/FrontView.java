package com.example.application.views.frontview;


import com.vaadin.flow.component.dependency.StyleSheet;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.FlexLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Menu;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.StreamResource;
import com.vaadin.flow.server.auth.AnonymousAllowed;
import de.mekaso.vaadin.addon.compani.animation.Animation;
import de.mekaso.vaadin.addon.compani.animation.AnimationBuilder;
import de.mekaso.vaadin.addon.compani.animation.AnimationTypes;
import de.mekaso.vaadin.addon.compani.effect.*;


@AnonymousAllowed
@PageTitle("Front")
@Route(value = "front", autoLayout = false)
@Menu(order = 2, icon = "line-awesome/svg/clock.svg")
@StyleSheet(Animation.STYLES)
public class FrontView extends FlexLayout{

    public FrontView() {

        double major = 1342223.45; // Valor simulado; puedes reemplazarlo con el valor guardado real

        double grand = 1105000.22; // Valor simulado; puedes reemplazarlo con el valor guardado real

        double minor = grand -200000; // Valor simulado; puedes reemplazarlo con el valor guardado real

        double mini = minor - 60000; // Valor simulado; puedes reemplazarlo con el valor guardado real
        // Imagen de fondo
        StreamResource imageResource = new StreamResource("myimage.png",
                () -> getClass().getResourceAsStream("/images/image.png"));
        Image backgroundImage = new Image(imageResource, "My Alt Image");
        backgroundImage.setSizeFull();
        backgroundImage.getStyle().set("position", "absolute").set("top", "0").set("left", "0").set("z-index", "-1");




        // Número en el centro de la pantalla
        VerticalLayout MajorLayout = new VerticalLayout();
        // MajorLayout.setSizeFull();
        Paragraph numeroTexto = new Paragraph("$" + String.valueOf(major));
        numeroTexto.addClassName("major");
        numeroTexto.getStyle().setFontSize("150px");
        numeroTexto.getStyle().setFontWeight("900");

        numeroTexto.getStyle().setColor("white");
        numeroTexto.getStyle().setMarginTop("4.05rem");
        MajorLayout.setAlignItems(Alignment.CENTER);

      /*  AnimationBuilder
                .createBuilderFor(numeroTexto)
                .create(AnimationTypes.TextAnimation.class)
                .withEffect(TextDisplayEffect.JoltZoom)
                .start();*/
        MajorLayout.add(numeroTexto);

        VerticalLayout GrandLayout = new VerticalLayout();
        //GrandLayout.setSizeFull();
        Paragraph numeroTexto1 = new Paragraph("$" + String.valueOf(grand));
        numeroTexto1.addClassName("grand");
        numeroTexto1.getStyle().setFontSize("110px");
        numeroTexto1.getStyle().setFontWeight("900");

        numeroTexto1.getStyle().setColor("white");
        numeroTexto1.getStyle().setMarginTop("-2.0rem");


        GrandLayout.add(numeroTexto1);
       /* AnimationBuilder
                .createBuilderFor(numeroTexto1)
                .create(AnimationTypes.TextAnimation.class)
                .withEffect(TextDisplayEffect.JoltZoom)
                .start();*/
        // Estilos del layout
        // setSizeFull();
        setAlignItems(Alignment.CENTER);
        GrandLayout.setAlignItems(Alignment.CENTER);


        HorizontalLayout MM = new HorizontalLayout();
        //GrandLayout.setSizeFull();
        Paragraph numeroTexto3 = new Paragraph("$" + String.valueOf(minor));
        numeroTexto3.addClassName("minor");
        numeroTexto3.getStyle().setFontSize("110px");
        numeroTexto3.getStyle().setFontWeight("900");

        numeroTexto3.getStyle().setColor("white");
        numeroTexto3.getStyle().setMarginRight("50px");
        numeroTexto3.getStyle().setPaddingLeft("0.5rem");
        numeroTexto3.getStyle().setPaddingTop("3.0rem");

        GrandLayout.add(numeroTexto1);
        /*AnimationBuilder
                .createBuilderFor(numeroTexto1)
                .create(AnimationTypes.TextAnimation.class)
                .withEffect(TextDisplayEffect.JoltZoom)
                .start();*/
        // Estilos del layout
        // setSizeFull();

        Paragraph numeroTexto4 = new Paragraph("$" + String.valueOf(mini));
        numeroTexto4.addClassName("mini");
        numeroTexto4.getStyle().setFontSize("110px");
        numeroTexto4.getStyle().setFontWeight("900");
        numeroTexto4.getStyle().setColor("white");
        numeroTexto4.getStyle().setPaddingRight("0.5rem");
        numeroTexto4.getStyle().setPaddingTop("3.0rem");


      /*  AnimationBuilder
                .createBuilderFor(numeroTexto1)
                .create(AnimationTypes.TextAnimation.class)
                .withEffect(TextDisplayEffect.JoltZoom)
                .start();*/
        // Estilos del layout
        // setSizeFull();

        MM.setJustifyContentMode(JustifyContentMode.EVENLY);
        MM.add(numeroTexto3,numeroTexto4);
        setAlignItems(Alignment.CENTER);
        //setJustifyContentMode(JustifyContentMode.END);
        getStyle()
                .set("position", "relative")
                .set("background-image", "url('/images/image.png')")
                .set("background-size", "cover")
                .set("background-position", "center");



        // Añadir los componentes al layout
        add(backgroundImage, MajorLayout,GrandLayout,MM);
    }
}
