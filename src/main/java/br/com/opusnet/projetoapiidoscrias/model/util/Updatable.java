package br.com.opusnet.projetoapiidoscrias.model.util;

import java.io.IOException;

public interface Updatable {
    void update() throws IOException;
    void render();
}
