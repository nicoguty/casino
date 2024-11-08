package com.example.application.views.frontview;

import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.FlexLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.StreamResource;
import com.vaadin.flow.server.auth.AnonymousAllowed;

@AnonymousAllowed
@PageTitle("Front")
@Route(value = "front", autoLayout = false)
public class FrontView extends FlexLayout {

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
