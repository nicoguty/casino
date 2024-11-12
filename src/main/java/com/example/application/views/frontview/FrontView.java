package com.example.application.views.frontview;


import com.vaadin.flow.component.dependency.StyleSheet;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.FlexLayout;
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
@StyleSheet(Animation.STYLES)
public class FrontView extends FlexLayout{

    public FrontView() {

        double numeroGuardado = 123.45; // Valor simulado; puedes reemplazarlo con el valor guardado real

        // Imagen de fondo
        StreamResource imageResource = new StreamResource("myimage.png",
                () -> getClass().getResourceAsStream("/images/image.png"));
        Image backgroundImage = new Image(imageResource, "My Alt Image");
        backgroundImage.setSizeFull();
        backgroundImage.getStyle().set("position", "absolute").set("top", "0").set("left", "0").set("z-index", "-1");

        // Número en el centro de la pantalla
        Paragraph numeroTexto = new Paragraph(String.valueOf(numeroGuardado));
        numeroTexto.getStyle().setFontSize("150px");
        numeroTexto.getStyle().setColor("white");
        numeroTexto.getStyle().setMarginRight("50px");

        AnimationBuilder
                .createBuilderFor(numeroTexto)
                .create(AnimationTypes.TextAnimation.class)
                .withEffect(TextDisplayEffect.Snake)
                .start();

        // Estilos del layout
        setSizeFull();
        setAlignItems(Alignment.CENTER);
        setJustifyContentMode(JustifyContentMode.END);
        getStyle()
                .set("position", "relative")
                .set("background-image", "url('/images/image.png')")
                .set("background-size", "cover")
                .set("background-position", "center");

        // Añadir los componentes al layout
        add(backgroundImage, numeroTexto);
    }
}
