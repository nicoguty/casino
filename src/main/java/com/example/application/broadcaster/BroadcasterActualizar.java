package com.example.application.broadcaster;

import com.vaadin.flow.component.UI;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Consumer;

public class BroadcasterActualizar {

    // Cambiamos a un mapa para asociar cada UI con su listener
    private static final Map<UI, Consumer<Boolean>> listeners = new LinkedHashMap<>();

    /**
     * Registra un listener asociado a una instancia de UI.
     *
     * @param ui       La instancia de UI asociada.
     * @param listener El listener que recibirá los eventos.
     */
    public static synchronized void register(UI ui, Consumer<Boolean> listener) {
        listeners.put(ui, listener);
    }

    /**
     * Elimina el registro de una UI y su listener asociado.
     *
     * @param ui La instancia de UI a eliminar.
     */
    public static synchronized void unregister(UI ui) {
        listeners.remove(ui);
    }

    /**
     * Envía un mensaje a todos los listeners registrados.
     * Garantiza que las actualizaciones se realicen en el contexto de la UI correspondiente.
     *
     * @param message El mensaje a enviar.
     */
    public static synchronized void broadcast(Boolean message) {
        for (Map.Entry<UI, Consumer<Boolean>> entry : listeners.entrySet()) {
            UI ui = entry.getKey();
            Consumer<Boolean> listener = entry.getValue();

            // Aseguramos que la ejecución ocurra en el hilo correcto
            ui.access(() -> listener.accept(message));
        }
    }
}